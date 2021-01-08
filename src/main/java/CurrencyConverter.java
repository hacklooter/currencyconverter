import com.google.gson.Gson;
import jsonmapping.JsonMap;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    private static final String API_URL = "https://api.exchangeratesapi.io";

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------");
        System.out.println("Possible Currencies");
        System.out.println("Use the Short ISO Code");
        System.out.println("----------------------------------");
        System.out.println("1:  EUR = Euro");
        System.out.println("2:  USD = United States Dollar");
        System.out.println("3:  CAD = Canadian dollar");
        System.out.println("4:  JPY = Japanese yen");
        System.out.println("5:  BGN = Bulgarian Lev");
        System.out.println("6:  CZK = Czeck Koruna");
        System.out.println("7:  DKK = Danish Krone");
        System.out.println("8:  GBP = Pound Sterling");
        System.out.println("9:  HUG = Hungarian Forint");
        System.out.println("10: PLN = Polish Zloty");
        System.out.println("11: SEK = Swedish Krona");
        System.out.println("12: CHF = Swiss Franc");
        System.out.println("13: ISK = Icelandic Krona");
        System.out.println("14: NOK = Norwegian Krone");
        System.out.println("15: HRK = Croatian Kuna");
        System.out.println("16: RUB = Russian Rouble");
        System.out.println("17: TRY = Turkish Lira");
        System.out.println("18: AUD = Australian Dollar");
        System.out.println("19: BRL = Brazilian Real");
        System.out.println("20: CNY = Chinese Yuan Renminbi");
        System.out.println("21: HKD = Hong Kong Dollar");
        System.out.println("22: IDR = Indonese Rupiah");
        System.out.println("23: ILS = Israeli Shekel");
        System.out.println("24: INR = Indian Rupee");
        System.out.println("25: KRW = South Korean Won");
        System.out.println("26: MXN = Mexican Peso");
        System.out.println("27: MYR = Malaysian Ringgit");
        System.out.println("28: NZD = New Zealand Dollar");
        System.out.println("29: PHP = Phillipine Peso");
        System.out.println("30: SGD = Singapore Dollar");
        System.out.println("31: THB = Thai Baht");
        System.out.println("31: ZAR = South African Rand");
        System.out.println("----------------------------------");

        System.out.println("Please enter your Base Currency!");
        System.out.println("Enter Here:");
        String base = scanner.nextLine();


        System.out.println("Please enter the currency you want to convert to!");
        System.out.println("Enter Here:");
        String conversionCurrency = scanner.next();
        while (conversionCurrency.equals(base)) {
            conversionCurrency = scanner.next();
        }

        System.out.println("Please Enter the amount of the Base Currency!");
        System.out.println("Enter Here:");
        String amount = scanner.next();
        int amountInt = 0;
        if (isNumeric(amount)) {
            amountInt = Integer.parseInt(amount);
        } else {
            while (!isNumeric(amount)) {
                System.out.println("Please Enter a valid Amount");
                amount = scanner.next();
            }
            amountInt = Integer.parseInt(amount);
        }
        JsonMap map = parseJson(getRates(base));
        double conversionRate;
        switch (conversionCurrency) {
            case "EUR":
                conversionRate = map.getRates().getEUR();
                break;
            case "USD":
                conversionRate = map.getRates().getUSD();
                break;
            case "JPY":
                conversionRate = map.getRates().getJPY();
                break;
            case "BGN":
                conversionRate = map.getRates().getBGN();
                break;
            case "CZK":
                conversionRate = map.getRates().getCZK();
                break;
            case "DKK":
                conversionRate = map.getRates().getDKK();
                break;
            case "GBP":
                conversionRate = map.getRates().getGBP();
                break;
            case "HUF":
                conversionRate = map.getRates().getHUF();
                break;
            case "PLN":
                conversionRate = map.getRates().getPLN();
                break;
            case "RON":
                conversionRate = map.getRates().getRON();
                break;
            case "SEK":
                conversionRate = map.getRates().getSEK();
                break;
            case "CHF":
                conversionRate = map.getRates().getCHF();
                break;
            case "ISK":
                conversionRate = map.getRates().getISK();
                break;
            case "NOK":
                conversionRate = map.getRates().getNOK();
                break;
            case "HRK":
                conversionRate = map.getRates().getHRK();
                break;
            case "RUB":
                conversionRate = map.getRates().getRUB();
                break;
            case "TRY":
                conversionRate = map.getRates().getTRY();
                break;
            case "AUD":
                conversionRate = map.getRates().getAUD();
                break;
            case "BRL":
                conversionRate = map.getRates().getBRL();
                break;
            case "CAD":
                conversionRate = map.getRates().getCAD();
                break;
            case "CNY":
                conversionRate = map.getRates().getCNY();
                break;
            case "HKD":
                conversionRate = map.getRates().getHKD();
                break;
            case "IDR":
                conversionRate = map.getRates().getIDR();
                break;
            case "ILS":
                conversionRate = map.getRates().getILS();
                break;
            case "INR":
                conversionRate = map.getRates().getINR();
                break;
            case "KRW":
                conversionRate = map.getRates().getKRW();
                break;
            case "MXN":
                conversionRate = map.getRates().getMXN();
                break;
            case "MYR":
                conversionRate = map.getRates().getMYR();
                break;
            case "NZD":
                conversionRate = map.getRates().getNZD();
                break;
            case "PHP":
                conversionRate = map.getRates().getPHP();
                break;
            case "SGD":
                conversionRate = map.getRates().getSGD();
                break;
            case "THB":
                conversionRate = map.getRates().getTHB();
                break;
            case "ZAR":
                conversionRate = map.getRates().getZAR();
                break;
            default:
                conversionRate = 1;
        }

        double result = amountInt * conversionRate;
        System.out.println("Base Currency:");
        System.out.println(amountInt);
        System.out.println("Converted Currency");
        System.out.println(result);




    }

    public static String createApiUrl() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(API_URL);
        stringBuilder.append("/latest");

        String url = stringBuilder.toString();

        return url;
    }
    public static String createApiUrl(String base) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(API_URL);
        stringBuilder.append("?base=");
        stringBuilder.append(base);

        String url = stringBuilder.toString();

        return url;
    }

    public static String getRates(String base) throws IOException, InterruptedException {


        if (base.equals("EUR")) {

            URL url = new URL(createApiUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(connection.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");

            return result;
        } else {

            URL url = new URL(createApiUrl(base));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(connection.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");

            return result;
        }


    }

    public static JsonMap parseJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, JsonMap.class);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }





}
