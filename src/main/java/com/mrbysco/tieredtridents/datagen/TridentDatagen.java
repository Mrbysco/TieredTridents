package com.mrbysco.tieredtridents.datagen;

import com.mrbysco.tieredtridents.datagen.assets.TridentItemProvider;
import com.mrbysco.tieredtridents.datagen.assets.TridentLanguageProvider;
import com.mrbysco.tieredtridents.datagen.data.TridentItemTagProvider;
import com.mrbysco.tieredtridents.datagen.data.TridentRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber
public class TridentDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent.Client event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		generator.addProvider(true, new TridentRecipeProvider.Runner(packOutput, lookupProvider));
		generator.addProvider(true, new TridentItemTagProvider(packOutput, lookupProvider));

		generator.addProvider(true, new TridentLanguageProvider(packOutput));
		generator.addProvider(true, new TridentItemProvider(packOutput));
	}
}
