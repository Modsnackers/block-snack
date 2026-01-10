package com.mindfair.modsnackers.blocksnack.datagen;

import java.util.concurrent.CompletableFuture;

import com.mindfair.modsnackers.blocksnack.BlockSnack;
import com.mindfair.modsnackers.blocksnack.ModTags;
import com.mindfair.modsnackers.blocksnack.items.ModItems;
import com.mindfair.modsnackers.blocksnack.items.StandardTerracottaItemGroup;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, BlockSnack.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        ModItems.TERRACOTTA_ITEMS_LIST.forEach((color, itemGroup) -> addTagsForTerracottaItemGroup(itemGroup));
    }

    private void addTagsForTerracottaItemGroup(StandardTerracottaItemGroup terracottaItemGroup) {
        tag(Tags.Items.BRICKS)
            .add(terracottaItemGroup.BrickItem.get());
        tag(ModTags.Items.BRICKS_TERRACOTTA)
            .add(terracottaItemGroup.BrickItem.get());
        tag(ItemTags.STAIRS)
            .add(terracottaItemGroup.BrickStairBlockItem.get());
        tag(ItemTags.SLABS)
            .add(terracottaItemGroup.BrickSlabBlockItem.get());
    }
}
