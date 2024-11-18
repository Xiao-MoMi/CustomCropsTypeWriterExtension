package net.momirealms.customcrops.typewriter.entry.event

import com.typewritermc.core.books.pages.Colors
import com.typewritermc.core.entries.Query
import com.typewritermc.core.entries.Ref
import com.typewritermc.core.extension.annotations.Entry
import com.typewritermc.core.extension.annotations.EntryListener
import com.typewritermc.engine.paper.entry.TriggerableEntry
import com.typewritermc.engine.paper.entry.entries.EventEntry
import com.typewritermc.engine.paper.entry.triggerAllFor
import net.momirealms.customcrops.api.event.WateringCanWaterSprinklerEvent

@Entry(
    "customcrops_watering_can_water_sprinkler_event",
    "CustomCrops WateringCan Water Sprinkler Event",
    Colors.PINK,
    "material-symbols:bigtop-updates"
)
class WateringCanWaterSprinklerEventEntry(
    override val id: String = "",
    override val name: String = "",
    override val triggers: List<Ref<TriggerableEntry>> = emptyList()
) : EventEntry {
}

@EntryListener(WateringCanWaterSprinklerEventEntry::class)
fun onWateringCanWaterSprinklerEvent(event: WateringCanWaterSprinklerEvent, query: Query<WateringCanWaterSprinklerEventEntry>) {
    if (event.isCancelled) return
    val entries = query.find()
    entries triggerAllFor event.player
}