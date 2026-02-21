# Historial de Navegación Web

## Descripción
Simulador de navegador web completo implementado mediante una **lista doblemente enlazada** con **interfaz gráfica** (Java Swing). El proyecto simula el funcionamiento real de un navegador web con botones de navegación, barra de direcciones, área de contenido y panel de historial lateral.

## Estructura del Proyecto
```
historial_de_navegacion/
├── src/
│   ├── app/
│   │   └── Main.java          # Interfaz de usuario (Frontend)
│   └── back/
│       ├── Nodo.java          # Clase Nodo de la lista enlazada
│       └── Navegador.java     # Clase Navegador (Backend)
├── bin/                       # Archivos compilados
├── doc/                       # Documentación Javadoc
├── lib/                       # Dependencias
├── dist/                      # Archivos ejecutables (JAR)
├── README.md                  # Descripción general del proyecto
└── launch.sh                  # Script de ejecución
```

## Clases Principales
### 1. Nodo.java
Representa un sitio web en el historial de navegación.
**Atributos:**
- `String url`: Dirección del sitio web
- `Nodo siguiente`: Referencia al siguiente nodo
- `Nodo anterior`: Referencia al nodo anterior
### 2. Navegador.java
Gestiona la lista enlazada de sitios web.
**Métodos principales:**
- `agregarDireccion(String url)`: Agrega un nuevo sitio al inicio
- `mostrarDireccionActual()`: Retorna la URL del sitio actual
- `mostrarHistorial()`: Muestra todas las URLs visitadas
- `obtenerTamanio()`: Retorna el número de sitios en el historial
- `estaVacio()`: Verifica si el historial está vacío
### 3. Main.java
Interfaz gráfica tipo navegador web con Java Swing.
**Componentes principales:**
**Barra de Navegación Superior:**
- **Botón Atrás (←)**: Navega a la página anterior
- **Botón Adelante (→)**: Navega a la página siguiente
- **Botón Recargar (↻)**: Recarga la página actual
- **Botón Inicio (⌂)**: Vuelve a la primera página
- **Barra de URL**: Campo para ingresar direcciones web
- **Botón Ir**: Navega a la URL ingresada
**Área Central:**
- **Panel de Contenido**: Simula la visualización de páginas web
- **Título de Página**: Muestra la URL actual
- **Área de Texto**: Contenido simulado de la página
**Panel Lateral Derecho:**
- **Lista de Historial**: Muestra todas las páginas visitadas
- **Selección Visual**: Click para navegar a cualquier página
**Barra de Estado Inferior:**
- **Mensajes**: Estado actual de la navegación

## Compilación y Ejecución
### Opción 1: Script de ejecución
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

## Documentación Javadoc
La documentación completa de la API está disponible en formato Javadoc en la carpeta `doc/`.
### Visualizar la documentación
Abre el archivo `index.html` en tu navegador:
```bash
# Opción 1: Abrir directamente
firefox doc/index.html
# Opción 2: Usar navegador del sistema
xdg-open doc/index.html
```
### Generar Javadoc (si es necesario)
```bash
javadoc -d doc -encoding UTF-8 -charset UTF-8 -sourcepath src -subpackages app:back \
  -windowtitle "Simulador de Historial de Navegación" \
  -doctitle "Simulador de Historial de Navegación - Documentación API" \
  -header "Práctica 5" -author -version
```

## Autor
**Práctica 5 - Estructuras de Datos**
- **Alumno**: Cesar de Jesus Becerra Vera
- **Materia**: Estructuras de Datos
- **Profesor**: Maria Obdulia Gonzalez Fernandez
- **Universidad**: Universidad de Guadalajara - Centro Universitario de los Altos (CUALTOS)

## Licencia
Este proyecto es de uso académico.

Fecha: 21 de febrero de 2026
