@file:Suppress("unused")

package old.school

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@OptIn(ExperimentalJsExport::class)
@JsExport
data class JsPhoto(
    val name: String,
    val description: String? = null,
)
