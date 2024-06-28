import java.util.*;
import java.util.stream.Collectors;

public class StreamExampleOne {

    public static void main(String[] args) {
        List<String> strings = List.of("one", "two", "three", "four");
        var map = strings.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));

        map.forEach((k, v) -> System.out.println(k + " :: " + v));

    }
}