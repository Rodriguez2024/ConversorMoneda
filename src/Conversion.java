import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;

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
            // Cliente HTTP
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
            //System.out.println("Conversión exitosa: " + this);

        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Método para convertir un monto de la moneda de origen a la moneda de destino
    public String montoTotal(double monto) {
        nuevaConversion();
        String formato = "#,##0.00"; // Define el patrón de formato
        DecimalFormat decimalFormat = new DecimalFormat(formato);
        String valor = decimalFormat.format(monto * tarifa);
        String valorTarifa = decimalFormat.format(tarifa);
        return "Conversión de " + origen + " a " + destino + " con una tarifa de " + valorTarifa+ "\n" +
                "El total es = " + valor;
    }

    @Override
    public String toString() {
        return "Conversión de " + origen + " a " + destino + " con una tarifa de " + tarifa;
    }

    @Override
    public int compareTo(Conversion o) {
        return Double.compare(this.tarifa, o.tarifa);
    }

/*    // Método main para probar la conversión
    public static void main(String[] args) {
        // Crear una instancia de Conversion
        Conversion conversion = new Conversion("USD", "EUR");
        conversion.nuevaConversion(); // Obtener la tarifa

        // Supongamos que el usuario quiere convertir 100 USD a EUR
//        double montoOriginal = 100.0;
//        double montoConvertido = conversion.convertirMonto(montoOriginal);
//        System.out.println("Monto convertido: " + montoConvertido + " " + conversion.destino);
    }*/
}
