package com.briefjoe.mod.init.tileentity;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class BriefjoeTileEntities {
	
	public static void register(){
		GameRegistry.registerTileEntity(TileEntityTrash.class, "bjmTrash");
		GameRegistry.registerTileEntity(TileEntityKeyHolder.class, "bjmKeyHolder");
		GameRegistry.registerTileEntity(TileEntityCookieJar.class, "bjmCookieJar");
		GameRegistry.registerTileEntity(TileEntityTable.class, "bjmTable");
		GameRegistry.registerTileEntity(TileEntityHalfTable.class, "bjmHalfTable");
		GameRegistry.registerTileEntity(TileEntityDisplayCase.class, "bjmDisplayCase");
		
	}
}
