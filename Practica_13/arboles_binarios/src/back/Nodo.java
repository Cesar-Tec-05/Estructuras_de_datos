/**
 * @author Cesar de Jesus Becerra Vera
 * @since 17 de Abril de 2026
 * @version 1.0
 * ARCHIVO: Nodo.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase que representa un nodo en un arbol binario.
 */

// Paquete para el backend del proyecto de arboles binarios.
package back;

/**
 * Nodo enlazado para un arbol binario de enteros.
 */
public class Nodo {
    /**
     * Valor entero almacenado en el nodo.
     */
    private int dato;

    /**
     * Referencia al hijo izquierdo.
     */
    private Nodo izquierdo, raiz;

    /**
     * Referencia al hijo derecho.
     */
    private Nodo derecho;

    /**
     * Crea un nodo con el valor indicado y sin hijos.
     * @param dato valor entero del nodo
     */
    public Nodo(int dato) {
        this.raiz = null;
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    /**
     * Obtiene el valor almacenado en el nodo.
     * @return valor entero del nodo
     */
    public int getDato() {
        return dato;
    }

    /**
     * Actualiza el valor almacenado en el nodo.
     * @param dato nuevo valor entero
     */
    public void setDato(int dato) {
        this.dato = dato;
    }

    /**
     * Obtiene la referencia al hijo izquierdo.
     * @return nodo hijo izquierdo o null si no existe
     */
    public Nodo getIzquierdo() {
        return izquierdo;
    }

    /**
     * Asigna la referencia al hijo izquierdo.
     * @param izquierdo nodo que sera hijo izquierdo
     */
    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    /**
     * Obtiene la referencia al hijo derecho.
     * @return nodo hijo derecho o null si no existe
     */
    public Nodo getDerecho() {
        return derecho;
    }

    /**
     * Asigna la referencia al hijo derecho.
     * @param derecho nodo que sera hijo derecho
     */
    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public Nodo getRaiz() {
        return raiz;
    }
}