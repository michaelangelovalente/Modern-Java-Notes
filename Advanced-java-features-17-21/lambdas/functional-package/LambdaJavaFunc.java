import java.util.*;
import java.util.function.*;

public class LambdaJavaFunc {

    public static void main(String[] args) {

        // Creating / Providing Objects with Supplier <T>
        Supplier<String> supplier = () -> "Hello Duke!";

        Random random = new Random(314L);
        Supplier<Integer> newRandom = () -> random.nextInt(10);

        for (int i = 0; i < 5; i++) {
            System.out.println(newRandom.get() + " ");
        }

        // Implementing and Using Consumers
        Consumer<Integer> printer = s -> System.out.println(s);

        for (int i = 0; i < 5; i++) {
            int nextRand = newRandom.get();
            printer.accept(nextRand);
        }

        // Consuming Two elements with a BiConsumer
        BiConsumer<Random, Integer> randomNumberPrinter = (random2, number) -> {
            for (int i = 0; i < number; i++) {
                System.out.println("next random = " + random2.nextInt());
            }
        };

        randomNumberPrinter.accept(new Random(314L), 5);

        // Passing a Consumer to an Iterable
        List<String> strings = List.of("Abc", "efg", "asdfds");
        Consumer<String> printer2 = s -> System.out.println(s);
        strings.forEach(printer2);

        // Testing objects with predicates
        Predicate<String> length3 = s -> s.length() == 3;
        String word = "123";
        boolean isOfLength3 = length3.test(word);

        String.format("Is of length 3? %b", isOfLength3);

        // Testing two elemnts with a BiPredicate
        Predicate<String, Integer> isOfLength = (word, length) -> word.length() == length;
        String word2 = "abc";
        int len = 3;
        boolean isWordOfLength3 = isOfLength.test(word2, len);

        // Passing a predicate to a Collection
        List<String> immutableString = List.of("one", "two", "three", "four", "five");

        List<String> strings2 = new ArrayList<>(immutableString);

        Predicate<String> isEvenLength = s -> s.length() % 2 == 0;
        strings2.removeIf(isEvenLength);

    }
}
