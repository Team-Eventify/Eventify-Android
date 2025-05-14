package core.common.annotations

import core.common.navigation.FeatureEntry
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class FeatureEntryKey(val value: KClass<out FeatureEntry>)