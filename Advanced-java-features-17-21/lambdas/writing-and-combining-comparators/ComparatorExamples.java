import java.util.*;
import java.util.function.*;

public class ComparatorExamples {

    class User {
        private String name;
        private String lastName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

    }

    public static void main(String[] args) {
        // Implementing a Comparator with a Lambda Expression
        // Comparator<Integer> comparator = (i1, i2) -> Integer.compare(i1, i2);
        Comparator<Integer> comparator = Integer::compare;

        // Using a Factory Method to Create a Comparator
        Comparator<String> comparator2 = (s1, s2) -> Integer.compare(s1.length(), s2.length());
        // rewriting the code above with composition
        Function<String, Integer> toLength = String::length;
        Comparator<String> comparator3 = (s1, s2) -> Integer.compare(toLength.apply(s1), toLength.apply(s2));

        // comparing() from Comparator interface
        Comparator<String> comparator4 = Comparator.comparing(String::length);
        List<String> t = Arrays.asList("1", "12", "123", "12345", "1234", "12", "12345679", "12345");

        // Chaining Comparators using .thenComparing()
        Comparator<User> byFirstName = Comparator.comparing(ComparatorExamples.User::getName);
        Comparator<User> byLastName = Comparator.comparing(ComparatorExamples.User::getLastName);

        Comparator<User> byFirstNameThenLastName = byFirstName.thenComparing(byLastName);
        // overloaded comparator equiv w/ version above

        Comparator<User> byFirstNameThenLastNameV2 = Comparator.comparing(User::getName).thenComparing(User::getLastName);
 
        /*------------------------------------------*/
        //Using natural order
        Comparator<String> byLengthThenAlphabetically = Comparator.comparing(String::length).thenComparing(naturalOrder())

        // Reversing a Comparator
        List<String> strings = Arrays.asList("one", "two", "three", "four", "five");
        string.sort(byLengthThenAlphabetically.reversed());
        System.out.println(strings);

        // Dealing with null values. Convention --> push null values at the end 
                        
        

    }
}