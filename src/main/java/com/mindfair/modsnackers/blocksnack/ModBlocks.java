package com.mindfair.modsnackers.blocksnack;

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
    
    // Creates a new Block with the id "blocksnack:example_block", combining the namespace and path
    public static final DeferredBlock<Block> TERRACOTTA_BRICKS = BLOCKS.register(
        "terracotta_bricks",
        registryName -> new Block(BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, registryName))
            .requiresCorrectToolForDrops()
            .destroyTime(1.85f)
            .explosionResistance(5.0f)));    

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
