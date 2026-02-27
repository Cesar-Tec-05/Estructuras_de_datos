/**
 * @author Cesar de Jesus Becerra Vera
 * @since 26 de Febrero de 2026
 * @version 1.0
 * ARCHIVO: Nodo.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase Nodo que representa cada direccion de memoria donde se almacena la informacion de cada estudiante.
 */

/**
 * Paquete back que contiene las clases relacionadas con la lógica de la aplicación.
 */
package back;

/**
 * Clase Nodo que representa cada dirección de memoria donde se almacena la información de cada estudiante.
 */
public class Nodo {
    // Atributos del estudiante
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String carrera;
    private String correo;
    private String rutaImagen;
    private String estado;
    // Materias y calificaciones
    private String[] materias;
    private double[] calificaciones;
    private int numMaterias;
    // Referencias para la lista doblemente enlazada
    private Nodo siguiente;
    private Nodo anterior;
    
    /**
     * Constructor de la clase Nodo.
     * @param nombre Nombre del estudiante
     * @param apellidoPaterno Apellido paterno del estudiante
     * @param apellidoMaterno Apellido materno del estudiante
     * @param carrera Carrera del estudiante
     * @param correo Correo electrónico del estudiante
     * @param rutaImagen Ruta de la imagen del estudiante
     */
    public Nodo(String nombre, String apellidoPaterno, String apellidoMaterno, String carrera, String correo, String rutaImagen) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.carrera = carrera;
        this.correo = correo;
        this.rutaImagen = rutaImagen;
        this.estado = "Activo";
        this.materias = new String[4];
        this.calificaciones = new double[4];
        this.numMaterias = 0;
        this.siguiente = null;
        this.anterior = null;
    }
    
    /**
     * Metodo para obtener el nombre del estudiante.
     * @return Nombre del estudiante
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Metodo para establecer el nombre del estudiante.
     * @param nombre Nombre del estudiante
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Metodo para obtener el apellido paterno del estudiante.
     * @return Apellido paterno del estudiante
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    
    /**
     * Metodo para establecer el apellido paterno del estudiante.
     * @param apellidoPaterno Apellido paterno del estudiante
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    
    /**
     * Metodo para obtener el apellido materno del estudiante.
     * @return Apellido materno del estudiante
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    
    /**
     * Metodo para establecer el apellido materno del estudiante.
     * @param apellidoMaterno Apellido materno del estudiante
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    
    /**
     * Metodo para obtener la carrera del estudiante.
     * @return Carrera del estudiante
     */
    public String getCarrera() {
        return carrera;
    }
    
    /**
     * Metodo para establecer la carrera del estudiante.
     * @param carrera Carrera del estudiante
     */
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    
    /**
     * Metodo para obtener el correo electrónico del estudiante.
     * @return Correo electrónico del estudiante
     */
    public String getCorreo() {
        return correo;
    }
    
    /**
     * Metodo para establecer el correo electrónico del estudiante.
     * @param correo Correo electrónico del estudiante
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    /**
     * Metodo para obtener la ruta de la imagen del estudiante.
     * @return Ruta de la imagen del estudiante
     */
    public String getRutaImagen() {
        return rutaImagen;
    }
    
    /**
     * Metodo para establecer la ruta de la imagen del estudiante.
     * @param rutaImagen Ruta de la imagen del estudiante
     */
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
    
    /**
     * Metodo para obtener el siguiente nodo en la lista.
     * @return Siguiente nodo en la lista
     */
    public Nodo getSiguiente() {
        return siguiente;
    }
    
    /**
     * Metodo para establecer el siguiente nodo en la lista.
     * @param siguiente Siguiente nodo en la lista
     */
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
    /**
     * Metodo para obtener el nodo anterior en la lista.
     * @return Nodo anterior en la lista
     */
    public Nodo getAnterior() {
        return anterior;
    }
    
    /**
     * Metodo para establecer el nodo anterior en la lista.
     * @param anterior Nodo anterior en la lista
     */
    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }
    
    /**
     * Metodo para obtener el nombre completo del estudiante.
     * @return Nombre completo del estudiante
     */
    public String getNombreCompleto() {
        return nombre + " " + apellidoPaterno + " " + apellidoMaterno;
    }
    
    /**
     * Metodo para obtener el estado del estudiante (Activo o De baja).
     * @return Estado del estudiante
     */
    public String getEstado() {
        return estado;
    }
    
    /**
     * Metodo para establecer el estado del estudiante (Activo o De baja).
     * @param estado Estado del estudiante
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    /**
     * Metodo para dar de baja a un estudiante (cambiar estado a "De baja" sin eliminar el nodo).
     */
    public void darDeBaja() {
        this.estado = "De baja";
    }
    
    /**
     * Metodo para verificar si el estudiante está activo.
     * @return true si el estudiante está activo, false si está de baja
     */
    public boolean estaActivo() {
        return "Activo".equals(estado);
    }
    
    /**
     * Metodo para agregar una materia y su calificación al estudiante.
     * @param materia Nombre de la materia
     * @param calificacion Calificación de la materia (debe estar entre 0 y 10)
     * @return true si la materia se agregó correctamente, false si no se pudo agregar (por límite de materias o calificación inválida)
     */
    public boolean agregarMateria(String materia, double calificacion) {
        if (numMaterias < 4 && calificacion >= 0 && calificacion <= 10) { // Validar que no se exceda el límite de materias y que la calificación sea válida
            materias[numMaterias] = materia;
            calificaciones[numMaterias] = calificacion;
            numMaterias++;
            return true;
        }
        return false;
    }
    
    /**
     * Metodo para actualizar una materia y su calificación en el estudiante.
     * @param index Índice de la materia a actualizar (0 a 3)
     * @param materia Nombre de la materia
     * @param calificacion Calificación de la materia (debe estar entre 0 y 10)
     */
    public void actualizarMateria(int index, String materia, double calificacion) {
        if (index >= 0 && index < numMaterias && calificacion >= 0 && calificacion <= 10) { // Validar que el índice sea válido y que la calificación sea válida
            materias[index] = materia;
            calificaciones[index] = calificacion;
        }
    }
    
    /**
     * Metodo para obtener el nombre de una materia y su calificación por índice.
     * @param index Índice de la materia (0 a 3)
     * @return Nombre de la materia si el índice es válido, cadena vacía si el índice es inválido
     */
    public String getMateria(int index) {
        if (index >= 0 && index < numMaterias) { // Validar que el índice sea válido
            return materias[index];
        }
        return "";
    }
    
    /**
     * Metodo para obtener la calificación de una materia por índice.
     * @param index Índice de la materia (0 a 3)
     * @return Calificación de la materia si el índice es válido, 0.0 si el índice es inválido
     */
    public double getCalificacion(int index) {
        if (index >= 0 && index < numMaterias) {
            return calificaciones[index];
        }
        return 0.0;
    }
    
    /**
     * Metodo para obtener el número de materias que tiene el estudiante.
     * @return Número de materias que tiene el estudiante
     */
    public int getNumMaterias() {
        return numMaterias;
    }
    
    /**
     * Metodo para calcular el promedio de las calificaciones del estudiante.
     * @return Promedio de las calificaciones del estudiante, o 0.0 si no tiene materias registradas
     */
    public double calcularPromedio() {
        if (numMaterias == 0) { // Validar que el estudiante tenga materias registradas para evitar división por cero
            return 0.0;
        }
        double suma = 0;
        for (int i = 0; i < numMaterias; i++) { // Sumar las calificaciones registradas
            suma += calificaciones[i];
        }
        return suma / numMaterias;
    }
    
    /**
     * Metodo para obtener un arreglo con las materias y calificaciones del estudiante.
     * @return Arreglo de 4 filas y 2 columnas con el nombre de la materia y su calificación.
     */
    public String[][] getMateriasYCalificaciones() {
        String[][] datos = new String[4][2];
        for (int i = 0; i < 4; i++) { // Llenar el arreglo con las materias y calificaciones registradas.
            if (i < numMaterias) { // Validar que el índice esté dentro del número de materias registradas para evitar mostrar datos no válidos
                datos[i][0] = materias[i];
                datos[i][1] = String.format("%.2f", calificaciones[i]);
            } else {
                datos[i][0] = "";
                datos[i][1] = "";
            }
        }
        return datos;
    }
}
