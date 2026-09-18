package repositorio;

import java.util.ArrayList;
import java.util.List;
import modelo.Doctor;

public class DoctorRepositorio {
    private final List<Doctor> doctores = new ArrayList<>();

    public boolean guardar(Doctor doctor) {
        if (buscarPorId(doctor.getId()) != null) return false;
        doctores.add(doctor);
        return true;
    }

    public List<Doctor> listar() { return new ArrayList<>(doctores); }

    public Doctor buscarPorId(int id) {
        for (Doctor doctor : doctores) if (doctor.getId() == id) return doctor;
        return null;
    }

    public boolean actualizar(Doctor doctor) {
        for (int indice = 0; indice < doctores.size(); indice++) {
            if (doctores.get(indice).getId() == doctor.getId()) {
                doctores.set(indice, doctor);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        Doctor doctor = buscarPorId(id);
        return doctor != null && doctores.remove(doctor);
    }
}
