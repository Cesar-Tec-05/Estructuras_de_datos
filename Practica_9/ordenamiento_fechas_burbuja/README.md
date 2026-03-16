# Ordenamiento de Fechas con Método Burbuja

## Descripción
Programa que implementa el método de ordenamiento burbuja para ordenar cronológicamente un arreglo fijo de 5 fechas.

El programa permite dos modos de carga de fechas:
- Captura manual por el usuario.
- Generación aleatoria por el programa.

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
ordenamiento_fechas_burbuja/
├── src/
│   ├── app/
│   │   └── Main.java # Clase principal que ejecuta la práctica
│   └── back/
│       ├── Fecha.java # Clase estructura para almacenar día, mes y año
│       └── Ordenamiento_burbuja.java # Lógica de comparación y ordenamiento burbuja
├── bin/ # Directorio para archivos .class compilados
├── doc/ # Documentación del proyecto (si aplica)
├── dist/ # Directorio para el archivo JAR generado
├── launch.sh # Script para compilar, empaquetar y ejecutar la aplicación
└── README.md # Este archivo de documentación
```

## Autor
Cesar de Jesus Becerra Vera
Práctica 9 - Estructuras de Datos
Universidad de Guadalajara - Centro Universitario de los Altos (CUAltos)

## Fecha
Marzo 2026
