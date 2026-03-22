package com.mrbysco.tieredtridents.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Transformation;
import com.mojang.serialization.MapCodec;
import com.mrbysco.tieredtridents.client.renderer.ThrownTieredTridentRenderer;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.projectile.TridentModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionfc;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public class TieredTridentSpecialRenderer implements SpecialModelRenderer<Identifier> {
	public static final Transformation DEFAULT_TRANSFORMATION = new Transformation((Vector3fc)null, (Quaternionfc)null, new Vector3f(1.0F, -1.0F, -1.0F), (Quaternionfc)null);
	private final TridentModel model;

	public TieredTridentSpecialRenderer(TridentModel model) {
		this.model = model;
	}

	@Override
	public void submit(@Nullable Identifier texture, PoseStack poseStack, SubmitNodeCollector nodeCollector,
	                   int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
		if (texture == null) return;

		nodeCollector.submitModelPart(this.model.root(), poseStack, this.model.renderType(texture),
				lightCoords, overlayCoords, null, false, hasFoil, -1, null, outlineColor);
	}

	@Override
	public void getExtents(Consumer<Vector3fc> output) {
		PoseStack poseStack = new PoseStack();
		this.model.root().getExtentsForGui(poseStack, output);
	}

	@Override
	public Identifier extractArgument(ItemStack stack) {
		return ThrownTieredTridentRenderer.textureMap.getOrDefault(stack.getItem(), TridentModel.TEXTURE);
	}

	public static record Unbaked() implements SpecialModelRenderer.Unbaked<Identifier> {
		public static final MapCodec<TieredTridentSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(new TieredTridentSpecialRenderer.Unbaked());

		public MapCodec<TieredTridentSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

		public TieredTridentSpecialRenderer bake(SpecialModelRenderer.BakingContext context) {
			return new TieredTridentSpecialRenderer(new TridentModel(context.entityModelSet().bakeLayer(ModelLayers.TRIDENT)));
		}
	}
}
