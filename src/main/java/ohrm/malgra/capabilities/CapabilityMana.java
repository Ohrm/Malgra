package ohrm.malgra.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

/**
 * Created by Toby on 12/04/2016.
 */
public class CapabilityMana {

    @CapabilityInject(IMana.class)
    public static final Capability<IMana> MANA = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(IMana.class, new CapabilityMana.Storage(), CapabilityMana.Default.class);
    }

    public interface IMana {
        int getMana();
        int getMaxMana();

        void setMana(int mana);
        void setMaxMana(int max);
        void remMana(int amount);
        void addMana(int amount);
    }

    public static class Storage implements Capability.IStorage<IMana> {

        @Override
        public NBTBase writeNBT(Capability<IMana> capability, IMana instance, EnumFacing side) {
            NBTTagCompound data = new NBTTagCompound();
            data.setInteger("mana", instance.getMana());
            data.setInteger("maxMana", instance.getMaxMana());
            return data;
        }

        @Override
        public void readNBT(Capability<IMana> capability, IMana instance, EnumFacing side, NBTBase nbt) {
            NBTTagCompound data = (NBTTagCompound) nbt;
            instance.setMana(data.getInteger("mana"));
            instance.setMaxMana(data.getInteger("maxMana"));
        }
    }


    public static class Default implements IMana {
        private int mana, maxMana;

        @Override
        public int getMana() {
            return this.mana;
        }

        @Override
        public int getMaxMana() {
            return this.maxMana;
        }

        @Override
        public void setMana(int mana) {
            this.mana = mana;
        }

        @Override
        public void setMaxMana(int max) {
            this.maxMana = max;
        }

        @Override
        public void remMana(int amount) {
            if (this.mana - amount >= 0)
                this.mana -= amount;
            else
                this.mana = 0;
        }

        @Override
        public void addMana(int amount) {
            if (this.mana + amount <= this.maxMana)
                this.mana += amount;
            else
                this.mana = this.maxMana;
        }
    }

}
