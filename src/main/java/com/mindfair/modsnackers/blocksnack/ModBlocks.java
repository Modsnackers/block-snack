package com.mindfair.modsnackers.blocksnack;
import com.mindfair.modsnackers.blocksnack.blocks.StandardTerracottaBlockGroup;

import java.util.EnumMap;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    // Create a Deferred Register to hold Blocks which will all be registered under the "blocksnack" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BlockSnack.MODID);
    
    // Registers all the Terracotta Bricks blocks and puts them in a list
    public static final EnumMap<TerracottaColors, StandardTerracottaBlockGroup> TERRACOTTA_BRICKS_LIST = registerTerracottaBrickBlocks();

    private static EnumMap<TerracottaColors, StandardTerracottaBlockGroup> registerTerracottaBrickBlocks () {
        EnumMap<TerracottaColors, StandardTerracottaBlockGroup> blocks = new EnumMap<TerracottaColors, StandardTerracottaBlockGroup>(TerracottaColors.class);

        for (TerracottaColors color: TerracottaColors.values()) {
            blocks.put(color, new StandardTerracottaBlockGroup(color, BLOCKS));
        }

        return blocks;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
