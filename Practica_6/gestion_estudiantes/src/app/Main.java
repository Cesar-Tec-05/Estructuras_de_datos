/**
 * @author Cesar de Jesus Becerra Vera
 * @since 26 de Febrero de 2026
 * @version 1.0
 * ARCHIVO: Main.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase Main que maneja la interfaz gráfica de la aplicación de gestión de estudiantes.
 */

/**
 * Paquete app que contiene la clase Main para la aplicación de gestión de estudiantes.
 */
package app;

// Importaciones necesarias para la interfaz gráfica y la lógica de gestión de estudiantes
import back.Gestion_estudiantes;
import back.Nodo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Clase Main que extiende JFrame y maneja la interfaz gráfica de la aplicación de gestión de estudiantes.
 */
public class Main extends JFrame {
    // Colores
    private static final Color DARK_BG = new Color(45, 45, 48);
    private static final Color DARKER_BG = new Color(30, 30, 30);
    private static final Color PANEL_LEFT_BG = new Color(28, 40, 45);
    private static final Color PANEL_RIGHT_BG = new Color(60, 63, 65);
    private static final Color BUTTON_PRIMARY = new Color(52, 152, 219); 
    private static final Color BUTTON_SUCCESS = new Color(46, 204, 113); 
    private static final Color BUTTON_DANGER = new Color(231, 76, 60); 
    private static final Color BUTTON_WARNING = new Color(241, 196, 15); 
    private static final Color BUTTON_INFO = new Color(155, 89, 182); 
    private static final Color BUTTON_SECONDARY = new Color(52, 73, 94); 
    private static final Color TEXT_COLOR = new Color(220, 220, 220);
    private static final Color TABLE_BG = new Color(55, 55, 55);
    private static final Color TABLE_HEADER = new Color(70, 70, 70);

    // Componentes de la interfaz
    private Gestion_estudiantes gestion;
    private Nodo nodoActual;

    // Panel izquierdo
    private JLabel lblFoto;
    private JLabel lblNombreDisplay;
    private JLabel lblCarreraDisplay;
    private JLabel lblCorreoDisplay;
    private JLabel lblEstadoDisplay;
    private JButton btnPrimero, btnAnterior, btnEliminar, btnSiguiente, btnUltimo;
    
    // Panel derecho - ahora solo muestra información
    private JTable tablaMaterias;
    private DefaultTableModel modeloTabla;
    private JLabel lblPromedioFinal;
    
    // Botones superiores
    private JButton btnInsertarAlumno;
    private JButton btnDarBaja;
    private JButton btnMostrarAlumno;
    private JButton btnReactivar;
    
