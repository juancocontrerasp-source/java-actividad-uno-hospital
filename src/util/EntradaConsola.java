package util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class EntradaConsola {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

    private EntradaConsola() { }

    public static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = SCANNER.nextLine().trim();
            if (!valor.isEmpty()) return valor;
            System.out.println("El campo no puede estar vacío.");
        }
    }

    public static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try { return Integer.parseInt(SCANNER.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Ingrese un número entero válido."); }
        }
    }

    public static int leerId(String mensaje) {
        while (true) {
            int id = leerEntero(mensaje);
            if (id >= 0) return id;
            System.out.println("El ID no puede ser negativo.");
        }
    }

    public static String leerFecha(String mensaje) {
        while (true) {
            String fecha = leerTexto(mensaje);
            try { LocalDate.parse(fecha, FORMATO_FECHA); return fecha; }
            catch (DateTimeParseException e) { System.out.println("Use el formato dd/MM/yyyy."); }
        }
    }

    public static String leerHora(String mensaje) {
        while (true) {
            String hora = leerTexto(mensaje);
            try { LocalTime.parse(hora, FORMATO_HORA); return hora; }
            catch (DateTimeParseException e) { System.out.println("Use el formato HH:mm."); }
        }
    }
}
