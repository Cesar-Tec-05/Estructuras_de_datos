/**
 * @author Cesar de Jesus Becerra Vera
 * @since 13 de Febrero de 2026
 * @version 1.0
 * ARCHIVO: Cancion.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase para representar una canción con sus atributos y métodos relacionados.
 */

/**
 * Paquete que contiene las clases relacionadas con la lógica de la aplicación de playlist de música.
 */
package back;

/**
 * Clase que representa una canción con sus atributos y métodos relacionados con la información de la canción.
 */
public class Cancion {
    // Atributos para almacenar la información de la canción
    private String nombre;
    private String artista;
    private String genero;
    private String duracion;

    /**
     * Constructor para crear una nueva instancia de Cancion con los atributos proporcionados.
     * @param nombre El nombre de la canción.
     * @param artista El nombre del artista o banda que interpreta la canción.
     * @param genero El género musical al que pertenece la canción (por ejemplo, pop, rock, jazz).
     * @param duracion La duración de la canción en formato "mm:ss" (minutos:segundos).
     */
    public Cancion(String nombre, String artista, String genero, String duracion) {
        this.nombre = nombre;
        this.artista = artista;
        this.genero = genero;
        this.duracion = duracion;
    }

    /**
     * Método para obtener el nombre de la canción.
     * @return El nombre de la canción.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para establecer el nombre de la canción.
     * @param nombre El nuevo nombre de la canción.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método para obtener el nombre del artista o banda que interpreta la canción.
     * @return El nombre del artista o banda.
     */
    public String getArtista() {
        return artista;
    }

    /**
     * Método para establecer el nombre del artista o banda que interpreta la canción.
     * @param artista El nuevo nombre del artista o banda.
     */
    public void setArtista(String artista) {
        this.artista = artista;
    }

    /**
     * Método para obtener el género musical de la canción.
     * @return El género musical de la canción.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Método para establecer el género musical de la canción.
     * @param genero El nuevo género musical de la canción.
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Método para obtener la duración de la canción.
     * @return La duración de la canción.
     */
    public String getDuracion() {
        return duracion;
    }

    /**
     * Método para establecer la duración de la canción.
     * @param duracion La nueva duración de la canción.
     */
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    /**
     * Método para obtener una representación en cadena de la canción, que incluye su nombre, artista, género y duración.
     * @return Una cadena que representa la canción.
     */
    @Override
    public String toString() {
        return String.format("♪ %s - %s [%s] (%s)", nombre, artista, genero, duracion);
    }

    /**
     * Método para obtener los detalles completos de la canción en un formato visualmente estructurado.
     * @return Una cadena que representa los detalles completos de la canción.
     */
    public String getDetalles() {
        return String.format(
            "┌──────────────────────────────────────┐\n" +
            "│ INFORMACIÓN DE LA CANCIÓN            │\n" +
            "├──────────────────────────────────────┤\n" +
            "│ Nombre:   %-26s │\n" +
            "│ Artista:  %-26s │\n" +
            "│ Género:   %-26s │\n" +
            "│ Duración: %-26s │\n" +
            "└──────────────────────────────────────┘",
            nombre, artista, genero, duracion
        );
    }
}
