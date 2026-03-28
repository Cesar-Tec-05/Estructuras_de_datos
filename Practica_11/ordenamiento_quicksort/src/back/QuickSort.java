/**
 * @author Cesar de Jesus Becerra Vera
 * @since 27 de Marzo de 2026
 * @version 1.0
 * ARCHIVO: QuickSort.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase que implementa el algoritmo de ordenamiento Quick Sort.
 */

package back; // Paquete que contiene las clases de la lógica de negocio del programa

import java.util.Random; // Importación de la clase Random para generar números aleatorios

/**
 * Clase QuickSort que implementa el algoritmo de ordenamiento Quick Sort.
 * Este algoritmo utiliza una estrategia de divide y conquista para ordenar arreglos de números.
 */
public class QuickSort {

	/** Tamaño fijo del arreglo de números */
	private static final int TAMANIO_ARREGLO = 10;

	/** Arreglo que almacena los números a ordenar */
	private Numeros[] numeros;

	/**
	 * Constructor que inicializa el arreglo fijo de números.
	 */
	public QuickSort() {
		numeros = new Numeros[TAMANIO_ARREGLO];
		inicializarNumeros();
	}

	/**
	 * Inicializa el arreglo con 10 números desordenados (valores por defecto).
	 */
	private void inicializarNumeros() {
		numeros[0] = new Numeros(64);
		numeros[1] = new Numeros(34);
		numeros[2] = new Numeros(25);
		numeros[3] = new Numeros(12);
		numeros[4] = new Numeros(22);
		numeros[5] = new Numeros(11);
		numeros[6] = new Numeros(90);
		numeros[7] = new Numeros(88);
		numeros[8] = new Numeros(45);
		numeros[9] = new Numeros(50);
	}

	/**
	 * Retorna el tamaño fijo del arreglo de números.
	 * @return tamaño del arreglo
	 */
	public int getTamanioArreglo() {
		return TAMANIO_ARREGLO;
	}

	/**
	 * Carga un arreglo de números proporcionado por el usuario.
	 * @param nuevosNumeros arreglo de números a cargar
	 */
	public void setNumeros(Numeros[] nuevosNumeros) {
		if (nuevosNumeros == null || nuevosNumeros.length != TAMANIO_ARREGLO) {
			throw new IllegalArgumentException("El arreglo debe contener exactamente " + TAMANIO_ARREGLO + " números.");
		}
		for (int i = 0; i < TAMANIO_ARREGLO; i++) {
			numeros[i] = nuevosNumeros[i];
		}
	}

	/**
	 * Genera 10 números aleatorios entre 1 y 100.
	 */
	public void generarNumerosAleatorios() {
		Random random = new Random();
		for (int i = 0; i < TAMANIO_ARREGLO; i++) {
			int numeroAleatorio = random.nextInt(100) + 1; // Números entre 1 y 100
			numeros[i] = new Numeros(numeroAleatorio);
		}
	}

	/**
	 * Muestra todos los números del arreglo en su estado actual.
	 */
	public void mostrarNumeros() {
		for (int i = 0; i < TAMANIO_ARREGLO; i++) {
			System.out.println("Posición " + i + ": " + numeros[i]);
		}
	}

	/**
	 * Obtiene el arreglo interno de números como array primitivo para ordenar.
	 * @return array de int con los números actuales
	 */
	private int[] obtenerArrayPrimitivo() {
		int[] array = new int[TAMANIO_ARREGLO];
		for (int i = 0; i < TAMANIO_ARREGLO; i++) {
			array[i] = numeros[i].getNumero();
		}
		return array;
	}

	/**
	 * Actualiza el arreglo de Numeros con los valores del array primitivo.
	 * @param array array de int con los números ordenados
	 */
	private void actualizarDesdeArray(int[] array) {
		for (int i = 0; i < TAMANIO_ARREGLO; i++) {
			numeros[i].setNumero(array[i]);
		}
	}

	/**
	 * Ordena los números utilizado el algoritmo Quick Sort.
	 */
	public void ordenarPorQuickSort() {
		int[] array = obtenerArrayPrimitivo();
		quickSort(array, 0, array.length - 1);
		actualizarDesdeArray(array);
	}

	/**
	 * Método privado que ordena un arreglo utilizando el algoritmo Quick Sort.
	 * Utiliza recursividad para dividir el arreglo y ordenar sus partes.
	 * @param array el arreglo de números a ordenar
	 * @param inicio el índice de inicio del arreglo
	 * @param fin el índice del final del arreglo
	 */
	private static void quickSort(int[] array, int inicio, int fin) {
		// Caso base: si el inicio es menor que el fin, se realiza la partición
		if (inicio < fin) {
			// Se particiona el arreglo y se obtiene el índice del pivote
			int indicePivote = particion(array, inicio, fin);
			// Se ordena recursivamente la parte izquierda (elementos menores al pivote)
			quickSort(array, inicio, indicePivote - 1);
			// Se ordena recursivamente la parte derecha (elementos mayores al pivote)
			quickSort(array, indicePivote + 1, fin);
		}
	}

	/**
	 * Método privado que realiza la partición del arreglo.
	 * Se elige el último elemento como pivote y se reordenan los elementos del arreglo:
	 * - Los elementos menores que el pivote quedan a su izquierda
	 * - Los elementos mayores que el pivote quedan a su derecha
	 * @param array el arreglo a particionar
	 * @param inicio el índice de inicio del arreglo
	 * @param fin el índice del final del arreglo
	 * @return el índice final del pivote después de la partición
	 */
	private static int particion(int[] array, int inicio, int fin) {
		// Se elige el último elemento como pivote
		int pivote = array[fin];
		// i es el índice del elemento más pequeño encontrado
		int i = (inicio - 1);
		// Recorrer todos los elementos desde el inicio hasta fin-1
		for (int j = inicio; j < fin; j++) {
			// Si el elemento actual es menor que el pivote
			if (array[j] < pivote) {
				// Incrementar el índice i
				i++;
				// Intercambiar el elemento en la posición i con el elemento en la posición j
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
		// Intercambiar el pivot con el elemento en la posición i+1
		int temp = array[i + 1];
		array[i + 1] = array[fin];
		array[fin] = temp;
		// Retornar el índice final del pivote
		return (i + 1);
	}
}
