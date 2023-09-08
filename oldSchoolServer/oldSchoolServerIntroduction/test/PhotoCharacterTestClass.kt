import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.Visibility
import org.jetbrains.academy.test.system.core.models.classes.ClassType
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.variable.TestVariable
import org.jetbrains.academy.test.system.core.models.variable.VariableMutability

internal val photoCharacterTestClass = TestClass(
    "PhotoCharacter",
    "org.jetbrains.kotlin.course.old.school.photo",
    classType = ClassType.ENUM,
    declaredFields = listOf(
        TestVariable(
            name = "backgroundColor",
            javaType = colorTestClass.getFullName(),
            visibility = Visibility.PUBLIC,
            mutability = VariableMutability.VAL,
        ),
        TestVariable(
            name = "hairType",
            javaType = hairTypeTestClass.getFullName(),
            visibility = Visibility.PUBLIC,
            mutability = VariableMutability.VAL,
        ),
        TestVariable(
            name = "accessories",
            javaType = "List",
            kotlinType = TestKotlinType(
                "List",
                accessoryTestClass.getFullName(),
                isNullable = true,
            ),
            visibility = Visibility.PUBLIC,
            mutability = VariableMutability.VAL,
        )
    ),
)
