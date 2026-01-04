package com.mindfair.modsnackers.blocksnack;

import java.util.EnumMap;

import com.mindfair.modsnackers.blocksnack.items.StandardTerracottaItemGroup;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BlockSnack.MODID);

    public static final EnumMap<TerracottaColors, StandardTerracottaItemGroup> TERRACOTTA_ITEMS_LIST = registerTerracottaBrickItems();

    private static EnumMap<TerracottaColors, StandardTerracottaItemGroup> registerTerracottaBrickItems () {
        EnumMap<TerracottaColors, StandardTerracottaItemGroup> items = new EnumMap<TerracottaColors, StandardTerracottaItemGroup>(TerracottaColors.class);

        for (TerracottaColors color: TerracottaColors.values()) {
            items.put(color, new StandardTerracottaItemGroup(color, ITEMS));
        }

        return items;
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
