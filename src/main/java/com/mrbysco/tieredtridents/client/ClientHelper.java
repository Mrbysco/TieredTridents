package com.mrbysco.tieredtridents.client;

import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.Map;

public class ClientHelper {
	public static final Map<Item, ResourceLocation> tieredModels = Map.of(
			TridentRegistry.WOODEN_TRIDENT.get(), TridentRegistry.WOODEN_TRIDENT.getId().withPrefix("item/"),
			TridentRegistry.STONE_TRIDENT.get(), TridentRegistry.STONE_TRIDENT.getId().withPrefix("item/"),
			TridentRegistry.IRON_TRIDENT.get(), TridentRegistry.IRON_TRIDENT.getId().withPrefix("item/"),
			TridentRegistry.GOLDEN_TRIDENT.get(), TridentRegistry.GOLDEN_TRIDENT.getId().withPrefix("item/"),
			TridentRegistry.DIAMOND_TRIDENT.get(), TridentRegistry.DIAMOND_TRIDENT.getId().withPrefix("item/"),
			TridentRegistry.NETHERITE_TRIDENT.get(), TridentRegistry.NETHERITE_TRIDENT.getId().withPrefix("item/"),
			TridentRegistry.BONE_TRIDENT.get(), TridentRegistry.BONE_TRIDENT.getId().withPrefix("item/"),
			TridentRegistry.PITCHFORK.get(), TridentRegistry.PITCHFORK.getId().withPrefix("item/"),
			TridentRegistry.COPPER_TRIDENT.get(), TridentRegistry.COPPER_TRIDENT.getId().withPrefix("item/")
	);

	public static final Map<Item, ResourceLocation> tieredHandModels = Map.of(
			TridentRegistry.WOODEN_TRIDENT.get(), TridentRegistry.WOODEN_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand"),
			TridentRegistry.STONE_TRIDENT.get(), TridentRegistry.STONE_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand"),
			TridentRegistry.IRON_TRIDENT.get(), TridentRegistry.IRON_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand"),
			TridentRegistry.GOLDEN_TRIDENT.get(), TridentRegistry.GOLDEN_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand"),
			TridentRegistry.DIAMOND_TRIDENT.get(), TridentRegistry.DIAMOND_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand"),
			TridentRegistry.NETHERITE_TRIDENT.get(), TridentRegistry.NETHERITE_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand"),
			TridentRegistry.BONE_TRIDENT.get(), TridentRegistry.BONE_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand"),
			TridentRegistry.PITCHFORK.get(), TridentRegistry.PITCHFORK.getId().withPrefix("item/").withSuffix("_in_hand"),
			TridentRegistry.COPPER_TRIDENT.get(), TridentRegistry.COPPER_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand")
	);
}
