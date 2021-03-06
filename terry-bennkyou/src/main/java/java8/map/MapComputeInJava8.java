package java8.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapComputeInJava8 {
    public static void main(String[] args) {
        //现在我们要做一个操作，统计字符串中每一个的 单词出现的次数。
        Map<String, Integer> wordCounts = new ConcurrentHashMap<>(10);
        String s =
                "Lorem ipsum dolor sit amet consetetur iam nonumy sadipscing " +
                        " elitr, sed diam nonumy eirmod tempor invidunt ut erat sed " +
                        "labore et dolore magna dolor sit amet aliquyam erat sed diam";

        wordCounts.put("sed", 0);
        for (String t : s.split(" ")) {
            wordCounts.compute(t, (k, v) -> {
                if (null == v) v = 0;
                v = v + 1;
                return v;
            });
        }
        System.out.println(wordCounts);
    }
}
