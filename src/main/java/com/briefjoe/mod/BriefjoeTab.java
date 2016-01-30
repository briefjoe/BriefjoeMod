package com.briefjoe.mod;

import com.briefjoe.mod.init.items.BriefjoeItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BriefjoeTab extends CreativeTabs {

	public BriefjoeTab(String label) {
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		return BriefjoeItems.nether_stick;
	}

}
