package io.github.jolkert.cobblemore.util

import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.util.asTranslated

fun lang(key: String, vararg args: Any) = "cobblemore.$key".asTranslated(*args)

// we're writing kotlin why is it not like this already??
val Pokemon.displayName get() = this.getDisplayName()