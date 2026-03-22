package com.mrbysco.tieredtridents;

import com.mojang.logging.LogUtils;
import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(TieredTridents.MOD_ID)
public class TieredTridents {
	public static final String MOD_ID = "tieredtridents";
	public static final Logger LOGGER = LogUtils.getLogger();

	public TieredTridents(IEventBus eventBus) {
		TridentRegistry.ITEMS.register(eventBus);
		TridentRegistry.ENTITIES.register(eventBus);
		TridentRegistry.CREATIVE_MODE_TABS.register(eventBus);
	}

	public static Identifier modLoc(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
