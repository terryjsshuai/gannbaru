package speciality;

import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        Optional<String> str = Optional.of("abc");
        System.out.println(str.get());

        str = Optional.ofNullable(null);
        System.out.println(str.orElse(""));
    }
}
