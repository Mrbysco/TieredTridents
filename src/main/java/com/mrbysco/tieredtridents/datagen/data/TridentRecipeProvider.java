package com.mrbysco.tieredtridents.datagen.data;

import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class TridentRecipeProvider extends RecipeProvider {
	public TridentRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	@Override
	protected void buildRecipes(RecipeOutput output) {
		buildTridentRecipe(output, Tags.Items.RODS_WOODEN, ItemTags.PLANKS, TridentRegistry.WOODEN_TRIDENT.get());
		buildTridentRecipe(output, Tags.Items.RODS_WOODEN, Tags.Items.COBBLESTONES_NORMAL, TridentRegistry.STONE_TRIDENT.get());
		buildTridentRecipe(output, Tags.Items.RODS_WOODEN, Tags.Items.INGOTS_IRON, TridentRegistry.IRON_TRIDENT.get());
		buildTridentRecipe(output, Tags.Items.RODS_WOODEN, Tags.Items.INGOTS_COPPER, TridentRegistry.COPPER_TRIDENT.get());
		buildTridentRecipe(output, Tags.Items.RODS_WOODEN, Tags.Items.INGOTS_GOLD, TridentRegistry.GOLDEN_TRIDENT.get());
		buildTridentRecipe(output, Tags.Items.RODS_WOODEN, Tags.Items.GEMS_DIAMOND, TridentRegistry.DIAMOND_TRIDENT.get());
		SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
				Ingredient.of(TridentRegistry.DIAMOND_TRIDENT.get()), Ingredient.of(Items.NETHERITE_INGOT),
				RecipeCategory.COMBAT, TridentRegistry.NETHERITE_TRIDENT.get()).unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
				.save(output, TridentRegistry.NETHERITE_TRIDENT.getId().withSuffix("_smithing"));
		buildTridentRecipe(output, Items.BONE, Items.BONE_MEAL, TridentRegistry.BONE_TRIDENT.get());
		buildTridentRecipe(output, Items.BLAZE_ROD, Items.BLAZE_POWDER, TridentRegistry.PITCHFORK.get());
	}

	private void buildTridentRecipe(RecipeOutput output, ItemLike stick, ItemLike material, ItemLike result) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
				.pattern(" MM")
				.pattern(" SM")
				.pattern("S  ")
				.define('S', stick)
				.define('M', material)
				.unlockedBy("has_material", has(material))
				.save(output);

	}

	private void buildTridentRecipe(RecipeOutput output, TagKey<Item> stick, TagKey<Item> material, ItemLike result) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
				.pattern(" MM")
				.pattern(" SM")
				.pattern("S  ")
				.define('S', stick)
				.define('M', material)
				.unlockedBy("has_material", has(material))
				.save(output);

	}
}