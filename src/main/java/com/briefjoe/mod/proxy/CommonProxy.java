package com.briefjoe.mod.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class CommonProxy implements ProxyInterface{
	
	public void registerRenders()
	{

	}

	public World getClientWorld()
	{
		return null;
	}

	public EntityPlayer getClientPlayer()
	{
		return null;
	}
	
	@Override
	public boolean isSinglePlayer()
	{
		return false;
	}

	@Override
	public boolean isDedicatedServer()
	{
		return MinecraftServer.getServer().isDedicatedServer();
	}
	
	@Override
	public void preInit()
	{
		
	}
}
