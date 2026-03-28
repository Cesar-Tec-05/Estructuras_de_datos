/**
 * @author Cesar de Jesus Becerra Vera
 * @since 27 de Marzo de 2026
 * @version 1.0
 * ARCHIVO: Main.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase principal para ejecutar el ordenamiento con el algoritmo Quick Sort.
 */

package app;

import back.Numeros;
import back.QuickSort;
import java.util.Scanner;

/**
 * Clase Main que ejecuta la práctica de ordenamiento con el algoritmo Quick Sort.
 */
public class Main {
	/**
	 * Método principal que ejecuta la práctica de ordenamiento Quick Sort.
	 * @param args argumentos de línea de comandos (no utilizados)
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		QuickSort ordenamiento = new QuickSort();
		System.out.println("===================================================");
		System.out.println("PRÁCTICA 11 - ORDENAMIENTO CON ALGORITMO QUICK SORT");
		System.out.println("===================================================\n");
		System.out.println("Selecciona el origen de los números:");
		System.out.println("1) Captura manual por el usuario");
		System.out.println("2) Generación aleatoria por el programa");
		int opcion = leerOpcion(scanner);
		if (opcion == 1) {
			Numeros[] numerosCapturados = capturarNumeros(scanner, ordenamiento.getTamanioArreglo());
			ordenamiento.setNumeros(numerosCapturados);
		} else {
			ordenamiento.generarNumerosAleatorios();
		}
		System.out.println("\n📌 Arreglo original (números sin ordenar):");
		ordenamiento.mostrarNumeros();
		ordenamiento.ordenarPorQuickSort();
		System.out.println("\n✅ Arreglo ordenado (de menor a mayor):");
		ordenamiento.mostrarNumeros();
		System.out.println("\n===================================================");
		scanner.close();
	}

	/**
	 * Método auxiliar para leer una opción del usuario.
	 * @param scanner el Scanner para leer entrada
	 * @return la opción seleccionada (1 o 2)
	 */
	private static int leerOpcion(Scanner scanner) {
		int opcion = 0;
		while (opcion != 1 && opcion != 2) {
			try {
				System.out.print("\nOpción: ");
				opcion = Integer.parseInt(scanner.nextLine());
				if (opcion != 1 && opcion != 2) {
					System.out.println("❌ Por favor, ingresa 1 o 2.");
				}
			} catch (NumberFormatException e) {
				System.out.println("❌ Entrada inválida. Por favor, ingresa un número.");
			}
		}
		return opcion;
	}

	/**
	 * Método auxiliar para capturar números manualmente del usuario.
	 * @param scanner el Scanner para leer entrada
	 * @param cantidad cantidad de números a capturar
	 * @return arreglo de Numeros con los valores capturados
	 */
	private static Numeros[] capturarNumeros(Scanner scanner, int cantidad) {
		Numeros[] numeros = new Numeros[cantidad];
		System.out.println("\nIngresa " + cantidad + " números enteros:");
		for (int i = 0; i < cantidad; i++) {
			int numero = 0;
			boolean valido = false;

			while (!valido) {
				try {
					System.out.print("Número " + (i + 1) + ": ");
					numero = Integer.parseInt(scanner.nextLine());
					valido = true;
				} catch (NumberFormatException e) {
					System.out.println("❌ Por favor, ingresa un número entero válido.");
				}
			}
			numeros[i] = new Numeros(numero);
		}
		return numeros;
	}
}
