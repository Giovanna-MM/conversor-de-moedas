# Currency Converter

Este é um projeto simples de conversor de moedas em Java que utiliza a API ExchangeRate-API para obter as taxas de câmbio atuais. O usuário pode escolher converter entre diferentes moedas.

## Funcionalidades

- Converter Real (BRL) para Dólar (USD)
- Converter Dólar (USD) para Real (BRL)
- Converter Real (BRL) para Euro (EUR)
- Converter Euro (EUR) para Real (BRL)
- Converter Real (BRL) para Libra (GBP)
- Converter Libra (GBP) para Real (BRL)

## Requisitos

- Java 11 ou superior
- Biblioteca Gson

## Configuração

1. Faça o download da biblioteca Gson:
   - [Gson](https://github.com/google/gson)

2. Adicione a biblioteca Gson ao seu projeto no IntelliJ IDEA:
   - Clique com o botão direito no projeto, vá em "Open Module Settings".
   - Vá para "Libraries", clique no "+" e adicione o arquivo `gson-2.11.0.jar`.

## Execução

1. Compile e execute o projeto no IntelliJ IDEA.
2. Escolha uma das opções de conversão no menu.
3. Insira o valor que deseja converter.
4. O programa exibirá o valor convertido.

## Código

### ExchangeRateAPI.java

```java
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
