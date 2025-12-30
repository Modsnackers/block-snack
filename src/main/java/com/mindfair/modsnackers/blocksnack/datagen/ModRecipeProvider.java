package com.mindfair.modsnackers.blocksnack.datagen;

import java.util.concurrent.CompletableFuture;

import com.mindfair.modsnackers.blocksnack.ModBlocks;
import com.mindfair.modsnackers.blocksnack.ModItems;
import com.mindfair.modsnackers.blocksnack.TerracottaColors;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
            super(packOutput, provider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "BlockSnack Recipes";
        }
    }

    @Override
    protected void buildRecipes() {
        buildTerracottaBrickStonecutterRecipe(TerracottaColors.NONE);
        buildTerracottaBricksRecipe(TerracottaColors.NONE);
    }

    private String getUnlockRuleName(TerracottaColors color){
        return color == TerracottaColors.NONE ? "has_terracotta_brick" : String.format("has_%s_terracotta_brick", color.name().toLowerCase());
    }
    private void buildTerracottaBrickStonecutterRecipe(TerracottaColors color) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.TERRACOTTA), RecipeCategory.MISC, ModItems.TERRACOTTA_BRICK_LIST.get(color), 4)
        .unlockedBy(
            getUnlockRuleName(color),
            this.has(ModItems.TERRACOTTA_BRICK_LIST.get(color))
        )
        .save(
            this.output,
            String.format(
                "%s_from_%s",
                TerracottaColors.getNameWithColorPrefix("terracotta_brick", color),
                TerracottaColors.getNameWithColorPrefix("terracotta_stonecutting", color)
            )
        );
    }

    private void buildTerracottaBricksRecipe(TerracottaColors color) {
        shaped(
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.TERRACOTTA_BRICKS_LIST.get(color).get())
            .pattern ("AA")
            .pattern ("AA")
            .define ('A', ModItems.TERRACOTTA_BRICK_LIST.get(color).get())
            .unlockedBy(
                getUnlockRuleName(color),
                has(ModItems.TERRACOTTA_BRICK_LIST.get(color))
            )
            .save(output, TerracottaColors.getNameWithColorPrefix("terracotta_bricks_basic", color)
        );
    }
}
