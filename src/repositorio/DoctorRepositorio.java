package repositorio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Doctor;
import util.ConexionBD;

public class DoctorRepositorio {
    public boolean guardar(Doctor doctor) {
        String sql = "INSERT INTO doctores (id, nombre, apellido, especialidad, telefono) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, doctor.getId());
            sentencia.setString(2, doctor.getNombre());
            sentencia.setString(3, doctor.getApellido());
            sentencia.setString(4, doctor.getEspecialidad());
            sentencia.setString(5, doctor.getTelefono());
            return sentencia.executeUpdate() == 1;
        } catch (SQLException | IOException e) {
            ErrorRepositorio.mostrar("guardar doctor", e);
            return false;
        }
    }

    public List<Doctor> listar() {
        List<Doctor> doctores = new ArrayList<>();
        String sql = "SELECT id, nombre, apellido, especialidad, telefono FROM doctores ORDER BY id";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {
            while (resultado.next()) doctores.add(convertir(resultado));
        } catch (SQLException | IOException e) {
            ErrorRepositorio.mostrar("listar doctores", e);
        }
        return doctores;
    }

    public Doctor buscarPorId(int id) {
        String sql = "SELECT id, nombre, apellido, especialidad, telefono FROM doctores WHERE id = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, id);
            try (ResultSet resultado = sentencia.executeQuery()) {
                return resultado.next() ? convertir(resultado) : null;
            }
        } catch (SQLException | IOException e) {
            ErrorRepositorio.mostrar("buscar doctor", e);
            return null;
        }
    }

    public boolean actualizar(Doctor doctor) {
        String sql = "UPDATE doctores SET nombre = ?, apellido = ?, especialidad = ?, telefono = ? WHERE id = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, doctor.getNombre());
            sentencia.setString(2, doctor.getApellido());
            sentencia.setString(3, doctor.getEspecialidad());
            sentencia.setString(4, doctor.getTelefono());
            sentencia.setInt(5, doctor.getId());
            return sentencia.executeUpdate() == 1;
        } catch (SQLException | IOException e) {
            ErrorRepositorio.mostrar("actualizar doctor", e);
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM doctores WHERE id = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, id);
            return sentencia.executeUpdate() == 1;
        } catch (SQLException | IOException e) {
            ErrorRepositorio.mostrar("eliminar doctor", e);
            return false;
        }
    }

    private Doctor convertir(ResultSet resultado) throws SQLException {
        return new Doctor(resultado.getInt("id"), resultado.getString("nombre"),
                resultado.getString("apellido"), resultado.getString("especialidad"),
                resultado.getString("telefono"));
    }
}
