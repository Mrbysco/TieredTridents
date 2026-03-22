package com.mrbysco.tieredtridents.datagen.data;

import com.mrbysco.tieredtridents.TieredTridents;
import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class TridentItemTagProvider extends ItemTagsProvider {
	public TridentItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, lookupProvider, TieredTridents.MOD_ID);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		tag(Tags.Items.TOOLS_TRIDENT).add(
				TridentRegistry.WOODEN_TRIDENT.asItem(),
				TridentRegistry.STONE_TRIDENT.asItem(),
				TridentRegistry.IRON_TRIDENT.asItem(),
				TridentRegistry.COPPER_TRIDENT.asItem(),
				TridentRegistry.GOLDEN_TRIDENT.asItem(),
				TridentRegistry.DIAMOND_TRIDENT.asItem(),
				TridentRegistry.NETHERITE_TRIDENT.asItem(),
				TridentRegistry.BONE_TRIDENT.asItem(),
				TridentRegistry.PITCHFORK.asItem()
		);
	}
}
