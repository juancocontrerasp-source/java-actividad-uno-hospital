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
        }
        return DriverManager.getConnection(propiedades.getProperty("db.url"),
                propiedades.getProperty("db.user"), propiedades.getProperty("db.password"));
    }
}
