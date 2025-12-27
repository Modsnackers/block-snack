package com.mindfair.modsnackers.blocksnack.datagen;

import java.util.stream.Stream;

import com.mindfair.modsnackers.blocksnack.BlockSnack;
import com.mindfair.modsnackers.blocksnack.ModBlocks;
import com.mindfair.modsnackers.blocksnack.ModItems;

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
        itemModels.generateFlatItem(ModItems.TERRACOTTA_BRICK.get(), ModelTemplates.FLAT_ITEM);

        /* Blocks */
        blockModels.createTrivialCube(ModBlocks.TERRACOTTA_BRICKS.get());
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream();
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return ModItems.ITEMS.getEntries().stream();
    }}
