package vista;

import modelo.Cita;
import modelo.Doctor;
import modelo.Enfermera;
import modelo.Paciente;
import repositorio.CitaRepositorio;
import repositorio.DoctorRepositorio;
import repositorio.EnfermeraRepositorio;
import repositorio.PacienteRepositorio;
import util.EntradaConsola;

public class MenuPrincipal {
    private final PacienteRepositorio pacientes = new PacienteRepositorio();
    private final DoctorRepositorio doctores = new DoctorRepositorio();
    private final EnfermeraRepositorio enfermeras = new EnfermeraRepositorio();
    private final CitaRepositorio citas = new CitaRepositorio();

    public void iniciar() {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = EntradaConsola.leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> menuPacientes();
                case 2 -> menuDoctores();
                case 3 -> menuEnfermeras();
                case 4 -> menuCitas();
                case 0 -> System.out.println("Programa finalizado.");
                default -> System.out.println("Opción inexistente.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n=====================================");
        System.out.println("       HOSPITAL SAN GABRIEL");
        System.out.println("=====================================");
        System.out.println("1. Gestionar pacientes");
        System.out.println("2. Gestionar doctores");
        System.out.println("3. Gestionar enfermeras");
        System.out.println("4. Gestionar citas");
        System.out.println("0. Salir");
    }

