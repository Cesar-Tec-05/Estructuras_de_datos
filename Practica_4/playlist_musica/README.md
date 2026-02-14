# 🎵 Playlist de Música - Lista Enlazada

Implementación de una playlist de música utilizando listas enlazadas en Java.

## 📋 Descripción

Este proyecto simula el funcionamiento de un reproductor de música donde cada canción es un nodo en una lista enlazada. La aplicación muestra una canción a la vez (estilo reproductor) y permite navegar entre ellas usando botones de navegación.

## ✨ Características
### Información de cada Canción
- **Nombre de la canción**
- **Nombre del artista**
- **Género musical**
- **Duración** 

### Operaciones Disponibles
1. **◄◄ Anterior**: Navega a la canción anterior en la playlist
2. **➕ Agregar**: Inserta una nueva canción al final de la playlist
3. **📋 Ver Playlist**: Muestra todas las canciones en una ventana emergente (haz clic en una canción para reproducirla)
4. **►► Siguiente**: Navega a la siguiente canción en la playlist
5. **🗑️ Eliminar Actual**: Elimina la canción que se está mostrando actualmente

### Modo de Visualización
- **Vista de Reproductor**: Muestra UNA canción a la vez con toda su información
- **Navegación Intuitiva**: Usa los botones ◄◄ y ►► para moverte entre canciones
- **Contador de Posición**: Indica qué canción estás viendo (ej: "Canción 2 de 5")
- **Playlist Completa**: Vista emergente con todas las canciones y marcador de la actual

## 🎨 Interfaz
La aplicación cuenta con una interfaz gráfica con:
- **Componentes nativos de Swing** (JLabels, JPanels, JDialog) para mostrar toda la información
- **Vista estilo reproductor** que muestra una canción a la vez con diseño visual
- **Tipografía grande y clara** para fácil lectura (Segoe UI)
- **Ventana de playlist nativa** con scroll, cada canción en su propio panel con bordes
- **Canciones clickeables** en la playlist - haz clic para ir directamente a reproducirla
- **Efectos hover** en canciones para indicar que son clickeables
- **Cursor de mano** sobre las canciones para mejor UX
- **Formulario de agregar canción** campos visibles y botones estilizados
- **Botones de navegación** con efectos hover interactivos
- **Separadores visuales** y espaciado
- **Indicador visual destacado** de la canción actual
- **Todas las ventanas modales** son nativas de Swing (JDialog) para mantener la coherencia visual

## 🚀 Cómo Ejecutar
### Opción 1: Usando el script de lanzamiento (Linux/Mac)
```bash
./launch.sh
```

### Opción 2: Manualmente
```bash
# Compilar
javac -encoding UTF-8 -d bin src/back/*.java src/app/*.java

# Ejecutar
java -cp bin app.ListaEnlazada
```

### Opción 3: Desde Windows
```cmd
javac -encoding UTF-8 -d bin src/back/*.java src/app/*.java
java -cp bin app.ListaEnlazada
```

## 📁 Estructura del Proyecto
```
playlist_musica/
├── src/
│   ├── app/                      # frontend
│   │   └── ListaEnlazada.java    # Interfaz gráfica y lógica basica de la playlist
│   └── back/                     # backend
│       ├── Cancion.java          # Clase que representa una canción
│       └── Nodo.java             # Nodo de la lista enlazada
├── dist/                         # Archivos empaquetados (.jar)
├── bin/                          # Archivos compilados (.class)
├── doc/                          # Documentación JavaDoc
├── lib/                          # Bibliotecas externas
├── launch.sh                     # Script de ejecución
└── README.md                     # Este archivo
```

## 🔧 Tecnologías Utilizadas
- **Java** (JDK 8 o superior)
- **Swing** para la interfaz gráfica
- **Listas Enlazadas** como estructura de datos

## 🎓 Práctica 4 - Estructuras de Datos

Proyecto desarrollado como parte del curso de Estructuras de Datos.
- **Profesor**: María Obdulia González Fernández
- **Alumno**: César de Jesús Becerra Vera
- **Universidad de Guadalajara - Centro Universitario de los Altos**