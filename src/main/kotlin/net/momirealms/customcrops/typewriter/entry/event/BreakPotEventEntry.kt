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
import net.momirealms.customcrops.api.event.PotBreakEvent
import org.bukkit.entity.Player

@Entry(
    "customcrops_break_pot_event",
    "CustomCrops Break Pot Event",
    Colors.BLUE_VIOLET,
    "material-symbols:bigtop-updates"
)
class BreakPotEventEntry(
    override val id: String = "",
    override val name: String = "",
    override val triggers: List<Ref<TriggerableEntry>> = emptyList(),
    @Help("The id of the pot set in CustomCrops")
    val potIds: List<String> = emptyList(),
) : EventEntry {
}

@EntryListener(BreakPotEventEntry::class)
fun onPotBreakEvent(event: PotBreakEvent, query: Query<BreakPotEventEntry>) {
    if (event.isCancelled) return
    val breaker = event.entityBreaker() as? Player ?: return
    val potId = event.potConfig().id()
    query.findWhere { entry ->
        entry.potIds.isEmpty() || entry.potIds.contains(potId)
    }.triggerAllFor(breaker, context())
}