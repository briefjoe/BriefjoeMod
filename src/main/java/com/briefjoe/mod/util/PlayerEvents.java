package com.briefjoe.mod.util;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class PlayerEvents {

	private final String PREFIX = "-> ";

	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent e)
	{
		System.out.println("Hi");
		EntityPlayer player = (EntityPlayer) e.player;
		//player.triggerAchievement(FurnitureAchievements.installMod);
		if (ConfigurationHandler.canDisplay)
		{
			if (!player.worldObj.isRemote)
			{
				if (!ConfigurationHandler.hasDisplayedOnce)
				{
					ChatComponentText prefix = new ChatComponentText(EnumChatFormatting.GOLD + "Thank you for downloading briefjoe's Utility mod!");
					//prefix.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("You can disable this login message in the config")));
					player.addChatMessage(prefix);
					
					ChatComponentText url;
					Random rand = new Random();
					int sel = rand.nextInt(3);
					switch(sel)
					{
					/**case 0:
						player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + PREFIX + EnumChatFormatting.GREEN + "Check out all MrCrayfish's Mods"));
						url = new ChatComponentText(EnumChatFormatting.GOLD + PREFIX + EnumChatFormatting.RESET + "mrcrayfish.com");
						url.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://www.mrcrayfish.com/mods.php"));
						url.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Open URL")));
						player.addChatMessage(url);
						break;
					case 1:
						player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + PREFIX + EnumChatFormatting.GREEN + "Check out the Furniture Mod Wiki"));
						url = new ChatComponentText(EnumChatFormatting.GOLD + PREFIX + EnumChatFormatting.RESET + "mrcrayfishs-furniture-mod.wikia.com");
						url.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://mrcrayfishs-furniture-mod.wikia.com/"));
						url.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Open URL")));
						player.addChatMessage(url);
						break;
					case 2:
						player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + PREFIX + EnumChatFormatting.GREEN + "Check out MrCrayfish's YouTube"));
						url = new ChatComponentText(EnumChatFormatting.GOLD + PREFIX + EnumChatFormatting.RESET + "youtube.com/user/MrCrayfishMinecraft");
						url.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.youtube.com/user/MrCrayfishMinecraft"));
						url.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Open URL")));
						player.addChatMessage(url);
						break;*/
					}
					//ConfigurationHandler.hasDisplayedOnce = true;
				}
			}
		}
	}
}
