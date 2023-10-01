package com.dm.earth.cabricality.content.core.threads;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.content.entries.CabfItems;
import com.dm.earth.cabricality.content.math.CalculationRecipe;
import com.dm.earth.cabricality.content.math.item.NumberItem;
import com.dm.earth.cabricality.lib.math.RecipeBuilderUtil;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.registry.Registries;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback.RecipeHandler;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;

import static com.dm.earth.cabricality.ModEntry.CABF;
import static com.dm.earth.cabricality.ModEntry.CR;

public class MathThread implements TechThread {
	@Override
	public void addRecipes(RecipeHandler handler) {
		// handler.register(recipeId("crafting", "calculation"), CalculationRecipe::new);

		CabfItems.MATH_CASTS.forEach(str -> handler.register(
				recipeId("stonecutting", str + "_cast"),
				id -> VanillaRecipeBuilders.stonecuttingRecipe(
						id, "",
						CR.asIngredient("copper_sheet"),
						CABF.asStack(str + "_cast"))
		));

		CabfItems.NUMBERS.forEach(num -> handler.register(
				recipeId("melting", NumberItem.getNumberItemName(num)),
				id -> RecipeManager.deserialize(id, RecipeBuilderUtil.generateMelting(
						Cabricality.id(NumberItem.getNumberItemName(num)),
						Registries.FLUID.getId(((NumberItem)
								Registries.ITEM.get(Cabricality.id(NumberItem.getNumberItemName(num)))
						).getFluid()),
						FluidConstants.DROPLET,
						null, 0, 200, 20)).value()
		));

		handler.register(
				recipeId("melting", "calculation_mechanism"),
				id -> RecipeManager.deserialize(id, RecipeBuilderUtil.generateMelting(
						Cabricality.id("calculation_mechanism"),
						Cabricality.id("raw_logic"),
						FluidConstants.NUGGET * 3,
						null, 0, 200, 20)).value()
		);
	}

	@Override
	public String getLevel() {
		return "math";
	}
}
