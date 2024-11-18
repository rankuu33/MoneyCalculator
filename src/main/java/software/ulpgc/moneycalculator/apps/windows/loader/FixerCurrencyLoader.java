package software.ulpgc.moneycalculator.apps.windows.loader;


import software.ulpgc.moneycalculator.apps.windows.model.Currency;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FixerCurrencyLoader implements CurrencyLoader {

    @Override
    public List<Currency> load() {
        try {
            return toList(loadJson());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private List<Currency> toList(String json) {
        List<Currency> list = new ArrayList<>();

        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonObject dataObject = jsonObject.getAsJsonObject("data");

        for (String coin : dataObject.keySet()) {
            JsonObject currencyObject = dataObject.getAsJsonObject(coin);
            String code = currencyObject.get("code").getAsString();
            String name = currencyObject.get("name").getAsString();
            String symbol = currencyObject.get("symbol").getAsString();
            list.add(new Currency(code, name, symbol));
        }

        return list;
    }

    private String loadJson() throws IOException {
        URL url = new URL("http://data.fixer.io/api/symbols?access_key=" + FixerAPI.key);
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }
}
