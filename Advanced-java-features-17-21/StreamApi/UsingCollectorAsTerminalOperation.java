import java.util.*;
import java.util.stream.*;

public class UsingCollectorAsTerminalOperation {

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

                // collectors covered --> .collect(), counting(), joining()

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
                Collection<String> strings = List.of("one", "two", "three");

                long count = strings.stream().count();
                long countWithACollector = strings.stream().collect(Collectors.counting());

                System.out.println("count =" + count);
                System.out.println("countWithACollector =" + countWithACollector);

                // ### Collecting in a String of Characters
                // using .joining()
                String joined = IntStream.range(0, 10)
                                .boxed()
                                .map(Object::toString)
                                .collect(Collectors.joining());
                // .collect(Collectors.joining(", ")); // overload1
                // .collect(Collectors.joining(", ", "{"), "}"); // overload2

                System.out.println("joined = " + joined);

                System.out.println("---------------------------------");
                // ### Partitioning Elements with a Predicate --> using .partitioningBy()
                // All the elements of the stream will be bound by true or false
                // --> returns Map<Boolean, List<T>>
                Collection<String> strings2 = List.of("one", "two", "three", "four", "five", "six", "seven", "eight",
                                "nine",
                                "ten", "eleven", "twelve");
                Map<Boolean, List<String>> map2 = strings2.stream()
                                .collect(Collectors.partitioningBy(s -> s.length() > 4));
                System.out.println(map2);
                // {false=[one, two, four, five, six, nine, ten], true=[three, seven, eight,
                // eleven, twelve]}

                System.out.println("---------------------------------");
                // ### Collecting in a Map with Grouping By
                // key --> Function<T, K>
                // value --> List<T>
                // creates a key for each element of the stream applying function ( Classifier )
                Collection<String> strings3 = List.of("one", "two", "three", "four", "five", "six", "seven", "eight",
                                "nine",
                                "ten", "eleven", "twelve");
                Map<Integer, List<String>> map3 = strings3.stream()
                                .collect(Collectors.groupingBy(String::length));
                map3.forEach((k, v) -> System.out.println(k + " :: " + v));
                System.out.println("-------------------");
                // ### Post-processing the values created with Grouping by using a downstream
                // This how the downstream would logically work
                // [one, two, six, ten] .stream().collect(Collectors.counting()) -> 4L
                // [four, five, nine] .stream().collect(Collectors.counting()) -> 3L
                // [three, seven, eight] .stream().collect(Collectors.counting()) -> 3L
                // [eleven, twelve] .stream().collect(Collectors.counting()) -> 2L
                Map<Integer, Long> map4 = strings3.stream()
                                .collect(Collectors.groupingBy(
                                                String::length,
                                                Collectors.counting()));
                map4.forEach((k, v) -> System.out.println(k + " :: " + v));

                System.out.println("################### Collecting with To Map ###########");
                // ### Collecting in a Map with To Map

                // pattern 1: useful for caching data
                // uses to Functions:
                // key --> key mapper: used to create the key
                // value --> value mapper: used to creat he value
                // note it does not handle the case where several elements of your stream
                // genereate the same key...
                // if it happens it throws IllegalStateException
                // List<User> users = ....;
                // Map<Long, User> userCache =
                // users.stream()
                // .collect( Collectors.toMap(
                // User::getPrimaryKey,
                // Function<T,U>.identity()
                // ));

                // pattern 2: multiple instances that have the same key
                // you pass a further argument of type BinaryOperator
                Collection<String> strings5 = List.of("one", "two", "three", "four", "five", "six", "seven", "eight",
                                "nine",
                                "ten", "eleven", "twelve");

                Map<Integer, String> map5 = strings5.stream()
                                .collect(
                                                Collectors.toMap(
                                                                // key mapper
                                                                element -> element.length(),
                                                                // value mapper
                                                                element -> element,
                                                                // merge function used for the two elements with the
                                                                // same key
                                                                (element1, element2) -> element1 + ", " + element2));
                map5.forEach((key, value) -> System.out.println(key + " :: " + value));
                Map<Integer, String> map6 = strings5.stream()
                                .collect(
                                                Collectors.toConcurrentMap(
                                                                // key mapper
                                                                element -> element.length(),
                                                                // value mapper
                                                                element -> element,
                                                                // merge function used for the two elements with the
                                                                // same key
                                                                (element1, element2) -> element1 + ", " + element2));
                map6.forEach((key, value) -> System.out.println(key + " :: " + value));

