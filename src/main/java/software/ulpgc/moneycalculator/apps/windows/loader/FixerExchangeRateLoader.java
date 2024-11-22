package software.ulpgc.moneycalculator.apps.windows.loader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class FixerExchangeRateLoader {
    // URL base de la API
    private static final String API_URL = "http://data.fixer.io/api/latest";

    // Convierte el JSON de tasas a un Map
    private Map<String, Double> toList(String json) {
        Map<String, Double> list = new HashMap<>();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

        // Validar que el JSON contiene las claves necesarias
        if (!jsonObject.has("rates") || !jsonObject.get("rates").isJsonObject()) {
            throw new IllegalArgumentException("Invalid JSON response: 'rates' key is missing or malformed.");
        }

        JsonObject ratesObject = jsonObject.getAsJsonObject("rates");
        for (Map.Entry<String, JsonElement> entry : ratesObject.entrySet()) {
            list.put(entry.getKey(), entry.getValue().getAsDouble());
        }
        return list;
    }

    // Carga el JSON desde la API
    private String loadJson() throws IOException {
        URL url = new URL(API_URL + "?access_key=" + FixerAPI.key);
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }

    // Carga y convierte las tasas a un Map
    public Map<String, Double> load() {
        try {
            String json = loadJson();
            return toList(json);
        } catch (IOException e) {
            System.err.println("Error loading exchange rates: " + e.getMessage());
            return Collections.emptyMap();
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid API response: " + e.getMessage());
            return Collections.emptyMap();
        }
    }
}
