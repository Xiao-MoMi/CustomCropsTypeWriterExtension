package net.momirealms.customcrops.typewriter.entry.event

import com.typewritermc.core.books.pages.Colors
import com.typewritermc.core.entries.Query
import com.typewritermc.core.entries.Ref
import com.typewritermc.core.extension.annotations.Entry
import com.typewritermc.core.extension.annotations.EntryListener
import com.typewritermc.engine.paper.entry.TriggerableEntry
import com.typewritermc.engine.paper.entry.entries.EventEntry
import com.typewritermc.engine.paper.entry.triggerAllFor
import net.momirealms.customcrops.api.event.ScarecrowPlaceEvent

@Entry(
    "customcrops_place_scarecrow_event",
    "CustomCrops Place Scarecrow Event",
    Colors.CYAN,
    "material-symbols:bigtop-updates"
)
class PlaceScarecrowEventEntry(
    override val id: String = "",
    override val name: String = "",
    override val triggers: List<Ref<TriggerableEntry>> = emptyList()
) : EventEntry {
}

@EntryListener(PlaceScarecrowEventEntry::class)
fun onScarecrowPlaceEvent(event: ScarecrowPlaceEvent, query: Query<PlaceScarecrowEventEntry>) {
    if (event.isCancelled) return
    val entries = query.find()
    entries triggerAllFor event.player
}