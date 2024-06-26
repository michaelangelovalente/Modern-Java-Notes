import java.util.*;

public class MapFactoryMethodsExamples {

    public static void main(String[] args) {
        Map<Integer, String> map = Map.ofEntries(
                Map.entry(1, "one"),
                Map.entry(2, "two"),
                Map.entry(3, "three"));

    }
}
