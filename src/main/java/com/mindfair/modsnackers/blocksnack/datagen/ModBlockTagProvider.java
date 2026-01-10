package com.mindfair.modsnackers.blocksnack.datagen;

import java.util.concurrent.CompletableFuture;

import com.mindfair.modsnackers.blocksnack.BlockSnack;
import com.mindfair.modsnackers.blocksnack.blocks.ModBlocks;
import com.mindfair.modsnackers.blocksnack.blocks.StandardTerracottaBlockGroup;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, BlockSnack.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        ModBlocks.TERRACOTTA_BRICKS_LIST.forEach((color, standardBlocks) -> addTagsForTerracottaBricks(standardBlocks));
    }

    private void addTagsForTerracottaBricks(StandardTerracottaBlockGroup terracottaBlockGroup) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(terracottaBlockGroup.BricksBlock.get())
            .add(terracottaBlockGroup.BrickStairBlock.get())
            .add(terracottaBlockGroup.BrickSlabBlock.get())
            .add(terracottaBlockGroup.BrickWallBlock.get());
        tag(BlockTags.WALLS).add(terracottaBlockGroup.BrickWallBlock.get());
    }
}
