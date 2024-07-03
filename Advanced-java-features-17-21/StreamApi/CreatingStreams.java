import java.util.stream.*;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;

public class CreatingStreams {

    public static void main(String[] args) {
        // https://dev.java/learn/api/streams/creating/#from-a-vararg-or-array
        // #### First factory method patterns for streams ####

        // Creating a stream from a Collection or an Iterator
        // ...

        // Creating a Stream from a Vararg or an Array
        // ...

        // ### Creating a Stream from a supplier
        // using Stream.generate( Supp )... Generates an infinite stream. You can cut it
        // using limit(). Useful for constant streams
        Stream<String> generated = Stream.generate(() -> "+");
        List<String> strings = generated.limit(20L).collect(Collectors.toList());

        System.out.println("strings = " + strings);

        // ### Creating a stream from a UnaryOperator and Seed
        // iterate() usufel for stream generation that needs varying values
        Stream<String> iterated = Stream.iterate("+", s -> s + "+");
        iterated.limit(5L).forEach(System.out::println);

        // overloaded version of iterate()
        Stream<String> iteratedOv = Stream.iterate("+", s -> s.length() <= 5, s -> s + "+");
        iteratedOv.forEach(System.out::println);

        // ## Creating a Stream from a Range of Numbers
        String[] letters = { "A", "B", "C", "D" };
        List<String> listLetters = IntStream.range(0, 10)
                .mapToObj(idx -> letters[idx % letters.length])
                .collect(Collectors.toList());

        // ## Creating a STream of Random Numbers
        Random random = new Random(314L);
        List<Integer> randomInts = random.ints(10, 1, 5)
                .boxed()
                .collect(Collectors.toList());
        System.out.println("randomInts = " + randomInts);

        // example stream that generates a stream of random booleans
        List<Boolean> booleans = random.doubles(1_000, 0d, 1d)
                .mapToObj(rand -> rand <= 0.8) // 80% prob to be true
                .collect(Collectors.toList());

        // Count the number of true in the list
        long numberOfTrue = booleans.stream()
                .filter(b -> b)
                .count();

        System.out.println("numberOfTrue = " + numberOfTrue);

        // example stream that generates random letters between letter A 50%, B 30%, C
        // 10% and D 10%
        List<String> lettersRandom = random.doubles(1_000, 0d, 1d)
                .mapToObj(rand -> rand < 0.5 ? "A" : rand < 0.8 ? "B" : rand < 0.9 ? "C" : "D")
                .collect(Collectors.toList());
        // COOL WAY TO USE TERNARY OPERATORS!
        // List<String> letters =
        // random.doubles(1_000, 0d, 1d)
        // .mapToObj(rand ->
        // rand < 0.5 ? "A" : // 50% of A
        // rand < 0.8 ? "B" : // 30% of B
        // rand < 0.9 ? "C" : // 10% of C
        // "D") // 10% of D
        // .collect(Collectors.toList());

        System.out.println(lettersRandom);
        Map<String, Long> map = lettersRandom.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);

        // ## Creating a Stream from the characters of a String
        String sentence = "Hello Duke";
        List<String> lettersSentence = sentence.chars()
                .mapToObj(codePoint -> (char) codePoint)
                .map(Object::toString)
                .collect(Collectors.toList());
        System.out.println("letters = " + lettersSentence);

        // ## Creating a stream from a regular expression
        String sentence2 = "For there is good news yet to hear and fine things to be seen";

        // using String split()
        // String[] el = sentence2.split(" ");
        // Stream<String> stream = Arrays.stream(el);

        // using Pattern.compile() --> improved solution. There is no overhead since you
        // do not need to create an array.
        Pattern pattern = Pattern.compile(" ");
        Stream<String> stream2 = pattern.splitAsStream(sentence2);
        List<String> words = stream2.collect(Collectors.toList());
        System.out.println("words = " + words);

    }
}