import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Seja bem-vindo(a) ao conversor de moedas, escolha uma das opções abaixo para continuar:");
            System.out.println("1) Real --> Dólar");
            System.out.println("2) Dólar --> Real");
            System.out.println("3) Real --> Euro");
            System.out.println("4) Euro --> Real");
            System.out.println("5) Real --> Libra");
            System.out.println("6) Libra --> Real");
            System.out.println("7) Sair");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1:
                    convertCurrency("BRL", "USD");
                    break;
                case 2:
                    convertCurrency("USD", "BRL");
                    break;
                case 3:
                    convertCurrency("BRL", "EUR");
                    break;
                case 4:
                    convertCurrency("EUR", "BRL");
                    break;
                case 5:
                    convertCurrency("BRL", "GBP");
                    break;
                case 6:
                    convertCurrency("GBP", "BRL");
                    break;
                case 7:
                    System.out.println("Saindo do programa...");
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }

    private static void convertCurrency(String fromCurrency, String toCurrency) {
        try {
            Map<String, Object> exchangeRates = ExchangeRateAPI.getExchangeRates(fromCurrency);
            Map<String, Double> conversionRates = (Map<String, Double>) exchangeRates.get("conversion_rates");
            Double exchangeRate = conversionRates.get(toCurrency);
            System.out.printf("Digite o valor em %s: ", fromCurrency);
            Scanner scanner = new Scanner(System.in);
            double amount = scanner.nextDouble();
            double convertedAmount = amount * exchangeRate;
            System.out.printf("%.2f %s equivalem a %.2f %s.%n", amount, fromCurrency, convertedAmount, toCurrency);
        } catch (Exception e) {
            System.out.println("Erro ao converter moeda: " + e.getMessage());
        }
    }
}
