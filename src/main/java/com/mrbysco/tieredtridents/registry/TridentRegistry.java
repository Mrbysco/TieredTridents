package com.mrbysco.tieredtridents.registry;

import com.mrbysco.tieredtridents.TieredTridents;
import com.mrbysco.tieredtridents.entity.ThrownTieredTrident;
import com.mrbysco.tieredtridents.item.TieredTridentItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class TridentRegistry {

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TieredTridents.MOD_ID);
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, TieredTridents.MOD_ID);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TieredTridents.MOD_ID);

	public static final DeferredItem<Item> WOODEN_TRIDENT = ITEMS.registerItem("wooden_trident", (properties) -> new TieredTridentItem(properties, Tiers.WOOD));
	public static final DeferredItem<Item> STONE_TRIDENT = ITEMS.registerItem("stone_trident", (properties) -> new TieredTridentItem(properties, Tiers.STONE));
	public static final DeferredItem<Item> IRON_TRIDENT = ITEMS.registerItem("iron_trident", (properties) -> new TieredTridentItem(properties, Tiers.IRON));
	public static final DeferredItem<Item> COPPER_TRIDENT = ITEMS.registerItem("copper_trident", (properties) -> new TieredTridentItem(properties, Tiers.IRON));
	public static final DeferredItem<Item> GOLDEN_TRIDENT = ITEMS.registerItem("golden_trident", (properties) -> new TieredTridentItem(properties, Tiers.GOLD));
	public static final DeferredItem<Item> DIAMOND_TRIDENT = ITEMS.registerItem("diamond_trident", (properties) -> new TieredTridentItem(properties, Tiers.DIAMOND));
	public static final DeferredItem<Item> NETHERITE_TRIDENT = ITEMS.registerItem("netherite_trident", (properties) -> new TieredTridentItem(properties, Tiers.NETHERITE));
	public static final DeferredItem<Item> BONE_TRIDENT = ITEMS.registerItem("bone_trident", (properties) -> new TieredTridentItem(properties, Tiers.STONE));
	public static final DeferredItem<Item> PITCHFORK = ITEMS.registerItem("pitchfork", (properties) -> new TieredTridentItem(properties, Tiers.STONE));

	public static final DeferredHolder<EntityType<?>, EntityType<ThrownTieredTrident>> TIERED_TRIDENT = ENTITIES.register("tiered_trident",
			() -> EntityType.Builder.<ThrownTieredTrident>of(ThrownTieredTrident::new, MobCategory.MISC)
					.sized(0.5F, 0.5F)
					.clientTrackingRange(4)
					.updateInterval(20)
					.build("tiered_trident"));

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("items", () -> CreativeModeTab.builder()
			.icon(() -> new ItemStack(WOODEN_TRIDENT.get()))
			.withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
			.title(Component.translatable("itemGroup.tieredtridents"))
			.displayItems((displayParameters, output) -> {
				List<ItemStack> stacks = ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
				output.acceptAll(stacks);
			}).build());
}
