package repositorio;

import java.util.ArrayList;
import java.util.List;
import modelo.Enfermera;

public class EnfermeraRepositorio {
    private final List<Enfermera> enfermeras = new ArrayList<>();

    public boolean guardar(Enfermera enfermera) {
        if (buscarPorId(enfermera.getId()) != null) return false;
        enfermeras.add(enfermera);
        return true;
    }

    public List<Enfermera> listar() { return new ArrayList<>(enfermeras); }

    public Enfermera buscarPorId(int id) {
        for (Enfermera enfermera : enfermeras) if (enfermera.getId() == id) return enfermera;
        return null;
    }

    public boolean actualizar(Enfermera enfermera) {
        for (int indice = 0; indice < enfermeras.size(); indice++) {
            if (enfermeras.get(indice).getId() == enfermera.getId()) {
                enfermeras.set(indice, enfermera);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        Enfermera enfermera = buscarPorId(id);
        return enfermera != null && enfermeras.remove(enfermera);
    }
}
