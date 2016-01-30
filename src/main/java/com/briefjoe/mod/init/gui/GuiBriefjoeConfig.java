package com.briefjoe.mod.init.gui;

import com.briefjoe.mod.util.ConfigurationHandler;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;

public class GuiBriefjoeConfig extends GuiConfig
{
	public GuiBriefjoeConfig(GuiScreen parent)
	{
		super(parent, new ConfigElement(ConfigurationHandler.config.getCategory(ConfigurationHandler.CATEGORY_SETTINGS)).getChildElements(), "cfm", false, false, GuiConfig.getAbridgedConfigPath(ConfigurationHandler.config.toString()));
	}
}
