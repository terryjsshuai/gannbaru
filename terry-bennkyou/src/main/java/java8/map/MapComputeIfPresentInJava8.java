package java8.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapComputeIfPresentInJava8 {
    public static void main(String[] args) {

        Map<String, Integer> wordCounts = new ConcurrentHashMap<>(10);
        String s =
                "Lorem ipsum dolor sit amet consetetur iam nonumy sadipscing " +
                        " elitr, sed diam nonumy eirmod tempor invidunt ut erat sed " +
                        "labore et dolore magna dolor sit amet aliquyam erat sed diam";

        wordCounts.put("sed", 0);
        for (String t : s.split(" ")) {
            wordCounts.computeIfPresent(t, (k, v) -> v + 1);
        }
        System.out.println(wordCounts);
    }
}
