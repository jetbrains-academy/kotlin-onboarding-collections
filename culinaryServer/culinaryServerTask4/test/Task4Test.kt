import org.jetbrains.kotlin.course.culinary.BlenderImpl
import org.jetbrains.kotlin.course.culinary.FruitType
import org.jetbrains.kotlin.course.culinary.functions.CookingService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task4Test {
    @Test
    fun testTask4() {
        CookingService().performCooking()
        val hasCitrus = BlenderImpl.contents.any { it.type == FruitType.Citrus }
        val hasBerry = BlenderImpl.contents.any { it.type == FruitType.Berry }
        println(BlenderImpl.contents)
        Assertions.assertTrue(
            hasCitrus && hasBerry,
            "The blender should contain Citrus and Berry."
        )
        Assertions.assertTrue(BlenderImpl.blending, "The blender should be blending.")
    }
}
