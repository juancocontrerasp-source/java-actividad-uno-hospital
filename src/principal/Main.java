package principal;

import modelo.*;

// Clase principal para probar el modelo del hospital
public class Main {
    public static void main(String[] args) {
        Hospital hospital = new Hospital("Hospital San Gabriel");

        // Crear paciente
        Paciente paciente = new Paciente(1, "Carlos Perez", "10203040", "3001234567", 35);

        // Crear médico
        Medico medico = new Medico(1, "Laura Gomez", "52345678", "Medicina General");

        // Crear cita
        Cita cita = new Cita(1, paciente, medico, "21/08/2026", "10:00");

        // Agregar al hospital
        hospital.agregarPaciente(paciente);
        hospital.agregarMedico(medico);
        hospital.agregarCita(cita);

        // Mostrar datos
        hospital.mostrarPacientes();
        hospital.mostrarMedicos();
        hospital.mostrarCitas();
    }
}
