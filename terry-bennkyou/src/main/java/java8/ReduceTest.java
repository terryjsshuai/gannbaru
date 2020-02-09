package java8;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReduceTest {
    public static void main(String[] args) {
        List<Integer> integers = Lists.newArrayList(1, 2, 3, 4);
        Optional<Integer> reduce = integers.stream().reduce(Integer::sum);
        System.out.println(reduce.orElse(0));
        System.out.println("=============================");

        Integer reduce1 = integers.stream().reduce(10, Integer::sum);
        System.out.println(reduce1);

        System.out.println("=============================");

        List<Integer> integers1 = new ArrayList<>();
        Optional<Integer> reduce2 = integers1.stream().reduce(Integer::sum);
        System.out.println(reduce2.orElse(0));

        System.out.println("=============================");
        Integer reduce3 = integers1.stream().reduce(11, Integer::sum);
        System.out.println(reduce3);

        System.out.println("=============================");

        Integer reduce4 = integers.stream().reduce(10, (a, b) -> a + b, (a, b) -> a - b);
        System.out.println(reduce4);

        System.out.println("=============================");
        List<Integer> integers2 = Lists.newArrayList(1);
        Optional<Integer> reduce5 = integers2.stream().reduce(Integer::sum);
        System.out.println(reduce5.orElse(0));
    }
}
