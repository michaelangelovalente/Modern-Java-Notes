import java.util.function.*;
import java.util.*;

public class MethodRef {

    public static void main(String[] args) {

        // Method ref
        // Consumer<String> printer = s -> System.out.println(s);
        Consumer<String> printer = System.out::println;

        // Writing Static Method References
        // DoubleUnaryOperator sqrt = a -> Math.sqrt(a);
        DoubleUnaryOperator sqrt = Math::sqrt;

        // Static Method ref /w multiple arguments
        // IntBinaryOperator max = (a, b) -> Integer.max(a,b);
        IntBinaryOperator max = Integer::max;
        // System.out.println(max.applyAsInt(12, 5909));

        // ------ Writing Unbound Method References -----
        // Methods that do not take any argument
        // Function<String, Integer> toLength = s -> s.length();
        Function<String, Integer> toLength = String::length;
        // System.out.println(toLength.apply("Ciao"));

        // BiFunction<String, String, Integer> indexOf = (s, w) -> s.indexOf(w);
        BiFunction<String, String, Integer> idxOf = String::indexOf;
        // System.out.println(idxOf.apply("12345", "2"));

        // ----- Writing Constructor Method References -----
        // Supplier<List<String>> newListOfStrings = () -> new ArrayList<>();
        Supplier<List<String>> newListOfStrings = ArrayList<String>::new;

    }
}
