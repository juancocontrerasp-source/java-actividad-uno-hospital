# Hospital San Gabriel

## Descripción

Aplicación académica desarrollada en Java para gestionar información básica de un hospital mediante una interfaz de consola.

## Objetivo

Practicar clases, objetos, encapsulamiento, listas, validaciones y la organización de un proyecto por responsabilidades.

## Funcionalidades

- Registrar, listar, buscar, actualizar y eliminar pacientes.
- Registrar, listar, buscar, actualizar y eliminar doctores.
- Registrar, listar, buscar, actualizar y eliminar enfermeras.
- Registrar, listar, buscar, actualizar y eliminar citas.
- Validar campos vacíos, IDs, números, fechas y horas.
- Comprobar que el paciente y el doctor existan antes de crear una cita.

## Tecnologías utilizadas

- Java 17 o superior.
- JDBC y MySQL Connector/J para guardar y consultar datos en MySQL.
- MySQL compatible con el script SQL incluido.

## Estructura del proyecto

```text
src/
	modelo/       Entidades del hospital.
	repositorio/  Operaciones CRUD en memoria.
	util/         Lectura de consola y conexión JDBC.
	vista/        Menú principal y submenús.
	principal/    Punto de entrada de la aplicación.
config/         Configuración de base de datos.
database/       Script de creación de tablas.
lib/            Controlador JDBC descargado localmente.
```

## Requisitos

- JDK instalado y disponible en el PATH.
- MySQL Server instalado y en ejecución.
- MySQL Connector/J, descargado con el comando indicado abajo.

## Configuración de base de datos

En `config/database.properties`, mantenga la URL y el usuario `root` y reemplace `REEMPLAZAR_CONTRASENA` por la contraseña creada para MySQL. Este archivo es local y está excluido de Git. Ejecute `database/hospital.sql` una vez para crear la base y sus tablas. Los repositorios Java se conectan a esa base mediante JDBC.

## Cómo ejecutar el proyecto

Desde la carpeta raíz:

```powershell
New-Item -ItemType Directory -Force out
New-Item -ItemType Directory -Force lib
Invoke-WebRequest -Uri 'https://repo.maven.apache.org/maven2/com/mysql/mysql-connector-j/9.4.0/mysql-connector-j-9.4.0.jar' -OutFile 'lib/mysql-connector-j-9.4.0.jar'
javac -encoding UTF-8 -cp 'lib/mysql-connector-j-9.4.0.jar' -d out (Get-ChildItem src -Recurse -Filter *.java).FullName
java -cp 'out;lib/mysql-connector-j-9.4.0.jar' principal.Main
```

Para crear las tablas desde **CMD** si no tiene Workbench, ejecute desde la carpeta del proyecto:

```cmd
"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -u root -p < database\hospital.sql
```

El comando solicitará la contraseña de MySQL. No la escriba en un comando ni la comparta.

## Opciones del menú

El menú principal permite entrar a pacientes, doctores, enfermeras y citas. Cada módulo presenta las opciones de registrar, listar, buscar, actualizar, eliminar y volver.

## Pruebas realizadas

Se verificó la compilación de todas las clases con Connector/J. El CRUD usa consultas preparadas y almacena los registros en MySQL; para probarlo, importe primero el script y configure la contraseña local.

## Autor

Estudiante de la actividad académica “Avance de proyecto de la app Hospital San Gabriel”.