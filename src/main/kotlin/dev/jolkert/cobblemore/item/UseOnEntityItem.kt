package dev.jolkert.cobblemore.item

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand

class UseOnEntityItem(settings: Settings, val onUseOnEntity: UseOverride) : Item(settings)
{

	override fun useOnEntity(stack: ItemStack, user: PlayerEntity, entity: LivingEntity, hand: Hand) =
		onUseOnEntity(stack, user, entity, hand)
}

typealias UseOverride = (ItemStack, PlayerEntity, LivingEntity, Hand) -> ActionResult