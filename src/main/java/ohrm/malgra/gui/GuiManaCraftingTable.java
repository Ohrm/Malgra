package ohrm.malgra.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import ohrm.malgra.tile.TileEntityManaCraftingTable;

public class GuiManaCraftingTable extends GuiContainer{

	/** x and y size of the inventory window in pixels. Defined as float, passed as int
	 *  These are used for drawing the player model. */
	private float xSize_lo;
	private float ySize_lo;

	/** ResourceLocation takes 2 parameters: ModId, path to texture at the location:
	 *  "src/minecraft/assets/modid/" */
	private static final ResourceLocation iconLocation = new ResourceLocation("malgra", "textures/gui/manacraftingtable.png");

	/** The inventory to render on screen */
	private TileEntityManaCraftingTable tileEntity;

	public GuiManaCraftingTable(InventoryPlayer player, TileEntityManaCraftingTable tile)
	{
		super(new ContainerManaCraftingTable(player, tile));
		this.tileEntity = tile;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);
		xSize_lo = mouseX;
		ySize_lo = mouseY;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String s = tileEntity.getName();
		fontRenderer.drawString(I18n.format(s), xSize / 2 - (xSize / 4 + 13), 5, 4210752);
		fontRenderer.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 4, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(iconLocation);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}

}
