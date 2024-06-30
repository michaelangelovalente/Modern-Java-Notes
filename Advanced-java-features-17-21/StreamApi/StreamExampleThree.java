import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExampleThree {

    public static void main(String[] args) {

        // ## Limiting and Skipping the elements of a stream
        List<Integer> ints = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        List<Integer> result = ints.stream()
                .skip(2)
                .limit(5)
                .collect(Collectors.toList());

        System.out.println("result = " + result);

        System.out.println("######### CONCATENATING STREAMS ##########");
        // ## Contenating Streams
        // you can use concat() if you only have to concantenate 2 stream, if you
        // you have more than 2 streams to join it would be advisable to use flatMap or
        // multimap

        // examples:
        List<Integer> list0 = List.of(1, 2, 3);
        List<Integer> list1 = List.of(4, 5, 6);
        List<Integer> list2 = List.of(7, 8, 9);

        // JOINING two streams
        // 1st pattern: concat
        List<Integer> concat = Stream.concat(list0.stream(), list1.stream())
                .collect(Collectors.toList());

        // 2nd pattern: flatMap
        List<Integer> flatMap = Stream.of(list0.stream(), list1.stream(), list2.stream())
                .flatMap(Function.identity())
                .collect(Collectors.toList());

        System.out.println("concat = " + concat);
        System.out.println("flatMap = " + flatMap);

        System.out.println("######## DEBUGGING STREAMS ##############");
        List<String> str = List.of("one", "two", "three", "four");
        List<String> resultDebug = str.stream()
                .peek(s1 -> System.out.println("Starting with = " + s1))
                .filter(s -> s.startsWith("t"))
                .peek(s -> System.out.println("Filtered = " + s))
                .map(String::toUpperCase)
                .peek(s -> System.out.println("Mapped = " + s))
                .collect(Collectors.toList());

        System.out.println("result = " + resultDebug);

    }
}