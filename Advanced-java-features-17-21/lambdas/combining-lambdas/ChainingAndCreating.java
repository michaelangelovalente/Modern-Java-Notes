import java.util.function.*;
import java.util.logging.Logger;
import java.util.*;

public class ChainingAndCreating {

    public static void main(String[] args) {
        // non-null, non-empty and shorter than 5 characters.
        Predicate<String> p = s -> (s != null) && !s.isEmpty() && s.length() < 5;
        // equivalent
        Predicate<String> nonNull = s -> s != null;
        Predicate<String> nonEmpty = s -> !s.isEmpty();
        Predicate<String> shorterThan5 = s -> s.length() < 5;

        Predicate<String> pComb = nonNull.and(nonEmpty).and(shorterThan5);

        // System.out.println(pComb.test("Ciao"));
        // ---------------------------------------
        Predicate<String> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNullOrEmpty = isNull.or(isEmpty);
        Predicate<String> isNotNullOrEmpty = isNullOrEmpty.negate();
        Predicate<String> shorterThan5V2 = s -> s.length() < 5;
        Predicate<String> pComb2 = isNotNullOrEmpty.and(shorterThan5V2);
        // System.out.println(pComb2.test("Ciaooooo"));

        // Creating Predicates with Factory Methods
        Predicate<String> isEqualDuke = Predicate.isEqual("Duke");
        Predicate<Collection<String>> collectionIsEmpty = Collection::isEmpty;
        Predicate<Collection<String>> collectionIsNotEmpty = Predicate.not(collectionIsEmpty);

        // Chaining Consumers with default Methods
        Logger logger = Logger.getLogger("MyApplicationLogger");
        Consumer<String> log = message -> logger.info(message);
        Consumer<String> print = message -> System.out.println(message);
        Consumer<String> longAndPrint = log.andThen(print);

        // Creating an Identity Function
        // Function<T,R> identity()
        Function<String, String> id = Function.identity();

    }

}