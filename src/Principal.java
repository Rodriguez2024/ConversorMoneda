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
            double monto = 0;

            // Solo se pide el monto si no se elige salir
            if (!opcion.equals("7")) {
                System.out.println("Cual es el monto a convertir: ");
                monto = Double.parseDouble(lectura.nextLine());
            }

            Conversion miConversion;
            switch (opcion) {
                case "1":
                    miConversion = new Conversion("USD", "ARS");
                    System.out.println(miConversion.montoTotal(monto));
                    break;
                case "2":
                    miConversion = new Conversion("ARS", "USD");
                    System.out.println(miConversion.montoTotal(monto));
                    break;
                case "3":
                    miConversion = new Conversion("USD", "BRL");
                    System.out.println(miConversion.montoTotal(monto));
                    break;
                case "4":
                    miConversion = new Conversion("BRL", "USD");
                    System.out.println(miConversion.montoTotal(monto));
                    break;
                case "5":
                    miConversion = new Conversion("USD", "COP");
                    System.out.println("El dinero a convertir es = " + monto);
                    System.out.println(miConversion.montoTotal(monto));
                    break;
                case "6":
                    miConversion = new Conversion("COP", "USD");
                    System.out.println(miConversion.montoTotal(monto));
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
