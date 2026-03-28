/**
 * @author Cesar de Jesus Becerra Vera
 * @since 27 de Marzo de 2026
 * @version 1.0
 * ARCHIVO: Main.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Interfaz gráfica de consola para el programa de búsqueda binaria en datos alfanuméricos.
 */

package app; // Paquete principal de la aplicación que contiene la clase Main para ejecutar el programa.

// Importaciones necesarias para la funcionalidad del programa
import back.BusquedaBinaria;
import java.util.Scanner;

/**
 * Clase principal del programa de búsqueda binaria en datos alfanuméricos.
 */
public class Main {
    // Instancias estáticas para el buscador y el scanner
    private static BusquedaBinaria buscador;
    private static Scanner scanner;
    
    /**
     * Método principal que inicia la aplicación.
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        // Inicializar el scanner para entrada del usuario
        scanner = new Scanner(System.in);
        try {
            // Arreglo de datos alfanuméricos ordenado (60 profesiones en español simples)
            String[] datos = {
                "Abogado", "Académico", "Acróbata", "Actor", "Actuario",
                "Acuarelista", "Acuicultor", "Acupuntor", "Adiestrador", "Adobero",
                "Aeromoza", "Afeitador", "Agente", "Agricultor", "Agrónomo",
                "Albañil", "Almacenero", "Analista", "Animador", "Anestesiólogo",
                "Antropólogo", "Aparejador", "Aparcero", "Apicultor", "Aprendiz",
                "Árbitro", "Archivero", "Arquitecto", "Artesano", "Artífice",
                "Artista", "Asador", "Aseador", "Asesor", "Aserrador",
                "Asfaltador", "Astrónomo", "Auditor", "Auxiliador", "Auxiliar",
                "Bailarín", "Banquero", "Barbero", "Barista", "Barman",
                "Barrendero", "Basculero", "Basketbolista", "Bastero", "Benedictino",
                "Bibliotecario", "Biólogo", "Bioquímico", "Bodeguero", "Bombero",
                "Botánico", "Botellero", "Botero", "Botijero", "Botillero"
            };
            // Crear instancia del buscador
            buscador = new BusquedaBinaria(datos);
            // Verificar que el arreglo esté ordenado
            if (!buscador.estaOrdenado()) {
                System.err.println("⚠️  Advertencia: El arreglo no está ordenado correctamente.\n");
            }
            mostrarMenu(datos);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
    
    /**
     * Muestra el menú principal e interactúa con el usuario.
     * @param datos Arreglo de datos para mostrar
     */
    private static void mostrarMenu(String[] datos) {
        boolean continuarPrograma = true;
        while (continuarPrograma) {
            mostrarEncabezado();
            mostrarOpciones();
            System.out.print("\n→ Seleccione una opción: ");
            String opcion = scanner.nextLine().trim();
            switch (opcion) {
                case "1":
                    mostrarDatos(datos);
                    break;
                case "2":
                    realizarBusqueda();
                    break;
                case "3":
                    mostrarInformacionAlgoritmo();
                    break;
                case "4":
                    continuarPrograma = false;
                    mostrarDespedida();
                    break;
                default:
                    System.out.println("\n❌ Opción inválida. Por favor, ingrese 1, 2, 3 o 4.\n");
            }
            if (continuarPrograma && (opcion.equals("1") || opcion.equals("2") || opcion.equals("3"))) {
                System.out.print("\n→ Presione Enter para continuar...");
                scanner.nextLine();
                System.out.println("\n" + "=".repeat(70) + "\n");
            }
        }
    }
    
    /**
     * Muestra el encabezado del programa.
     */
    private static void mostrarEncabezado() {
        System.out.println("┌" + "─".repeat(68) + "┐");
        System.out.println("│" + centrartexto("BÚSQUEDA BINARIA EN DATOS ALFANUMÉRICOS", 68) + "│");
        System.out.println("├" + "─".repeat(68) + "┤");
        System.out.println("│" + centrartexto("Algoritmo de búsqueda eficiente", 68) + "│");
        System.out.println("└" + "─".repeat(68) + "┘");
    }
    
    /**
     * Muestra las opciones del menú principal.
     */
    private static void mostrarOpciones() {
        System.out.println("\n📋 OPCIONES DEL MENÚ:\n");
        System.out.println("   1. Ver todos los datos del arreglo");
        System.out.println("   2. Realizar una búsqueda binaria");
        System.out.println("   3. Información sobre el algoritmo");
        System.out.println("   4. Salir del programa");
    }
    
    /**
     * Muestra todos los datos del arreglo en formato formateado.
     * @param datos Arreglo a mostrar
     */
    private static void mostrarDatos(String[] datos) {
        System.out.println("\n📊 DATOS DEL ARREGLO (ORDENADOS ALFABÉTICAMENTE):\n");
        System.out.println("   Total de elementos: " + datos.length);
        System.out.println("   Rango: " + datos[0] + " → " + datos[datos.length - 1]);
        System.out.println("\n   " + "─".repeat(64));
        for (int i = 0; i < datos.length; i++) {
            if (i % 5 == 0 && i != 0) {
                System.out.println();
            }
            System.out.print(String.format("   [%2d] %-15s", i, datos[i]));
        }
        System.out.println("\n   " + "─".repeat(64));
    }
    
