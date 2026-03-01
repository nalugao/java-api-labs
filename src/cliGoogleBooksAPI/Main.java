package cliGoogleBooksAPI;

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
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o livro que você procura: ");
        var titulo = sc.nextLine();

        URI uri = new URI("https://www.googleapis.com/books/v1/volumes?q=" + URLEncoder.encode(titulo, StandardCharsets.UTF_8.toString()));

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}