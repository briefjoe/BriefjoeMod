package com.briefjoe.mod.util;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockEvents {
	
	@SubscribeEvent
	public void onUseItemEvent(PlayerInteractEvent event)
	{
		/*World world = event.world;
		if(event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
		{
			EntityPlayer player = event.entityPlayer;
			IBlockState state = world.getBlockState(event.pos);
			Block block = state.getBlock();
			if(!(block instanceof BlockFirePit))
				return;
			
			System.out.println("3");
		
			ItemStack current = player.getCurrentEquippedItem();
			
			if(current == null)
				return;
			
			System.out.println("4");
			
			if(current.getItem() instanceof ItemFlintAndSteel)
			{
				if(block.getMetaFromState(state) == 3)
				{
					world.setBlockState(event.pos, state.withProperty(BlockFirePit.STAGE, 4));
					world.markBlockForUpdate(event.pos);
					current.damageItem(1, player);
				}
				event.setCanceled(true);
			}
		}*/
	}
}
