package com.briefjoe.mod.init.items;

import com.briefjoe.mod.BriefjoeMod;
import com.briefjoe.mod.Reference;
import com.briefjoe.mod.init.blocks.BriefjoeBlocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BriefjoeItems {
	
	//nether stuff
	public static Item nether_stick;
	public static Item netherrite;
	
	
	public static Item item_log;
	
	public static Item itemtrampoline1,itemtrampoline2,itemtrampoline3,itemtrampolineop;
	
	public static Item key,key_chain;
	//public static Item key_namer;
	
	public static Item itemTrashCan;
	
	public static Item item_cookie_jar;
	
	public static Item quick_glue;
	public static Item liquid_repairium;
	
	public static Item orb_fruit;
	
	public static void init(){
		//nether stuff
		nether_stick = new ItemNetherStick().setUnlocalizedName("nether_stick").setCreativeTab(BriefjoeMod.tabBriefjoe);
		netherrite = new Item().setUnlocalizedName("netherrite").setCreativeTab(BriefjoeMod.tabBriefjoe);
		
		item_log = new ItemLog(BriefjoeBlocks.log_pile).setUnlocalizedName("item_log").setCreativeTab(BriefjoeMod.tabBriefjoe);
		
		itemtrampoline1 = new ItemPlacer(BriefjoeBlocks.blocktrampoline1).setUnlocalizedName("itemtrampoline1").setCreativeTab(BriefjoeMod.tabBriefjoe);
		itemtrampoline2 = new ItemPlacer(BriefjoeBlocks.blocktrampoline2).setUnlocalizedName("itemtrampoline2").setCreativeTab(BriefjoeMod.tabBriefjoe);
		itemtrampoline3 = new ItemPlacer(BriefjoeBlocks.blocktrampoline3).setUnlocalizedName("itemtrampoline3").setCreativeTab(BriefjoeMod.tabBriefjoe);
		itemtrampolineop = new ItemPlacer(BriefjoeBlocks.blocktrampolineop).setUnlocalizedName("itemtrampolineop").setCreativeTab(BriefjoeMod.tabBriefjoe);
		
		key = new ItemKey().setUnlocalizedName("key").setCreativeTab(BriefjoeMod.tabBriefjoe);
		key_chain = new ItemKeyChain().setUnlocalizedName("key_chain").setCreativeTab(BriefjoeMod.tabBriefjoe);
		//key_namer = new ItemKeyNamer().setUnlocalizedName("key_namer").setCreativeTab(BriefjoeMod.tabBriefjoe);
		
		itemTrashCan = new ItemPlacer(BriefjoeBlocks.blocktrash_bin).setUnlocalizedName("itemTrashCan").setCreativeTab(BriefjoeMod.tabBriefjoe);
		
		item_cookie_jar = new ItemPlacer(BriefjoeBlocks.block_cookie_jar).setUnlocalizedName("item_cookie_jar").setCreativeTab(BriefjoeMod.tabBriefjoe);
		
		quick_glue = new Item().setUnlocalizedName("quick_glue").setCreativeTab(BriefjoeMod.tabBriefjoe);
		liquid_repairium = new Item().setUnlocalizedName("liquid_repairium").setCreativeTab(BriefjoeMod.tabBriefjoe);
		
		orb_fruit = new Item().setUnlocalizedName("orb_fruit").setCreativeTab(BriefjoeMod.tabBriefjoe);
	}
	
	public static void register(){
		//nether stuff
		GameRegistry.registerItem(nether_stick, nether_stick.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(netherrite, netherrite.getUnlocalizedName().substring(5));
		
		GameRegistry.registerItem(item_log, item_log.getUnlocalizedName().substring(5));
		
		GameRegistry.registerItem(itemtrampoline1, itemtrampoline1.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(itemtrampoline2, itemtrampoline2.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(itemtrampoline3, itemtrampoline3.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(itemtrampolineop, itemtrampolineop.getUnlocalizedName().substring(5));
		
		GameRegistry.registerItem(key, key.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(key_chain, key_chain.getUnlocalizedName().substring(5));
		//GameRegistry.registerItem(key_namer, key_namer.getUnlocalizedName().substring(5));
		
		GameRegistry.registerItem(itemTrashCan, itemTrashCan.getUnlocalizedName().substring(5));
		
		GameRegistry.registerItem(item_cookie_jar, item_cookie_jar.getUnlocalizedName().substring(5));
		
		GameRegistry.registerItem(quick_glue, quick_glue.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(liquid_repairium, liquid_repairium.getUnlocalizedName().substring(5));
		
		GameRegistry.registerItem(orb_fruit, orb_fruit.getUnlocalizedName().substring(5));
	}
	
	public static void registerRenders(){
		//nether stuff
		registerRender(nether_stick);
		registerRender(netherrite);
		
		registerRender(item_log);
		
		registerRender(itemtrampoline1);
		registerRender(itemtrampoline2);
		registerRender(itemtrampoline3);
		registerRender(itemtrampolineop);
		
		registerRender(key);
		registerRenderVariants(key_chain, 4);
		//registerRender(key_namer);
		
		registerRender(itemTrashCan);
		
		registerRender(item_cookie_jar);
		
		registerRender(quick_glue);
		registerRender(liquid_repairium);
		
		registerRender(orb_fruit);
	}
	
	public static void registerRender(Item item){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
	public static void registerRenderVariants(Item item, int amount) 
	{
		String[] variants = new String[amount];
		for(int i = 0; i < amount; i++)
		{
			variants[i] = Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5) + "_" + i;
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, i, new ModelResourceLocation(variants[i], "inventory"));
		}
		ModelBakery.addVariantName(item, variants);
	}
}
