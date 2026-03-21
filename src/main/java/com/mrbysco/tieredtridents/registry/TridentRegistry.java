package com.mrbysco.tieredtridents.registry;

import com.mrbysco.tieredtridents.TieredTridents;
import com.mrbysco.tieredtridents.item.TieredTridentItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class TridentRegistry {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TieredTridents.MOD_ID);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TieredTridents.MOD_ID);

	public static final RegistryObject<Item> WOODEN_TRIDENT = ITEMS.register("wooden_trident", () -> new TieredTridentItem(itemBuilder(), Tiers.WOOD));
	public static final RegistryObject<Item> STONE_TRIDENT = ITEMS.register("stone_trident", () -> new TieredTridentItem(itemBuilder(), Tiers.STONE));
	public static final RegistryObject<Item> IRON_TRIDENT = ITEMS.register("iron_trident", () -> new TieredTridentItem(itemBuilder(), Tiers.IRON));
	public static final RegistryObject<Item> COPPER_TRIDENT = ITEMS.register("copper_trident", () -> new TieredTridentItem(itemBuilder(), Tiers.IRON));
	public static final RegistryObject<Item> GOLDEN_TRIDENT = ITEMS.register("golden_trident", () -> new TieredTridentItem(itemBuilder(), Tiers.GOLD));
	public static final RegistryObject<Item> DIAMOND_TRIDENT = ITEMS.register("diamond_trident", () -> new TieredTridentItem(itemBuilder(), Tiers.DIAMOND));
	public static final RegistryObject<Item> NETHERITE_TRIDENT = ITEMS.register("netherite_trident", () -> new TieredTridentItem(itemBuilder(), Tiers.NETHERITE));
	public static final RegistryObject<Item> BONE_TRIDENT = ITEMS.register("bone_trident", () -> new TieredTridentItem(itemBuilder(), Tiers.STONE));
	public static final RegistryObject<Item> PITCHFORK = ITEMS.register("pitchfork", () -> new TieredTridentItem(itemBuilder(), Tiers.STONE));

	private static Item.Properties itemBuilder() {
		return new Item.Properties();
	}

	public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("items", () -> CreativeModeTab.builder()
			.icon(() -> new ItemStack(WOODEN_TRIDENT.get()))
			.withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
			.title(Component.translatable("itemGroup.tieredtridents"))
			.displayItems((displayParameters, output) -> {
				List<ItemStack> stacks = ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
				output.acceptAll(stacks);
			}).build());
}
