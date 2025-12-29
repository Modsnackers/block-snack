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
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.TERRACOTTA), RecipeCategory.MISC, ModItems.TERRACOTTA_BRICK_LIST.get(TerracottaColors.NONE), 4)
        .unlockedBy("has_terracotta_brick", this.has(ModItems.TERRACOTTA_BRICK_LIST.get(TerracottaColors.NONE)))
        .save(this.output, "terracotta_brick_from_terracotta_stonecutting");;
        
        shaped(
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.TERRACOTTA_BRICKS_LIST.get(TerracottaColors.NONE).get())
            .pattern ("AA")
            .pattern ("AA")
            .define ('A', ModItems.TERRACOTTA_BRICK_LIST.get(TerracottaColors.NONE).get())
            .unlockedBy("has_terracotta_brick", has(ModItems.TERRACOTTA_BRICK_LIST.get(TerracottaColors.NONE)))
            .save(output, "terracotta_bricks_basic");
    }
}
