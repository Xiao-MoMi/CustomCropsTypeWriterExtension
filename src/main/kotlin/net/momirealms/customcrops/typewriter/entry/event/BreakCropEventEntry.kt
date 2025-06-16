package net.momirealms.customcrops.typewriter.entry.event

import com.typewritermc.core.interaction.context
import com.typewritermc.core.books.pages.Colors
import com.typewritermc.core.entries.Query
import com.typewritermc.core.entries.Ref
import com.typewritermc.core.extension.annotations.Entry
import com.typewritermc.core.extension.annotations.EntryListener
import com.typewritermc.core.extension.annotations.Help
import com.typewritermc.engine.paper.entry.TriggerableEntry
import com.typewritermc.engine.paper.entry.entries.EventEntry
import com.typewritermc.engine.paper.entry.triggerAllFor
import net.momirealms.customcrops.api.event.CropBreakEvent
import org.bukkit.entity.Player

@Entry(
    "customcrops_break_crop_event",
    "CustomCrops Break Crop Event",
    Colors.ORANGE_RED,
    "material-symbols:bigtop-updates"
)
class BreakCropEventEntry(
    override val id: String = "",
    override val name: String = "",
    override val triggers: List<Ref<TriggerableEntry>> = emptyList(),
    @Help("The id of the crop set in CustomCrops")
    val cropIds: List<String> = emptyList(),
) : EventEntry {
}

@EntryListener(BreakCropEventEntry::class)
fun onCropBreakEvent(event: CropBreakEvent, query: Query<BreakCropEventEntry>) {
    if (event.isCancelled) return
    val breaker = event.entityBreaker() as? Player ?: return
    val cropId = event.cropConfig().id()
    query.findWhere { entry ->
        entry.cropIds.isEmpty() || entry.cropIds.contains(cropId)
    }.triggerAllFor(breaker, context())
}