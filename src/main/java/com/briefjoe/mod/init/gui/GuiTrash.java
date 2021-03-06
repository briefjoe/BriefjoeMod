package com.briefjoe.mod.init.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.briefjoe.mod.init.gui.container.ContainerTrash;
import com.briefjoe.mod.init.tileentity.TileEntityTrash;
import com.briefjoe.mod.messages.MessageEmptyTrash;
import com.briefjoe.mod.messages.PacketHandler;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiTrash extends GuiContainer{
	
	
	
	private static final ResourceLocation gui = new ResourceLocation("bjm:textures/gui/trash.png");
	private GuiButton button_empty;
	private int tileX, tileY, tileZ;
	private ItemStack[] trashContents = new ItemStack[12];
	
	public GuiTrash(IInventory playerInventory, IInventory trashInventory, int tileX, int tileY, int tileZ)
	{
		super(new ContainerTrash(playerInventory, trashInventory));
		this.tileX = tileX;
		this.tileY = tileY;
		this.tileZ = tileZ;
		this.xSize = 176;
		this.ySize = 197;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRendererObj.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(gui);
		int l = (width - xSize) / 2;
		int i1 = (height - ySize) / 2;
		this.drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
	}

	@Override
	public void initGui()
	{
		super.initGui();
		Keyboard.enableRepeatEvents(false);
		buttonList.clear();
		int posX = width / 2;
		int posY = height / 2;
		button_empty = new GuiButton(0, posX + 40, posY - 50, 40, 20, "Empty");
		button_empty.enabled = true;
		buttonList.add(button_empty);
		
	}

	@Override
	protected void actionPerformed(GuiButton guibutton)
	{
		if (!guibutton.enabled)
		{
			return;
		}
		if (guibutton.id == 0)
		{
			this.emptyTrash();
		}
	}

	@Override
	protected void mouseClicked(int i, int j, int k)
	{
		try
		{
			super.mouseClicked(i, j, k);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	protected void emptyTrash()
	{
		PacketHandler.INSTANCE.sendToServer(new MessageEmptyTrash(tileX, tileY, tileZ));
	}
}
