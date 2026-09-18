package repositorio;

import java.util.ArrayList;
import java.util.List;
import modelo.Cita;

public class CitaRepositorio {
    private final List<Cita> citas = new ArrayList<>();

    public boolean guardar(Cita cita) {
        if (buscarPorId(cita.getId()) != null) return false;
        citas.add(cita);
        return true;
    }

    public List<Cita> listar() { return new ArrayList<>(citas); }

    public Cita buscarPorId(int id) {
        for (Cita cita : citas) if (cita.getId() == id) return cita;
        return null;
    }

    public boolean actualizar(Cita cita) {
        for (int indice = 0; indice < citas.size(); indice++) {
            if (citas.get(indice).getId() == cita.getId()) {
                citas.set(indice, cita);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        Cita cita = buscarPorId(id);
        return cita != null && citas.remove(cita);
    }
}
