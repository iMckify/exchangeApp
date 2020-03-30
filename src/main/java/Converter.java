import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;

public class Converter {

    public LinkedHashMap<String, BigDecimal> read(String path) {
        LinkedHashMap<String, BigDecimal> currencies = new LinkedHashMap<String, BigDecimal>();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(path));

            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",", 2);

                String name = data[0];
                BigDecimal exchangeRate = parseRate(data[1]);

                currencies.put(name, exchangeRate);
            }
            csvReader.close();
            return currencies;
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File is not found");
        } catch (IOException ex) {
            throw new IllegalArgumentException("File format is not supported");
        } catch (Exception exe) {
            throw new IllegalArgumentException("Read failed");
        }
    }

    public BigDecimal parseRate(String rate) {
        try {
            return new BigDecimal(rate.replace("\"", "").replace(",", "."));
        } catch (Exception e) {
            throw new IllegalArgumentException("Number has not been parsed");
        }
    }

    public BigDecimal convert(LinkedHashMap<String, BigDecimal> currencies, double quantity, String from, String to) {
        BigDecimal fromValue = currencies.get(from);
        BigDecimal toValue = currencies.get(to);

        if(isValid(quantity) && isValid(fromValue) && isValid(toValue)) {
            return BigDecimal.valueOf(quantity).multiply(fromValue).divide(toValue, 18, RoundingMode.FLOOR);
        } else {
            throw new IllegalArgumentException("Input is not valid");
        }
    }

    private boolean isValid(Number x) {
        if(x != null && !x.equals(0.0)) {
            return true;
        } else {
            return false;
        }
    }
}
