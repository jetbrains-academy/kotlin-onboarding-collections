package org.jetbrains.kotlin.course.culinary.models.action

import org.jetbrains.kotlin.course.culinary.models.ItemType

data class Action(val type: ActionType, val parameter: ItemType? = null)