                System.out.println("-----------------------");
                /// ### Extracting Maxes from a Histogram
                Collection<String> strings7 = List.of("one", "two", "three", "four", "five", "six", "seven", "eight",
                                "nine",
                                "ten", "eleven", "twelve");

                Map<Integer, Long> histogram = strings7.stream()
                                .collect(
                                                Collectors.groupingBy(
                                                                String::length,
                                                                Collectors.counting()));
                histogram.forEach((k, v) -> System.out.println(k + " :: " + v));
                // 3 :: 4 --> MAX value to be extracted
                // 4 :: 3
                // 5 :: 3
                // 6 :: 2
                // to create a stream on a map you need one of:
                // entrySet() --> set of entries
                // keySet()
                // values()

                // the case above needs the entier view of the map ( both key and max value)
                // so we use entrySet()

                Map.Entry<Integer, Long> maxValue = histogram.entrySet().stream()
                                .max(Map.Entry.comparingByValue())
                                .orElseThrow();
                System.out.println("maxValue = " + maxValue);

                // readable Pattern using records (JAVA SE 16+)

                // we first createa a record to model the key-value pairs of the map
                record NumberOfLength(int lengthO, long numberO) {
                        static NumberOfLength fromEntry(Map.Entry<Integer, Long> entry) {
                                return new NumberOfLength(entry.getKey(), entry.getValue());
                        }

                        static Comparator<NumberOfLength> comparingByLength() {
                                return Comparator.comparing(NumberOfLength::lengthO);
                        }

                }
                // the previous pattern would look like this then
                NumberOfLength maxNumberOfLength = histogram.entrySet().stream()
                                .map(NumberOfLength::fromEntry)
                                .max(NumberOfLength.comparingByLength())
                                .orElseThrow();
                System.out.println(maxNumberOfLength);

                System.out.println("############# Mapping with a Collector ##############");
                // #### Using intermediate Collectors ( intermediate operations like mapping,
                // filtering, flatmapping)
                // using .mapping() collector to create a mapping function
                Collection<String> strings9 = List.of("one", "two", "three", "four", "five", "six", "seven", "eight",
                                "nine",
                                "ten", "eleven", "twelve");
                List<String> result9 = strings9.stream()
                                .collect(
                                                Collectors.mapping(String::toUpperCase, Collectors.toList()));
                System.out.println("result = " + result9);

                System.out.println("Extracting ambigous max");
                // Extracing an Ambigous value ( max value example )
                Collection<String> strings10 = List.of("two", "three", "four", "five", "six", "seven", "eight", "nine",
                                "ten", "eleven", "twelve");

                Map<Integer, Long> histogram10 = strings10.stream()
                                .collect(
                                                Collectors.groupingBy(
                                                                String::length,
                                                                Collectors.counting()));

                histogram10.forEach((key, value) -> System.out.println(key + " :: " + value));
                // 3 :: 3 (amb max)
                // 4 :: 3 (amb max)
                // 5 :: 3
                // 6 :: 2

                var map10 = histogram10.entrySet().stream()
                                .map(NumberOfLength::fromEntry)
                                .collect(
                                                Collectors.groupingBy(
                                                                NumberOfLength::numberO, // values from map
                                                                // Downstream collector
                                                                Collectors.mapping(NumberOfLength::lengthO,
                                                                                // list of keys with the given max
                                                                                Collectors.toList())));

                System.out.println("map10 = " + map10);
                // final result
                Map.Entry<Long, List<Integer>> result10 = map10.entrySet().stream()
                                .max(Map.Entry.comparingByKey())
                                .orElseThrow();
                System.out.println("result = " + result10);
                // map10 = {2=[6], 3=[3, 4, 5]}
                // result = 3=[3, 4, 5]

                // ### Filtering and FlatMapping with a COlelctor
                // Collectors.filtering() & Collectors.flatMapping() work the same way but the
                // first take a predicate and the second takes a flatmapping function
        }

}