/**
 * @author Cesar de Jesus Becerra Vera
 * @since 28 de Enero de 2026
 * @version 1.0
 * ARCHIVO: Pilas.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Editor de Texto con funcionalidades de edición y un sistema de deshacer basado en pilas (LIFO).
 */

// Paquete principal
package app;

// Importaciones necesarias
import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa una acción realizada en el editor de texto.
 */
class Accion {
    // Tipo de acción y estado anterior del documento
    String tipoAccion;
    String estadoAnterior;

    /**
     * Constructor de la clase Accion.
     * @param tipo tipo de acción realizada
     * @param estado estado anterior del documento antes de la acción
     */
    public Accion(String tipo, String estado) {
        this.tipoAccion = tipo;
        this.estadoAnterior = estado;
    }
}

/**
 * Clase principal del editor de texto con pila de deshacer.
 */
public class Pilas extends JFrame {
    // Componentes de la interfaz gráfica
    private JTextPane documentoTexto;  
    private JTextArea historialTexto;  
    private JButton btnInsertar, btnEliminar, btnCopiar, btnPegar, btnCortar;
    private JButton btnNegrita, btnCursiva, btnSubrayar, btnDeshacer, btnMostrar;
    
    Accion pila[];  // Pila de acciones
    int capacidad = 10; // Capacidad máxima fija de 10 elementos
    int tope = 0; // Índice del tope de la pila
    
    String portapapeles = "";  // Simula el portapapeles
    JLabel lblEstado; // Label para mostrar el estado de la pila
    
    /**
     * Constructor de la clase Pilas.
     */
    public Pilas() {
        pila = new Accion[capacidad]; // Inicializar la pila
        initComponents(); // Inicializar componentes de la interfaz
    }

    /**
     * Inicializa los componentes de la interfaz gráfica.
     */
    public void initComponents() {
        // Configuración de la ventana principal
        setTitle("Editor de Texto con Pila (LIFO)");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Panel superior con título e información
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        
        // Título
        JLabel lblTitulo = new JLabel("Editor de Documento - LIFO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);
        
        // Estado de la pila
        lblEstado = new JLabel("Acciones en pila: 0/10", SwingConstants.CENTER);
        lblEstado.setFont(new Font("Arial", Font.PLAIN, 12));
        lblEstado.setForeground(new Color(60, 60, 60));
        panelSuperior.add(lblEstado, BorderLayout.CENTER);
        
        // Agregar panel superior a la ventana
        add(panelSuperior, BorderLayout.NORTH);
        
        // Panel central dividido
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(550);
        
        // Panel izquierdo - DOCUMENTO
        JPanel panelDocumento = new JPanel(new BorderLayout());
        panelDocumento.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5));
        
        // Título del documento
        JLabel lblDoc = new JLabel("📄 DOCUMENTO", SwingConstants.CENTER);
        lblDoc.setFont(new Font("Arial", Font.BOLD, 14));
        lblDoc.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        panelDocumento.add(lblDoc, BorderLayout.NORTH);
        
        // Área de texto del documento
        documentoTexto = new JTextPane();
        documentoTexto.setContentType("text/html");
        documentoTexto.setFont(new Font("Arial", Font.PLAIN, 14));
        documentoTexto.setEditable(false);  // No permite escribir directamente
        documentoTexto.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        documentoTexto.setText("<html><head><style>body, b, i, u { font-family: Arial; font-size: 14px; }</style></head><body></body></html>");  // Documento vacío al inicio
        
        // Scroll para el área de texto
        JScrollPane scrollDoc = new JScrollPane(documentoTexto);
        scrollDoc.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelDocumento.add(scrollDoc, BorderLayout.CENTER);
        
        // Agregar panel del documento al split pane
        splitPane.setLeftComponent(panelDocumento);
        
