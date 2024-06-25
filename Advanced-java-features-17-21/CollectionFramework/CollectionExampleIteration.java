import java.util.*;

public class CollectionExampleIteration {

    public static void main(String[] args) {
        // ## Iterating over the elements of a Collection

        // Using an Iterator on a Collection
        Collection<String> strings = List.of("one", "two", "three", "four");
        // for (Iterator<String> iterator = strings.iterator(); iterator.hasNext();) {
        // String el = iterator.next();
        // if (el.length() == 3) {
        // System.out.println(el);
        // }
        // }

        // Updating a Collection While iterating over it
        Collection<String> strings2 = new ArrayList<>();
        strings2.add("one");
        strings2.add("two");
        strings2.add("three");

        Iterator<String> iterator2 = strings2.iterator();

        // while (iterator2.hasNext()) {
        // String el = iterator2.next();
        // strings2.remove(el);
        // }

        // implementing Iterable iterator exampel
        for (int i : new Range(0, 5)) {
            System.out.println("i = " + i);
        }
    }
}
