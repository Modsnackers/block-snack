package com.mindfair.modsnackers.blocksnack.items;

import com.mindfair.modsnackers.blocksnack.BlockSnack;
import com.mindfair.modsnackers.blocksnack.TerracottaColors;
import com.mindfair.modsnackers.blocksnack.blocks.ModBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class StandardTerracottaItemGroup {
    public StandardTerracottaItemGroup(TerracottaColors color, DeferredRegister.Items itemRegister) {
        Color = color;
        ItemRegister = itemRegister;
        BrickItem = registerTerracottaBrick();
        BricksBlockItem = registerTerracottaBricks(ModBlocks.TERRACOTTA_BRICKS_LIST.get(Color).BricksBlock);
        BrickStairBlockItem = registerTerracottaBricks(ModBlocks.TERRACOTTA_BRICKS_LIST.get(Color).BrickStairBlock);
        BrickSlabBlockItem = registerTerracottaBricks(ModBlocks.TERRACOTTA_BRICKS_LIST.get(Color).BrickSlabBlock);
        BrickWallBlockItem = registerTerracottaBricks(ModBlocks.TERRACOTTA_BRICKS_LIST.get(Color).BrickWallBlock);
    }
    public TerracottaColors Color;
    private DeferredRegister.Items ItemRegister;
    public DeferredItem<Item> BrickItem;
    public DeferredItem<BlockItem> BricksBlockItem;
    public DeferredItem<BlockItem> BrickStairBlockItem;
    public DeferredItem<BlockItem> BrickSlabBlockItem;
    public DeferredItem<BlockItem> BrickWallBlockItem;

    private DeferredItem<Item> registerTerracottaBrick() {
        String name = TerracottaColors.getNameWithColorPrefix("terracotta_brick", Color);
        return ItemRegister.register(
            name,
            () -> new Item(new Item.Properties()
                .useItemDescriptionPrefix()
                .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse(String.format("%s:%s", BlockSnack.MODID, name))))
            )
        );
    }
    private DeferredItem<BlockItem> registerTerracottaBricks(DeferredBlock<? extends Block> block) {
        return ItemRegister.registerSimpleBlockItem(
            block.getId().getPath(),
            block
        );
    }
}
