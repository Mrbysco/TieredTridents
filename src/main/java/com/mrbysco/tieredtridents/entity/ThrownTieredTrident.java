package com.mrbysco.tieredtridents.entity;

import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ThrownTieredTrident extends ThrownTrident {
	private static final EntityDataAccessor<ItemStack> TIER_ITEM = SynchedEntityData.defineId(ThrownTieredTrident.class, EntityDataSerializers.ITEM_STACK);

	public ThrownTieredTrident(EntityType<? extends ThrownTrident> entityType, Level level) {
		super(entityType, level);
	}

	public ThrownTieredTrident(Level level, LivingEntity shooter, ItemStack stack) {
		super(level, shooter, stack);
	}

	@Override
	public EntityType<?> getType() {
		return TridentRegistry.TIERED_TRIDENT.get();
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(TIER_ITEM, ItemStack.EMPTY);
	}

	@Override
	protected ItemStack getPickupItem() {
		return getOriginalItem().copy();
	}

	public void setOriginalItem(ItemStack stack) {
		this.entityData.set(TIER_ITEM, stack);
	}

	public ItemStack getOriginalItem() {
		return this.entityData.get(TIER_ITEM);
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		Entity entity = result.getEntity();
		float f = 8.0F;
		if (entity instanceof LivingEntity livingentity) {
			f += EnchantmentHelper.getDamageBonus(this.getOriginalItem(), livingentity.getMobType());
		}

		Entity entity1 = this.getOwner();
		DamageSource damagesource = this.damageSources().trident(this, (Entity)(entity1 == null ? this : entity1));
		this.dealtDamage = true;
		SoundEvent soundevent = SoundEvents.TRIDENT_HIT;
		if (entity.hurt(damagesource, f)) {
			if (entity.getType() == EntityType.ENDERMAN) {
				return;
			}

			if (entity instanceof LivingEntity) {
				LivingEntity livingentity1 = (LivingEntity)entity;
				if (entity1 instanceof LivingEntity) {
					EnchantmentHelper.doPostHurtEffects(livingentity1, entity1);
					EnchantmentHelper.doPostDamageEffects((LivingEntity)entity1, livingentity1);
				}

				this.doPostHurtEffects(livingentity1);
			}
		}

		this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
		float f1 = 1.0F;
		if (this.level() instanceof ServerLevel && this.level().isThundering() && this.isChanneling()) {
			BlockPos blockpos = entity.blockPosition();
			if (this.level().canSeeSky(blockpos)) {
				LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(this.level());
				if (lightningbolt != null) {
					lightningbolt.moveTo(Vec3.atBottomCenterOf(blockpos));
					lightningbolt.setCause(entity1 instanceof ServerPlayer ? (ServerPlayer)entity1 : null);
					this.level().addFreshEntity(lightningbolt);
					soundevent = SoundEvents.TRIDENT_THUNDER;
					f1 = 5.0F;
				}
			}
		}

		this.playSound(soundevent, f1, 1.0F);
	}
}
