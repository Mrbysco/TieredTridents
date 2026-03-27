package com.mrbysco.tieredtridents.datagen.assets;

import com.mrbysco.tieredtridents.TieredTridents;
import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class TridentLanguageProvider extends LanguageProvider {
	public TridentLanguageProvider(PackOutput output) {
		super(output, TieredTridents.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		add("itemGroup.tieredtridents", "Tiered Tridents");
		addItem(TridentRegistry.WOODEN_TRIDENT, "Wooden Trident");
		addItem(TridentRegistry.STONE_TRIDENT, "Stone Trident");
		addItem(TridentRegistry.IRON_TRIDENT, "Iron Trident");
		addItem(TridentRegistry.COPPER_TRIDENT, "Copper Trident");
		addItem(TridentRegistry.GOLDEN_TRIDENT, "Golden Trident");
		addItem(TridentRegistry.DIAMOND_TRIDENT, "Diamond Trident");
		addItem(TridentRegistry.NETHERITE_TRIDENT, "Netherite Trident");
		addItem(TridentRegistry.BONE_TRIDENT, "Bone Trident");
		addItem(TridentRegistry.PITCHFORK, "Pitchfork");
	}
}
