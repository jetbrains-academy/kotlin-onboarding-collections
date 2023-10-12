import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.Visibility
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.TestVariable
import org.jetbrains.academy.test.system.core.models.variable.VariableMutability

internal val getCommandMethod = TestMethod(
    "getCommand",
    TestKotlinType(
        "Command",
        isNullable = true
    ),
    arguments = listOf(
        TestVariable(
            name = "mode",
            javaType = "mode",
        )
    )
)

internal val addCommandMethod = TestMethod(
    "addCommand",
    TestKotlinType(
        "Boolean",
    ),
    arguments = listOf(
        TestVariable(
            name = "command",
            javaType = "command",
        )
    )
)

internal val maxCapacityVariable = TestVariable(
    name = "MAX_CAPACITY",
    javaType = "int",
    // Because it is inside companion object
    visibility = Visibility.PRIVATE,
    mutability = VariableMutability.VAL,
    isStatic = true,
    isConst = true
)

internal val commandsVariable = TestVariable(
    name = "commands",
    javaType = "ArrayDeque",
    kotlinType = TestKotlinType(
    "ArrayDeque",
        params = listOf("org.jetbrains.kotlin.course.tamagotchi.models.Command")
    ),
    visibility = Visibility.PUBLIC,
    mutability = VariableMutability.VAL,
)

internal val gameServiceTestClass = TestClass(
    "GameService",
    "org.jetbrains.kotlin.course.tamagotchi.game",
    declaredFields = listOf(
        maxCapacityVariable,
        commandsVariable
    ),
    customMethods = listOf(
        addCommandMethod,
        getCommandMethod
    ),
)
