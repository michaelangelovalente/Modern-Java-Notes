import java.util.*;
import java.util.stream.*;

public class FunctionalJava {

    public static void main(String[] args) {

        // Imperative forloop
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }

        for (int i = 0; i <= 5; i++) {
            System.out.println(i);
        }

        // Functional forloop
        IntStream.range(0, 5)
                .forEach(System.out::println);

        IntStream.rangeClosed(0, 5)
                .forEach(System.out::println);

        // ####### Iterating with steps #####

        for (int i = 0; i < 15; i = i + 3) {

        }

    }
}
