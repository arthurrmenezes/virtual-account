package services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ConsumeApi {
	
	public String useApi() {
		
		HttpClient client = HttpClient.newHttpClient();
		
		HttpRequest request = HttpRequest.newBuilder()
		         .uri(URI.create("https://economia.awesomeapi.com.br/last/USD-BRL"))
		         .build();

		HttpResponse<String> response = null;
		try {
			response = client
				     .send(request, BodyHandlers.ofString());
		} catch (IOException | InterruptedException exception) {
			System.out.println("Error: " + exception.getMessage());
		}
		
		return response.body();
	}

}
