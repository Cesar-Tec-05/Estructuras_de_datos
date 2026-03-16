package back;

import java.util.Random;

/**
 * @author Cesar de Jesus Becerra Vera
 * @since 16 de Marzo de 2026
 * @version 1.0
 * ARCHIVO: Ordenamiento_burbuja.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase que implementa el ordenamiento de fechas con el método de la burbuja.
 */
public class Ordenamiento_burbuja {
	private static final int TAMANIO_ARREGLO = 5;
	private Fecha[] fechas;

	/**
	 * Constructor que inicializa el arreglo fijo de fechas.
	 */
	public Ordenamiento_burbuja() {
		fechas = new Fecha[TAMANIO_ARREGLO];
		inicializarFechas();
	}

	/**
	 * Inicializa el arreglo con 5 fechas desordenadas.
	 */
	private void inicializarFechas() {
		fechas[0] = new Fecha(15, 8, 2024);
		fechas[1] = new Fecha(3, 1, 2022);
		fechas[2] = new Fecha(25, 12, 2023);
		fechas[3] = new Fecha(10, 5, 2021);
		fechas[4] = new Fecha(7, 9, 2022);
	}

	/**
	 * Retorna el tamaño fijo del arreglo de fechas.
	 * @return tamaño del arreglo
	 */
	public int getTamanioArreglo() {
		return TAMANIO_ARREGLO;
	}

	/**
	 * Carga un arreglo de fechas proporcionado por el usuario.
	 * @param nuevasFechas arreglo de fechas a cargar
	 */
	public void setFechas(Fecha[] nuevasFechas) {
		if (nuevasFechas == null || nuevasFechas.length != TAMANIO_ARREGLO) {
			throw new IllegalArgumentException("El arreglo debe contener exactamente " + TAMANIO_ARREGLO + " fechas.");
		}

		for (int i = 0; i < TAMANIO_ARREGLO; i++) {
			fechas[i] = nuevasFechas[i];
		}
	}

	/**
	 * Genera 5 fechas aleatorias dentro de rangos controlados.
	 */
	public void generarFechasAleatorias() {
		Random random = new Random();

		for (int i = 0; i < TAMANIO_ARREGLO; i++) {
			int dia = random.nextInt(28) + 1;
			int mes = random.nextInt(12) + 1;
			int anio = random.nextInt(27) + 2000;

			fechas[i] = new Fecha(dia, mes, anio);
		}
	}

	/**
	 * Muestra todas las fechas del arreglo en su estado actual.
	 */
	public void mostrarFechas() {
		for (int i = 0; i < TAMANIO_ARREGLO; i++) {
			System.out.println("Posición " + i + ": " + fechas[i]);
		}
	}

	/**
	 * Ordena el arreglo con el método de burbuja de forma ascendente cronológica.
	 * La comparación se realiza manualmente: año, luego mes, luego día.
	 */
	public void ordenarPorBurbuja() {
		for (int i = 0; i < TAMANIO_ARREGLO - 1; i++) {
			boolean huboIntercambio = false;

			for (int j = 0; j < TAMANIO_ARREGLO - 1 - i; j++) {
				if (compararFechas(fechas[j], fechas[j + 1]) > 0) {
					intercambiar(j, j + 1);
					huboIntercambio = true;
				}
			}

			if (!huboIntercambio) {
				break;
			}
		}
	}

	/**
	 * Compara dos fechas de manera manual por año, mes y día.
	 * @param fechaUno primera fecha
	 * @param fechaDos segunda fecha
	 * @return valor positivo si fechaUno es mayor, negativo si es menor, 0 si son iguales
	 */
	private int compararFechas(Fecha fechaUno, Fecha fechaDos) {
		if (fechaUno.getAnio() != fechaDos.getAnio()) {
			return fechaUno.getAnio() - fechaDos.getAnio();
		}

		if (fechaUno.getMes() != fechaDos.getMes()) {
			return fechaUno.getMes() - fechaDos.getMes();
		}

		return fechaUno.getDia() - fechaDos.getDia();
	}

	/**
	 * Intercambia dos elementos del arreglo.
	 * @param indiceUno primer índice
	 * @param indiceDos segundo índice
	 */
	private void intercambiar(int indiceUno, int indiceDos) {
		Fecha temporal = fechas[indiceUno];
		fechas[indiceUno] = fechas[indiceDos];
		fechas[indiceDos] = temporal;
	}

}
