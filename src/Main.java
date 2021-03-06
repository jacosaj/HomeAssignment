import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please, enter any integer number from 5 to 20: ");
        int i = sc.nextInt();
        if (i < 5 || i > 20) {
            System.out.println("You entered wrong integer number");
            return;
        }

        List<String> randomWords = new ArrayList<>();

        int count = 0;
        while (count < i){
            String word;
            do {

                String randomWord = RandomWordReader.getRandomWord();
                JSONArray response = new JSONArray(randomWord);
                word = response.getJSONObject(0).getString("word");
            } while (randomWords.contains(word));
            randomWords.add(word);
            count++;
        }
        System.out.println("Those are your random words:");
        System.out.println(randomWords);
        System.out.println();


        for (String randomWord: randomWords){
            System.out.println("Recording info from: " + randomWord);
            String songFromWord = SongReader.getSongFromWord(randomWord);
            JSONObject songResponse = new JSONObject(songFromWord);
            JSONArray recordings = songResponse.getJSONArray("recordings");
            if (!recordings.isEmpty()) {
                JSONObject recording = recordings.getJSONObject(0);
                String artist = recording.getJSONArray("artist-credit").getJSONObject(0).getString("name");
                String album = recording.getJSONArray("releases").getJSONObject(0).getString("title");
                String title = recording.getString("title");
                System.out.println("Title: " + title);
                System.out.println("Album: " + album);
                System.out.println("Artist: " + artist);
            } else {
                System.out.println("No recording found!");
            }
            System.out.println();
        }
    }
}
