# Editor de Texto con Pila de Deshacer

## Descripción
Este programa implementa un **editor de texto similar a Microsoft Word** que utiliza una **pila** (stack) para almacenar las acciones de edición y permitir deshacerlas. Es una simulación real de la funcionalidad "Deshacer" (Ctrl+Z) de los editores de texto profesionales.

## Características

### 💻 Documento Editable
- Simula un documento de Word real
- Las acciones se aplican directamente al texto del documento

### 📚 Capacidad de la Pila
- **Capacidad máxima fija**: 10 acciones
- Implementación mediante **arreglos** (arrays)
- Almacena el estado completo del documento antes de cada acción

#### 🔧 Operaciones de la Pila

- ##### Push

- ##### Pop

- ##### Mostrar

## Estructura de Datos

```
Pila de Objetos Accion[]
Capacidad: 10 elementos

class Accion {
    String tipoAccion;        // Nombre de la acción
    String estadoAnterior;    // Contenido del documento antes de la acción
}

Ejemplo:
  TOPE ↓
→ [10] Aplicar negrita          (Más reciente)
  [9]  Pegar texto: "ejemplo"
  [8]  Cortar texto
  [7]  Insertar texto: "hola"
  [6]  Eliminar texto
  BASE ↑                        (Más antigua)
```

## Principio LIFO (Last In, First Out)
- El **último** cambio realizado es el **primero** en deshacerse
- Cada acción almacena el estado completo del documento
- Al deshacer, se restaura exactamente el estado anterior

## Compilación y Ejecución

### Opción 1: Usar el script launch.sh
```bash
./launch.sh
```

### Opción 2: Compilación manual
```bash
# Compilar
javac -d bin src/app/Pilas.java

# Ejecutar
java -cp bin app.Pilas
```

## Cómo Usar

1. **Insertar texto**: Posiciona el cursor → Click en "📝 Insertar" → Escribe el texto
2. **Aplicar formatos**: Selecciona texto → Click en el formato deseado (B, I, U)
3. **Copiar/Pegar**: Selecciona texto → Copiar → Posiciona cursor → Pegar
4. **Deshacer**: Click en "↶ Deshacer" para revertir la última acción
5. **Ver historial**: Click en "📊 Ver Pila" para ver todas las acciones

## 🏗️ Estructura del Proyecto

```
Practica_3/pilas/
├── src/
│   ── app/
│       └── Pilas.java        # Programa principal del editor de texto
├── dist/                        # Archivos distribuidos (JAR)s
├── bin/                         # Archivos compilados (.class)
├── doc/                         # Documentación
├── lib/                         # Librerías externas (si las hubiera)
├── launch.sh                    # Script para ejecutar el editor de texto
└── README.md                    # Este archivo
```


## Tecnologías
- **Lenguaje**: Java
- **GUI**: Swing (JFrame, JTextArea, JSplitPane, JButton)
- **Estructura de datos**: Array estático de objetos Accion
- **IDE Recomendado:** Visual Studio Code con Extension Pack for Java
- **JDK:** Java Development Kit 11 o superior

## Autor
Cesar de Jesus Becerra Vera - Estructuras de Datos


