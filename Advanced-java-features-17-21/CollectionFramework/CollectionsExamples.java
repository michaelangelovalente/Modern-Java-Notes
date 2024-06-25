import java.util.*;
import java.util.function.*;

public class CollectionsExamples {

    public static void main(String[] args) {
        // ##### Storing elements in a collection
        Collection<String> strings = List.of("one", "two");

        String[] largerTab = { "three", "three", "three", "I", "was", "there" };
        System.out.println("largerTab = " + Arrays.toString(largerTab));

        String[] result = strings.toArray(largerTab);
        System.out.println("result = " + Arrays.toString(result));

        // #### Filtering out elements of a Collection with a Predicate
        Predicate<String> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNullOrEmpty = isNull.or(isEmpty);

        Collection<String> strings2 = new ArrayList<>();
        strings2.add(null);
        strings2.add("");
        strings2.add("one");
        strings2.add("two");

        strings2.add("");
        strings2.add("three");
        strings2.add(null);

        System.out.println("strings = " + strings2);
        strings2.removeIf(isNullOrEmpty);
        System.out.println("filtered strings = " + strings2);

    }
}