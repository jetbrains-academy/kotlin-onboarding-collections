import org.jetbrains.academy.test.system.core.models.Visibility
import org.jetbrains.academy.test.system.core.models.classes.ClassType
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.variable.TestVariable

internal val accessoryTestClass = TestClass(
    "Accessory",
    "org.jetbrains.kotlin.course.old.school.photo",
    classType = ClassType.ENUM,
    declaredEnumEntries = listOf(
        TestVariable(
            name = "Glasses",
            javaType = "Accessory",
            visibility = Visibility.PUBLIC,
        ),
        TestVariable(
            name = "Earrings",
            javaType = "Accessory",
            visibility = Visibility.PUBLIC,
        ),
        TestVariable(
            name = "Watch",
            javaType = "Accessory",
            visibility = Visibility.PUBLIC,
        ),
        TestVariable(
            name = "Pen",
            javaType = "Accessory",
            visibility = Visibility.PUBLIC,
        ),
        TestVariable(
            name = "Hat",
            javaType = "Accessory",
            visibility = Visibility.PUBLIC,
        ),
    ),
)
