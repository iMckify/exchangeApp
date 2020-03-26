import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;

public class Converter {

    private static LinkedHashMap<String, BigDecimal> currencies = new LinkedHashMap<String, BigDecimal>();

    public static void main(String[] args) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("./data.csv"));

        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",", 2);
            String name = data[0];
            BigDecimal exchangeRate = new BigDecimal(data[1].replace("\"", "").replace(",","."));

            currencies.put(name, exchangeRate);
        }
        csvReader.close();

        System.out.println(currencies.toString());

        BigDecimal answer = convert(1, "EUR", "USD");
        System.out.println("Answer: " + answer + " ");
    }

    public static BigDecimal convert(int quantity, String from, String to) {
        return BigDecimal.valueOf(quantity).multiply(currencies.get(from)).divide(currencies.get(to), 18, RoundingMode.FLOOR);
    }
}
