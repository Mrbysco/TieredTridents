package com.mrbysco.tieredtridents.datagen.assets;

import com.mrbysco.tieredtridents.TieredTridents;
import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class TridentItemProvider extends ItemModelProvider {
	public TridentItemProvider(PackOutput output, ExistingFileHelper fileHelper) {
		super(output, TieredTridents.MOD_ID, fileHelper);
	}

	@Override
	protected void registerModels() {
		tridentModels(TridentRegistry.WOODEN_TRIDENT.getId());
		tridentModels(TridentRegistry.STONE_TRIDENT.getId());
		tridentModels(TridentRegistry.IRON_TRIDENT.getId());
		tridentModels(TridentRegistry.COPPER_TRIDENT.getId());
		tridentModels(TridentRegistry.GOLDEN_TRIDENT.getId());
		tridentModels(TridentRegistry.DIAMOND_TRIDENT.getId());
		tridentModels(TridentRegistry.NETHERITE_TRIDENT.getId());
		tridentModels(TridentRegistry.BONE_TRIDENT.getId());
		tridentModels(TridentRegistry.PITCHFORK.getId());
	}

	private void tridentModels(ResourceLocation itemId) {
		String path = itemId.getPath();
		String inHandModel = path + "_in_hand";
		String throwingModel = path + "_throwing";
		ResourceLocation particleTexture = modLoc("item/" + path);
		ModelFile throwingModelFile = new ModelFile.UncheckedModelFile(modLoc(ITEM_FOLDER + "/" + throwingModel).toString());

		// Regular item model
		getBuilder(itemId.toString())
				.parent(new ModelFile.UncheckedModelFile("item/generated"))
				.texture("layer0", ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), "item/" + itemId.getPath()));

		// In-hand model
		getBuilder(inHandModel)
				.parent(new ModelFile.UncheckedModelFile("builtin/entity"))
				.guiLight(BlockModel.GuiLight.FRONT)
				.texture("particle", particleTexture)
				.transforms()
				.transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND).rotation(0.0F, 60.0F, 0.0F).translation(11.0F, 17.0F, -2.0F).scale(1.0F, 1.0F, 1.0F).end()
				.transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND).rotation(0.0F, 60.0F, 0.0F).translation(3.0F, 17.0F, 12.0F).scale(1.0F, 1.0F, 1.0F).end()
				.transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND).rotation(0.0F, -90.0F, 25.0F).translation(-3.0F, 17.0F, 1.0F).scale(1.0F, 1.0F, 1.0F).end()
				.transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND).rotation(0.0F, 90.0F, -25.0F).translation(13.0F, 17.0F, 1.0F).scale(1.0F, 1.0F, 1.0F).end()
				.transform(ItemDisplayContext.GUI).rotation(15.0F, -25.0F, -5.0F).translation(2.0F, 3.0F, 0.0F).scale(0.65F, 0.65F, 0.65F).end()
				.transform(ItemDisplayContext.FIXED).rotation(0.0F, 180.0F, 0.0F).translation(-2.0F, 4.0F, -5.0F).scale(0.5F, 0.5F, 0.5F).end()
				.transform(ItemDisplayContext.GROUND).rotation(0.0F, 0.0F, 0.0F).translation(4.0F, 4.0F, 2.0F).scale(0.25F, 0.25F, 0.25F).end()
				.end()
				.override()
				.predicate(mcLoc("throwing"), 1.0F)
				.model(throwingModelFile)
				.end();

		// Throwing model
		getBuilder(throwingModel)
				.parent(new ModelFile.UncheckedModelFile("builtin/entity"))
				.guiLight(BlockModel.GuiLight.FRONT)
				.texture("particle", particleTexture)
				.transforms()
				.transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND).rotation(0.0F, 90.0F, 180.0F).translation(8.0F, -17.0F, 9.0F).scale(1.0F, 1.0F, 1.0F).end()
				.transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND).rotation(0.0F, 90.0F, 180.0F).translation(8.0F, -17.0F, -7.0F).scale(1.0F, 1.0F, 1.0F).end()
				.transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND).rotation(0.0F, -90.0F, 25.0F).translation(-3.0F, 17.0F, 1.0F).scale(1.0F, 1.0F, 1.0F).end()
				.transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND).rotation(0.0F, 90.0F, -25.0F).translation(13.0F, 17.0F, 1.0F).scale(1.0F, 1.0F, 1.0F).end()
				.transform(ItemDisplayContext.GUI).rotation(15.0F, -25.0F, -5.0F).translation(2.0F, 3.0F, 0.0F).scale(0.65F, 0.65F, 0.65F).end()
				.transform(ItemDisplayContext.FIXED).rotation(0.0F, 180.0F, 0.0F).translation(-2.0F, 4.0F, -5.0F).scale(0.5F, 0.5F, 0.5F).end()
				.transform(ItemDisplayContext.GROUND).rotation(0.0F, 0.0F, 0.0F).translation(4.0F, 4.0F, 2.0F).scale(0.25F, 0.25F, 0.25F).end()
				.end();
	}
}
