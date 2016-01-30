package com.briefjoe.mod.init.gui;

import com.briefjoe.mod.init.gui.container.ContainerKeyChain;
import com.briefjoe.mod.init.gui.container.ContainerKeyHolder;
import com.briefjoe.mod.init.gui.container.ContainerTrash;
import com.briefjoe.mod.init.items.ItemKeyChain;
import com.briefjoe.mod.init.tileentity.TileEntityKeyHolder;
import com.briefjoe.mod.init.tileentity.TileEntityTrash;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile_entity = world.getTileEntity(new BlockPos(x, y, z));
		
		if (tile_entity instanceof TileEntityTrash)
		{
			return new ContainerTrash(player.inventory, (TileEntityTrash) tile_entity);
		}
		
		if(id == GuiKeyChain.ID)
		{
			return new ContainerKeyChain(player.inventory, ItemKeyChain.getInv(player));
		}
		
		if(id == GuiKeyHolder.ID)
		{
			TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
			if(tileEntity instanceof TileEntityKeyHolder)
			{
				System.out.println("Opened GUI Client Side");
				return new ContainerKeyHolder(player.inventory, (TileEntityKeyHolder) tileEntity);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile_entity = world.getTileEntity(new BlockPos(x, y, z));
		
		if (tile_entity instanceof TileEntityTrash)
		{
			return new GuiTrash(player.inventory, (TileEntityTrash) tile_entity, x, y, z);
		}
		
		if(id == GuiKeyChain.ID)
		{
			return new GuiKeyChain(player.inventory, ItemKeyChain.getInv(player), player);
		}
		
		if(id == GuiKeyHolder.ID)
		{
			TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
			if(tileEntity instanceof TileEntityKeyHolder)
			{
				System.out.println("Opened GUI Server Side");
				return new GuiKeyHolder(player.inventory, (TileEntityKeyHolder) tileEntity);
			}
		}
		return null;
	}
}
