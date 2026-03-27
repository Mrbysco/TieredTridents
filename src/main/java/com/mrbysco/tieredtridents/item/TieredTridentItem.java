package com.mrbysco.tieredtridents.item;

import com.mrbysco.tieredtridents.client.TridentBEWLR;
import com.mrbysco.tieredtridents.entity.ThrownTieredTrident;
import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class TieredTridentItem extends TridentItem {
	private final Tier tier;

	public TieredTridentItem(Properties properties, Tier tier) {
		super(properties
				.durability(tier.getUses())
				.component(DataComponents.TOOL, TridentItem.createToolProperties())
				.attributes(SwordItem.createAttributes(tier, 3, -2.4F))
		);
		this.tier = tier;
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
	public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof Player player) {
			int i = this.getUseDuration(stack, entityLiving) - timeLeft;
			if (i >= 10) {
				float f = EnchantmentHelper.getTridentSpinAttackStrength(stack, player);
				if ((!(f > 0.0F) || player.isInWaterOrRain()) && !isTooDamagedToUse(stack)) {
					Holder<SoundEvent> holder = EnchantmentHelper.pickHighestLevel(stack, EnchantmentEffectComponents.TRIDENT_SOUND).orElse(SoundEvents.TRIDENT_THROW);
					if (!level.isClientSide) {
						stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(entityLiving.getUsedItemHand()));
						if (f == 0.0F) {
							ThrownTieredTrident tieredTrident = new ThrownTieredTrident(level, player, stack);
							tieredTrident.setOriginalItem(stack);
							tieredTrident.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
							if (player.hasInfiniteMaterials()) {
								tieredTrident.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
							}

							level.addFreshEntity(tieredTrident);
							level.playSound((Player)null, tieredTrident, holder.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
							if (!player.hasInfiniteMaterials()) {
								player.getInventory().removeItem(stack);
							}
						}
					}

					player.awardStat(Stats.ITEM_USED.get(this));
					if (f > 0.0F) {
						float f7 = player.getYRot();
						float f1 = player.getXRot();
						float f2 = -Mth.sin(f7 * ((float)Math.PI / 180F)) * Mth.cos(f1 * ((float)Math.PI / 180F));
						float f3 = -Mth.sin(f1 * ((float)Math.PI / 180F));
						float f4 = Mth.cos(f7 * ((float)Math.PI / 180F)) * Mth.cos(f1 * ((float)Math.PI / 180F));
						float f5 = Mth.sqrt(f2 * f2 + f3 * f3 + f4 * f4);
						f2 *= f / f5;
						f3 *= f / f5;
						f4 *= f / f5;
						player.push(f2, f3, f4);
						player.startAutoSpinAttack(20, 8.0F, stack);
						if (player.onGround()) {
							player.move(MoverType.SELF, new Vec3(0.0F, 1.1999999F, 0.0F));
						}

						level.playSound((Player)null, player, holder.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
					}
				}
			}
		}
	}

	private static boolean isTooDamagedToUse(ItemStack stack) {
		return stack.getDamageValue() >= stack.getMaxDamage() - 1;
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
