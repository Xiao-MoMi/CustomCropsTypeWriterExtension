package net.momirealms.customcrops.typewriter.entry.event

import com.typewritermc.core.books.pages.Colors
import com.typewritermc.core.entries.Query
import com.typewritermc.core.entries.Ref
import com.typewritermc.core.extension.annotations.Entry
import com.typewritermc.core.extension.annotations.EntryListener
import com.typewritermc.engine.paper.entry.TriggerableEntry
import com.typewritermc.engine.paper.entry.entries.EventEntry
import com.typewritermc.engine.paper.entry.triggerAllFor
import net.momirealms.customcrops.api.event.ScarecrowBreakEvent
import org.bukkit.entity.Player

@Entry(
    "customcrops_break_scarecrow_event",
    "CustomCrops Break Scarecrow Event",
    Colors.CYAN,
    "material-symbols:bigtop-updates"
)
class BreakScarecrowEventEntry(
    override val id: String = "",
    override val name: String = "",
    override val triggers: List<Ref<TriggerableEntry>> = emptyList()
) : EventEntry {
}

@EntryListener(BreakScarecrowEventEntry::class)
fun onScarecrowBreakEvent(event: ScarecrowBreakEvent, query: Query<BreakScarecrowEventEntry>) {
    if (event.isCancelled) return
    val breaker = event.entityBreaker() as? Player ?: return
    val entries = query.find()
    entries triggerAllFor breaker
}