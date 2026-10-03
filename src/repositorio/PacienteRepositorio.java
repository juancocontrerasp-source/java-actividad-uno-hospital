package repositorio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Paciente;
import util.ConexionBD;

public class PacienteRepositorio {
    public boolean guardar(Paciente paciente) {
        String sql = "INSERT INTO pacientes (id, nombre, apellido, documento, telefono, edad) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, paciente.getId());
            sentencia.setString(2, paciente.getNombre());
            sentencia.setString(3, paciente.getApellido());
            sentencia.setString(4, paciente.getDocumento());
            sentencia.setString(5, paciente.getTelefono());
            sentencia.setInt(6, paciente.getEdad());
            return sentencia.executeUpdate() == 1;
        } catch (SQLException | IOException e) {
            ErrorRepositorio.mostrar("guardar paciente", e);
            return false;
        }
    }

    public List<Paciente> listar() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT id, nombre, apellido, documento, telefono, edad FROM pacientes ORDER BY id";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {
            while (resultado.next()) pacientes.add(convertir(resultado));
        } catch (SQLException | IOException e) {
            ErrorRepositorio.mostrar("listar pacientes", e);
        }
        return pacientes;
    }

    public Paciente buscarPorId(int id) {
        String sql = "SELECT id, nombre, apellido, documento, telefono, edad FROM pacientes WHERE id = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, id);
            try (ResultSet resultado = sentencia.executeQuery()) {
                return resultado.next() ? convertir(resultado) : null;
            }
        } catch (SQLException | IOException e) {
            ErrorRepositorio.mostrar("buscar paciente", e);
            return null;
        }
    }

    public boolean actualizar(Paciente paciente) {
        String sql = "UPDATE pacientes SET nombre = ?, apellido = ?, documento = ?, telefono = ?, edad = ? WHERE id = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, paciente.getNombre());
            sentencia.setString(2, paciente.getApellido());
            sentencia.setString(3, paciente.getDocumento());
            sentencia.setString(4, paciente.getTelefono());
            sentencia.setInt(5, paciente.getEdad());
            sentencia.setInt(6, paciente.getId());
            return sentencia.executeUpdate() == 1;
        } catch (SQLException | IOException e) {
            ErrorRepositorio.mostrar("actualizar paciente", e);
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM pacientes WHERE id = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, id);
            return sentencia.executeUpdate() == 1;
        } catch (SQLException | IOException e) {
            ErrorRepositorio.mostrar("eliminar paciente", e);
            return false;
        }
    }

    private Paciente convertir(ResultSet resultado) throws SQLException {
        return new Paciente(resultado.getInt("id"), resultado.getString("nombre"),
                resultado.getString("apellido"), resultado.getString("documento"),
                resultado.getString("telefono"), resultado.getInt("edad"));
    }
}
