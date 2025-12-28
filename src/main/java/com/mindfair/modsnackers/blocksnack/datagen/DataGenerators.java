package com.mindfair.modsnackers.blocksnack.datagen;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.mindfair.modsnackers.blocksnack.BlockSnack;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = BlockSnack.MODID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        BlockSnack.LOGGER.info("gatherClientData");
        gatherModData(generator, lookupProvider);
    }

    private static void gatherModData(DataGenerator generator, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        PackOutput packOutput = generator.getPackOutput();

        generator.addProvider(true, new LootTableProvider(
            packOutput,
            Collections.emptySet(),
            List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)),
            lookupProvider)
        );
        // Add Mod Block Tags Provider
        // Add Mod Item Tags Provider
        generator.addProvider(true, new ModRecipeProvider.Runner(packOutput, lookupProvider));
        generator.addProvider(true, new ModModelProvider(packOutput));
    }
}
