/**
 * @author Cesar de Jesus Becerra Vera
 * @since 20 de Marzo de 2026
 * @version 1.0
 * ARCHIVO: Shell.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase que implementa el ordenamiento de nombres con el método de Shell.
 */
package back;

import java.util.Random;

/**
 * Clase Shell que implementa el algoritmo de ordenamiento Shell para nombres.
 */
public class Shell {
	/** Tamaño fijo del arreglo de nombres */
	private static final int TAMANIO_ARREGLO = 10;
	/** Arreglo que almacena los nombres a ordenar */
	private Nombres[] nombres;

	/**
	 * Constructor que inicializa el arreglo fijo de nombres.
	 */
	public Shell() {
		nombres = new Nombres[TAMANIO_ARREGLO];
		inicializarNombres();
	}

	/**
	 * Inicializa el arreglo con 10 nombres desordenados.
	 */
	private void inicializarNombres() {
		nombres[0] = new Nombres("Alejandra");
		nombres[1] = new Nombres("carlos");
		nombres[2] = new Nombres("BEATRIZ");
		nombres[3] = new Nombres("Daniel");
		nombres[4] = new Nombres("eva");
		nombres[5] = new Nombres("FRANCISCO");
		nombres[6] = new Nombres("gabriela");
		nombres[7] = new Nombres("HECTOR");
		nombres[8] = new Nombres("iris");
		nombres[9] = new Nombres("JAIME");
	}

	/**
	 * Retorna el tamaño fijo del arreglo de nombres.
	 * @return tamaño del arreglo
	 */
	public int getTamanioArreglo() {
		return TAMANIO_ARREGLO;
	}

	/**
	 * Carga un arreglo de nombres proporcionado por el usuario.
	 * @param nuevosNombres arreglo de nombres a cargar
	 */
	public void setNombres(Nombres[] nuevosNombres) {
		if (nuevosNombres == null || nuevosNombres.length != TAMANIO_ARREGLO) {
			throw new IllegalArgumentException("El arreglo debe contener exactamente " + TAMANIO_ARREGLO + " nombres.");
		}

		for (int i = 0; i < TAMANIO_ARREGLO; i++) {
			nombres[i] = nuevosNombres[i];
		}
	}

	/**
	 * Genera 10 nombres aleatorios.
	 */
	public void generarNombresAleatorios() {
		String[] nombresDisponibles = {
			"Ana", "Andrés", "araceli", "ANGEL", "antonia",
			"Bruno", "Brenda", "BEATRIZ", "benjamin", "blanca",
			"Carlos", "Carla", "CARLOS", "cristóbal", "clara",
			"Daniel", "Diana", "DIANA", "diego", "dolores",
			"Eduardo", "Elisa", "EMILIO", "elena", "enrique",
			"Fernando", "Francisca", "FRANCISCO", "fábio", "flora",
			"Gabriel", "Gabriela", "GASPAR", "gloria", "gregorio",
			"Héctor", "Herminia", "HILARIO", "horacio", "hilda",
			"Irene", "Ismael", "IVÁN", "iris", "ignacio",
			"Javier", "Jacqueline", "JAIME", "juana", "jesús"
		};

		Random random = new Random();
		for (int i = 0; i < TAMANIO_ARREGLO; i++) {
			String nombreAleatorio = nombresDisponibles[random.nextInt(nombresDisponibles.length)];
			nombres[i] = new Nombres(nombreAleatorio);
		}
	}

	/**
	 * Muestra todos los nombres del arreglo en su estado actual.
	 */
	public void mostrarNombres() {
		for (int i = 0; i < TAMANIO_ARREGLO; i++) {
			System.out.println("Posición " + i + ": " + nombres[i]);
		}
	}

	/**
	 * Ordena el arreglo con el método de Shell de forma ascendente alfabética.
	 * La comparación se realiza sin distinción de mayúsculas y minúsculas.
	 */
	public void ordenarPorShell() {
		int salto = TAMANIO_ARREGLO / 2;

		while (salto > 0) {
			for (int i = salto; i < TAMANIO_ARREGLO; i++) {
				int j = i;

				while (j >= salto && compararNombres(nombres[j - salto].getNombre(), nombres[j].getNombre()) > 0) {
					intercambiar(j, j - salto);
					j -= salto;
				}
			}
			salto = salto / 2;
		}
	}

	/**
	 * Compara dos nombres de forma alfabética, ignorando mayúsculas y minúsculas.
	 * @param nombre1 primer nombre a comparar
	 * @param nombre2 segundo nombre a comparar
	 * @return valor negativo si nombre1 &lt; nombre2, cero si son iguales, positivo si nombre1 &gt; nombre2
	 */
	private int compararNombres(String nombre1, String nombre2) {
		return nombre1.toLowerCase().compareTo(nombre2.toLowerCase());
	}

	/**
	 * Intercambia dos nombres en el arreglo.
	 * @param i índice del primer nombre
	 * @param j índice del segundo nombre
	 */
	private void intercambiar(int i, int j) {
		Nombres temp = nombres[i];
		nombres[i] = nombres[j];
		nombres[j] = temp;
	}
}
