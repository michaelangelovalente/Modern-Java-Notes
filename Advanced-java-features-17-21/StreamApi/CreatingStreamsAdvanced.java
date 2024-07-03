import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreatingStreamsAdvanced {

    public static void main(String[] args) {

        // ##### Creating a Stream with the Builder Pattern
        // 2 Steps:
        // -- 1 Add elemetns to the stream will consume in the builder
        // -- 2
        Stream.Builder<String> builder = Stream.<String>builder();

        builder.add("one")
                .add("two")
                .add("three")
                .add("four");

        Stream<String> stream = builder.build();

        List<String> list = stream.collect(Collectors.toList());

        // ##### Creating a Stream from lines of a text file
        // for I/O stream. Streams in general implement Autoclosable, being that a
        // stream i a resource that can be closed.
        // We take advante og that to close the file once we are done reading it.
        /*
         * Path log = Path.of("/tmp/debug.log"); // path to file
         * // try-with-resources pattern will call the close() method of your stream.
         * Which
         * // will
         * // properly close the text file parsed
         * try (Stream<String> lines = Files.lines(log)) {
         * long warnings = lines
         * .filter(line -> line.contains("WARNING"))
         * .count();
         * System.out.println("Number of warnings = " + warnings);
         * } catch (IOException e) {
         * // do something w/ exc
         * }
         */
        System.out.println("######### Creating a Stream on a HTTP Source #########\n\n");
        // Creating a Stream on a HTTP Source
        URI uri = URI.create("https://www.gutenberg.org/files/98/98-0.txt");

        // The code to open create an HTTP request.
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).build();

        // Send the request
        HttpResponse<Stream<String>> r = null;
        try {
            r = client.send(request, HttpResponse.BodyHandlers.ofLines());
        } catch (Exception e) {
            // TODO: handle exception
        }

        List<String> lines = null;
        try (Stream<String> stream3 = r.body()) {
            lines = stream3
                    .dropWhile(line -> !line.equals("A TALE OF TWO CITIES"))
                    .takeWhile(line -> !line.equals("*** END OF THE PROJECT GUTENBERG EBOOK A TALE OF TWO CITIES ***"))
                    .collect(Collectors.toList());
        }

        System.out.println("# lines = " + lines.size());
        // System.out.println(lines);

    }
}