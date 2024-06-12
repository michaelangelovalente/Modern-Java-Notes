import java.util.List;

import javax.sql.rowset.Predicate;

public class Lambda {

    List<String> retainStringOfLength3(List<String> strings) {

        Predicate<String> predicate = s -> s.length() == 3;

        List<String> stringsOfLength3 = new Arraylist<>():q;

        for (String s : strings) {
            if (predicate.test(s)) {
                stringsOfLength3.add(s);
            }
        }

        return stringsOfLength3;
    }

    // example case of capturing in a lambda a local value
    int calculateTotalPrice(List<Product> products) {

        int totalPrice = 0;
        Consumer<Product> consumer = product -> totalPrice += product.getPrice();

        for (Product product : products) {
            consumer.accept(product);
        }

    }

    public static void main(String[] args) {

        // PREDICATE
        // Predicate<String> predicate =
        // (String s) -> {
        // return s.length() == 3;
        // }

        // CONSUMER
        Consumer<String> print = s -> System.out.println(s);
    }
}
