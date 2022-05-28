import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import static java.net.http.HttpResponse.BodyHandlers.ofString;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
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
    public static void main(String[] args) throws Exception {
        //Scanner sc = new Scanner(System.in);
        System.out.print("Please, enter any integer number from 5 to 20: ");
        int i = 6;//sc.nextInt();
        if (i>= 5 && i <=20) {
            System.out.println("You entered this integer number: " + i);
        } else {
            System.out.println("You entered wrong integer number");
            return;
        }

        List<String> randomWords = new ArrayList<>();

        int count = 0;
        while (count < i){
            String word;
            do {
                String randomWord = getRandomWord();
                JSONArray response = new JSONArray(randomWord);
                word = ((JSONObject)response.get(0)).getString("word");
            } while (randomWords.contains(word));
            randomWords.add(word);
            count++;
        }
        System.out.println(randomWords);
        for (String randomWord: randomWords){

        }
        System.out.println(getSongFromWord(randomWords.get(0)));

    }
}
