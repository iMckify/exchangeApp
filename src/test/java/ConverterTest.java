import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(JUnit4.class)
public class ConverterTest {

    private Converter converter;

    @Before
    public void setup() {
        converter = new Converter();
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    //===============  read  =================================
    @Test
    public void read_result() {
        String filePath = "./data.csv";

        LinkedHashMap<String, BigDecimal> expected = new LinkedHashMap<String, BigDecimal>() {{
            put("EUR", new BigDecimal("1"));
            put("USD", new BigDecimal("0.809552722"));
            put("GBP", new BigDecimal("1.126695"));
            put("BTC", new BigDecimal("6977.0896569209"));
            put("ETH", new BigDecimal("685.29447470022"));
            put("FKE", new BigDecimal("0.025"));
        }};

        LinkedHashMap<String, BigDecimal> currencies = converter.read(filePath);

        assertEquals(expected, currencies);
    }

    @Test
    public void read_badPath_ex() {
        String filePath = "./badFile.csv";

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("File is not found");

        LinkedHashMap<String, BigDecimal> currencies = converter.read(filePath);
    }

    @Test
    public void read_nullPath_ex() {
        String filePath = null;

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Read failed");

        LinkedHashMap<String, BigDecimal> currencies = converter.read(filePath);
    }

    @Test
    public void read_dataTest_result() {
        String filePath = "./dataTest.csv";

        LinkedHashMap<String, BigDecimal> expected = new LinkedHashMap<String, BigDecimal>() {{
            put("XRP", new BigDecimal("0.15835"));
            put("JPY", new BigDecimal("0.0083713"));
            put("AUD", new BigDecimal("0.5536"));
            put("CAD", new BigDecimal("0.63886"));
            put("LTC", new BigDecimal("35"));
            put("EOS", new BigDecimal("20.210"));
        }};

        LinkedHashMap<String, BigDecimal> currencies = converter.read(filePath);

        assertEquals(expected, currencies);
    }

    @Test
    public void read_dataFailTest_ex() {
        String filePath = "./dataFailTest.csv";

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Read failed");

        LinkedHashMap<String, BigDecimal> currencies = converter.read(filePath);
}

    //===============  convert  ==============================
    @Test
    public void convert_BTC2USD_result() {
        LinkedHashMap<String, BigDecimal> currencies = new LinkedHashMap<String, BigDecimal>() {{
            put("EUR", new BigDecimal("1"));
            put("USD", new BigDecimal("0.809552722"));
            put("GBP", new BigDecimal("1.126695"));
            put("BTC", new BigDecimal("6977.0896569209"));
            put("ETH", new BigDecimal("685.29447470022"));
            put("FKE", new BigDecimal("0.025"));
        }};
        double quantity = 0.000625;
        String from = "BTC";
        String to = "USD";

        BigDecimal expected = new BigDecimal("5.386531250000000000");

        BigDecimal actual = converter.convert(currencies, quantity, from, to);

        assertEquals(expected, actual);
    }

    @Test
    public void convert_BTC2GBP_result() {
        LinkedHashMap<String, BigDecimal> currencies = new LinkedHashMap<String, BigDecimal>() {{
            put("EUR", new BigDecimal("1"));
            put("USD", new BigDecimal("0.809552722"));
            put("GBP", new BigDecimal("1.126695"));
            put("BTC", new BigDecimal("6977.0896569209"));
            put("ETH", new BigDecimal("685.29447470022"));
            put("FKE", new BigDecimal("0.025"));
        }};
        double quantity = 0.0875;
        String from = "BTC";
        String to = "GBP";

        BigDecimal expected = new BigDecimal("541.846147342962159235");

        BigDecimal actual = converter.convert(currencies, quantity, from, to);

        assertEquals(expected, actual);
    }

    @Test
    public void convert_BTC2ETH_result() {
        LinkedHashMap<String, BigDecimal> currencies = new LinkedHashMap<String, BigDecimal>() {{
            put("EUR", new BigDecimal("1"));
            put("USD", new BigDecimal("0.809552722"));
            put("GBP", new BigDecimal("1.126695"));
            put("BTC", new BigDecimal("6977.0896569209"));
            put("ETH", new BigDecimal("685.29447470022"));
            put("FKE", new BigDecimal("0.025"));
        }};
        double quantity = 0.25;
        String from = "BTC";
        String to = "ETH";

        BigDecimal expected = new BigDecimal("2.545288892039078097");

        BigDecimal actual = converter.convert(currencies, quantity, from, to);

        assertEquals(expected, actual);
    }

    @Test
    public void convert_BTC2FKE_result() {
        LinkedHashMap<String, BigDecimal> currencies = new LinkedHashMap<String, BigDecimal>() {{
            put("EUR", new BigDecimal("1"));
            put("USD", new BigDecimal("0.809552722"));
            put("GBP", new BigDecimal("1.126695"));
            put("BTC", new BigDecimal("6977.0896569209"));
            put("ETH", new BigDecimal("685.29447470022"));
            put("FKE", new BigDecimal("0.025"));
        }};
        double quantity = 0.33333;
        String from = "BTC";
        String to = "FKE";

        BigDecimal expected = new BigDecimal("93026.931813657743880000");

        BigDecimal actual = converter.convert(currencies, quantity, from, to);

        assertEquals(expected, actual);
    }

    @Test
    public void convert_USD2GBP_result() {
        LinkedHashMap<String, BigDecimal> currencies = new LinkedHashMap<String, BigDecimal>() {{
            put("EUR", new BigDecimal("1"));
            put("USD", new BigDecimal("0.809552722"));
            put("GBP", new BigDecimal("1.126695"));
            put("BTC", new BigDecimal("6977.0896569209"));
            put("ETH", new BigDecimal("685.29447470022"));
            put("FKE", new BigDecimal("0.025"));
        }};
        double quantity = 62510;
        String from = "USD";
        String to = "GBP";

        BigDecimal expected = new BigDecimal("44914.675801543452309631");

        BigDecimal actual = converter.convert(currencies, quantity, from, to);

        assertEquals(expected, actual);
    }

    @Test
    public void convert_USD2BTC_result() {
        LinkedHashMap<String, BigDecimal> currencies = new LinkedHashMap<String, BigDecimal>() {{
            put("EUR", new BigDecimal("1"));
            put("USD", new BigDecimal("0.809552722"));
            put("GBP", new BigDecimal("1.126695"));
            put("BTC", new BigDecimal("6977.0896569209"));
            put("ETH", new BigDecimal("685.29447470022"));
            put("FKE", new BigDecimal("0.025"));
        }};
        double quantity = 6265690;
        String from = "USD";
        String to = "BTC";

        BigDecimal expected = new BigDecimal("727.008916916614936560");

        BigDecimal actual = converter.convert(currencies, quantity, from, to);

        assertEquals(expected, actual);
    }

    @Test
    public void convert_USD2ETH_result() {
        LinkedHashMap<String, BigDecimal> currencies = new LinkedHashMap<String, BigDecimal>() {{
            put("EUR", new BigDecimal("1"));
            put("USD", new BigDecimal("0.809552722"));
            put("GBP", new BigDecimal("1.126695"));
            put("BTC", new BigDecimal("6977.0896569209"));
            put("ETH", new BigDecimal("685.29447470022"));
            put("FKE", new BigDecimal("0.025"));
        }};
        double quantity = 7250;
        String from = "USD";
        String to = "ETH";

        BigDecimal expected = new BigDecimal("8.564576909900650907");

        BigDecimal actual = converter.convert(currencies, quantity, from, to);

        assertEquals(expected, actual);
    }

    @Test
    public void convert_USD2FKE_result() {
        LinkedHashMap<String, BigDecimal> currencies = new LinkedHashMap<String, BigDecimal>() {{
            put("EUR", new BigDecimal("1"));
            put("USD", new BigDecimal("0.809552722"));
            put("GBP", new BigDecimal("1.126695"));
            put("BTC", new BigDecimal("6977.0896569209"));
            put("ETH", new BigDecimal("685.29447470022"));
            put("FKE", new BigDecimal("0.025"));
        }};
        double quantity = 68230;
        String from = "USD";
        String to = "FKE";

        BigDecimal expected = new BigDecimal("2209431.288882400000000000");

        BigDecimal actual = converter.convert(currencies, quantity, from, to);

        assertEquals(expected, actual);
    }

    @Test
    public void convert_bad2FKE_ex() {
        LinkedHashMap<String, BigDecimal> currencies = new LinkedHashMap<String, BigDecimal>() {{
            put("EUR", new BigDecimal("1"));
            put("USD", new BigDecimal("0.809552722"));
            put("GBP", new BigDecimal("1.126695"));
            put("BTC", new BigDecimal("6977.0896569209"));
            put("ETH", new BigDecimal("685.29447470022"));
            put("FKE", new BigDecimal("0.025"));
        }};
        double quantity = 68230;
        String from = "bad";
        String to = "FKE";

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Input is not valid");

        BigDecimal actual = converter.convert(currencies, quantity, from, to);
    }

    @Test
    public void convert_null2FKE_ex() {
        LinkedHashMap<String, BigDecimal> currencies = new LinkedHashMap<String, BigDecimal>() {{
            put("EUR", new BigDecimal("1"));
            put("USD", new BigDecimal("0.809552722"));
            put("GBP", new BigDecimal("1.126695"));
            put("BTC", new BigDecimal("6977.0896569209"));
            put("ETH", new BigDecimal("685.29447470022"));
            put("FKE", new BigDecimal("0.025"));
        }};
        double quantity = 68230;
        String from = null;
        String to = "FKE";

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Input is not valid");

        BigDecimal actual = converter.convert(currencies, quantity, from, to);
    }

    @Test
    public void convert_zeroQuantity_ex() {
        LinkedHashMap<String, BigDecimal> currencies = new LinkedHashMap<String, BigDecimal>() {{
            put("EUR", new BigDecimal("1"));
            put("USD", new BigDecimal("0.809552722"));
            put("GBP", new BigDecimal("1.126695"));
            put("BTC", new BigDecimal("6977.0896569209"));
            put("ETH", new BigDecimal("685.29447470022"));
            put("FKE", new BigDecimal("0.025"));
        }};
        double quantity = 0;
        String from = "USD";
        String to = "BTC";

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Input is not valid");

        BigDecimal actual = converter.convert(currencies, quantity, from, to);
    }
}
