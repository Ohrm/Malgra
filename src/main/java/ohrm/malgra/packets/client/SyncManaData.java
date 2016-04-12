package ohrm.malgra.packets.client;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;
import ohrm.malgra.capabilities.CapabilityMana;
import ohrm.malgra.packets.AbstractMessage.AbstractClientMessage;

/**
 * 
 * A packet to send ALL data stored in your extended properties to the client.
 * This is handy if you only need to send your data once per game session or
 * all of your data needs to be synchronized together; it's also handy while
 * first starting, since you only need one packet for everything - however,
 * you should NOT use such a packet in your final product!!!
 * 
 * Each packet should handle one thing and one thing only, in order to minimize
 * network traffic as much as possible. There is no point sending 20+ fields'
 * worth of data when you just need the current mana amount; conversely, it's
 * foolish to send 20 packets for all the data when the player first loads, when
 * you could send it all in one single packet.
 * 
 * TL;DR - make separate packets for each piece of data, and one big packet for
 * those times when you need to send everything.
 *
 */
public class SyncManaData extends AbstractClientMessage<SyncManaData>
{
	// Previously, we've been writing each field in our properties one at a time,
	// but that is really annoying, and we've already done it in the save and load
	// NBT methods anyway, so here's a slick way to efficiently send all of your
	// extended data, and no matter how much you add or remove, you'll never have
	// to change the packet / synchronization of your data.

	// this will store our ExtendedPlayer data, allowing us to easily read and write
	private int mana, maxMana;

	// The basic, no-argument constructor MUST be included to use the new automated handling
	public SyncManaData() {}

	// We need to initialize our data, so provide a suitable constructor:
	public SyncManaData(CapabilityMana.IMana mana) {
		this.mana = mana.getMana();
		this.maxMana = mana.getMaxMana();
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		this.mana = buffer.readInt();
		this.maxMana = buffer.readInt();
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		buffer.writeInt(this.mana);
		buffer.writeInt(this.maxMana);
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		final CapabilityMana.IMana mana = player.getCapability(CapabilityMana.MANA, null);
		mana.setMana(this.mana);
		mana.setMaxMana(this.maxMana);
	}
}