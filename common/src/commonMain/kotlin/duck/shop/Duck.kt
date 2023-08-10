package duck.shop

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@OptIn(ExperimentalJsExport::class)
@JsExport
data class JsDuck(
    val name: String,
    val description: String? = null,
    val hasKotlinAttribute: Boolean = false,
)
