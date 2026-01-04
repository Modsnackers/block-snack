package com.mindfair.modsnackers.blocksnack.blocks;

import com.mindfair.modsnackers.blocksnack.TerracottaColors;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class StandardTerracottaBlockGroup {
        public StandardTerracottaBlockGroup(TerracottaColors color, DeferredRegister.Blocks blockRegister) {
            BricksBlock = registerTerracottaBricks(color, blockRegister);
        }
        public DeferredBlock<Block> BricksBlock;

        private DeferredBlock<Block> registerTerracottaBricks(TerracottaColors color, DeferredRegister.Blocks blockRegister) {
            return blockRegister.register(
                TerracottaColors.getNameWithColorPrefix("terracotta_bricks", color),
                registryName -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    .requiresCorrectToolForDrops()
                    .destroyTime(1.85f)
                    .explosionResistance(5.0f)
                )
            );
        }
}
