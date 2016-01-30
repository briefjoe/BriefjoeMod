package com.briefjoe.mod.init.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.LockCode;
import net.minecraft.world.World;

public class ItemKey extends Item {
	
	public ItemKey() 
	{
		setMaxStackSize(1);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote)
		{
			if(playerIn.isSneaking())
			{
				EntityPlayerMP playerMp = (EntityPlayerMP) playerIn;
				TileEntity tileEntity = worldIn.getTileEntity(pos);
				if(tileEntity instanceof TileEntityLockable)
				{
					TileEntityLockable tileEntityLockable = (TileEntityLockable) tileEntity;
					if(tileEntityLockable.isLocked())
					{	
						if(tileEntityLockable.getLockCode().getLock().equals(stack.getDisplayName()))
						{
							tileEntityLockable.setLockCode(LockCode.EMPTY_CODE);
							playerMp.playerNetServerHandler.sendPacket(new S02PacketChat((new ChatComponentText(EnumChatFormatting.GREEN + "Succesfully unlocked this block.")), (byte)2));
						}
						else
						{
							playerMp.playerNetServerHandler.sendPacket(new S02PacketChat((new ChatComponentText(EnumChatFormatting.YELLOW + "You need to have correct key to unlock this block.")), (byte)2));
						}
					}
				}
			}
		}
		return true;
	}
	
	/*@Override
	@SideOnly(Side.CLIENT)
	public int getMetadata(ItemStack stack) 
	{
		ItemStack inUseStack = Minecraft.getMinecraft().thePlayer.getItemInUse();
		if(inUseStack != null)
		{
			if(inUseStack.getItem() == this)
			{
				return 1;
			}
		}
		return 0;
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        playerIn.setItemInUse(itemStackIn, 72000);
        return itemStackIn;
    }
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) 
	{
		System.out.println(Minecraft.getMinecraft().objectMouseOver.entityHit);
		//playerIn.attackTargetEntityWithCurrentItem(null);
		return super.onItemUseFinish(stack, worldIn, playerIn);
	}*/
}
