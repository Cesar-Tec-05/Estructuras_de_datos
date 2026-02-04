// Paquete principal de la aplicación
package app;

/**
 * @author Cesar de Jesus Becerra Vera
 * @since 03 de Febrero de 2026
 * @version 1.0
 * ARCHIVO: Main.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Aplicación gráfica para el sistema de turnos bancarios utilizando una cola circular.
 */

// Importaciones necesarias
import back.Turnos_banco;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase principal que implementa la interfaz gráfica para el sistema de turnos bancarios.
 */
public class Main extends JFrame {
    // Componentes de la interfaz
    private Turnos_banco sistemaTurnos;
    private JTextField txtNombreCliente;
    private JTextArea txtAreaTurnos;
    private JTextArea txtAreaEstado;
    private JPanel panelCola;
    private JLabel lblEstadisticas;
    private JLabel lblProximoTurno;

    // Colores de la interfaz
    private final Color COLOR_FONDO_OSCURO = new Color(30, 30, 30);          // Fondo principal
    private final Color COLOR_PANEL_OSCURO = new Color(45, 45, 45);          // Paneles secundarios
    private final Color COLOR_PRIMARIO = new Color(66, 135, 245);            // Azul brillante
    private final Color COLOR_SECUNDARIO = new Color(76, 145, 255);          // Azul más claro
    private final Color COLOR_EXITO = new Color(72, 199, 116);               // Verde brillante
    private final Color COLOR_PELIGRO = new Color(255, 99, 99);              // Rojo brillante
    private final Color COLOR_ADVERTENCIA = new Color(255, 193, 7);          // Amarillo dorado
    private final Color COLOR_TEXTO_CLARO = new Color(240, 240, 240);        // Texto principal
    private final Color COLOR_TEXTO_SECUNDARIO = new Color(180, 180, 180);   // Texto secundario
    private final Color COLOR_BORDE = new Color(70, 70, 70);                 // Bordes
    private final Color COLOR_CELDA_VACIA = new Color(60, 60, 60);           // Celda vacía
    private final Color COLOR_CELDA_OCUPADA = new Color(76, 145, 255);       // Celda con dato
    
    /**
     * Constructor para inicializar la interfaz gráfica.
     */
    public Main() {
        configurarVentana();
        inicializarSistema();
        crearComponentes();
        actualizarInterfaz();
    }
    
