package com.briefjoe.mod.init.gui.slots;

import com.briefjoe.mod.init.items.BriefjoeItems;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotKeyHolder extends Slot {

	public SlotKeyHolder(IInventory inventoryIn, int index, int xPosition, int yPosition) 
	{
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) 
	{
		return (stack.getItem()  == BriefjoeItems.key && stack.hasDisplayName()) | stack.getItem() == BriefjoeItems.key_chain;
	}
}
