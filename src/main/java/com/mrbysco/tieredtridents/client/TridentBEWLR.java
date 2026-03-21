package com.mrbysco.tieredtridents.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mrbysco.tieredtridents.TieredTridents;
import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.client.model.TridentModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

public class TridentBEWLR extends BlockEntityWithoutLevelRenderer {
	private final Map<Item, ResourceLocation> textureMap = Map.of(
			TridentRegistry.WOODEN_TRIDENT.get(), TieredTridents.modLoc("textures/entity/wooden_trident.png"),
			TridentRegistry.STONE_TRIDENT.get(), TieredTridents.modLoc("textures/entity/stone_trident.png"),
			TridentRegistry.IRON_TRIDENT.get(), TieredTridents.modLoc("textures/entity/iron_trident.png"),
			TridentRegistry.COPPER_TRIDENT.get(), TieredTridents.modLoc("textures/entity/copper_trident.png"),
			TridentRegistry.GOLDEN_TRIDENT.get(), TieredTridents.modLoc("textures/entity/golden_trident.png"),
			TridentRegistry.DIAMOND_TRIDENT.get(), TieredTridents.modLoc("textures/entity/diamond_trident.png"),
			TridentRegistry.NETHERITE_TRIDENT.get(), TieredTridents.modLoc("textures/entity/netherite_trident.png"),
			TridentRegistry.BONE_TRIDENT.get(), TieredTridents.modLoc("textures/entity/bone_trident.png"),
			TridentRegistry.PITCHFORK.get(), TieredTridents.modLoc("textures/entity/pitchfork.png")
	);

	private TridentModel tridentModel;

	public TridentBEWLR(BlockEntityRendererProvider.Context context) {
		super(context.getBlockEntityRenderDispatcher(), context.getModelSet());
		this.tridentModel = new TridentModel(context.getModelSet().bakeLayer(ModelLayers.TRIDENT));
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack,
	                         MultiBufferSource buffer, int packedLight, int packedOverlay) {
		poseStack.pushPose();
		poseStack.scale(1.0F, -1.0F, -1.0F);
		VertexConsumer vertexConsumer = ItemRenderer.getFoilBufferDirect(
				buffer, this.tridentModel.renderType(textureMap.getOrDefault(stack.getItem(), TridentModel.TEXTURE)), false, stack.hasFoil()
		);
		this.tridentModel.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay);
		poseStack.popPose();
	}
}
