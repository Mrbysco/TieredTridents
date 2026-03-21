package com.mrbysco.tieredtridents.client;

import com.mrbysco.tieredtridents.TieredTridents;
import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TieredTridents.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientHandler {

	@SubscribeEvent
	public static void modelRegistry(ModelEvent.RegisterAdditional event) {
		event.register(TridentRegistry.WOODEN_TRIDENT.getId().withPrefix("item/"));
		event.register(TridentRegistry.WOODEN_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand"));
		event.register(TridentRegistry.STONE_TRIDENT.getId().withPrefix("item/"));
		event.register(TridentRegistry.STONE_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand"));
		event.register(TridentRegistry.IRON_TRIDENT.getId().withPrefix("item/"));
		event.register(TridentRegistry.IRON_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand"));
		event.register(TridentRegistry.GOLDEN_TRIDENT.getId().withPrefix("item/"));
		event.register(TridentRegistry.GOLDEN_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand"));
		event.register(TridentRegistry.DIAMOND_TRIDENT.getId().withPrefix("item/"));
		event.register(TridentRegistry.DIAMOND_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand"));
		event.register(TridentRegistry.NETHERITE_TRIDENT.getId().withPrefix("item/"));
		event.register(TridentRegistry.NETHERITE_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand"));
		event.register(TridentRegistry.BONE_TRIDENT.getId().withPrefix("item/"));
		event.register(TridentRegistry.BONE_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand"));
		event.register(TridentRegistry.PITCHFORK.getId().withPrefix("item/"));
		event.register(TridentRegistry.PITCHFORK.getId().withPrefix("item/").withSuffix("_in_hand"));
		event.register(TridentRegistry.COPPER_TRIDENT.getId().withPrefix("item/"));
		event.register(TridentRegistry.COPPER_TRIDENT.getId().withPrefix("item/").withSuffix("_in_hand"));
	}
}
