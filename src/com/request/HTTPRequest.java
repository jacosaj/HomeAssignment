package com.request;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import static java.net.http.HttpResponse.BodyHandlers.ofString;

public class HTTPRequest {
        private static final URI WORDS_URI =
                URI.create("https://random-words-api.vercel.app/word");

        public static void main(String[] args) throws Exception {
            System.out.println(getRandomWords());
        }

        private static String getRandomWords() throws Exception {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(WORDS_URI)
                    .GET()
                    .build();

            return client.send(request, ofString())
                    .body();
        }
    }
