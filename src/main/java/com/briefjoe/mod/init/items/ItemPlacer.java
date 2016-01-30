package com.briefjoe.mod.init.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemPlacer extends Item{
	
	private Block block;
	private int flag = 3;

	public ItemPlacer(Block block)
	{
		this.block = block;
		//setCreativeTab(BriefjoeMod.tabBriefjoe);
	}
	
	public void setFlag(int flag)
	{
		this.flag = flag;
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		IBlockState iblockstate = world.getBlockState(pos);
		Block block = iblockstate.getBlock();

		if (block == Blocks.snow_layer && ((Integer) iblockstate.getValue(BlockSnow.LAYERS)).intValue() < 1)
		{
			side = EnumFacing.UP;
		}
		else if (!block.isReplaceable(world, pos))
		{
			pos = pos.offset(side);
		}

		if (!player.canPlayerEdit(pos, side, stack))
		{
			return false;
		}
		else if (stack.stackSize == 0)
		{
			return false;
		}
		else
		{
			if (world.canBlockBePlaced(this.block, pos, false, side, (Entity) null, stack))
			{
				IBlockState iblockstate1 = this.block.onBlockPlaced(world, pos, side, hitX, hitY, hitZ, 0, player);

				if (world.setBlockState(pos, iblockstate1, flag))
				{
					iblockstate1 = world.getBlockState(pos);

					if (iblockstate1.getBlock() == this.block)
					{
						ItemBlock.setTileEntityNBT(world, pos, stack);
						iblockstate1.getBlock().onBlockPlacedBy(world, pos, iblockstate1, player, stack);
					}

					world.playSoundEffect((double) ((float) pos.getX() + 0.5F), (double) ((float) pos.getY() + 0.5F), (double) ((float) pos.getZ() + 0.5F), this.block.stepSound.getPlaceSound(), (this.block.stepSound.getVolume() + 1.0F) / 2.0F, this.block.stepSound.getFrequency() * 0.8F);
					--stack.stackSize;
					return true;
				}
			}

			return false;
		}
	}
}