    /**
     * Constructor de la clase Main que inicializa la interfaz gráfica
     */
    public Main() {
        // Inicializar la gestión de estudiantes y el nodo actual
        gestion = new Gestion_estudiantes();
        nodoActual = null;
        // Configurar la ventana principal
        setTitle("Gestión de Estudiantes");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(DARK_BG);
        // Crear componentes
        crearPanelSuperior();
        crearPanelIzquierdo();
        crearPanelDerecho();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Método para crear el panel superior con los botones de acción (Registrar, Dar de baja, Buscar, Reactivar)
     */
    private void crearPanelSuperior() {
        // Panel superior con botones de acción
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelSuperior.setBackground(DARKER_BG);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        // Crear botones
        btnInsertarAlumno = crearBoton("Registrar Alumno", BUTTON_SUCCESS);
        btnInsertarAlumno.addActionListener(e -> abrirVentanaRegistro());
        // Botón para dar de baja a un estudiante por correo
        btnDarBaja = crearBoton("Dar de Baja", BUTTON_DANGER);
        btnDarBaja.addActionListener(e -> darDeBaja());
        // Botón para reactivar a un estudiante dado de baja por correo
        btnReactivar = crearBoton("Reactivar Alumno", BUTTON_INFO);
        btnReactivar.addActionListener(e -> reactivarAlumno());
        // Botón para mostrar un estudiante por correo
        btnMostrarAlumno = crearBoton("Buscar Alumno", BUTTON_WARNING);
        btnMostrarAlumno.addActionListener(e -> mostrarAlumno());
        // Agregar botones al panel superior
        panelSuperior.add(btnInsertarAlumno);
        panelSuperior.add(btnDarBaja);
        panelSuperior.add(btnReactivar);
        panelSuperior.add(btnMostrarAlumno);
        add(panelSuperior, BorderLayout.NORTH);
    }
    
    /**
     * Método para crear un botón
     * @param texto el texto que se mostrará en el botón
     * @param color el color de fondo del botón
     * @return el botón creado con el estilo aplicado
     */
    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setBackground(color);
        btn.setForeground(TEXT_COLOR);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
    
    /**
     * Método para crear el panel izquierdo que muestra la foto y datos del estudiante actual
     */
    private void crearPanelIzquierdo() {
        // Panel izquierdo con foto y datos del estudiante
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BorderLayout());
        panelIzquierdo.setPreferredSize(new Dimension(320, 550));
        panelIzquierdo.setBackground(PANEL_LEFT_BG);
        panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Panel para la foto y datos
        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
        panelDatos.setBackground(PANEL_LEFT_BG);
        panelDatos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Foto
        lblFoto = new JLabel();
        lblFoto.setPreferredSize(new Dimension(180, 180));
        lblFoto.setMaximumSize(new Dimension(180, 180));
        lblFoto.setMinimumSize(new Dimension(180, 180));
        lblFoto.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
        lblFoto.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFoto.setOpaque(true);
        lblFoto.setBackground(new Color(70, 70, 70));
        lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
        lblFoto.setIcon(new ImageIcon());
        // Datos del estudiante
        lblNombreDisplay = crearLabelInfo("Nombre: Sin selección");
        lblCarreraDisplay = crearLabelInfo("Carrera: --");
        lblCorreoDisplay = crearLabelInfo("Correo: --");
        lblEstadoDisplay = crearLabelInfo("Estado: --");
        lblEstadoDisplay.setFont(new Font("Arial", Font.BOLD, 13));
        // Agregar componentes al panel de datos
        panelDatos.add(lblFoto);
        panelDatos.add(Box.createVerticalStrut(25));
        panelDatos.add(lblNombreDisplay);
        panelDatos.add(Box.createVerticalStrut(10));
        panelDatos.add(lblCarreraDisplay);
        panelDatos.add(Box.createVerticalStrut(10));
        panelDatos.add(lblCorreoDisplay);
        panelDatos.add(Box.createVerticalStrut(10));
        panelDatos.add(lblEstadoDisplay);
        // Panel de navegación
        JPanel panelNavegacion = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 15));
        panelNavegacion.setBackground(PANEL_LEFT_BG);
        // Botones de navegación
        btnPrimero = crearBotonNavegacion("<<", BUTTON_INFO);
        btnPrimero.addActionListener(e -> irPrimero());
        // Botón para dar de baja al estudiante actual
        btnAnterior = crearBotonNavegacion("<", BUTTON_PRIMARY);
        btnAnterior.addActionListener(e -> irAnterior());
        // Botón para eliminar al estudiante actual
        btnEliminar = crearBotonNavegacion("X", BUTTON_DANGER);
        btnEliminar.setToolTipText("Dar de baja al estudiante actual");
        btnEliminar.addActionListener(e -> darDeBajaActual());
        // Botón para ir al siguiente estudiante
        btnSiguiente = crearBotonNavegacion(">", BUTTON_PRIMARY);
        btnSiguiente.addActionListener(e -> irSiguiente());
        // Botón para ir al último estudiante
        btnUltimo = crearBotonNavegacion(">>", BUTTON_INFO);
        btnUltimo.addActionListener(e -> irUltimo());
        // Agregar botones al panel de navegación
        panelNavegacion.add(btnPrimero);
        panelNavegacion.add(btnAnterior);
        panelNavegacion.add(btnEliminar);
        panelNavegacion.add(btnSiguiente);
        panelNavegacion.add(btnUltimo);
        panelIzquierdo.add(panelDatos, BorderLayout.CENTER);
        panelIzquierdo.add(panelNavegacion, BorderLayout.SOUTH);
        add(panelIzquierdo, BorderLayout.WEST);
    }
    
    /**
     * Método para crear un JLabel con estilo para mostrar información del estudiante
     * @param texto el texto que se mostrará en el JLabel
     * @return el JLabel creado con el estilo aplicado
     */
    private JLabel crearLabelInfo(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(TEXT_COLOR);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbl.setFont(new Font("Arial", Font.PLAIN, 13));
        return lbl;
    }
    
    /**
     * Método para crear un botón de navegación con estilo específico
     * @param texto el texto que se mostrará en el botón
     * @param color el color de fondo del botón
     * @return el botón creado con el estilo aplicado para navegación (tamaño más pequeño y fuente más grande)
     */
    private JButton crearBotonNavegacion(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setPreferredSize(new Dimension(50, 35));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
    
    /**
     * Método para crear el panel derecho que muestra la tabla de materias y calificaciones del estudiante actual, así como su promedio final.
     */
    private void crearPanelDerecho() {
        JPanel panelDerecho = new JPanel(new BorderLayout(10, 10));
        panelDerecho.setBackground(PANEL_RIGHT_BG);
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Título
        JLabel lblTitulo = new JLabel("Calificaciones del Estudiante", SwingConstants.CENTER);
        lblTitulo.setForeground(TEXT_COLOR);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        // Panel de tabla
        JPanel panelTabla = new JPanel(new BorderLayout(0, 10));
        panelTabla.setBackground(PANEL_RIGHT_BG);
        // Tabla de materias y calificaciones
        String[] columnas = {"Materia", "Calificación"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            /**
             * Metodo para hacer que las celdas de la tabla no sean editables directamente por el usuario.
             * @param row el índice de la fila
             * @param column el índice de la columna
             * @return false para indicar que ninguna celda es editable
             */
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // Configurar la tabla
        tablaMaterias = new JTable(modeloTabla);
        tablaMaterias.setRowHeight(35);
        tablaMaterias.setBackground(TABLE_BG);
        tablaMaterias.setForeground(TEXT_COLOR);
        tablaMaterias.setGridColor(new Color(80, 80, 80));
        tablaMaterias.setFont(new Font("Arial", Font.PLAIN, 13));
        tablaMaterias.getTableHeader().setBackground(TABLE_HEADER);
        tablaMaterias.getTableHeader().setForeground(TEXT_COLOR);
        tablaMaterias.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tablaMaterias.setSelectionBackground(new Color(100, 100, 150));
        // Agregar la tabla a un JScrollPane para permitir desplazamiento si hay muchas materias
        JScrollPane scrollTabla = new JScrollPane(tablaMaterias);
        scrollTabla.setBackground(TABLE_BG);
        scrollTabla.getViewport().setBackground(TABLE_BG);
        scrollTabla.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));
        // Panel de promedio
        JPanel panelPromedio = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelPromedio.setBackground(PANEL_RIGHT_BG);
        // Etiqueta para mostrar el título del promedio
        JLabel lblPromTitulo = new JLabel("Promedio Final:");
        lblPromTitulo.setForeground(TEXT_COLOR);
        lblPromTitulo.setFont(new Font("Arial", Font.BOLD, 15));
        // Etiqueta para mostrar el valor del promedio final, inicialmente con "--" hasta que se seleccione un estudiante con materias registradas
        lblPromedioFinal = new JLabel("--");
        lblPromedioFinal.setForeground(new Color(100, 255, 100));
        lblPromedioFinal.setFont(new Font("Arial", Font.BOLD, 18));
        // Agregar componentes al panel de promedio
        panelPromedio.add(lblPromTitulo);
        panelPromedio.add(lblPromedioFinal);
        // Botón para agregar materias
        JButton btnAgregarMateria = crearBoton("Agregar/Editar Materias", BUTTON_PRIMARY);
        btnAgregarMateria.addActionListener(e -> abrirVentanaMaterias());
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBackground(PANEL_RIGHT_BG);
        panelBoton.add(btnAgregarMateria);
        // Agregar componentes al panel derecho
        panelTabla.add(scrollTabla, BorderLayout.CENTER);
        panelTabla.add(panelPromedio, BorderLayout.SOUTH);
        // Agregar título, tabla y botón al panel derecho
        panelDerecho.add(lblTitulo, BorderLayout.NORTH);
        panelDerecho.add(panelTabla, BorderLayout.CENTER);
        panelDerecho.add(panelBoton, BorderLayout.SOUTH);
        add(panelDerecho, BorderLayout.CENTER);
    }
    
    /**
     * Metodo para navegar al primer estudiante de la lista
     */
    private void irPrimero() {
        if (!gestion.estaVacia()) { // Validar que la lista no esté vacía
            nodoActual = gestion.getPrimero();
            actualizarVisualizacion();
        } else {
            mostrarMensaje("La lista está vacía", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Metodo para navegar al estudiante anterior en la lista
     */
    private void irAnterior() {
        if (nodoActual != null && nodoActual.getAnterior() != null) { // Validar que haya un nodo actual seleccionado
            nodoActual = nodoActual.getAnterior();
            actualizarVisualizacion();
        } else {
            mostrarMensaje("No hay estudiante anterior", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Metodo para navegar al siguiente estudiante en la lista
     */
    private void irSiguiente() {
        if (nodoActual != null && nodoActual.getSiguiente() != null) { // Validar que haya un nodo actual seleccionado
            nodoActual = nodoActual.getSiguiente();
            actualizarVisualizacion();
        } else {
            mostrarMensaje("No hay estudiante siguiente", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Metodo para navegar al último estudiante de la lista
     */
    private void irUltimo() {
        if (!gestion.estaVacia()) { // Validar que la lista no esté vacía
            nodoActual = gestion.getUltimo();
            actualizarVisualizacion();
        } else {
            mostrarMensaje("La lista está vacía", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Metodo para dar de baja al estudiante actualmente seleccionado
     */
    private void darDeBajaActual() {
        if (nodoActual != null) { // Validar que haya un nodo actual seleccionado
            if (nodoActual.getEstado().equals("De baja")) {
                mostrarMensaje("Este estudiante ya está dado de baja", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Confirmar la acción de dar de baja al estudiante actual
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de dar de baja a " + nodoActual.getNombreCompleto() + "?",
                    "Confirmar baja",
                    JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) { // Si el usuario confirma, proceder a dar de baja al estudiante actual
                nodoActual.darDeBaja();
                mostrarMensaje("Estudiante dado de baja exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                actualizarVisualizacion();
            }
        } else {
            mostrarMensaje("No hay estudiante seleccionado", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Metodo para abrir la ventana de registro de un nuevo estudiante
     */
    private void abrirVentanaRegistro() {
        JDialog dialogoRegistro = new JDialog(this, "Registrar Nuevo Alumno", true);
        dialogoRegistro.setSize(650, 520);
        dialogoRegistro.setLayout(new BorderLayout(10, 10));
        dialogoRegistro.getContentPane().setBackground(DARK_BG);
        // Panel principal con dos secciones
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(DARK_BG);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        // Panel izquierdo para previsualización de foto
        JPanel panelFoto = new JPanel();
        panelFoto.setLayout(new BoxLayout(panelFoto, BoxLayout.Y_AXIS));
        panelFoto.setBackground(DARK_BG);
        panelFoto.setPreferredSize(new Dimension(200, 400));
        // Etiqueta para mostrar la previsualización de la foto
        JLabel lblPreviewFoto = new JLabel();
        lblPreviewFoto.setPreferredSize(new Dimension(180, 180));
        lblPreviewFoto.setMaximumSize(new Dimension(180, 180));
        lblPreviewFoto.setMinimumSize(new Dimension(180, 180));
        lblPreviewFoto.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
        lblPreviewFoto.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPreviewFoto.setOpaque(true);
        lblPreviewFoto.setBackground(new Color(70, 70, 70));
        lblPreviewFoto.setHorizontalAlignment(SwingConstants.CENTER);
        lblPreviewFoto.setForeground(Color.GRAY);
        lblPreviewFoto.setText("Sin foto");
        // Etiqueta para el texto de la previsualización
        JLabel lblTextoFoto = new JLabel("Vista previa");
        lblTextoFoto.setForeground(TEXT_COLOR);
        lblTextoFoto.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTextoFoto.setFont(new Font("Arial", Font.ITALIC, 12));
        // Agregar componentes al panel de foto
        panelFoto.add(Box.createVerticalStrut(20));
        panelFoto.add(lblPreviewFoto);
        panelFoto.add(Box.createVerticalStrut(10));
        panelFoto.add(lblTextoFoto);
        // Panel derecho para formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(DARK_BG);
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Configuración de GridBagConstraints para el formulario
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Campos de texto para el formulario de registro
        JTextField txtNombre = new JTextField(20);
        JTextField txtApellidoP = new JTextField(20);
        JTextField txtApellidoM = new JTextField(20);
        JTextField txtCarrera = new JTextField(20);
        JTextField txtCorreo = new JTextField(20);
        // Estilo de campos
        estilizarTextField(txtNombre);
        estilizarTextField(txtApellidoP);
        estilizarTextField(txtApellidoM);
        estilizarTextField(txtCarrera);
        estilizarTextField(txtCorreo);
        // Agregar campos al formulario
        agregarCampoFormulario(panelFormulario, "Nombre:", txtNombre, gbc, 0);
        agregarCampoFormulario(panelFormulario, "Apellido Paterno:", txtApellidoP, gbc, 1);
        agregarCampoFormulario(panelFormulario, "Apellido Materno:", txtApellidoM, gbc, 2);
        agregarCampoFormulario(panelFormulario, "Carrera:", txtCarrera, gbc, 3);
        agregarCampoFormulario(panelFormulario, "Correo:", txtCorreo, gbc, 4);
        // Botón de foto
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        JButton btnFoto = crearBoton("Seleccionar Fotografía", BUTTON_WARNING);
        btnFoto.addActionListener(e -> {
            gestion.cargarImagen();
            if (gestion.getTemporalimagen() != null) { // Validar que se haya seleccionado una imagen
                // Mostrar previsualización
                gestion.setCargarDatos(lblPreviewFoto, gestion.getTemporalimagen());
                lblPreviewFoto.setText("");
                mostrarMensaje("Imagen cargada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panelFormulario.add(btnFoto, gbc);
        // Combinar paneles
        panelPrincipal.add(panelFoto, BorderLayout.WEST);
        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(DARK_BG);
        // Botón para guardar el nuevo estudiante
        JButton btnGuardar = crearBoton("Guardar", BUTTON_SUCCESS);
        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String apellidoP = txtApellidoP.getText().trim();
            String apellidoM = txtApellidoM.getText().trim();
            String carrera = txtCarrera.getText().trim();
            String correo = txtCorreo.getText().trim();
            String rutaImagen = gestion.getTemporalimagen();
            if (nombre.isEmpty() || apellidoP.isEmpty() || apellidoM.isEmpty() || carrera.isEmpty() || correo.isEmpty()) { // Validar que todos los campos estén llenos
                mostrarMensaje("Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (gestion.agregarEstudiante(nombre, apellidoP, apellidoM, carrera, correo, rutaImagen)) { // Intentar agregar el nuevo estudiante a la gestión
                mostrarMensaje("Estudiante registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                gestion.setTemporalimagen(null);
                if (nodoActual == null) { // Si no hay un nodo actual seleccionado, mostrar el nuevo estudiante registrado
                    nodoActual = gestion.getPrimero();
                    actualizarVisualizacion();
                }
                dialogoRegistro.dispose();
            } else {
                mostrarMensaje("Error al registrar estudiante", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        // Botón para cancelar el registro y cerrar la ventana
        JButton btnCancelar = crearBoton("Cancelar", BUTTON_DANGER);
        btnCancelar.addActionListener(e -> {
            gestion.setTemporalimagen(null);
            dialogoRegistro.dispose();
        });
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        dialogoRegistro.add(panelPrincipal, BorderLayout.CENTER);
        dialogoRegistro.add(panelBotones, BorderLayout.SOUTH);
        dialogoRegistro.setLocationRelativeTo(this);
        dialogoRegistro.setVisible(true);
    }
    
    /**
     * Metodo para abrir la ventana de gestión de materias
     */
    private void abrirVentanaMaterias() {
        if (nodoActual == null) { // Validar que haya un nodo actual seleccionado antes de permitir agregar o editar materias
            mostrarMensaje("Debe seleccionar un estudiante primero", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (nodoActual.getEstado().equals("De baja")) { // Validar que el estudiante seleccionado no esté dado de baja antes de permitir agregar o editar materias
            mostrarMensaje("No puede modificar materias de un estudiante dado de baja", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Crear un diálogo para mostrar las materias y calificaciones del estudiante actual, permitiendo agregar o editar hasta 4 materias
        JDialog dialogoMaterias = new JDialog(this, "Materias de " + nodoActual.getNombre(), true);
        dialogoMaterias.setSize(450, 400);
        dialogoMaterias.setLayout(new BorderLayout(10, 10));
        dialogoMaterias.getContentPane().setBackground(DARK_BG);
        // Panel central para mostrar los campos de materia y calificación
        JPanel panelCentral = new JPanel(new GridLayout(4, 3, 10, 10));
        panelCentral.setBackground(DARK_BG);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Arreglos para los campos de texto de materias y calificaciones, permitiendo hasta 4 materias
        JTextField[] txtMaterias = new JTextField[4];
        JTextField[] txtCalificaciones = new JTextField[4];
        // Crear campos para cada materia y calificación, y cargar los datos existentes del nodo actual si los hay
        for (int i = 0; i < 4; i++) { // Permitir hasta 4 materias por estudiante
            JLabel lblNum = new JLabel("Materia " + (i + 1) + ":");
            lblNum.setForeground(TEXT_COLOR);
            txtMaterias[i] = new JTextField();
            txtCalificaciones[i] = new JTextField(5);
            estilizarTextField(txtMaterias[i]);
            estilizarTextField(txtCalificaciones[i]);
            // Cargar datos existentes
            if (i < nodoActual.getNumMaterias()) {
                txtMaterias[i].setText(nodoActual.getMateria(i));
                txtCalificaciones[i].setText(String.valueOf(nodoActual.getCalificacion(i)));
            }
            panelCentral.add(lblNum);
            panelCentral.add(txtMaterias[i]);
            panelCentral.add(txtCalificaciones[i]);
        }
        // Panel de botones para guardar o cancelar la edición de materias
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(DARK_BG);
        // Botón para guardar las materias y calificaciones ingresadas, con validación de datos antes de actualizar el nodo actual
        JButton btnGuardar = crearBoton("Guardar", BUTTON_SUCCESS);
        btnGuardar.addActionListener(e -> {
            try {
                // Validar todas las materias primero
                for (int i = 0; i < 4; i++) {
                    String materia = txtMaterias[i].getText().trim();
                    String calStr = txtCalificaciones[i].getText().trim();
                    if (!materia.isEmpty() && !calStr.isEmpty()) {
                        try {
                            double calificacion = Double.parseDouble(calStr);
                            if (calificacion < 0 || calificacion > 10) {
                                mostrarMensaje("Las calificaciones deben estar entre 0 y 10", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        } catch (NumberFormatException ex) {
                            mostrarMensaje("Calificación inválida en materia " + (i + 1), "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
                // Si todas las materias son válidas, proceder a actualizar el nodo actual con las nuevas materias y calificaciones
                for (int i = 0; i < 4; i++) {
                    String materia = txtMaterias[i].getText().trim();
                    String calStr = txtCalificaciones[i].getText().trim();
                    if (!materia.isEmpty() && !calStr.isEmpty()) {
                        double calificacion = Double.parseDouble(calStr);
                        if (i < nodoActual.getNumMaterias()) {
                            nodoActual.actualizarMateria(i, materia, calificacion);
                        } else {
                            nodoActual.agregarMateria(materia, calificacion);
                        }
                    }
                }
                mostrarMensaje("Materias actualizadas exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                actualizarVisualizacion();
                dialogoMaterias.dispose();
            } catch (Exception ex) {
                mostrarMensaje("Error al guardar materias: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        // Botón para cancelar la edición de materias y cerrar el diálogo sin guardar cambios
        JButton btnCancelar = crearBoton("Cancelar", BUTTON_SECONDARY);
        btnCancelar.addActionListener(e -> dialogoMaterias.dispose());
        // Agregar botones al panel de botones
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        // Agregar panel central y de botones al diálogo
        dialogoMaterias.add(panelCentral, BorderLayout.CENTER);
        dialogoMaterias.add(panelBotones, BorderLayout.SOUTH);
        dialogoMaterias.setLocationRelativeTo(this);
        dialogoMaterias.setVisible(true);
    }
    
    /**
     * Metodo para dar de baja a un estudiante por correo
     */
    private void darDeBaja() {
        String correo = JOptionPane.showInputDialog(this, "Ingrese el correo del estudiante a dar de baja:");
        if (correo != null && !correo.trim().isEmpty()) { // Validar que se haya ingresado un correo
            if (gestion.darDeBajaEstudiante(correo)) {
                mostrarMensaje("Estudiante dado de baja exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                if (nodoActual != null && nodoActual.getCorreo().equals(correo)) { // Si el estudiante dado de baja es el actualmente seleccionado, actualizar la visualización para reflejar el cambio de estado
                    actualizarVisualizacion();
                }
            } else {
                mostrarMensaje("No se encontró estudiante con ese correo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Metodo para reactivar a un estudiante dado de baja por correo
     */
    private void reactivarAlumno() {
        String correo = JOptionPane.showInputDialog(this, "Ingrese el correo del estudiante a reactivar:");
        if (correo != null && !correo.trim().isEmpty()) { // Validar que se haya ingresado un correo
            Nodo estudiante = gestion.buscarEstudiante(correo);
            if (estudiante == null) { // Validar que se haya encontrado un estudiante con el correo ingresado
                mostrarMensaje("No se encontró estudiante con ese correo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (estudiante.getEstado().equals("Activo")) { // Validar que el estudiante encontrado esté dado de baja antes de intentar reactivarlo
                mostrarMensaje("Este estudiante ya está activo", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (gestion.reactivarEstudiante(correo)) { // Intentar reactivar al estudiante encontrado
                mostrarMensaje("Estudiante reactivado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                if (nodoActual != null && nodoActual.getCorreo().equals(correo)) { // Si el estudiante reactivado es el actualmente seleccionado, actualizar la visualización para reflejar el cambio de estado
                    actualizarVisualizacion();
                }
            } else {
                mostrarMensaje("Error al reactivar estudiante", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Metodo para mostrar un estudiante por correo, actualizando la visualización con los datos del estudiante encontrado si se encuentra uno con el correo ingresado
     */
    private void mostrarAlumno() {
        String correo = JOptionPane.showInputDialog(this, "Ingrese el correo del estudiante a buscar:");
        if (correo != null && !correo.trim().isEmpty()) { // Validar que se haya ingresado un correo
            Nodo nodo = gestion.buscarEstudiante(correo);
            if (nodo != null) {
                nodoActual = nodo;
                actualizarVisualizacion();
                mostrarMensaje("Estudiante encontrado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                mostrarMensaje("No se encontró estudiante con ese correo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Metodo para actualizar la visualización de los datos del estudiante actual en el panel izquierdo, incluyendo su foto, nombre, carrera, correo, estado y la tabla de materias y calificaciones en el panel derecho.
     */
    private void actualizarVisualizacion() {
        if (nodoActual != null) {
            lblNombreDisplay.setText("Nombre: " + nodoActual.getNombreCompleto());
            lblCarreraDisplay.setText("Carrera: " + nodoActual.getCarrera());
            lblCorreoDisplay.setText("Correo: " + nodoActual.getCorreo());
            // Actualizar estado con color
            String estado = nodoActual.getEstado();
            lblEstadoDisplay.setText("Estado: " + estado);
            if ("Activo".equals(estado)) {
                lblEstadoDisplay.setForeground(new Color(100, 255, 100));
            } else {
                lblEstadoDisplay.setForeground(new Color(255, 100, 100));
            }
            // Cargar foto
            if (nodoActual.getRutaImagen() != null && !nodoActual.getRutaImagen().isEmpty()) {
                gestion.setCargarDatos(lblFoto, nodoActual.getRutaImagen());
            } else {
                lblFoto.setIcon(null);
                lblFoto.setText("Sin foto");
                lblFoto.setForeground(Color.GRAY);
                lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
            }
            // Actualizar tabla de materias
            actualizarTablaMaterias();
        } else {
            lblNombreDisplay.setText("Nombre: Sin selección");
            lblCarreraDisplay.setText("Carrera: --");
            lblCorreoDisplay.setText("Correo: --");
            lblEstadoDisplay.setText("Estado: --");
            lblEstadoDisplay.setForeground(TEXT_COLOR);
            lblFoto.setIcon(null);
            lblFoto.setText("");
            modeloTabla.setRowCount(0);
            lblPromedioFinal.setText("--");
        }
    }
    
    /**
     * Metodo para actualizar la tabla de materias y calificaciones del estudiante actual, así como el promedio final, cambiando el color del promedio según su valor (verde para 8 o más, naranja para 6 a 7.9, rojo para menos de 6) y mostrando "--" si no hay materias registradas.
     */
    private void actualizarTablaMaterias() {
        modeloTabla.setRowCount(0);
        if (nodoActual != null) { // Validar que haya un nodo actual seleccionado antes de intentar actualizar la tabla de materias
            String[][] materias = nodoActual.getMateriasYCalificaciones();
            for (int i = 0; i < 4; i++) {
                if (!materias[i][0].isEmpty()) {
                    modeloTabla.addRow(new Object[]{materias[i][0], materias[i][1]});
                }
            }
            double promedio = nodoActual.calcularPromedio();
            if (promedio > 0) { // Validar que el promedio sea mayor a 0 antes de mostrarlo, para evitar mostrar un promedio de 0 cuando no hay materias registradas
                lblPromedioFinal.setText(String.format("%.2f", promedio));
                // Cambiar color según el promedio
                if (promedio >= 8.0) {
                    lblPromedioFinal.setForeground(new Color(100, 255, 100));
                } else if (promedio >= 6.0) {
                    lblPromedioFinal.setForeground(new Color(255, 200, 100));
                } else {
                    lblPromedioFinal.setForeground(new Color(255, 100, 100));
                }
            } else {
                lblPromedioFinal.setText("--");
                lblPromedioFinal.setForeground(TEXT_COLOR);
            }
        }
    }

    /**
     * Metodo para aplicar un estilo 
     * @param field el JTextField al que se le aplicará el estilo
     */
    private void estilizarTextField(JTextField field) {
        field.setBackground(new Color(70, 70, 70));
        field.setForeground(TEXT_COLOR);
        field.setCaretColor(TEXT_COLOR);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 100, 100)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }
    
    /**
     * Metodo para agregar un campo de texto con su etiqueta correspondiente al formulario de registro
     * @param panel el JPanel al que se le agregará el campo de texto y su etiqueta
     * @param label el texto de la etiqueta que se mostrará junto al campo de texto
     * @param field el JTextField que se agregará al formulario para que el usuario ingrese información
     * @param gbc el GridBagConstraints que se utilizará para posicionar el campo de texto y su etiqueta en el formulario
     * @param row el número de fila en el formulario donde se agregará el campo de texto y su etiqueta, comenzando desde 0 para la primera fila
     */
    private void agregarCampoFormulario(JPanel panel, String label, JTextField field, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        JLabel lbl = new JLabel(label);
        lbl.setForeground(TEXT_COLOR);
        lbl.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(lbl, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(field, gbc);
    }
    
    /**
     * Metodo para mostrar un mensaje al usuario utilizando JOptionPane, aplicando un estilo oscuro personalizado para que el mensaje se integre visualmente con el resto de la aplicación.
     * @param mensaje el texto del mensaje que se mostrará al usuario en el cuadro de diálogo
     * @param titulo el título del cuadro de diálogo que se mostrará en la barra de título del mismo
     * @param tipo el tipo de mensaje que se mostrará, utilizando los valores predefinidos de JOptionPane (por ejemplo, JOptionPane.INFORMATION_MESSAGE, JOptionPane.ERROR_MESSAGE, JOptionPane.WARNING_MESSAGE) para indicar el tipo de mensaje que se está mostrando al usuario
     */
    private void mostrarMensaje(String mensaje, String titulo, int tipo) {
        UIManager.put("OptionPane.background", DARK_BG);
        UIManager.put("Panel.background", DARK_BG);
        UIManager.put("OptionPane.messageForeground", TEXT_COLOR);
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }
    
    /**
     * Método principal para iniciar la aplicación
     * @param args los argumentos de línea de comandos (no se utilizan en esta aplicación)
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new Main());
    }
}
