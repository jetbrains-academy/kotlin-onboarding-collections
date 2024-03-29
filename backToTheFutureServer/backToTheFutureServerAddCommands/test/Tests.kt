import org.jetbrains.academy.test.system.core.models.method.TestMethodInvokeData
import org.jetbrains.kotlin.course.tamagotchi.models.Command
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.lang.reflect.Field

class Test {
    @Test
    fun gameServiceTestClassTest() {
        val clazz = gameServiceTestClass.checkBaseDefinition()
        gameServiceTestClass.checkFieldsDefinition(clazz, false)
    }

    private fun getField(invokeData: TestMethodInvokeData, name: String): Field {
        val field = invokeData.clazz.declaredFields.find { it.name == name }
        assertTrue(field != null) { "Can not find field $name" }
        field!!.isAccessible = true
        return field
    }

    private fun getCommandsFieldValue(field: Field, instance: Any): ArrayDeque<*> {
        (field.get(instance) as? ArrayDeque<*>)?.let {
            return it
        }
        assertTrue(false) { "The command field must be ArrayDeque (the Kotlin implementation)!" }
        return ArrayDeque<Command>()
    }

    @Test
    fun commandFieldTest() {
        val invokeData = TestMethodInvokeData(gameServiceTestClass, addCommandMethod)
        val commandsField = getField(invokeData, commandsVariable.name)
        val initialValue = getCommandsFieldValue(commandsField, invokeData.instance)
        assertTrue(initialValue.isEmpty()) { "The initial value of the command field should be an empty array, the current implementation creates an array with ${initialValue.size} elements" }
    }
}