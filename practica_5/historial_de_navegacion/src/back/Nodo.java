/**
 * @author Cesar de Jesus Becerra Vera
 * @since 21 de Febrero de 2026
 * @version 1.0
 * ARCHIVO: Nodo.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase Nodo que representa un sitio web en el historial de navegación.
 */

/**
 * Paquete back que contiene las clases fundamentales para la gestión del historial de navegación,
 */
package back;

/**
 * Clase Nodo que contiene la direccion de memoria del siguiente nodo, la direccion del nodo anterior y la url del sitio web visitado.
 */
public class Nodo {
    // Atributos del nodo
    private String url;
    private Nodo siguiente;
    private Nodo anterior;

    /**
     * Constructor de la clase Nodo
     * @param url La dirección web que representa el nodo
     */
    public Nodo(String url) {
        this.url = url;
        this.siguiente = null;
        this.anterior = null;
    }

    /**
     * Metodo para obtener la URL del nodo
     * @return La URL del nodo
     */
    public String getUrl() {
        return url;
    }

    /**
     * Metodo para establecer la URL del nodo
     * @param url La nueva URL a asignar al nodo
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Metodo para obtener el nodo siguiente en la lista
     * @return El nodo siguiente o null si no hay siguiente
     */
    public Nodo getSiguiente() {
        return siguiente;
    }

    /**
     * Metodo para establecer el nodo siguiente en la lista
     * @param siguiente El nodo a asignar como siguiente del nodo actual
     */
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * Metodo para obtener el nodo anterior en la lista
     * @return El nodo anterior o null si no hay anterior
     */
    public Nodo getAnterior() {
        return anterior;
    }

    /**
     * Metodo para establecer el nodo anterior en la lista
     * @param anterior El nodo a asignar como anterior del nodo actual
     */
    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }

    /**
     * Metodo toString para representar el nodo como una cadena de texto (la URL)
     * @return La URL del nodo como cadena
     */
    @Override
    public String toString() {
        return url;
    }
}
