import java.util.*;

public class CreatingAndProcessDatawithCollFactoryMethods {

    public static void main(String[] args) {

        // ############## Wrapping a collection in an immutable collection

        // the unmodifiable conllection is backed by the original, this means that it
        // can be modified by modifying the original collection as shown below
        List<String> strings = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4"));
        List<String> immutableStrings = Collections.unmodifiableList(strings);
        System.out.println(immutableStrings);
        System.out.println("----------");
        strings.add("555");
        System.out.println(immutableStrings);

    }
}