import org.jetbrains.academy.test.system.core.models.Visibility
import org.jetbrains.academy.test.system.core.models.classes.ClassType
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.variable.TestVariable


internal val colorTestClass = TestClass(
    "Color",
    "org.jetbrains.kotlin.course.old.school.photo",
    classType = ClassType.ENUM,
    declaredEnumEntries = listOf(
        TestVariable(
            name = "White",
            javaType = "Color",
            visibility = Visibility.PUBLIC,
        ),
        TestVariable(
            name = "Blue",
            javaType = "Color",
            visibility = Visibility.PUBLIC,
        ),
        TestVariable(
            name = "Orange",
            javaType = "Color",
            visibility = Visibility.PUBLIC,
        ),
        TestVariable(
            name = "Yellow",
            javaType = "Color",
            visibility = Visibility.PUBLIC,
        ),
        TestVariable(
            name = "Pink",
            javaType = "Color",
            visibility = Visibility.PUBLIC,
        ),
        TestVariable(
            name = "Purple",
            javaType = "Color",
            visibility = Visibility.PUBLIC,
        ),
        TestVariable(
            name = "Green",
            javaType = "Color",
            visibility = Visibility.PUBLIC,
        ),
        TestVariable(
            name = "Gray",
            javaType = "Color",
            visibility = Visibility.PUBLIC,
        ),
    ),
)
