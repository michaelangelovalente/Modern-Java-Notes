import java.util.function.*;
import java.util.*;
import java.util.stream.*;

public class CreatingYourOwnCollector {

        public static void main(String[] args) {
                // #### Four basic components of a collector
                // (1) - First component (SUPPLIER): container in which the elemetns of the
                // stream
                // will be collected. e.g ArrayList Hashset ecc..
                // the creation of these can be modeled with an instance of Supplier

                // (2) - Second component (ACCUMULATOR): is an Operation that models the adding
                // of a
                // single element from the stream to the container.
                // This component is modeled by a BiConsumer (accumulator) that takes 2
                // arguments: the container, the element of the stream that should be added to
                // the partially filled container

                // On parallelization:
                // The way this works in the Collector API is that a stream is divided in
                // substreams
                // and each will will be collected in its own instance of the container.

                // The final stage when the substreams are processed you have several containers
                // with the processed elements.
                //

                // (3) - Third Component (Combiner) - Modelled by Binary Operator, used to merge
                // the
                // containers together. It take
                // It takes two partially fillede contianers and returns one.
                // The BinaryOperator is modeled by the BiConsumer.
                // (Needed for parallel streams)

                // (4) - The fourth component (Finisher) - Needed by collectors that need
                // post-processing on the built container.

                // #### Collecting primitive types in a Collection --> Collectors.collect()
                // Example specialized stream of numbers w/ IntStream.collect()
                // IntStream.collect() takes three arguments
                // an Instance of Supplier, instance of ObjIntConsumer( ACCUMULATOR), instnace
                // of BiConsumer (Combiner)

                Supplier<List<Integer>> supplier = ArrayList::new; // (SUPPLIER)
                ObjIntConsumer<List<Integer>> accumulator = Collection::add; // (ACCUMULATOR)
                BiConsumer<List<Integer>, List<Integer>> combiner = Collection::addAll;// (COMBINER)

                List<Integer> collect = IntStream.range(0, 10)
                                .collect(supplier, accumulator, combiner);

                System.out.println("collect = " + collect);

                // Running this code produces the following result.
                // collect = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

                // ### Collecting Primitive types in a StringBuffer --> Collectors.joining()
                // Collecting elements in a string buffer
                Supplier<StringBuffer> supplier1 = StringBuffer::new;
                ObjIntConsumer<StringBuffer> accumulator1 = StringBuffer::append;
                BiConsumer<StringBuffer, StringBuffer> combiner1 = StringBuffer::append;

                StringBuffer collect1 = IntStream.range(0, 10)
                                .collect(null, null, null);

                // Running this code produces the following result.
                // collect = 0123456789

                // ### (FINISHER) Using a Finisher to Post-Process --> Collectors.joining()
                // The code above creates the collection then calles the .toString() method,
                // Collectors.joining() directly produces a string.

                // (FINISHER) --> the fourth component uses an instance of Function that takes
                // the container in which
                // the elements are accumulated and transforms it to something else.
                Function<StringBuffer, String> finisher = stringBuffer -> stringBuffer.toString();
                // Most finishers use the identity function. This is the case with toList(),
                // toSet(), groupingBy and toMap()

                // In all other other cases, the mutable container used internally by the
                // collector becomes an intermediate container that maps the data to an
                // immutable object ( that can be another container )

                // ## (ACCUMULATOR + FINISHER) method --> .collectingAndThen( ACCUMULATOR,
                // FINISHER)
                // An improved ( readability ) version uses .collectingAndThen() method.
                // the method takes a collector and then a finisher.
                Collection<String> strings = List.of("two", "three", "four", "five", "six", "seven", "eight", "nine",
                                "ten", "eleven", "twelve");

                Map<Integer, Long> histogram = strings.stream()
                                .collect(
                                                Collectors.groupingBy(
                                                                String::length,
                                                                Collectors.counting())); // step 1: build a histogram of
                                                                                         // type Map<Integer, Long>

                Map.Entry<Integer, Long> maxValue = histogram.entrySet().stream()
                                .max(Map.Entry.comparingByValue())
                                .orElseThrow(); // Extract maximum value of the histogram comparing key-value paris by
                                                // value.

                // The second step is in fact a transformation of map to another key/value pair
                // Datastructure
                // and can be modeled using the following function
                Function<Map<Integer, Long>, Map.Entry<Integer, Long>> finisherMap = map -> map.entrySet().stream()
                                .max(Map.Entry.comparingByValue())
                                .orElseThrow();
                // The function:
                // - extract a key-value pair from the histogram map. ( takes an instance of a
                // map<k.v>)
                // - return a key-value pair from that map ( returns instance of Map.Entry )

                // So the pattern would become....
                // (using .collectingAndThen)

                Collection<String> stringsWFinisher = List.of("two", "three", "four", "five", "six", "seven", "eight",
                                "nine",
                                "ten", "eleven", "twelve");

                Function<Map<Integer, Long>, Map.Entry<Integer, Long>> finisherMap2 = map -> map.entrySet().stream()
                                .max(Map.Entry.comparingByValue())
                                .orElseThrow();
                Map.Entry<Integer, Long> maxValueWFinisher = stringsWFinisher.stream()
                                .collect(

                                                Collectors.collectingAndThen(
                                                                Collectors.groupingBy(
                                                                                String::length,
                                                                                Collectors.counting()),
                                                                finisherMap2));

                System.out.println("maxValue = " + maxValueWFinisher);
                // Using Collectors to extract the max value would allow you to use the result
                // as downstream collector
                // for another collector.
                // This enables you to combine more collectors

                // ### Understanding the Types of a Collector
                // ### Combining the result of two collectors with the Teeing ( . teeing())
                // Collector

                enum Color {
                        RED, BLUE, WHITE, YELLOW
                }

                enum Engine {
                        ELECTRIC, HYBRID, GAS
                }

                enum Drive {
                        WD2, WD4
                }

                interface Vehicle {
                }

                record Car(Color color, Engine engine, Drive drive, int passengers) implements Vehicle {
                }

                record Truck(Engine engine, Drive drive, int weight) implements Vehicle {
                }

                // Suppose you need to find all the electric cars...
                // Suppose you need to find all the electric trucks ...
                // then you need to merge them

                // What you need to do is,,,
                // 1. Filter the vehicles to get all the electric cars
                // 2. Filter the vehicles to get all the electric trucks
                // 3. Merge the results

                // You can use Collectors.teeing() factory method to do this.
                // it takes three arguments
                // 1. downstream collector
                // 2. downstream collector
                // 3. BiFunction used to merge the two containers created by the downstream
                // collector

        }
}