import org.jetbrains.academy.test.system.core.models.Visibility
import org.jetbrains.academy.test.system.core.models.classes.ClassType
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.variable.TestVariable


internal val hairTypeTestClass = TestClass(
    "HairType",
    "org.jetbrains.kotlin.course.old.school.photo",
    classType = ClassType.ENUM,
    declaredEnumEntries = listOf(
        TestVariable(
            name = "Dark",
            javaType = "HairType",
            visibility = Visibility.PUBLIC,
        ),
        TestVariable(
            name = "Light",
            javaType = "HairType",
            visibility = Visibility.PUBLIC,
        ),
    ),
)
