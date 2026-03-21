package com.mrbysco.tieredtridents.datagen;

import com.mrbysco.tieredtridents.datagen.assets.TridentItemProvider;
import com.mrbysco.tieredtridents.datagen.assets.TridentLanguageProvider;
import com.mrbysco.tieredtridents.datagen.data.TridentRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TridentDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		generator.addProvider(event.includeServer(), new TridentRecipeProvider(packOutput));

		generator.addProvider(event.includeClient(), new TridentLanguageProvider(packOutput));
		generator.addProvider(event.includeClient(), new TridentItemProvider(packOutput, helper));
	}
}
