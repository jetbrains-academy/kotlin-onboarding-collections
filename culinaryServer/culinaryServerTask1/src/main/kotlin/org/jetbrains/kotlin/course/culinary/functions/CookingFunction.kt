package org.jetbrains.kotlin.course.culinary.functions

import culinary.JsAction
import org.jetbrains.kotlin.course.culinary.actions
import org.springframework.http.StreamingHttpOutputMessage
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/functions/")
class CookingFunction(val service: CookingService) {
    @CrossOrigin
    @PostMapping("/cooking")
    fun recipe(@RequestBody body: StreamingHttpOutputMessage.Body): List<JsAction> {
        service.performCooking()
        return actions
    }
}
