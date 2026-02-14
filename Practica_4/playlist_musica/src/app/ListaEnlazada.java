/**
 * @author Cesar de Jesus Becerra Vera
 * @since 13 de Febrero de 2026
 * @version 1.0
 * ARCHIVO: ListaEnlazada.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Clase que implementa el front y la lógica de la playlist utilizando una lista enlazada simple. 
 * Permite agregar, eliminar, navegar y mostrar canciones con una interfaz gráfica. 
 */

/**
 * Paquete app: Contiene la clase ListaEnlazada que implementa la lógica de la playlist y la interfaz gráfica.
 */
package app;

// Importaciones necesarias
import back.Nodo;
import back.Cancion;
import javax.swing.*;
import java.awt.*;

/**
 * Clase que implementa el front y la lógica de la playlist utilizando una lista enlazada simple.
 */
public class ListaEnlazada extends JFrame {
    // Atributos para la lista enlazada
    Nodo frente = null, Final = null, aux = null;
    Nodo nuevo;
    Nodo cancionActual = null; // Nodo que se está mostrando actualmente
    
    /**
     * Metodo para agregar una nueva canción a la playlist.
     * @param cancion La canción a agregar a la playlist.
     */
    void agregarCancion(Cancion cancion) {
        nuevo = new Nodo(cancion, null); // Crear un nuevo nodo con la canción
        if (Final != null) { // Cuando hay elementos en la lista
            Final.setSiguiente(nuevo);
            Final = nuevo;
        } else { // Cuando la lista está vacía, el nuevo nodo es el frente y el final
            Final = nuevo;
            frente = nuevo;
            cancionActual = nuevo; // La primera canción es la actual
        }
        mostrarCancionActual(); // Actualizar la interfaz para mostrar la nueva canción agregada
    }

    /**
     * Metodo para mostrar la canción actual en la interfaz gráfica.
     */
    void mostrarCancionActual() {
        if (cancionActual == null || frente == null) { // Si no hay canciones en la playlist, mostrar mensaje de playlist vacía
            lblNombreCancion.setText("No hay canciones");
            lblArtista.setText("");
            lblGenero.setText("");
            lblDuracion.setText("");
            lblPosicion.setText("Playlist vacía");
            return;
        }
        Cancion cancion = cancionActual.getCancion();
        // Calcular posición actual
        int posicionActual = 1;
        int totalCanciones = 0;
        aux = frente;
        while (aux != null) { // Recorrer la lista para contar el total de canciones y encontrar la posición actual
            if (aux == cancionActual) { // Si el nodo actual es el nodo que se está mostrando, guardar su posición
                posicionActual = totalCanciones + 1;
            }
            totalCanciones++;
            aux = aux.getSiguiente();
        }
        // Actualizar los labels con la información de la canción
        lblNombreCancion.setText(cancion.getNombre());
        lblArtista.setText("🎤 " + cancion.getArtista());
        lblGenero.setText("🎼 " + cancion.getGenero());
        lblDuracion.setText("⏱ " + cancion.getDuracion());
        lblPosicion.setText(String.format("Canción %d de %d", posicionActual, totalCanciones));
    }

    /**
     * Metodo para navegar a la siguiente canción en la playlist.
     */
    void siguienteCancion() {
        if (cancionActual == null || cancionActual.getSiguiente() == null) { // Si no hay canción actual o si la canción actual es la última, mostrar mensaje de fin de playlist
            mostrarMensajeError("Esta es la última canción de la playlist", "Fin de Playlist");
            return;
        }
        cancionActual = cancionActual.getSiguiente();
        mostrarCancionActual();
    }

    /**
     * Metodo para navegar a la canción anterior en la playlist.
     */
    void anteriorCancion() {
        if (cancionActual == null || cancionActual == frente) { // Si no hay canción actual o si la canción actual es la primera, mostrar mensaje de inicio de playlist
            mostrarMensajeError("Esta es la primera canción de la playlist", "Inicio de Playlist");
            return;
        }
        // Buscar el nodo anterior
        aux = frente;
        while (aux != null && aux.getSiguiente() != cancionActual) { // Recorrer la lista hasta encontrar el nodo anterior al nodo actual
            aux = aux.getSiguiente();
        }
        if (aux != null) { // Si se encontró el nodo anterior, actualizar la canción actual
            cancionActual = aux;
            mostrarCancionActual();
        }
    }

