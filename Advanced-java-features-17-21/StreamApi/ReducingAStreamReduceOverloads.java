import java.util.function.*;
import java.util.stream.*;
import java.util.*;

public class ReducingAStreamReduceOverloads {

    public static void main(String[] args) {
        // ## Reducing with an Identity element

        // this works well even if the list is empty.
        // overload v1
        System.out.println("### overload v1 reduce #####");
        List<Integer> ints = List.of(3, 6, 2, 1);
        BinaryOperator<Integer> sum = (a, b) -> a + b;
        int identity = 0;

        int result = identity;
        for (int i : ints) {
            result = sum.apply(result, i);
        }

        System.out.println("Ov1: sum = " + result);

        // Using the stream api with 1st arg (identity)
        Stream<Integer> intsStr = Stream.of(0, 0, 0, 0);
        int sumStr = intsStr.reduce(0, (a, b) -> a + b);

        System.out.println("Ov1 (stream) sum = " + sumStr);

        // overload v2
        System.out.println("### overload v2 reduce #####");
        Stream<Integer> intsStrm2 = Stream.of(2, 8, 1, 5, 3);
        Optional<Integer> optionalStrm2 = intsStrm2.reduce((i1, i2) -> i1 > i2 ? i1 : i2);
        if (optionalStrm2.isPresent()) {
            System.out.println("Ov2 result = " + optionalStrm2.orElseThrow());
        } else {
            System.out.println("No result could be computed");
        }

        System.out.println("############## overload v3 reduce #################");
        // ## Fusing Mapping and Reduction in one Method
        // overload v3
        // <U> U reduce(U identity,
        // BiFunction<U, ? super T, U> accumulator,
        // BinaryOperator<U> combiner);
        Stream<String> stringsOv3 = Stream.of("one", "two", "three", "four");
        BiFunction<Integer, String, Integer> accumulatorOv3 = (partialReduction, element) -> partialReduction
                + element.length();
        BinaryOperator<Integer> combinerOv3 = (length1, length2) -> length1 + length2;
        int resultOv3 = stringsOv3.reduce(0, accumulatorOv3, combinerOv3);
        System.out.println("Reduce res1=" + resultOv3);

        // You can instead use a mapper for the accumulator
        Function<String, Integer> mapper = String::length;
        BiFunction<Integer, String, Integer> accumulatorOv3Improved = (partialReduction, element) -> partialReduction
                + mapper.apply(element);
        resultOv3 = stringsOv3.reduce(0, accumulatorOv3Improved, combinerOv3);
        System.out.println("Reduce res2=" + resultOv3);
    }
}