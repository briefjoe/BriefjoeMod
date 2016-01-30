package com.briefjoe.mod.util;

import java.io.File;

import com.briefjoe.mod.api.RecipeRegistry;
import com.briefjoe.mod.api.Recipes;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {
	
	public static Configuration config;

	public static final String CATEGORY_RECIPE_SETTINGS = "recipe-settings";
	public static final String CATEGORY_API = "recipe-api";
	public static final String CATEGORY_SETTINGS = "settings";

	public static String[] items = {};
	public static boolean canDisplay = true;
	public static boolean hasDisplayedOnce = false;
	public static boolean api_debug = true;
	
	public static boolean oven_1 = true, oven_2 = true, oven_3 = true, oven_4 = true, oven_5 = true, oven_6 = true, oven_7 = true;
	
	public static void init(File file)
	{
		if (config == null)
		{
			config = new Configuration(file);
			loadConfig(false);
		}
	}

	public static void loadConfig(boolean shouldChange)
	{
		//shouldChangeapi_debug = config.getBoolean("recipe-api-debug", CATEGORY_SETTINGS, true, "If true, prints out information about RecipeAPI. Recommended 'true' for people trying to add custom recipe.");
		canDisplay = config.getBoolean("welcome_message", CATEGORY_SETTINGS, canDisplay, "Enabled or disable the welcome message");
		//items = config.getStringList("custom-recipes", CATEGORY_API, items, "Insert custom recipes here");
		//config.addCustomCategoryComment(CATEGORY_RECIPE_SETTINGS, "Enabled or disable the default recipes");
		//config.addCustomCategoryComment(CATEGORY_API, "RecipeAPI Configuration. How to use: http://mrcrayfishs-furniture-mod.wikia.com/wiki/Configuration");
		updateEnabledRecipes();

		if (config.hasChanged() && shouldChange)
		{
			Recipes.clearLocalRecipes();
			Recipes.clearCommRecipes();
			RecipeRegistry.registerDefaultRecipes();
			RecipeRegistry.registerConfigRecipes();
			Recipes.addCommRecipesToLocal();
			Recipes.updateDataList();
		}
		config.save();
	}

	private static void updateEnabledRecipes()
	{
		oven_1 = config.getBoolean("oven-1", CATEGORY_RECIPE_SETTINGS, oven_1, "Beef -> Cooked Beef");
		oven_2 = config.getBoolean("oven-2", CATEGORY_RECIPE_SETTINGS, oven_2, "Porkchop -> Cooked Porkchop");
		oven_3 = config.getBoolean("oven-3", CATEGORY_RECIPE_SETTINGS, oven_3, "Potato -> Cooked Potato");
		oven_4 = config.getBoolean("oven-4", CATEGORY_RECIPE_SETTINGS, oven_4, "Chicken -> Cooked Chicken");
		oven_5 = config.getBoolean("oven-5", CATEGORY_RECIPE_SETTINGS, oven_5, "Raw Fish -> Cooked Fish");
		oven_6 = config.getBoolean("oven-6", CATEGORY_RECIPE_SETTINGS, oven_6, "Raw Salmon -> Cooked Salmon");
		oven_7 = config.getBoolean("oven-7", CATEGORY_RECIPE_SETTINGS, oven_7, "Mutton -> Cooked Mutton");
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs)
	{
		if (eventArgs.modID.equals("bjm"))
		{
			loadConfig(false);
		}
	}
	
}
