package repositorio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import modelo.Cita;
import modelo.Doctor;
import modelo.Paciente;
import util.ConexionBD;

public class CitaRepositorio {
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
    private static final String CONSULTA_BASE = "SELECT c.id, c.fecha, c.hora, c.motivo, c.estado, "
            + "p.id AS paciente_id, p.nombre AS paciente_nombre, p.apellido AS paciente_apellido, "
            + "p.documento AS paciente_documento, p.telefono AS paciente_telefono, p.edad AS paciente_edad, "
            + "d.id AS doctor_id, d.nombre AS doctor_nombre, d.apellido AS doctor_apellido, "
            + "d.especialidad AS doctor_especialidad, d.telefono AS doctor_telefono "
            + "FROM citas c JOIN pacientes p ON c.paciente_id = p.id "
            + "JOIN doctores d ON c.doctor_id = d.id ";

    public boolean guardar(Cita cita) {
        String sql = "INSERT INTO citas (id, paciente_id, doctor_id, fecha, hora, motivo, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, cita.getId());
            sentencia.setInt(2, cita.getPaciente().getId());
            sentencia.setInt(3, cita.getMedico().getId());
            sentencia.setDate(4, convertirFecha(cita.getFecha()));
            sentencia.setTime(5, convertirHora(cita.getHora()));
            sentencia.setString(6, cita.getMotivo());
            sentencia.setString(7, cita.getEstado());
            return sentencia.executeUpdate() == 1;
        } catch (SQLException | IOException | java.time.format.DateTimeParseException e) {
            ErrorRepositorio.mostrar("guardar cita", e);
            return false;
        }
    }

    public List<Cita> listar() {
        List<Cita> citas = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(CONSULTA_BASE + "ORDER BY c.id");
             ResultSet resultado = sentencia.executeQuery()) {
            while (resultado.next()) citas.add(convertir(resultado));
        } catch (SQLException | IOException e) {
            ErrorRepositorio.mostrar("listar citas", e);
        }
        return citas;
    }

    public Cita buscarPorId(int id) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(CONSULTA_BASE + "WHERE c.id = ?")) {
            sentencia.setInt(1, id);
            try (ResultSet resultado = sentencia.executeQuery()) {
                return resultado.next() ? convertir(resultado) : null;
            }
        } catch (SQLException | IOException e) {
            ErrorRepositorio.mostrar("buscar cita", e);
            return null;
        }
    }

    public boolean actualizar(Cita cita) {
        String sql = "UPDATE citas SET paciente_id = ?, doctor_id = ?, fecha = ?, hora = ?, motivo = ?, estado = ? WHERE id = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, cita.getPaciente().getId());
            sentencia.setInt(2, cita.getMedico().getId());
            sentencia.setDate(3, convertirFecha(cita.getFecha()));
            sentencia.setTime(4, convertirHora(cita.getHora()));
            sentencia.setString(5, cita.getMotivo());
            sentencia.setString(6, cita.getEstado());
            sentencia.setInt(7, cita.getId());
            return sentencia.executeUpdate() == 1;
        } catch (SQLException | IOException | java.time.format.DateTimeParseException e) {
            ErrorRepositorio.mostrar("actualizar cita", e);
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM citas WHERE id = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, id);
            return sentencia.executeUpdate() == 1;
        } catch (SQLException | IOException e) {
            ErrorRepositorio.mostrar("eliminar cita", e);
            return false;
        }
    }

    private Cita convertir(ResultSet resultado) throws SQLException {
        Paciente paciente = new Paciente(resultado.getInt("paciente_id"),
                resultado.getString("paciente_nombre"), resultado.getString("paciente_apellido"),
                resultado.getString("paciente_documento"), resultado.getString("paciente_telefono"),
                resultado.getInt("paciente_edad"));
        Doctor doctor = new Doctor(resultado.getInt("doctor_id"), resultado.getString("doctor_nombre"),
                resultado.getString("doctor_apellido"), resultado.getString("doctor_especialidad"),
                resultado.getString("doctor_telefono"));
        Cita cita = new Cita(resultado.getInt("id"), paciente, doctor,
                resultado.getDate("fecha").toLocalDate().format(FORMATO_FECHA),
                resultado.getTime("hora").toLocalTime().format(FORMATO_HORA), resultado.getString("motivo"));
        cita.setEstado(resultado.getString("estado"));
        return cita;
    }

    private Date convertirFecha(String fecha) {
        return Date.valueOf(LocalDate.parse(fecha, FORMATO_FECHA));
    }

    private Time convertirHora(String hora) {
        return Time.valueOf(LocalTime.parse(hora, FORMATO_HORA));
    }
}
