package back;

public class Nodo {
    private int info; // Atributo para almacenar datos enteros de la lista
    private Nodo siguiente;

    public Nodo(int info, Nodo siguiente) {
        this.info = info;
        this.siguiente = siguiente;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
