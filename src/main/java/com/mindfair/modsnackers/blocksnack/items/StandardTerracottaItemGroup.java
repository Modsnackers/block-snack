package com.mindfair.modsnackers.blocksnack.items;

import com.mindfair.modsnackers.blocksnack.BlockSnack;
import com.mindfair.modsnackers.blocksnack.ModBlocks;
import com.mindfair.modsnackers.blocksnack.TerracottaColors;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class StandardTerracottaItemGroup {
    public StandardTerracottaItemGroup(TerracottaColors color, DeferredRegister.Items itemRegister) {
        Color = color;
        ItemRegister = itemRegister;
        BrickItem = registerTerracottaBrick();
        BricksBlockItem = registerTerracottaBricks();
    }
    public TerracottaColors Color;
    private DeferredRegister.Items ItemRegister;
    public DeferredItem<Item> BrickItem;
    public DeferredItem<BlockItem> BricksBlockItem;

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
    private DeferredItem<BlockItem> registerTerracottaBricks() {
        return ItemRegister.registerSimpleBlockItem(
            TerracottaColors.getNameWithColorPrefix("terracotta_bricks", Color),
            ModBlocks.TERRACOTTA_BRICKS_LIST.get(Color).BricksBlock
        );
    }
}
