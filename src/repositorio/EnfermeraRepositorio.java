package repositorio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Enfermera;
import util.ConexionBD;

public class EnfermeraRepositorio {
    public boolean guardar(Enfermera enfermera) {
        String sql = "INSERT INTO enfermeras (id, nombre, apellido, telefono, turno) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, enfermera.getId());
            sentencia.setString(2, enfermera.getNombre());
            sentencia.setString(3, enfermera.getApellido());
            sentencia.setString(4, enfermera.getTelefono());
            sentencia.setString(5, enfermera.getTurno());
            return sentencia.executeUpdate() == 1;
        } catch (SQLException | IOException e) {
            ErrorRepositorio.mostrar("guardar enfermera", e);
            return false;
        }
    }

    public List<Enfermera> listar() {
        List<Enfermera> enfermeras = new ArrayList<>();
        String sql = "SELECT id, nombre, apellido, telefono, turno FROM enfermeras ORDER BY id";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {
            while (resultado.next()) enfermeras.add(convertir(resultado));
        } catch (SQLException | IOException e) {
            ErrorRepositorio.mostrar("listar enfermeras", e);
        }
        return enfermeras;
    }

    public Enfermera buscarPorId(int id) {
        String sql = "SELECT id, nombre, apellido, telefono, turno FROM enfermeras WHERE id = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, id);
            try (ResultSet resultado = sentencia.executeQuery()) {
                return resultado.next() ? convertir(resultado) : null;
            }
        } catch (SQLException | IOException e) {
            ErrorRepositorio.mostrar("buscar enfermera", e);
            return null;
        }
    }

    public boolean actualizar(Enfermera enfermera) {
        String sql = "UPDATE enfermeras SET nombre = ?, apellido = ?, telefono = ?, turno = ? WHERE id = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, enfermera.getNombre());
            sentencia.setString(2, enfermera.getApellido());
            sentencia.setString(3, enfermera.getTelefono());
            sentencia.setString(4, enfermera.getTurno());
            sentencia.setInt(5, enfermera.getId());
            return sentencia.executeUpdate() == 1;
        } catch (SQLException | IOException e) {
            ErrorRepositorio.mostrar("actualizar enfermera", e);
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM enfermeras WHERE id = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, id);
            return sentencia.executeUpdate() == 1;
        } catch (SQLException | IOException e) {
            ErrorRepositorio.mostrar("eliminar enfermera", e);
            return false;
        }
    }

    private Enfermera convertir(ResultSet resultado) throws SQLException {
        return new Enfermera(resultado.getInt("id"), resultado.getString("nombre"),
                resultado.getString("apellido"), resultado.getString("telefono"),
                resultado.getString("turno"));
    }
}
