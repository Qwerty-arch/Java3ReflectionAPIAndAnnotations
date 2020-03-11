
public class MyTest {

    @Test(description = "Я запускаю тест 1")
    public static void test1() {
        System.out.println("test1 (priority = default)");
    }

    @Test(description = "Я запускаю тест 2", priority = 1)
    public static void test2() {
        System.out.println("test2 (priority = 1)");
    }

    @Test(description = "Я запускаю тест 3", priority = 10)
    public static void test3() {
        System.out.println("test3 (priority = 10)");
    }

    @BeforeSuite
    public static void test4() {
        System.out.println("test4 with @BeforeSuite");
    }

    @AfterSuite
    public static void test5() {
        System.out.println("test5 with @AfterSuite");
    }
}