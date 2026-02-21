/**
 * @author Cesar de Jesus Becerra Vera
 * @since 21 de Febrero de 2026
 * @version 1.0
 * ARCHIVO: Navegador.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase Navegador que gestiona el historial de navegación web.
 */

/**
 * Paquete back que contiene las clases fundamentales para la gestión del historial de navegación,
 */
package back;

/**
 * Clase Navegador que implementa una lista enlazada para almacenar el historial de navegación web.
 */
public class Navegador {
    // Atributo que representa el nodo inicial de la lista (el sitio web más reciente)
    private Nodo inicio;

    /**
     * Constructor de la clase Navegador, inicializa el historial vacío
     */
    public Navegador() {
        this.inicio = null;
    }

    /**
     * Metodo para agregar una nueva dirección al historial de navegación
     * @param url La dirección web a agregar al historial
     */
    public void agregarDireccion(String url) {
        // Crear un nuevo nodo con la URL proporcionada
        Nodo nuevoNodo = new Nodo(url);
        if (inicio == null) { // Si la lista está vacía, el nuevo nodo es el inicio 
            inicio = nuevoNodo;
        } else { // Si la lista no está vacía, el nuevo nodo se convierte en el nuevo inicio y apunta al nodo anterior
            nuevoNodo.setSiguiente(inicio);
            inicio.setAnterior(nuevoNodo);
            inicio = nuevoNodo;
        }
        System.out.println("✓ Dirección agregada: " + url);
    }

    /**
     * Metodo para mostrar la dirección actual (la URL del nodo inicial)
     * @return La URL del sitio web actual o null si el historial está vacío
     */
    public String mostrarDireccionActual() {
        if (inicio == null) { // Si el historial está vacío, no hay dirección actual que mostrar
            return null;
        }
        return inicio.getUrl();
    }

    /**
     * Metodo para mostrar todo el historial de navegación, desde el sitio web más reciente hasta el más antiguo
     */
    public void mostrarHistorial() {
        if (inicio == null) { // Si el historial está vacío no hay nada que mostrar
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("  El historial está vacío");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            return;
        }
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  HISTORIAL DE NAVEGACIÓN");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        // Atributos para recorrer la lista y un contador para numerar las entradas del historial    
        Nodo actual = inicio;
        int contador = 1;
        while (actual != null) { // Mientras haya nodos en la lista, se muestra la URL de cada nodo, indicando cuál es el actual
            if (contador == 1) { // El primer nodo es el actual, se marca con una flecha
                System.out.println("  " + contador + ". " + actual.getUrl() + " ← ACTUAL");
            } else { // Los nodos siguientes son parte del historial, se muestran sin marca especial
                System.out.println("  " + contador + ". " + actual.getUrl());
            }
            actual = actual.getSiguiente();
            contador++;
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
    }

    /**
     * Metodo para verificar si el historial de navegación está vacío
     * @return true si el historial está vacío, false en caso contrario
     */
    public boolean estaVacio() {
        return inicio == null;
    }

    /**
     * Metodo para obtener el tamaño del historial de navegación, contando el número de nodos en la lista
     * @return El número de sitios web almacenados en el historial
     */
    public int obtenerTamanio() {
        // Atributos para recorrer la lista y contar el número de nodos
        int contador = 0;
        Nodo actual = inicio;
        while (actual != null) { // Mientras haya nodos en la lista, se incrementa el contador y se avanza al siguiente nodo
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }

    /**
     * Metodo para obtener el nodo inicial del historial de navegación
     * @return El nodo inicial del historial, que representa el sitio web más reciente
     */
    public Nodo getInicio() {
        return inicio;
    }
}
