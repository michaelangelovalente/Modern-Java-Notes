import java.util.*;

public class SortedKeysAndNavigableMap {

    public static void main(String[] args) {

        // #### Sorted maps
        SortedMap<Integer, String> sMap = new TreeMap<>();
        sMap.put(1, "one");
        sMap.put(2, "two");
        sMap.put(3, "three");
        sMap.put(4, "four");
        sMap.put(5, "five");
        sMap.put(6, "six");

        Integer viewUpTo = 3;
        SortedMap<Integer, String> headMap = sMap.headMap(viewUpTo); // return view of map upTo val 3
        headMap.put(0, "zero"); // works
        // headMap.put(4, "four"); // does not work --> map is a view upTo 3

        // #### NavigableMap
        NavigableMap<Integer, String> nMap = new TreeMap<>();
        nMap.put(1, "one");
        nMap.put(2, "two");
        nMap.put(3, "three");
        nMap.put(4, "four");
        nMap.put(5, "five");
        nMap.put(6, "six");

        nMap.keySet().forEach(k -> System.out.println(k + " "));
        System.out.println();

        NavigableSet<Integer> descindingKeys = nMap.descendingKeySet();
        descindingKeys.forEach(k -> System.out.println(k + " "));

        // ## CHOOSING IMMUTABLE TYPE FOR YOU KEY
        // ### Avoiding the Use of Mutable keys --. (this sums it up....) :D just i case
        // check: https://dev.java/learn/api/collections-framework/choosing-keys/

    }

}