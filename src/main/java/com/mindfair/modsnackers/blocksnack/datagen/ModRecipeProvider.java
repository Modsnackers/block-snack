package com.mindfair.modsnackers.blocksnack.datagen;

import java.util.concurrent.CompletableFuture;

import com.mindfair.modsnackers.blocksnack.TerracottaColors;
import com.mindfair.modsnackers.blocksnack.blocks.ModBlocks;
import com.mindfair.modsnackers.blocksnack.blocks.StandardTerracottaBlockGroup;
import com.mindfair.modsnackers.blocksnack.items.ModItems;
import com.mindfair.modsnackers.blocksnack.items.StandardTerracottaItemGroup;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredItem;

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

    private String getUnlockRuleName(DeferredItem<?> requiredItem){
        return String.format("has_%s", requiredItem.getId().getPath());
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
        DeferredItem<Item> brickItem = ModItems.TERRACOTTA_ITEMS_LIST.get(color).BrickItem;
        SingleItemRecipeBuilder.stonecutting(getTerracottaIngredient(color), RecipeCategory.MISC, ModItems.TERRACOTTA_ITEMS_LIST.get(color).BrickItem, 4)
            .unlockedBy(
                getUnlockRuleName(brickItem),
                this.has(brickItem)
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
        StandardTerracottaItemGroup terracottaItemGroup = ModItems.TERRACOTTA_ITEMS_LIST.get(color);
        StandardTerracottaBlockGroup terracottaBlockGroup = ModBlocks.TERRACOTTA_BRICKS_LIST.get(color);
        shaped(
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.TERRACOTTA_BRICKS_LIST.get(color).BricksBlock.get())
            .pattern ("AA")
            .pattern ("AA")
            .define ('A', ModItems.TERRACOTTA_ITEMS_LIST.get(color).BrickItem.get())
            .unlockedBy(
                getUnlockRuleName(terracottaItemGroup.BrickItem),
                has(terracottaItemGroup.BrickItem)
            )
            .save(output, TerracottaColors.getNameWithColorPrefix("terracotta_bricks_basic", color)
        );
        stairBuilder(terracottaBlockGroup.BrickStairBlock.get(), Ingredient.of(terracottaItemGroup.BricksBlockItem))
            .group(TerracottaColors.getNameWithColorPrefix("terracotta_bricks", color))
            .unlockedBy(
                getUnlockRuleName(terracottaItemGroup.BricksBlockItem),
                has(terracottaItemGroup.BricksBlockItem)
            )
            .save(output, TerracottaColors.getNameWithColorPrefix("terracotta_brick_stair", color)
        );
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, terracottaBlockGroup.BrickSlabBlock.get(), Ingredient.of(terracottaItemGroup.BricksBlockItem))
            .group(TerracottaColors.getNameWithColorPrefix("terracotta_bricks", color))
            .unlockedBy(
                getUnlockRuleName(terracottaItemGroup.BricksBlockItem),
                has(terracottaItemGroup.BricksBlockItem)
            )
            .save(output, TerracottaColors.getNameWithColorPrefix("terracotta_brick_slab", color)
        );
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, terracottaBlockGroup.BrickWallBlock.get(), Ingredient.of(terracottaItemGroup.BricksBlockItem))
            .group(TerracottaColors.getNameWithColorPrefix("terracotta_bricks", color))
            .unlockedBy(
                getUnlockRuleName(terracottaItemGroup.BricksBlockItem),
                has(terracottaItemGroup.BricksBlockItem)
            )
            .save(output, TerracottaColors.getNameWithColorPrefix("terracotta_brick_wall", color)
        );
    }
}
