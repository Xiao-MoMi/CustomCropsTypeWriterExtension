package net.momirealms.customcrops.typewriter.entry.event

import com.typewritermc.core.books.pages.Colors
import com.typewritermc.core.entries.Query
import com.typewritermc.core.entries.Ref
import com.typewritermc.core.extension.annotations.Entry
import com.typewritermc.core.extension.annotations.EntryListener
import com.typewritermc.core.extension.annotations.Help
import com.typewritermc.engine.paper.entry.TriggerableEntry
import com.typewritermc.engine.paper.entry.entries.EventEntry
import com.typewritermc.engine.paper.entry.triggerAllFor
import net.momirealms.customcrops.api.event.PotPlaceEvent

@Entry(
    "customcrops_place_pot_event",
    "CustomCrops Place Pot Event",
    Colors.BLUE_VIOLET,
    "material-symbols:bigtop-updates"
)
class PlacePotEventEntry(
    override val id: String = "",
    override val name: String = "",
    override val triggers: List<Ref<TriggerableEntry>> = emptyList(),
    @Help("The id of the pot set in CustomCrops")
    val potIds: List<String> = emptyList(),
) : EventEntry {
}

@EntryListener(PlacePotEventEntry::class)
fun onPotPlaceEvent(event: PotPlaceEvent, query: Query<PlacePotEventEntry>) {
    if (event.isCancelled) return
    val potId = event.potConfig().id()
    query.findWhere { entry ->
        entry.potIds.isEmpty() || entry.potIds.contains(potId)
    }.triggerAllFor(event.player)
}