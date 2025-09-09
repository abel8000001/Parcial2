package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.google.gson.reflect.TypeToken;
import model.Frase;

public class ApiManager {
    public static List<Frase> consumirApi() {

        Gson gson = new Gson();
        String apiUrl = "https://zenquotes.io/api/quotes";

        try {
            // Fetch the JSON data from the API
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            // Read the response from the API
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                // Read all lines into a single String
                StringBuilder jsonText = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    jsonText.append(line);
                }

                // Create a TypeToken for a list of Frase objects
                Type fraseListType = new TypeToken<List<Frase>>(){}.getType();

                // Deserialize the JSON string into a List<Frase>
                List<Frase> fraseList = gson.fromJson(jsonText.toString(), fraseListType);

                conn.disconnect();

                return fraseList;
            }

        } catch (Exception e) {
            e.printStackTrace();

            return new ArrayList<Frase>();
        }
    }
}