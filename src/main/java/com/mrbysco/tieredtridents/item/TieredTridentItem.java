package com.mrbysco.tieredtridents.item;

import com.google.common.collect.ImmutableMultimap;
import com.mrbysco.tieredtridents.client.TridentBEWLR;
import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class TieredTridentItem extends TridentItem {
	private final Tier tier;

	public TieredTridentItem(Properties properties, Tier tier) {
		super(properties.defaultDurability(tier.getUses()));
		this.tier = tier;
		int attackDamageModifier = 3;
		float attackSpeedModifier = -2.4F;

		float attackDamage = (float) attackDamageModifier + tier.getAttackDamageBonus();
		ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", attackDamage, AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", attackSpeedModifier, AttributeModifier.Operation.ADDITION));
		this.defaultModifiers = builder.build();
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		boolean flag = super.canApplyAtEnchantingTable(stack, enchantment) && !(enchantment == Enchantments.IMPALING || enchantment == Enchantments.RIPTIDE);
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
