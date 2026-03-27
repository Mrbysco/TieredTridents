package com.mrbysco.tieredtridents.entity;

import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

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
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(TIER_ITEM, ItemStack.EMPTY);
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
		Entity entity1 = this.getOwner();
		DamageSource damagesource = this.damageSources().trident(this, (Entity)(entity1 == null ? this : entity1));
		if (this.level() instanceof ServerLevel serverlevel) {
			f = EnchantmentHelper.modifyDamage(serverlevel, this.getOriginalItem(), entity, damagesource, f);
		}

		this.dealtDamage = true;
		if (entity.hurt(damagesource, f)) {
			if (entity.getType() == EntityType.ENDERMAN) {
				return;
			}

			if (this.level() instanceof ServerLevel serverlevel1) {
				EnchantmentHelper.doPostAttackEffectsWithItemSource(serverlevel1, entity, damagesource, this.getOriginalItem());
			}

			if (entity instanceof LivingEntity livingentity) {
				this.doKnockback(livingentity, damagesource);
				this.doPostHurtEffects(livingentity);
			}
		}

		this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01, -0.1, -0.01));
		this.playSound(SoundEvents.TRIDENT_HIT, 1.0F, 1.0F);
	}
}
