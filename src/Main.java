import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Parqueadero parqueadero = new Parqueadero(5000);
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú Parqueadero:");
            System.out.println("1. Ingreso de un carro al parqueadero");
            System.out.println("2. Salida de un carro del parqueadero");
            System.out.println("3. Informar los ingresos del parqueadero");
            System.out.println("4. Consultar la cantidad de puestos disponibles");
            System.out.println("5. Avanzar el reloj del parqueadero");
            System.out.println("6. Cambiar la tarifa del parqueadero");
            System.out.println("7. Consultar el tiempo promedio de permanencia");
            System.out.println("8. Consultar carro con más horas en el parqueadero");
            System.out.println("9. Consultar si hay carros con más de 8 horas");
            System.out.println("10. Consultar carros con más de 3 horas parqueados");
            System.out.println("11. Consultar si hay carros con la misma placa");
            System.out.println("12. Consultar cantidad de carros con placa PB");
            System.out.println("13. Consultar si hay carros con 24 o más horas");
            System.out.println("14. Vaciar el parqueadero");
            System.out.println("15. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la placa del carro: ");
                    String placa = scanner.next();
                    System.out.print("Ingrese la hora de entrada (entre 6 y 21): ");
                    int horaEntrada = scanner.nextInt();
                    if (parqueadero.ingresarCarro(placa, horaEntrada)) {
                        System.out.println("Carro ingresado correctamente.");
                    } else {
                        System.out.println("No hay puestos disponibles.");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese la placa del carro: ");
                    placa = scanner.next();
                    double costo = parqueadero.darSalidaCarro(placa);
                    if (costo > 0) {
                        System.out.println("El costo por el tiempo parqueado es: " + costo);
                    } else {
                        System.out.println("El carro no se encontró en el parqueadero.");
                    }
                    break;
                case 3:
                    System.out.println("Ingresos del parqueadero: " + parqueadero.ingresosParqueadero());
                    break;
                case 4:
                    System.out.println("Puestos disponibles: " + parqueadero.cantidadPuestosDisponibles());
                    break;
                case 5:
                    System.out.print("Ingrese la cantidad de horas a avanzar: ");
                    int horas = scanner.nextInt();
                    parqueadero.avanzarReloj(horas);
                    System.out.println("El reloj se ha avanzado.");
                    break;
                case 6:
                    System.out.print("Ingrese la nueva tarifa por hora: ");
                    double nuevaTarifa = scanner.nextDouble();
                    parqueadero.cambiarTarifa(nuevaTarifa);
                    System.out.println("La tarifa se ha actualizado.");
                    break;
                case 7:
                    System.out.println("Tiempo promedio de permanencia: " + parqueadero.darTiempoPromedio() + " horas.");
                    break;
                case 8:
                    Carro carro = parqueadero.carroConMasHoras();
                    if (carro != null) {
                        System.out.println("El carro con más horas es: " + carro.getPlaca());
                    } else {
                        System.out.println("No hay carros en el parqueadero.");
                    }
                    break;
                case 9:
                    if (parqueadero.hayCarroMasDeOchoHoras()) {
                        System.out.println("Sí hay carros con más de 8 horas.");
                    } else {
                        System.out.println("No hay carros con más de 8 horas.");
                    }
                    break;
                case 10:
                    ArrayList<Carro> carros = parqueadero.darCarrosMasDeTresHorasParqueados();
                    System.out.println("Carros con más de 3 horas parqueados:");
                    for (Carro c : carros) {
                        System.out.println(c.getPlaca());
                    }
                    break;
                case 11:
                    if (parqueadero.hayCarrosPlacaIgual()) {
                        System.out.println("Sí hay carros con la misma placa.");
                    } else {
                        System.out.println("No hay carros con la misma placa.");
                    }
                    break;
                case 12:
                    System.out.println(parqueadero.metodo1());
                    break;
                case 13:
                    if (parqueadero.hayCarroCon24Horas()) {
                        System.out.println("Sí hay carros con 24 o más horas.");
                    } else {
                        System.out.println("No hay carros con 24 o más horas.");
                    }
                    break;
                case 14:
                    System.out.println(parqueadero.metodo2());
                    break;
                case 15:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 15);

        scanner.close();
    }
}
