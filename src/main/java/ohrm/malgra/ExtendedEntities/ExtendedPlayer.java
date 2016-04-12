package ohrm.malgra.ExtendedEntities;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.sun.org.glassfish.external.arc.Stability;

import akka.actor.dsl.Inbox.Get;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import ohrm.malgra.OhrmsMagicMain;
import ohrm.malgra.packets.PacketDispatcher;
import ohrm.malgra.packets.client.SyncPlayerPropsMessage;

public class ExtendedPlayer implements IExtendedEntityProperties{
	
	public final static String OHRMS_MAGIC_MANA = "ohrmsMagicMana";
	
	private final EntityPlayer player;
	public static final int MANA_WATCHER = 20;
	
	private int currentMana, maxMana;
	
	public ExtendedPlayer(EntityPlayer player) {
		
		this.player = player;
		currentMana = 0;
		maxMana = 0;
		this.player.getDataWatcher().addObject(MANA_WATCHER, this.maxMana);
		
	}
	
	public static final void Register(EntityPlayer player){
		
		player.registerExtendedProperties(OHRMS_MAGIC_MANA, new ExtendedPlayer(player));
		
	}

	public static final ExtendedPlayer Get(EntityPlayer player){
		
		return (ExtendedPlayer)player.getExtendedProperties(OHRMS_MAGIC_MANA);
		
	}
	
	public void copy(ExtendedPlayer props) {
		player.getDataWatcher().updateObject(MANA_WATCHER, 0);
		maxMana = props.maxMana;
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
	
		NBTTagCompound properties = new NBTTagCompound();
		
		properties.setInteger("CurrentMana", player.getDataWatcher().getWatchableObjectInt(MANA_WATCHER));
		properties.setInteger("MaxMana", maxMana);
		
		compound.setTag(OHRMS_MAGIC_MANA, properties);
		
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		
		NBTTagCompound properties = (NBTTagCompound)compound.getTag(OHRMS_MAGIC_MANA);
		
		player.getDataWatcher().updateObject(MANA_WATCHER, properties.getInteger("CurrentMana"));
		this.maxMana = properties.getInteger("MaxMana");
		
		System.out.println("[Ohrm's Magic] Mana from NBT: " + this.currentMana + "/" + this.maxMana);
		
	}

	@Override
	public void init(Entity entity, World world) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Returns true and consumes the required amount of mana if the player has enough.
	 * Else return false and consume one mana if possible
	 * @param amount Amount to consume
	 * @return true if player has enough mana
	 */
	public boolean ConsumeMana(int amount){
		
		if(GetMana() < amount){
			if(GetMana() >= 1)
				SetCurrentMana(GetMana() - 1);
			return false;
		}
		else{
			SetCurrentMana(GetMana() - amount);
			return true;
		}
	}

	public void AddMana(int amount){
		
		if(GetMana() + amount > GetMaxMana())
			SetCurrentMana(GetMaxMana());
		else
			SetCurrentMana(GetMana() + amount);
		
	}
	
	public final void SetCurrentMana(int amount) {
		player.getDataWatcher().updateObject(MANA_WATCHER, amount > 0 ? (amount < maxMana ? amount : maxMana) : 0);
	}
	
	public int GetMana(){
		
		return player.getDataWatcher().getWatchableObjectInt(MANA_WATCHER);
		
	}
	
	public int GetMaxMana(){
		
		return maxMana;
		
	}
	
	public final void SetMaxMana(int amount) {
		maxMana = (amount > 0 ? amount : 0);
		// if your extended properties contains a lot of data, it would be better
		// to make an individual packet for maxMana, rather than sending all of
		// the data each time max mana changes...
		
		PacketDispatcher.sendTo(new SyncPlayerPropsMessage(player), (EntityPlayerMP) player);
	}
	
}