    /**
     * Realiza la búsqueda binaria interactuando con el usuario.
     */
    private static void realizarBusqueda() {
        System.out.println("\n🔍 BÚSQUEDA BINARIA:\n");
        // Solicitar el valor a buscar con validación
        String valor = obtenerEntradaValida("Ingrese el valor a buscar: ");
        if (valor == null) {
            return;
        }
        System.out.println("\n   Buscando: \"" + valor + "\"...\n");
        // Realizar la búsqueda
        int posicion = buscador.buscar(valor);
        // Mostrar resultados
        System.out.println("   " + "─".repeat(64));
        if (posicion >= 0) {
            System.out.println("   ✅ ELEMENTO ENCONTRADO");
            System.out.println("   " + "─".repeat(64));
            System.out.println("   📍 Posición (índice): " + posicion);
            System.out.println("   📝 Valor: " + buscador.obtenerElemento(posicion));
            System.out.println("   🔄 Comparaciones realizadas: " + buscador.obtenerComparaciones());
            // Mostrar contexto (elementos adyacentes si existen)
            System.out.println("\n   Contexto en el arreglo:");
            mostrarContexto(posicion);
        } else {
            System.out.println("   ❌ ELEMENTO NO ENCONTRADO");
            System.out.println("   " + "─".repeat(64));
            System.out.println("   📝 El valor \"" + valor + "\" no existe en el arreglo.");
            System.out.println("   🔄 Comparaciones realizadas: " + buscador.obtenerComparaciones());
            sugerirElementosSimilares(valor);
        }
        System.out.println("   " + "─".repeat(64));
    }
    
    /**
     * Muestra el contexto del elemento encontrado (elementos adyacentes).
     * @param posicion Posición del elemento encontrado
     */
    private static void mostrarContexto(int posicion) {
        System.out.println();
        // Mostrar elemento anterior
        if (posicion > 0) {
            System.out.println(String.format("      [%d] %s", posicion - 1, buscador.obtenerElemento(posicion - 1)));
        }
        // Mostrar elemento encontrado (destacado)
        System.out.println(String.format("    → [%d] %s ← ENCONTRADO", posicion, buscador.obtenerElemento(posicion)));
        // Mostrar elemento siguiente
        if (posicion < buscador.obtenerTamano() - 1) {
            System.out.println(String.format("      [%d] %s", posicion + 1, buscador.obtenerElemento(posicion + 1)));
        }
    }
    
    /**
     * Sugiere elementos similares al valor no encontrado.
     * @param valor Valor que no fue encontrado
     */
    private static void sugerirElementosSimilares(String valor) {
        String valorNormalizado = valor.trim().toLowerCase();
        System.out.println("\n   💡 Elementos que comienzan con la misma letra:");
        boolean encontrado = false;
        char primeraLetra = valorNormalizado.charAt(0);
        for (int i = 0; i < buscador.obtenerTamano(); i++) {
            String elemento = buscador.obtenerElemento(i);
            if (elemento != null && elemento.toLowerCase().charAt(0) == primeraLetra) {
                System.out.println("      [" + i + "] " + elemento);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("      No hay elementos que comiencen con '" + primeraLetra + "'");
        }
    }
    
    /**
     * Obtiene entrada del usuario con validación.
     * @param mensaje Mensaje a mostrar al usuario
     * @return Entrada validada o null si es cancelada
     */
    private static String obtenerEntradaValida(String mensaje) {
        System.out.print("   " + mensaje);
        String entrada = scanner.nextLine().trim();
        if (entrada.isEmpty()) {
            System.out.println("   ⚠️  La entrada no puede estar vacía.");
            return null;
        }
        return entrada;
    }
    
    /**
     * Muestra información sobre el algoritmo de búsqueda binaria.
     */
    private static void mostrarInformacionAlgoritmo() {
        System.out.println("Comparaciones máximas para " + buscador.obtenerTamano() + 
                        " elementos: " + calcularComparacionesMaximas(buscador.obtenerTamano()) + "\n");
    }
    
    /**
     * Calcula el número máximo de comparaciones para búsqueda binaria.
     * @param tamanio Tamaño del arreglo
     * @return Número máximo de comparaciones (log2(n) + 1)
     */
    private static int calcularComparacionesMaximas(int tamanio) {
        return (int) Math.ceil(Math.log(tamanio) / Math.log(2)) + 1;
    }
    
    /**
     * Muestra un mensaje de despedida.
     */
    private static void mostrarDespedida() {
        System.out.println("\n┌" + "─".repeat(68) + "┐");
        System.out.println("│" + centrartexto("¡GRACIAS POR USAR EL PROGRAMA!", 68) + "│");
        System.out.println("│" + centrartexto("Hasta luego", 68) + "│");
        System.out.println("└" + "─".repeat(68) + "┘\n");
    }
    
    /**
     * Centra un texto dentro de un ancho especificado.
     * @param texto Texto a centrar
     * @param ancho Ancho total disponible
     * @return Texto centrado con espacios
     */
    private static String centrartexto(String texto, int ancho) {
        int espacios = (ancho - texto.length()) / 2;
        int espaciosExtra = (ancho - texto.length()) % 2;
        return " ".repeat(espacios) + texto + " ".repeat(espacios + espaciosExtra);
    }
}
