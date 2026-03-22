package com.mrbysco.tieredtridents.client;

import com.mrbysco.tieredtridents.TieredTridents;
import com.mrbysco.tieredtridents.client.renderer.ThrownTieredTridentRenderer;
import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;

@EventBusSubscriber(modid = TieredTridents.MOD_ID, value = Dist.CLIENT)
public class ClientHandler {

	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(TridentRegistry.TIERED_TRIDENT.get(), ThrownTieredTridentRenderer::new);
	}

	@SubscribeEvent
	public static void registerSpecialModelRenderers(RegisterSpecialModelRendererEvent event) {
		event.register(TieredTridents.modLoc("trident"), TieredTridentSpecialRenderer.Unbaked.MAP_CODEC);
	}
}
