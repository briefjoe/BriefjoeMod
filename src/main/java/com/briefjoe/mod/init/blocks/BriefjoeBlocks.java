package com.briefjoe.mod.init.blocks;

import com.briefjoe.mod.BriefjoeMod;
import com.briefjoe.mod.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BriefjoeBlocks {
	
	public static Block netherrite_block;
	
	public static Block log_pile;
	//public static Block fire_pit;
	
	public static Block blocktrampoline1;
	public static Block blocktrampoline2;
	public static Block blocktrampoline3;
	public static Block blocktrampolineop;
	
	public static Block blocktrash_bin;
	
	public static Block sitting;
	
	public static Block block_key_holder;
	
	public static Block block_cookie_jar;
	
	public static Block blockTable,blockHalfTable;
	
	public static Block blockDisplayCase;
	
	//public static Block plant_orb_berry;
	
	public static void init(){
		netherrite_block = new BlockNetherrite(Material.iron).setUnlocalizedName("netherrite_block");
		
		log_pile = new BlockLogPile(Material.wood).setUnlocalizedName("log_pile");
		//fire_pit = new BlockFirePit(Material.wood).setUnlocalizedName("fire_pit");
		
		blocktrampoline1 = new BlockTrampoline1(Material.glass).setUnlocalizedName("blocktrampoline1");
		blocktrampoline2 = new BlockTrampoline2(Material.glass).setUnlocalizedName("blocktrampoline2");
		blocktrampoline3 = new BlockTrampoline3(Material.glass).setUnlocalizedName("blocktrampoline3");
		blocktrampolineop = new BlockTrampolineop(Material.glass).setUnlocalizedName("blocktrampolineop");
		
		blocktrash_bin = new BlockTrash(Material.clay).setUnlocalizedName("blocktrash_bin");
		
		sitting = new BlockSitting(Material.wood).setUnlocalizedName("sitting").setCreativeTab(BriefjoeMod.tabBriefjoe);
		
		block_key_holder = new BlockKeyHolder(Material.wood).setUnlocalizedName("block_key_holder").setCreativeTab(BriefjoeMod.tabBriefjoe);
		
		block_cookie_jar = new BlockCookieJar(Material.glass).setUnlocalizedName("block_cookie_jar");
		
		blockTable = new BlockTable(Material.rock).setUnlocalizedName("blockTable").setCreativeTab(BriefjoeMod.tabBriefjoe);
		blockHalfTable = new BlockHalfTable(Material.rock).setUnlocalizedName("blockHalfTable").setCreativeTab(BriefjoeMod.tabBriefjoe);
		
		blockDisplayCase = new BlockDisplayCase(Material.wood).setUnlocalizedName("blockDisplayCase").setCreativeTab(BriefjoeMod.tabBriefjoe);
		//plant_orb_berry = new BlockOrbBerry(Material.plants);
	}
	
	public static void register(){
		GameRegistry.registerBlock(netherrite_block, netherrite_block.getUnlocalizedName().substring(5));
		
		GameRegistry.registerBlock(log_pile, log_pile.getUnlocalizedName().substring(5));
		//GameRegistry.registerBlock(fire_pit, fire_pit.getUnlocalizedName().substring(5));
		
		GameRegistry.registerBlock(blocktrampoline1, blocktrampoline1.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(blocktrampoline2, blocktrampoline2.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(blocktrampoline3, blocktrampoline3.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(blocktrampolineop, blocktrampolineop.getUnlocalizedName().substring(5));
		
		GameRegistry.registerBlock(blocktrash_bin, blocktrash_bin.getUnlocalizedName().substring(5));
		
		GameRegistry.registerBlock(sitting, sitting.getUnlocalizedName().substring(5));
		
		GameRegistry.registerBlock(block_key_holder, block_key_holder.getUnlocalizedName().substring(5));
		
		GameRegistry.registerBlock(block_cookie_jar, block_cookie_jar.getUnlocalizedName().substring(5));
		
		GameRegistry.registerBlock(blockTable, blockTable.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(blockHalfTable, blockHalfTable.getUnlocalizedName().substring(5));
		
		GameRegistry.registerBlock(blockDisplayCase, blockDisplayCase.getUnlocalizedName().substring(5));
	}
	
	public static void registerRenders(){
		registerRender(netherrite_block);
		
		registerRender(log_pile);
		//registerRender(fire_pit);
		
		registerRender(blocktrampoline1);
		registerRender(blocktrampoline2);
		registerRender(blocktrampoline3);
		registerRender(blocktrampolineop);
		
		registerRender(blocktrash_bin);
		
		registerRender(sitting);
		
		registerRender(block_key_holder);
		
		registerRender(block_cookie_jar);
		
		registerRender(blockTable);
		registerRender(blockHalfTable);
		
		registerRender(blockDisplayCase);
	}
	
	public static void registerRender(Block block){
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
}
