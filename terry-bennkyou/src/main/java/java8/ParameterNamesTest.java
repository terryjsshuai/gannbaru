package java8;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ParameterNamesTest {
    public static void main(String[] args) throws Exception {
        Method method = ParameterNamesTest.class.getMethod("main", String[].class);
        for (final Parameter parameter : method.getParameters()) {
            System.out.println("Parameter: " + parameter.getName());
        }
    }
}
