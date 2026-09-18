package modelo;

/** Representa a una enfermera del hospital. */
public class Enfermera {
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String turno;

    public Enfermera(int id, String nombre, String apellido, String telefono, String turno) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.turno = turno;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }
    public String getTurno() { return turno; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setTurno(String turno) { this.turno = turno; }

    @Override
    public String toString() {
        return "Enfermera{" + "id=" + id + ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' + ", telefono='" + telefono + '\'' +
                ", turno='" + turno + '\'' + '}';
    }
}
