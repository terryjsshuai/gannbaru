package design.pattern.nullobject;

import java.util.Arrays;

public class CustomerFactory {

    public static final String[] names = {"Rob", "Joe", "Julie"};

    public static AbstractCustomer getCustomer(String name) {
        if (Arrays.stream(names).filter(s -> s.equalsIgnoreCase(name)).findFirst().isPresent()) {
            return new RealCustomer(name);
        }
        /*for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(name)) {
                return new RealCustomer(name);
            }
        }*/
        return new NullCustomer();
    }
}