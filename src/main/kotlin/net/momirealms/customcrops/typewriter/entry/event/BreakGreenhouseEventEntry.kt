package net.momirealms.customcrops.typewriter.entry.event

import com.typewritermc.core.books.pages.Colors
import com.typewritermc.core.entries.Query
import com.typewritermc.core.entries.Ref
import com.typewritermc.core.extension.annotations.Entry
import com.typewritermc.core.extension.annotations.EntryListener
import com.typewritermc.engine.paper.entry.TriggerableEntry
import com.typewritermc.engine.paper.entry.entries.EventEntry
import com.typewritermc.engine.paper.entry.triggerAllFor
import net.momirealms.customcrops.api.event.GreenhouseGlassBreakEvent
import org.bukkit.entity.Player

@Entry(
    "customcrops_break_greenhouse_event",
    "CustomCrops Break Greenhouse Event",
    Colors.YELLOW,
    "material-symbols:bigtop-updates"
)
class BreakGreenhouseEventEntry(
    override val id: String = "",
    override val name: String = "",
    override val triggers: List<Ref<TriggerableEntry>> = emptyList()
) : EventEntry {
}

@EntryListener(BreakGreenhouseEventEntry::class)
fun onGreenhouseGlassBreakEvent(event: GreenhouseGlassBreakEvent, query: Query<BreakGreenhouseEventEntry>) {
    if (event.isCancelled) return
    val breaker = event.entityBreaker() as? Player ?: return
    val entries = query.find()
    entries triggerAllFor breaker
}