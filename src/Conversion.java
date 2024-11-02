import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Conversion implements Comparable<Conversion> {
    private String origen;
    private String destino;
    private double tarifa;
    private String direccionBase = "https://v6.exchangerate-api.com/v6/6427c5e4bf03a6d4ebaaf25f/pair/";

    public Conversion(String origen, String destino) {
        this.origen = origen;
        this.destino = destino;
        this.direccionBase += origen + "/" + destino;
    }

    public void nuevaConversion() {
        try {
            //Cliente HTTP
            HttpClient client = HttpClient.newHttpClient();
            // Solicitud
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccionBase))
                    .build();
            // Respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            // System.out.println("JSON recibido: " + json);

            // Configuración de Gson
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();

            // Parseo del JSON en un objeto ConversionOdbm
            ConversionOdbm conversionOdbm = gson.fromJson(json, ConversionOdbm.class);
            this.tarifa = conversionOdbm.conversion_rate();
            System.out.println("Conversión exitosa: " + this);

        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Conversión de " + origen + " a " + destino + " con una tarifa de " + tarifa;
    }

    @Override
    public int compareTo(Conversion o) {
        return Double.compare(this.tarifa, o.tarifa);
    }
}
