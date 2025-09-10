package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Frase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiManager {
    private static final Logger mainLogger = LoggerFactory.getLogger("MainLogger");

    public static List<Frase> consumirApi() {
        mainLogger.info("Inicio del consumo de la API de frases...");

        Gson gson = new Gson();
        String apiUrl = "https://zenquotes.io/api/quotes";

        try {
            mainLogger.info("Conectando con la API: {}", apiUrl);
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                mainLogger.info("Error en la respuesta de la API. Código HTTP: {}", conn.getResponseCode());
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                StringBuilder jsonText = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    jsonText.append(line);
                }

                Type fraseListType = new TypeToken<List<Frase>>(){}.getType();
                List<Frase> fraseList = gson.fromJson(jsonText.toString(), fraseListType);

                conn.disconnect();

                mainLogger.info("Se recibieron {} frases desde la API.", fraseList.size());
                mainLogger.info("Finalizó correctamente el consumo de la API.");
                return fraseList;
            }

        } catch (Exception e) {
            mainLogger.info("Error durante el consumo de la API: {}", e.getMessage());
            e.printStackTrace();
            return new ArrayList<Frase>();
        }
    }
}
