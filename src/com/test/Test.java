package com.test;

import custom.Student;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Test {

//    Lock lock = new ReentrantLock(true);

    public static Test instance = null;

    Integer temp = 100;

    private Test() {

    }

    public static Test getInstance() {
        if (Objects.isNull(instance)) {
            synchronized (Test.class) {
                if (Objects.isNull(instance)) {
                    instance = new Test();
                }
            }
        }
        return instance;
    }

//    private static final System.Logger LOGGER = System.getLogger(Test.class.getName());


    public static void main(String[] args) {

//        var name = "Duke";
//        var info = STR."My name is \{name}"; // String interpolation
//        System.out.println(info);
//
//        ArrayList<Integer> st = new ArrayList<>();
//        var map = new HashMap<Integer,Integer>();
//
//        var num = Math.clamp(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
//        System.out.println(num);
//
//        var sb = new StringBuilder();
//        sb.repeat("str", 3);
//
//        var str = "123";
//        str.indent()
//        str.splitWithDelimiters()
//        Arrays.mismatch()
//        LOGGER.log(System.Logger.Level.INFO, "Hello, %s!", str);
//        LOGGER.log(System.Logger.Level.WARNING,STR."This is test : \{str}");

//        var bi = new BigInteger(str);
//        bi = bi.parallelMultiply(BigInteger.TEN);


//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 4, 5, 6, 7, 8, 9, 9, 10);
//
//        numbers.stream().
//                filter(var -> numbers.indexOf(var) != numbers.lastIndexOf(var)).distinct().
//                forEach(System.out::println);
//
//        List<Integer> numbers1 = Arrays.asList(6, 7, 8, 9, 9, 10);
//        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 4, 5);
//
//
//        var merged = Stream.concat(numbers1.stream(), numbers2.stream()).toList();

//        var str = "aaabbcaabbddd";
//        var prev = str.charAt(0);
//        var count = 1;
//        for (var i = 1; i < str.length(); i++) {
//            var curr = str.charAt(i);
//            if (prev == curr) {
//                count++;
//            } else {
//                System.out.println(prev + " = " + count);
//                prev = curr;
//                count = 1;
//            }
//        }
//        System.out.println(prev + " = " + count);
        var list = List.of("A_1",
                "A_2",
                "B_1",
                "A_a3",
                "C_2",
                "B_2",
                "A_");


    }


    public Map<String, Double> getAverage(List<Student> studentList) {
        return studentList.parallelStream().
                collect(Collectors.groupingBy
                        (Student::getName, Collectors.averagingDouble(Student::getMarks)));
    }
}





