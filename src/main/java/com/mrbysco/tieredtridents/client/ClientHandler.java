package com.mrbysco.tieredtridents.client;

import com.mrbysco.tieredtridents.TieredTridents;
import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;

@EventBusSubscriber(modid = TieredTridents.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientHandler {

	@SubscribeEvent
	public static void modelRegistry(ModelEvent.RegisterAdditional event) {
		event.register(ModelResourceLocation.standalone(TridentRegistry.WOODEN_TRIDENT.getId().withPrefix("item/")));
		event.register(ModelResourceLocation.standalone(TridentRegistry.WOODEN_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand")));
		event.register(ModelResourceLocation.standalone(TridentRegistry.STONE_TRIDENT.getId().withPrefix("item/")));
		event.register(ModelResourceLocation.standalone(TridentRegistry.STONE_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand")));
		event.register(ModelResourceLocation.standalone(TridentRegistry.IRON_TRIDENT.getId().withPrefix("item/")));
		event.register(ModelResourceLocation.standalone(TridentRegistry.IRON_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand")));
		event.register(ModelResourceLocation.standalone(TridentRegistry.GOLDEN_TRIDENT.getId().withPrefix("item/")));
		event.register(ModelResourceLocation.standalone(TridentRegistry.GOLDEN_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand")));
		event.register(ModelResourceLocation.standalone(TridentRegistry.DIAMOND_TRIDENT.getId().withPrefix("item/")));
		event.register(ModelResourceLocation.standalone(TridentRegistry.DIAMOND_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand")));
		event.register(ModelResourceLocation.standalone(TridentRegistry.NETHERITE_TRIDENT.getId().withPrefix("item/")));
		event.register(ModelResourceLocation.standalone(TridentRegistry.NETHERITE_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand")));
		event.register(ModelResourceLocation.standalone(TridentRegistry.BONE_TRIDENT.getId().withPrefix("item/")));
		event.register(ModelResourceLocation.standalone(TridentRegistry.BONE_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand")));
		event.register(ModelResourceLocation.standalone(TridentRegistry.PITCHFORK.getId().withPrefix("item/")));
		event.register(ModelResourceLocation.standalone(TridentRegistry.PITCHFORK.getId().withPrefix("item/").withSuffix("_in_hand")));
		event.register(ModelResourceLocation.standalone(TridentRegistry.COPPER_TRIDENT.getId().withPrefix("item/")));
		event.register(ModelResourceLocation.standalone(TridentRegistry.COPPER_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand")));
	}
}
