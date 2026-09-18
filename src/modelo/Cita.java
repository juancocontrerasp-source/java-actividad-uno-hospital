package modelo;

// Clase que representa una cita entre paciente y médico
public class Cita {
    private int id;
    private Paciente paciente;
    private Medico medico;
    private String fecha;
    private String hora;
    private String motivo;
    private String estado;

    // Constructor recibe id, paciente, medico, fecha y hora
    public Cita(int id, Paciente paciente, Medico medico, String fecha, String hora) {
        this(id, paciente, medico, fecha, hora, "");
    }

    public Cita(int id, Paciente paciente, Medico medico, String fecha, String hora, String motivo) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.estado = "PROGRAMADA";
    }

    // Getters
    public int getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    // Cambia el estado a CANCELADA
    public void cancelarCita() {
        this.estado = "CANCELADA";
    }

    // Cambia el estado a ATENDIDA
    public void atenderCita() {
        this.estado = "ATENDIDA";
    }

    @Override
    public String toString() {
        return "Cita {" +
                "id=" + id +
                ", paciente='" + paciente.getNombre() + '\'' +
                ", medico='" + medico.getNombre() + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", motivo='" + motivo + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
