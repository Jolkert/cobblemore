package io.github.jolkert.cobblemore.item

import com.cobblemon.mod.common.api.Priority
import com.cobblemon.mod.common.api.abilities.Ability
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.asTranslated
import io.github.jolkert.cobblemore.util.displayName
import io.github.jolkert.cobblemore.util.lang
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand

object ItemFunctions
{
	@JvmStatic
	fun useAbilityCapsule(stack: ItemStack, user: PlayerEntity, target: LivingEntity, hand: Hand): ActionResult
	{
		if (user !is ServerPlayerEntity)
			return ActionResult.FAIL

		if (target !is PokemonEntity || target.ownerUuid != user.uuid)
			return ActionResult.PASS

		val pokemon = target.pokemon
		if (pokemon.ability.forced)
		{
			user.sendMessage(lang("item.ability_capsule.fail.forced", pokemon.displayName))
			return ActionResult.SUCCESS
		}
		if (pokemon.ability.isHidden())
		{
			user.sendMessage(lang("item.ability_capsule.fail.has_hidden", pokemon.displayName))
			return ActionResult.SUCCESS
		}

		val otherAbility =
			pokemon.form.abilities.mapping[Priority.LOWEST]?.firstOrNull { it.template.name != pokemon.ability.name }?.template?.create()
		if (otherAbility == null)
		{
			user.sendMessage(lang("item.ability_capsule.fail.one_ability", pokemon.displayName))
			return ActionResult.SUCCESS
		}

		pokemon.ability = otherAbility
		pokemon.checkAbility()
		stack.decrement(1)
		user.sendMessage(lang("item.ability_capsule.success", pokemon.displayName, otherAbility.displayName.asTranslated()))
		return ActionResult.SUCCESS
	}

	// LOW is hidden. LOWEST are normal
	private fun Ability.isHidden() = this.priority == Priority.LOW
}