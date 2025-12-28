package com.mindfair.modsnackers.blocksnack;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> BRICKS_TERRACOTTA = createTag("bricks/terracotta");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(BlockSnack.MODID, name));
        }
    }
}
