package com.mrbysco.tieredtridents;

import com.mojang.logging.LogUtils;
import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(TieredTridents.MOD_ID)
public class TieredTridents {
	public static final String MOD_ID = "tieredtridents";
	public static final Logger LOGGER = LogUtils.getLogger();

	public TieredTridents() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		TridentRegistry.ITEMS.register(eventBus);
		TridentRegistry.CREATIVE_MODE_TABS.register(eventBus);
	}

	public static ResourceLocation modLoc(String path) {
		return new ResourceLocation(MOD_ID, path);
	}
}
