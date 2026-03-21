/**
 * @author Cesar de Jesus Becerra Vera
 * @since 20 de Marzo de 2026
 * @version 1.0
 * ARCHIVO: Nombres.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase que representa un nombre de persona.
 */
package back;

/**
 * Clase Nombres para almacenar y mostrar un nombre.
 */
public class Nombres {
	/** El nombre de la persona */
    private String nombre;

    /**
     * Constructor de la clase Nombres.
     * @param nombre el nombre de la persona
     */
    public Nombres(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter del nombre.
     * @return el nombre almacenado
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter del nombre.
     * @param nombre el nombre a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Convierte el nombre a su representación en String.
     * @return el nombre
     */
    @Override
    public String toString() {
        return nombre;
    }
}
