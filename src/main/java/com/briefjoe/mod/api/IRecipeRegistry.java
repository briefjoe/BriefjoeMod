package com.briefjoe.mod.api;

public interface IRecipeRegistry
{
	/**
	 * Adds a value to the recipe variables
	 * <br><br>
	 * Please read {@link RecipeVariables} or your recipe might not work
	 * 
	 * @param type
	 *            The recipe type
	 * @param params
	 *            The variables for the recipe.
	 */
	@Deprecated
	public void registerRecipe(String type, RecipeVariables params);
	
	/**
	 * Registers a recipe<br>
	 * <br>
	 * Please read {@link RecipeVariables} for information about<br>
	 * required variables for the specific {@link RecipeType}.
	 * 
	 * @param type
	 *            The recipe type. See {@link RecipeType}
	 * @param params
	 *            The variables for the recipe.
	 */
	public void registerRecipe(RecipeType type, RecipeVariables variables);
}
