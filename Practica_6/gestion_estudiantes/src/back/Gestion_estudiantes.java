/**
 * @author Cesar de Jesus Becerra Vera
 * @since 26 de Febrero de 2026
 * @version 1.0
 * ARCHIVO: Gestion_estudiantes.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase Gestion_estudiantes que maneja la lista de estudiantes.
 */

/**
 * Paquete back que contiene las clases Nodo y Gestion_estudiantes para manejar la lista de estudiantes.
 */
package back;

// Importaciones necesarias para manejo de archivos e interfaces gráficas
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

/**
 * Clase Gestion_estudiantes que maneja la lista de estudiantes utilizando una lista doblemente enlazada.
 */
public class Gestion_estudiantes {
    // Atributos para el manejo de la lista y la imagen temporal
    private Nodo head; 
    private Nodo tail; 
    private String temporalimagen;
    
    /**
     * Constructor de la clase Gestion_estudiantes que inicializa la lista vacía y la imagen temporal.
     */
    public Gestion_estudiantes() {
        this.head = null;
        this.tail = null;
        this.temporalimagen = null;
    }
    
    /**
     * Método para verificar si la lista de estudiantes está vacía.
     * @return true si la lista está vacía, false en caso contrario
     */
    public boolean estaVacia() {
        return head == null;
    }
    
    /**
     * Método para agregar un nuevo estudiante a la lista. Valida que los datos no estén vacíos antes de agregar.
     * @param nombre del estudiante
     * @param apellidoPaterno del estudiante
     * @param apellidoMaterno del estudiante
     * @param carrera del estudiante
     * @param correo del estudiante (identificador único)
     * @param rutaImagen del estudiante (opcional, puede ser null o vacío)
     * @return true si el estudiante se agregó correctamente, false si alguno de los datos obligatorios está vacío o nulo
     */
    public boolean agregarEstudiante(String nombre, String apellidoPaterno, String apellidoMaterno, String carrera, String correo, String rutaImagen) {
        if (nombre == null || nombre.trim().isEmpty() ||
            apellidoPaterno == null || apellidoPaterno.trim().isEmpty() ||
            apellidoMaterno == null || apellidoMaterno.trim().isEmpty() ||
            carrera == null || carrera.trim().isEmpty() ||
            correo == null || correo.trim().isEmpty()) { // Validar que los datos obligatorios no estén vacíos o nulos
            return false;
        }
        // Instanciar un nuevo nodo con los datos del estudiante
        Nodo nuevoNodo = new Nodo(nombre, apellidoPaterno, apellidoMaterno, carrera, correo, rutaImagen);
        
        if (estaVacia()) { // Si la lista está vacía, el nuevo nodo será tanto el head como el tail
            head = nuevoNodo;
            tail = nuevoNodo;
        } else {
            tail.setSiguiente(nuevoNodo);
            nuevoNodo.setAnterior(tail);
            tail = nuevoNodo;
        }
        return true;
    }
    
