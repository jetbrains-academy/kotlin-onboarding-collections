import org.jetbrains.kotlin.course.culinary.*
import org.jetbrains.kotlin.course.culinary.functions.CookingService
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TaskTest {
    @Test
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

    @Test
    fun testTask2() {
        CookingService().performCooking()
        assertTrue(PotImpl.tastesPerfect, "The soup in the pot does not taste perfect.")
        assertTrue(PotImpl.simmering)
    }

    @Test
    fun testTask3() {
        CookingService().performCooking()
        assertTrue(SaladBowlImpl.contents.size in 1..5) {
            "The salad bowl should contain between 1 and 5 cut vegetables, now it is ${SaladBowlImpl.contents.size}."
        }
        assertTrue(SaladBowlImpl.mixing, "The salad bowl should be mixing.")
    }

    @Test
    fun testTask4() {
        CookingService().performCooking()
        val hasCitrus = BlenderImpl.contents.any { it.type == FruitType.Citrus }
        val hasBerry = BlenderImpl.contents.any { it.type == FruitType.Berry }
        println(BlenderImpl.contents)
        assertTrue(
            hasCitrus && hasBerry,
            "The blender should contain Citrus and Berry."
        )
        assertTrue(BlenderImpl.blending, "The blender should be blending.")
    }
}
