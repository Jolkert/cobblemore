package dev.jolkert.cobblemore.item

import com.cobblemon.mod.common.item.group.CobblemonItemGroups
import com.cobblemon.mod.common.pokemon.helditem.CobblemonHeldItemManager
import dev.jolkert.cobblemore.Cobblemore
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
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

	@JvmField val EJECT_PACK = heldItem("eject_pack")

	@JvmField val TERRAIN_EXTENDER = heldItem("terrain_extender")

	@JvmField val MISTY_SEED = heldItem("misty_seed")
	@JvmField val ELECTRIC_SEED = heldItem("electric_seed")
	@JvmField val GRASSY_SEED = heldItem("grassy_seed")
	@JvmField val PSYCHIC_SEED = heldItem("psychic_seed")

	@JvmField val METRONOME = heldItem("metronome")

	@JvmField val WIDE_LENS = heldItem("wide_lens")
	@JvmField val ZOOM_LENS = heldItem("zoom_lens")
	@JvmField val SCOPE_LENS = heldItem("scope_lens")

	@JvmField val THROAT_SPRAY = heldItem("throat_spray")
	@JvmField val ROOM_SERVICE = heldItem("room_service")

	@JvmField val PROTECTIVE_PADS = heldItem("protective_pads")
	@JvmField val UTILITY_UMBRELLA = heldItem("utility_umbrella")
	@JvmField val SHED_SHELL = heldItem("shed_shell")
}