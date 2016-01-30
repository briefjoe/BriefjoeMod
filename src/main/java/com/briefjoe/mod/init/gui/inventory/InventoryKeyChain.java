package com.briefjoe.mod.init.gui.inventory;

import java.util.UUID;

import com.briefjoe.mod.init.items.BriefjoeItems;
import com.briefjoe.mod.util.NBTHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryKeyChain extends InventoryBasic 
{
	private EntityPlayer player;
	private ItemStack keychain;
	private boolean reading = false;
	private String uniqueId = "";
	
	public InventoryKeyChain(EntityPlayer player, ItemStack keychain) 
	{
		super("KeyChain", false, getInventorySize());
		this.player = player;
		this.keychain = keychain;
		if(!hasInventory())
		{
			uniqueId = UUID.randomUUID().toString();
			createInventory();
		}
		loadInventory();
	}
	
	@Override
	public void openInventory(EntityPlayer player)
	{
		loadInventory();
	}

	@Override
	public void closeInventory(EntityPlayer player)
	{
		saveInventory();
	}
	
	@Override
	public void markDirty()
	{
		super.markDirty();
		if (!reading)
		{
			saveInventory();
		}
	}
	
	@Override
	public int getInventoryStackLimit() 
	{
		return 1;
	}

	protected static int getInventorySize()
	{
		return 6;
	}
	
	protected boolean hasInventory()
	{
		return NBTHelper.getCompoundTag(keychain, "KeyChain").hasKey("Keys");
	}
	
	protected void createInventory()
	{
		writeToNBT();
	}
	
	public void loadInventory()
	{
		readFromNBT();
	}

	public void saveInventory()
	{
		writeToNBT();
		setNBT();
	}
	
	protected void setNBT()
	{
		for (ItemStack itemStack : player.inventory.mainInventory)
		{
			if (itemStack != null && itemStack.getItem() == BriefjoeItems.key_chain)
			{
				NBTTagCompound nbt = itemStack.getTagCompound();
				if (nbt != null)
				{
					if (nbt.getCompoundTag("KeyChain").getString("UniqueID").equals(uniqueId))
					{
						itemStack.setTagCompound(keychain.getTagCompound());
						break;
					}
				}
			}
		}
	}
	
	protected void writeToNBT()
	{
		NBTTagList tagList = new NBTTagList();
		for (int slot = 0; slot < getSizeInventory(); slot++)
		{
			if (getStackInSlot(slot) != null)
			{
				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setByte("Slot", (byte) slot);
				getStackInSlot(slot).writeToNBT(tagCompound);
				tagList.appendTag(tagCompound);
			}
		}
		NBTTagCompound inventory = new NBTTagCompound();
		inventory.setTag("Keys", tagList);
		inventory.setString("UniqueID", uniqueId);
		NBTHelper.setCompoundTag(keychain, "KeyChain", inventory);
	}
	
	protected void readFromNBT()
	{
		reading = true;
		NBTTagCompound nbt = NBTHelper.getCompoundTag(keychain, "KeyChain");
		if ("".equals(uniqueId))
		{
			this.uniqueId = nbt.getString("UniqueID");
			if ("".equals(uniqueId))
			{
				this.uniqueId = UUID.randomUUID().toString();
			}
		}
		NBTTagList tagList = (NBTTagList) NBTHelper.getCompoundTag(keychain, "KeyChain").getTag("Keys");
		if(tagList != null)
		{
			for (int i = 0; i < tagList.tagCount(); i++)
			{
				NBTTagCompound tagCompound = (NBTTagCompound) tagList.getCompoundTagAt(i);
				int slot = tagCompound.getByte("Slot") & 0xff;
	
				if (slot >= 0 && slot < getSizeInventory())
				{
					setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(tagCompound));
				}
			}
		}
		reading = false;
	}
}