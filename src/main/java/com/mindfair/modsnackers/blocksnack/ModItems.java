package com.mindfair.modsnackers.blocksnack;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BlockSnack.MODID);

    public static final DeferredItem<Item> TERRACOTTA_BRICK = ITEMS.register(
        "terracotta_brick",
        () -> new Item(new Item.Properties()
            .useItemDescriptionPrefix()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse(String.format("%s:%s", BlockSnack.MODID, "terracotta_brick"))))
        )
    );

    public static final DeferredItem<BlockItem> TERRACOTTA_BRICKS = ITEMS.registerSimpleBlockItem(
        "terracotta_bricks",
        ModBlocks.TERRACOTTA_BRICKS
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
