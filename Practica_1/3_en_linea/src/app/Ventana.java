/**
 * @author Cesar de Jesus Becerra Vera
 * @since 19 de Enero de 2026
 * @version 1.0
 * ARCHIVO: Ventana.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Interfaz gráfica para el juego 3 en línea con matriz 3x3 de botones,
 * gestión de turnos, puntos acumulados y partidas configurables (1-5) entre 2 jugadores.
 */

// Paquete al que pertenece la clase
package app;

// Importaciones necesarias
import back.Jugadores;
import back.juego;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase Ventana que representa la interfaz gráfica del juego 3 en línea.
 */
public class Ventana extends JFrame {
    
    // Atributos de la clase
    private juego juegoActual;
    private JButton[][] botones;
    private JLabel lblJugador1, lblJugador2, lblTurno, lblPartida, lblEstado;
    private JPanel panelTablero, panelInfo, panelSuperior;
    
    /**
     * Constructor de la clase Ventana.
     * Inicializa la ventana, solicita nombres de jugadores, y configura los componentes gráficos.
     */
    public Ventana() {
        configurarVentana();
        solicitarNombresJugadores();
        inicializarComponentes();
        mostrarVentana();
    }
    
    /**
     * Metodo para configurar las propiedades básicas de la ventana.
     */
    private void configurarVentana() {
        setTitle("3 en Raya");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 240));
    }
    
    /**
     * Metodo para solicitar los nombres de los jugadores mediante cuadros de diálogo.
     */
    private void solicitarNombresJugadores() {
        // Solicitar nombre del Jugador 1
        String nombre1 = JOptionPane.showInputDialog(this, 
            "Ingrese el nombre del Jugador 1 (X):", 
            "Configuración", 
            JOptionPane.QUESTION_MESSAGE);
        if (nombre1 == null || nombre1.trim().isEmpty()) { // Si no se ingresa nombre se asigna un nombre por defecto
            nombre1 = "Jugador 1";
        }
        // Solicitar nombre del Jugador 2
        String nombre2 = JOptionPane.showInputDialog(this, 
            "Ingrese el nombre del Jugador 2 (O):", 
            "Configuración", 
            JOptionPane.QUESTION_MESSAGE);
        if (nombre2 == null || nombre2.trim().isEmpty()) { // Si no se ingresa nombre se asigna un nombre por defecto
            nombre2 = "Jugador 2";
        }
        // Solicitar número de partidas a jugar
        int numPartidas = 5; // Valor por defecto
        while (true) { // Bucle hasta obtener un número válido
            String input = JOptionPane.showInputDialog(this, 
                "¿Cuántas partidas desean jugar?\n(Mínimo: 1, Máximo: 5)", 
                "Configuración de Partidas", 
                JOptionPane.QUESTION_MESSAGE);
            if (input == null || input.trim().isEmpty()) { // Si se cancela o no se ingresa nada, usar valor por defecto
                numPartidas = 5;
                break;
            }
            try {
                numPartidas = Integer.parseInt(input.trim());
                if (numPartidas >= 1 && numPartidas <= 5) { // Verificar rango válido
                    break; // Número válido
                } else { // Número fuera de rango
                    JOptionPane.showMessageDialog(this, 
                        "Por favor, ingrese un número entre 1 y 5.",
                        "Número Inválido",
                        JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException e) { // Manejo de entrada no numérica
                JOptionPane.showMessageDialog(this, 
                    "Por favor, ingrese un número válido.",
                    "Entrada Inválida",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        // Crear objetos Jugadores y el juego creando una nueva instancia de juego
        Jugadores jugador1 = new Jugadores(nombre1, true);
        Jugadores jugador2 = new Jugadores(nombre2, false);
        // Inicializar el juego creando una nueva instancia de juego
        juegoActual = new juego(jugador1, jugador2, numPartidas);
    }
    
    /**
     * Metodo para inicializar y configurar los componentes gráficos de la ventana.
     */
    private void inicializarComponentes() {
        // Panel superior con información de jugadores
        panelSuperior = new JPanel(new GridLayout(2, 2, 10, 5));
        panelSuperior.setBackground(new Color(52, 73, 94));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        // Etiquetas de información de jugadores y estado del juego
        lblJugador1 = crearEtiquetaJugador(
            juegoActual.getJugadores()[0].getNombre() + " (X): 0 puntos", 
            new Color(231, 76, 60));
        // Etiqueta del Jugador 2
        lblJugador2 = crearEtiquetaJugador(
            juegoActual.getJugadores()[1].getNombre() + " (O): 0 puntos", 
            new Color(52, 152, 219));
        // Etiqueta del turno actual
        lblTurno = crearEtiquetaInfo("Turno: " + juegoActual.getJugadorActual().getNombre());
        lblPartida = crearEtiquetaInfo("Partida: 0 / " + juegoActual.getMaxPartidas());
        // Agregar etiquetas al panel superior
        panelSuperior.add(lblJugador1);
        panelSuperior.add(lblJugador2);
        panelSuperior.add(lblTurno);
        panelSuperior.add(lblPartida);
        // Panel del tablero 3x3
        panelTablero = new JPanel(new GridLayout(3, 3, 5, 5));
        panelTablero.setBackground(new Color(52, 73, 94));
        panelTablero.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        botones = new JButton[3][3]; // Matriz de botones para el tablero
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) { // Crear botón para cada casilla
                botones[i][j] = crearBotonCasilla(i, j);
                panelTablero.add(botones[i][j]);
            }
        }
        // Panel inferior con estado y botones
        panelInfo = new JPanel(new BorderLayout(10, 10));
        panelInfo.setBackground(new Color(240, 240, 240));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        // Etiqueta de estado del juego
        lblEstado = new JLabel("¡Que comience el juego!", JLabel.CENTER);
        lblEstado.setFont(new Font("Arial", Font.BOLD, 16));
        lblEstado.setForeground(new Color(46, 204, 113));
        // Botón para reiniciar la partida
        JButton btnReiniciar = new JButton("Nueva Partida");
        btnReiniciar.setFont(new Font("Arial", Font.BOLD, 14));
        btnReiniciar.setBackground(new Color(46, 204, 113));
        btnReiniciar.setForeground(Color.WHITE);
        btnReiniciar.setFocusPainted(false);
        btnReiniciar.addActionListener(e -> nuevaPartida());
        // Agregar componentes al panel inferior
        panelInfo.add(lblEstado, BorderLayout.CENTER);
        panelInfo.add(btnReiniciar, BorderLayout.SOUTH);
        // Agregar paneles a la ventana
        add(panelSuperior, BorderLayout.NORTH);
        add(panelTablero, BorderLayout.CENTER);
        add(panelInfo, BorderLayout.SOUTH);
    }
    
    /**
     * Metodo para crear una etiqueta de jugador con estilo personalizado.
     * @param texto Texto que se mostrará en la etiqueta.
     * @param color Color de fondo de la etiqueta.
     * @return JLabel con el estilo aplicado.
     */
    private JLabel crearEtiquetaJugador(String texto, Color color) {
        JLabel etiqueta = new JLabel(texto, JLabel.CENTER);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 16));
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setOpaque(true);
        etiqueta.setBackground(color);
        etiqueta.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        return etiqueta;
    }
    
    /**
     * Metodo para crear una etiqueta de información con estilo personalizado.
     * @param texto Texto que se mostrará en la etiqueta.
     * @return JLabel con el estilo aplicado.
     */
    private JLabel crearEtiquetaInfo(String texto) {
        JLabel etiqueta = new JLabel(texto, JLabel.CENTER);
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 14));
        etiqueta.setForeground(Color.WHITE);
        return etiqueta;
    }
    
    /**
     * Metodo para crear un botón de casilla del tablero con estilo y acción personalizada.
     * @param fila Fila del botón en el tablero.
     * @param columna Columna del botón en el tablero.
     * @return JButton con el estilo y acción aplicada.
     */
    private JButton crearBotonCasilla(int fila, int columna) {
        // Crear un botón para una casilla específica del tablero
        JButton boton = new JButton("");
        boton.setFont(new Font("Arial", Font.BOLD, 60));
        boton.setBackground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(new Color(52, 73, 94), 3));
        boton.addActionListener(new ActionListener() { // Acción al hacer clic en el botón
            /**
             * Método que se ejecuta al realizar una acción en el botón.
             * @param e Evento de acción.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarJugada(fila, columna);
            }
        });
        return boton;
    }
    
    /**
     * Método para realizar una jugada en el juego.
     * @param fila parametro para la fila
     * @param columna parametro para la columna
     */
    private void realizarJugada(int fila, int columna) {
        if (juegoActual.juegoTerminado()) { // Verificar si el juego ya ha terminado
            JOptionPane.showMessageDialog(this, 
                "El juego ha terminado. Resultado:\n" + juegoActual.obtenerGanadorFinal(),
                "Juego Terminado", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if (juegoActual.realizarMovimiento(fila, columna)) { // Si el movimiento es válido, actualizar el botón
            String simbolo = juegoActual.getSimboloActual();
            botones[fila][columna].setText(simbolo);
            if (simbolo.equals("X")) { // Si el símbolo es X, color rojo, si es O, color azul
                botones[fila][columna].setForeground(new Color(231, 76, 60));
            } else { // Símbolo O
                botones[fila][columna].setForeground(new Color(52, 152, 219));
            }
            if (juegoActual.verificarGanador()) { // Verificar si hay un ganador
                juegoActual.registrarVictoria();
                String ganador = juegoActual.getJugadorActual().getNombre();
                lblEstado.setText("¡" + ganador + " gana esta partida!");
                lblEstado.setForeground(new Color(46, 204, 113));
                // Actualizar la puntuación y bloquear el tablero
                actualizarPuntuacion();
                bloquearTablero();
                if (juegoActual.juegoTerminado()) { // Verificar si el juego ha terminado
                    mostrarResultadoFinal();
                } else { // Preguntar si se desea iniciar una nueva partida
                    int opcion = JOptionPane.showConfirmDialog(this, 
                        "¡" + ganador + " gana!\n¿Iniciar nueva partida?",
                        "Partida Terminada", 
                        JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION) { // Iniciar nueva partida si se selecciona "Sí"
                        nuevaPartida();
                    } else {
                        lblEstado.setText("Juego pausado. Presiona 'Nueva Partida' para continuar.");
                        lblEstado.setForeground(new Color(243, 156, 18));
                    }
                }
            } else if (juegoActual.verificarEmpate()) { // Verificar si hay un empate
                lblEstado.setText("¡Empate en esta partida!");
                lblEstado.setForeground(new Color(243, 156, 18));
                juegoActual.registrarVictoria(); // Incrementa contador sin dar puntos
                juegoActual.getJugadorActual().agregarPunto(); // Quita el punto que se agregó
                juegoActual.getJugadorActual().agregarPunto(); // Este es un hack, mejor refactorizar
                // Actualización: mejor incrementar directamente partidasJugadas
                // Actualizar la puntuación y bloquear el tablero
                actualizarPuntuacion();
                bloquearTablero();
                if (juegoActual.juegoTerminado()) { // Verificar si el juego ha terminado
                    mostrarResultadoFinal();
                } else { // Preguntar si se desea iniciar una nueva partida
                    int opcion = JOptionPane.showConfirmDialog(this, 
                        "¡Empate!\n¿Iniciar nueva partida?",
                        "Partida Terminada", 
                        JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION) { // Iniciar nueva partida si se selecciona "Sí"
                        nuevaPartida();
                    } else {
                        lblEstado.setText("Juego pausado. Presiona 'Nueva Partida' para continuar.");
                        lblEstado.setForeground(new Color(243, 156, 18));
                    }
                }
            } else { // Cambiar turno si no hay ganador ni empate
                juegoActual.cambiarTurno();
                actualizarTurno();
            }
        } else { // Movimiento inválido, casilla ocupada
            lblEstado.setText("Casilla ocupada. Intenta otra.");
            lblEstado.setForeground(new Color(231, 76, 60));
        }
    }
    
    /**
     * Método para actualizar la puntuación y la información de la partida en la interfaz gráfica.
     */
    private void actualizarPuntuacion() {
        // Actualizar las etiquetas de puntuación y partida
        Jugadores[] jugadores = juegoActual.getJugadores();
        // Etiqueta del Jugador 1
        lblJugador1.setText(jugadores[0].getNombre() + " (X): " + 
                        jugadores[0].getPuntos() + " puntos");
        // Etiqueta del Jugador 2
        lblJugador2.setText(jugadores[1].getNombre() + " (O): " + 
                        jugadores[1].getPuntos() + " puntos");
        // Etiqueta de la partida actual
        lblPartida.setText("Partida: " + juegoActual.getPartidasJugadas() + 
                        " / " + juegoActual.getMaxPartidas());
    }
    
    /**
     * Método para actualizar la etiqueta del turno actual en la interfaz gráfica.
     */
    private void actualizarTurno() {
        lblTurno.setText("Turno: " + juegoActual.getJugadorActual().getNombre());
    }
    
    /**
     * Método para bloquear el tablero, deshabilitando todos los botones.
     */
    private void bloquearTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setEnabled(false);
            }
        }
    }
    
    /**
     * Método para desbloquear el tablero, habilitando todos los botones y limpiando su texto.
     */
    private void desbloquearTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setEnabled(true);
                botones[i][j].setText("");
                botones[i][j].setBackground(Color.WHITE);
            }
        }
    }
    
    /**
     * Método para iniciar una nueva partida o reiniciar el juego si se han jugado todas las partidas.
     */
    private void nuevaPartida() {
        if (juegoActual.juegoTerminado()) { // Verificar si el juego ha terminado
            int opcion = JOptionPane.showConfirmDialog(this, 
                "Las " + juegoActual.getMaxPartidas() + " partidas han terminado.\n¿Desea iniciar un nuevo juego?",
                "Nuevo Juego", 
                JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) { // Iniciar un nuevo juego si se selecciona "Sí"
                dispose();
                new Ventana();
            }
        } else { // Iniciar una nueva partida
            juegoActual.nuevaPartida();
            desbloquearTablero();
            actualizarTurno();
            lblEstado.setText("¡Nueva partida iniciada!");
            lblEstado.setForeground(new Color(46, 204, 113));
        }
    }
    
    /**
     * Método para mostrar el resultado final del juego después de todas las partidas.
     */
    private void mostrarResultadoFinal() {
        // Mostrar el resultado final en un cuadro de diálogo
        String mensaje = "¡Juego Terminado!\n\n" + juegoActual.obtenerGanadorFinal() + 
                        "\n\nPuntuación Final:\n" +
                        juegoActual.getJugadores()[0].getNombre() + ": " + 
                        juegoActual.getJugadores()[0].getPuntos() + " puntos\n" +
                        juegoActual.getJugadores()[1].getNombre() + ": " + 
                        juegoActual.getJugadores()[1].getPuntos() + " puntos";
        JOptionPane.showMessageDialog(this, mensaje, 
            "Resultado Final", 
            JOptionPane.INFORMATION_MESSAGE);
        lblEstado.setText(juegoActual.obtenerGanadorFinal());
        lblEstado.setForeground(new Color(155, 89, 182));
    }
    
    /**
     * Método para mostrar la ventana.
     */
    private void mostrarVentana() {
        setVisible(true);
    }
    
    /**
     * Método principal para iniciar la aplicación.
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { // Establecer el Look and Feel del sistema operativo
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) { // Manejo de excepciones
                e.printStackTrace();
            }
            new Ventana(); // Crear y mostrar la ventana del juego instanciando la clase Ventana
        });
    }
}
