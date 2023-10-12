import org.jetbrains.academy.test.system.core.models.method.TestMethodInvokeData
import org.jetbrains.kotlin.course.tamagotchi.game.GameService
import org.jetbrains.kotlin.course.tamagotchi.models.Command
import org.jetbrains.kotlin.course.tamagotchi.models.Mode
import org.junit.jupiter.api.Test
import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException
import java.util.*

class Test {
    companion object {
        private val commandToAdd = Command.Eat
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

    private fun invokeAddCommand(invokeData: TestMethodInvokeData) = gameServiceTestClass.invokeMethodWithArgs(
        commandToAdd,
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
            val result = invokeAddCommand(invokeData)
            assert(result) { "Try to invoke ${addCommandMethod.name} $it-th time, it should add the command into the commands array since the array's size is less than the ${maxCapacityVariable.name}, but the command was not added, the returned value is $result" }
            currentCommands = getCommandsFieldValue(commandsField, invokeData.instance)
            assert(previousCommandsSize + 1 == currentCommands.size) { "Try to invoke ${addCommandMethod.name}, but the size of the commands array was not changed" }
            previousCommandsSize = currentCommands.size
        }

        val result = invokeAddCommand(invokeData)
        assert(!result) { "Try to invoke ${addCommandMethod.name} ${maxCapacityVariable.name} + 1-th time, the function should return false, but it returns $result" }
    }

    private fun testEmptyCommands(invokeData: TestMethodInvokeData, mode: Mode) {
        // By some reason it does not work with invokeMethodWithArgs
        val result = invokeData.method.invoke(invokeData.instance, Mode.Queue)?.toString()
        assert(result == null) { "Try to invoke ${getCommandMethod.name} with an empty commands array, it should return null, but it returns $result" }
    }

    @Test
    fun getCommandTestMethodBaseTest() {
        getCommandTestMethodTestForMode(Mode.Queue)
        getCommandTestMethodTestForMode(Mode.Stack)
    }

    private fun getCommandTestMethodTestForMode(mode: Mode) {
        // TODO: it does not work with reflection. So, will test in the regular way
        val service = GameService()
        var result = service.getCommand(mode)
        assert(result == null) { "Try to invoke ${getCommandMethod.name} with an empty commands array, it should return null, but it returns $result" }

        val numberOfCommands = 10
        repeat(numberOfCommands) {
            service.addCommand(commandToAdd)
        }
        repeat(numberOfCommands) {
            result = service.getCommand(mode)
            assert(result == commandToAdd) { "Try to invoke ${getCommandMethod.name} with non empty commands array, it should return $commandToAdd, but it returns $result" }
        }

        result = service.getCommand(mode)
        assert(result == null) { "Try to invoke ${getCommandMethod.name} with an empty commands array, it should return null, but it returns $result" }
    }

    @Test
    fun getCommandTestMethodQueueTest() {
        // TODO: it does not work with reflection. So, will test in the regular way
        val service = GameService()
        val commandsToAdd = listOf(Command.Eat, Command.Sleep, Command.Clean)
        commandsToAdd.forEach { service.addCommand(it) }

        commandsToAdd.forEach {
            val result = service.getCommand(Mode.Queue)
            assert(result == it) { "Try to get elements in the Queue mode, but the order is incorrect. It should follow the First-In-First-Out approach." }
        }
    }

    @Test
    fun getCommandTestMethodStackTest() {
        // TODO: it does not work with reflection. So, will test in the regular way
        val service = GameService()
        val commandsToAdd = listOf(Command.Eat, Command.Sleep, Command.Clean)
        commandsToAdd.forEach { service.addCommand(it) }

        commandsToAdd.reversed().forEach {
            val result = service.getCommand(Mode.Stack)
            assert(result == it) { "Try to get elements in the Stack mode, but the order is incorrect. It should follow the Last-In-First-Out approach." }
        }
    }
}