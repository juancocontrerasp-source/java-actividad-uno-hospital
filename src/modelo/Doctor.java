package modelo;

/** Representa a un doctor del hospital. Mantiene compatibilidad con Medico. */
public class Doctor extends Medico {
    public Doctor(int id, String nombre, String apellido, String especialidad, String telefono) {
        super(id, nombre, apellido, "", especialidad, telefono);
    }

    public Doctor(int id, String nombre, String apellido, String documento, String especialidad, String telefono) {
        super(id, nombre, apellido, documento, especialidad, telefono);
    }
}
