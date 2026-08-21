package modelo;

// Clase que representa a un médico
public class Medico {
    private int id;
    private String nombre;
    private String documento;
    private String especialidad;

    // Constructor con todos los atributos
    public Medico(int id, String nombre, String documento, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.especialidad = especialidad;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    // Setter para la especialidad
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Medico {" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}
