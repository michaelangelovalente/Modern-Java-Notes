import java.util.Collection;
import java.util.stream.Stream;

public class FindingCaracteristicsInStreams {
    // https://dev.java/learn/api/streams/characteristics/
    public static void main(String[] args) {

        // SPLITERATOR
        // Stream API relies on the SPLITERATOR interface, which have the main
        // charatacteristics: ORDERED, DISTINCT, NONNULL, SORTED, SIZED, SUBSIZED

        // ORDERED...

        // SORTED
        // some intermediate operation clear the SORTED characteristic
        Collection<String> stringCollection = List.of("one", "two", "two", "three", "four", "five");

        // sorted
        Stream<String> strings = stringCollection.stream().sorted();
        // sorted
        Stream<String> filteredStrings = strings.filter(s -> s.length() < 5);

        // not SORTED --> lost/clear characteristic
        Stream<Integer> lengths = filteredStrings.map(String::length);

        // DISTINCT
        // Kept when filtering. Lost when Mapping or Flatmapping.
        Collection<String> stringCollection2 = List.of("one", "two", "two", "three", "four", "five");
        // distinct
        Stream<String> strings2 = stringCollection2.stream().distinct();
        // distinct
        Stream<String> filteredStrings2 = strings2.filter(s -> s.length());
        // not DISTINCT. map --> causes the stream to lose the charact.
        Stream<Integer> lengths2 = filteredStrings2.map(String::length);

        // NON-NULL
        // Structures from Collectionn Framework do not accept null values
        // e.g. ArrayDeque and Concurrent Structures
        // ....

        // SIZED AMD SUBSIZED STREAM
        // SIZED
        // Sized streams are important when using parallel streams.
        // Streams created from Collection have the size() method, so getting the number
        // is easier
        // on the other hand something from
        // Files.lines(path) makes it more difficult to obtain this information.

        // SUBSIZED
        // information about stream splitted during
        // parallel computation
        // Streams where splitted datasource have a predictable size
        // e.g. ArrayList
        // On the other hand HashSet you cant tell how many elements there are
        // when splitting it. so HashSet is considered sized, since the original
        // structure's size is predictable, but not SUBSIZED, since there is
        // no way to tell the number of elements for each size.

        // MAPPING and SORTING preserves SIZED and SUBSIZED
        // FLATMAPPING, FILTERING and calling distinct() erases these chatacterstics.

        // CONLCUSION: HAVING SUBSIZED and SIZED streams are better for parallel
        // computations
    }
}
