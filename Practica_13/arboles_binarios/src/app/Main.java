/**
 * @author Cesar de Jesus Becerra Vera
 * @since 17 de Abril de 2026
 * @version 1.0
 * ARCHIVO: Main.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 4TO SEMESTRE
 * PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
 * DESCRIPCIÓN: Aplicacion de consola para crear y manipular un arbol binario de busqueda.
 */

// Paquete para la aplicacion principal del proyecto de arboles binarios.
package app;

// Importaciones necesarias para la aplicacion de consola y el manejo del arbol binario.
import back.ArbolBinario;
import java.util.Scanner;

/**
 * Aplicacion de consola para crear y manipular un arbol binario de busqueda.
 * Permite crear raiz, insertar nodos y mostrar recorridos preorden, inorden
 * y postorden con validacion de entradas.
 */
public class Main {
    /**
     * Scanner compartido para lectura de datos desde consola.
     */
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Instancia unica del arbol binario usado por el menu.
     */
    private static final ArbolBinario ARBOL = new ArbolBinario();

    /**
     * Constructor privado para evitar instanciacion de la clase utilitaria.
     */
    private Main() {
    }

    /**
     * Punto de entrada del programa.
     * @param args argumentos de linea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();
            switch (opcion) {
                case 1:
                    crearNodoRaiz();
                    break;
                case 2:
                    insertarNodo();
                    break;
                case 3:
                    cargarValoresPredefinidos();
                    break;
                case 4:
                    mostrarRecorridos();
                    break;
                case 5:
                    limpiarArbol();
                    break;
                case 6:
                    continuar = false;
                    System.out.println("\nPrograma finalizado.");
                    break;
                default:
                    System.out.println("\nOpcion no valida.");
                    break;
            }
        }
        SCANNER.close();
    }

    /**
     * Muestra el menu principal del programa.
     */
    private static void mostrarMenu() {
        System.out.println("\n========================================");
        System.out.println("   ARBOL BINARIO - MENU PRINCIPAL");
        System.out.println("========================================");
        System.out.println("1. Crear nodo raiz");
        System.out.println("2. Insertar nuevo nodo");
        System.out.println("3. Cargar valores predefinidos");
        System.out.println("4. Mostrar recorridos (pre/in/post)");
        System.out.println("5. Reiniciar arbol");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    /**
     * Lee la opcion del menu y valida que este en el rango permitido.
     * @return opcion valida entre 1 y 6
     */
    private static int leerOpcion() {
        while (true) {
            String entrada = SCANNER.nextLine().trim();
            if (entrada.matches("[1-6]")) {
                return Integer.parseInt(entrada);
            }
            System.out.print("Entrada invalida. Ingrese un numero entre 1 y 6: ");
        }
    }

    /**
     * Solicita y crea la raiz del arbol si aun no existe.
     */
    private static void crearNodoRaiz() {
        int valor = leerEntero("Ingrese el valor entero para la raiz: ");
        if (ARBOL.crearRaiz(valor)) {
            System.out.println("Raiz creada correctamente con valor: " + valor);
        } else {
            System.out.println("El arbol ya tiene una raiz. Use la opcion de insertar nodos.");
        }
    }

    /**
     * Solicita e inserta un nuevo valor en el arbol.
     */
    private static void insertarNodo() {
        int valor = leerEntero("Ingrese el valor entero a insertar: ");
        if (ARBOL.insertar(valor)) {
            System.out.println("Valor insertado correctamente: " + valor);
        } else {
            System.out.println("El valor " + valor + " ya existe. No se insertan duplicados.");
        }
    }

    /**
     * Carga un conjunto fijo de valores para pruebas rapidas.
     */
    private static void cargarValoresPredefinidos() {
        int[] valores = {50, 25, 75, 10, 30, 60, 90, 5, 15, 27, 65, 80};
        int insertados = 0;
        for (int valor : valores) {
            if (ARBOL.insertar(valor)) {
                insertados++;
            }
        }
        System.out.println("Se procesaron " + valores.length + " valores predefinidos.");
        System.out.println("Insertados exitosamente: " + insertados + ".");
    }

    /**
     * Muestra en consola los tres recorridos clasicos del arbol.
     */
    private static void mostrarRecorridos() {
        if (ARBOL.estaVacio()) {
            System.out.println("El arbol esta vacio. Inserte nodos primero.");
            return;
        }
        System.out.println("\nContenido del arbol:");
        System.out.println("Preorden : " + ARBOL.recorrerPreorden());
        System.out.println("Inorden  : " + ARBOL.recorrerInorden());
        System.out.println("Postorden: " + ARBOL.recorrerPostorden());
    }

    /**
     * Limpia el arbol actual para iniciar una nueva captura de datos.
     */
    private static void limpiarArbol() {
        ARBOL.reiniciar();
        System.out.println("Arbol reiniciado correctamente.");
    }

    /**
     * Lee un numero entero con validacion de formato y rango de tipo int.
     * @param mensaje texto a mostrar antes de solicitar la entrada
     * @return valor entero validado
     */
    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = SCANNER.nextLine().trim();
            if (entrada.matches("-?\\d+")) {
                try {
                    return Integer.parseInt(entrada);
                } catch (NumberFormatException e) {
                    System.out.println("Numero fuera de rango para tipo int. Intente de nuevo.");
                }
            } else {
                System.out.println("Entrada invalida. Debe ingresar un numero entero.");
            }
        }
    }
}

