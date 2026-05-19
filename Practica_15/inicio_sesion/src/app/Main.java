/**
 * @author Cesar de Jesus Becerra Vera
 * @since 18 de Mayo de 2026
 * @version 1.0
 * ARCHIVO: Main.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Interfaz gráfica para registrar, mostrar y autentificarse con usuarios almacenados secuencialmente.
 */

// Paquete principal de la aplicación.
package app;

// Importaciones necesarias para la interfaz gráfica, manejo de archivos y la clase Usuarios.
import back.Usuarios;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Clase principal que construye la interfaz gráfica y gestiona el almacenamiento secuencial.
 */
public class Main extends JFrame {
    // Identificador de versión para serialización (no se utiliza en esta aplicación, pero es una buena práctica incluirlo).
    private static final long serialVersionUID = 1L;
    private static final File ARCHIVO_DATOS = new File("db/usuarios.txt");
    /** Campo de texto para capturar el usuario. */
    private JTextField txtUsuario;
    /** Campo oculto para capturar la contraseña. */
    private JPasswordField txtContrasena;
    /** Área de texto para mostrar los registros guardados. */
    private JTextArea areaRegistros;
    /** Etiqueta inferior para mostrar el estado de la operación actual. */
    private JLabel lblEstado;

    /**
     * Construye la ventana principal.
     */
    public Main() {
        asegurarArchivoDatos();
        inicializarComponentes();
    }

