package com.briefjoe.mod.util;

import com.briefjoe.mod.BriefjoeMod;
import com.briefjoe.mod.messages.MessageConfig;
import com.briefjoe.mod.messages.PacketHandler;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class SyncEvent {
	
	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent event)
	{
		if (BriefjoeMod.proxy.isDedicatedServer())
		{
			PacketHandler.INSTANCE.sendTo(new MessageConfig(), (EntityPlayerMP) event.player);
		}
	}
}
