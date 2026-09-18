CREATE DATABASE IF NOT EXISTS hospital_san_gabriel;
USE hospital_san_gabriel;

CREATE TABLE IF NOT EXISTS pacientes (
    id INT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    apellido VARCHAR(80) NOT NULL,
    documento VARCHAR(30) NOT NULL UNIQUE,
    telefono VARCHAR(30) NOT NULL,
    edad INT NOT NULL
);

CREATE TABLE IF NOT EXISTS doctores (
    id INT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    apellido VARCHAR(80) NOT NULL,
    especialidad VARCHAR(100) NOT NULL,
    telefono VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS enfermeras (
    id INT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    apellido VARCHAR(80) NOT NULL,
    telefono VARCHAR(30) NOT NULL,
    turno VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS citas (
    id INT PRIMARY KEY,
    paciente_id INT NOT NULL,
    doctor_id INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    motivo VARCHAR(200) NOT NULL,
    estado VARCHAR(30) NOT NULL DEFAULT 'PROGRAMADA',
    CONSTRAINT fk_cita_paciente FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
    CONSTRAINT fk_cita_doctor FOREIGN KEY (doctor_id) REFERENCES doctores(id)
);
