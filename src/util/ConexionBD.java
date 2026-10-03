package util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/** Abre conexiones usando la configuración externa de config/database.properties. */
public class ConexionBD {
    private static final String ARCHIVO_CONFIGURACION = "config/database.properties";

    private ConexionBD() { }

    public static Connection obtenerConexion() throws SQLException, IOException {
        Properties propiedades = new Properties();
        Path ruta = Paths.get(ARCHIVO_CONFIGURACION);
        try (InputStream entrada = Files.newInputStream(ruta)) {
            propiedades.load(entrada);
        } catch (IOException e) {
            throw new SQLException("No se pudo leer " + ARCHIVO_CONFIGURACION + ".", e);
        }

        String url = propiedades.getProperty("db.url", "").trim();
        String usuario = propiedades.getProperty("db.user", "").trim();
        String contrasena = propiedades.getProperty("db.password", "").trim();
        if (!url.startsWith("jdbc:mysql:") || usuario.isEmpty()
                || contrasena.isEmpty() || contrasena.startsWith("REEMPLAZAR")) {
            throw new SQLException("Configure db.url, db.user y db.password en "
                    + ARCHIVO_CONFIGURACION + ".");
        }
        return DriverManager.getConnection(url, usuario, contrasena);
    }
}
