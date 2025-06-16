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
import net.momirealms.customcrops.api.event.CropBreakEvent
import org.bukkit.entity.Player

@Entry(
    "customcrops_break_crop_stage_event",
    "CustomCrops Break Crop Stage Event",
    Colors.ORANGE_RED,
    "material-symbols:bigtop-updates"
)
class BreakCropStageEventEntry(
    override val id: String = "",
    override val name: String = "",
    override val triggers: List<Ref<TriggerableEntry>> = emptyList(),
    @Help("The item id of the stage model. (Includes namespace if you are using ItemsAdder/CraftEngine)")
    val stageIds: List<String> = emptyList(),
) : EventEntry {
}

@EntryListener(BreakCropStageEventEntry::class)
fun onCropBreakEvent(event: CropBreakEvent, query: Query<BreakCropStageEventEntry>) {
    if (event.isCancelled) return
    val breaker = event.entityBreaker() as? Player ?: return
    val stageId = event.cropStageItemID()
    query.findWhere { entry ->
        entry.stageIds.isEmpty() || entry.stageIds.contains(stageId)
    }.triggerAllFor(breaker, context())
}