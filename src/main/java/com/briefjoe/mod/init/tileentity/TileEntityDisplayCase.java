package com.briefjoe.mod.init.tileentity;

import com.briefjoe.mod.init.gui.inventory.ISimpleInventory;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDisplayCase extends TileEntity implements ISimpleInventory
{
	private ItemStack item = null;
	private int rotation = 0;

	public void setItem(ItemStack item)
	{
		this.item = item;
	}

	public ItemStack getItem()
	{
		return item;
	}

	public void setRotation(int rotation)
	{
		this.rotation = rotation;
	}

	public int getRotation()
	{
		return rotation;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		if (par1NBTTagCompound.hasKey("Items"))
		{
			NBTTagList tagList = (NBTTagList) par1NBTTagCompound.getTag("Items");
			for (int i = 0; i < tagList.tagCount(); ++i)
			{
				NBTTagCompound nbttagcompound1 = tagList.getCompoundTagAt(i);
				ItemStack stack = ItemStack.loadItemStackFromNBT(nbttagcompound1);
				this.setItem(stack);
			}
		}
		this.rotation = par1NBTTagCompound.getInteger("Rotation");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		NBTTagList tagList = new NBTTagList();
		ItemStack itemStack = item;
		if (itemStack != null)
		{
			NBTTagCompound nbttagcompound1 = new NBTTagCompound();
			itemStack.writeToNBT(nbttagcompound1);
			tagList.appendTag(nbttagcompound1);
		}
		par1NBTTagCompound.setTag("Items", tagList);
		par1NBTTagCompound.setInteger("Rotation", rotation);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		NBTTagCompound tagCom = pkt.getNbtCompound();
		this.readFromNBT(tagCom);
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tagCom = new NBTTagCompound();
		this.writeToNBT(tagCom);
		return new S35PacketUpdateTileEntity(pos, getBlockMetadata(), tagCom);
	}

	@Override
	public int getSize()
	{
		return 1;
	}

	@Override
	public void clear()
	{
		item = null;
	}

	@Override
	public ItemStack getItem(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
