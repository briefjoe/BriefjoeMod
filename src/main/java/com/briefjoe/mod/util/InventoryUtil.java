package com.briefjoe.mod.util;

import java.util.Random;

import com.briefjoe.mod.init.gui.inventory.ISimpleInventory;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class InventoryUtil {
	
	private static final Random RANDOM = new Random();

	public static void dropInventoryItems(World world, BlockPos pos, ISimpleInventory inv)
	{
		for (int i = 0; i < inv.getSize(); i++)
		{
			ItemStack stack = inv.getItem(i);

			if (stack != null)
			{
				spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
			}
		}
	}

	private static void spawnItemStack(World world, double posX, double posY, double posZ, ItemStack stack)
	{
		float f = RANDOM.nextFloat() * 0.8F + 0.1F;
		float f1 = RANDOM.nextFloat() * 0.8F + 0.1F;
		float f2 = RANDOM.nextFloat() * 0.8F + 0.1F;

		while (stack.stackSize > 0)
		{
			int i = RANDOM.nextInt(21) + 10;

			if (i > stack.stackSize)
			{
				i = stack.stackSize;
			}

			stack.stackSize -= i;
			EntityItem entityitem = new EntityItem(world, posX + (double) f, posY + (double) f1, posZ + (double) f2, new ItemStack(stack.getItem(), i, stack.getMetadata()));

			if (stack.hasTagCompound())
			{
				entityitem.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
			}

			float f3 = 0.05F;
			entityitem.motionX = RANDOM.nextGaussian() * (double) f3;
			entityitem.motionY = RANDOM.nextGaussian() * (double) f3 + 0.20000000298023224D;
			entityitem.motionZ = RANDOM.nextGaussian() * (double) f3;
			world.spawnEntityInWorld(entityitem);
		}
	}
}
