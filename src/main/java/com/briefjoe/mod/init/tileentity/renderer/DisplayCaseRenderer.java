package com.briefjoe.mod.init.tileentity.renderer;

import org.lwjgl.opengl.GL11;

import com.briefjoe.mod.init.tileentity.TileEntityDisplayCase;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;

public class DisplayCaseRenderer extends TileEntitySpecialRenderer
{
	private EntityItem entityItem = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D);

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float p_180535_8_, int p_180535_9_)
	{
		TileEntityDisplayCase table = (TileEntityDisplayCase) tileEntity;
		if (table.getItem() != null)
		{
			entityItem.setEntityItemStack(table.getItem());

			GL11.glPushMatrix();
			this.entityItem.hoverStart = 0.0F;

			float xOffset = 0.0F;
			float zOffset = 0.0F;
			
			switch (table.getRotation())
			{
			case 0:
				zOffset -= 0.15F;
				break;
			case 1:
				xOffset += 0.35F;
				zOffset += 0.2F;
				break;
			case 2:
				zOffset += 0.55F;
				break;
			case 3:
				xOffset -= 0.35F;
				zOffset += 0.2F;
				break;
			}
			
			GL11.glDisable(GL11.GL_LIGHTING);
			WorldRenderer renderer = Tessellator.getInstance().getWorldRenderer();
			renderer.setBrightness(15728880);

			GL11.glTranslatef((float) posX + 0.5F + xOffset, (float) posY + 0.94F, (float) posZ + 0.3F + zOffset);
			GL11.glRotatef(table.getRotation() * -90F, 0, 1, 0);
			GL11.glRotatef(0, 0, 1, 1);
			Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(entityItem, 0.0D, 0.0D, 0.075D, 0.0F, 0.0F);
			
			GL11.glEnable(GL11.GL_LIGHTING);
			
			GL11.glPopMatrix();
		}
	}
}