        // Panel derecho - HISTORIAL
        JPanel panelHistorial = new JPanel(new BorderLayout());
        panelHistorial.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10));
        
        // Título del historial
        JLabel lblHist = new JLabel("📋 HISTORIAL DE ACCIONES", SwingConstants.CENTER);
        lblHist.setFont(new Font("Arial", Font.BOLD, 14));
        lblHist.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        panelHistorial.add(lblHist, BorderLayout.NORTH);
        
        // Área de texto del historial
        historialTexto = new JTextArea();
        historialTexto.setEditable(false);
        historialTexto.setFont(new Font("Monospaced", Font.PLAIN, 12));
        historialTexto.setBackground(new Color(245, 245, 245));
        
        // Scroll para el área de historial
        JScrollPane scrollHist = new JScrollPane(historialTexto);
        scrollHist.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelHistorial.add(scrollHist, BorderLayout.CENTER);
        
        // Agregar panel del historial al split pane
        splitPane.setRightComponent(panelHistorial);
        add(splitPane, BorderLayout.CENTER);
        
        // Panel inferior con botones
        JPanel panelBotones = new JPanel(new GridLayout(2, 5, 5, 5));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        
        // Botones de acción
        btnInsertar = new JButton("📝 Insertar");
        btnEliminar = new JButton("✂️ Eliminar");
        btnCopiar = new JButton("📋 Copiar");
        btnPegar = new JButton("📄 Pegar");
        btnCortar = new JButton("✂️ Cortar");
        btnNegrita = new JButton("B Negrita");
        btnCursiva = new JButton("I Cursiva");
        btnSubrayar = new JButton("U Subrayar");
        btnDeshacer = new JButton("↶ Deshacer");
        btnMostrar = new JButton("📊 Ver Pila");
        
        // Estilos de los botones
        btnNegrita.setFont(new Font("Arial", Font.BOLD, 11));
        btnCursiva.setFont(new Font("Arial", Font.ITALIC, 11));
        
        // Colores personalizados
        Color colorAccion = new Color(70, 130, 180);
        Color colorDeshacer = new Color(220, 20, 60);
        
        for (JButton btn : new JButton[]{btnInsertar, btnEliminar, btnCopiar, btnPegar, btnCortar, 
                                        btnNegrita, btnCursiva, btnSubrayar, btnMostrar}) { // Ciclo para aplicar estilo
            btn.setBackground(colorAccion);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
        }
        
        // Estilo específico para el botón Deshacer
        btnDeshacer.setBackground(colorDeshacer);
        btnDeshacer.setForeground(Color.WHITE);
        btnDeshacer.setFocusPainted(false);
        btnDeshacer.setFont(new Font("Arial", Font.BOLD, 12));
        
        // Agregar botones al panel
        panelBotones.add(btnInsertar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCopiar);
        panelBotones.add(btnPegar);
        panelBotones.add(btnCortar);
        panelBotones.add(btnNegrita);
        panelBotones.add(btnCursiva);
        panelBotones.add(btnSubrayar);
        panelBotones.add(btnDeshacer);
        panelBotones.add(btnMostrar);
        
        // Agregar panel de botones a la ventana
        add(panelBotones, BorderLayout.SOUTH);
        
        // Centrar la ventana
        setLocationRelativeTo(null);
        agregarListeners();
        actualizarEstado();
    }

    /**
     * Agrega una acción a la pila (PUSH)
     * @param tipoAccion Tipo de acción realizada
     * @param estadoAnterior Estado del documento antes de la acción
     */
    private void push(String tipoAccion, String estadoAnterior) {
        if (tope < capacidad) { // Verificar si hay espacio en la pila
            pila[tope] = new Accion(tipoAccion, estadoAnterior); // Agregar acción a la pila
            tope++;
            historialTexto.append("✓ " + tipoAccion + "\n");
            actualizarEstado();
        } else { // Pila llena
            historialTexto.append("✗ Pila llena (10/10)\n");
            JOptionPane.showMessageDialog(this, 
                "La pila está llena. Debe deshacer acciones antes de agregar nuevas.",
                "Pila Llena", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Deshace la última acción realizada (POP)
     */
    private void deshacer() {
        if (tope > 0) { // Verificar si la pila no está vacía
            tope--;
            Accion accion = pila[tope]; // Obtener la acción a deshacer
            
            // Restaurar el estado anterior del documento
            documentoTexto.setEditable(true);
            documentoTexto.setText(accion.estadoAnterior);
            documentoTexto.setEditable(false);
            
            historialTexto.append("↶ DESHECHO: " + accion.tipoAccion + "\n");
            pila[tope] = null; // Eliminar el elemento del tope
            actualizarEstado();
        } else { // Pila vacía
            historialTexto.append("✗ Pila vacía - No hay acciones para deshacer\n");
            JOptionPane.showMessageDialog(this, 
                "No hay acciones para deshacer.",
                "Pila Vacía", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Muestra el estado actual de la pila en el área de historial (TOP)
     */
    private void mostrarPila() {
        historialTexto.append("\n" + "=".repeat(40) + "\n");
        historialTexto.append("ESTADO DE LA PILA (LIFO)\n");
        historialTexto.append("=".repeat(40) + "\n");
        
        if (tope == 0) { // Pila vacía
            historialTexto.append("Pila vacía\n");
        } else { // Mostrar elementos de la pila
            historialTexto.append("Acciones: " + tope + "/10\n\n");
            historialTexto.append("TOPE ↓\n");
            for (int i = tope - 1; i >= 0; i--) {
                String marca = (i == tope - 1) ? "→ " : "  ";
                historialTexto.append(marca + "[" + (i+1) + "] " + pila[i].tipoAccion + "\n");
            }
            historialTexto.append("BASE ↑\n");
        }
        historialTexto.append("=".repeat(40) + "\n\n");
    }

    /**
     * Actualiza el estado de la pila en la etiqueta lblEstado
     */
    private void actualizarEstado() {
        String estado = " ";
        if (tope == capacidad) { // Pila llena
            estado = " (LLENA)";
            lblEstado.setForeground(Color.RED);
        } else if (tope == 0) { // Pila vacía
            estado = " (VACÍA)";
            lblEstado.setForeground(new Color(100, 100, 100));
        } else { // Pila con elementos
            lblEstado.setForeground(new Color(0, 128, 0));
        }
        lblEstado.setText("Acciones en pila: " + tope + "/10" + estado);
    }

    /**
     * Inserta texto en el documento.
     */
    private void insertarTexto() {
        if (tope >= capacidad) { // Verificar si la pila está llena ANTES de hacer cualquier cosa
            JOptionPane.showMessageDialog(this, 
                "La pila está llena (10/10).\nDebe deshacer acciones antes de agregar nuevas.",
                "Pila Llena", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String texto = JOptionPane.showInputDialog(this, "Ingrese el texto a insertar:");
        if (texto != null && !texto.isEmpty()) { // Verificar que se ingresó texto
            String estadoAnterior = documentoTexto.getText();
            String contenidoActual = documentoTexto.getText();
            
            // Extraer el contenido del body
            String body = "";
            if (contenidoActual.contains("<body")) { // Buscar etiquetas body
                int inicio = contenidoActual.indexOf(">", contenidoActual.indexOf("<body")) + 1;
                int fin = contenidoActual.lastIndexOf("</body>");
                if (inicio > 0 && fin > inicio) { // Extraer contenido entre body
                    body = contenidoActual.substring(inicio, fin);
                }
            }
            
            // Si ya hay contenido, agregar salto de línea
            String nuevoContenido = body.trim().isEmpty() ? texto : body + "<br>" + texto;
            
            // Actualizar el documento con el nuevo contenido
            documentoTexto.setEditable(true);
            documentoTexto.setText("<html><head><style>body, b, i, u { font-family: Arial; font-size: 14px; }</style></head><body>" + nuevoContenido + "</body></html>");
            documentoTexto.setEditable(false);
            
            push("Insertar texto: \"" + texto + "\"", estadoAnterior); // Agregar acción a la pila
        }
    }
    
    /**
     * Elimina el texto seleccionado en el documento.
     */
    private void eliminarTexto() {
        if (tope >= capacidad) { // Verificar si la pila está llena ANTES de hacer cualquier cosa
            JOptionPane.showMessageDialog(this, 
                "La pila está llena (10/10).\nDebe deshacer acciones antes de agregar nuevas.",
                "Pila Llena", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Obtener el texto seleccionado
        String seleccion = documentoTexto.getSelectedText();
        
        if (seleccion != null && !seleccion.isEmpty()) { // Verificar que haya selección
            String estadoAnterior = documentoTexto.getText();
            
            // Eliminar el texto seleccionado
            documentoTexto.setEditable(true);
            documentoTexto.replaceSelection("");
            documentoTexto.setEditable(false);
            
            push("Eliminar texto seleccionado", estadoAnterior); // Agregar acción a la pila
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar texto para eliminar."); // Mensaje si no hay selección
        }
    }
    
    /**
     * Copia el texto seleccionado al portapapeles simulado.
     */
    private void copiarTexto() {
        String seleccion = documentoTexto.getSelectedText();
        if (seleccion != null && !seleccion.isEmpty()) { // Verificar que haya selección
            portapapeles = seleccion;
            historialTexto.append("ℹ️ Texto copiado al portapapeles\n");
        } else { // No hay selección
            JOptionPane.showMessageDialog(this, "Debe seleccionar texto para copiar.");
        }
    }
    
    /**
     * Pega el texto del portapapeles en el documento.
     */
    private void pegarTexto() {
        if (tope >= capacidad) { // Verificar si la pila está llena ANTES de hacer cualquier cosa
            JOptionPane.showMessageDialog(this, 
                "La pila está llena (10/10).\nDebe deshacer acciones antes de agregar nuevas.",
                "Pila Llena", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!portapapeles.isEmpty()) { // Verificar que el portapapeles no esté vacío
            String estadoAnterior = documentoTexto.getText();
            String contenidoActual = documentoTexto.getText();
            
            // Extraer el contenido del body
            String body = "";
            if (contenidoActual.contains("<body")) { // Buscar etiquetas body
                int inicio = contenidoActual.indexOf(">", contenidoActual.indexOf("<body")) + 1;
                int fin = contenidoActual.lastIndexOf("</body>");
                if (inicio > 0 && fin > inicio) {
                    body = contenidoActual.substring(inicio, fin);
                }
            }
            
            // Si ya hay contenido, agregar salto de línea
            String nuevoContenido = body.trim().isEmpty() ? portapapeles : body + "<br>" + portapapeles;
            
            // Actualizar el documento con el nuevo contenido
            documentoTexto.setEditable(true);
            documentoTexto.setText("<html><head><style>body, b, i, u { font-family: Arial; font-size: 14px; }</style></head><body>" + nuevoContenido + "</body></html>");
            documentoTexto.setEditable(false);
            
            push("Pegar texto: \"" + portapapeles + "\"", estadoAnterior); // Agregar acción a la pila
        } else {
            JOptionPane.showMessageDialog(this, "El portapapeles está vacío. Copie texto primero.");
        }
    }
    
    private void cortarTexto() {
        if (tope >= capacidad) { // Verificar si la pila está llena ANTES de hacer cualquier cosa
            JOptionPane.showMessageDialog(this, 
                "La pila está llena (10/10).\nDebe deshacer acciones antes de agregar nuevas.",
                "Pila Llena", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String seleccion = documentoTexto.getSelectedText();
        if (seleccion != null && !seleccion.isEmpty()) { // Verificar que haya selección
            String estadoAnterior = documentoTexto.getText();
            portapapeles = seleccion;
            
            documentoTexto.setEditable(true);
            documentoTexto.replaceSelection("");
            documentoTexto.setEditable(false);
            
            push("Cortar texto", estadoAnterior); // Agregar acción a la pila
        } else { // No hay selección
            JOptionPane.showMessageDialog(this, "Debe seleccionar texto para cortar.");
        }
    }
    
    /**
     * Aplica formato de negrita al texto seleccionado.
     */
    private void aplicarNegrita() {
        if (tope >= capacidad) { // Verificar si la pila está llena ANTES de hacer cualquier cosa
            JOptionPane.showMessageDialog(this, 
                "La pila está llena (10/10).\nDebe deshacer acciones antes de agregar nuevas.",
                "Pila Llena", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Obtener el texto seleccionado
        String seleccion = documentoTexto.getSelectedText();
        
        if (seleccion != null && !seleccion.isEmpty()) { // Verificar que haya selección
            String estadoAnterior = documentoTexto.getText();
            String contenidoActual = documentoTexto.getText();
            
            // Limpiar etiquetas HTML de la selección
            String seleccionLimpia = seleccion.replaceAll("<[^>]*>", "");
            
            // Reemplazar en el contenido
            String nuevoContenido = contenidoActual.replace(seleccion, "<b>" + seleccionLimpia + "</b>");
            
            // Actualizar el documento con el nuevo contenido
            documentoTexto.setEditable(true);
            documentoTexto.setText(nuevoContenido);
            documentoTexto.setEditable(false);
            
            push("Aplicar negrita", estadoAnterior); // Agregar acción a la pila
        } else { // No hay selección
            JOptionPane.showMessageDialog(this, "Debe seleccionar texto para aplicar negrita.");
        }
    }
    
    /**
     * Aplica formato de cursiva al texto seleccionado.
     */
    private void aplicarCursiva() {
        if (tope >= capacidad) { // Verificar si la pila está llena ANTES de hacer cualquier cosa
            JOptionPane.showMessageDialog(this, 
                "La pila está llena (10/10).\nDebe deshacer acciones antes de agregar nuevas.",
                "Pila Llena", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Obtener el texto seleccionado
        String seleccion = documentoTexto.getSelectedText();
        
        if (seleccion != null && !seleccion.isEmpty()) { // Verificar que haya selección
            String estadoAnterior = documentoTexto.getText();
            String contenidoActual = documentoTexto.getText();
            
            // Limpiar etiquetas HTML de la selección
            String seleccionLimpia = seleccion.replaceAll("<[^>]*>", "");
            
            // Reemplazar en el contenido
            String nuevoContenido = contenidoActual.replace(seleccion, "<i>" + seleccionLimpia + "</i>");
            
            // Actualizar el documento con el nuevo contenido
            documentoTexto.setEditable(true);
            documentoTexto.setText(nuevoContenido);
            documentoTexto.setEditable(false);
            
            push("Aplicar cursiva", estadoAnterior); // Agregar acción a la pila
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar texto para aplicar cursiva.");
        }
    }
    
    /**
     * Aplica formato de subrayado al texto seleccionado.
     */
    private void aplicarSubrayado() {
        if (tope >= capacidad) { // Verificar si la pila está llena ANTES de hacer cualquier cosa
            JOptionPane.showMessageDialog(this, 
                "La pila está llena (10/10).\nDebe deshacer acciones antes de agregar nuevas.",
                "Pila Llena", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Obtener el texto seleccionado
        String seleccion = documentoTexto.getSelectedText();
        
        if (seleccion != null && !seleccion.isEmpty()) { // Verificar que haya selección
            String estadoAnterior = documentoTexto.getText();
            String contenidoActual = documentoTexto.getText();
            
            // Limpiar etiquetas HTML de la selección
            String seleccionLimpia = seleccion.replaceAll("<[^>]*>", "");
            
            // Reemplazar en el contenido
            String nuevoContenido = contenidoActual.replace(seleccion, "<u>" + seleccionLimpia + "</u>");
            
            // Actualizar el documento con el nuevo contenido
            documentoTexto.setEditable(true);
            documentoTexto.setText(nuevoContenido);
            documentoTexto.setEditable(false);
            
            push("Aplicar subrayado", estadoAnterior); // Agregar acción a la pila
        } else { // No hay selección
            JOptionPane.showMessageDialog(this, "Debe seleccionar texto para subrayar.");
        }
    }

    /**
     * Agrega los listeners a los botones. EVENTOS
     */
    private void agregarListeners() {
        btnInsertar.addActionListener(e -> insertarTexto());
        btnEliminar.addActionListener(e -> eliminarTexto());
        btnCopiar.addActionListener(e -> copiarTexto());
        btnPegar.addActionListener(e -> pegarTexto());
        btnCortar.addActionListener(e -> cortarTexto());
        btnNegrita.addActionListener(e -> aplicarNegrita());
        btnCursiva.addActionListener(e -> aplicarCursiva());
        btnSubrayar.addActionListener(e -> aplicarSubrayado());
        btnDeshacer.addActionListener(e -> deshacer());
        btnMostrar.addActionListener(e -> mostrarPila());
    }

    /**
     * Método principal para iniciar la aplicación.
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { // Iniciar en el hilo de eventos de Swing
            Pilas ventana = new Pilas(); // Crear instancia de la ventana
            ventana.setVisible(true); // Hacer visible la ventana
        });
    }
}

