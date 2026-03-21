package com.mrbysco.tieredtridents.item;

import com.mrbysco.tieredtridents.client.TridentBEWLR;
import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.List;
import java.util.function.Consumer;

public class TieredTridentItem extends TridentItem {
	private final Tier tier;

	public TieredTridentItem(Properties properties, Tier tier) {
		super(properties
				.component(DataComponents.TOOL, TridentItem.createToolProperties())
				.attributes(SwordItem.createAttributes(tier, 3, -2.4F))
		);
		this.tier = tier;
	}

	public static Tool createToolProperties() {
		return new Tool(List.of(
				Tool.Rule.minesAndDrops(List.of(Blocks.COBWEB), 15.0F),
				Tool.Rule.overrideSpeed(BlockTags.SWORD_EFFICIENT, 1.5F)
		), 1.0F, 2);
	}

	@Override
	public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
		boolean flag = super.supportsEnchantment(stack, enchantment) && !(enchantment == Enchantments.IMPALING || enchantment == Enchantments.RIPTIDE);
		if (stack.is(TridentRegistry.COPPER_TRIDENT.get())) {
			return flag;
		}
		return flag && enchantment != Enchantments.CHANNELING;
	}

	@Override
	public int getEnchantmentValue(ItemStack stack) {
		return this.tier.getEnchantmentValue();
	}

	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
		return this.tier.getRepairIngredient().test(repair) || super.isValidRepairItem(toRepair, repair);
	}

	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		consumer.accept(new IClientItemExtensions() {
			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				Minecraft mc = Minecraft.getInstance();
				return new TridentBEWLR(new BlockEntityRendererProvider.Context(
						mc.getBlockEntityRenderDispatcher(),
						mc.getBlockRenderer(),
						mc.getItemRenderer(),
						mc.getEntityRenderDispatcher(),
						mc.getEntityModels(),
						mc.font
				));
			}
		});
	}
}
