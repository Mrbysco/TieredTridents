package com.mrbysco.tieredtridents.client;

import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.Item;

import java.util.Map;

public class ClientHelper {
	public static final Map<Item, ModelResourceLocation> tieredModels = Map.of(
			TridentRegistry.WOODEN_TRIDENT.get(), ModelResourceLocation.standalone(TridentRegistry.WOODEN_TRIDENT.getId().withPrefix("item/")),
			TridentRegistry.STONE_TRIDENT.get(), ModelResourceLocation.standalone(TridentRegistry.STONE_TRIDENT.getId().withPrefix("item/")),
			TridentRegistry.IRON_TRIDENT.get(), ModelResourceLocation.standalone(TridentRegistry.IRON_TRIDENT.getId().withPrefix("item/")),
			TridentRegistry.GOLDEN_TRIDENT.get(), ModelResourceLocation.standalone(TridentRegistry.GOLDEN_TRIDENT.getId().withPrefix("item/")),
			TridentRegistry.DIAMOND_TRIDENT.get(), ModelResourceLocation.standalone(TridentRegistry.DIAMOND_TRIDENT.getId().withPrefix("item/")),
			TridentRegistry.NETHERITE_TRIDENT.get(), ModelResourceLocation.standalone(TridentRegistry.NETHERITE_TRIDENT.getId().withPrefix("item/")),
			TridentRegistry.BONE_TRIDENT.get(), ModelResourceLocation.standalone(TridentRegistry.BONE_TRIDENT.getId().withPrefix("item/")),
			TridentRegistry.PITCHFORK.get(), ModelResourceLocation.standalone(TridentRegistry.PITCHFORK.getId().withPrefix("item/")),
			TridentRegistry.COPPER_TRIDENT.get(), ModelResourceLocation.standalone(TridentRegistry.COPPER_TRIDENT.getId().withPrefix("item/"))
	);

	public static final Map<Item, ModelResourceLocation> tieredHandModels = Map.of(
			TridentRegistry.WOODEN_TRIDENT.get(), ModelResourceLocation.standalone(TridentRegistry.WOODEN_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand")),
			TridentRegistry.STONE_TRIDENT.get(), ModelResourceLocation.standalone(TridentRegistry.STONE_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand")),
			TridentRegistry.IRON_TRIDENT.get(), ModelResourceLocation.standalone(TridentRegistry.IRON_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand")),
			TridentRegistry.GOLDEN_TRIDENT.get(), ModelResourceLocation.standalone(TridentRegistry.GOLDEN_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand")),
			TridentRegistry.DIAMOND_TRIDENT.get(), ModelResourceLocation.standalone(TridentRegistry.DIAMOND_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand")),
			TridentRegistry.NETHERITE_TRIDENT.get(), ModelResourceLocation.standalone(TridentRegistry.NETHERITE_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand")),
			TridentRegistry.BONE_TRIDENT.get(), ModelResourceLocation.standalone(TridentRegistry.BONE_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand")),
			TridentRegistry.PITCHFORK.get(), ModelResourceLocation.standalone(TridentRegistry.PITCHFORK.getId().withPrefix("item/").withSuffix("_in_hand")),
			TridentRegistry.COPPER_TRIDENT.get(), ModelResourceLocation.standalone(TridentRegistry.COPPER_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand"))
	);
}
