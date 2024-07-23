import java.util.*;
import java.util.stream.*;

public class UsingCollectorAsTerminaOperation {

    // https://dev.java/learn/api/streams/using-collectors/
    // Collector: interface used to refer to an
    // instance of Collection (e.g Collectors.toList()) or Map(e.g.
    // Collectors.toSet())
    // Collectors: factory class to create to create most used collections
    // you can implement your own collection by Implementing
    // the Collector interface
    public static void main(String[] args) {
        // ### Collecting in a Collection
        // Three main ways to collect elements:
        // .toList() --> immutable: toUnmodifiableList()
        // .toSet() --> immutable: toUnmodifiableSet()
        // .toCollection(supplier)

        // Example
        List<Integer> numbers = IntStream.range(0, 10)
                .boxed() // intermediate operation to create a Stream<Integer>
                .collect(Collectors.toList());

        Set<Integer> evenNumbers = IntStream.range(0, 10)
                .map(number -> number / 2)
                .boxed()
                .collect(Collectors.toSet());
        System.out.println("evenNumbers = " + evenNumbers);

        // Example using a supplier
        LinkedList<Integer> linkedList = IntStream.range(0, 10)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println("Linked lists = " + linkedList);

        // ## Counting with a Colelctor
        // Colelctors offer some terminal methods
        // example counting
        Collection<String> string = List.of("one", "two", "three");
    }

}