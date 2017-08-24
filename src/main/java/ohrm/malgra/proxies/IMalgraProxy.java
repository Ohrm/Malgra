package ohrm.malgra.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public interface IMalgraProxy {
    void PreInit(FMLPreInitializationEvent e);

    void Init(FMLInitializationEvent e);

    void PostInit(FMLPostInitializationEvent e);

    EntityPlayer getPlayerEntity(MessageContext ctx);

    IThreadListener getThreadFromContext(MessageContext ctx);
}
