package modelo;

import java.util.ArrayList;

// Clase que representa el hospital y almacena listas de pacientes, médicos y citas
public class Hospital {
    private String nombre;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Medico> medicos;
    private ArrayList<Cita> citas;

    public Hospital(String nombre) {
        this.nombre = nombre;
        this.pacientes = new ArrayList<>();
        this.medicos = new ArrayList<>();
        this.citas = new ArrayList<>();
    }

    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public void agregarMedico(Medico medico) {
        medicos.add(medico);
    }

    public void agregarCita(Cita cita) {
        citas.add(cita);
    }

    // Muestra todos los pacientes por consola
    public void mostrarPacientes() {
        System.out.println("-- Pacientes en " + nombre + " --");
        for (Paciente p : pacientes) {
            System.out.println(p);
        }
    }

    // Muestra todos los médicos por consola
    public void mostrarMedicos() {
        System.out.println("-- Medicos en " + nombre + " --");
        for (Medico m : medicos) {
            System.out.println(m);
        }
    }

    // Muestra todas las citas por consola
    public void mostrarCitas() {
        System.out.println("-- Citas en " + nombre + " --");
        for (Cita c : citas) {
            System.out.println(c);
        }
    }
}
