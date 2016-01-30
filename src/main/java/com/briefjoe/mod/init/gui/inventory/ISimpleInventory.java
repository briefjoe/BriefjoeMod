package com.briefjoe.mod.init.gui.inventory;

import net.minecraft.item.ItemStack;

public abstract interface ISimpleInventory {
	
	public int getSize();

	public ItemStack getItem(int i);
	
	public void clear();
}
