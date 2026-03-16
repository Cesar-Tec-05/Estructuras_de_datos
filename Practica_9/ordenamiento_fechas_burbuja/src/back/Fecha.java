/**
 * @author Cesar de Jesus Becerra Vera
 * @since 16 de Marzo de 2026
 * @version 1.0
 * ARCHIVO: Fecha.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase que representa una fecha mediante día, mes y año.
 */
package back;

/**
 * Clase Fecha para almacenar y mostrar una fecha.
 */
public class Fecha {
    private int dia;
    private int mes;
    private int anio;

    /**
     * Constructor de la clase Fecha.
     * @param dia día de la fecha
     * @param mes mes de la fecha
     * @param anio año de la fecha
     */
    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    /**
     * Getter del día.
     * @return día
     */
    public int getDia() {
        return dia;
    }

    /**
     * Getter del mes.
     * @return mes
     */
    public int getMes() {
        return mes;
    }

    /**
     * Getter del año.
     * @return año
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Convierte la fecha a formato DD/MM/AAAA.
     * @return fecha con formato legible
     */
    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", dia, mes, anio);
    }
}