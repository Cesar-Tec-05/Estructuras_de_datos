/**
 * @author Cesar de Jesus Becerra Vera
 * @since 17 de Abril de 2026
 * @version 1.0
 * ARCHIVO: ArbolBinario.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase que administra un arbol binario de busqueda (ABB) de valores enteros.
 */

// Paquete para el backend del proyecto de arboles binarios.
package back;

/**
 * Clase que administra un arbol binario de busqueda (ABB) de valores enteros.
 */
public class ArbolBinario {
    /**
     * Referencia al nodo raiz del arbol.
     */
    private Nodo raiz;

    /**
     * Crea un arbol vacio.
     */
    public ArbolBinario() {
        this.raiz = null;
    }

    /**
     * Verifica si el arbol no tiene nodos.
     * @return true si esta vacio
     */
    public boolean estaVacio() {
        return raiz == null;
    }

    /**
     * Elimina todos los nodos del arbol.
     */
    public void reiniciar() {
        this.raiz = null;
    }

    /**
     * Inserta un valor en el arbol.
     * Si el arbol esta vacio, el valor se convierte en raiz.
     * @param valor valor entero a insertar
     * @return true si se inserto; false si el valor ya existia
     */
    public boolean insertar(int valor) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            return true;
        }
        Nodo actual = raiz;
        Nodo padre = null;
        while (actual != null) {
            padre = actual;
            if (valor < actual.getDato()) {
                actual = actual.getIzquierdo();
            } else if (valor > actual.getDato()) {
                actual = actual.getDerecho();
            } else {
                return false;
            }
        }
        Nodo nuevo = new Nodo(valor);
        if (valor < padre.getDato()) {
            padre.setIzquierdo(nuevo);
        } else {
            padre.setDerecho(nuevo);
        }
        return true;
    }

    /**
     * Crea la raiz solo si el arbol esta vacio.
     * @param valor valor para la raiz
     * @return true si se creo la raiz; false si ya existia
     */
    public boolean crearRaiz(int valor) {
        if (raiz != null) {
            return false;
        }
        raiz = new Nodo(valor);
        return true;
    }

    /**
     * Devuelve el recorrido preorden (raiz, izquierdo, derecho).
     * @return cadena con los valores del recorrido
     */
    public String recorrerPreorden() {
        StringBuilder sb = new StringBuilder();
        preorden(raiz, sb);
        return sb.toString().trim();
    }

    /**
     * Devuelve el recorrido inorden (izquierdo, raiz, derecho).
     * @return cadena con los valores del recorrido
     */
    public String recorrerInorden() {
        StringBuilder sb = new StringBuilder();
        inorden(raiz, sb);
        return sb.toString().trim();
    }

    /**
     * Devuelve el recorrido postorden (izquierdo, derecho, raiz).
     * @return cadena con los valores del recorrido
     */
    public String recorrerPostorden() {
        StringBuilder sb = new StringBuilder();
        postorden(raiz, sb);
        return sb.toString().trim();
    }

    /**
     * Recorre recursivamente en preorden y construye la cadena de salida.
     * @param nodo nodo actual del recorrido
     * @param sb acumulador de texto
     */
    private void preorden(Nodo nodo, StringBuilder sb) {
        if (nodo == null) {
            return;
        }
        sb.append(nodo.getDato()).append(" ");
        preorden(nodo.getIzquierdo(), sb);
        preorden(nodo.getDerecho(), sb);
    }

    /**
     * Recorre recursivamente en inorden y construye la cadena de salida.
     * @param nodo nodo actual del recorrido
     * @param sb acumulador de texto
     */
    private void inorden(Nodo nodo, StringBuilder sb) {
        if (nodo == null) {
            return;
        }
        inorden(nodo.getIzquierdo(), sb);
        sb.append(nodo.getDato()).append(" ");
        inorden(nodo.getDerecho(), sb);
    }

    /**
     * Recorre recursivamente en postorden y construye la cadena de salida.
     * @param nodo nodo actual del recorrido
     * @param sb acumulador de texto
     */
    private void postorden(Nodo nodo, StringBuilder sb) {
        if (nodo == null) {
            return;
        }
        postorden(nodo.getIzquierdo(), sb);
        postorden(nodo.getDerecho(), sb);
        sb.append(nodo.getDato()).append(" ");
    }
}
