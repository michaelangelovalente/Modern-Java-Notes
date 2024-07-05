import java.util.List;
import java.util.function.BinaryOperator;

public class ReducingAStream {
    public static void main(String[] args) {

        // ### Using a Binary Operator to reduce a stream

        // imperative
        List<Integer> ints = List.of(3, 6, 2, 1);
        int sum = ints.get(0);
        for (int i = 1; i < ints.size(); i++) {
            sum += ints.get(i);
        }
        System.out.println("sum = " + sum);

        // sum of elements using a BinaryOperator
        BinaryOperator<Integer> sumB = (a, b) -> a + b;
        int result = ints.get(0);
        for (int i = 1; i < ints.size(); i++) {
            result = sumB.apply(result, ints.get(i));
        }
        System.out.println(result);

        // Compute MAX using a BINARY OPERATOR
        BinaryOperator<Integer> max = (a, b) -> a > b ? a : b;
        int resultMax = ints.get(0);
        for (int i = 0; i < ints.size(); i++) {
            result = max.apply(result, ints.get(i));
        }

        System.out.println("max = " + resultMax);

        // The binary operator is how the reduce works "logically"
    }

}