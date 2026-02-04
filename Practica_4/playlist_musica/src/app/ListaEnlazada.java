package app;

import back.Nodo;
import javax.swing.*;
import java.awt.*;

public class ListaEnlazada extends JFrame {

    Nodo frente = null, Final = null, aux = null;
    Nodo nuevo;

    // Componentes de la interfaz
    private JTextArea areaVisualizacion;
    private JButton btnInsertar;
    private JButton btnMostrar;
    private JButton btnEliminar;
    private JButton btnIzquierdo;
    private JButton btnDerecho;

    public ListaEnlazada() {
        initComponents();
    }

    private void initComponents() {
        // Configuración de la ventana
        setTitle("Lista enlazadas");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBackground(new Color(70, 80, 120));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Área de visualización (NULL)
        areaVisualizacion = new JTextArea();
        areaVisualizacion.setText("NULL");
        areaVisualizacion.setFont(new Font("Arial", Font.BOLD, 24));
        areaVisualizacion.setEditable(false);
        areaVisualizacion.setBackground(new Color(80, 100, 150));
        areaVisualizacion.setForeground(Color.WHITE);
        areaVisualizacion.setBorder(BorderFactory.createLineBorder(new Color(50, 60, 100), 2));
        
        JScrollPane scrollPane = new JScrollPane(areaVisualizacion);
        scrollPane.setPreferredSize(new Dimension(800, 300));

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(new Color(70, 80, 120));

        // Crear botones
        btnIzquierdo = new JButton("←");
        btnIzquierdo.setPreferredSize(new Dimension(80, 50));
        btnIzquierdo.setBackground(new Color(200, 200, 150));
        btnIzquierdo.setFont(new Font("Arial", Font.BOLD, 24));
        btnIzquierdo.setFocusPainted(false);

        btnInsertar = new JButton("Insertar");
        btnInsertar.setPreferredSize(new Dimension(120, 50));
        btnInsertar.setBackground(new Color(200, 200, 150));
        btnInsertar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnInsertar.setFocusPainted(false);

        btnMostrar = new JButton("Mostrar");
        btnMostrar.setPreferredSize(new Dimension(120, 50));
        btnMostrar.setBackground(new Color(200, 200, 150));
        btnMostrar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnMostrar.setFocusPainted(false);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setPreferredSize(new Dimension(120, 50));
        btnEliminar.setBackground(new Color(200, 200, 150));
        btnEliminar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnEliminar.setFocusPainted(false);

        btnDerecho = new JButton("→");
        btnDerecho.setPreferredSize(new Dimension(80, 50));
        btnDerecho.setBackground(new Color(200, 200, 150));
        btnDerecho.setFont(new Font("Arial", Font.BOLD, 24));
        btnDerecho.setFocusPainted(false);

        // Agregar botones al panel
        panelBotones.add(btnIzquierdo);
        panelBotones.add(btnInsertar);
        panelBotones.add(btnMostrar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnDerecho);

        // Agregar componentes al panel principal
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // Agregar panel principal a la ventana
        add(panelPrincipal);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ListaEnlazada ventana = new ListaEnlazada();
            ventana.setVisible(true);
        });
    }
}
