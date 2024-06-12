import java.util.*;
import java.util.function.*;

public class LambdasPrecateAndCollections {

    public static void main(String[] args) {

        // Passing a predicate to a Collection
        List<String> immutableString = List.of("one", "two", "three", "four", "five");

        List<String> strings = new ArrayList<>(immutableString);

        Predicate<String> isEvenLength = s -> s.length() % 2 == 0;
        strings.removeIf(isEvenLength);
        System.out.println(strings);
        System.out.println("--------------");

        // T,U .... args.
        // R return type
        // Mapping Objects to Other Objects with Function<T, R>
        // takes in a string and returns the length of that string
        Function<String, Integer> toLength = s -> s.length();
        String word = "12345";
        int length = toLength.apply(word);
        System.out.println(length);
        System.out.println("--------------");

        // Mapping Two Elements with a BiFunction<T, U, R>
        BiFunction<String, String, Integer> findWordInSentence = (word2, sentence2) -> sentence2.indexOf(word2);
        String sentenceTest = "The quick brown fox jumped over the lazy fox";
        String wordTest = "quick";
        var res = findWordInSentence.apply(wordTest, sentenceTest);
        System.out.println(res);
        System.out.println("---------------");

        //

    }
}
