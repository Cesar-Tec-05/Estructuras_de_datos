/**
 * @author Cesar de Jesus Becerra Vera
 * @since 21 de Febrero de 2026
 * @version 1.0
 * ARCHIVO: Main.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase Main que gestiona la interfaz gráfica del simulador de historial de navegación web.
 */

/**
 * Paquete app que contiene la clase principal para la ejecución del programa y la interfaz gráfica.
 */
package app;

// Importaciones necesarias para la interfaz gráfica y la gestión del historial de navegación
import back.Navegador;
import back.Nodo;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * Clase Main que extiende JFrame para crear una interfaz gráfica.
 */
public class Main extends JFrame {
    // Atributos para gestionar el navegador y la página actual
    private Navegador navegador;
    private Nodo paginaActual;
    // Componentes de la barra de navegación
    private JButton btnAtras;
    private JButton btnAdelante;
    private JButton btnRecargar;
    private JButton btnHome;
    private JTextField barraUrl;
    private JButton btnIr;
    // Área de visualización
    private JPanel panelContenido;
    private JLabel lblPaginaActual;
    private JTextArea areaContenido;
    // Barra de estado
    private JLabel lblEstado;
    // Panel de historial lateral
    private JList<String> listaHistorial;
    private DefaultListModel<String> modeloHistorial;

    /**
     * Constructor de la clase Main, inicializa el navegador y los componentes de la interfaz gráfica
     */
    public Main() {
        navegador = new Navegador();
        paginaActual = null;
        inicializarComponentes();
    }

