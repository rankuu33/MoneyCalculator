package software.ulpgc.moneycalculator.apps.windows.loader;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class FixerExchangeRateLoader  {

    public Map<String,Double> load(){
        try {
            return toList(loadJson());
        } catch (IOException e) {
            return Collections.emptyMap();
        }
    }

    private Map<String,Double> toList(String json) {
        Map<String,Double> list = new HashMap<>();
        Map<String, JsonElement> symbols = new Gson().fromJson(json, JsonObject.class).get("rates").getAsJsonObject().asMap();
        for (String symbol : symbols.keySet())
            list.put(symbol, symbols.get(symbol).getAsDouble());
        return list;
    }

    private String loadJson()  throws IOException {
        URL url = new URL("http://data.fixer.io/api/latest?access_key=" + FixerAPI.key);
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }

    }
}
