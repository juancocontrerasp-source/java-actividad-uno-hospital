package repositorio;

import java.util.ArrayList;
import java.util.List;
import modelo.Paciente;

public class PacienteRepositorio {
    private final List<Paciente> pacientes = new ArrayList<>();

    public boolean guardar(Paciente paciente) {
        if (buscarPorId(paciente.getId()) != null) return false;
        pacientes.add(paciente);
        return true;
    }

    public List<Paciente> listar() { return new ArrayList<>(pacientes); }

    public Paciente buscarPorId(int id) {
        for (Paciente paciente : pacientes) if (paciente.getId() == id) return paciente;
        return null;
    }

    public boolean actualizar(Paciente paciente) {
        for (int indice = 0; indice < pacientes.size(); indice++) {
            if (pacientes.get(indice).getId() == paciente.getId()) {
                pacientes.set(indice, paciente);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        Paciente paciente = buscarPorId(id);
        return paciente != null && pacientes.remove(paciente);
    }
}
