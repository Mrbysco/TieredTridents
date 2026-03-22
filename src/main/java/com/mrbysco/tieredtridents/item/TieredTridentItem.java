package com.mrbysco.tieredtridents.item;

import com.mrbysco.tieredtridents.entity.ThrownTieredTrident;
import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Position;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class TieredTridentItem extends TridentItem {

	public TieredTridentItem(Properties properties, ToolMaterial material) {
		super(properties
				.component(DataComponents.TOOL, TridentItem.createToolProperties())
				.tool(material, BlockTags.SWORD_EFFICIENT, 3, -2.4F, 0.0F)
		);
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
	public boolean releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int ticks) {
		if (livingEntity instanceof Player player) {
			int i = this.getUseDuration(stack, livingEntity) - ticks;
			if (i < 10) {
				return false;
			} else {
				float f = EnchantmentHelper.getTridentSpinAttackStrength(stack, player);
				if (f > 0.0F && !player.isInWaterOrRain()) {
					return false;
				} else if (stack.nextDamageWillBreak()) {
					return false;
				} else {
					Holder<SoundEvent> holder = EnchantmentHelper.pickHighestLevel(stack, EnchantmentEffectComponents.TRIDENT_SOUND)
							.orElse(SoundEvents.TRIDENT_THROW);
					player.awardStat(Stats.ITEM_USED.get(this));
					if (level instanceof ServerLevel serverlevel) {
						stack.hurtWithoutBreaking(1, player);
						if (f == 0.0F) {
							ItemStack itemstack = stack.consumeAndReturn(1, player);
							ThrownTieredTrident tieredTrident = Projectile.spawnProjectileFromRotation(
									ThrownTieredTrident::new, serverlevel, itemstack, player, 0.0F, 2.5F, 1.0F
							);
							tieredTrident.setOriginalItem(itemstack.copy());
							if (player.hasInfiniteMaterials()) {
								tieredTrident.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
							}

							level.playSound(null, tieredTrident, holder.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
							return true;
						}
					}

					if (f > 0.0F) {
						float f7 = player.getYRot();
						float f1 = player.getXRot();
						float f2 = -Mth.sin(f7 * (float) (Math.PI / 180.0)) * Mth.cos(f1 * (float) (Math.PI / 180.0));
						float f3 = -Mth.sin(f1 * (float) (Math.PI / 180.0));
						float f4 = Mth.cos(f7 * (float) (Math.PI / 180.0)) * Mth.cos(f1 * (float) (Math.PI / 180.0));
						float f5 = Mth.sqrt(f2 * f2 + f3 * f3 + f4 * f4);
						f2 *= f / f5;
						f3 *= f / f5;
						f4 *= f / f5;
						player.push(f2, f3, f4);
						player.startAutoSpinAttack(20, 8.0F, stack);
						if (player.onGround()) {
							player.move(MoverType.SELF, new Vec3(0.0, 1.1999999F, 0.0));
						}

						level.playSound(null, player, holder.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
						return true;
					} else {
						return false;
					}
				}
			}
		} else {
			return false;
		}
	}

	@Override
	public Projectile asProjectile(Level level, Position position, ItemStack stack, Direction direction) {
		ThrownTieredTrident throwntrident = new ThrownTieredTrident(level, position.x(), position.y(), position.z(), stack.copyWithCount(1));
		throwntrident.pickup = AbstractArrow.Pickup.ALLOWED;
		return throwntrident;
	}
}
