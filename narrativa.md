# Narrativa del proyecto

## Problema

Un hospital necesita organizar la información básica de sus pacientes, doctores, enfermeras y citas. Cuando estos datos se manejan sin orden, resulta difícil encontrar información, actualizarla o comprobar las relaciones entre una cita y sus participantes.

## Solución

Hospital San Gabriel es una aplicación académica de consola desarrollada en Java. Permite registrar, consultar, actualizar y eliminar información de las entidades principales del hospital.

## Entidades principales

- **Paciente:** conserva sus datos personales y de contacto.
- **Doctor:** conserva sus datos y especialidad.
- **Enfermera:** conserva sus datos de contacto y turno.
- **Cita:** relaciona un paciente con un doctor en una fecha y hora, indicando el motivo.

## Funcionamiento

Al iniciar, el usuario observa un menú principal. Cada entidad tiene un submenú con las operaciones CRUD. La clase `EntradaConsola` centraliza la lectura y valida números, fechas, horas y campos obligatorios. Los repositorios almacenan los registros temporalmente en memoria durante la ejecución.

Antes de registrar una cita, el programa comprueba que existan el paciente y el doctor seleccionados.

## Base de datos

La clase `ConexionBD` lee la URL, el usuario y la contraseña desde `config/database.properties`. El script `database/hospital.sql` contiene las tablas y claves foráneas. La ejecución actual usa repositorios en memoria para poder probar el programa sin configurar todavía un motor ni un controlador JDBC.

## Aprendizajes

Durante la actividad se practicó la creación de clases y objetos, encapsulamiento mediante getters y setters, enumeraciones, listas, separación por responsabilidades, validación de entradas y relaciones entre entidades. También se preparó la estructura necesaria para conectar el programa con una base de datos.
