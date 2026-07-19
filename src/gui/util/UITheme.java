package gui.util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public final class UITheme {

    public static final Color COLOR_FONDO = new Color(245, 247, 250);
    public static final Color COLOR_PRIMARIO = new Color(41, 98, 255);
    public static final Color COLOR_PRIMARIO_HOVER = new Color(30, 78, 210);
    public static final Color COLOR_SECUNDARIO = new Color(90, 99, 115);
    public static final Color COLOR_TEXTO = new Color(33, 37, 41);
    public static final Color COLOR_BORDE = new Color(210, 214, 220);
    public static final Color COLOR_BLANCO = Color.WHITE;
    public static final Color COLOR_PELIGRO = new Color(214, 69, 65);
    public static final Color COLOR_EXITO = new Color(46, 160, 90);

    public static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font FUENTE_SUBTITULO = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font FUENTE_LABEL = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FUENTE_LABEL_BOLD = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FUENTE_BOTON = new Font("Segoe UI", Font.BOLD, 13);

    private UITheme() {
    }

    /** Panel contenedor base con fondo y margen estándar. */
    public static JPanel panelBase() {
        JPanel panel = new JPanel();
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));
        return panel;
    }

    public static JLabel titulo(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(FUENTE_TITULO);
        label.setForeground(COLOR_TEXTO);
        return label;
    }

    public static JLabel subtitulo(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(FUENTE_SUBTITULO);
        label.setForeground(COLOR_TEXTO);
        return label;
    }

    public static JLabel label(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(FUENTE_LABEL);
        label.setForeground(COLOR_TEXTO);
        return label;
    }

    public static JTextField campoTexto(int columnas) {
        JTextField campo = new JTextField(columnas);
        estilizarCampo(campo);
        return campo;
    }

    public static void estilizarCampo(JTextField campo) {
        campo.setFont(FUENTE_LABEL);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE, 1, true),
                new EmptyBorder(6, 8, 6, 8)));
    }

    /** Botón principal (acción destacada, ej. "Crear proyecto"). */
    public static JButton botonPrimario(String texto) {
        JButton boton = botonBase(texto);
        boton.setBackground(COLOR_PRIMARIO);
        boton.setForeground(COLOR_BLANCO);
        boton.getModel().addChangeListener(e -> {
            if (boton.getModel().isRollover()) {
                boton.setBackground(COLOR_PRIMARIO_HOVER);
            } else {
                boton.setBackground(COLOR_PRIMARIO);
            }
        });
        return boton;
    }

    /** Botón secundario (acciones normales dentro de una pantalla). */
    public static JButton botonSecundario(String texto) {
        JButton boton = botonBase(texto);
        boton.setBackground(COLOR_BLANCO);
        boton.setForeground(COLOR_TEXTO);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE, 1, true),
                new EmptyBorder(8, 16, 8, 16)));
        return boton;
    }

    /** Botón de navegación de retorno / cancelar. */
    public static JButton botonVolver(String texto) {
        JButton boton = botonBase(texto);
        boton.setBackground(COLOR_SECUNDARIO);
        boton.setForeground(COLOR_BLANCO);
        return boton;
    }

    /** Botón de acción peligrosa / irreversible. */
    public static JButton botonPeligro(String texto) {
        JButton boton = botonBase(texto);
        boton.setBackground(COLOR_PELIGRO);
        boton.setForeground(COLOR_BLANCO);
        return boton;
    }

    private static JButton botonBase(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(FUENTE_BOTON);
        boton.setFocusPainted(false);
        boton.setBorder(new EmptyBorder(8, 16, 8, 16));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setOpaque(true);
        boton.setBorderPainted(false);
        return boton;
    }

    public static JTable tablaEstilizada(JTable tabla) {
        tabla.setFont(FUENTE_LABEL);
        tabla.setRowHeight(26);
        tabla.setGridColor(COLOR_BORDE);
        tabla.setSelectionBackground(COLOR_PRIMARIO);
        tabla.setSelectionForeground(COLOR_BLANCO);
        tabla.getTableHeader().setFont(FUENTE_LABEL_BOLD);
        tabla.getTableHeader().setBackground(COLOR_FONDO);
        tabla.getTableHeader().setReorderingAllowed(false);
        return tabla;
    }

    public static GridBagConstraints gbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;
        return gbc;
    }
}
