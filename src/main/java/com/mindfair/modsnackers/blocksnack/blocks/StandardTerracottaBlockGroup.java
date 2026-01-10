package com.mindfair.modsnackers.blocksnack.blocks;

import com.mindfair.modsnackers.blocksnack.TerracottaColors;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class StandardTerracottaBlockGroup {
        public StandardTerracottaBlockGroup(TerracottaColors color, DeferredRegister.Blocks blockRegister) {
            BricksBlock = registerTerracottaBricks(color, blockRegister);
            BrickStairBlock = registerTerracottaBrickStair(color, blockRegister);
            BrickSlabBlock = registerTerracottaBrickSlab(color, blockRegister);
        }
        private static final float DESTROY_TIME = 1.85f;
        private static final float EXPLOSION_RESISTANCE = 5.00f;
        public DeferredBlock<Block> BricksBlock;
        public DeferredBlock<StairBlock> BrickStairBlock;
        public DeferredBlock<SlabBlock> BrickSlabBlock;

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
        private DeferredBlock<StairBlock> registerTerracottaBrickStair(TerracottaColors color, DeferredRegister.Blocks blockRegister) {
            return blockRegister.registerBlock(
                TerracottaColors.getNameWithColorPrefix("terracotta_brick_stairs", color),
                (properties) -> new StairBlock(BricksBlock.get().defaultBlockState(),
                    properties
                    .requiresCorrectToolForDrops()
                    .destroyTime(DESTROY_TIME)
                    .explosionResistance(EXPLOSION_RESISTANCE)
                )
            );
        }
        private DeferredBlock<SlabBlock> registerTerracottaBrickSlab(TerracottaColors color, DeferredRegister.Blocks blockRegister) {
            return blockRegister.registerBlock(
                TerracottaColors.getNameWithColorPrefix("terracotta_brick_slab", color),
                (properties) -> new SlabBlock(
                    properties
                    .requiresCorrectToolForDrops()
                    .destroyTime(DESTROY_TIME)
                    .explosionResistance(EXPLOSION_RESISTANCE)
                )
            );
        }
}
