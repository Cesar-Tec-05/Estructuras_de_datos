## Inicio de Sesión
Aplicación con interfaz gráfica en Java Swing que demuestra el almacenamiento y gestión secuencial de registros en un archivo de texto. El programa permite registrar usuarios y contraseñas, persistir los datos en una base de datos local, visualizar los registros almacenados y autenticarse contra los datos guardados.

### Descripción
Esta práctica implementa un sistema de gestión de usuarios capaz de:
- **Guardar datos de manera secuencial**: Los registros se escriben uno después de otro en un archivo de texto, respetando el orden de inserción.
- **Persistencia en archivo**: Cada dato se almacena de forma permanente en [db/usuarios.txt](db/usuarios.txt), permitiendo recuperar la información entre ejecuciones.
- **Interfaz gráfica intuitiva**: Menú accesible con opciones claramente definidas para cada operación.

### Funcionalidades Principales
1. **Agregar Registro**: Permite ingresar un usuario y contraseña nuevos, que se guardan automáticamente en el archivo de texto de forma secuencial.
2. **Mostrar Registros**: Visualiza en una ventana todos los usuarios y contraseñas almacenados hasta el momento.
3. **Autenticarse**: Verifica las credenciales ingresadas contra los registros guardados, validando que el usuario existe y la contraseña coincide.
4. **Salir**: Cierra la aplicación de forma ordenada.

### Almacenamiento de Datos
Los registros se guardan de forma secuencial en [db/usuarios.txt](db/usuarios.txt) con el siguiente formato:
```
usuario1|contraseña1
usuario2|contraseña2
usuario3|contraseña3
```

Cada usuario y su contraseña se separan con una barra vertical (`|`) y se escriben en líneas consecutivas. El archivo preserva el orden de inserción de los registros.

### Ejecución
1. Abre una terminal en el directorio [inicio_sesion](.).
2. Ejecuta el script de arranque:
   ```bash
   ./launch.sh
   ```
3. El script realizará las siguientes acciones:
   - Compilará el código fuente de Java.
   - Generará un archivo JAR ejecutable.
   - Iniciará la aplicación con la interfaz gráfica.

### Estructura del Proyecto
- **[src/app/Main.java](src/app/Main.java)**: Contiene la clase principal que implementa la interfaz gráfica (ventana Swing) y gestiona el menú de opciones.
- **[src/back/Usuarios.java](src/back/Usuarios.java)**: Contiene la clase modelo que representa un registro de usuario con métodos para lectura, escritura y validación de credenciales.
- **[db/usuarios.txt](db/usuarios.txt)**: Archivo de base de datos local donde se almacenan los registros de forma secuencial.
- **[launch.sh](launch.sh)**: Script de compilación, empaquetamiento y ejecución compatible con Linux, macOS y Windows (Git Bash/WSL).

### Requisitos
- Java Development Kit (JDK) 8 o superior
- Sistema operativo compatible: Linux, macOS, Windows (con Git Bash o WSL)

---

**Autor**: Cesar de Jesus Becerra Vera  
**Fecha**: 18 de Mayo de 2026  
**Versión**: 1.0  
**Institución**: Centro Universitario de los Altos / Universidad de Guadalajara  
**Programa**: Ingeniería en Computación / 4to Semestre  
**Profesor**: María Obdulia González Fernández