    /**
     * Metodo para mostrar la playlist completa en una ventana personalizada con scroll.
     */
    void mostrarPlaylistCompleta() {
        if (frente == null) { // Si la playlist está vacía, mostrar mensaje de playlist vacía
            JOptionPane.showMessageDialog(null, "La playlist está vacía",
                "Playlist Vacía", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Crear ventana.
        JDialog dialog = new JDialog();
        dialog.setTitle("🎵 Playlist Completa");
        dialog.setSize(700, 500);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(new Color(30, 30, 30));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        // Título
        JLabel lblTitulo = new JLabel("🎵 PLAYLIST COMPLETA 🎵");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(100, 200, 255));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        // Panel de canciones con scroll
        JPanel panelCanciones = new JPanel();
        panelCanciones.setLayout(new BoxLayout(panelCanciones, BoxLayout.Y_AXIS));
        panelCanciones.setBackground(new Color(40, 40, 40));
        // Recorrer la lista y agregar cada canción al panel
        aux = frente;
        int posicion = 1;
        while (aux != null) { // Recorrer la lista hasta el final
            Cancion cancion = aux.getCancion();
            boolean esActual = (aux == cancionActual);
            final Nodo nodoClickeado = aux; // Guardar referencia para el listener
            // Panel para cada canción
            JPanel panelCancion = new JPanel();
            panelCancion.setLayout(new BoxLayout(panelCancion, BoxLayout.Y_AXIS));
            panelCancion.setBackground(esActual ? new Color(60, 60, 80) : new Color(45, 45, 45));
            panelCancion.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(esActual ? new Color(100, 200, 255) : new Color(60, 60, 60), 2),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
            ));
            panelCancion.setMaximumSize(new Dimension(650, 100));
            panelCancion.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mano
            
            /**
             * Listener para el panel de cada canción.
             * @param evt El evento de mouse que se ha producido.
             */
            panelCancion.addMouseListener(new java.awt.event.MouseAdapter() {
                private Color colorOriginal = panelCancion.getBackground();
                /**
                 * Evento de clic en el panel de la canción. 
                 * @param evt El evento de mouse que se ha producido.
                 */
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    // Cambiar a la canción seleccionada
                    cancionActual = nodoClickeado;
                    mostrarCancionActual();
                    dialog.dispose(); // Cerrar la ventana
                }
                /**
                 * Evento de mouse entrando en el panel de la canción.
                 * @param evt El evento de mouse que se ha producido.
                 */
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    // Efecto hover - aclarar un poco el fondo
                    if (nodoClickeado != cancionActual) {
                        panelCancion.setBackground(new Color(55, 55, 55));
                    } else {
                        panelCancion.setBackground(new Color(70, 70, 90));
                    }
                }
                /**
                 * Evento de mouse saliendo del panel de la canción.
                 * @param evt El evento de mouse que se ha producido.
                 */
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    // Volver al color original
                    panelCancion.setBackground(colorOriginal);
                }
            });
            // Nombre con número y marcador
            JLabel lblNombre = new JLabel((esActual ? "▶ " : "") + posicion + ". " + cancion.getNombre());
            lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 16));
            lblNombre.setForeground(esActual ? new Color(100, 200, 255) : new Color(255, 255, 255));
            lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
            panelCancion.add(lblNombre);
            panelCancion.add(Box.createRigidArea(new Dimension(0, 5)));
            // Info de la canción
            JLabel lblInfo = new JLabel(String.format("🎤 %s  •  🎼 %s  •  ⏱ %s",
                cancion.getArtista(), cancion.getGenero(), cancion.getDuracion()));
            lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            lblInfo.setForeground(new Color(200, 200, 200));
            lblInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
            panelCancion.add(lblInfo);
            panelCanciones.add(panelCancion);
            panelCanciones.add(Box.createRigidArea(new Dimension(0, 8)));
            // Mover al siguiente nodo
            aux = aux.getSiguiente();
            posicion++;
        }
        // Agregar scroll al panel de canciones
        JScrollPane scrollPane = new JScrollPane(panelCanciones);
        scrollPane.setBackground(new Color(40, 40, 40));
        scrollPane.getViewport().setBackground(new Color(40, 40, 40));
        scrollPane.setBorder(null);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        // Panel inferior con total de canciones e instrucción
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));
        panelInferior.setBackground(new Color(30, 30, 30));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        // Instrucción para el usuario
        JLabel lblInstruccion = new JLabel("💡 Haz clic en una canción para reproducirla");
        lblInstruccion.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        lblInstruccion.setForeground(new Color(100, 200, 255));
        lblInstruccion.setHorizontalAlignment(SwingConstants.CENTER);
        lblInstruccion.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInferior.add(lblInstruccion);
        panelInferior.add(Box.createRigidArea(new Dimension(0, 5)));
        // Total de canciones
        JLabel lblTotal = new JLabel("Total: " + (posicion - 1) + " canciones");
        lblTotal.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        lblTotal.setForeground(new Color(150, 150, 150));
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
        lblTotal.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInferior.add(lblTotal);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
        dialog.add(panelPrincipal);
        dialog.setVisible(true);
    }

    /**
     * Metodo para eliminar la canción actual de la playlist.
     */
    void eliminarCancionActual() { 
        if (cancionActual == null || frente == null) { // Si no hay canción actual o si la playlist está vacía, mostrar mensaje de playlist vacía
            mostrarMensajeError("No hay ninguna canción para eliminar", "Playlist Vacía");
            return;
        }
        // Obtener el nombre de la canción actual para mostrarlo en el mensaje de confirmación
        String nombreCancion = cancionActual.getCancion().getNombre();
        Cancion cancion = cancionActual.getCancion();
        // Crear ventana de confirmación
        JDialog dialog = new JDialog();
        dialog.setTitle("🗑️ Confirmar Eliminación");
        dialog.setSize(500, 380);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(new Color(30, 30, 30));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Título de advertencia
        JLabel lblTitulo = new JLabel("⚠️ ¿Eliminar esta canción?");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(255, 200, 100));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        // Panel de información de la canción
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBackground(new Color(45, 45, 45));
        panelInfo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 100, 100), 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        // Información de la canción a eliminar
        JLabel lblNombre = new JLabel("♪ " + cancion.getNombre());
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblNombre.setForeground(new Color(255, 255, 255));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInfo.add(lblNombre);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 15)));
        // Detalles adicionales de la canción
        JLabel lblArtista = new JLabel("🎤 " + cancion.getArtista());
        lblArtista.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblArtista.setForeground(new Color(200, 200, 200));
        lblArtista.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInfo.add(lblArtista);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 8)));
        // Género y duración
        JLabel lblGenero = new JLabel("🎼 " + cancion.getGenero());
        lblGenero.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblGenero.setForeground(new Color(200, 200, 200));
        lblGenero.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInfo.add(lblGenero);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 8)));
        // Duración
        JLabel lblDuracion = new JLabel("⏱ " + cancion.getDuracion());
        lblDuracion.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblDuracion.setForeground(new Color(200, 200, 200));
        lblDuracion.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInfo.add(lblDuracion);
        panelPrincipal.add(panelInfo, BorderLayout.CENTER);
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(new Color(30, 30, 30));
        // Variable para confirmar la eliminación
        final boolean[] confirmado = {false};
        // Botón Eliminar
        JButton btnEliminar = new JButton("🗑️ Eliminar");
        btnEliminar.setPreferredSize(new Dimension(150, 45));
        btnEliminar.setBackground(new Color(198, 40, 40));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(e -> {
            confirmado[0] = true;
            dialog.dispose();
        });
        /**
         * Listener para el botón de eliminar.
         * @param evt El evento de mouse que se ha producido.
         */
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            /**
             * Evento de mouse entrando en el botón de eliminar. Cambia el color del botón para dar feedback visual.
             * @param evt El evento de mouse que se ha producido.
             */
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminar.setBackground(new Color(220, 60, 60));
            }
            /**
             * Evento de mouse saliendo del botón de eliminar. Vuelve al color original del botón.
             * @param evt El evento de mouse que se ha producido.
             */
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminar.setBackground(new Color(198, 40, 40));
            }
        });
        // Botón Cancelar
        JButton btnCancelar = new JButton("✗ Cancelar");
        btnCancelar.setPreferredSize(new Dimension(150, 45));
        btnCancelar.setBackground(new Color(120, 120, 120));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(e -> dialog.dispose());
        /**
         * Listener para el botón de cancelar. Cambia el color del botón para dar feedback visual al usuario.
          * @param evt El evento de mouse que se ha producido.
         */
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            /**
             * Evento de mouse entrando en el botón de cancelar. Cambia el color del botón para dar feedback visual.
             * @param evt El evento de mouse que se ha producido.
             */
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelar.setBackground(new Color(140, 140, 140));
            }
            /**
             * Evento de mouse saliendo del botón de cancelar. Vuelve al color original del botón.
             * @param evt El evento de mouse que se ha producido.
             */
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelar.setBackground(new Color(120, 120, 120));
            }
        });
        // Agregar botones al panel de botones y luego al panel principal
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        dialog.add(panelPrincipal);
        dialog.setVisible(true);
        if (!confirmado[0]) { // Si el usuario no confirmó la eliminación, salir del método sin eliminar la canción
            return;
        }
        // Proceder con la eliminación
        Nodo nodoAEliminar = cancionActual;
        Nodo siguienteNodo = cancionActual.getSiguiente();
        // Caso: Es el único nodo
        if (frente == Final && frente == cancionActual) {
            frente = null;
            Final = null;
            cancionActual = null;
        }
        // Caso: Es el frente
        else if (cancionActual == frente) {
            frente = frente.getSiguiente();
            cancionActual = frente;
        }
        // Caso: Es cualquier otro nodo
        else {
            // Buscar el nodo anterior
            Nodo anterior = frente;
            while (anterior != null && anterior.getSiguiente() != cancionActual) {
                anterior = anterior.getSiguiente();
            }
            // Reconectar los nodos para eliminar el nodo actual
            if (anterior != null) {
                anterior.setSiguiente(cancionActual.getSiguiente());
                // Si era el último, actualizar Final
                if (cancionActual == Final) {
                    Final = anterior;
                    cancionActual = anterior;
                } else {
                    cancionActual = siguienteNodo;
                }
            }
        }
        // Desconectar el nodo eliminado
        nodoAEliminar.setSiguiente(null);
        mostrarCancionActual();
        // Mostrar mensaje de éxito después de eliminar la canción
        mostrarMensajeExito("Canción '" + nombreCancion + "' eliminada correctamente ✓", "Eliminación Exitosa");
    }
    
    /**
     * Metodo para mostrar un mensaje de error en una ventana.
     * @param mensaje El mensaje de error a mostrar.
     * @param titulo El título de la ventana de error.
     */
    void mostrarMensajeError(String mensaje, String titulo) {
        JDialog dialog = new JDialog();
        dialog.setTitle("⚠️ " + titulo);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        // Panel principal del mensaje de error
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Label para mostrar el mensaje de error
        JLabel lblMensaje = new JLabel("<html><center>" + mensaje + "</center></html>");
        lblMensaje.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblMensaje.setForeground(new Color(255, 200, 100));
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblMensaje, BorderLayout.CENTER);
        // Botón para cerrar el mensaje de error
        JButton btnOk = new JButton("Aceptar");
        btnOk.setPreferredSize(new Dimension(100, 40));
        btnOk.setBackground(new Color(120, 120, 120));
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnOk.setFocusPainted(false);
        btnOk.setBorderPainted(false);
        btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnOk.addActionListener(e -> dialog.dispose());
        // Panel para el botón de aceptar
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBackground(new Color(30, 30, 30));
        panelBoton.add(btnOk);
        panel.add(panelBoton, BorderLayout.SOUTH);
        // Agregar el panel al diálogo y mostrarlo
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    /**
     * Metodo para mostrar un mensaje de éxito en una ventana.
     * @param mensaje El mensaje de éxito a mostrar.
     * @param titulo El título de la ventana de éxito.
     */
    void mostrarMensajeExito(String mensaje, String titulo) {
        JDialog dialog = new JDialog();
        dialog.setTitle("✓ " + titulo);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        // Panel principal del mensaje de éxito
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Label para mostrar el mensaje de éxito
        JLabel lblMensaje = new JLabel("<html><center>" + mensaje + "</center></html>");
        lblMensaje.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblMensaje.setForeground(new Color(100, 255, 150));
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblMensaje, BorderLayout.CENTER);
        // Botón para cerrar el mensaje de éxito
        JButton btnOk = new JButton("Aceptar");
        btnOk.setPreferredSize(new Dimension(100, 40));
        btnOk.setBackground(new Color(46, 125, 50));
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnOk.setFocusPainted(false);
        btnOk.setBorderPainted(false);
        btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnOk.addActionListener(e -> dialog.dispose());
        // Panel para el botón de aceptar
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBackground(new Color(30, 30, 30));
        panelBoton.add(btnOk);
        panel.add(panelBoton, BorderLayout.SOUTH);
        // Agregar el panel al diálogo y mostrarlo
        dialog.add(panel);
        dialog.setVisible(true);
    }

    /**
     * Metodo para eliminar una canción específica de la playlist por su nombre.
     * @param nombreCancion El nombre de la canción que se desea eliminar de la playlist.
     */
    void eliminarCancion(String nombreCancion) {
        if (frente == null && Final == null) { // Si la playlist está vacía, mostrar mensaje de playlist vacía
            JOptionPane.showMessageDialog(null, "La playlist está vacía", 
                "Playlist Vacía", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Caso especial - La canción está en el frente
        if (frente.getCancion().getNombre().equalsIgnoreCase(nombreCancion)) {
            Nodo nodoAEliminar = frente;
            JOptionPane.showMessageDialog(null, 
                "Se eliminará la canción:\n\n" + nodoAEliminar.getCancion().getDetalles(),
                "Eliminar Canción", JOptionPane.INFORMATION_MESSAGE);
            frente = frente.getSiguiente();
            // Si era el único elemento
            if (frente == null) {
                Final = null;
                cancionActual = null;
            } else if (nodoAEliminar == cancionActual) {
                cancionActual = frente;
            }
            // Desconectar el nodo eliminado
            nodoAEliminar.setSiguiente(null);
            mostrarCancionActual();
            JOptionPane.showMessageDialog(null, "Canción eliminada correctamente ✓",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // Buscar la canción en el resto de la lista
        Nodo anterior = frente;
        Nodo actual = frente.getSiguiente();
        boolean encontrado = false;
        while (actual != null) { // Recorrer la lista hasta encontrar la canción o llegar al final
            if (actual.getCancion().getNombre().equalsIgnoreCase(nombreCancion)) { // Si se encuentra la canción, marcar como encontrada y salir del bucle
                encontrado = true;
                break;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
        if (!encontrado) { // Verificar si se encontró la canción
            JOptionPane.showMessageDialog(null, 
                "La canción '" + nombreCancion + "' no se encontró en la playlist",
                "Canción No Encontrada", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Eliminar el nodo encontrado
        Nodo nodoAEliminar = actual;
        JOptionPane.showMessageDialog(null, 
            "Se eliminará la canción:\n\n" + nodoAEliminar.getCancion().getDetalles(),
            "Eliminar Canción", JOptionPane.INFORMATION_MESSAGE);
        // Reconectar los apuntadores
        anterior.setSiguiente(actual.getSiguiente());
        // Caso especial - Si era el último nodo, actualizar Final
        if (actual == Final) {
            Final = anterior;
        }
        // Si era la canción actual, mover a la siguiente o anterior
        if (actual == cancionActual) {
            if (actual.getSiguiente() != null) {
                cancionActual = actual.getSiguiente();
            } else {
                cancionActual = anterior;
            }
        }
        // Desconectar el nodo eliminado
        nodoAEliminar.setSiguiente(null);
        mostrarCancionActual();
        JOptionPane.showMessageDialog(null, "Canción eliminada correctamente ✓",
            "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Metodo para mostrar la información de una canción específica por su nombre.
     * @param nombreCancion El nombre de la canción de la cual se desea mostrar la información detallada.
     */
    void mostrarInfoCancion(String nombreCancion) {
        if (frente == null && Final == null) { // Si la playlist está vacía, mostrar mensaje de playlist vacía
            JOptionPane.showMessageDialog(null, "La playlist está vacía",
                "Playlist Vacía", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Buscar la canción en la lista
        aux = frente;
        boolean encontrado = false;
        while (aux != null) { // Recorrer la lista hasta encontrar la canción o llegar al final
            if (aux.getCancion().getNombre().equalsIgnoreCase(nombreCancion)) { // Si se encuentra la canción, marcar como encontrada y mostrar detalles
                encontrado = true;
                JOptionPane.showMessageDialog(null, aux.getCancion().getDetalles(),
                    "Información de la Canción", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            aux = aux.getSiguiente();
        }
        if (!encontrado) { // Verificar si se encontró la canción
            JOptionPane.showMessageDialog(null, 
                "La canción '" + nombreCancion + "' no se encontró en la playlist",
                "Canción No Encontrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Componentes de la interfaz
    private JPanel panelCancionActual;
    private JLabel lblNombreCancion;
    private JLabel lblArtista;
    private JLabel lblGenero;
    private JLabel lblDuracion;
    private JLabel lblPosicion;
    private JButton btnAgregarCancion;
    private JButton btnEliminarCancion;
    private JButton btnMostrarPlaylist;
    private JButton btnIzquierdo;
    private JButton btnDerecho;

    /**
     * Constructor de la clase ListaEnlazada. Inicializa los componentes de la interfaz gráfica.
     */
    public ListaEnlazada() {
        initComponents();
        mostrarCancionActual();
    }

    /**
     * Metodo para inicializar los componentes de la interfaz gráfica.
     */
    private void initComponents() {
        // Configuración de la ventana
        setTitle("🎵 Playlist de Música - Lista Enlazada");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        // Panel principal con modo oscuro
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBackground(new Color(30, 30, 30));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Panel para mostrar la canción actual
        panelCancionActual = new JPanel();
        panelCancionActual.setLayout(new BoxLayout(panelCancionActual, BoxLayout.Y_AXIS));
        panelCancionActual.setBackground(new Color(40, 40, 40));
        panelCancionActual.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 60, 60), 3),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        // Título "Reproduciendo Ahora"
        JLabel lblTitulo = new JLabel("🎵 REPRODUCIENDO AHORA 🎵");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(100, 200, 255));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCancionActual.add(lblTitulo);
        panelCancionActual.add(Box.createRigidArea(new Dimension(0, 30)));
        // Nombre de la canción (grande y destacado)
        lblNombreCancion = new JLabel("No hay canciones");
        lblNombreCancion.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblNombreCancion.setForeground(new Color(255, 255, 255));
        lblNombreCancion.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCancionActual.add(lblNombreCancion);
        panelCancionActual.add(Box.createRigidArea(new Dimension(0, 40)));
        // Panel para la información de la canción
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(3, 1, 0, 15));
        panelInfo.setBackground(new Color(40, 40, 40));
        panelInfo.setMaximumSize(new Dimension(600, 150));
        // Artista
        lblArtista = new JLabel("");
        lblArtista.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblArtista.setForeground(new Color(200, 200, 200));
        lblArtista.setHorizontalAlignment(SwingConstants.CENTER);
        panelInfo.add(lblArtista);
        // Género
        lblGenero = new JLabel("");
        lblGenero.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblGenero.setForeground(new Color(200, 200, 200));
        lblGenero.setHorizontalAlignment(SwingConstants.CENTER);
        panelInfo.add(lblGenero);
        // Duración
        lblDuracion = new JLabel("");
        lblDuracion.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblDuracion.setForeground(new Color(200, 200, 200));
        lblDuracion.setHorizontalAlignment(SwingConstants.CENTER);
        panelInfo.add(lblDuracion);
        // Agregar el panel de información al panel de canción actual
        panelCancionActual.add(panelInfo);
        panelCancionActual.add(Box.createRigidArea(new Dimension(0, 40)));
        // Separador visual
        JSeparator separador = new JSeparator();
        separador.setMaximumSize(new Dimension(600, 2));
        separador.setForeground(new Color(80, 80, 80));
        panelCancionActual.add(separador);
        panelCancionActual.add(Box.createRigidArea(new Dimension(0, 20)));
        // Posición en la playlist
        lblPosicion = new JLabel("Playlist vacía");
        lblPosicion.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        lblPosicion.setForeground(new Color(150, 150, 150));
        lblPosicion.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCancionActual.add(lblPosicion);
        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setBackground(new Color(30, 30, 30));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Primera fila de botones (navegación y acciones principales)
        JPanel primeraFila = new JPanel();
        primeraFila.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        primeraFila.setBackground(new Color(30, 30, 30));
        // Segunda fila de botones (acciones secundarias)
        JPanel segundaFila = new JPanel();
        segundaFila.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        segundaFila.setBackground(new Color(30, 30, 30));
        // Botón Anterior (←)
        btnIzquierdo = new JButton("◄◄");
        btnIzquierdo.setPreferredSize(new Dimension(100, 50));
        btnIzquierdo.setBackground(new Color(70, 70, 70));
        btnIzquierdo.setForeground(Color.WHITE);
        btnIzquierdo.setFont(new Font("Arial", Font.BOLD, 20));
        btnIzquierdo.setFocusPainted(false);
        btnIzquierdo.setBorderPainted(false);
        btnIzquierdo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIzquierdo.addActionListener(evt -> anteriorCancion());
        /**
         * Listener para el botón de navegación anterior. 
          * @param evt El evento de mouse que se ha producido.
         */
        btnIzquierdo.addMouseListener(new java.awt.event.MouseAdapter() {
            /**
             * Evento de mouse entrando en el botón de navegación anterior. 
             * @param evt El evento de mouse que se ha producido.
             */
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIzquierdo.setBackground(new Color(90, 90, 90));
            }
            /**
             * Evento de mouse saliendo del botón de navegación anterior. 
             * @param evt El evento de mouse que se ha producido.
             */
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIzquierdo.setBackground(new Color(70, 70, 70));
            }
        });
        // Botón Agregar
        btnAgregarCancion = crearBoton("➕ Agregar", new Color(46, 125, 50));
        btnAgregarCancion.setPreferredSize(new Dimension(150, 50));
        btnAgregarCancion.addActionListener(evt -> agregarCancionActionPerformed(evt));
        // Botón Mostrar Playlist
        btnMostrarPlaylist = crearBoton("📋 Ver Playlist", new Color(25, 118, 210));
        btnMostrarPlaylist.setPreferredSize(new Dimension(150, 50));
        btnMostrarPlaylist.addActionListener(evt -> mostrarPlaylistCompleta());
        // Botón Siguiente (→)
        btnDerecho = new JButton("►►");
        btnDerecho.setPreferredSize(new Dimension(100, 50));
        btnDerecho.setBackground(new Color(70, 70, 70));
        btnDerecho.setForeground(Color.WHITE);
        btnDerecho.setFont(new Font("Arial", Font.BOLD, 20));
        btnDerecho.setFocusPainted(false);
        btnDerecho.setBorderPainted(false);
        btnDerecho.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDerecho.addActionListener(evt -> siguienteCancion());
        /**
         * Listener para el botón de navegación siguiente. 
          * @param evt El evento de mouse que se ha producido.
         */
        btnDerecho.addMouseListener(new java.awt.event.MouseAdapter() {
            /**
             * Evento de mouse entrando en el botón de navegación siguiente. 
             * @param evt El evento de mouse que se ha producido.
             */
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDerecho.setBackground(new Color(90, 90, 90));
            }
            /**
             * Evento de mouse saliendo del botón de navegación siguiente. 
             * @param evt El evento de mouse que se ha producido.
             */
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDerecho.setBackground(new Color(70, 70, 70));
            }
        });
        // Botón Eliminar (canción actual)
        btnEliminarCancion = crearBoton("🗑️ Eliminar", new Color(198, 40, 40));
        btnEliminarCancion.setPreferredSize(new Dimension(200, 50));
        btnEliminarCancion.addActionListener(evt -> eliminarCancionActual());
        // Agregar botones a la primera fila
        primeraFila.add(btnIzquierdo);
        primeraFila.add(btnAgregarCancion);
        primeraFila.add(btnMostrarPlaylist);
        primeraFila.add(btnDerecho);
        // Agregar botones a la segunda fila
        segundaFila.add(btnEliminarCancion);
        // Agregar filas al panel de botones
        panelBotones.add(primeraFila);
        panelBotones.add(segundaFila);
        // Agregar componentes al panel principal
        panelPrincipal.add(panelCancionActual, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        // Agregar panel principal a la ventana
        add(panelPrincipal);
    }

    /**
     * Metodo para crear un botón con estilos
     * @param texto El texto que se mostrará en el botón.
     * @param color El color de fondo del botón.
     * @return Un JButton con el estilo aplicado.
     */
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        /**
         * Listener para el botón. 
          * @param evt El evento de mouse que se ha producido.
         */
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            /**
             * Evento de mouse entrando en el botón. Cambia el color del botón para dar feedback visual al usuario.
             * @param evt El evento de mouse que se ha producido.
             */
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(color.brighter());
            }
            /**
             * Evento de mouse saliendo del botón. Restaura el color original del botón.
             * @param evt El evento de mouse que se ha producido.
             */
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(color);
            }
        });
        return boton;
    }
    
    /**
     * Metodo para agregar una nueva canción a la playlist.
     * @param evt El evento de acción que se ha producido al hacer clic en el botón de agregar canción.
     */
    private void agregarCancionActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Crear ventana personalizada en lugar de JOptionPane
            JDialog dialog = new JDialog();
            dialog.setTitle("➕ Agregar Nueva Canción");
            dialog.setSize(500, 350);
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            // Panel principal con estilo oscuro
            JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
            panelPrincipal.setBackground(new Color(30, 30, 30));
            panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            // Título
            JLabel lblTitulo = new JLabel("Agregar Nueva Canción a la Playlist");
            lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
            lblTitulo.setForeground(new Color(100, 200, 255));
            lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
            panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
            // Panel de formulario
            JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 10, 15));
            panelFormulario.setBackground(new Color(30, 30, 30));
            panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            // Campos de texto con estilo oscuro
            JTextField nombreField = new JTextField(20);
            JTextField artistaField = new JTextField(20);
            JTextField generoField = new JTextField(20);
            JTextField duracionField = new JTextField(20);
            // Estilizar campos de texto
            JTextField[] campos = {nombreField, artistaField, generoField, duracionField};
            for (JTextField campo : campos) { // Aplicar estilo oscuro a cada campo de texto
                campo.setBackground(new Color(50, 50, 50));
                campo.setForeground(new Color(220, 220, 220));
                campo.setCaretColor(new Color(220, 220, 220));
                campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                campo.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(80, 80, 80), 1),
                    BorderFactory.createEmptyBorder(5, 8, 5, 8)
                ));
            }
            // Etiquetas con estilo
            JLabel lblNombre = new JLabel("Nombre de la canción:");
            JLabel lblArtista = new JLabel("Artista:");
            JLabel lblGenero = new JLabel("Género:");
            JLabel lblDuracion = new JLabel("Duración (mm:ss):");
            JLabel[] etiquetas = {lblNombre, lblArtista, lblGenero, lblDuracion};
            for (JLabel etiqueta : etiquetas) {
                etiqueta.setForeground(new Color(200, 200, 200));
                etiqueta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            }
            panelFormulario.add(lblNombre);
            panelFormulario.add(nombreField);
            panelFormulario.add(lblArtista);
            panelFormulario.add(artistaField);
            panelFormulario.add(lblGenero);
            panelFormulario.add(generoField);
            panelFormulario.add(lblDuracion);
            panelFormulario.add(duracionField);
            panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
            // Panel de botones
            JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
            panelBotones.setBackground(new Color(30, 30, 30));
            // Botones de aceptar y cancelar con estilo
            JButton btnAceptar = new JButton("✓ Agregar");
            btnAceptar.setPreferredSize(new Dimension(130, 40));
            btnAceptar.setBackground(new Color(46, 125, 50));
            btnAceptar.setForeground(Color.WHITE);
            btnAceptar.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnAceptar.setFocusPainted(false);
            btnAceptar.setBorderPainted(false);
            btnAceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Botón Cancelar
            JButton btnCancelar = new JButton("✗ Cancelar");
            btnCancelar.setPreferredSize(new Dimension(130, 40));
            btnCancelar.setBackground(new Color(120, 120, 120));
            btnCancelar.setForeground(Color.WHITE);
            btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnCancelar.setFocusPainted(false);
            btnCancelar.setBorderPainted(false);
            btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Variable para confirmar si el usuario aceptó agregar la canción
            final boolean[] aceptado = {false};
            /**
             * Listener para el botón de aceptar. Marca la canción como aceptada y cierra el diálogo.
             * @param evt El evento de acción que se ha producido al hacer clic en el botón de agregar canción.
             */
            btnAceptar.addActionListener(e -> {
                aceptado[0] = true;
                dialog.dispose();
            });
            /**
             * Listener para el botón de cancelar. Cierra el diálogo sin agregar la canción.
             * @param evt El evento de acción que se ha producido al hacer clic en el botón
             */
            btnCancelar.addActionListener(e -> {
                dialog.dispose();
            });
            // Agregar botones al panel de botones y luego al panel principal
            panelBotones.add(btnAceptar);
            panelBotones.add(btnCancelar);
            panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
            dialog.add(panelPrincipal);
            dialog.setVisible(true);
            // Procesar resultado
            if (aceptado[0]) {
                String nombre = nombreField.getText().trim();
                String artista = artistaField.getText().trim();
                String genero = generoField.getText().trim();
                String duracion = duracionField.getText().trim();
                // Validar que no haya campos vacíos
                if (nombre.isEmpty() || artista.isEmpty() || genero.isEmpty() || duracion.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Validar formato de duración (mm:ss)
                Cancion nuevaCancion = new Cancion(nombre, artista, genero, duracion);
                agregarCancion(nuevaCancion);
                JOptionPane.showMessageDialog(null, "Canción agregada exitosamente ✓",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) { // Capturar cualquier excepción inesperada y mostrar un mensaje de error
            JOptionPane.showMessageDialog(null, "Error al agregar la canción: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo principal para ejecutar la aplicación.
     * @param args Los argumentos de línea de comandos (no se utilizan en esta aplicación).
     */
    public static void main(String[] args) {
        // Configurar look and feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Ejecutar la aplicación en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            ListaEnlazada ventana = new ListaEnlazada();
            ventana.setVisible(true);
        });
    }
}
