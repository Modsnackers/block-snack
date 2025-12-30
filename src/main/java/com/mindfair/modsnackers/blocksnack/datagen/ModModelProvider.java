package com.mindfair.modsnackers.blocksnack.datagen;

import java.util.stream.Stream;

import com.mindfair.modsnackers.blocksnack.BlockSnack;
import com.mindfair.modsnackers.blocksnack.ModBlocks;
import com.mindfair.modsnackers.blocksnack.ModItems;
import com.mindfair.modsnackers.blocksnack.TerracottaColors;

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
        registerBrickItemModel(itemModels, ModItems.TERRACOTTA_BRICK_LIST.get(TerracottaColors.NONE).get());

        /* Blocks */
        registerBricksBlockModel(blockModels, ModBlocks.TERRACOTTA_BRICKS_LIST.get(TerracottaColors.NONE).get());
        registerBricksBlockModel(blockModels, ModBlocks.TERRACOTTA_BRICKS_LIST.get(TerracottaColors.BLACK).get());
    }

    private void registerBrickItemModel(ItemModelGenerators itemModels, Item brickItem) {
        itemModels.generateFlatItem(brickItem, ModelTemplates.FLAT_ITEM);
    }

    private void registerBricksBlockModel(BlockModelGenerators blockModels, Block bricksBlock) {
        blockModels.createTrivialCube(bricksBlock);
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream();
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return ModItems.ITEMS.getEntries().stream();
    }}
