package com.briefjoe.mod.init.tileentity;

import com.briefjoe.mod.init.gui.inventory.ISimpleInventory;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCookieJar extends TileEntity implements ISimpleInventory
{
	@Override
	public int getSize()
	{
		return getBlockType().getMetaFromState(getWorld().getBlockState(pos));
	}

	@Override
	public ItemStack getItem(int i)
	{
		return new ItemStack(Items.cookie);
	}

	@Override
	public void clear()
	{
	}
}
