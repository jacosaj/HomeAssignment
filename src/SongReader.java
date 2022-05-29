import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import static java.net.http.HttpResponse.BodyHandlers.ofString;

public class SongReader {
    public static String getSongFromWord(String word) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        URI songUri = URI.create("https://musicbrainz.org/ws/2/recording/?fmt=json&query=recording:" + word);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(songUri)
                .GET()
                .build();

        return client.send(request, ofString())
                .body();
    }
}
