/**
 * @author Cesar de Jesus Becerra Vera
 * @since 13 de Febrero de 2026
 * @version 1.0
 * ARCHIVO: Nodo.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase para representar un nodo en una lista enlazada que contiene una canción.
 */

/**
 * Paquete que contiene las clases relacionadas con la lógica de la aplicación de playlist de música.
 */
package back;

/**
 * Clase que representa un nodo en una lista enlazada que contiene una canción y dirección de memoria del siguiente nodo.
 */
public class Nodo {
    // Atributos para almacenar la canción y la referencia al siguiente nodo en la lista enlazada
    private Cancion cancion; 
    private Nodo siguiente;

    /**
     * Constructor para crear un nuevo nodo con la canción proporcionada y la referencia al siguiente nodo.
     * @param cancion La canción que se almacenará en el nodo.
     * @param siguiente La referencia al siguiente nodo en la lista enlazada (puede ser null si es el último nodo).
     */
    public Nodo(Cancion cancion, Nodo siguiente) {
        this.cancion = cancion;
        this.siguiente = siguiente;
    }

    /**
     * Método para obtener la canción almacenada en el nodo.
     * @return La canción almacenada en el nodo.
     */
    public Cancion getCancion() {
        return cancion;
    }

    /**
     * Método para establecer la canción almacenada en el nodo.
     * @param cancion La nueva canción que se almacenará en el nodo.
     */
    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }

    /**
     * Método para obtener la referencia al siguiente nodo en la lista enlazada.
     * @return La referencia al siguiente nodo en la lista enlazada (puede ser null si es el último nodo).
     */
    public Nodo getSiguiente() {
        return siguiente;
    }

    /**
     * Método para establecer la referencia al siguiente nodo en la lista enlazada.
     * @param siguiente La nueva referencia al siguiente nodo en la lista enlazada (puede ser null si es el último nodo).
     */
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
