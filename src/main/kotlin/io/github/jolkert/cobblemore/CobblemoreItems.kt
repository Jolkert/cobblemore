package io.github.jolkert.cobblemore

import com.cobblemon.mod.common.item.group.CobblemonItemGroups
import com.cobblemon.mod.common.pokemon.helditem.CobblemonHeldItemManager
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier


@Suppress("unused")
object CobblemoreItems
{
	data class RegistryItem(val item: Item, val id: Identifier)

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
	fun create(id: String) = Item(FabricItemSettings()).also {
		itemList.add(RegistryItem(it, Identifier(Cobblemore.MOD_ID, id)))
	}

	fun register()
	{
		for ((item, id) in itemList)
		{
			Registry.register(Registries.ITEM, id, item)
			CobblemonHeldItemManager.registerRemap(item, id.path.substringAfterLast('/').replace("_", ""))
			ItemGroupEvents.modifyEntriesEvent(CobblemonItemGroups.HELD_ITEMS_KEY).register { it.add(item) }
		}
	}

	@JvmField val EVIOLITE = create("eviolite")
	@JvmField val LIFE_ORB = create("life_orb")
	@JvmField val EXPERT_BELT = create("expert_belt")
	@JvmField val AIR_BALLOON = create("air_balloon")
	@JvmField val FOCUS_SASH = create("focus_sash")

	@JvmField val WEAKNESS_POLICY = create("weakness_policy")
	@JvmField val BLUNDER_POLICY = create("blunder_policy")

	@JvmField val RED_CARD = create("red_card")
	@JvmField val EJECT_BUTTON = create("eject_button")
	@JvmField val EJECT_PACK = create("eject_pack")

	@JvmField val DAMP_ROCK = create("damp_rock")
	@JvmField val HEAT_ROCK = create("heat_rock")
	@JvmField val SMOOTH_ROCK = create("smooth_rock")
	@JvmField val ICY_ROCK = create("icy_rock")

	@JvmField val TERRAIN_EXTENDER = create("terrain_extender")

	@JvmField val MISTY_SEED = create("misty_seed")
	@JvmField val ELECTRIC_SEED = create("electric_seed")
	@JvmField val GRASSY_SEED = create("grassy_seed")
	@JvmField val PSYCHIC_SEED = create("psychic_seed")

	@JvmField val FLAME_ORB = create("flame_orb")
	@JvmField val TOXIC_ORB = create("toxic_orb")

	@JvmField val METRONOME = create("metronome")
	@JvmField val SHELL_BELL = create("shell_bell")
	@JvmField val LOADED_DICE = create("loaded_dice")

	@JvmField val WIDE_LENS = create("wide_lens")
	@JvmField val ZOOM_LENS = create("zoom_lens")
	@JvmField val SCOPE_LENS = create("scope_lens")

	@JvmField val THROAT_SPRAY = create("throat_spray")
	@JvmField val ROOM_SERVICE = create("room_service")

	@JvmField val PROTECTIVE_PADS = create("protective_pads")
	@JvmField val COVERT_CLOAK = create("covert_cloak")
	@JvmField val UTILITY_UMBRELLA = create("utility_umbrella")
	@JvmField val SHED_SHELL = create("shed_shell")

}