package com.mindfair.modsnackers.blocksnack;

import java.util.EnumMap;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    // Create a Deferred Register to hold Blocks which will all be registered under the "blocksnack" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BlockSnack.MODID);
    
    // Registers all the Terracotta Bricks blocks and puts them in a list
    public static final EnumMap<TerracottaColors, DeferredBlock<Block>> TERRACOTTA_BRICKS_LIST = registerTerracottaBrickBlocks();

    private static EnumMap<TerracottaColors, DeferredBlock<Block>> registerTerracottaBrickBlocks () {
        EnumMap<TerracottaColors, DeferredBlock<Block>> items = new EnumMap<TerracottaColors, DeferredBlock<Block>>(TerracottaColors.class);

        items.put(TerracottaColors.NONE, registerTerracottaBricks(TerracottaColors.NONE));
        items.put(TerracottaColors.BLACK, registerTerracottaBricks(TerracottaColors.BLACK));

        return items;
    }

    private static DeferredBlock<Block> registerTerracottaBricks(TerracottaColors color) {
        String name = (color == TerracottaColors.NONE ? "terracotta_bricks" : String.format("%s_terracotta_bricks", color.name().toLowerCase()));
        return BLOCKS.register(
            name,
            registryName -> new Block(BlockBehaviour.Properties.of()
                .setId(ResourceKey.create(Registries.BLOCK, registryName))
                .requiresCorrectToolForDrops()
                .destroyTime(1.85f)
                .explosionResistance(5.0f)
            )
        );
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
