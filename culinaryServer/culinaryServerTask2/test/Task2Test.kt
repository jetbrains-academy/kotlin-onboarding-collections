import org.jetbrains.kotlin.course.culinary.PotImpl
import org.jetbrains.kotlin.course.culinary.functions.CookingService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task2Test {
    @Test
    fun testTask2() {
        CookingService().performCooking()
        Assertions.assertTrue(PotImpl.tastesPerfect, "The soup in the pot does not taste perfect.")
        Assertions.assertTrue(PotImpl.simmering)
    }
}