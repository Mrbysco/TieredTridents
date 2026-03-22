package com.mrbysco.tieredtridents.datagen.assets;

import com.mrbysco.tieredtridents.TieredTridents;
import com.mrbysco.tieredtridents.client.TieredTridentSpecialRenderer;
import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.special.TridentSpecialRenderer;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import java.util.Optional;

public class TridentItemProvider extends ModelProvider {
	public TridentItemProvider(PackOutput output) {
		super(output, TieredTridents.MOD_ID);
	}

	@Override
	protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
		generateTrident(itemModels, TridentRegistry.WOODEN_TRIDENT.get());
		generateTrident(itemModels, TridentRegistry.STONE_TRIDENT.get());
		generateTrident(itemModels, TridentRegistry.IRON_TRIDENT.get());
		generateTrident(itemModels, TridentRegistry.COPPER_TRIDENT.get());
		generateTrident(itemModels, TridentRegistry.GOLDEN_TRIDENT.get());
		generateTrident(itemModels, TridentRegistry.DIAMOND_TRIDENT.get());
		generateTrident(itemModels, TridentRegistry.NETHERITE_TRIDENT.get());
		generateTrident(itemModels, TridentRegistry.BONE_TRIDENT.get());
		generateTrident(itemModels, TridentRegistry.PITCHFORK.get());
	}

	private static final ModelTemplate TRIDENT_IN_HAND_TEMPLATE = new ModelTemplate(
			Optional.of(Identifier.withDefaultNamespace("item/trident_in_hand")),
			Optional.empty(),
			TextureSlot.PARTICLE
	);
	private static final ModelTemplate TRIDENT_THROWING_TEMPLATE = new ModelTemplate(
			Optional.of(Identifier.withDefaultNamespace("item/trident_throwing")),
			Optional.empty(),
			TextureSlot.PARTICLE
	);


	public void generateTrident(ItemModelGenerators itemModels, Item tridentItem) {
		TextureMapping particle = new TextureMapping()
				.put(TextureSlot.PARTICLE, TextureMapping.getItemTexture(tridentItem));

		TRIDENT_IN_HAND_TEMPLATE.create(
				ModelLocationUtils.getModelLocation(tridentItem, "_in_hand"),
				particle,
				itemModels.modelOutput
		);
		TRIDENT_THROWING_TEMPLATE.create(
				ModelLocationUtils.getModelLocation(tridentItem, "_throwing"),
				particle,
				itemModels.modelOutput
		);

		ItemModel.Unbaked flatModel = ItemModelUtils.plainModel(itemModels.createFlatItemModel(tridentItem, ModelTemplates.FLAT_ITEM));
		ItemModel.Unbaked inHandNormalModel = ItemModelUtils.specialModel(
				ModelLocationUtils.getModelLocation(tridentItem, "_in_hand"), new TieredTridentSpecialRenderer.Unbaked()
		);
		ItemModel.Unbaked inHandThrowingModel = ItemModelUtils.specialModel(
				ModelLocationUtils.getModelLocation(tridentItem, "_throwing"), new TieredTridentSpecialRenderer.Unbaked()
		);
		ItemModel.Unbaked inHandModel = ItemModelUtils.conditional(
				TridentSpecialRenderer.DEFAULT_TRANSFORMATION, ItemModelUtils.isUsingItem(), inHandThrowingModel, inHandNormalModel
		);
		itemModels.itemModelOutput.accept(tridentItem, ItemModelGenerators.createFlatModelDispatch(flatModel, inHandModel));
	}
}
