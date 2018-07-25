package java8;

import java.util.Arrays;

public class LambdaTest {
    public static void testLambda() {
        Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));
        System.out.println("-------------------------------------------------------------------");
        Arrays.asList("a", "b", "d").forEach((String e) -> System.out.println(e));
        System.out.println("-------------------------------------------------------------------");
        String separator = ",";
        Arrays.asList("a", "b", "d").forEach((String e) -> System.out.print(e + separator));
        System.out.println("-------------------------------------------------------------------");
        Arrays.asList("b", "a", "d").sort((e1, e2) -> e1.compareTo(e2));
        Arrays.asList("a", "b", "d").sort((e1, e2) -> {
            int result = e1.compareTo(e2);
            return result;
        });

    }

    public static void main(String[] args) {
        testLambda();
    }
}
