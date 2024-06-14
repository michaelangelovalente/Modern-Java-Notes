package com.example.section10;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");
        String[] items = {"apples", "bananas", "milk", "eggs"};

        List<String> list = List.of(items);
        System.out.println(list);

        ArrayList<String> test = new ArrayList<>(List.of("test1", "test2"));
        System.out.println(test);
        test.addAll(new ArrayList<>(List.of("test4", "test5")));
        System.out.println(test);
        test.retainAll(List.of("test5","test1"));
        System.out.println(test);

        test.addAll(Arrays.asList("test7", "test8", "£test9"));
        System.out.println(test);
        test.sort(Comparator.naturalOrder());

        var groceryArray = test.toArray(new String[test.size()]);

    }
}