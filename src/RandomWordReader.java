import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import static java.net.http.HttpResponse.BodyHandlers.ofString;

public class RandomWordReader {
    private static final URI WORDS_URI =
            URI.create("https://random-words-api.vercel.app/word");
    public static String getRandomWord() throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(WORDS_URI)
                .GET()
                .build();

        return client.send(request, ofString())
                .body();
    }
}
