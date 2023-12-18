package dev.jolkert.cobblemore

import dev.jolkert.cobblemore.item.CobblemoreItems
import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier

object Cobblemore : ModInitializer
{
	const val MOD_ID = "cobblemore"
	fun resource(path: String) = Identifier(MOD_ID, path)

	override fun onInitialize()
	{
		CobblemoreItems.register()
	}
}