    /**
     * Método para configurar la ventana principal.
     */
    private void configurarVentana() {
        // Configuración básica de la ventana
        setTitle("Sistema de Turnos Bancarios - Cola Circular");
        setSize(1400, 850);
        setMinimumSize(new Dimension(1000, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(COLOR_FONDO_OSCURO);
        setLocationRelativeTo(null);
        // Maximizar pero permitir redimensionamiento
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    /**
     * Método para inicializar el sistema de turnos con capacidad definida por el usuario.
     */
    private void inicializarSistema() {
        String input = JOptionPane.showInputDialog( // Solicitar capacidad de la cola
            this,
            "Ingrese la capacidad máxima de la cola:",
            "Configuración Inicial",
            JOptionPane.QUESTION_MESSAGE
        );
        try { // Validar entrada y crear sistema
            int capacidad = Integer.parseInt(input);
            if (capacidad <= 0) { // Capacidad inválida
                throw new NumberFormatException();
            }
            sistemaTurnos = new Turnos_banco(capacidad);
        } catch (Exception e) { // Manejar entrada inválida
            JOptionPane.showMessageDialog( // Mensaje de advertencia
                this,
                "Capacidad inválida. Se usará capacidad por defecto: 10",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE
            );
            sistemaTurnos = new Turnos_banco(10);
        }
    }
    
    /**
     * Método para crear y agregar los componentes gráficos a la ventana.
     */
    private void crearComponentes() {
        // Panel superior - Información y estadísticas
        add(crearPanelSuperior(), BorderLayout.NORTH);
        // Panel central - Visualización de la cola
        add(crearPanelCentral(), BorderLayout.CENTER);
        // Panel derecho - Controles
        add(crearPanelDerecho(), BorderLayout.EAST);
        // Panel inferior - Lista de turnos
        add(crearPanelInferior(), BorderLayout.SOUTH);
    }
    
    /**
     * Método para crear el panel superior con título y estadísticas.
     * @return Panel superior creado
     */
    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(COLOR_PANEL_OSCURO);
        panel.setBorder(BorderFactory.createCompoundBorder( // Bordes compuestos
            BorderFactory.createMatteBorder(0, 0, 2, 0, COLOR_PRIMARIO),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        // Título
        JLabel lblTitulo = new JLabel("SISTEMA DE TURNOS BANCARIOS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(COLOR_TEXTO_CLARO);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        // Estadísticas
        lblEstadisticas = new JLabel();
        lblEstadisticas.setFont(new Font("Arial", Font.PLAIN, 16));
        lblEstadisticas.setForeground(COLOR_TEXTO_CLARO);
        lblEstadisticas.setHorizontalAlignment(SwingConstants.CENTER);
        // Próximo turno
        lblProximoTurno = new JLabel();
        lblProximoTurno.setFont(new Font("Arial", Font.BOLD, 18));
        lblProximoTurno.setForeground(COLOR_ADVERTENCIA);
        lblProximoTurno.setHorizontalAlignment(SwingConstants.CENTER);
        // Agregar componentes al panel
        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(lblEstadisticas, BorderLayout.CENTER);
        panel.add(lblProximoTurno, BorderLayout.SOUTH);
        return panel;
    }
    
    /**
     * Método para crear el panel central que muestra la cola circular.
     * @return Panel central creado
     */
    private JPanel crearPanelCentral() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_FONDO_OSCURO);
        panel.setBorder(BorderFactory.createTitledBorder( // Borde con título
            BorderFactory.createLineBorder(COLOR_PRIMARIO, 2),
            "Visualización de la Cola Circular",
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 16),
            COLOR_TEXTO_CLARO
        ));
        panelCola = new JPanel() { // Panel personalizado para dibujar la cola
            @Override // Anular método de pintura
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarCola((Graphics2D) g);
            }
        };
        panelCola.setBackground(COLOR_PANEL_OSCURO);
        // Calcular tamaño dinámico basado en la capacidad de la cola
        int capacidad = sistemaTurnos.getCapacidad();
        int tamañoCelda = 160; // Tamaño estándar de celda
        int espaciado = 20;
        int celdaSPorFila = 6; // Máximo de celdas por fila
        int filas = (int) Math.ceil((double) capacidad / celdaSPorFila);
        // Calcular dimensiones necesarias
        int anchoNecesario = Math.min(capacidad, celdaSPorFila) * (tamañoCelda + espaciado) + 120;
        int altoNecesario = filas * (tamañoCelda + 75) + 150;
        // Establecer tamaño preferido del panel
        panelCola.setPreferredSize(new Dimension(anchoNecesario, altoNecesario));
        // Agregar panel de cola dentro de un JScrollPane
        JScrollPane scroll = new JScrollPane(panelCola);
        scroll.setBackground(COLOR_PANEL_OSCURO);
        scroll.getViewport().setBackground(COLOR_PANEL_OSCURO);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }
    
    /**
     * Método para dibujar la representación gráfica de la cola circular.
     * @param g Objeto Graphics2D para dibujar en el panel
     */
    private void dibujarCola(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        // Obtener datos de la cola
        int capacidad = sistemaTurnos.getCapacidad();
        String[] cola = sistemaTurnos.getCola();
        int inicio = sistemaTurnos.getInicio();
        int fin = sistemaTurnos.getFin();
        // Configuración fija para dibujo consistente
        int tamañoCelda = 160;
        int espaciado = 20;
        int celdasPorFila = 6;
        // Posición inicial
        int x = 60;
        int y = 100;
        int celdaEnFila = 0;
        // Dibujar cada posición de la cola
        for (int i = 0; i < capacidad; i++) {
            // Determinar color de la celda
            Color colorCelda = COLOR_CELDA_VACIA;
            Color colorTexto = COLOR_TEXTO_SECUNDARIO;
            Color colorBorde = COLOR_BORDE;
            if (cola[i] != null) { // Celda ocupada
                colorCelda = COLOR_CELDA_OCUPADA;
                colorTexto = COLOR_TEXTO_CLARO;
                colorBorde = COLOR_SECUNDARIO;
            }
            if (i == inicio && !sistemaTurnos.estaVacia()) { // Índice de inicio
                colorCelda = COLOR_EXITO;
                colorTexto = COLOR_FONDO_OSCURO;
                colorBorde = COLOR_EXITO.brighter();
            }
            if (i == fin) { // Índice de fin
                if (cola[i] == null) {
                    colorCelda = COLOR_ADVERTENCIA;
                    colorTexto = COLOR_FONDO_OSCURO;
                    colorBorde = COLOR_ADVERTENCIA.brighter();
                }
            }
            // Dibujar celda
            g.setColor(colorCelda);
            g.fillRoundRect(x, y, tamañoCelda, tamañoCelda, 15, 15);
            g.setColor(colorBorde);
            g.setStroke(new BasicStroke(3));
            g.drawRoundRect(x, y, tamañoCelda, tamañoCelda, 15, 15);
            // Dibujar índice
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.setColor(COLOR_TEXTO_CLARO);
            String indice = "[" + i + "]";
            FontMetrics fm = g.getFontMetrics();
            int anchoTexto = fm.stringWidth(indice);
            g.drawString(indice, x + (tamañoCelda - anchoTexto) / 2, y - 10);
            // Dibujar contenido
            if (cola[i] != null) { // Celda con dato
                g.setColor(colorTexto);
                String contenido = cola[i];
                // Dividir el texto en líneas
                String[] partes = contenido.split(" - ");
                int yTexto = y + tamañoCelda / 2 - 15;
                int lineHeight = 18;
                // Primera línea: "Turno #X"
                if (partes.length > 0) { // Turno
                    g.setFont(new Font("Arial", Font.BOLD, 13));
                    String turno = partes[0];
                    anchoTexto = g.getFontMetrics().stringWidth(turno);
                    g.drawString(turno, x + (tamañoCelda - anchoTexto) / 2, yTexto);
                    yTexto += lineHeight;
                }
                // Segunda línea: "Cliente:"
                if (partes.length > 1) { // Cliente
                    String clienteParte = partes[1];
                    String[] clienteInfo = clienteParte.split(": ");
                    // Etiqueta "Cliente:"
                    g.setFont(new Font("Arial", Font.PLAIN, 12));
                    String etiqueta = "Cliente:";
                    anchoTexto = g.getFontMetrics().stringWidth(etiqueta);
                    g.drawString(etiqueta, x + (tamañoCelda - anchoTexto) / 2, yTexto);
                    yTexto += lineHeight;
                    // Tercera línea: Nombre del cliente
                    if (clienteInfo.length > 1) { // Nombre
                        g.setFont(new Font("Arial", Font.BOLD, 12));
                        String nombreCliente = clienteInfo[1];
                        // Dividir nombre si es muy largo
                        if (nombreCliente.length() > 15) { // Si supera 15 caracteres
                            // Primera parte del nombre
                            String parte1 = nombreCliente.substring(0, 15);
                            anchoTexto = g.getFontMetrics().stringWidth(parte1);
                            g.drawString(parte1, x + (tamañoCelda - anchoTexto) / 2, yTexto);
                            yTexto += lineHeight;
                            // Segunda parte si existe
                            if (nombreCliente.length() > 15) { // Más de 15 caracteres
                                String parte2 = nombreCliente.substring(15);
                                if (parte2.length() > 12) { // Limitar segunda línea a 12 caracteres
                                    parte2 = parte2.substring(0, 12) + "...";
                                }
                                anchoTexto = g.getFontMetrics().stringWidth(parte2);
                                g.drawString(parte2, x + (tamañoCelda - anchoTexto) / 2, yTexto);
                            }
                        } else { // Nombre corto
                            anchoTexto = g.getFontMetrics().stringWidth(nombreCliente);
                            g.drawString(nombreCliente, x + (tamañoCelda - anchoTexto) / 2, yTexto);
                        }
                    }
                }
            } else { // Celda vacía
                g.setFont(new Font("Arial", Font.ITALIC, 14));
                g.setColor(colorTexto);
                String vacio = "vacío";
                anchoTexto = g.getFontMetrics().stringWidth(vacio);
                g.drawString(vacio, x + (tamañoCelda - anchoTexto) / 2, y + tamañoCelda / 2);
            }
            // Dibujar marcadores
            g.setFont(new Font("Arial", Font.BOLD, 14));
            if (i == inicio && !sistemaTurnos.estaVacia()) { // Índice de inicio
                g.setColor(COLOR_EXITO);
                String marcador = "▲ INICIO";
                anchoTexto = g.getFontMetrics().stringWidth(marcador);
                g.drawString(marcador, x + (tamañoCelda - anchoTexto) / 2, y + tamañoCelda + 20);
            }
            if (i == fin) { // Índice de fin
                g.setColor(COLOR_ADVERTENCIA);
                String marcador = "▼ FIN";
                anchoTexto = g.getFontMetrics().stringWidth(marcador);
                g.drawString(marcador, x + (tamañoCelda - anchoTexto) / 2, y + tamañoCelda + 40);
            }
            // Avanzar posición
            x += tamañoCelda + espaciado;
            celdaEnFila++;
            // Salto de línea después de celdasPorFila elementos
            if (celdaEnFila >= celdasPorFila) { // Nueva fila
                x = 60;
                y += tamañoCelda + 75;
                celdaEnFila = 0;
            }
        }
    }
    
    /**
     * Método para crear el panel derecho con controles y estado.
     * @return Panel derecho creado
     */
    private JPanel crearPanelDerecho() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(COLOR_PANEL_OSCURO);
        panel.setBorder(BorderFactory.createCompoundBorder( // Bordes compuestos
            BorderFactory.createMatteBorder(0, 2, 0, 0, COLOR_PRIMARIO),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panel.setPreferredSize(new Dimension(350, 0));
        // Título
        JLabel lblTitulo = new JLabel("Control de Turnos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(COLOR_TEXTO_CLARO);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitulo);
        panel.add(Box.createRigidArea(new Dimension(0, 25)));
        // Campo de texto para nombre
        JLabel lblNombre = new JLabel("Nombre del Cliente:");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblNombre.setForeground(COLOR_TEXTO_CLARO);
        lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblNombre);
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        // Campo de entrada de texto
        txtNombreCliente = new JTextField();
        txtNombreCliente.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        txtNombreCliente.setFont(new Font("Arial", Font.PLAIN, 15));
        txtNombreCliente.setBackground(COLOR_FONDO_OSCURO);
        txtNombreCliente.setForeground(COLOR_TEXTO_CLARO);
        txtNombreCliente.setCaretColor(COLOR_TEXTO_CLARO);
        txtNombreCliente.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDE, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(txtNombreCliente);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        // Botón Agregar Turno
        JButton btnAgregar = crearBoton("📝 Agregar Turno", COLOR_EXITO);
        btnAgregar.addActionListener(e -> agregarTurno());
        panel.add(btnAgregar);
        panel.add(Box.createRigidArea(new Dimension(0, 12)));
        // Botón Atender Turno
        JButton btnAtender = crearBoton("🔔 Atender Turno", COLOR_PELIGRO);
        btnAtender.addActionListener(e -> atenderTurno());
        panel.add(btnAtender);
        panel.add(Box.createRigidArea(new Dimension(0, 12)));
        // Botón Ver Próximo
        JButton btnVerProximo = crearBoton("👁️ Ver Próximo", COLOR_SECUNDARIO);
        btnVerProximo.addActionListener(e -> verProximoTurno());
        panel.add(btnVerProximo);
        panel.add(Box.createRigidArea(new Dimension(0, 25)));
        // Separador
        JSeparator sep = new JSeparator();
        sep.setForeground(COLOR_BORDE);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        panel.add(sep);
        panel.add(Box.createRigidArea(new Dimension(0, 25)));
        // Botón Demostración
        JButton btnDemo = crearBoton("🎮 Demostración", COLOR_ADVERTENCIA);
        btnDemo.addActionListener(e -> ejecutarDemostracion());
        panel.add(btnDemo);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        // Área de estado
        JLabel lblEstado = new JLabel("Estado del Sistema:");
        lblEstado.setFont(new Font("Arial", Font.BOLD, 14));
        lblEstado.setForeground(COLOR_TEXTO_CLARO);
        lblEstado.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblEstado);
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        // Área de texto para estado
        txtAreaEstado = new JTextArea(10, 20);
        txtAreaEstado.setEditable(false);
        txtAreaEstado.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtAreaEstado.setBackground(COLOR_FONDO_OSCURO);
        txtAreaEstado.setForeground(COLOR_TEXTO_CLARO);
        txtAreaEstado.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollEstado = new JScrollPane(txtAreaEstado);
        scrollEstado.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        scrollEstado.setBorder(BorderFactory.createLineBorder(COLOR_BORDE, 1));
        scrollEstado.getViewport().setBackground(COLOR_FONDO_OSCURO);
        panel.add(scrollEstado);
        return panel;
    }
    
    /**
     * Método para crear el panel inferior que muestra la lista de turnos pendientes.
     * @return Panel inferior creado
     */
    private JPanel crearPanelInferior() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_FONDO_OSCURO);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_PRIMARIO, 2),
            "Lista de Turnos Pendientes",
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 16),
            COLOR_TEXTO_CLARO
        ));
        panel.setPreferredSize(new Dimension(0, 180));
        // Área de texto para lista de turnos
        txtAreaTurnos = new JTextArea();
        txtAreaTurnos.setEditable(false);
        txtAreaTurnos.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtAreaTurnos.setBackground(COLOR_PANEL_OSCURO);
        txtAreaTurnos.setForeground(COLOR_TEXTO_CLARO);
        txtAreaTurnos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Agregar área de texto dentro de un JScrollPane
        JScrollPane scroll = new JScrollPane(txtAreaTurnos);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(COLOR_PANEL_OSCURO);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }
    
    /**
     * Método para crear un botón con estilo personalizado.
     * @param texto Texto del botón
     * @param color Color de fondo del botón
     * @return Botón creado con estilo personalizado
     */
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 15));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        // Efecto hover
        boton.addMouseListener(new MouseAdapter() {
            /**
             * Método llamado cuando el mouse entra en el botón.
             * @param e Evento del mouse
             */
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(color.brighter());
            }
            /**
             * Método llamado cuando el mouse sale del botón.
             * @param e Evento del mouse
             */
            public void mouseExited(MouseEvent e) {
                boton.setBackground(color);
            }
        });
        
        return boton;
    }
    
    /**
     * Metodo para agregar un nuevo turno a la cola.
     */
    private void agregarTurno() {
        String nombre = txtNombreCliente.getText().trim();
        if (nombre.isEmpty()) { // Validar campo vacío
            JOptionPane.showMessageDialog(
                this,
                "Por favor ingrese el nombre del cliente",
                "Campo Requerido",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        // Intentar agregar el turno
        int numeroTurno = sistemaTurnos.enqueue(nombre);
        if (numeroTurno == -1) { // Cola llena
            JOptionPane.showMessageDialog(
                this,
                "Cola llena. No se pueden agregar más turnos.",
                "Cola Llena",
                JOptionPane.ERROR_MESSAGE
            );
        } else { // Turno agregado exitosamente
            JOptionPane.showMessageDialog(
                this,
                "Turno #" + numeroTurno + " registrado exitosamente\nCliente: " + nombre,
                "Turno Agregado",
                JOptionPane.INFORMATION_MESSAGE
            );
            txtNombreCliente.setText("");
        }
        actualizarInterfaz();
    }
    
    /**
     * Método para atender el próximo turno en la cola.
     */
    private void atenderTurno() {
        String turno = sistemaTurnos.dequeue();
        if (turno == null) { // Cola vacía
            JOptionPane.showMessageDialog(
                this,
                "No hay turnos pendientes para atender",
                "Cola Vacía",
                JOptionPane.INFORMATION_MESSAGE
            );
        } else { // Turno atendido exitosamente
            JOptionPane.showMessageDialog(
                this,
                "Atendiendo:\n" + turno,
                "Turno Atendido",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
        actualizarInterfaz();
    }
    
    /**
     * Metodo para ver el próximo turno sin atenderlo.
     */
    private void verProximoTurno() {
        String turno = sistemaTurnos.peek();
        
        if (turno == null) {
            JOptionPane.showMessageDialog(
                this,
                "No hay turnos pendientes",
                "Cola Vacía",
                JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Próximo turno:\n" + turno,
                "Próximo en la Cola",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
    
    /**
     * Método para ejecutar una demostración automática del sistema.
     */
    private void ejecutarDemostracion() {
        int respuesta = JOptionPane.showConfirmDialog(
            this,
            "¿Desea ejecutar una demostración automática?\nEsto agregará y atenderá varios turnos.",
            "Confirmar Demostración",
            JOptionPane.YES_NO_OPTION
        );
        if (respuesta != JOptionPane.YES_OPTION) { // Cancelar demo
            return;
        }
        // Deshabilitar controles durante la demo
        setEnabled(false);
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                String[] clientes = {"Ana García", "Luis Martínez", "María López", 
                                    "Carlos Ruiz", "Elena Torres", "Pedro Sánchez"};
                // Agregar turnos
                for (int i = 0; i < Math.min(clientes.length, sistemaTurnos.getCapacidad()); i++) { // Respetar capacidad
                    sistemaTurnos.enqueue(clientes[i]);
                    SwingUtilities.invokeLater(() -> actualizarInterfaz());
                    Thread.sleep(1000);
                }
                Thread.sleep(1500);
                // Atender algunos turnos
                for (int i = 0; i < Math.min(3, sistemaTurnos.getTamanio()); i++) { // Atender hasta 3 turnos
                    sistemaTurnos.dequeue();
                    SwingUtilities.invokeLater(() -> actualizarInterfaz());
                    Thread.sleep(1000);
                }
                Thread.sleep(1500);
                // Agregar más turnos (demuestra comportamiento circular)
                String[] nuevosClientes = {"Laura Díaz", "Miguel Ángel", "Sofia Romero"};
                for (int i = 0; i < Math.min(nuevosClientes.length, 
                        sistemaTurnos.getCapacidad() - sistemaTurnos.getTamanio()); i++) { // Respetar capacidad
                    sistemaTurnos.enqueue(nuevosClientes[i]);
                    SwingUtilities.invokeLater(() -> actualizarInterfaz());
                    Thread.sleep(1000);
                }
                return null;
            }
            
            /**
             * Método llamado al completar la demostración.
             */
            @Override
            protected void done() {
                setEnabled(true);
                JOptionPane.showMessageDialog(
                    Main.this,
                    "Demostración completada.\nObserve cómo los nuevos turnos\nse insertaron en las posiciones liberadas.",
                    "Demo Finalizada",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        };
        worker.execute();
    }
    
    /**
     * Método para actualizar todos los elementos de la interfaz gráfica.
     */
    private void actualizarInterfaz() {
        // Actualizar estadísticas
        int tamanio = sistemaTurnos.getTamanio();
        int capacidad = sistemaTurnos.getCapacidad();
        int disponibles = capacidad - tamanio;
        // Actualizar etiqueta de estadísticas
        lblEstadisticas.setText(String.format(
            "Turnos: %d/%d  |  Disponibles: %d  |  Estado: %s",
            tamanio, capacidad, disponibles,
            sistemaTurnos.estaVacia() ? "VACÍA" : (sistemaTurnos.estaLlena() ? "LLENA" : "NORMAL")
        ));
        // Actualizar próximo turno
        String proximo = sistemaTurnos.peek();
        if (proximo != null) { // Hay próximo turno
            lblProximoTurno.setText("▶ Próximo: " + proximo);
        } else { // No hay turnos
            lblProximoTurno.setText("▶ No hay turnos pendientes");
        }
        // Actualizar lista de turnos
        String[] turnos = sistemaTurnos.obtenerTurnosOrdenados();
        StringBuilder sb = new StringBuilder();
        if (turnos.length == 0) { // Cola vacía
            sb.append("No hay turnos en la cola\n");
        } else { // Listar turnos
            for (int i = 0; i < turnos.length; i++) { // Recorrer turnos
                sb.append(String.format("%d. %s\n", i + 1, turnos[i]));
            }
        }
        txtAreaTurnos.setText(sb.toString());
        // Actualizar estado del sistema
        txtAreaEstado.setText(String.format(
            "═══ ESTADO DEL SISTEMA ═══\n" +
            "Capacidad:     %d\n" +
            "Ocupados:      %d\n" +
            "Disponibles:   %d\n" +
            "Índice Inicio: %d\n" +
            "Índice Fin:    %d\n" +
            "¿Vacía?:       %s\n" +
            "¿Llena?:       %s\n",
            capacidad, tamanio, disponibles,
            sistemaTurnos.getInicio(),
            sistemaTurnos.getFin(),
            sistemaTurnos.estaVacia() ? "Sí" : "No",
            sistemaTurnos.estaLlena() ? "Sí" : "No"
        ));
        // Redibujar cola
        panelCola.repaint();
    }
    
    /**
     * Método principal para iniciar la aplicación.
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Configurar Look and Feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { // Manejar excepción
            e.printStackTrace();
        }
        // Ejecutar la aplicación en el Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}
