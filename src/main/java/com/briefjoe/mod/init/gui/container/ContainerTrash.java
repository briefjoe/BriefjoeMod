package com.briefjoe.mod.init.gui.container;

import com.briefjoe.mod.init.tileentity.TileEntityTrash;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTrash extends Container
{
	private IInventory trashInventory;

	public ContainerTrash(IInventory playerInventory, IInventory trashInventory)
	{
		this.trashInventory = trashInventory;
		this.trashInventory.openInventory(null);

		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				this.addSlotToContainer(new Slot(trashInventory, j + i * 3, j * 18 + 62, i * 18 + 18));
			}
		}

		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, j * 18 + 8, i * 18 + 115));
			}
		}

		for (int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(playerInventory, i, i * 18 + 8, 173));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slot)
	{
		ItemStack var3 = null;
		Slot var4 = (Slot) this.inventorySlots.get(slot);

		if (var4 != null && var4.getHasStack())
		{
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();

			if (slot < 12)
			{
				if (!this.mergeItemStack(var5, 12, this.inventorySlots.size(), true))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(var5, 0, 12, false))
			{
				return null;
			}

			if (var5.stackSize == 0)
			{
				var4.putStack((ItemStack) null);
			}
			else
			{
				var4.onSlotChanged();
			}
		}

		return var3;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer)
	{
		return this.trashInventory.isUseableByPlayer(entityPlayer);
	}

	@Override
	public void onContainerClosed(EntityPlayer entityPlayer)
	{
		super.onContainerClosed(entityPlayer);
		trashInventory.closeInventory(entityPlayer);
		
	}

}
