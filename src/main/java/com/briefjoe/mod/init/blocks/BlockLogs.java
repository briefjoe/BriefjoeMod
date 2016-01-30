package com.briefjoe.mod.init.blocks;

import java.util.List;

import com.briefjoe.mod.init.items.BriefjoeItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockLogs extends Block {

	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 11); 
	private IBlockState state;
	
	public BlockLogs(Material materialIn) 
	{
		super(materialIn);
		this.setStepSound(Block.soundTypeWood);
		this.setHardness(1.0F);
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) 
	{
		return worldIn.isSideSolid(pos.down(), EnumFacing.UP);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, BlockPos pos)
	{
			setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);;
	}
	
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) 
	{
		setBlockBounds(0F, 0F, 0F, 1F, 1.0F, 1F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
		
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return isBurning() ? 0 : (Integer) state.getValue(STAGE);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return isBurning() ? this.getDefaultState() : this.getDefaultState().withProperty(STAGE, meta);
	}

	@Override
	protected BlockState createBlockState()
	{
		return isBurning() ? super.createBlockState() : new BlockState(this, new IProperty[] { STAGE });
	}
	
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.CUTOUT;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
	{
		return new ItemStack(BriefjoeItems.item_log);
	}
	
	public abstract boolean isBurning();
	
}
