package software.ulpgc.moneycalculator.apps.windows.loader;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import software.ulpgc.moneycalculator.apps.windows.model.Currency;

import com.google.gson.JsonObject;



import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
        JsonObject symbols = new Gson().fromJson(json, JsonObject.class).getAsJsonObject("symbols");

        for (Map.Entry<String, JsonElement> entry : symbols.entrySet()) {
            String code = entry.getKey();
            String name = entry.getValue().getAsString();

            list.add(new Currency(code, name, ""));
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
