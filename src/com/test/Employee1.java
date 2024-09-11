package com.test;


import java.util.List;

public final class Employee1 {
    private final  String name;
    private final int age;
    private final List<Integer> list;

    public Employee1(String name, int age, List<Integer> list) {
        this.name = name;
        this.age = age;
        this.list = List.copyOf(list);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Integer> getList() {
        return list;
    }
}
