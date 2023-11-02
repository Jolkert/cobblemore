package io.github.jolkert.cobblemore

import com.cobblemon.mod.common.Cobblemon
import com.cobblemon.mod.common.pokemon.helditem.CobblemonHeldItemManager
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object Cobblemore : ModInitializer
{
	const val MOD_ID = "cobblemore"

	override fun onInitialize()
	{
		CobblemoreItems.register()
	}
}
