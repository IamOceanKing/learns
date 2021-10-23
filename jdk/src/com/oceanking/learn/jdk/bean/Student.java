package com.oceanking.learn.jdk.bean;

/**
 * @author whaiy
 * @version 1.0 2019/01/23
 */
public class Student implements Comparable {

    private int age;

    private String name;

    public Student(int age) {
        this.age = age;
    }

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        return ((Student) o).getAge() - this.age;
    }

}
