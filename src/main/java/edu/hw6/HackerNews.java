package edu.hw6;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {

    private HackerNews() {
    }

    private static final String BASE_URL = "https://hacker-news.firebaseio.com/v0/";
    private static final String TOP_STORIES_URL = BASE_URL + "topstories.json";
    private static final String ITEM_URL = BASE_URL + "item/";
    private static final Duration TIMEOUT = Duration.ofSeconds(5);

    public static long[] hackerNewsTopStories() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(TOP_STORIES_URL))
                .GET().timeout(TIMEOUT)
                .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            client.close();
            String[] data = response.body().substring(1, response.body().length() - 1).split(",");
            long[] convertedResponse = new long[data.length];
            for (int i = 0; i < data.length; i++) {
                convertedResponse[i] = Long.parseLong(data[i]);
            }
            return convertedResponse;
        } catch (IOException | InterruptedException | URISyntaxException e) {
            return new long[0];
        }
    }

    public static String news(long id) {
        String newsURL = ITEM_URL + id + ".json";
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(newsURL))
                .GET().timeout(TIMEOUT)
                .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            client.close();
            String data = response.body();
            Pattern pattern = Pattern.compile("\"title\":\"(.*?)\"");
            Matcher matcher = pattern.matcher(data);
            if (matcher.find()) {
                String answer = matcher.group().split(":")[1];
                return answer.substring(1, answer.length() - 1);
            }
            return null;
        } catch (IOException | InterruptedException | URISyntaxException e) {
            return null;
        }
    }
}
