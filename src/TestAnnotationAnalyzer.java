import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestAnnotationAnalyzer {

    public static void start(Class c) {
        List<Method> methods = new ArrayList<>();
        Method[] classMethods = c.getDeclaredMethods();

        /*
        * Добавляем методы с аннотацией @Test в ArrayList
         */
        for (Method m : classMethods) {
            if (m.isAnnotationPresent(Test.class)) {
                methods.add(m);
            }
        }
        Collections.sort(methods, (o1, o2) -> -(o1.getAnnotation(Test.class).priority() - o2.getAnnotation(Test.class).priority()));    // Comparator<Method> // тут происходит сравнение по приоритету(порядку запуска)

        /*
        * Ищем @BeforeSuite и ставим его на первое место в порядке запуска
         */
        for (Method m : classMethods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (methods.get(0).isAnnotationPresent(BeforeSuite.class)) {
                    throw new RuntimeException("@BeforeSuite может быть только один");
                }
                methods.add(0, m);
            }
        }

        /*
         * Ищем @AfterSuite  и ставим его на последнее место в порядке запуска
         */
        for (Method m : classMethods) {
            if (m.isAnnotationPresent(AfterSuite.class)) {
                if (methods.get(methods.size() - 1).isAnnotationPresent(AfterSuite.class)) {
                    throw new RuntimeException("@AfterSuite может быть только один");
                }
                methods.add(m);
            }
        }

        try {
            for (Method m : methods) {
                m.invoke(null);
            }
            }catch(IllegalAccessException e){
                e.printStackTrace();
            } catch(InvocationTargetException e){
                e.printStackTrace();
            }
        }
}
