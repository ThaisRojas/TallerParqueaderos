import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parqueadero {
    private static final int CAPACIDAD = 40;
    private Map<Integer, Carro> Puestos;
    private double tarifaPorHora;
    private int reloj;
    private double ingresosTotales; // Campo para los ingresos totales

    public Parqueadero(double tarifaInicial) {
        Puestos = new HashMap<>();
        for (int i = 1; i <= CAPACIDAD; i++) {
            Puestos.put(i, null);
        }
        tarifaPorHora = tarifaInicial;
        reloj = 6;
        ingresosTotales = 0.0;
    }

    public boolean ingresarCarro(String placa, int horaEntrada) {
        for (int i = 1; i <= CAPACIDAD; i++) {
            if (Puestos.get(i) == null) {
                Puestos.put(i, new Carro(placa, horaEntrada));
                return true;
            }
        }
        return false;
    }

    public double darSalidaCarro(String placa) {
        for (int i = 1; i <= CAPACIDAD; i++) {
            Carro carro = Puestos.get(i);
            if (carro != null && carro.getPlaca().equals(placa)) {
                int horasParqueado = reloj - carro.getHoraEntrada();
                double costo = tarifaPorHora * horasParqueado;
                Puestos.put(i, null);
                ingresosTotales += costo; // Actualiza los ingresos totales
                return costo;
            }
        }
        return 0.0;
    }

    public double darTiempoPromedio() {
        int sumaTiempos = 0;
        int contadorCarros = 0;

        for (Carro carro : Puestos.values()) {
            if (carro != null) {
                sumaTiempos += (reloj - carro.getHoraEntrada());
                contadorCarros += 1;
            }
        }

        return contadorCarros == 0 ? 0 : (double) sumaTiempos / contadorCarros;
    }

    public Carro carroConMasHoras() {
        Carro carroMasHoras = null;
        int maxHoras = 0;

        for (Carro carro : Puestos.values()) {
            if (carro != null) {
                int horasParqueado = reloj - carro.getHoraEntrada();
                if (horasParqueado > maxHoras) {
                    maxHoras = horasParqueado;
                    carroMasHoras = carro;
                }
            }
        }

        return carroMasHoras;
    }

    public boolean hayCarroMasDeOchoHoras() {
        for (Carro carro : Puestos.values()) {
            if (carro != null && (reloj - carro.getHoraEntrada()) > 8) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Carro> darCarrosMasDeTresHorasParqueados() {
        ArrayList<Carro> carros = new ArrayList<>();

        for (Carro carro : Puestos.values()) {
            if (carro != null && (reloj - carro.getHoraEntrada()) > 3) {
                carros.add(carro);
            }
        }

        return carros;
    }

    public boolean hayCarrosPlacaIgual() {
        ArrayList<String> placas = new ArrayList<>();

        for (Carro carro : Puestos.values()) {
            if (carro != null) {
                if (placas.contains(carro.getPlaca())) {
                    return true;
                } else {
                    placas.add(carro.getPlaca());
                }
            }
        }
        return false;
    }

    public int contarCarrosQueComienzanConPlacaPB() {
        int contador = 0;

        for (Carro carro : Puestos.values()) {
            if (carro != null && carro.getPlaca().startsWith("PB")) {
                contador++;
            }
        }

        return contador;
    }

    public boolean hayCarroCon24Horas() {
        for (Carro carro : Puestos.values()) {
            if (carro != null && (reloj - carro.getHoraEntrada()) >= 24) {
                return true;
            }
        }
        return false;
    }

    public int desocuparParqueadero() {
        int cantidadCarrosSacados = 0;

        for (int i = 1; i <= CAPACIDAD; i++) {
            if (Puestos.get(i) != null) {
                Puestos.put(i, null);
                cantidadCarrosSacados++;
            }
        }

        return cantidadCarrosSacados;
    }

    public String metodo1() {
        int cantidadPB = contarCarrosQueComienzanConPlacaPB();
        boolean hayCarro24Horas = hayCarroCon24Horas();
        return "Cantidad de carros con placa PB: " + cantidadPB +
                " – Hay carro parqueado por 24 o más horas: " + (hayCarro24Horas ? "Sí" : "No");
    }

    public String metodo2() {
        int cantidadCarrosSacados = desocuparParqueadero();
        return "Cantidad de carros sacados: " + cantidadCarrosSacados;
    }

    public void avanzarReloj(int horas) {
        reloj += horas;
    }

    public void cambiarTarifa(double nuevaTarifa) {
        tarifaPorHora = nuevaTarifa;
    }

    public int cantidadPuestosDisponibles() {
        int disponibles = 0;
        for (Carro carro : Puestos.values()) {
            if (carro == null) {
                disponibles++;
            }
        }
        return disponibles;
    }

    public double ingresosParqueadero() {
        return ingresosTotales;
    }
}
