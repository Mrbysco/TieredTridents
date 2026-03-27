package com.mrbysco.tieredtridents.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.mrbysco.tieredtridents.TieredTridents;
import com.mrbysco.tieredtridents.entity.ThrownTieredTrident;
import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.client.model.TridentModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;

import java.util.Map;

public class ThrownTieredTridentRenderer extends EntityRenderer<ThrownTieredTrident> {
	public static final ResourceLocation TRIDENT_LOCATION = new ResourceLocation("textures/entity/trident.png");
	public static final Map<Item, ResourceLocation> textureMap = Map.of(
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
	private final TridentModel model;

	public ThrownTieredTridentRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new TridentModel(context.bakeLayer(ModelLayers.TRIDENT));
	}

	@Override
	public void render(ThrownTieredTrident entity, float entityYaw, float partialTicks, PoseStack poseStack,
	                   MultiBufferSource buffer, int packedLight) {
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
		poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot()) + 90.0F));
		VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(buffer, this.model.renderType(this.getTextureLocation(entity)), false, entity.isFoil());
		this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		poseStack.popPose();
		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}

	@Override
	public ResourceLocation getTextureLocation(ThrownTieredTrident entity) {
		return textureMap.getOrDefault(entity.getOriginalItem().getItem(), TRIDENT_LOCATION);
	}
}
