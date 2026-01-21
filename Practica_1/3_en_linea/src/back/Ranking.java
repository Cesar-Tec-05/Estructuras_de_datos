/**
 * @author Cesar de Jesus Becerra Vera
 * @since 20 de Enero de 2026
 * @version 1.0
 * ARCHIVO: Ranking.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase para gestionar el ranking de los 5 mejores jugadores del juego 3 en línea.
 * Utiliza un arreglo de objetos para almacenar y ordenar los mejores puntajes.
 * 
 * DEFINICIÓN DE UN ARREGLO DE OBJETOS:
 * Un arreglo de objetos es una estructura de datos que permite almacenar múltiples instancias
 * de una clase en posiciones contiguas de memoria. A diferencia de un arreglo de tipos primitivos
 * (int, char, etc.), un arreglo de objetos almacena referencias a objetos creados a partir de
 * una clase personalizada. Cada elemento del arreglo puede acceder a los atributos y métodos
 * del objeto que contiene.
 * 
 * Ejemplo: Jugadores[] ranking = new Jugadores[5];
 * - Crea un arreglo que puede almacenar hasta 5 referencias a objetos de tipo Jugadores
 * - Cada posición del arreglo (ranking[0], ranking[1], etc.) contiene un objeto Jugadores
 * - Permite organizar y gestionar múltiples jugadores de manera eficiente
 */

// El paquete back contiene las clases relacionadas con la lógica del juego
package back;

/**
 * Clase para gestionar el ranking de los mejores jugadores.
 */
public class Ranking {
    
    /**
     * ARREGLO DE OBJETOS - Estructura de datos principal
     * Este arreglo almacena los 5 mejores jugadores del juego.
     * Cada posición contiene un objeto de tipo Jugadores con su información completa.
     */
    private Jugadores[] topJugadores;
    private static final int MAX_JUGADORES = 5;
    private int cantidadActual;
    
    /**
     * Constructor de la clase Ranking.
     * Inicializa el arreglo de objetos para almacenar hasta 5 jugadores.
     */
    public Ranking() {
        // Inicializar el arreglo de objetos Jugadores con capacidad para 5 elementos
        topJugadores = new Jugadores[MAX_JUGADORES];
        cantidadActual = 0;
    }
    
    /**
     * Método para agregar o actualizar un jugador en el ranking.
     * Si el jugador ya existe, actualiza sus puntos.
     * Si no existe y tiene puntos suficientes, lo agrega al ranking.
     * Mantiene el arreglo ordenado de mayor a menor puntaje.
     * @param jugador Objeto Jugadores a agregar o actualizar en el ranking
     */
    public void agregarJugador(Jugadores jugador) {
        // Solo agregar jugadores con al menos 1 punto
        if (jugador.getPuntos() <= 0) {
            return;
        }
        // Buscar si el jugador ya existe en el ranking
        int posicionExistente = buscarJugador(jugador.getNombre());
        if (posicionExistente != -1) {
            // Jugador existe - actualizar sus puntos sumando los nuevos
            int puntosAnteriores = topJugadores[posicionExistente].getPuntos();
            topJugadores[posicionExistente] = new Jugadores(
                jugador.getNombre(), 
                false
            );
            // Sumar puntos acumulados
            for (int i = 0; i < puntosAnteriores + jugador.getPuntos(); i++) {
                topJugadores[posicionExistente].agregarPunto();
            }
        } else {
            // Jugador nuevo - verificar si puede entrar al top 5
            if (cantidadActual < MAX_JUGADORES) {
                // Hay espacio disponible
                topJugadores[cantidadActual] = new Jugadores(jugador.getNombre(), false);
                for (int i = 0; i < jugador.getPuntos(); i++) {
                    topJugadores[cantidadActual].agregarPunto();
                }
                cantidadActual++;
            } else {
                // Ranking lleno - verificar si supera al último
                if (jugador.getPuntos() > topJugadores[MAX_JUGADORES - 1].getPuntos()) {
                    // Reemplazar al último jugador
                    topJugadores[MAX_JUGADORES - 1] = new Jugadores(jugador.getNombre(), false);
                    for (int i = 0; i < jugador.getPuntos(); i++) {
                        topJugadores[MAX_JUGADORES - 1].agregarPunto();
                    }
                }
            }
        }
        // Ordenar el ranking después de cada modificación
        ordenarRanking();
    }
    
    /**
     * Método privado para buscar un jugador por nombre en el arreglo.
     * Recorre el arreglo de objetos comparando nombres.
     * @param nombre Nombre del jugador a buscar
     * @return Índice del jugador en el arreglo, o -1 si no existe
     */
    private int buscarJugador(String nombre) {
        for (int i = 0; i < cantidadActual; i++) { // Recorrer el arreglo
            if (topJugadores[i] != null && 
                topJugadores[i].getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Método privado para ordenar el arreglo de jugadores por puntos.
     * Utiliza el algoritmo de ordenamiento burbuja de mayor a menor.
     */
    private void ordenarRanking() {
        for (int i = 0; i < cantidadActual - 1; i++) { // Recorrer el arreglo
            for (int j = 0; j < cantidadActual - i - 1; j++) { // Comparar elementos adyacentes
                if (topJugadores[j] != null && topJugadores[j + 1] != null) { // Verificar si no es nulo
                    if (topJugadores[j].getPuntos() < topJugadores[j + 1].getPuntos()) { // Comparar puntos
                        // Intercambiar objetos en el arreglo
                        Jugadores temp = topJugadores[j];
                        topJugadores[j] = topJugadores[j + 1];
                        topJugadores[j + 1] = temp;
                    }
                }
            }
        }
    }
    
    /**
     * Método para obtener el arreglo completo de los mejores jugadores.
     * @return Arreglo de objetos Jugadores con el top 5
     */
    public Jugadores[] getTopJugadores() {
        return topJugadores;
    }
    
    /**
     * Método para obtener la cantidad actual de jugadores en el ranking.
     * @return Número de jugadores almacenados actualmente
     */
    public int getCantidadActual() {
        return cantidadActual;
    }
    
    /**
     * Método para obtener una representación en texto del ranking.
     * @return String con el ranking formateado
     */
    public String obtenerRankingTexto() {
        if (cantidadActual == 0) { // No hay jugadores
            return "No hay jugadores en el ranking aún.";
        }
        StringBuilder texto = new StringBuilder(); // Usar StringBuilder para eficiencia
        texto.append("═══════════════════════════════════════\n");
        texto.append("       🏆 TOP 5 MEJORES JUGADORES 🏆\n");
        texto.append("═══════════════════════════════════════\n\n");
        for (int i = 0; i < cantidadActual; i++) { // Recorrer el arreglo de objetos
            if (topJugadores[i] != null) { // Verificar si no es nulo
                texto.append(String.format("%d. %-20s - %d puntos\n", 
                    i + 1, 
                    topJugadores[i].getNombre(), 
                    topJugadores[i].getPuntos()));
            }
        }
        texto.append("\n═══════════════════════════════════════");
        return texto.toString();
    }
    
    /**
     * Método para limpiar el ranking (útil para reiniciar).
     */
    public void limpiarRanking() {
        topJugadores = new Jugadores[MAX_JUGADORES];
        cantidadActual = 0;
    }
}
