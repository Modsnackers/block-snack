package com.mindfair.modsnackers.blocksnack.datagen;

import java.util.stream.Stream;

import com.mindfair.modsnackers.blocksnack.BlockSnack;
import com.mindfair.modsnackers.blocksnack.blocks.ModBlocks;
import com.mindfair.modsnackers.blocksnack.blocks.StandardTerracottaBlockGroup;
import com.mindfair.modsnackers.blocksnack.items.ModItems;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, BlockSnack.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        /* Items */
        ModItems.TERRACOTTA_ITEMS_LIST.forEach((color, itemGroup) -> registerBrickItemModel(itemModels, itemGroup.BrickItem.get()));

        /* Blocks */
        ModBlocks.TERRACOTTA_BRICKS_LIST.forEach((color, standardBlocks) -> registerTerracottaBricksBlockModel(blockModels, standardBlocks));
    }

    private void registerBrickItemModel(ItemModelGenerators itemModels, Item brickItem) {
        itemModels.generateFlatItem(brickItem, ModelTemplates.FLAT_ITEM);
    }
    private void registerTerracottaBricksBlockModel(BlockModelGenerators blockModels, StandardTerracottaBlockGroup terracottaBlockGroup) {
        blockModels.family(terracottaBlockGroup.BricksBlock.get())
            .stairs(terracottaBlockGroup.BrickStairBlock.get())
            .slab(terracottaBlockGroup.BrickSlabBlock.get());
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream();
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return ModItems.ITEMS.getEntries().stream();
    }}
