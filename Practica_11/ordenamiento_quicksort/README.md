# Ordenamiento con Algoritmo Quick Sort
## Descripción
Programa que implementa el algoritmo de ordenamiento Quick Sort para ordenar un arreglo fijo de 10 números enteros.
El programa utiliza la estrategia de **divide y conquista** para particionar el arreglo alrededor de un pivote y ordena recursivamente las subdivisiones.
El ordenamiento se realiza de **menor a mayor** sin utilizar métodos predefinidos del lenguaje.

## Compilación y Ejecución
### Opción 1: Usando el script launch.sh
```bash
./launch.sh
```
### Opción 2: Manual
```bash
# Compilar
javac -encoding UTF-8 -d bin src/back/*.java src/app/*.java
# Ejecutar
java -cp bin app.Main
```

## Estructura del Proyecto
```
ordenamiento_quicksort/
├── src/
│   ├── app/
│   │   └── Main.java # Clase principal que ejecuta la práctica
│   └── back/
│       ├── Numeros.java # Clase estructura para almacenar un número
│       └── QuickSort.java # Lógica de comparación y ordenamiento Quick Sort
├── bin/ # Directorio para archivos .class compilados
├── doc/ # Documentación Javadoc del proyecto
├── dist/ # Directorio para el archivo JAR generado
├── launch.sh # Script para compilar, empaquetar y ejecutar la aplicación
└── README.md # Este archivo de documentación
```

## Algoritmo de Ordenamiento Quick Sort
Quick Sort es un algoritmo de ordenamiento eficiente que utiliza la estrategia de divide y conquista.
**Características:**
- Elige un elemento como pivote
- Particiona el arreglo en elementos menores y mayores que el pivote
- Ordena recursivamente cada partición
- Complejidad: O(n log n) en promedio, O(n²) en el peor caso
- Ordena de forma ascendente (menor a mayor)
- No utiliza métodos predefinidos de ordenamiento
**Pasos del algoritmo:**
1. Seleccionar un pivote (último elemento del arreglo)
2. Particionar el arreglo alrededor del pivote
3. Aplicar Quick Sort recursivamente a la subarray izquierda
4. Aplicar Quick Sort recursivamente a la subarray derecha

## Flujo del Programa
1. **Menú inicial:** El usuario elige cargar números manualmente o generar números aleatorios.
2. **Muestra original:** Se despliega el arreglo antes del ordenamiento.
3. **Ordenamiento:** Se aplica el algoritmo Quick Sort.
4. **Muestra final:** Se despliega el arreglo después del ordenamiento en orden ascendente.

## Autor
Cesar de Jesus Becerra Vera  
Práctica 11 - Estructuras de Datos  
Universidad de Guadalajara - Centro Universitario de los Altos (CUAltos)  

## Fecha
Marzo 2026
