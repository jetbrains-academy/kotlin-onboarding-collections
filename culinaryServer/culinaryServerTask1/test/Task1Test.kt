import org.jetbrains.kotlin.course.culinary.*
import org.jetbrains.kotlin.course.culinary.functions.CookingService
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Task1Test {
    fun testTask1() {
        CookingService().performCooking()
        assertTrue(
            PotImpl.contents.size == 3,
            "There should not be more than three tomatoes in the pot."
        )
        val notTomato = PotImpl.contents.firstOrNull { !(it is CutVegetable && it.type == VegetableType.Tomato) }
        assertNull(notTomato) { "There is something other than tomato in pot, $notTomato" }
        assertTrue(PotImpl.simmering)
    }
}
