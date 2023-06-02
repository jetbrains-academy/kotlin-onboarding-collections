@file:Suppress("unused")

package old.school

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@OptIn(ExperimentalJsExport::class)
@JsExport
data class JsFrame(
    val id: Int,
    val title: String,
)
