#!/bin/bash

# ================================================================
# Script de lanzamiento para producción - Búsqueda Binaria con Datos Alfanuméricos
# @author Cesar de Jesus Becerra Vera
# @since 27 de Marzo de 2026
# @version 1.0
# ARCHIVO: launch.sh
# CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
# INGENIERIA EN COMPUTACION / 4TO SEMESTRE
# PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
# DESCRIPCIÓN: Script para compilar, empaquetar y ejecutar la aplicación de búsqueda binaria con datos alfanuméricos.
# Compatible: Linux, macOS, Windows (Git Bash/WSL)
# ================================================================

# Obtener el directorio donde se encuentra este script
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR"

# Configuración de rutas
DIST_DIR="dist"
BIN_DIR="bin"

# Mensaje de inicio
echo "========================================="
echo "🚀 Iniciando el proceso de lanzamiento de Búsqueda Binaria con Datos Alfanuméricos 🚀"
echo "========================================="
echo ""

# Crear directorio dist si no existe
if [ ! -d "$DIST_DIR" ]; then
	echo "📁 Creando directorio dist..."
	mkdir -p "$DIST_DIR"
fi

# Crear directorio bin si no existe
if [ ! -d "$BIN_DIR" ]; then
	echo "📁 Creando directorio bin..."
	mkdir -p "$BIN_DIR"
fi

# Limpiar compilaciones anteriores
echo "🧹 Limpiando compilaciones anteriores..."
rm -rf "$BIN_DIR"/*
rm -rf "$DIST_DIR"/*

# Compilar el proyecto
echo "🔨 Compilando el proyecto..."
javac -encoding UTF-8 -d "$BIN_DIR" src/back/*.java src/app/*.java

# Verificar si la compilación fue exitosa
if [ $? -ne 0 ]; then
	echo "❌ Error en la compilación."
	exit 1
fi
echo "✅ Compilación exitosa."

# Crear JAR ejecutable
echo "📦 Creando archivo JAR..."
cd "$BIN_DIR"
jar cfe "../$DIST_DIR/busqueda_binaria.jar" app.Main app/*.class back/*.class
cd ..

# Verificar si la creación del JAR fue exitosa
if [ $? -eq 0 ]; then
	echo "✅ JAR creado exitosamente en $DIST_DIR/busqueda_binaria.jar"
	echo ""
	echo "📝 Iniciando la aplicación de Búsqueda Binaria con Datos Alfanuméricos..."
	echo ""
	java -jar "$DIST_DIR/busqueda_binaria.jar"
else
	echo "❌ Error al crear el JAR."
	exit 1
fi
