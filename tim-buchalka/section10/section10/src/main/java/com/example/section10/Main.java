package com.example.section10;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");
        String[] items = {"apples", "bananas", "milk", "eggs"};

        List<String> list = List.of(items);
        System.out.println(list);

        ArrayList<String> test = new ArrayList<>(List.of("test1", "test2"));

    }
}