# 🎮 Juego de 3 en Raya (Tic-Tac-Toe)

**Autor:** Cesar de Jesus Becerra Vera  
**Fecha:** 19 de Enero de 2026  
**Institución:** Centro Universitario de los Altos / Universidad de Guadalajara  
**Carrera:** Ingeniería en Computación - 4to Semestre  
**Profesor:** Maria Obdulia Gonzalez Fernandez  
**Materia:** Estructuras de Datos

## 📝 Descripción del Proyecto

Juego de 3 en raya implementado en Java con interfaz gráfica (Swing) que incluye:

- ✅ Matriz 3x3 con botones gráficos
- ✅ Registro de nombres de ambos jugadores
- ✅ **Número de partidas configurable** (1 a 5 partidas)
- ✅ Sistema de turnos alternados
- ✅ Puntos acumulados por jugador
- ✅ **Estructura de datos:** Arreglo de objetos Jugadores y matriz para el tablero
- ✅ **Ranking de los 5 mejores jugadores** (tabla de mejores puntajes)
- ✅ Validación de movimientos y detección de ganador o empate
- ✅ Determinación del ganador final según puntaje

## 🏗️ Estructura del Proyecto

```
3_en_linea/
├── src/
│   ├── app/
│   │   └── Ventana.java        # Interfaz gráfica del juego
│   └── back/
│       ├── Jugadores.java      # Clase para gestionar jugadores
│       └── juego.java          # Lógica del juego
│       └── Ranking.java        # Clase para gestionar el ranking
├── dist/                        # Archivos distribuidos (JAR)s
├── bin/                         # Archivos compilados (.class)
├── doc/                         # Documentación
├── lib/                         # Librerías externas (si las hubiera)
├── launch.sh                    # Script para ejecutar el juego
└── README.md                    # Este archivo
```

## 🎯 Características Principales

### Clase Jugadores
- Almacena nombre del jugador
- Control de turno (booleano)
- Puntos acumulados (int)
- Métodos para agregar puntos y cambiar turno

### Clase juego
- **Arreglo de Jugadores:** Los 2 jugadores se almacenan en un arreglo `Jugadores[]`
- Tablero 3x3 implementado como matriz de Strings
- Validación de movimientos
- Verificación de ganador (filas, columnas, diagonales)
- Detección de empates
- **Control de partidas configurable** (1 a 5 partidas)
- Determinación del ganador final

### Clase Ranking
- **Arreglo de objetos para Top 5:** Almacena los 5 mejores jugadores
- Máximo de 5 espacios en el arreglo
- Sistema de ordenamiento automático por puntos
- Actualización de puntajes acumulados
- Sustitución del jugador con menor puntaje si llega uno mejor
- Métodos para visualizar y gestionar el ranking

### Interfaz Gráfica (Ventana)
- Matriz 3x3 de botones interactivos
- Panel de información de jugadores
- Indicador de turno actual
- Contador de partidas
- Visualización de puntos en tiempo real
- **Botón "Nueva Partida"** para reiniciar
- **Botón "Ver Top 5"** para mostrar el ranking de mejores jugadores
- Diálogo con resultado final
- Sistema automático de guardado en ranking

## 🚀 Cómo Ejecutar

### Método Recomendado (Producción)

El script `launch.sh` compila el proyecto, crea un JAR ejecutable en la carpeta `dist/` y lanza el juego:

```bash
./launch.sh
```

Este script:
- Crea automáticamente la carpeta `dist/` si no existe
- Compila todos los archivos fuente
- Genera `dist/3enraya.jar` (archivo ejecutable)
- Lanza el juego automáticamente
- Compatible con Linux, macOS y Windows (Git Bash/WSL)

### Ejecución Manual del JAR

Una vez generado el JAR con `launch.sh`, puedes ejecutarlo directamente:

```bash
java -jar dist/3enraya.jar
```

### Compilación y Ejecución Manual

```bash
# Compilar
javac -d bin src/back/*.java src/app/*.java

# Ejecutar
java -cp bin app.Ventana
```

## 📊 Estructura de Datos Utilizada

El proyecto cumple con el requisito de **Estructuras de Datos** mediante:

```java
// Arreglo de objetos Jugadores
private Jugadores[] jugadores; // Arreglo de 2 elementos

// Inicialización
jugadores = new Jugadores[2];
jugadores[0] = jugador1;
jugadores[1] = jugador2;

// Tablero 3x3 como matriz
private String[][] tablero; // Matriz 3x3 para el juego
tablero = new String[3][3];
```

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Java
- **Interfaz Gráfica:** Java Swing
- **IDE Recomendado:** Visual Studio Code con Extension Pack for Java
- **JDK:** Java Development Kit 11 o superior

## 📚 Conceptos Aplicados

- Programación Orientada a Objetos
- Estructuras de Datos (Arreglos)
- Manejo de eventos (ActionListener)
- Interfaces gráficas con Swing

## 👨‍💻 Autor

**Cesar de Jesus Becerra Vera**  
Estudiante de Ingeniería en Computación  
Centro Universitario de los Altos - UdeG

---

*Práctica 1 - Estructuras de Datos - 4to Semestre*