    /**
     * Crea el directorio db y el archivo de datos si todavía no existen.
     */
    private void asegurarArchivoDatos() {
        File directorio = ARCHIVO_DATOS.getParentFile();
        if (directorio != null && !directorio.exists()) {
            directorio.mkdirs();
        }
        if (!ARCHIVO_DATOS.exists()) {
            try {
                ARCHIVO_DATOS.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "No fue posible crear el archivo de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Inicializa la ventana, el menú y los componentes visuales.
     */
    private void inicializarComponentes() {
        setTitle("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(980, 620);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setJMenuBar(crearBarraMenu());
        JPanel contenedor = new JPanel(new BorderLayout(16, 16));
        contenedor.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        contenedor.setBackground(new Color(245, 245, 245));
        contenedor.add(crearEncabezado(), BorderLayout.NORTH);
        contenedor.add(crearPanelCentral(), BorderLayout.CENTER);
        contenedor.add(crearBarraEstado(), BorderLayout.SOUTH);
        add(contenedor, BorderLayout.CENTER);
    }

    /**
     * Crea la barra de menú con las acciones principales.
     * @return Barra de menú configurada.
     */
    private JMenuBar crearBarraMenu() {
        JMenuBar barraMenu = new JMenuBar();
        JMenu menuOpciones = new JMenu("Opciones");
        JMenuItem itemAgregar = new JMenuItem("Agregar registro");
        JMenuItem itemMostrar = new JMenuItem("Mostrar registros");
        JMenuItem itemAutenticar = new JMenuItem("Autentificarse");
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemAgregar.addActionListener(evt -> agregarRegistro());
        itemMostrar.addActionListener(evt -> mostrarRegistros());
        itemAutenticar.addActionListener(evt -> autenticar());
        itemSalir.addActionListener(evt -> salir());
        menuOpciones.add(itemAgregar);
        menuOpciones.add(itemMostrar);
        menuOpciones.add(itemAutenticar);
        menuOpciones.addSeparator();
        menuOpciones.add(itemSalir);
        barraMenu.add(menuOpciones);
        return barraMenu;
    }

    /**
     * Crea el panel superior con el título y la descripción de la práctica.
     * @return Panel de encabezado.
     */
    private JPanel crearEncabezado() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 245));
        JLabel titulo = new JLabel("Registro secuencial de usuarios");
        titulo.setAlignmentX(LEFT_ALIGNMENT);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setForeground(new Color(35, 35, 35));
        JLabel subtitulo = new JLabel("Alta de registros, lectura del archivo y autentificación desde una interfaz gráfica.");
        subtitulo.setAlignmentX(LEFT_ALIGNMENT);
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 15));
        subtitulo.setForeground(new Color(90, 90, 90));
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 6)));
        panel.add(subtitulo);
        return panel;
    }

    /**
     * Crea el panel principal con el formulario y el área de registros.
     * @return Panel central.
     */
    private JPanel crearPanelCentral() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 0, 0, 16);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1.0;
        constraints.gridx = 0;
        constraints.weightx = 0.40;
        panel.add(crearPanelFormulario(), constraints);
        constraints.gridx = 1;
        constraints.weightx = 0.60;
        constraints.insets = new Insets(0, 0, 0, 0);
        panel.add(crearPanelRegistros(), constraints);
        return panel;
    }

    /**
     * Crea el formulario para capturar usuario y contraseña.
     * @return Panel de captura.
     */
    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(8, 0, 8, 0);
        constraints.weightx = 1.0;
        JLabel titulo = new JLabel("Captura de datos");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setForeground(new Color(40, 40, 40));
        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("SansSerif", Font.PLAIN, 15));
        txtUsuario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtContrasena = new JPasswordField();
        txtContrasena.setFont(new Font("SansSerif", Font.PLAIN, 15));
        txtContrasena.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        JButton btnAgregar = crearBoton("Agregar registro");
        JButton btnMostrar = crearBoton("Mostrar registros");
        JButton btnAutenticar = crearBoton("Autentificarse");
        btnAgregar.addActionListener(evt -> agregarRegistro());
        btnMostrar.addActionListener(evt -> mostrarRegistros());
        btnAutenticar.addActionListener(evt -> autenticar());
        constraints.gridy = 0;
        panel.add(titulo, constraints);
        constraints.gridy++;
        panel.add(lblUsuario, constraints);
        constraints.gridy++;
        panel.add(txtUsuario, constraints);
        constraints.gridy++;
        panel.add(lblContrasena, constraints);
        constraints.gridy++;
        panel.add(txtContrasena, constraints);
        constraints.gridy++;
        panel.add(Box.createRigidArea(new Dimension(0, 4)), constraints);
        constraints.gridy++;
        panel.add(btnAgregar, constraints);
        constraints.gridy++;
        panel.add(btnMostrar, constraints);
        constraints.gridy++;
        panel.add(btnAutenticar, constraints);
        return panel;
    }

    /**
     * Crea el panel donde se muestran los registros almacenados.
     * @return Panel de resultados.
     */
    private JPanel crearPanelRegistros() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        JLabel titulo = new JLabel("Registros almacenados");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setForeground(new Color(40, 40, 40));
        areaRegistros = new JTextArea();
        areaRegistros.setEditable(false);
        areaRegistros.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaRegistros.setLineWrap(false);
        areaRegistros.setWrapStyleWord(false);
        areaRegistros.setText("Presiona 'Mostrar registros' para cargar la información almacenada.");
        JScrollPane scroll = new JScrollPane(areaRegistros);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        panel.add(titulo, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Crea la barra inferior para mostrar mensajes de estado.
     * @return Panel de estado.
     */
    private JPanel crearBarraEstado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 2, 0, 2));
        panel.setOpaque(false);
        lblEstado = new JLabel("Listo para registrar usuarios.", SwingConstants.LEFT);
        lblEstado.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblEstado.setForeground(new Color(90, 90, 90));
        panel.add(lblEstado, BorderLayout.WEST);
        return panel;
    }

    /**
     * Crea un botón con estilo uniforme.
     * @param texto Texto visible en el botón.
     * @return Botón configurado.
     */
    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("SansSerif", Font.BOLD, 14));
        boton.setBackground(new Color(40, 85, 150));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setAlignmentX(LEFT_ALIGNMENT);
        return boton;
    }

    /**
     * Agrega un registro al archivo de texto de manera secuencial.
     */
    private void agregarRegistro() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarMensaje("Debes capturar usuario y contraseña.", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (usuario.contains("|") || contrasena.contains("|")) {
            mostrarMensaje("No se permite usar el carácter '|' en los datos.", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Usuarios registro = new Usuarios(usuario, contrasena);
        try (BufferedWriter escritor = Files.newBufferedWriter(
            ARCHIVO_DATOS.toPath(),
            StandardCharsets.UTF_8,
            StandardOpenOption.CREATE,
            StandardOpenOption.APPEND)) {
            escritor.write(registro.toRegistro());
            escritor.newLine();
            escritor.flush();
            limpiarCampos();
            lblEstado.setText("Registro agregado correctamente.");
            mostrarMensaje("El registro fue guardado en db/usuarios.txt.", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            mostrarMensaje("Error al guardar los datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Lee y muestra todos los registros almacenados en el archivo.
     */
    private void mostrarRegistros() {
        StringBuilder contenido = new StringBuilder();
        int contador = 0;
        try (BufferedReader lector = Files.newBufferedReader(ARCHIVO_DATOS.toPath(), StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                Usuarios registro = Usuarios.fromRegistro(linea);
                if (registro != null) {
                    contador++;
                    contenido.append(contador)
                        .append(") Usuario: ")
                        .append(registro.getUsuario())
                        .append(" | Contraseña: ")
                        .append(registro.getContrasena())
                        .append(System.lineSeparator());
                }
            }
        } catch (IOException ex) {
            mostrarMensaje("Error al leer los datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (contador == 0) {
            areaRegistros.setText("No hay registros almacenados todavía.");
            lblEstado.setText("Archivo vacío.");
            return;
        }
        areaRegistros.setText(contenido.toString());
        areaRegistros.setCaretPosition(0);
        lblEstado.setText("Se mostraron " + contador + " registro(s).");
    }

    /**
     * Autentifica un usuario comparando los datos capturados contra el archivo secuencial.
     */
    private void autenticar() {
        String usuarioBuscado = txtUsuario.getText().trim();
        String contrasenaBuscada = new String(txtContrasena.getPassword()).trim();
        if (usuarioBuscado.isEmpty() || contrasenaBuscada.isEmpty()) {
            mostrarMensaje("Captura usuario y contraseña para autentificarte.", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try (BufferedReader lector = Files.newBufferedReader(ARCHIVO_DATOS.toPath(), StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                Usuarios registro = Usuarios.fromRegistro(linea);
                if (registro != null
                    && usuarioBuscado.equals(registro.getUsuario())
                    && contrasenaBuscada.equals(registro.getContrasena())) {
                    lblEstado.setText("Autentificación correcta para " + usuarioBuscado + ".");
                    mostrarMensaje("Autentificación exitosa.", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
        } catch (IOException ex) {
            mostrarMensaje("Error al autentificar: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            return;
        }
        lblEstado.setText("Autentificación fallida para " + usuarioBuscado + ".");
        mostrarMensaje("Usuario o contraseña incorrectos.", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Limpia los campos del formulario.
     */
    private void limpiarCampos() {
        txtUsuario.setText("");
        txtContrasena.setText("");
        txtUsuario.requestFocusInWindow();
    }

    /**
     * Muestra un mensaje emergente al usuario.
     * @param mensaje Texto del mensaje.
     * @param tipo Tipo de mensaje de JOptionPane.
     */
    private void mostrarMensaje(String mensaje, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, "Inicio de Sesión", tipo);
    }

    /**
     * Cierra la aplicación.
     */
    private void salir() {
        dispose();
        System.exit(0);
    }

    /**
     * Punto de entrada de la aplicación.
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
