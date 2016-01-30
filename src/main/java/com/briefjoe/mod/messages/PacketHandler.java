package com.briefjoe.mod.messages;

import com.briefjoe.mod.Reference;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler
{
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

	public static void init()
	{
		INSTANCE.registerMessage(MessageConfig.class, MessageConfig.class, 0, Side.CLIENT);
		INSTANCE.registerMessage(MessageEmptyTrash.class, MessageEmptyTrash.class, 1, Side.SERVER);
	}
}
