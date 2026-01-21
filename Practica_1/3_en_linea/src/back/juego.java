/**
 * @author Cesar de Jesus Becerra Vera
 * @since 19 de Enero de 2026
 * @version 1.0
 * ARCHIVO: juego.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase juego para gestionar la lógica del juego 3 en línea.
 */

// El paquete back contiene las clases relacionadas con la lógica del juego
package back;

/**
 * Clase que representa el juego 3 en línea.
 */
public class juego {
    // Atributos del juego
    private String[][] tablero;
    /**
     * ARREGLO DE OBJETOS - Almacenamiento de jugadores
     * Este arreglo almacena objetos de tipo Jugadores, permitiendo gestionar
     * la información de ambos jugadores en una estructura de datos única.
     * Cada elemento del arreglo (jugadores[0] y jugadores[1]) contiene un objeto
     * con los atributos y métodos de la clase Jugadores.
     */
    private Jugadores[] jugadores;
    // Variables para el control del juego
    private int turnoActual;
    private int partidasJugadas;
    private int maxPartidas;
    
    /**
     * Constructor de la clase juego.
     * @param jugador1 Primer jugador.
     * @param jugador2 Segundo jugador.
     * @param numPartidas Número de partidas a jugar (entre 1 y 5).
     */
    public juego(Jugadores jugador1, Jugadores jugador2, int numPartidas) {
        // Crear el arreglo de objetos con capacidad para 2 jugadores
        jugadores = new Jugadores[2];
        // Asignar los objetos Jugadores a las posiciones del arreglo
        jugadores[0] = jugador1;
        jugadores[1] = jugador2;
        // Inicializar el tablero y otras variables
        tablero = new String[3][3];
        turnoActual = 0;
        maxPartidas = (numPartidas >= 1 && numPartidas <= 5) ? numPartidas : 5;
        partidasJugadas = 0;
        // Inicializar el tablero con valores vacíos
        inicializarTablero();
        // Establecer el turno del primer jugador
        jugadores[0].setTurno(true);
        jugadores[1].setTurno(false);
    }
    
    /**
     * Inicializa el tablero del juego con valores vacíos.
     */
    public void inicializarTablero() {
        for (int i = 0; i < 3; i++) { // Recorrer filas
            for (int j = 0; j < 3; j++) { // Recorrer columnas
                tablero[i][j] = "";
            }
        }
    }
    
    /**
     * Realiza un movimiento en el tablero.
     * @param fila La fila donde se realizará el movimiento.
     * @param columna La columna donde se realizará el movimiento.
     * @return true si el movimiento es válido y se realiza, false en caso contrario.
     */
    public boolean realizarMovimiento(int fila, int columna) {
        if (fila < 0 || fila > 2 || columna < 0 || columna > 2) { // Verificar límites de fila y columna
            return false;
        }
        if (!tablero[fila][columna].equals("")) { // Verificar si la casilla ya está ocupada
            return false;
        }
        // Colocar el símbolo del jugador actual en la casilla
        String simbolo = turnoActual == 0 ? "X" : "O";
        tablero[fila][columna] = simbolo;
        return true;
    }
    
