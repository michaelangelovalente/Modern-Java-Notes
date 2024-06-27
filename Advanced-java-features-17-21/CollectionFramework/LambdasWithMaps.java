import java.util.*;

public class LambdasWithMaps {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        map.forEach((k, v) -> System.out.println(k + " :: " + v));
        map.replaceAll((k, v) -> {
            System.out.println(String.format("Modifying....[%s::%s]", k, v));
            return v.toUpperCase();
        });
        System.out.println(map);

        System.out.println("---COMPUTING VALUES-----");
        // ##### Computing Values
        // --- example case without compute function
        List<String> strings = List.of("one", "two", "three", "four", "five", "six", "seven");
        // Map<Integer, List<String>> mapC = new HashMap<>();
        // for (String word : strings) {
        // int length = word.length();

        // if (!mapC.containsKey(length)) {
        // mapC.put(length, new ArrayList<>());
        // }
        // mapC.get(length).add(word);
        // }

        // System.out.println(mapC);

        // ---- using compute()...--> putIfAbsent()
        Map<Integer, List<String>> mapC = new HashMap<>();
        // for (String word : strings) {
        // int length = word.length();
        // mapC.putIfAbsent(length, new ArrayList<>());
        // mapC.get(length).add(word);
        // }

        // -- even better computeIfAbsent()...
        for (String word : strings) {
            int length = word.length();
            mapC.computeIfAbsent(length, key -> new ArrayList<>())
                    .add(word);
        }

        // ##### Merging values
        // example case using merge()
        System.out.println("---------- MERGING VALUES ------");
        List<String> strings2 = List.of("one", "two", "three", "four", "five", "six", "seven");
        Map<Integer, String> mapM = new HashMap<>();
        for (String word : strings2) {
            int l = word.length();
            mapM.merge(l, word,
                    (existingValue, newWord) -> existingValue + ", " + newWord);
        }
        System.out.println(mapM);
        System.out.println("--------------------");
        mapM.forEach(
                (k, v) -> System.out.println(k + " :: " + v));

    }
}