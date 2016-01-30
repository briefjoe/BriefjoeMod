package com.briefjoe.mod;

import com.briefjoe.mod.event.KeyEvents;
import com.briefjoe.mod.init.blocks.BriefjoeBlocks;
import com.briefjoe.mod.init.gui.GuiHandler;
import com.briefjoe.mod.init.items.BriefjoeItems;
import com.briefjoe.mod.init.tileentity.BriefjoeTileEntities;
import com.briefjoe.mod.init.tileentity.EntitySittableBlock;
import com.briefjoe.mod.messages.PacketHandler;
import com.briefjoe.mod.proxy.CommonProxy;
import com.briefjoe.mod.util.BlockEvents;
import com.briefjoe.mod.util.PlayerEvents;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCMessage;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class BriefjoeMod {
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
    
	public static CreativeTabs tabBriefjoe = new BriefjoeTab("tabBriefjoe");
	
	
	@Instance(Reference.MOD_ID)
	public static BriefjoeMod instance;
	
    @EventHandler
    public void preinit (FMLPreInitializationEvent event){
    	
    	BriefjoeTileEntities.register();
    	
    	BriefjoeBlocks.init();
    	BriefjoeBlocks.register();
    	BriefjoeItems.init();
    	BriefjoeItems.register();
    	
    	PacketHandler.init();
    }
    
    @EventHandler
    public void init (FMLInitializationEvent event){
    	proxy.registerRenders();
    	
    	MinecraftForge.EVENT_BUS.register(new KeyEvents());
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    	
    	//GameRegistry.registerTileEntity(TileEntityKeyHolder.class, "bjmKeyHolder");
    	
    	EntityRegistry.registerModEntity(EntitySittableBlock.class, "MountableBlock", 0, this, 80, 1, false);
    }
    
    @EventHandler
    public void postinit(FMLPostInitializationEvent event){
    	
    	FMLCommonHandler.instance().bus().register(new PlayerEvents());
		MinecraftForge.EVENT_BUS.register(new BlockEvents());
		
    }
    
    @EventHandler
	public void processIMC(FMLInterModComms.IMCEvent event)
	{
		if (event.getMessages().size() > 0)
		{
			
		}
		for (IMCMessage imcMessage : event.getMessages())
		{
			if (!imcMessage.isStringMessage())
				continue;

			if (imcMessage.key.equalsIgnoreCase("register"))
			{
				register(imcMessage.getStringValue(), imcMessage.getSender());
			}
		}
	}
    
    public void register(String method, String modid)
	{
		String[] data = method.split("\\.");
		String methodName = data[data.length - 1];
		String className = method.substring(0, method.length() - methodName.length() - 1);
		String modName = Loader.instance().getIndexedModList().get(modid).getName();
	}
}
