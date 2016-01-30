package com.briefjoe.mod.init.blocks;

import java.util.List;
import java.util.Random;

import com.briefjoe.mod.init.items.BriefjoeItems;
import com.briefjoe.mod.init.tileentity.TileEntityCookieJar;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCookieJar extends Block implements ITileEntityProvider
{
	public static final PropertyInteger COOKIE_COUNT = PropertyInteger.create("cookie_count", 0, 6);

	public BlockCookieJar(Material material)
	{
		super(material);
		setHardness(0.5F);
		setStepSound(Block.soundTypeGlass);
		this.setDefaultState(this.blockState.getBaseState().withProperty(COOKIE_COUNT, Integer.valueOf(0)));
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
	public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state)
	{
		if (!world.isRemote)
		{
			for (int i = 0; i < getMetaFromState(state); i++)
			{
				EntityItem cookie = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5, new ItemStack(Items.cookie));
				world.spawnEntityInWorld(cookie);
			}
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		int metadata = getMetaFromState(state);
		ItemStack currentItem = player.getCurrentEquippedItem();
		if (currentItem != null)
		{
			if (currentItem.getItem() == Items.cookie && metadata < 6)
			{
				world.setBlockState(pos, state.withProperty(COOKIE_COUNT, metadata + 1), 2);
				currentItem.stackSize--;
				world.updateComparatorOutputLevel(pos, this);
				return true;
			}
		}
		if (metadata > 0)
		{
			world.setBlockState(pos, state.withProperty(COOKIE_COUNT, metadata - 1), 2);
			if (!world.isRemote)
			{
				EntityItem var14 = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5, new ItemStack(Items.cookie));
				world.spawnEntityInWorld(var14);
			}
			world.markBlockForUpdate(pos);
			return true;
		}
		return false;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, BlockPos pos)
	{
		setBlockBounds(4F * 0.0625F, 0.0F, 4F * 0.0625F, 12F * 0.0625F, 0.65F, 12F * 0.0625F);
	}

	@Override
	public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
	{
		setBlockBounds(4F * 0.0625F, 0.0F, 4F * 0.0625F, 12F * 0.0625F, 0.625F, 12F * 0.0625F);
		super.addCollisionBoxesToList(world, pos, state, mask, list, collidingEntity);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityCookieJar();
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return BriefjoeItems.item_cookie_jar;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
	{
		return new ItemStack(BriefjoeItems.item_cookie_jar);
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return state.withProperty(COOKIE_COUNT, 0);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((Integer) state.getValue(COOKIE_COUNT)).intValue();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(COOKIE_COUNT, meta);
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { COOKIE_COUNT });
	}

	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.TRANSLUCENT;
	}
	
	@Override
	public boolean hasComparatorInputOverride() 
	{
		return true;
	}
	
	@Override
	public int getComparatorInputOverride(World world, BlockPos pos) 
	{
		TileEntityCookieJar jar = (TileEntityCookieJar) world.getTileEntity(pos);
		return jar.getSize();
	}
}
