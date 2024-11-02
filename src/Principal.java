import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);
        String opcion = "";

        while (true) {
            Menu linea = new Menu();
            System.out.println(linea.imprimeMenu());
            opcion = lectura.nextLine();

            Conversion comunicacion;
            switch (opcion) {
                case "1":
                    comunicacion = new Conversion("USD", "ARS");
                    comunicacion.nuevaConversion();
                    break;
                case "2":
                    comunicacion = new Conversion("ARS", "USD");
                    comunicacion.nuevaConversion();
                    break;
                case "3":
                    comunicacion = new Conversion("USD", "BRL");
                    comunicacion.nuevaConversion();
                    break;
                case "4":
                    comunicacion = new Conversion("BRL", "USD");
                    comunicacion.nuevaConversion();
                    break;
                case "5":
                    comunicacion = new Conversion("USD", "COP");
                    comunicacion.nuevaConversion();
                    break;
                case "6":
                    comunicacion = new Conversion("COP", "USD");
                    comunicacion.nuevaConversion();
                    break;
                case "7":
                    System.out.println("Gracias por utilizar esta plataforma!");
                    return; // Rompe el bucle y finaliza el programa
                default:
                    System.out.println("No es una opción válida");
            }
        }
    }
}
