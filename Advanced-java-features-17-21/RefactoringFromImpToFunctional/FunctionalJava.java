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
        // imperative
        for (int i = 0; i < 15; i = i + 3) {
            System.out.println(i);
        }

        // functional
        IntStream.iterate(0, i -> i < 15, i -> i + 3)
                .forEach(System.out::println);

        // #### Unbounded Iteration with a break ######
        IntStream.iterate(0, i -> i + 3)
                .takeWhile(i -> i <= 20)
                .forEach(System.out::println);

        // ######## Converting foreach with if ######

        // Iteration example using foreach
        List<String> names = List.of("Jack", "Paula", "Kate", "Peter");
        for (String name : names) {
            System.out.println(name);
        }

        // functional style
        names.forEach(n -> System.out.println(n));

        // ##### Picking select elemetns with if

        // Imperative
        for (String name : names) {
            if (name.length() == 4) {
                System.out.println(name);
            }
        }

        // functional
        names.stream()
                .filter(n -> n.length() == 4)
                .forEach(n -> System.out.println(n));

        // ## Transforming while iterating
        // Imperative
        for (String name : names) {
            System.out.println(name.toUpperCase());
        }

        // Functional
        names.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        // ## Picking Elements to Transform
        // Imperative
        for (String name : names) {
            if (name.length() == 4) {
                System.out.println(name.toUpperCase());
            }
        }

        // Functional
        names.stream()
                .filter(n -> n.length() == 4)
                .map(String::toUpperCase)
                .forEach(System.out::println);

    }
}
