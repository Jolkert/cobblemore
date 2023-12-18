package dev.jolkert.cobblemore.util

import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.util.asTranslated
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.text.TextColor
import net.minecraft.util.Formatting

fun lang(key: String, vararg args: Any) = "cobblemore.$key".asTranslated(*args)

// we're writing kotlin why is it not like this already??
val Pokemon.displayName get() = this.getDisplayName()
