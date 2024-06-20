import java.util.*;

public class SimpleLoops {
   // Imperative forloop
    for(int i = 0; i < 5; i++){
        System.out.println(i);
    }

    for(int i = 0; i <= 5; i++){
        System.out.println(i);
    }

    // Functional forloop
    IntStream.range(0,5)
    .forEach(System.out::println);

    IntStream.rangeClosed(0,5)
    .forEach(System.out::println);


    // iterate() --> used when some values are skipped
    
    
}
