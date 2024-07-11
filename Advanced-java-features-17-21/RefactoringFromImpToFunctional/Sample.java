import java.nio.file.*;

public class Sample {

    public static void main(String[] args) {
        // imperative
        try {
            final var filePath = "./Sample.java";
            final var wordOfInterest = "public";

            try (var reader = Files.newBufferedReader(Path.of(filePath))) {
                String line = "";
                long count = 0;

                while ((line = reader.readLine()) != null) {
                    if (line.contains(wordOfInterest)) {
                        count++;
                    }
                }

                System.out.println(String.format("Founf %d lines with the word %s", count, wordOfInterest));
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // functional
        try {
            final var filePath = "./Sample.java";
            final var wordOfInterest = "public";

            try (var stream = Files.lines(Path.of(filePath))) {
                long count = stream.filter(l -> l.contains(wordOfInterest)).count();

                System.out.println(String.format("Found %d lines with the word %s", count, wordOfInterest));
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

}