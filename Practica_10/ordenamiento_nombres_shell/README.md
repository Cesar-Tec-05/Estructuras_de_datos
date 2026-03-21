# Ordenamiento de Nombres con Método Shell

## Descripción
Programa que implementa el método de ordenamiento Shell para ordenar alfabéticamente un arreglo fijo de 10 nombres.

El programa permite dos modos de carga de nombres:
- Captura manual por el usuario.
- Generación aleatoria por el programa.

El ordenamiento considera mayúsculas y minúsculas de forma consistente, realizando comparaciones sin distinción entre ellas.

## Compilación y Ejecución
### Opción 1: Usando el script launch.sh
```bash
./launch.sh
```

### Opción 2: Manual
```bash
# Compilar
javac -d bin src/back/*.java src/app/*.java

# Ejecutar
java -cp bin app.Main
```

## Estructura del Proyecto
```
ordenamiento_nombres_shell/
├── src/
│   ├── app/
│   │   └── Main.java # Clase principal que ejecuta la práctica
│   └── back/
│       ├── Nombres.java # Clase estructura para almacenar un nombre
│       └── Shell.java # Lógica de comparación y ordenamiento Shell
├── bin/ # Directorio para archivos .class compilados
├── doc/ # Documentación del proyecto (si aplica)
├── dist/ # Directorio para el archivo JAR generado
├── launch.sh # Script para compilar, empaquetar y ejecutar la aplicación
└── README.md # Este archivo de documentación
```

## Algoritmo de Ordenamiento Shell
El ordenamiento Shell utiliza una secuencia de saltos que se reduce gradualmente. Es una extensión del ordenamiento por inserción que permite que los elementos se muevan rápidamente a sus posiciones aproximadas.

**Características:**
- Más eficiente que burbuja para arreglos medianos.
- Complejidad: O(n²) en el peor caso, pero generalmente O(n log n).
- Ordena de forma alfabética (A-Z) sin distinción de mayúsculas/minúsculas.

## Flujo del Programa
1. **Menú inicial:** El usuario elige cargar nombres manualmente o generar nombres aleatorios.
2. **Muestra original:** Se despliega el arreglo antes del ordenamiento.
3. **Ordenamiento:** Se aplica el algoritmo Shell Sort.
4. **Muestra final:** Se despliega el arreglo después del ordenamiento.

## Requisitos Cumplidos
✅ Arreglo de tamaño fijo (10 espacios)  
✅ Cada posición almacena un nombre  
✅ Implementación del algoritmo Shell Sort  
✅ Muestra del arreglo original y ordenado  
✅ Ordenamiento considerando mayúsculas y minúsculas de forma consistente  

## Autor
Cesar de Jesus Becerra Vera  
Práctica 10 - Estructuras de Datos  
Universidad de Guadalajara - Centro Universitario de los Altos (CUAltos)  

## Fecha
Marzo 2026
