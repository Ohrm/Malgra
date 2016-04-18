package ohrm.malgra.items;

import net.minecraft.item.Item;
import ohrm.malgra.MalgraMain;

/**
 * Created by Toby on 14/04/2016.
 */
public class ExtractorTip extends Item {

    ToolMaterial material;
    String prefix;

    public ExtractorTip(ToolMaterial material, String prefix) {
        this.material = material;
        this.prefix = prefix;
        this.setCreativeTab(MalgraMain.magicTab);
    }

    public ToolMaterial getMaterial() {
        return material;
    }

    public void setMaterial(ToolMaterial material) {
        this.material = material;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