    /**
     * Verifica si hay un ganador en el juego.
     * @return true si hay un ganador, false en caso contrario.
     */
    public boolean verificarGanador() {
        String simbolo = turnoActual == 0 ? "X" : "O"; // Símbolo del jugador actual
        // Verificar filas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0].equals(simbolo) && 
                tablero[i][1].equals(simbolo) && 
                tablero[i][2].equals(simbolo)) {
                return true;
            }
        }
        // Verificar columnas
        for (int j = 0; j < 3; j++) {
            if (tablero[0][j].equals(simbolo) && 
                tablero[1][j].equals(simbolo) && 
                tablero[2][j].equals(simbolo)) {
                return true;
            }
        }
        // Verificar diagonales
        if (tablero[0][0].equals(simbolo) && 
            tablero[1][1].equals(simbolo) && 
            tablero[2][2].equals(simbolo)) {
            return true;
        }
        // Verificar segunda diagonal
        if (tablero[0][2].equals(simbolo) && 
            tablero[1][1].equals(simbolo) && 
            tablero[2][0].equals(simbolo)) {
            return true;
        }
        return false;
    }
    
    /**
     * Verifica si el juego ha terminado en empate.
     * @return // true si hay empate, false en caso contrario.
     */
    public boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) { // Recorrer filas
            for (int j = 0; j < 3; j++) { // Recorrer columnas
                if (tablero[i][j].equals("")) { // Si hay una casilla vacía, no hay empate
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Cambia el turno al siguiente jugador.
     */
    public void cambiarTurno() {
        jugadores[turnoActual].setTurno(false);
        turnoActual = (turnoActual + 1) % 2;
        jugadores[turnoActual].setTurno(true);
    }
    
    /**
     * Registra la victoria del jugador actual y aumenta el contador de partidas jugadas.
     */
    public void registrarVictoria() {
        jugadores[turnoActual].agregarPunto();
        partidasJugadas++;
    }
    
    /**
     * Registra un empate sin dar puntos a ningún jugador.
     * Solo incrementa el contador de partidas jugadas.
     */
    public void registrarEmpate() {
        partidasJugadas++;
    }
    
    /**
     * Inicia una nueva partida si no se ha alcanzado el máximo de partidas.
     */
    public void nuevaPartida() {
        if (partidasJugadas < maxPartidas) { // Verificar si se puede iniciar una nueva partida
            inicializarTablero();
            turnoActual = 0;
            jugadores[0].setTurno(true);
            jugadores[1].setTurno(false);
        }
    }
    
    /**
     * Obtiene el ganador final después de todas las partidas.
     * @return El nombre del ganador o indica si hubo un empate.
     */
    public String obtenerGanadorFinal() {
        if (jugadores[0].getPuntos() > jugadores[1].getPuntos()) { // Comparar puntos
            return jugadores[0].getNombre() + " es el GANADOR con " + 
                    jugadores[0].getPuntos() + " puntos";
        } else if (jugadores[1].getPuntos() > jugadores[0].getPuntos()) { // Comparar puntos
            return jugadores[1].getNombre() + " es el GANADOR con " + 
                    jugadores[1].getPuntos() + " puntos";
        } else { // Empate
            return "EMPATE - Ambos jugadores tienen " + 
                    jugadores[0].getPuntos() + " puntos";
        }
    }
    
    /**
     * Verifica si el juego ha terminado después de alcanzar el máximo de partidas.
     * @return true si el juego ha terminado, false en caso contrario.
     */
    public boolean juegoTerminado() {
        return partidasJugadas >= maxPartidas;
    }
    
    /**
     * Obtiene el valor de una casilla del tablero.
     * @param fila La fila de la casilla.
     * @param columna La columna de la casilla.
     * @return El valor de la casilla en el tablero.
     */
    public String getCasilla(int fila, int columna) {
        return tablero[fila][columna];
    }
    
    /**
     * Obtiene el jugador actual.
     * @return El jugador cuyo turno es actualmente.
     */
    public Jugadores getJugadorActual() {
        return jugadores[turnoActual];
    }
    
        /**
        * Obtiene el arreglo de jugadores.
        * @return El arreglo de jugadores.
        */
    public Jugadores[] getJugadores() {
        return jugadores;
    }
    
    /**
     * Obtiene el número de partidas jugadas.
     * @return El número de partidas jugadas.
     */
    public int getPartidasJugadas() {
        return partidasJugadas;
    }
    
    /**
     * Obtiene el número máximo de partidas permitidas.
     * @return El número máximo de partidas.
     */
    public int getMaxPartidas() {
        return maxPartidas;
    }
    
    /**
     * Obtiene el símbolo del jugador actual.
     * @return El símbolo del jugador actual ("X" o "O").
     */
    public String getSimboloActual() {
        return turnoActual == 0 ? "X" : "O";
    }
    
    /**
     * Obtiene el índice del turno actual.
     * @return El índice del turno actual.
     */
    public int getTurnoActual() {
        return turnoActual;
    }
}
