#!/bin/bash

# ================================================================
# Script de lanzamiento para producción - inicio_sesion
# @author Cesar de Jesus Becerra Vera
# @since 18 de Mayo de 2026
# @version 1.0
# ARCHIVO: launch.sh
# CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
# INGENIERIA EN COMPUTACION / 4TO SEMESTRE
# PROFESOR: MARIA OBDULIA GONZALEZ FERNANDEZ
# DESCRIPCIÓN: Script para compilar, documentar, empaquetar y ejecutar la práctica de inicio de sesión.
# Compatible: Linux, macOS, Windows (Git Bash/WSL)
# ================================================================

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR"

DIST_DIR="dist"
BIN_DIR="bin"

echo "========================================="
echo "  INICIO DE SESIÓN - Despliegue a Producción"
echo "========================================="
echo ""

mkdir -p "$DIST_DIR" "$BIN_DIR" db

echo "🧹 Limpiando compilaciones anteriores..."
rm -rf "$BIN_DIR"/*
rm -rf "$DIST_DIR"/*

echo "🔨 Compilando el proyecto..."
javac -encoding UTF-8 -d "$BIN_DIR" src/back/*.java src/app/*.java

if [ $? -ne 0 ]; then
	echo "❌ Error en la compilación."
	exit 1
fi

echo "✅ Compilación exitosa."

echo " Creando archivo JAR..."
cd "$BIN_DIR"
jar cfe "../$DIST_DIR/inicio_sesion.jar" app.Main app/*.class back/*.class
cd ..

if [ $? -eq 0 ]; then
	echo "✅ JAR creado exitosamente en $DIST_DIR/inicio_sesion.jar"
	echo ""
	echo "📝 Iniciando la aplicación..."
	echo ""
	java -jar "$DIST_DIR/inicio_sesion.jar"
else
	echo "❌ Error al crear el JAR."
	exit 1
fi
