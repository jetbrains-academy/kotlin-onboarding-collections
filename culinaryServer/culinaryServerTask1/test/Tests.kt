import org.jetbrains.kotlin.course.culinary.game.CookingService
import org.jetbrains.kotlin.course.culinary.game.clearKitchen
import org.jetbrains.kotlin.course.culinary.game.pot
import org.jetbrains.kotlin.course.culinary.implementation.cooking.BlenderImpl
import org.jetbrains.kotlin.course.culinary.implementation.cooking.PotImpl
import org.jetbrains.kotlin.course.culinary.implementation.cooking.SaladBowlImpl
import org.jetbrains.kotlin.course.culinary.implementation.storage.FridgeImpl
import org.jetbrains.kotlin.course.culinary.models.food.CutVegetable
import org.jetbrains.kotlin.course.culinary.models.food.FruitType
import org.jetbrains.kotlin.course.culinary.models.food.Vegetable
import org.jetbrains.kotlin.course.culinary.models.food.VegetableType
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Test {

    companion object {
        private const val TOMATO_NUMBER = 10
    }

    private fun FridgeImpl.refillWithTomatoes() {
        vegetables.addAll(List(TOMATO_NUMBER) { Vegetable(VegetableType.Tomato, true) })
    }

    @Test
    fun testTask1() {
        clearKitchen()
        FridgeImpl.refillWithTomatoes()
        CookingService().cookTomatoSoup()
        assertTrue(
            PotImpl.filling.size == 3,
            "There should not be more than three tomatoes in the pot."
        )
        val notTomato = PotImpl.filling.firstOrNull { !(it is CutVegetable && it.type == VegetableType.Tomato) }
        assertNull(notTomato) { "There is something other than tomato in pot, $notTomato" }
        assertTrue(PotImpl.simmering)
    }

    @Test
    fun testTask2() {
        val tastes = List(100) {
            clearKitchen()
            FridgeImpl.refillWithTomatoes()
            CookingService().cookWithSpices()
            assertTrue(PotImpl.simmering)
            pot.doesTastePerfect()
        }
        assertTrue(tastes.toSet().size == 2, "The spices should be added randomly")

    }

    @Test
    fun testTask3() {
        clearKitchen()
        FridgeImpl.refill()
        CookingService().cookSalad()
        assertTrue(SaladBowlImpl.filling.size in 1..5) {
            "The salad bowl should contain between 1 and 5 cut vegetables, now it is ${SaladBowlImpl.filling.size}."
        }
        assertTrue(SaladBowlImpl.mixing, "The salad bowl should be mixing.")
    }

    @Test
    fun testTask4() {
        clearKitchen()
        FridgeImpl.refill()
        CookingService().cookSmoothie()
        val hasCitrus = BlenderImpl.filling.any { it.type == FruitType.Citrus }
        val hasBerry = BlenderImpl.filling.any { it.type == FruitType.Berry }
        println(BlenderImpl.filling)
        assertTrue(
            hasCitrus && hasBerry,
            "The blender should contain Citrus and Berry."
        )
        assertTrue(BlenderImpl.blending, "The blender should be blending.")
    }
}