    /**
     * Metodo para inicializar los componentes de la interfaz gráfica.
     */
    private void inicializarComponentes() {
        // Configuración de la ventana principal
        setTitle("Navegador Web");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(0, 0));
        panelPrincipal.setBackground(new Color(30, 30, 30));
        // Crear componentes
        JPanel barraNavegacion = crearBarraNavegacion();
        JPanel contenidoPrincipal = crearContenidoPrincipal();
        JPanel barraEstado = crearBarraEstado();
        // Agregar componentes
        panelPrincipal.add(barraNavegacion, BorderLayout.NORTH);
        panelPrincipal.add(contenidoPrincipal, BorderLayout.CENTER);
        panelPrincipal.add(barraEstado, BorderLayout.SOUTH);
        // Agregar panel principal a la ventana
        add(panelPrincipal);
        // Configurar eventos
        configurarEventos();
        // Actualizar estado inicial
        actualizarBotones();
    }

    /**
     * Metodo para crear la barra de navegación con botones y barra de URL.
     * @return El panel que contiene la barra de navegación completa
     */
    private JPanel crearBarraNavegacion() {
        // Configuración del panel de navegación
        JPanel panel = new JPanel(new BorderLayout(5, 0));
        panel.setBackground(new Color(40, 40, 40));
        panel.setBorder(new CompoundBorder(
            new MatteBorder(0, 0, 1, 0, new Color(60, 60, 60)),
            new EmptyBorder(8, 10, 8, 10)
        ));
        // Panel izquierdo - Botones de navegación
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panelBotones.setOpaque(false);
        // Crear botones con símbolos Unicode
        btnAtras = crearBotonNavegacion("←", "Atrás", new Color(255, 255, 255));
        btnAdelante = crearBotonNavegacion("→", "Adelante", new Color(255, 255, 255));
        btnRecargar = crearBotonNavegacion("↻", "Recargar", new Color(255, 255, 255));
        btnHome = crearBotonNavegacion("⌂", "Inicio", new Color(255, 255, 255));
        // Agregar botones al panel de navegación
        panelBotones.add(btnAtras);
        panelBotones.add(btnAdelante);
        panelBotones.add(btnRecargar);
        panelBotones.add(Box.createRigidArea(new Dimension(10, 0)));
        panelBotones.add(btnHome);
        // Panel central - Barra de URL
        JPanel panelUrl = new JPanel(new BorderLayout(5, 0));
        panelUrl.setOpaque(false);
        panelUrl.setBorder(new EmptyBorder(0, 10, 0, 10));
        // Configuración de la barra de URL
        barraUrl = new JTextField();
        barraUrl.setFont(new Font("Arial", Font.PLAIN, 14));
        barraUrl.setBorder(new CompoundBorder(
            new LineBorder(new Color(80, 80, 80), 1, true),
            new EmptyBorder(8, 12, 8, 12)
        ));
        // Estilo de la barra de URL
        barraUrl.setBackground(new Color(55, 55, 55));
        barraUrl.setForeground(new Color(230, 230, 230));
        barraUrl.setCaretColor(new Color(230, 230, 230));
        // Botón Ir
        btnIr = new JButton("Ir");
        btnIr.setFont(new Font("Arial", Font.BOLD, 12));
        btnIr.setBackground(new Color(66, 133, 244));
        btnIr.setForeground(Color.WHITE);
        btnIr.setFocusPainted(false);
        btnIr.setBorderPainted(false);
        btnIr.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIr.setPreferredSize(new Dimension(60, 35));
        // Efecto hover para el botón Ir
        panelUrl.add(barraUrl, BorderLayout.CENTER);
        panelUrl.add(btnIr, BorderLayout.EAST);
        // Agregar todo al panel principal
        panel.add(panelBotones, BorderLayout.WEST);
        panel.add(panelUrl, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Metodo auxiliar para crear un botón de navegación
     * @param simbolo El símbolo Unicode que representa la función del botón
     * @param tooltip El texto que se muestra al pasar el cursor sobre el botón
     * @param color El color del texto del botón
     * @return El botón configurado con el símbolo, tooltip y estilo especificados
     */
    private JButton crearBotonNavegacion(String simbolo, String tooltip, Color color) {
        // Configuración básica del botón
        JButton boton = new JButton(simbolo);
        boton.setFont(new Font("Arial", Font.BOLD, 20));
        boton.setForeground(color);
        boton.setBackground(new Color(40, 40, 40));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(true);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(45, 35));
        boton.setToolTipText(tooltip);
        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            /**
             * Metodo para cambiar el color del botón al pasar el cursor sobre él, creando un efecto de hover
             * @param evt El evento de mouse que se produce al entrar o salir del botón
             */
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(60, 60, 60));
            }
            /**
             * Metodo para restaurar el color original del botón al salir el cursor de él, finalizando el efecto de hover
             * @param evt El evento de mouse que se produce al salir del botón
             */
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(40, 40, 40));
            }
        });
        return boton;
    }

    /**
     * Metodo para crear el contenido principal de la interfaz.
      * @return El panel que contiene el área de contenido y el panel de historial
     */
    private JPanel crearContenidoPrincipal() {
        // Configuración del panel de contenido principal
        JPanel panel = new JPanel(new BorderLayout(0, 0));
        panel.setBackground(new Color(30, 30, 30));
        // Área de contenido (simula la página web)
        panelContenido = crearAreaContenido();
        // Panel de historial lateral
        JPanel panelHistorial = crearPanelHistorial();
        // Agregar componentes
        panel.add(panelContenido, BorderLayout.CENTER);
        panel.add(panelHistorial, BorderLayout.EAST);
        return panel;
    }

    /**
     * Metodo para crear el área de contenido donde se muestra la página web actual y su información
      * @return El panel que contiene el área de contenido con el título y la descripción de la página
     */
    private JPanel crearAreaContenido() {
        // Configuración del panel de contenido
        JPanel panel = new JPanel(new BorderLayout(0, 0));
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(new MatteBorder(0, 0, 0, 1, new Color(60, 60, 60)));
        // Panel superior con título de la página
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(35, 35, 35));
        panelTitulo.setBorder(new CompoundBorder(
            new MatteBorder(0, 0, 1, 0, new Color(60, 60, 60)),
            new EmptyBorder(15, 20, 15, 20)
        ));
        // Etiqueta para mostrar la URL de la página actual, inicialmente muestra "Nueva pestaña"
        lblPaginaActual = new JLabel("Nueva pestaña");
        lblPaginaActual.setFont(new Font("Arial", Font.BOLD, 24));
        lblPaginaActual.setForeground(new Color(230, 230, 230));
        // Agregar la etiqueta al panel de título
        panelTitulo.add(lblPaginaActual, BorderLayout.WEST);
        // Área de contenido
        areaContenido = new JTextArea();
        areaContenido.setFont(new Font("Arial", Font.PLAIN, 16));
        areaContenido.setEditable(false);
        areaContenido.setLineWrap(true);
        areaContenido.setWrapStyleWord(true);
        areaContenido.setBackground(new Color(30, 30, 30));
        areaContenido.setBorder(new EmptyBorder(20, 20, 20, 20));
        areaContenido.setForeground(new Color(200, 200, 200));
        areaContenido.setText(
            "Bienvenido al Navegador Web\n\n"
        );
        // Posicionar el cursor al inicio del área de contenido
        JScrollPane scroll = new JScrollPane(areaContenido);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        // Agregar el panel de título y el área de contenido al panel principal
        panel.add(panelTitulo, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Metodo para crear el panel lateral que muestra el historial de navegación
      * @return El panel que contiene el título del historial y la lista de URLs visitadas
     */
    private JPanel crearPanelHistorial() {
        // Configuración del panel de historial lateral
        JPanel panel = new JPanel(new BorderLayout(0, 0));
        panel.setPreferredSize(new Dimension(280, 0));
        panel.setBackground(new Color(35, 35, 35));
        // Título del historial
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(35, 35, 35));
        panelTitulo.setBorder(new CompoundBorder(
            new MatteBorder(0, 0, 1, 0, new Color(60, 60, 60)),
            new EmptyBorder(15, 15, 15, 15)
        ));
        // Etiqueta para el título del historial
        JLabel lblTitulo = new JLabel("Historial");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(230, 230, 230));
        // Icono para el título del historial
        JLabel lblIcono = new JLabel("📜");
        lblIcono.setFont(new Font("Arial", Font.PLAIN, 20));
        // Agregar el icono y el título al panel de título del historial
        panelTitulo.add(lblIcono, BorderLayout.WEST);
        panelTitulo.add(lblTitulo, BorderLayout.CENTER);
        // Lista de historial
        modeloHistorial = new DefaultListModel<>();
        listaHistorial = new JList<>(modeloHistorial);
        listaHistorial.setFont(new Font("Arial", Font.PLAIN, 13));
        listaHistorial.setBackground(new Color(35, 35, 35));
        listaHistorial.setForeground(new Color(200, 200, 200));
        listaHistorial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaHistorial.setBorder(new EmptyBorder(10, 10, 10, 10));
        listaHistorial.setCellRenderer(new HistorialCellRenderer());
        // Agregar la lista de historial a un JScrollPane para permitir desplazamiento si hay muchas entradas
        JScrollPane scrollHistorial = new JScrollPane(listaHistorial);
        scrollHistorial.setBorder(null);
        scrollHistorial.getVerticalScrollBar().setUnitIncrement(16);
        // Agregar el panel de título y el scroll con la lista de historial al panel lateral
        panel.add(panelTitulo, BorderLayout.NORTH);
        panel.add(scrollHistorial, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Metodo para crear la barra de estado que muestra mensajes de estado y errores al usuario
      * @return El panel que contiene la barra de estado con una etiqueta para mostrar mensajes
     */
    private JPanel crearBarraEstado() {
        // Configuración del panel de barra de estado
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(40, 40, 40));
        panel.setBorder(new CompoundBorder(
            new MatteBorder(1, 0, 0, 0, new Color(60, 60, 60)),
            new EmptyBorder(5, 15, 5, 15)
        ));
        // Etiqueta para mostrar mensajes de estado al usuario, inicialmente muestra "Listo"
        lblEstado = new JLabel("Listo");
        lblEstado.setFont(new Font("Arial", Font.PLAIN, 12));
        lblEstado.setForeground(new Color(180, 180, 180));
        // Agregar la etiqueta a la barra de estado
        panel.add(lblEstado, BorderLayout.WEST);
        return panel;
    }

    /**
     * Metodo para configurar los eventos de los botones y la lista de historial
     */
    private void configurarEventos() {
        // Botón Ir y Enter en la barra de URL
        btnIr.addActionListener(e -> navegarAUrl());
        barraUrl.addActionListener(e -> navegarAUrl());
        // Botón Atrás
        btnAtras.addActionListener(e -> navegarAtras());
        // Botón Adelante
        btnAdelante.addActionListener(e -> navegarAdelante());
        // Botón Recargar
        btnRecargar.addActionListener(e -> recargarPagina());
        // Botón Home
        btnHome.addActionListener(e -> irAlInicio());
        // Click en el historial
        listaHistorial.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Evitar múltiples eventos al seleccionar un elemento
                int index = listaHistorial.getSelectedIndex();
                if (index >= 0) { // Si se seleccionó un índice válido, navegar a esa página del historial
                    navegarAIndice(index);
                }
            }
        });
    }

    /**
     * Metodo para navegar a la URL ingresada en la barra de URL, agregándola al historial y actualizando la interfaz gráfica
     */
    private void navegarAUrl() {
        // Atributo para obtener la URL ingresada por el usuario, eliminando espacios al inicio y al final
        String url = barraUrl.getText().trim();
        if (url.isEmpty()) { // Si la URL está vacía, mostrar un mensaje de error
            lblEstado.setText("Error: Ingrese una URL");
            return;
        }
        // Agregar al navegador
        navegador.agregarDireccion(url);
        paginaActual = navegador.getInicio();
        // Actualizar interfaz
        mostrarPagina(url);
        actualizarHistorial();
        actualizarBotones();
        lblEstado.setText("Página cargada: " + url);
    }

    /**
     * Metodo para navegar hacia atrás en el historial, actualizando la página actual y la interfaz gráfica
     */
    private void navegarAtras() {
        if (paginaActual != null && paginaActual.getSiguiente() != null) { // Si hay una página anterior en el historial, se navega hacia atrás actualizando la página actual
            paginaActual = paginaActual.getSiguiente();
            String url = paginaActual.getUrl();
            // Actualizar interfaz gráfica con la nueva página actual
            barraUrl.setText(url);
            mostrarPagina(url);
            actualizarBotones();
            resaltarEnHistorial();
            lblEstado.setText("Navegando hacia atrás: " + url);
        }
    }

    /**
     * Metodo para navegar hacia adelante en el historial, actualizando la página actual y la interfaz gráfica
     */
    private void navegarAdelante() {
        if (paginaActual != null && paginaActual.getAnterior() != null) { // Si hay una página siguiente en el historial, se navega hacia adelante actualizando la página actual
            paginaActual = paginaActual.getAnterior();
            String url = paginaActual.getUrl();
            // Actualizar interfaz gráfica con la nueva página actual
            barraUrl.setText(url);
            mostrarPagina(url);
            actualizarBotones();
            resaltarEnHistorial();
            lblEstado.setText("Navegando hacia adelante: " + url);
        }
    }

    /**
     * Metodo para recargar la página actual, mostrando un mensaje de estado si no hay página para recargar
     */
    private void recargarPagina() {
        if (paginaActual != null) { // Si hay una página actual, se recarga mostrando un mensaje de estado con la URL de la página recargada
            String url = paginaActual.getUrl();
            mostrarPagina(url);
            lblEstado.setText("Página recargada: " + url);
        } else { // Si no hay página actual, se muestra un mensaje de error indicando que no hay página para recargar
            lblEstado.setText("No hay página para recargar");
        }
    }

    /**
     * Metodo para navegar al inicio del historial, actualizando la página actual y la interfaz gráfica
     */
    private void irAlInicio() {
        if (navegador.getInicio() != null) { // Si hay una página en el historial, se navega al inicio actualizando la página actual con el nodo inicial del historial
            paginaActual = navegador.getInicio();
            String url = paginaActual.getUrl();
            // Actualizar interfaz gráfica con la nueva página actual
            barraUrl.setText(url);
            mostrarPagina(url);
            actualizarBotones();
            resaltarEnHistorial();
            lblEstado.setText("Inicio: " + url);
        }
    }

    /**
     * Metodo para navegar a una página específica del historial al hacer clic en ella
      * @param index El índice de la página en el historial a la que se desea navegar
     */
    private void navegarAIndice(int index) {
        // Atributo para recorrer la lista del historial hasta el índice seleccionado por el usuario
        Nodo actual = navegador.getInicio();
        int i = 0;
        while (actual != null && i < index) { // Mientras haya nodos en la lista y no se haya alcanzado el índice deseado, se avanza al siguiente nodo
            actual = actual.getSiguiente();
            i++;
        }
        if (actual != null) { // Si se encontró un nodo en el índice seleccionado, se actualiza la página actual con ese nodo y se muestra su URL en la barra de navegación
            paginaActual = actual;
            String url = actual.getUrl();
            barraUrl.setText(url);
            mostrarPagina(url);
            actualizarBotones();
            lblEstado.setText("Historial: " + url);
        }
    }

    /**
     * Metodo para mostrar la página actual en el área de contenido
      * @param url La URL de la página que se desea mostrar en el área de contenido
     */
    private void mostrarPagina(String url) {
        lblPaginaActual.setText(url);
        areaContenido.setText(
            "Sitio Web: " + url + "\n\n" +
            "═══════════════════════════════════════════\n\n" +
            "URL: " + url + "\n\n" +
            "Estadísticas:\n" +
            "• Total de páginas en historial: " + navegador.obtenerTamanio() + "\n" +
            "• Página actual: " + url + "\n\n" +
            "Usa las flechas de navegación para moverte por el historial."
        );
        areaContenido.setCaretPosition(0);
    }

    /**
     * Metodo para actualizar la lista de historial en la interfaz gráfica
     */
    private void actualizarHistorial() {
        modeloHistorial.clear();
        // Atributo para recorrer la lista del historial
        Nodo actual = navegador.getInicio();
        int contador = 1;
        while (actual != null) { // Mientras haya nodos en la lista, se agrega cada URL al modelo del historial
            String texto = contador + ". " + actual.getUrl();
            modeloHistorial.addElement(texto);
            actual = actual.getSiguiente();
            contador++;
        }
        // Seleccionar el actual
        resaltarEnHistorial();
    }

    /**
     * Metodo para resaltar la página actual en la lista de historial
     */
    private void resaltarEnHistorial() {
        if (paginaActual == null) return; // Si no hay página actual, no se resalta nada
        // Atributo para recorrer la lista del historial y encontrar el índice de la página actual
        Nodo actual = navegador.getInicio();
        int index = 0;
        while (actual != null) { // Mientras haya nodos en la lista, se compara cada nodo con la página actual para encontrar su índice
            if (actual == paginaActual) { // Si se encuentra el nodo que corresponde a la página actual, se selecciona ese índice en la lista de historial y se asegura que sea visible
                listaHistorial.setSelectedIndex(index);
                listaHistorial.ensureIndexIsVisible(index);
                break;
            }
            actual = actual.getSiguiente();
            index++;
        }
    }

    /**
     * Metodo para actualizar el estado de los botones de navegación
     */
    private void actualizarBotones() {
        // Atributos para determinar si hay páginas hacia atrás o adelante en el historial
        boolean hayAtras = paginaActual != null && paginaActual.getSiguiente() != null;
        boolean hayAdelante = paginaActual != null && paginaActual.getAnterior() != null;
        boolean hayPagina = paginaActual != null;
        // Habilitar o deshabilitar botones según el estado del historial
        btnAtras.setEnabled(hayAtras);
        btnAdelante.setEnabled(hayAdelante);
        btnRecargar.setEnabled(hayPagina);
        btnHome.setEnabled(hayPagina && paginaActual != navegador.getInicio());
        // Cambiar color: habilitado = blanco (contraste), deshabilitado = color de fondo (se oculta)
        btnAtras.setForeground(hayAtras ? new Color(255, 255, 255) : new Color(40, 40, 40));
        btnAdelante.setForeground(hayAdelante ? new Color(255, 255, 255) : new Color(40, 40, 40));
        btnRecargar.setForeground(hayPagina ? new Color(255, 255, 255) : new Color(40, 40, 40));
        btnHome.setForeground((hayPagina && paginaActual != navegador.getInicio()) ? 
            new Color(255, 255, 255) : new Color(40, 40, 40));
    }

    /**
     * Renderer personalizado para la lista de historial
     */
    class HistorialCellRenderer extends DefaultListCellRenderer {
        /**
         * Metodo para personalizar la apariencia de cada celda en la lista de historial
          * @param list La lista a la que pertenece el elemento que se va a renderizar
          * @param value El valor del elemento que se va a renderizar (en este caso, la URL del historial)
          * @param index El índice del elemento en la lista
          * @param isSelected Indica si el elemento está seleccionado
          * @param cellHasFocus Indica si el elemento tiene el foco
          * @return Un componente personalizado para representar el elemento en la lista de historial
         */
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, 
                int index, boolean isSelected, boolean cellHasFocus) {
            // Configuración básica del JLabel para cada elemento de la lista
            JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);
            // Estilo del JLabel para simular un diseño de lista de historial con separación entre elementos y un borde inferior
            label.setBorder(new CompoundBorder(
                new MatteBorder(0, 0, 1, 0, new Color(50, 50, 50)),
                new EmptyBorder(8, 10, 8, 10)
            ));
            if (isSelected) { // Si el elemento está seleccionado, se resalta con un fondo azul
                label.setBackground(new Color(66, 133, 244));
                label.setForeground(Color.WHITE);
            } else { // Si el elemento no está seleccionado, se muestra con el fondo oscuro y texto claro del diseño general
                label.setBackground(new Color(35, 35, 35));
                label.setForeground(new Color(200, 200, 200));
            }
            // Agregar icono
            if (index == 0) { // El primer elemento es la página actual, se marca con un icono de globo terráqueo
                label.setText("🌐 " + value);
            } else { // Los demás elementos son parte del historial
                label.setText("  " + value);
            }
            return label;
        }
    }

    /**
     * Metodo main para ejecutar la aplicación, estableciendo el look and feel del sistema y mostrando la ventana principal
      * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        // Establecer look and feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Crear y mostrar la interfaz
        SwingUtilities.invokeLater(() -> {
            Main ventana = new Main();
            ventana.setVisible(true);
        });
    }
}
