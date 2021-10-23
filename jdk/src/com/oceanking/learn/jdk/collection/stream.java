package com.oceanking.learn.jdk.collection;

import com.oceanking.learn.jdk.bean.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.stream.Collectors;

/**
 * @author whaiy
 * @version 1.0 2019/01/23
 */
public class stream {

    @Test
    public void filter() throws InterruptedException {
        List<Integer> nums = new ArrayList<>();
        List<Integer> evens = nums.stream().filter(num -> num % 2 == 0).collect(Collectors.toList());

        evens = nums.stream().filter(num -> num % 2 == 0).distinct().collect(Collectors.toList());
    }

    @Test
    public void distinct() throws InterruptedException {
        List<Integer> nums = new ArrayList<>();
        List<Integer> evens = nums.stream().filter(num -> num % 2 == 0).distinct().collect(Collectors.toList());
    }

    @Test
    public void limit() throws InterruptedException {
        List<Integer> nums = new ArrayList<>();
        List<Integer> evens = nums.stream().filter(num -> num % 2 == 0).limit(2).collect(Collectors.toList());

  /*      List<Student> sortedCivilStudents = students.stream()
            .filter(student -> "土木工程".equals(student.getMajor())).sorted((s1, s2) -> s1.getAge() - s2.getAge())
            .limit(2)
            .collect(Collectors.toList());*/

        List<Student> students = new ArrayList<>();
        students.stream().sorted(Comparator.comparing(s -> s.getAge())).limit(2).collect(Collectors.toList());
    }

    @Test
    public void map() throws InterruptedException {
        List<Student> students = new ArrayList<>();

        Student s1 = new Student(6, "liu");
        Student s2 = new Student(5, "wu");

        students.add(s1);
        students.add(s2);
        //JDK8中有双冒号的用法，就是把方法当做参数传到stream内部，使stream的每个元素都传入到该方法里面执行一下
        List<String> names = students.stream().map(Student::getName).collect(Collectors.toList());

        List<Integer> collect = students.stream().map(s -> s.getAge()).collect(Collectors.toList());

        int sum = students.stream().mapToInt(s -> s.getAge()).sum();

        String[] strs = {"java8", "is", "easy", "to", "use"};
        List<String> distinctStrs = Arrays.stream(strs).map(str -> str.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());

        System.out.println(distinctStrs);
        System.out.println(names);
        System.out.println(collect);
    }

    @Test
    public void allMatch() throws InterruptedException {
        List<Student> students = new ArrayList<>();
        boolean isAdult = students.stream().allMatch(student -> student.getAge() >= 18);
    }

    public static void main(String[] args) {
        Student s1 = new Student(6);
        Student s2 = new Student(5);
        System.out.println(s1.compareTo(s2));
        List list = new ArrayList();
        list.add(s1);
        list.add(s2);
        Collections.sort(list);
        System.out.println(list);
    }

}
