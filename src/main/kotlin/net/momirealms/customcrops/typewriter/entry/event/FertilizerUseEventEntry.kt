package net.momirealms.customcrops.typewriter.entry.event

import com.typewritermc.core.books.pages.Colors
import com.typewritermc.core.entries.Query
import com.typewritermc.core.entries.Ref
import com.typewritermc.core.extension.annotations.Entry
import com.typewritermc.core.extension.annotations.EntryListener
import com.typewritermc.core.extension.annotations.Help
import com.typewritermc.core.interaction.context
import com.typewritermc.engine.paper.entry.TriggerableEntry
import com.typewritermc.engine.paper.entry.entries.EventEntry
import com.typewritermc.engine.paper.entry.triggerAllFor
import net.momirealms.customcrops.api.event.FertilizerUseEvent

@Entry(
    "customcrops_use_fertilizer_event",
    "CustomCrops Use Fertilizer Event",
    Colors.GREEN,
    "material-symbols:bigtop-updates"
)
class FertilizerUseEventEntry(
    override val id: String = "",
    override val name: String = "",
    override val triggers: List<Ref<TriggerableEntry>> = emptyList(),
    @Help("The id of the fertilizer set in CustomCrops")
    val fertilizerIds: List<String> = emptyList(),
) : EventEntry {
}

@EntryListener(FertilizerUseEventEntry::class)
fun onFertilizerUseEvent(event: FertilizerUseEvent, query: Query<FertilizerUseEventEntry>) {
    if (event.isCancelled) return
    val id = event.fertilizer().id()
    query.findWhere { entry ->
        entry.fertilizerIds.isEmpty() || entry.fertilizerIds.contains(id)
    }.triggerAllFor(event.player, context())
}