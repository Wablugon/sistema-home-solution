package gui.util;

import javax.swing.*;
import java.awt.*;

/**
 * Métodos de utilidad para la generación de ventanas emergentes
 * (alertas, errores y confirmaciones), centralizando el uso de JOptionPane
 * en toda la capa de presentación.
 */
public final class DialogoUtils {

    private DialogoUtils() {
    }

    public static void mostrarInfo(Component padre, String mensaje) {
        JOptionPane.showMessageDialog(padre, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarError(Component padre, String mensaje) {
        JOptionPane.showMessageDialog(padre, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void mostrarAdvertencia(Component padre, String mensaje) {
        JOptionPane.showMessageDialog(padre, mensaje, "Atención", JOptionPane.WARNING_MESSAGE);
    }

    public static boolean confirmar(Component padre, String mensaje) {
        int resultado = JOptionPane.showConfirmDialog(padre, mensaje, "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return resultado == JOptionPane.YES_OPTION;
    }

    /** Pide un valor numérico simple al usuario mediante un input dialog. Devuelve null si cancela o el valor no es válido. */
    public static Double pedirNumero(Component padre, String mensaje) {
        String valor = JOptionPane.showInputDialog(padre, mensaje);
        if (valor == null || valor.isBlank()) {
            return null;
        }
        try {
            return Double.parseDouble(valor.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
