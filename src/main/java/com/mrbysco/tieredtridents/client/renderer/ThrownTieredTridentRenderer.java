package com.mrbysco.tieredtridents.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.tieredtridents.TieredTridents;
import com.mrbysco.tieredtridents.entity.ThrownTieredTrident;
import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.projectile.TridentModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.feature.ItemFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.world.item.Item;

import java.util.Map;

public class ThrownTieredTridentRenderer extends EntityRenderer<ThrownTieredTrident, ThrownTieredTridentRenderState> {
	public static final Identifier TRIDENT_LOCATION = Identifier.withDefaultNamespace("textures/entity/trident.png");
	public static final Map<Item, Identifier> textureMap = Map.of(
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
	public void submit(ThrownTieredTridentRenderState renderState, PoseStack poseStack,
	                   SubmitNodeCollector nodeCollector, CameraRenderState camera) {
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(renderState.yRot - 90.0F));
		poseStack.mulPose(Axis.ZP.rotationDegrees(renderState.xRot + 90.0F));
		nodeCollector.order(0).submitModel(this.model, Unit.INSTANCE, poseStack, renderState.tridentTexture,
				renderState.lightCoords, OverlayTexture.NO_OVERLAY, renderState.outlineColor, null);
		if (renderState.isFoil) {
			nodeCollector.order(1).submitModel(this.model, Unit.INSTANCE, poseStack,
					ItemFeatureRenderer.getFoilRenderType(this.model.renderType(renderState.tridentTexture), false),
					renderState.lightCoords, OverlayTexture.NO_OVERLAY, renderState.outlineColor, null);
		}

		poseStack.popPose();
		super.submit(renderState, poseStack, nodeCollector, camera);
	}

	@Override
	public ThrownTieredTridentRenderState createRenderState() {
		return new ThrownTieredTridentRenderState();
	}

	@Override
	public void extractRenderState(ThrownTieredTrident entity, ThrownTieredTridentRenderState renderState, float partialTick) {
		super.extractRenderState(entity, renderState, partialTick);
		renderState.yRot = entity.getYRot(partialTick);
		renderState.xRot = entity.getXRot(partialTick);
		renderState.isFoil = entity.isFoil();
		if (entity.getOriginalItem().isEmpty()) {
			renderState.tridentTexture = TRIDENT_LOCATION;
		} else {
			renderState.tridentTexture = textureMap.getOrDefault(entity.getOriginalItem().getItem(), TRIDENT_LOCATION);
		}
	}
}
