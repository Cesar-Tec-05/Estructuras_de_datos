# 🔍 Búsqueda Binaria en Datos Alfanuméricos
---
## 📝 Descripción del Proyecto
Aplicación que implementa el **algoritmo de búsqueda binaria** para localizar valores específicos dentro de un arreglo de datos alfanuméricos completamente ordenado. El programa proporciona una interfaz interactiva que permite al usuario:
- ✅ Visualizar el arreglo ordenado completo
- ✅ Buscar elementos usando búsqueda binaria
- ✅ Conocer la posición exacta del elemento encontrado
- ✅ Recibir información sobre el proceso de búsqueda
- ✅ Alternativas cuando el elemento no existe
**Características principales:**
- 🎯 Búsqueda eficiente con complejidad O(log n)
- 📊 Arreglo de 60 profesiones en español ordenadas alfabéticamente
- 🔄 Contador de comparaciones realizadas
- 💡 Sugerencias de elementos similares
- ✔️ Validación completa de entrada del usuario
---

## 🏗️ Estructura del Proyecto
```
busqueda_binaria_datos_alfanumericos/
├── src/
│   ├── app/
│   │   └── Main.java                 # Interfaz gráfica de consola
│   └── back/
│       └── BusquedaBinaria.java      # Implementación del algoritmo
├── bin/                              # Archivos compilados (.class)
├── doc/                              # Documentación JavaDoc
├── lib/                              # Librerías externas (si las hubiera)
├── launch.sh                         # Script para ejecutar el programa
└── README.md                         # Este archivo
```
---

## 🚀 Instalación y Ejecución
### Requisitos Previos
- Java Development Kit (JDK) 11 o superior
- Sistema operativo: Linux, Windows o macOS
### Método 1: Usando el Script de Ejecución (Linux/macOS)
```bash
# Navegar al directorio del proyecto
cd busqueda_binaria_datos_alfanumericos/
# Dar permisos de ejecución al script
chmod +x launch.sh
# Ejecutar el programa
./launch.sh
```
### Método 2: Compilación y Ejecución Manual
```bash
# Navegar al directorio del proyecto
cd busqueda_binaria_datos_alfanumericos/
# Compilar el proyecto
javac -d bin src/app/*.java src/back/*.java
# Ejecutar la aplicación
java -cp bin app.Main
```
### Método 3: En VS Code
1. Abrir el archivo `src/app/Main.java`
2. Presionar `Ctrl + Shift + D` para abrir el depurador
3. Seleccionar "Java" como lenguaje de depuración
4. Hacer clic en "Ejecutar" o presionar `F5`
---

## 📖 Guía de Uso
### 1. Visualizar el Arreglo
- Seleccionar opción **1** en el menú
- Se muestra el arreglo completo con índices numerados
- Total de elementos y rango (primer → último)
### 2. Realizar una Búsqueda
- Seleccionar opción **2** en el menú
- Ingresar el valor a buscar (ej: "Abogado" o "Bombero")
- El programa muestra:
  - ✅ Si fue encontrado o no
  - 📍 Posición exacta (si existe)
  - 🔄 Número de comparaciones realizadas
  - 📝 Contexto (elementos adyacentes)
  - 💡 Sugerencias si no existe
### 3. Consultar Información del Algoritmo
- Seleccionar opción **3** en el menú
- Se muestra documentación completa del algoritmo
- Incluye descripción, ventajas, limitaciones y complejidad
### 4. Salir del Programa
- Seleccionar opción **4** en el menú
- Se despliega un mensaje de despedida
---


## 📄 Licencia
Este proyecto es parte del curso de Estructuras de Datos de la Universidad de Guadalajara.
---

## 👨‍💻 Autor
**Cesar de Jesus Becerra Vera**  
Centro Universitario de los Altos  
Universidad de Guadalajara  
Marzo de 2026
