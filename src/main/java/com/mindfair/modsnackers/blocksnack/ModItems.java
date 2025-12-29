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
    private enum TerracottaColors {
        NONE, BLACK, BLUE, BROWN, CYAN, GRAY, GREEN, LIGHT_BLUE, LIGHT_GRAY,
        LIME, MAGENTA, ORANGE, PINK, PURPLE, RED, WHITE, YELLOW
    }

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BlockSnack.MODID);

    public static final DeferredItem<Item> TERRACOTTA_BRICK = registerTerracottaBrick(TerracottaColors.NONE);

    private static DeferredItem<Item> registerTerracottaBrick(TerracottaColors color) {
        String name = (color == TerracottaColors.NONE ? "terracotta_brick" : String.format("%s_terracotta_brick", color));
        return ITEMS.register(
            name,
            () -> new Item(new Item.Properties()
                .useItemDescriptionPrefix()
                .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse(String.format("%s:%s", BlockSnack.MODID, name))))
            )
        );
    }

    public static final DeferredItem<BlockItem> TERRACOTTA_BRICKS = ITEMS.registerSimpleBlockItem(
        "terracotta_bricks",
        ModBlocks.TERRACOTTA_BRICKS
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
