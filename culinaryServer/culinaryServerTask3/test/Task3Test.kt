import org.jetbrains.kotlin.course.culinary.SaladBowlImpl
import org.jetbrains.kotlin.course.culinary.functions.CookingService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task3Test {
    @Test
    fun testTask3() {
        CookingService().performCooking()
        Assertions.assertTrue(SaladBowlImpl.contents.size in 1..5) {
            "The salad bowl should contain between 1 and 5 cut vegetables, now it is ${SaladBowlImpl.contents.size}."
        }
        Assertions.assertTrue(SaladBowlImpl.mixing, "The salad bowl should be mixing.")
    }
}