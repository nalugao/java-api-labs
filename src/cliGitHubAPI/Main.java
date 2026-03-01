package cliGitHubAPI;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Who are you looking for?");
        String username = sc.nextLine();

        URI uri = new URI("https://api.github.com/users/" + URLEncoder.encode(username, StandardCharsets.UTF_8.toString()));
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 404) {
                throw new GitHubUserNotFoundException("User not found on GitHub.");
            }

            String responseJson = response.body();

            Gson gson = new Gson();
            UsernameJson text = gson.fromJson(responseJson, UsernameJson.class);
            System.out.println(text);

        } catch (GitHubUserNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("End of the program.");
        }


    }
}
