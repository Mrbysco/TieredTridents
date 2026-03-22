package com.mrbysco.tieredtridents.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mrbysco.tieredtridents.client.renderer.ThrownTieredTridentRenderer;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.projectile.TridentModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public class TieredTridentSpecialRenderer implements SpecialModelRenderer<Identifier> {
	private final TridentModel model;

	public TieredTridentSpecialRenderer(TridentModel model) {
		this.model = model;
	}

	@Override
	public void submit(@Nullable Identifier texture, ItemDisplayContext displayContext, PoseStack poseStack, SubmitNodeCollector nodeCollector, int packedLight, int packedOverlay, boolean hasFoil, int outlineColor) {
		if (texture == null) return;

		poseStack.pushPose();
		poseStack.scale(1.0F, -1.0F, -1.0F);
		nodeCollector.submitModelPart(this.model.root(), poseStack, this.model.renderType(texture),
				packedLight, packedOverlay, null, false, hasFoil, -1, null, outlineColor);
		poseStack.popPose();
	}

	@Override
	public void getExtents(Consumer<Vector3fc> output) {
		PoseStack posestack = new PoseStack();
		posestack.scale(1.0F, -1.0F, -1.0F);
		this.model.root().getExtentsForGui(posestack, output);
	}

	@Override
	public Identifier extractArgument(ItemStack stack) {
		return ThrownTieredTridentRenderer.textureMap.getOrDefault(stack.getItem(), TridentModel.TEXTURE);
	}

	public record Unbaked() implements SpecialModelRenderer.Unbaked {
		public static final MapCodec<TieredTridentSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(new TieredTridentSpecialRenderer.Unbaked());

		@Override
		public MapCodec<TieredTridentSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

		@Override
		public SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext context) {
			return new TieredTridentSpecialRenderer(new TridentModel(context.entityModelSet().bakeLayer(ModelLayers.TRIDENT)));
		}
	}
}
