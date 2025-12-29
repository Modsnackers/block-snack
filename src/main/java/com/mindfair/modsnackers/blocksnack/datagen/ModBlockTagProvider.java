package com.mindfair.modsnackers.blocksnack.datagen;

import java.util.concurrent.CompletableFuture;

import com.mindfair.modsnackers.blocksnack.BlockSnack;
import com.mindfair.modsnackers.blocksnack.ModBlocks;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, BlockSnack.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        addTagsForTerracottaBricks(ModBlocks.TERRACOTTA_BRICKS.get());
    }

    private void addTagsForTerracottaBricks(Block terracottaBricksBlock) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(terracottaBricksBlock);
    }
}
