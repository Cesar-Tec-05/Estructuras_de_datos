/**
 * @author Cesar de Jesus Becerra Vera
 * @since 18 de Mayo de 2026
 * @version 1.0
 * ARCHIVO: Usuarios.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase modelo para almacenar un usuario y su contraseña en el archivo de texto.
 */

// Paquete que contiene las clases de la lógica de la aplicación.
package back;

/**
 * Clase que representa un registro de usuario para almacenamiento secuencial.
 */
public class Usuarios {
    // Atributos para el nombre de usuario y la contraseña.
    private String usuario;
    private String contrasena;

    /**
     * Construye un registro de usuario.
     * @param usuario Nombre del usuario.
     * @param contrasena Contraseña del usuario.
     */
    public Usuarios(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    /**
     * Obtiene el nombre del usuario.
     * @return Nombre del usuario.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return Contraseña del usuario.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Actualiza el nombre del usuario.
     * @param usuario Nuevo nombre del usuario.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Actualiza la contraseña del usuario.
     * @param contrasena Nueva contraseña del usuario.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Convierte el registro al formato que se almacena en el archivo.
     * @return Cadena con el formato usuario|contraseña.
     */
    public String toRegistro() {
        return usuario + "|" + contrasena;
    }

    /**
     * Crea una instancia a partir de una línea leída del archivo.
     * @param registro Línea del archivo en formato usuario|contraseña.
     * @return Instancia de Usuarios o null si la línea es inválida.
     */
    public static Usuarios fromRegistro(String registro) {
        if (registro == null || registro.isBlank()) {
            return null;
        }
        String[] partes = registro.split("\\|", 2);
        if (partes.length < 2) {
            return null;
        }
        return new Usuarios(partes[0], partes[1]);
    }
}