    private void menuPacientes() {
        int opcion;
        do {
            System.out.println("\n----- PACIENTES -----");
            System.out.println("1. Registrar paciente\n2. Listar pacientes\n3. Buscar paciente\n4. Actualizar paciente\n5. Eliminar paciente\n0. Volver");
            opcion = EntradaConsola.leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> registrarPaciente();
                case 2 -> listar(pacientes.listar());
                case 3 -> buscarPaciente();
                case 4 -> actualizarPaciente();
                case 5 -> eliminarPaciente();
                case 0 -> { }
                default -> System.out.println("Opción inexistente.");
            }
        } while (opcion != 0);
    }

    private void registrarPaciente() {
        int id = EntradaConsola.leerId("ID: ");
        if (pacientes.buscarPorId(id) != null) { System.out.println("Ya existe un paciente con ese ID."); return; }
        Paciente paciente = new Paciente(id, EntradaConsola.leerTexto("Nombre: "), EntradaConsola.leerTexto("Apellido: "),
                EntradaConsola.leerTexto("Documento: "), EntradaConsola.leerTexto("Teléfono: "), EntradaConsola.leerEntero("Edad: "));
        pacientes.guardar(paciente);
        System.out.println("Paciente registrado correctamente.");
    }

    private void buscarPaciente() {
        Paciente paciente = pacientes.buscarPorId(EntradaConsola.leerId("ID del paciente: "));
        System.out.println(paciente == null ? "No existe un paciente con ese ID." : paciente);
    }

    private void actualizarPaciente() {
        Paciente paciente = pacientes.buscarPorId(EntradaConsola.leerId("ID del paciente: "));
        if (paciente == null) { System.out.println("No existe un paciente con ese ID."); return; }
        paciente.setNombre(EntradaConsola.leerTexto("Nombre: ")); paciente.setApellido(EntradaConsola.leerTexto("Apellido: "));
        paciente.setDocumento(EntradaConsola.leerTexto("Documento: ")); paciente.setTelefono(EntradaConsola.leerTexto("Teléfono: "));
        paciente.setEdad(EntradaConsola.leerEntero("Edad: ")); pacientes.actualizar(paciente);
        System.out.println("Paciente actualizado correctamente.");
    }

    private void eliminarPaciente() {
        if (pacientes.eliminar(EntradaConsola.leerId("ID del paciente: "))) System.out.println("Paciente eliminado correctamente.");
        else System.out.println("No existe un paciente con ese ID.");
    }

    private void menuDoctores() {
        int opcion;
        do {
            System.out.println("\n----- DOCTORES -----");
            System.out.println("1. Registrar doctor\n2. Listar doctores\n3. Buscar doctor\n4. Actualizar doctor\n5. Eliminar doctor\n0. Volver");
            opcion = EntradaConsola.leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> registrarDoctor();
                case 2 -> listar(doctores.listar());
                case 3 -> buscarDoctor();
                case 4 -> actualizarDoctor();
                case 5 -> eliminarDoctor();
                case 0 -> { }
                default -> System.out.println("Opción inexistente.");
            }
        } while (opcion != 0);
    }

    private void registrarDoctor() {
        int id = EntradaConsola.leerId("ID: ");
        if (doctores.buscarPorId(id) != null) { System.out.println("Ya existe un doctor con ese ID."); return; }
        Doctor doctor = new Doctor(id, EntradaConsola.leerTexto("Nombre: "), EntradaConsola.leerTexto("Apellido: "),
                EntradaConsola.leerTexto("Especialidad: "), EntradaConsola.leerTexto("Teléfono: "));
        doctores.guardar(doctor); System.out.println("Doctor registrado correctamente.");
    }

    private void buscarDoctor() {
        Doctor doctor = doctores.buscarPorId(EntradaConsola.leerId("ID del doctor: "));
        System.out.println(doctor == null ? "No existe un doctor con ese ID." : doctor);
    }

    private void actualizarDoctor() {
        Doctor doctor = doctores.buscarPorId(EntradaConsola.leerId("ID del doctor: "));
        if (doctor == null) { System.out.println("No existe un doctor con ese ID."); return; }
        doctor.setNombre(EntradaConsola.leerTexto("Nombre: ")); doctor.setApellido(EntradaConsola.leerTexto("Apellido: "));
        doctor.setEspecialidad(EntradaConsola.leerTexto("Especialidad: ")); doctor.setTelefono(EntradaConsola.leerTexto("Teléfono: "));
        doctores.actualizar(doctor); System.out.println("Doctor actualizado correctamente.");
    }

    private void eliminarDoctor() {
        if (doctores.eliminar(EntradaConsola.leerId("ID del doctor: "))) System.out.println("Doctor eliminado correctamente.");
        else System.out.println("No existe un doctor con ese ID.");
    }

    private void menuEnfermeras() {
        int opcion;
        do {
            System.out.println("\n----- ENFERMERAS -----");
            System.out.println("1. Registrar enfermera\n2. Listar enfermeras\n3. Buscar enfermera\n4. Actualizar enfermera\n5. Eliminar enfermera\n0. Volver");
            opcion = EntradaConsola.leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> registrarEnfermera();
                case 2 -> listar(enfermeras.listar());
                case 3 -> buscarEnfermera();
                case 4 -> actualizarEnfermera();
                case 5 -> eliminarEnfermera();
                case 0 -> { }
                default -> System.out.println("Opción inexistente.");
            }
        } while (opcion != 0);
    }

    private void registrarEnfermera() {
        int id = EntradaConsola.leerId("ID: ");
        if (enfermeras.buscarPorId(id) != null) { System.out.println("Ya existe una enfermera con ese ID."); return; }
        Enfermera enfermera = new Enfermera(id, EntradaConsola.leerTexto("Nombre: "), EntradaConsola.leerTexto("Apellido: "),
                EntradaConsola.leerTexto("Teléfono: "), EntradaConsola.leerTexto("Turno: "));
        enfermeras.guardar(enfermera); System.out.println("Enfermera registrada correctamente.");
    }

    private void buscarEnfermera() {
        Enfermera enfermera = enfermeras.buscarPorId(EntradaConsola.leerId("ID de la enfermera: "));
        System.out.println(enfermera == null ? "No existe una enfermera con ese ID." : enfermera);
    }

    private void actualizarEnfermera() {
        Enfermera enfermera = enfermeras.buscarPorId(EntradaConsola.leerId("ID de la enfermera: "));
        if (enfermera == null) { System.out.println("No existe una enfermera con ese ID."); return; }
        enfermera.setNombre(EntradaConsola.leerTexto("Nombre: ")); enfermera.setApellido(EntradaConsola.leerTexto("Apellido: "));
        enfermera.setTelefono(EntradaConsola.leerTexto("Teléfono: ")); enfermera.setTurno(EntradaConsola.leerTexto("Turno: "));
        enfermeras.actualizar(enfermera); System.out.println("Enfermera actualizada correctamente.");
    }

    private void eliminarEnfermera() {
        if (enfermeras.eliminar(EntradaConsola.leerId("ID de la enfermera: "))) System.out.println("Enfermera eliminada correctamente.");
        else System.out.println("No existe una enfermera con ese ID.");
    }

    private void menuCitas() {
        int opcion;
        do {
            System.out.println("\n----- CITAS -----");
            System.out.println("1. Registrar cita\n2. Listar citas\n3. Buscar cita\n4. Actualizar cita\n5. Eliminar cita\n0. Volver");
            opcion = EntradaConsola.leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> registrarCita();
                case 2 -> listar(citas.listar());
                case 3 -> buscarCita();
                case 4 -> actualizarCita();
                case 5 -> eliminarCita();
                case 0 -> { }
                default -> System.out.println("Opción inexistente.");
            }
        } while (opcion != 0);
    }

    private void registrarCita() {
        int pacienteId = EntradaConsola.leerId("ID del paciente: ");
        Paciente paciente = pacientes.buscarPorId(pacienteId);
        if (paciente == null) { System.out.println("No existe un paciente con ese ID."); return; }
        int doctorId = EntradaConsola.leerId("ID del doctor: ");
        Doctor doctor = doctores.buscarPorId(doctorId);
        if (doctor == null) { System.out.println("No existe un doctor con ese ID."); return; }
        int id = EntradaConsola.leerId("ID de la cita: ");
        if (citas.buscarPorId(id) != null) { System.out.println("Ya existe una cita con ese ID."); return; }
        Cita cita = new Cita(id, paciente, doctor, EntradaConsola.leerFecha("Fecha (dd/MM/yyyy): "),
                EntradaConsola.leerHora("Hora (HH:mm): "), EntradaConsola.leerTexto("Motivo: "));
        citas.guardar(cita); System.out.println("Cita registrada correctamente.");
    }

    private void buscarCita() {
        Cita cita = citas.buscarPorId(EntradaConsola.leerId("ID de la cita: "));
        System.out.println(cita == null ? "No existe una cita con ese ID." : cita);
    }

    private void actualizarCita() {
        Cita cita = citas.buscarPorId(EntradaConsola.leerId("ID de la cita: "));
        if (cita == null) { System.out.println("No existe una cita con ese ID."); return; }
        cita.setFecha(EntradaConsola.leerFecha("Fecha (dd/MM/yyyy): ")); cita.setHora(EntradaConsola.leerHora("Hora (HH:mm): "));
        cita.setMotivo(EntradaConsola.leerTexto("Motivo: ")); citas.actualizar(cita);
        System.out.println("Cita actualizada correctamente.");
    }

    private void eliminarCita() {
        if (citas.eliminar(EntradaConsola.leerId("ID de la cita: "))) System.out.println("Cita eliminada correctamente.");
        else System.out.println("No existe una cita con ese ID.");
    }

    private void listar(Iterable<?> elementos) {
        boolean hayElementos = false;
        for (Object elemento : elementos) { System.out.println(elemento); hayElementos = true; }
        if (!hayElementos) System.out.println("No hay registros.");
    }
}
