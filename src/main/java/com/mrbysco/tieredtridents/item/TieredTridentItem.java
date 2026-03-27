package com.mrbysco.tieredtridents.item;

import com.google.common.collect.ImmutableMultimap;
import com.mrbysco.tieredtridents.client.TridentBEWLR;
import com.mrbysco.tieredtridents.entity.ThrownTieredTrident;
import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
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
	public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int timeLeft) {
		if (livingEntity instanceof Player player) {
			int i = this.getUseDuration(stack) - timeLeft;
			if (i >= 10) {
				int j = EnchantmentHelper.getRiptide(stack);
				if (j <= 0 || player.isInWaterOrRain()) {
					if (!level.isClientSide) {
						stack.hurtAndBreak(1, player, (player1) -> {
							player1.broadcastBreakEvent(livingEntity.getUsedItemHand());
						});
						if (j == 0) {
							ThrownTieredTrident tieredTrident = new ThrownTieredTrident(level, player, stack);
							tieredTrident.setOriginalItem(stack.copy());
							tieredTrident.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F + (float)j * 0.5F, 1.0F);
							if (player.getAbilities().instabuild) {
								tieredTrident.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
							}

							level.addFreshEntity(tieredTrident);
							level.playSound((Player)null, tieredTrident, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
							if (!player.getAbilities().instabuild) {
								player.getInventory().removeItem(stack);
							}
						}
					}

					player.awardStat(Stats.ITEM_USED.get(this));
					if (j > 0) {
						float f7 = player.getYRot();
						float f = player.getXRot();
						float f1 = -Mth.sin(f7 * ((float)Math.PI / 180F)) * Mth.cos(f * ((float)Math.PI / 180F));
						float f2 = -Mth.sin(f * ((float)Math.PI / 180F));
						float f3 = Mth.cos(f7 * ((float)Math.PI / 180F)) * Mth.cos(f * ((float)Math.PI / 180F));
						float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
						float f5 = 3.0F * ((1.0F + (float)j) / 4.0F);
						f1 *= f5 / f4;
						f2 *= f5 / f4;
						f3 *= f5 / f4;
						player.push(f1, f2, f3);
						player.startAutoSpinAttack(20);
						if (player.onGround()) {
							float f6 = 1.1999999F;
							player.move(MoverType.SELF, new Vec3(0.0D, (double)1.1999999F, 0.0D));
						}

						SoundEvent soundevent;
						if (j >= 3) {
							soundevent = SoundEvents.TRIDENT_RIPTIDE_3;
						} else if (j == 2) {
							soundevent = SoundEvents.TRIDENT_RIPTIDE_2;
						} else {
							soundevent = SoundEvents.TRIDENT_RIPTIDE_1;
						}

						level.playSound((Player)null, player, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
					}

				}
			}
		}
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
