package gui;

import gui.util.DialogoUtils;
import gui.util.UITheme;

import javax.swing.*;
import java.awt.*;

public class FormularioTarea extends JDialog {

    private JTextField textTitulo;
    private JTextField textDescripcion;
    private JTextField textDias;
    private boolean confirmado = false;

    public FormularioTarea(Frame owner) {
        super(owner, "Nueva Tarea", true);
        setSize(420, 260);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        crearComponentes();
    }

    private void crearComponentes() {
        JPanel panel = UITheme.panelBase();
        panel.setLayout(new GridBagLayout());

        textTitulo = UITheme.campoTexto(22);
        textDescripcion = UITheme.campoTexto(22);
        textDias = UITheme.campoTexto(8);

        panel.add(UITheme.label("Título"), UITheme.gbc(0, 0));
        panel.add(textTitulo, UITheme.gbc(1, 0));
        panel.add(UITheme.label("Descripción"), UITheme.gbc(0, 1));
        panel.add(textDescripcion, UITheme.gbc(1, 1));
        panel.add(UITheme.label("Cantidad de días"), UITheme.gbc(0, 2));
        panel.add(textDias, UITheme.gbc(1, 2));

        JButton btnAceptar = UITheme.botonPrimario("Aceptar");
        JButton btnCancelar = UITheme.botonVolver("Cancelar");
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setBackground(UITheme.COLOR_FONDO);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnAceptar);

        GridBagConstraints gbcBotones = UITheme.gbc(0, 3);
        gbcBotones.gridwidth = 2;
        gbcBotones.fill = GridBagConstraints.HORIZONTAL;
        panel.add(panelBotones, gbcBotones);

        setContentPane(panel);

        btnAceptar.addActionListener(e -> {
            if (validarDatos()) {
                confirmado = true;
                dispose();
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }

    private boolean validarDatos() {
        if (textTitulo.getText().trim().isEmpty()) {
            DialogoUtils.mostrarAdvertencia(this, "Debe ingresar un título para la tarea.");
            return false;
        }
        try {
            Double.parseDouble(textDias.getText().trim());
        } catch (NumberFormatException ex) {
            DialogoUtils.mostrarAdvertencia(this, "La cantidad de días debe ser un número válido.");
            return false;
        }
        return true;
    }

    /** Indica si el usuario confirmó el alta de la tarea (Aceptar) o la canceló. */
    public boolean isConfirmado() {
        return confirmado;
    }

    public String getTitulo() {
        return textTitulo.getText().trim();
    }

    public String getDescripcion() {
        return textDescripcion.getText().trim();
    }

    public String getDias() {
        return textDias.getText().trim();
    }
}
