// paquete para el backend
package back;

/**
 * @author Cesar de Jesus Becerra Vera
 * @since 03 de Febrero de 2026
 * @version 1.0
 * ARCHIVO: turnos_banco.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase para gestionar los turnos en un banco utilizando una cola circular.
 */

/**
 * Clase que implementa una cola circular para gestionar los turnos de clientes en un banco.
 */
public class Turnos_banco {
    // Atributos de la cola circular
    private String[] cola;
    private int inicio;
    private int fin;
    private int tamanio;
    private int capacidad;
    private static int contadorTurnos = 1;
    
    /**
     * Constructor para inicializar la cola circular con una capacidad específica.
     * @param capacidad Capacidad máxima de la cola
     */
    public Turnos_banco(int capacidad) {
        this.capacidad = capacidad;
        this.cola = new String[capacidad];
        this.inicio = 0;
        this.fin = 0;
        this.tamanio = 0;
    }
    
    /**
     * Método para verificar si la cola está vacía.
     * @return true si la cola está vacía, false en caso contrario
     */
    public boolean estaVacia() {
        return tamanio == 0;
    }
    
    /**
     * Método para verificar si la cola está llena.
     * @return true si la cola está llena, false en caso contrario
     */
    public boolean estaLlena() {
        return tamanio == capacidad;
    }
    
    /**
     * Método para agregar un nuevo turno a la cola (operación enqueue).
     * @param nombreCliente Nombre del cliente que solicita el turno
     * @return Número de turno asignado, o -1 si la cola está llena
     */
    public int enqueue(String nombreCliente) {
        if (estaLlena()) { // Verificar si hay espacio disponible en la cola
            return -1;
        }
        // Asignar número de turno único
        int numeroTurno = contadorTurnos++;
        // Crear el turno con formato: "Turno #X - Cliente: Nombre"
        String turno = "Turno #" + numeroTurno + " - Cliente: " + nombreCliente;
        // Insertar el turno en la posición 'fin' del arreglo
        cola[fin] = turno;
        // Avanzar el índice 'fin' de forma circular
        // El operador módulo (%) hace que cuando fin llegue a capacidad, vuelva a 0
        fin = (fin + 1) % capacidad;
        // Incrementar el contador de elementos
        tamanio++;
        return numeroTurno;
    }
    
    /**
     * Método para atender (retirar) el próximo turno de la cola (operación dequeue).
     * @return El turno atendido, o null si la cola está vacía
     */
    public String dequeue() {
        if (estaVacia()) { // Verificar si hay turnos para atender
            return null;
        }
        // Obtener el turno en la posición 'inicio'
        String turnoAtendido = cola[inicio];
        // Limpiar la posición (opcional, pero buena práctica)
        cola[inicio] = null;
        // Avanzar el índice 'inicio' de forma circular
        // El operador módulo (%) garantiza que inicio vuelva a 0 cuando alcance la capacidad
        inicio = (inicio + 1) % capacidad;
        // Decrementar el contador de elementos
        tamanio--;
        return turnoAtendido;
    }
    
    /**
     * Método para ver el próximo turno sin retirarlo de la cola.
     * @return El próximo turno, o null si la cola está vacía
     */
    public String peek() {
        if (estaVacia()) { // Verificar si hay turnos en la cola
            return null;
        }
        return cola[inicio];
    }
    
    /**
     * Método para obtener el tamaño actual de la cola.
     * @return Número de elementos en la cola
     */
    public int getTamanio() {
        return tamanio;
    }
    
    /**
     * Método para obtener la capacidad máxima de la cola.
     * @return Capacidad de la cola
     */
    public int getCapacidad() {
        return capacidad;
    }
    
    /**
     * Método para obtener el índice de inicio de la cola.
     * @return Índice del próximo elemento a atender
     */
    public int getInicio() {
        return inicio;
    }
    
    /**
     * Método para obtener el índice de fin de la cola.
     * @return Índice donde se agregará el próximo elemento
     */
    public int getFin() {
        return fin;
    }
    
    /**
     * Método para obtener el arreglo completo de la cola (para propósitos de depuración).
     * @return Arreglo de turnos en la cola
     */
    public String[] getCola() {
        return cola;
    }
    
    /**
     * Método para obtener una representación ordenada de los turnos en la cola.
     * @return Arreglo de turnos en orden desde el inicio hasta el fin
     */
    public String[] obtenerTurnosOrdenados() {
        if (estaVacia()) { // Verificar si la cola está vacía
            return new String[0];
        }
        // Crear un arreglo para almacenar los turnos en orden
        String[] turnosOrdenados = new String[tamanio];
        int contador = 0;
        int indiceActual = inicio;
        while (contador < tamanio) { // Recorrer la cola desde 'inicio' hasta 'fin'
            turnosOrdenados[contador] = cola[indiceActual];
            indiceActual = (indiceActual + 1) % capacidad;
            contador++;
        }
        return turnosOrdenados;
    }
}
