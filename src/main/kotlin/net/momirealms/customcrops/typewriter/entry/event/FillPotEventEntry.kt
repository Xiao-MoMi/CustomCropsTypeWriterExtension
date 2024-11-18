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
import net.momirealms.customcrops.api.event.PotFillEvent

@Entry(
    "customcrops_fill_pot_event",
    "CustomCrops Fill Pot Event",
    Colors.BLUE_VIOLET,
    "material-symbols:bigtop-updates"
)
class FillPotEventEntry(
    override val id: String = "",
    override val name: String = "",
    override val triggers: List<Ref<TriggerableEntry>> = emptyList(),
    @Help("The id of the pot set in CustomCrops")
    val potIds: List<String> = emptyList(),
) : EventEntry {
}

@EntryListener(FillPotEventEntry::class)
fun onPotFillEvent(event: PotFillEvent, query: Query<FillPotEventEntry>) {
    if (event.isCancelled) return
    val potId = event.potConfig().id()
    query.findWhere { entry ->
        entry.potIds.isEmpty() || entry.potIds.contains(potId)
    }.triggerAllFor(event.player)
}