package io.github.jolkert.cobblemore.item

import com.cobblemon.mod.common.item.CobblemonItem
import com.cobblemon.mod.common.item.group.CobblemonItemGroups
import com.cobblemon.mod.common.pokemon.helditem.CobblemonHeldItemManager
import io.github.jolkert.cobblemore.Cobblemore
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.util.ActionResult
import net.minecraft.util.Identifier


@Suppress("unused")
object CobblemoreItems
{
	data class RegistryItem(val item: Item, val id: Identifier, val groupKey: RegistryKey<ItemGroup>? = null, val isHeldItem: Boolean = false)

	/*
		I spent. h o u r s
		trying to figure out
		why the hell `itemList` wasn't being initialized by the time I got to the `createHeldItem` function
		its literally just because it was physically below it in line number in the source code
		why does kotlin work like that?
		why does it not just initialize the variable when its being accessed?
		i do not understand
		for future reference: order in the source code matters when dealing with static contexts in kotlin
		you have been warned

		- morgan 2023-11-01
	 */
	@JvmStatic
	private var itemList: MutableList<RegistryItem> = mutableListOf()

	@JvmStatic
	fun create(id: String, groupKey: RegistryKey<ItemGroup>? = null, isHeldItem: Boolean = false) =
		preRegister(id, Item(FabricItemSettings()), groupKey, isHeldItem)

	@JvmStatic
	fun heldItem(id: String) = create(id, CobblemonItemGroups.HELD_ITEMS_KEY, true)

	@JvmStatic
	fun useOnEntityItem(id: String, groupKey: RegistryKey<ItemGroup>?, lambda: UseOverride) =
		preRegister(id, UseOnEntityItem(FabricItemSettings(), lambda), groupKey)

	@JvmStatic
	fun preRegister(id: String, item: Item, groupKey: RegistryKey<ItemGroup>? = null, isHeldItem: Boolean = false) = item.also {
		itemList.add(RegistryItem(it, Cobblemore.resource(id), groupKey, isHeldItem))
	}

	fun register()
	{
		for ((item, id, group, isHeld) in itemList)
		{
			Registry.register(Registries.ITEM, id, item)
			if (group != null)
				ItemGroupEvents.modifyEntriesEvent(group).register { it.add(item) }
			if (isHeld)
				CobblemonHeldItemManager.registerRemap(item, id.path.substringAfterLast('/').replace("_", ""))
		}
	}

	// Pokemon Modifying Items
	@JvmField val ABILITY_CAPSULE =
		useOnEntityItem("ability_capsule",CobblemonItemGroups.CONSUMABLES_KEY, ItemFunctions::useAbilityCapsule)
	@JvmField val ABILITY_PATCH =
		useOnEntityItem("ability_patch", CobblemonItemGroups.CONSUMABLES_KEY, ItemFunctions::useAbilityPatch)

	// Held items
	@JvmField val EVIOLITE = heldItem("eviolite")
	@JvmField val LIFE_ORB = heldItem("life_orb")
	@JvmField val EXPERT_BELT = heldItem("expert_belt")
	@JvmField val AIR_BALLOON = heldItem("air_balloon")
	@JvmField val FOCUS_SASH = heldItem("focus_sash")

	@JvmField val WEAKNESS_POLICY = heldItem("weakness_policy")
	@JvmField val BLUNDER_POLICY = heldItem("blunder_policy")

	@JvmField val RED_CARD = heldItem("red_card")
	@JvmField val EJECT_BUTTON = heldItem("eject_button")
	@JvmField val EJECT_PACK = heldItem("eject_pack")

	@JvmField val DAMP_ROCK = heldItem("damp_rock")
	@JvmField val HEAT_ROCK = heldItem("heat_rock")
	@JvmField val SMOOTH_ROCK = heldItem("smooth_rock")
	@JvmField val ICY_ROCK = heldItem("icy_rock")

	@JvmField val TERRAIN_EXTENDER = heldItem("terrain_extender")

	@JvmField val MISTY_SEED = heldItem("misty_seed")
	@JvmField val ELECTRIC_SEED = heldItem("electric_seed")
	@JvmField val GRASSY_SEED = heldItem("grassy_seed")
	@JvmField val PSYCHIC_SEED = heldItem("psychic_seed")

	@JvmField val FLAME_ORB = heldItem("flame_orb")
	@JvmField val TOXIC_ORB = heldItem("toxic_orb")

	@JvmField val METRONOME = heldItem("metronome")
	@JvmField val SHELL_BELL = heldItem("shell_bell")
	@JvmField val LOADED_DICE = heldItem("loaded_dice")

	@JvmField val WIDE_LENS = heldItem("wide_lens")
	@JvmField val ZOOM_LENS = heldItem("zoom_lens")
	@JvmField val SCOPE_LENS = heldItem("scope_lens")

	@JvmField val THROAT_SPRAY = heldItem("throat_spray")
	@JvmField val ROOM_SERVICE = heldItem("room_service")

	@JvmField val PROTECTIVE_PADS = heldItem("protective_pads")
	@JvmField val COVERT_CLOAK = heldItem("covert_cloak")
	@JvmField val UTILITY_UMBRELLA = heldItem("utility_umbrella")
	@JvmField val SHED_SHELL = heldItem("shed_shell")

}