    /**
     * Método para eliminar un estudiante de la lista por su correo.
     * @param correo del estudiante a eliminar
     * @return true si el estudiante se eliminó correctamente, false si no se encontró el estudiante o la lista está vacía
     */
    public boolean eliminarEstudiante(String correo) {
        if (estaVacia()) { // Si la lista está vacía, no se puede eliminar ningún estudiante
            return false;
        }
        // Empezar la búsqueda desde el head de la lista
        Nodo actual = head;
        
        while (actual != null) { // Recorrer la lista hasta encontrar el estudiante con el correo especificado
            if (actual.getCorreo().equals(correo)) {
                if (actual == head && actual == tail) { // Caso 1: Es el único nodo en la lista
                    head = null;
                    tail = null;
                }
                else if (actual == head) { // Caso 2: Es el primer nodo
                    head = head.getSiguiente();
                    head.setAnterior(null);
                }
                else if (actual == tail) { // Caso 3: Es el último nodo
                    tail = tail.getAnterior();
                    tail.setSiguiente(null);
                }
                else { // Caso 4: Está en medio
                    actual.getAnterior().setSiguiente(actual.getSiguiente());
                    actual.getSiguiente().setAnterior(actual.getAnterior());
                }
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false; 
    }
    
    /**
     * Método para dar de baja a un estudiante por su correo.
     * @param correo del estudiante a dar de baja
     * @return true si el estudiante se dio de baja correctamente, false si no se encontró el estudiante o la lista está vacía
     */
    public boolean darDeBajaEstudiante(String correo) {
        Nodo estudiante = buscarEstudiante(correo);
        if (estudiante != null) { // Si se encuentra el estudiante, cambiar su estado a "Baja"
            estudiante.darDeBaja();
            return true;
        }
        return false;
    }
    
    /**
     * Método para reactivar a un estudiante por su correo.
     * @param correo del estudiante a reactivar
     * @return true si el estudiante se reactivó correctamente, false si no se encontró el estudiante o la lista está vacía
     */
    public boolean reactivarEstudiante(String correo) {
        Nodo estudiante = buscarEstudiante(correo);
        if (estudiante != null) { // Si se encuentra el estudiante, cambiar su estado a "Activo"
            estudiante.setEstado("Activo");
            return true;
        }
        return false;
    }
    
    /**
     * Método para buscar un estudiante por su correo.
     * @param correo del estudiante a buscar
     * @return el nodo del estudiante si se encuentra, null si no se encuentra o la lista está vacía
     */
    public Nodo buscarEstudiante(String correo) {
        if (estaVacia()) { // Si la lista está vacía, no se puede encontrar ningún estudiante
            return null;
        }
        Nodo actual = head;
        while (actual != null) { // Recorrer la lista hasta encontrar el estudiante con el correo especificado
            if (actual.getCorreo().equals(correo)) { // Si se encuentra el estudiante, retornar su nodo
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    /**
     * Método para obtener el primer nodo (head) de la lista.
     * @return el nodo head de la lista, o null si la lista está vacía
     */
    public Nodo getPrimero() {
        return head;
    }
    
    /**
     * Método para obtener el último nodo (tail) de la lista.
     * @return el nodo tail de la lista, o null si la lista está vacía
     */
    public Nodo getUltimo() {
        return tail;
    }
    
    /**
     * Método para obtener el nodo siguiente a un nodo dado.
     * @param actual nodo del cual se desea obtener el siguiente
     * @return el nodo siguiente al nodo dado, o null si el nodo dado es null o no tiene un nodo siguiente
     */
    public Nodo getSiguiente(Nodo actual) {
        if (actual != null) { // Validar que el nodo dado no sea null antes de intentar obtener su siguiente
            return actual.getSiguiente();
        }
        return null;
    }
    
    /**
     * Método para obtener el nodo anterior a un nodo dado.
     * @param actual nodo del cual se desea obtener el anterior
     * @return el nodo anterior al nodo dado, o null si el nodo dado es null o no tiene un nodo anterior
     */
    public Nodo getAnterior(Nodo actual) {
        if (actual != null) { // Validar que el nodo dado no sea null antes de intentar obtener su anterior
            return actual.getAnterior();
        }
        return null;
    }
    
    /**
     * Método para contar el número de estudiantes en la lista.
     * @return el número de estudiantes en la lista, o 0 si la lista está vacía
     */
    public int contarEstudiantes() {
        int contador = 0;
        Nodo actual = head;
        while (actual != null) { // Recorrer la lista desde el head hasta el tail, incrementando el contador por cada nodo encontrado
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }
    
    /**
     * Método para recorrer la lista hacia adelante y obtener todos los estudiantes.
     * @return una cadena con la información de todos los estudiantes en la lista
     */
    public String recorrerAdelante() {
        if (estaVacia()) { // Si la lista está vacía, retornar un mensaje indicando que no hay estudiantes
            return "La lista está vacía";
        }
        // Atributo StringBuilder para construir la cadena de resultado de manera eficiente
        StringBuilder resultado = new StringBuilder();
        Nodo actual = head;
        int contador = 1;
        while (actual != null) { // Recorrer la lista desde el head hasta el tail, agregando la información de cada estudiante a la cadena de resultado
            resultado.append("Estudiante ").append(contador).append(":\n");
            resultado.append("Nombre: ").append(actual.getNombreCompleto()).append("\n");
            resultado.append("Carrera: ").append(actual.getCarrera()).append("\n");
            resultado.append("Correo: ").append(actual.getCorreo()).append("\n\n");
            actual = actual.getSiguiente();
            contador++;
        }
        return resultado.toString();
    }
    
    /**
     * Método para recorrer la lista hacia atrás y obtener todos los estudiantes.
     * @return una cadena con la información de todos los estudiantes en la lista
     */
    public String recorrerAtras() {
        if (estaVacia()) { // Si la lista está vacía, retornar un mensaje indicando que no hay estudiantes
            return "La lista está vacía";
        }
        // Atributo StringBuilder para construir la cadena de resultado de manera eficiente
        StringBuilder resultado = new StringBuilder();
        Nodo actual = tail;
        int contador = 1;
        while (actual != null) { // Recorrer la lista desde el tail hasta el head
            resultado.append("Estudiante ").append(contador).append(":\n");
            resultado.append("Nombre: ").append(actual.getNombreCompleto()).append("\n");
            resultado.append("Carrera: ").append(actual.getCarrera()).append("\n");
            resultado.append("Correo: ").append(actual.getCorreo()).append("\n\n");
            actual = actual.getAnterior();
            contador++;
        }
        return resultado.toString();
    }
    
    /**
     * Método para cargar una imagen utilizando un JFileChooser y almacenar su ruta en el atributo temporalimagen.
     */
    public void cargarImagen() {
        try {
            JFileChooser explorador = new JFileChooser();
            explorador.addChoosableFileFilter(new FileNameExtensionFilter("imagen", "jpg", "png"));
            explorador.showOpenDialog(null);
            File auxFile = explorador.getSelectedFile();
            temporalimagen = auxFile.getAbsolutePath();
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el archivo");
        }
    }
    
    /**
     * Método para cargar los datos de la imagen en un JLabel a partir de la ruta almacenada en temporalimagen.
     * @param jFoto JLabel donde se mostrará la imagen cargada
     * @param rutaImagen ruta de la imagen a cargar (puede ser null o vacía para limpiar el JLabel)
     */
    public void setCargarDatos(JLabel jFoto, String rutaImagen) {
        if (rutaImagen == null || rutaImagen.isEmpty()) { // Si la ruta de la imagen es null o vacía, limpiar el JLabel y retornar
            jFoto.setIcon(null);
            return;
        }
        try {
            ImageIcon imageIcon = new ImageIcon(rutaImagen);
            Image img = imageIcon.getImage();
            if (jFoto.getWidth() > 0 && jFoto.getHeight() > 0) { // Validar que el JLabel tenga dimensiones válidas antes de intentar escalar la imagen
                Image imagenEscalada = img.getScaledInstance(jFoto.getWidth(),
                        jFoto.getHeight(), Image.SCALE_SMOOTH);
                jFoto.setIcon(new ImageIcon(imagenEscalada));
            } else {
                jFoto.setIcon(imageIcon);
            }
        } catch (Exception e) {
            jFoto.setIcon(null);
        }
    }
    
    /**
     * Getter para imagen temporal
     * @return la ruta de la imagen temporal almacenada en el atributo temporalimagen
     */
    public String getTemporalimagen() {
        return temporalimagen;
    }
    
    /**
     * Setter para imagen temporal
     * @param temporalimagen la ruta de la imagen temporal a almacenar en el atributo temporalimagen
     */
    public void setTemporalimagen(String temporalimagen) {
        this.temporalimagen = temporalimagen;
    }
}
