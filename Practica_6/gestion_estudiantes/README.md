# Gestión de Estudiantes - Lista Doblemente Enlazada

## Descripción
Programa que implementa una lista doblemente enlazada para gestionar la información académica de estudiantes.

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
gestion_estudiantes/
├── src/ 
│   ├── app/
│   │   └── Main.java # Clase principal que contiene la clase Main con la interfaz gráfica y la lógica de la aplicación.
│   └── back/
│       ├── Gestion_estudiantes.java # Clase que implementa la lógica de gestión de estudiantes
│       └── Nodo.java # Clase que representa un nodo en la lista doblemente enlazada
├── bin/ # Directorio para los archivos .class compilados
├── doc/ # Documentación del proyecto (si aplica)
├── dist/ # Directorio para el archivo JAR generado
├── launch.sh # Script para compilar, empaquetar y ejecutar la aplicación
└── README.md # Este archivo de documentación
```

## Autor
Cesar de Jesus Becerra Vera
Práctica 6 - Estructuras de Datos
Universidad de Guadalajara - Centro Universitario de los Altos (CUAltos)
## Fecha
Febrero 2026
