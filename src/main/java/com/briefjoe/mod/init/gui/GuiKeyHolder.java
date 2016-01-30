package com.briefjoe.mod.init.gui;

import com.briefjoe.mod.init.gui.container.ContainerKeyHolder;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiKeyHolder extends GuiContainer 
{	
	public static final int ID = 1;
	
	private static final ResourceLocation gui = new ResourceLocation("bjm:textures/gui/key_holder.png");
	private IInventory inventoryKeys;
	
	public GuiKeyHolder(IInventory inventoryPlayer, IInventory inventoryKeys) 
	{
		super(new ContainerKeyHolder(inventoryPlayer, inventoryKeys));
		this.inventoryKeys = inventoryKeys;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRendererObj.drawString(inventoryKeys.getName(), xSize / 2 - 21, 5, 4210752);
		this.fontRendererObj.drawString("Inventory", 8, (ySize - 96) + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(gui);
		int l = (width - xSize) / 2;
		int i1 = (height - ySize) / 2;
		this.drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
	}
	
	@Override
	public void onGuiClosed()
	{
		super.onGuiClosed();
	}
}
