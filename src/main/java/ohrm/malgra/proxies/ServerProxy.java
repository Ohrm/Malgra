package ohrm.malgra.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ServerProxy implements IMalgraProxy {

	@Override
	public void PreInit(FMLPreInitializationEvent e) {

	}

	@Override
	public void Init(FMLInitializationEvent e){

	}

	@Override
	public void PostInit(FMLPostInitializationEvent e){

	}

	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		return ctx.getServerHandler().player;
	}

	@Override
	public IThreadListener getThreadFromContext(MessageContext ctx) {
		return ctx.getServerHandler().player.getServer();
	}

}
