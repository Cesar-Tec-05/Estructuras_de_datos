# Arboles Binarios - Insercion y Recorridos

## Descripcion
Programa de consola que permite crear y manipular un arbol binario de busqueda usando nodos enlazados.

La aplicacion permite:
- Crear la raiz del arbol.
- Insertar nodos respetando la estructura del arbol binario de busqueda.
- Recorrer y mostrar el contenido del arbol en preorden, inorden y postorden.
- Cargar un conjunto de valores predefinidos para pruebas rapidas.

## Estructura del Proyecto
```
arboles_binarios/
├── src/
│   ├── app/
│   │   └── Main.java              # Interfaz de consola y menu principal
│   └── back/
│       ├── Nodo.java              # Nodo enlazado del arbol
│       └── ArbolBinario.java      # Logica de insercion y recorridos
├── bin/                           # Archivos compilados (.class)
├── dist/                          # Archivo JAR ejecutable generado por launch.sh
├── doc/                           # Documentacion JavaDoc (si se genera)
├── lib/                           # Librerias externas (si aplica)
├── launch.sh                      # Script de despliegue de produccion
└── README.md                      # Este archivo
```

## Compilacion y Ejecucion
### Opcion 1: Script de lanzamiento
```bash
chmod +x launch.sh
./launch.sh
```

### Opcion 2: Manual
```bash
# Compilar
javac -encoding UTF-8 -d bin src/back/*.java src/app/*.java

# Ejecutar
java -cp bin app.Main
```

## Flujo General de Uso
1. Crear la raiz desde el menu.
2. Insertar nodos adicionales.
3. Visualizar recorridos para comprobar la estructura jerarquica.
4. Reiniciar arbol si se desea una nueva prueba.

## Autor
Cesar de Jesus Becerra Vera

## Fecha
Abril 2026
