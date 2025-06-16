package net.momirealms.customcrops.typewriter.entry.event

import com.typewritermc.core.books.pages.Colors
import com.typewritermc.core.entries.Query
import com.typewritermc.core.entries.Ref
import com.typewritermc.core.extension.annotations.Entry
import com.typewritermc.core.extension.annotations.EntryListener
import com.typewritermc.core.interaction.context
import com.typewritermc.engine.paper.entry.TriggerableEntry
import com.typewritermc.engine.paper.entry.entries.EventEntry
import com.typewritermc.engine.paper.entry.triggerAllFor
import net.momirealms.customcrops.api.event.GreenhouseGlassPlaceEvent

@Entry(
    "customcrops_place_greenhouse_event",
    "CustomCrops Place Greenhouse Event",
    Colors.YELLOW,
    "material-symbols:bigtop-updates"
)
class PlaceGreenhouseEventEntry(
    override val id: String = "",
    override val name: String = "",
    override val triggers: List<Ref<TriggerableEntry>> = emptyList()
) : EventEntry {
}

@EntryListener(PlaceGreenhouseEventEntry::class)
fun onGreenhouseGlassPlaceEvent(event: GreenhouseGlassPlaceEvent, query: Query<PlaceGreenhouseEventEntry>) {
    if (event.isCancelled) return
    val entries = query.find()
    entries.triggerAllFor(event.player, context())
}