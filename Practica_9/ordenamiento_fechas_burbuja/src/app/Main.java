package app;

import back.Fecha;
import back.Ordenamiento_burbuja;
import java.util.Scanner;

/**
 * @author Cesar de Jesus Becerra Vera
 * @since 16 de Marzo de 2026
 * @version 1.0
 * ARCHIVO: Main.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase principal para ejecutar el ordenamiento de fechas con el método de la burbuja.
 */
public class Main {

	/**
	 * Método principal que ejecuta la práctica de ordenamiento de fechas.
	 * @param args argumentos de línea de comandos (no utilizados)
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Ordenamiento_burbuja ordenamiento = new Ordenamiento_burbuja();

		System.out.println("===========================================================");
		System.out.println("PRÁCTICA 9 - ORDENAMIENTO DE FECHAS CON MÉTODO DE BURBUJA");
		System.out.println("===========================================================\n");

		System.out.println("Selecciona el origen de las fechas:");
		System.out.println("1) Captura manual por el usuario");
		System.out.println("2) Generación aleatoria por el programa");

		int opcion = leerOpcion(scanner);

		if (opcion == 1) {
			Fecha[] fechasCapturadas = capturarFechas(scanner, ordenamiento.getTamanioArreglo());
			ordenamiento.setFechas(fechasCapturadas);
		} else {
			ordenamiento.generarFechasAleatorias();
		}

		System.out.println("📌 Arreglo original (fechas sin ordenar):");
		ordenamiento.mostrarFechas();

		ordenamiento.ordenarPorBurbuja();

		System.out.println("\n✅ Arreglo ordenado cronológicamente (de la más antigua a la más reciente):");
		ordenamiento.mostrarFechas();

		scanner.close();
	}

	/**
	 * Lee la opción del usuario para elegir cómo cargar las fechas.
	 * @param scanner lector de entrada
	 * @return 1 para captura manual, 2 para generación aleatoria
	 */
	private static int leerOpcion(Scanner scanner) {
		while (true) {
			System.out.print("Ingresa una opción (1 o 2): ");
			if (scanner.hasNextInt()) {
				int opcion = scanner.nextInt();
				if (opcion == 1 || opcion == 2) {
					return opcion;
				}
				System.out.println("⚠️ Opción no válida. Solo se permite 1 o 2.\n");
			} else {
				scanner.next();
				System.out.println("⚠️ Debes ingresar un número entero.\n");
			}
		}
	}

	/**
	 * Captura las fechas ingresadas por el usuario.
	 * @param scanner lector de entrada
	 * @param tamanio cantidad de fechas a capturar
	 * @return arreglo de fechas capturado
	 */
	private static Fecha[] capturarFechas(Scanner scanner, int tamanio) {
		Fecha[] fechas = new Fecha[tamanio];

		System.out.println("\nCaptura de fechas (formato DD/MM/AAAA mediante números):");
		for (int i = 0; i < tamanio; i++) {
			System.out.println("\nFecha " + (i + 1) + ":");
			int dia = leerDatoEnRango(scanner, "Día (1-31): ", 1, 31);
			int mes = leerDatoEnRango(scanner, "Mes (1-12): ", 1, 12);
			int anio = leerDatoEnRango(scanner, "Año (1900-2100): ", 1900, 2100);

			fechas[i] = new Fecha(dia, mes, anio);
		}

		System.out.println();
		return fechas;
	}

	/**
	 * Lee un entero dentro de un rango específico.
	 * @param scanner lector de entrada
	 * @param mensaje texto para solicitar el dato
	 * @param minimo valor mínimo permitido
	 * @param maximo valor máximo permitido
	 * @return valor entero válido dentro del rango
	 */
	private static int leerDatoEnRango(Scanner scanner, String mensaje, int minimo, int maximo) {
		while (true) {
			System.out.print(mensaje);
			if (scanner.hasNextInt()) {
				int valor = scanner.nextInt();
				if (valor >= minimo && valor <= maximo) {
					return valor;
				}
				System.out.println("⚠️ Valor fuera de rango. Debe estar entre " + minimo + " y " + maximo + ".");
			} else {
				scanner.next();
				System.out.println("⚠️ Debes ingresar un número entero.");
			}
		}
	}

}
