import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ExchangeRateAPI {
    private static final String API_KEY = "3daa5f4774adbbb37e6b9971";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static Map<String, Object> getExchangeRates(String baseCurrency) throws Exception {
        String url = BASE_URL + API_KEY + "/latest/" + baseCurrency;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            Gson gson = new Gson();
            return gson.fromJson(response.body(), Map.class);
        } else {
            throw new Exception("Erro ao obter taxa de câmbio: " + response.statusCode());
        }
    }
}
