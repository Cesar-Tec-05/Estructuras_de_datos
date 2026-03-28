/**
 * @author Cesar de Jesus Becerra Vera
 * @since 27 de Marzo de 2026
 * @version 1.0
 * ARCHIVO: BusquedaBinaria.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase que implementa el algoritmo de búsqueda binaria para localizar valores en arreglos alfanuméricos ordenados.
 */

package back; // Paquete para la lógica de búsqueda binaria

/**
 * Clase que implementa el algoritmo de búsqueda binaria para datos alfanuméricos.
 */
public class BusquedaBinaria {
    // Arreglo de datos alfanuméricos ordenados
    private String[] datos;
    private int comparaciones;
    
    /**
     * Constructor que inicializa la clase con un arreglo de datos alfanuméricos ordenado.
     * @param datos Arreglo de cadenas ordenado alfabéticamente (de menor a mayor)
     */
    public BusquedaBinaria(String[] datos) {
        this.datos = datos;
        this.comparaciones = 0;
    }
    
    /**
     * Realiza búsqueda binaria para encontrar un valor específico en el arreglo.
     * @param objetivo Cadena a buscar en el arreglo
     * @return Índice del elemento si es encontrado, -1 si no existe
     */
    public int buscar(String objetivo) {
        // Validar entrada
        if (objetivo == null || objetivo.trim().isEmpty()) {
            return -1;
        }
        // Normalizar la búsqueda (convertir a minúsculas para comparación case-insensitive)
        String objetivoNormalizado = objetivo.trim().toLowerCase();
        // Reiniciar contador de comparaciones
        comparaciones = 0;
        int izquierda = 0;
        int derecha = datos.length - 1;
        while (izquierda <= derecha) {
            comparaciones++;
            // Calcular el punto medio sin riesgo de desbordamiento
            int medio = izquierda + (derecha - izquierda) / 2;
            // Comparar el elemento en el medio con el objetivo (case-insensitive)
            int comparacion = datos[medio].toLowerCase().compareTo(objetivoNormalizado);
            if (comparacion == 0) {
                // Elemento encontrado
                return medio;
            } else if (comparacion < 0) {
                // El elemento está en la mitad derecha
                izquierda = medio + 1;
            } else {
                // El elemento está en la mitad izquierda
                derecha = medio - 1;
            }
        }
        // Elemento no encontrado
        return -1;
    }
    
    /**
     * Obtiene el número de comparaciones realizadas en la última búsqueda.
     * @return Número de comparaciones efectuadas
     */
    public int obtenerComparaciones() {
        return comparaciones;
    }
    
    /**
     * Obtiene el tamaño del arreglo de datos.
     * @return Longitud del arreglo
     */
    public int obtenerTamano() {
        return datos.length;
    }
    
    /**
     * Obtiene el elemento en una posición específica del arreglo.
     * @param indice Posición del elemento a obtener
     * @return El elemento en el índice especificado, o null si está fuera de rango
     */
    public String obtenerElemento(int indice) {
        if (indice >= 0 && indice < datos.length) {
            return datos[indice];
        }
        return null;
    }
    
    /**
     * Verifica si el arreglo está ordenado correctamente.
     * @return true si el arreglo está ordenado, false en caso contrario
     */
    public boolean estaOrdenado() {
        for (int i = 0; i < datos.length - 1; i++) {
            if (datos[i].toLowerCase().compareTo(datos[i + 1].toLowerCase()) > 0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Retorna una representación en cadena de los datos del arreglo.
     * @return Cadena con los elementos del arreglo
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < datos.length; i++) {
            sb.append(datos[i]);
            if (i < datos.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
