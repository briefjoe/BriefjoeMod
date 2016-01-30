package com.briefjoe.mod.init.blocks;

import java.util.Random;

import com.briefjoe.mod.init.items.BriefjoeItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockLogPile extends BlockLogs
{
	private IBlockState state;
	public BlockLogPile(Material materialIn) 
	{
		super(materialIn);
		this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, 0));
	}

	@Override
	public boolean isBurning() 
	{
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) 
	{	
		
		int metadata = getMetaFromState(state);
		ItemStack currentItem = playerIn.getCurrentEquippedItem();
		
		if(playerIn.isSneaking()){
		if (metadata >= 1)
		{
				worldIn.setBlockState(pos, state.withProperty(STAGE, metadata - 1), 2);
				if (!worldIn.isRemote)
				{
					EntityItem var14 = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5, new ItemStack(BriefjoeItems.item_log));
					worldIn.spawnEntityInWorld(var14);
					worldIn.playSoundEffect((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, Block.soundTypeWood.getPlaceSound(), 1.0F, 0.4F + 0.8F);
				}
				worldIn.markBlockForUpdate(pos);
				return true;
			}
			else if (metadata <= 0){
				if (!worldIn.isRemote)
				{
					EntityItem var14 = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5, new ItemStack(BriefjoeItems.item_log));
					worldIn.spawnEntityInWorld(var14);
					worldIn.setBlockToAir(pos);
					worldIn.playSoundEffect((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, Block.soundTypeWood.getPlaceSound(), 1.0F, 0.4F + 0.8F);
				}
			}
		}
		return false;
	}
	
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) 
	{
		return getMetaFromState(state) + 1;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return BriefjoeItems.item_log;
	}
}