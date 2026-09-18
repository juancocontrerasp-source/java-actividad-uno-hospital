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
- `ArrayList` para el almacenamiento temporal durante la ejecución.
- JDBC preparado para una futura conexión a base de datos.
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
```

## Requisitos

- JDK instalado y disponible en el PATH.
- No se necesita base de datos para probar el menú actual.
- Para JDBC se requiere un motor y su controlador correspondiente.

## Configuración de base de datos

Revise `config/database.properties` y reemplace los valores de ejemplo. Ejecute `database/hospital.sql` en el motor elegido. La conexión está preparada en `util/ConexionBD.java`, pero los repositorios actuales trabajan en memoria para mantener el proyecto sencillo y ejecutable.

## Cómo ejecutar el proyecto

Desde la carpeta raíz:

```powershell
New-Item -ItemType Directory -Force out
javac -encoding UTF-8 -d out (Get-ChildItem src -Recurse -Filter *.java).FullName
java -cp out principal.Main
```

En Linux o macOS puede usar `javac -d out $(find src -name "*.java")`.

## Opciones del menú

El menú principal permite entrar a pacientes, doctores, enfermeras y citas. Cada módulo presenta las opciones de registrar, listar, buscar, actualizar, eliminar y volver.

## Pruebas realizadas

Se verificó la compilación de todas las clases. El programa permite probar manualmente el CRUD de las cuatro entidades, IDs inexistentes, opciones inválidas, números incorrectos, campos vacíos y formatos incorrectos de fecha y hora.

## Autor

Estudiante de la actividad académica “Avance de proyecto de la app Hospital San Gabriel”.