package com.mindfair.modsnackers.blocksnack.datagen;

import java.util.concurrent.CompletableFuture;

import com.mindfair.modsnackers.blocksnack.BlockSnack;
import com.mindfair.modsnackers.blocksnack.ModItems;
import com.mindfair.modsnackers.blocksnack.ModTags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, BlockSnack.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        addTagsForTerracottaBrick(ModItems.TERRACOTTA_BRICK.get());
    }

    private void addTagsForTerracottaBrick(Item terracottaBrickItem) {
        tag(Tags.Items.BRICKS)
            .add(terracottaBrickItem);
        tag(ModTags.Items.BRICKS_TERRACOTTA)
            .add(terracottaBrickItem);
    }
}
