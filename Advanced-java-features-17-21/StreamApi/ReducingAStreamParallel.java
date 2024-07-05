import java.util.List;
import java.util.function.*;

public class ReducingAStreamParallel {
    public static void main(String[] args) {
        // https://dev.java/learn/api/streams/reducing/

        List<Integer> ints = List.of(3, 6, 2, 1);
        BinaryOperator<Integer> sum = (a, b) -> a + b;

        // This is how logically parallel streams work.
        int result1 = reduce(ints.subList(0, 2), sum);
        int result2 = reduce(ints.subList(2, 4), sum);

        int result = sum.apply(result1, result2);
        System.out.println("sum = " + result);

    }

    // ####### Choosing a binary operator that can be used in parallel
    // reduce method that takes a binary operator and reduces a list of integers
    static int reduce(List<Integer> ints, BinaryOperator<Integer> sum) {
        int result = ints.get(0);
        for (int i = 1; i < ints.size(); i++) {
            result = sum.apply(result, ints.get(i));
        }
        return result;
    }
}
