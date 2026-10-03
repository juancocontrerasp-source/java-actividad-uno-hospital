package repositorio;

import java.io.IOException;
import java.sql.SQLException;

public final class ErrorRepositorio {
    private ErrorRepositorio() { }

    public static void mostrar(String operacion, Exception error) {
        String detalle = error.getMessage();
        if (error instanceof SQLException || error instanceof IOException) {
            System.err.println("No se pudo " + operacion + ". Revise MySQL y config/database.properties. " + detalle);
        } else {
            System.err.println("No se pudo " + operacion + ". " + detalle);
        }
    }
}