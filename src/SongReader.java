import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;

import static java.net.http.HttpResponse.BodyHandlers.ofString;

public class SongReader {
    public static String getSongFromWord(List<String> words) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        StringBuilder combinedWords = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            if (i == words.size() - 1){
                combinedWords.append(words.get(i));
            } else {
                combinedWords.append(words.get(i)).append(" OR ");
            }
        }
        URI songUri = URI.create("https://musicbrainz.org/ws/2/recording/?fmt=json&query=recording:" + combinedWords);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(songUri)
                .GET()
                .build();

        return client.send(request, ofString())
                .body();
    }
}
