package com.mrbysco.tieredtridents.entity;

import com.mrbysco.tieredtridents.registry.TridentRegistry;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ThrownTieredTrident extends ThrownTrident {
	private static final EntityDataAccessor<ItemStack> TIER_ITEM = SynchedEntityData.defineId(ThrownTieredTrident.class, EntityDataSerializers.ITEM_STACK);

	private ItemStack originalItem;

	public ThrownTieredTrident(EntityType<? extends ThrownTrident> type, Level level) {
		super(type, level);
	}

	public ThrownTieredTrident(Level level, LivingEntity shooter, ItemStack pickupItemStack) {
		super(level, shooter, pickupItemStack);
	}

	public ThrownTieredTrident(Level level, double x, double y, double z, ItemStack pickupItemStack) {
		super(level, x, y, z, pickupItemStack);
		this.entityData.set(TIER_ITEM, pickupItemStack);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(TIER_ITEM, ItemStack.EMPTY);
	}

	public void setOriginalItem(ItemStack stack) {
		this.entityData.set(TIER_ITEM, stack);
	}

	public ItemStack getOriginalItem() {
		return this.entityData.get(TIER_ITEM);
	}

	@Override
	public EntityType<?> getType() {
		return TridentRegistry.TIERED_TRIDENT.get();
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return TridentRegistry.WOODEN_TRIDENT.toStack();
	}
}
