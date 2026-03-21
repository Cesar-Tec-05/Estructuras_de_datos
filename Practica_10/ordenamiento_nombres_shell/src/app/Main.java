/**
 * @author Cesar de Jesus Becerra Vera
 * @since 20 de Marzo de 2026
 * @version 1.0
 * ARCHIVO: Main.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase principal para ejecutar el ordenamiento de nombres con el método de Shell.
 */
package app;

import back.Nombres;
import back.Shell;
import java.util.Scanner;

/**
 * Clase Main que ejecuta la práctica de ordenamiento de nombres con el método Shell.
 */
public class Main {

	/**
	 * Constructor privado para evitar la instanciación de la clase Main.
	 */
	private Main() {
	}

	/**
	 * Método principal que ejecuta la práctica de ordenamiento de nombres.
	 * @param args argumentos de línea de comandos (no utilizados)
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Shell ordenamiento = new Shell();

		System.out.println("===========================================================");
		System.out.println("PRÁCTICA 10 - ORDENAMIENTO DE NOMBRES CON MÉTODO DE SHELL");
		System.out.println("===========================================================\n");

		System.out.println("Selecciona el origen de los nombres:");
		System.out.println("1) Captura manual por el usuario");
		System.out.println("2) Generación aleatoria por el programa");

		int opcion = leerOpcion(scanner);

		if (opcion == 1) {
			Nombres[] nombresCapturados = capturarNombres(scanner, ordenamiento.getTamanioArreglo());
			ordenamiento.setNombres(nombresCapturados);
		} else {
			ordenamiento.generarNombresAleatorios();
		}

		System.out.println("\n📌 Arreglo original (nombres sin ordenar):");
		ordenamiento.mostrarNombres();

		ordenamiento.ordenarPorShell();

		System.out.println("\n✅ Arreglo ordenado alfabéticamente (A-Z):");
		ordenamiento.mostrarNombres();

		scanner.close();
	}

	/**
	 * Lee la opción del usuario para elegir cómo cargar los nombres.
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
	 * Captura los nombres ingresados por el usuario.
	 * @param scanner lector de entrada
	 * @param tamanio cantidad de nombres a capturar
	 * @return arreglo de nombres capturado
	 */
	private static Nombres[] capturarNombres(Scanner scanner, int tamanio) {
		Nombres[] nombresCapturados = new Nombres[tamanio];

		System.out.println("\nCaptura de nombres:");
		for (int i = 0; i < tamanio; i++) {
			System.out.print("Nombre " + (i + 1) + ": ");
			String nombre = scanner.nextLine().trim();

			if (nombre.isEmpty()) {
				System.out.println("⚠️ El nombre no puede estar vacío. Intenta de nuevo.");
				i--;
				continue;
			}

			nombresCapturados[i] = new Nombres(nombre);
		}

		System.out.println();
		return nombresCapturados;
	}
}
