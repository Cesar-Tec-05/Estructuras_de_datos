# Sistema de Turnos Bancarios con Cola Circular

## 📋 Descripción

Sistema de gestión de turnos para un banco implementado con una **cola circular** (estructura de datos FIFO - First In, First Out).

**Autor:** Cesar de Jesus Becerra Vera  
**Institución:** Centro Universitario de los Altos - Universidad de Guadalajara  
**Curso:** Estructuras de Datos - 4to Semestre  
**Profesor:** Maria Obdulia Gonzalez Fernandez  
**Fecha:** Febrero 2026

---

## 🎯 Características Principales

- **Cola Circular Dinámica**: Implementación eficiente de una cola circular con capacidad configurable
- **Interfaz Gráfica Moderna**: Diseño oscuro e intuitivo con temática profesional
- **Visualización en Tiempo Real**: Representación gráfica del estado de la cola
- **Gestión Completa de Turnos**: 
  - Agregar clientes a la cola
  - Atender clientes (eliminar de la cola)
  - Consultar el próximo cliente
  - Limpiar toda la cola
- **Estadísticas Dinámicas**: Información en tiempo real sobre espacios ocupados y disponibles
- **Sistema de Turnos Automático**: Numeración automática e incremental de turnos

---

## 🏗️ Estructura del Proyecto

```
cola_circular/
├── src/
│   ├── app/
│   │   └── Main.java           # Interfaz gráfica del sistema
│   └── back/
│       └── Turnos_banco.java   # Lógica de la cola circular
├── bin/                         # Archivos compilados
├── doc/                         # Documentación JavaDoc
├── lib/                         # Librerías externas
├── launch.sh                    # Script de ejecución
└── README.md                    # Este archivo
```

---

## 🔧 Componentes del Sistema

### 1. Turnos_banco.java (Backend)
Clase que implementa la cola circular con las siguientes funcionalidades:

- **Métodos principales:**
  - `agregarCliente(String nombre)`: Agrega un cliente a la cola
  - `atenderCliente()`: Atiende y elimina el cliente al inicio de la cola
  - `verProximo()`: Consulta el siguiente cliente sin eliminarlo
  - `estaVacia()`: Verifica si la cola está vacía
  - `estaLlena()`: Verifica si la cola está llena
  - `limpiarCola()`: Vacía completamente la cola
  - `obtenerTamanio()`: Retorna el número de elementos en la cola

### 2. Main.java (Frontend)
Interfaz gráfica con diseño moderno que incluye:

- **Panel de Control**: Botones para todas las operaciones
- **Campo de Entrada**: Para ingresar el nombre del cliente
- **Visualización de Cola**: Representación gráfica de los elementos
- **Panel de Estado**: Muestra el estado actual de todas las operaciones
- **Estadísticas**: Información sobre espacios ocupados y disponibles
- **Próximo Turno**: Indicador del siguiente cliente a ser atendido

---

## 🚀 Instalación y Ejecución

### Requisitos Previos
- Java Development Kit (JDK) 8 o superior
- Sistema operativo: Linux, Windows o macOS

### Método 1: Usando el Script de Ejecución (Linux/macOS)

```bash
# Dar permisos de ejecución al script
chmod +x launch.sh

# Ejecutar el programa
./launch.sh
```

### Método 2: Compilación Manual

```bash
# Compilar el proyecto
javac -d bin src/app/*.java src/back/*.java

# Ejecutar la aplicación
java -cp bin app.Main
```

---

## 📖 Uso del Sistema

### Operaciones Disponibles

1. **Agregar Cliente**

2. **Atender Cliente**

3. **Ver Próximo**

4. **Limpiar Cola** 

5. **Salir**

---

## 💡 Conceptos de Estructura de Datos

### Cola Circular
Una **cola circular** es una estructura de datos lineal que sigue el principio FIFO (First In, First Out) donde el último elemento apunta al primero, formando una estructura circular. 

**Ventajas:**
- Uso eficiente de la memoria
- No es necesario desplazar elementos
- Reutilización de espacios liberados
- Operaciones de inserción y eliminación en O(1)

**Implementación:**
- Array de tamaño fijo
- Punteros `inicio` y `fin` para control de posiciones
- Uso de aritmética modular para circularidad: `(posicion + 1) % capacidad`

---

## 📝 Autor

**Cesar de Jesus Becerra Vera**  
Centro Universitario de los Altos - UdeG  
Ingeniería en Computación