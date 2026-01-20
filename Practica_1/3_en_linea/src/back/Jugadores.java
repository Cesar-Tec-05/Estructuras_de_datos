/**
 * @author Cesar de Jesus Becerra Vera
 * @since 19 de Enero de 2026
 * @version 1.0
 * ARCHIVO: Jugadores.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase Jugadores para gestionar la información de los jugadores en el juego 3 en línea, 
 * incluyendo sus nombres, turnos y puntos acumulados en un objeto, almacenado en un arreglo.
 */

// El paquete back contiene las clases relacionadas con la lógica del juego
package back;

/**
 * Clase que representa a un jugador en el juego 3 en línea.
 */
public class Jugadores {
    // Atributos del jugador
    private String nombre;
    private boolean turno;
    private int puntos;

    /**
     * Constructor de la clase Jugadores.
     * @param nombre Nombre del jugador.
     * @param turno Indica si es el turno del jugador.
     */
    public Jugadores(String nombre, boolean turno) {
        this.nombre = nombre;
        this.turno = turno;
        this.puntos = 0;
    }

    /**
     * Obtiene el nombre del jugador.
     * @return El nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Indica si es el turno del jugador.
     * @return true si es el turno del jugador, false en caso contrario.
     */
    public boolean isTurno() {
        return turno;
    }

    /**
     * Obtiene los puntos acumulados del jugador.
     * @return Los puntos del jugador.
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Establece el turno del jugador.
     * @param turno true si es el turno del jugador, false en caso contrario.
     */
    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    /**
     * Agrega un punto al jugador.
     */
    public void agregarPunto() {
        this.puntos++;
    }

    /**
     * Establece el nombre del jugador.
     * @param nombre El nombre del jugador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
