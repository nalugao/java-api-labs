package cliViaCepAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write your CEP: ");
        String cep = "";

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        cep = sc.nextLine();

        if (cep.length() != 8) {
            throw new CepCharacterException("CEP must contain 8 digits");
        }

        try {

            URI address = new URI("http://viacep.com.br/ws/" + cep + "/json/");

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(address).
                    build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            FileWriter writer = new FileWriter("cep.json");

            CepRecord cepJson = gson.fromJson(json, CepRecord.class);

            writer.write(gson.toJson(cepJson));
            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
