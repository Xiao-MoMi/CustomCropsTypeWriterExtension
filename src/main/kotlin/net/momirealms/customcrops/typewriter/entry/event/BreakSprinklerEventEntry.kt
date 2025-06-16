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
import net.momirealms.customcrops.api.event.SprinklerBreakEvent
import org.bukkit.entity.Player

@Entry(
    "customcrops_break_sprinkler_event",
    "CustomCrops Break Sprinkler Event",
    Colors.PURPLE,
    "material-symbols:bigtop-updates"
)
class BreakSprinklerEventEntry(
    override val id: String = "",
    override val name: String = "",
    override val triggers: List<Ref<TriggerableEntry>> = emptyList(),
    @Help("The id of the sprinkler set in CustomCrops")
    val sprinklerIds: List<String> = emptyList(),
) : EventEntry {
}

@EntryListener(BreakSprinklerEventEntry::class)
fun onSprinklerBreakEvent(event: SprinklerBreakEvent, query: Query<BreakSprinklerEventEntry>) {
    if (event.isCancelled) return
    val breaker = event.entityBreaker() as? Player ?: return
    val sprinklerId = event.sprinklerConfig().id()
    query.findWhere { entry ->
        entry.sprinklerIds.isEmpty() || entry.sprinklerIds.contains(sprinklerId)
    }.triggerAllFor(breaker, context())
}