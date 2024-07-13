import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdddingTerminalOperationsOnStream {
        public static void main(String[] args) {
                // When you use reduce you have a lot of
                // points to check. (BinaryOp has to be assiociative, check identity element
                // ecc...)
                // You should avoid the use when possibile.
                // Use the alternatives below instead

                // ## Counting elements processed by a stream
                Collection<String> strings = List.of("one", "two", "three", "four", "five", "six", "seven", "eight",
                                "nine",
                                "ten");

                long count = strings.stream()
                                .filter(s -> s.length() == 3)
                                .count();
                System.out.println("count = " + count);

                // ## Consuming each elemetn one by one
                strings.stream()
                                .filter(s -> s.length())
                                .map(String::toUpperCase)
                                .forEach(System.out::println);

                // "capturing lambda expression" using result::add
                // causes a performance hit and doesn't allow stream parallels.
                // This is due to ArrayList not tailored to handle concurrent access
                Collection<String> strings2 = List.of("one", "two", "three", "four", "five", "six", "seven", "eight",
                                "nine",
                                "ten");
                List<String> result = new ArrayList<>();
                strings2.stream()
                                .filter(s -> s.length() == 3)
                                .map(String::toUpperCase)
                                .forEach(result::add);

                // You can in alternative store it in a class tailored to handle
                // concurent access

                Collection<String> strings3 = List.of("one", "two", "three", "four", "five", "six", "seven", "eight",
                                "nine",
                                "ten");
                List<String> result3 = new ArrayList<>();
                strings2.stream()
                                .filter(s -> s.length() == 3)
                                .map(String::toUpperCase)
                                .collect(Collectors.toList());
                // Collector api can handle concurrency

                // An alternative using JAVA SE 16
                Stream<String> strings4 = Stream.of("one", "two", "three", "four");
                List<String> result4 = strings.filter(s -> s.length() == 3)
                                .map(String::toUpperCase)
                                .toList();// unmodifiable list.

                // ## Collecting Stream Elements in a Collection, or an Array
                // Main siturations StreamApi handles
                // - Immutable lists
                // - usage of ArrayList vs LinkedList
                // - Collection of thirdPart or homemade implementation of List

                // ## Collecting in a PLain ArrayList
                // Unkown number of elements
                Stream<String> strings5 = Stream.of("one", "two", "three", "four");

                List<String> resuList = strings5.filter(s -> s.length() == 3)
                                .map(String::toUpperCase)
                                .collect(Collectors.toList());

                // For known number of elements. .toCollection(Supplier)
                Stream<String> strings6 = Stream.of("one", "two", "three", "four");
                List<String> result6 = strings6.filter(s -> s.length() == 3)
                                .map(String::toUpperCase)
                                .collect(Collectors.toCollection(() -> new ArrayLIst<>(10_000)));

                // ## Collecting in an Immmutable List
                Stream<String> strings7 = Stream.of("one", "two", "three", "four");
                List<String> result7 = strings8.filter(s -> s.length() == 3)
                                .map(String::toUpperCase)
                                .collect(Collectors.toUnmodifiableList());
                // From JAVA SE 16 you can use the pattern below
                Stream<String> strings8 = Stream.of("one", "two", "three", "four");
                List<String> result8 = strings8.filter(s -> s.length() == 3)
                                .map(String::toUpperCase)
                                .toList();

                // ## Collecting in a Homemade List
                // this pattern can be used to build any implementation,
                // even the ones that are not
                // part of the JDK
                Stream<String> strings9 = Stream.of("one", "two", "three", "four");
                List<String> result9 = strings.filter(s -> s.length() == 3)
                                .map(String::toUpperCase)
                                .collect(Collectors.toCollection());

                // ## Collecting in a Set
                Stream<String> strings10 = Stream.of("one", "two", "three", "four");
                // Set is part of collection so a less efficient way to
                // collect data in a set is
                // Set<String> result10 = strings10.filter(s -> s.length() == 3)
                // .map(String::toUpperCase)
                // .collect(Collectors.toCollection(HashSet::new));
                Set<String> result10 = strings10.filter(s -> s.length() == 3)
                                .map(String::toUpperCase)
                                .collect(Collectors.toSet());

                // ## Collecting in an Array
                Stream<String> strings11 = Stream.of("one", "two", "three", "four");
                String[] result11 = strings11.filter(s -> s.length() == 3)
                                .map(String::toUpperCase)
                                .toArray(String[]::new);

                // ## Max, min, average patterns
                Stream<String> strings12 = Stream.of("one", "two", "three", "four", "five");
                String longest = strings12
                                .max(Comparator.comparing(Strings::length))
                                .orElseThrow();

                // ### Finding an Element w/ findFirst() and findAny()
                Collection<String> strings13 = List.of("one", "two", "three", "four", "five", "six", "seven", "eight",
                                "nine", "ten");

                // Ordered stream processed in parallel, you can make it unorderd using
                // /unordered() or using a Set
                String first = strings13.stream()
                                // .unordered()
                                .parallel()
                                .filter(s -> s.length() == 3)
                                .findFirst()
                                .orElseThrow();

                // Checking if the elements of a Stream Match

                Collection<String> strings14 = List.of("one", "two", "three", "four", "five");
                // Checks for first validate predicate
                // it works, but it's inefficient.
                boolean exists = strings14.stream()
                                .filter(s -> s.length())
                                .findFirst()
                                .isPresent();

                // A more efficient solution would be
                // Stream API's methods: anyMatch(prediate).
                // There are also allMatch(predicate), noneMatch(predicate)
                boolean noBlank = strings14.stream()
                                .allMatch(Predicate.not(String::isBlank));

                boolean oneGT3 = strings14.stream()
                                .anyMatch(s -> s.length() == 3);

                // returns true if none of the elements match
                boolean allLT10 = strings14.stream()
                                .noneMatch(s -> s.length() > 10);

        }
}