import org.jetbrains.academy.test.system.core.models.method.TestMethodInvokeData
import org.jetbrains.kotlin.course.tamagotchi.models.Command
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.lang.reflect.Field
import java.util.*

class Test {
    companion object {
        private val commandToAdd = Command.Eat
    }
    private fun invokeAddCommand(invokeData: TestMethodInvokeData, command: Command = commandToAdd) = gameServiceTestClass.invokeMethodWithArgs(
        command,
        clazz = invokeData.clazz,
        instance = invokeData.instance,
        javaMethod = invokeData.method,
    ) as Boolean

    @Test
    fun addCommandTestMethodTest() {
        val invokeData = TestMethodInvokeData(gameServiceTestClass, addCommandMethod)
        val commandsField = getField(invokeData, commandsVariable.name)
        var currentCommands = getCommandsFieldValue(commandsField, invokeData.instance)
        var previousCommandsSize = currentCommands.size
        val maxCapacity = getField(invokeData, maxCapacityVariable.name).get(invokeData.instance) as Int

        repeat(maxCapacity) {
            val command = Command.entries.random()
            val result = invokeAddCommand(invokeData, command)
            assertTrue(result) { "Try to invoke ${addCommandMethod.name} $it-th time, it should add the command into the commands array since the array's size is less than the ${maxCapacityVariable.name}, but the command was not added, the returned value is $result" }
            currentCommands = getCommandsFieldValue(commandsField, invokeData.instance)
            assertTrue(previousCommandsSize + 1 == currentCommands.size) { "Try to invoke ${addCommandMethod.name}, but the size of the commands array was not changed" }
            assertTrue(currentCommands.last().toString() == command.toString()) { "the method ${addCommandMethod.name} should add a command into the end of the collection" }
            previousCommandsSize = currentCommands.size
        }

        val result = invokeAddCommand(invokeData)
        assertTrue(!result) { "Try to invoke ${addCommandMethod.name} ${maxCapacityVariable.name} + 1-th time, the function should return false, but it returns $result" }
    }
    @Test
    fun gameServiceTestClassTest() {
        val clazz = gameServiceTestClass.checkBaseDefinition()
        gameServiceTestClass.checkFieldsDefinition(clazz, false)
    }

    private fun getField(invokeData: TestMethodInvokeData, name: String): Field {
        val field = invokeData.clazz.declaredFields.find { it.name == name }
        assert(field != null) { "Can not find field $name" }
        field!!.isAccessible = true
        return field
    }

    private fun getCommandsFieldValue(field: Field, instance: Any): ArrayDeque<*> {
        (field.get(instance) as? ArrayDeque<*>)?.let {
            return it
        }
        assert(false) { "The command field must be ArrayDeque!" }
        return ArrayDeque<Command>()
    }

    @Test
    fun commandFieldTest() {
        val invokeData = TestMethodInvokeData(gameServiceTestClass, addCommandMethod)
        val commandsField = getField(invokeData, commandsVariable.name)
        val initialValue = getCommandsFieldValue(commandsField, invokeData.instance)
        assert(initialValue.isEmpty()) { "The initial value of the command field should be an empty array, the current implementation creates an array with ${initialValue.size} elements" }
    }
}