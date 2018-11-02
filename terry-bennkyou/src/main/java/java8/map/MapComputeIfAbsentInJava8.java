package java8.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapComputeIfAbsentInJava8 {

    public static void main(String[] args) {

        //学生的集合

        List<Student> students = new ArrayList<>();
        students.add(new Student("张三", "男", 18));
        students.add(new Student("李四", "男", 20));
        students.add(new Student("韩梅梅", "女", 18));
        students.add(new Student("小红", "女", 45));

        //声明接收结果的 map
        Map<String, List<Student>> resultMap = new HashMap<>();
        for (Student student : students) {
            List<Student> s = resultMap.computeIfAbsent(student.getSex(), k -> new ArrayList<>());
            s.add(student);
        }

        resultMap.entrySet().forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));
    }
}
