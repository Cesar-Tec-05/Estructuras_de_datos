/**
 * @author Cesar de Jesus Becerra Vera
 * @since 27 de Marzo de 2026
 * @version 1.0
 * ARCHIVO: Numeros.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase que representa un número entero.
 */

package back; // Paquete que contiene las clases de la lógica de negocio del programa

/**
 * Clase Numeros para almacenar y mostrar un número entero.
 */
public class Numeros {
	
	/** El número entero almacenado */
	private int numero;

	/**
	 * Constructor de la clase Numeros.
	 * @param numero el número entero a almacenar
	 */
	public Numeros(int numero) {
		this.numero = numero;
	}

	/**
	 * Getter del número.
	 * @return el número almacenado
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Setter del número.
	 * @param numero el número a establecer
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * Convierte el número a su representación en String.
	 * @return el número como String
	 */
	@Override
	public String toString() {
		return String.valueOf(numero);
	}
}
