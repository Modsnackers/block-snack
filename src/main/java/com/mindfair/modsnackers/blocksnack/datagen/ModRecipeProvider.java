package com.mindfair.modsnackers.blocksnack.datagen;

import java.util.concurrent.CompletableFuture;

import com.mindfair.modsnackers.blocksnack.TerracottaColors;
import com.mindfair.modsnackers.blocksnack.blocks.ModBlocks;
import com.mindfair.modsnackers.blocksnack.items.ModItems;

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
        ModItems.TERRACOTTA_ITEMS_LIST.forEach((color, item) -> buildTerracottaBrickStonecutterRecipe(color));
        ModBlocks.TERRACOTTA_BRICKS_LIST.forEach((color, block) -> buildTerracottaBricksRecipe(color));
    }

    private String getUnlockRuleName(TerracottaColors color){
        return color == TerracottaColors.NONE ? "has_terracotta_brick" : String.format("has_%s_terracotta_brick", color.name().toLowerCase());
    }
    private Ingredient getTerracottaIngredient(TerracottaColors color) {
        switch (color) {
            case TerracottaColors.NONE: return Ingredient.of(Blocks.TERRACOTTA);
            case TerracottaColors.BLACK: return Ingredient.of(Blocks.BLACK_TERRACOTTA);
            case TerracottaColors.BLUE: return Ingredient.of(Blocks.BLUE_TERRACOTTA);
            case TerracottaColors.BROWN: return Ingredient.of(Blocks.BROWN_TERRACOTTA);
            case TerracottaColors.CYAN: return Ingredient.of(Blocks.CYAN_TERRACOTTA);
            case TerracottaColors.GRAY: return Ingredient.of(Blocks.GRAY_TERRACOTTA);
            case TerracottaColors.GREEN: return Ingredient.of(Blocks.GREEN_TERRACOTTA);
            case TerracottaColors.LIGHT_BLUE: return Ingredient.of(Blocks.LIGHT_BLUE_TERRACOTTA);
            case TerracottaColors.LIGHT_GRAY: return Ingredient.of(Blocks.LIGHT_GRAY_TERRACOTTA);
            case TerracottaColors.LIME: return Ingredient.of(Blocks.LIME_TERRACOTTA);
            case TerracottaColors.MAGENTA: return Ingredient.of(Blocks.MAGENTA_TERRACOTTA);
            case TerracottaColors.ORANGE: return Ingredient.of(Blocks.ORANGE_TERRACOTTA);
            case TerracottaColors.PINK: return Ingredient.of(Blocks.PINK_TERRACOTTA);
            case TerracottaColors.PURPLE: return Ingredient.of(Blocks.PURPLE_TERRACOTTA);
            case TerracottaColors.RED: return Ingredient.of(Blocks.RED_TERRACOTTA);
            case TerracottaColors.WHITE: return Ingredient.of(Blocks.WHITE_TERRACOTTA);
            case TerracottaColors.YELLOW: return Ingredient.of(Blocks.YELLOW_TERRACOTTA);
            default: throw new IllegalArgumentException(String.format("Unrecognized terracotta color '%s'", color.name()));
        }
    }
    private void buildTerracottaBrickStonecutterRecipe(TerracottaColors color) {
        SingleItemRecipeBuilder.stonecutting(getTerracottaIngredient(color), RecipeCategory.MISC, ModItems.TERRACOTTA_ITEMS_LIST.get(color).BrickItem, 4)
        .unlockedBy(
            getUnlockRuleName(color),
            this.has(ModItems.TERRACOTTA_ITEMS_LIST.get(color).BrickItem)
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
            ModBlocks.TERRACOTTA_BRICKS_LIST.get(color).BricksBlock.get())
            .pattern ("AA")
            .pattern ("AA")
            .define ('A', ModItems.TERRACOTTA_ITEMS_LIST.get(color).BrickItem.get())
            .unlockedBy(
                getUnlockRuleName(color),
                has(ModItems.TERRACOTTA_ITEMS_LIST.get(color).BrickItem)
            )
            .save(output, TerracottaColors.getNameWithColorPrefix("terracotta_bricks_basic", color)
        );
    }
}
