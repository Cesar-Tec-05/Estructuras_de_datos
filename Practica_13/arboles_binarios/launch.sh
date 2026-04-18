#!/bin/bash

# ================================================================
# Script de lanzamiento para produccion - Arboles Binarios
# @author Cesar de Jesus Becerra Vera
# @since Abril de 2026
# @version 1.0
# ARCHIVO: launch.sh
# CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
# INGENIERIA EN COMPUTACION / 4TO SEMESTRE
# PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
# DESCRIPCION: Script para compilar, empaquetar y ejecutar la aplicacion de arboles binarios.
# Compatible: Linux, macOS, Windows (Git Bash/WSL)
# ================================================================

# Obtener el directorio donde se encuentra este script
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR"

# Configuracion de rutas
DIST_DIR="dist"
BIN_DIR="bin"
JAR_NAME="arboles_binarios.jar"

# Mensaje de inicio
echo "========================================="
echo "Iniciando el proceso de lanzamiento de Arboles Binarios"
echo "========================================="
echo ""

# Crear directorio dist si no existe
if [ ! -d "$DIST_DIR" ]; then
    echo "Creando directorio dist..."
    mkdir -p "$DIST_DIR"
fi

# Crear directorio bin si no existe
if [ ! -d "$BIN_DIR" ]; then
    echo "Creando directorio bin..."
    mkdir -p "$BIN_DIR"
fi

# Limpiar compilaciones anteriores
echo "Limpiando compilaciones anteriores..."
rm -rf "$BIN_DIR"/*
rm -rf "$DIST_DIR"/*

# Compilar el proyecto
echo "Compilando el proyecto..."
javac -encoding UTF-8 -d "$BIN_DIR" src/back/*.java src/app/*.java

# Verificar si la compilacion fue exitosa
if [ $? -ne 0 ]; then
    echo "Error en la compilacion."
    exit 1
fi
echo "Compilacion exitosa."

# Crear JAR ejecutable
echo "Creando archivo JAR..."
cd "$BIN_DIR"
jar cfe "../$DIST_DIR/$JAR_NAME" app.Main app/*.class back/*.class
cd ..

# Verificar si la creacion del JAR fue exitosa
if [ $? -eq 0 ]; then
    echo "JAR creado exitosamente en $DIST_DIR/$JAR_NAME"
    echo ""
    echo "Iniciando la aplicacion de Arboles Binarios..."
    echo ""
    java -jar "$DIST_DIR/$JAR_NAME"
else
    echo "Error al crear el JAR."
    exit 1
fi
