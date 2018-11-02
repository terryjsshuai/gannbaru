package java8.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTestInJava8 {
    public static void main(String[] args) {
        //现在我们要做一个操作，把list中的对象，按照属性男女分组，然后把年龄汇总
        //学生的集合
        List<Student> students = new ArrayList<>();
        students.add(new Student("张三", "男", 18));
        students.add(new Student("李四", "男", 20));
        students.add(new Student("韩梅梅", "女", 18));
        students.add(new Student("小红", "女", 45));

        //声明接收结果的 map
        Map<String, Integer> resultMap = new HashMap<>();
        for (Student student : students) {
            resultMap.merge(student.getSex(), student.getAge(), (a, b) -> b + a);
        }
        printResult(resultMap);
    }

    private static void printResult(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            System.out.println("key:" + e.getKey() + "   " + "value:" + e.getValue());
        }
    }
}
