package gui;

import gui.util.DialogoUtils;
import gui.util.UITheme;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

/**
 * Diálogo modal para capturar la fecha de finalización de un proyecto
 * al marcarlo como finalizado.
 */
public class FormularioProyectoFinalizado extends JDialog {

    private JFormattedTextField fechaFinalizacion;
    private boolean confirmado = false;

    public FormularioProyectoFinalizado(Frame owner) {
        super(owner, "Finalizar Proyecto", true);
        setSize(420, 200);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        crearComponentes();
    }

    private void crearComponentes() {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("####-##-##");
            formatter.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            // Máscara fija y válida: no debería ocurrir en tiempo de ejecución.
        }

        JPanel panel = UITheme.panelBase();
        panel.setLayout(new GridBagLayout());

        JLabel labelFecha = UITheme.label("Fecha de finalización (AAAA-MM-DD)");
        fechaFinalizacion = new JFormattedTextField(formatter);
        UITheme.estilizarCampo(fechaFinalizacion);
        fechaFinalizacion.setColumns(10);

        panel.add(labelFecha, UITheme.gbc(0, 0));
        panel.add(fechaFinalizacion, UITheme.gbc(1, 0));

        JButton btnAceptar = UITheme.botonPrimario("Aceptar");
        JButton btnCancelar = UITheme.botonVolver("Cancelar");
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setBackground(UITheme.COLOR_FONDO);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnAceptar);

        GridBagConstraints gbcBotones = UITheme.gbc(0, 1);
        gbcBotones.gridwidth = 2;
        gbcBotones.fill = GridBagConstraints.HORIZONTAL;
        panel.add(panelBotones, gbcBotones);

        setContentPane(panel);

        btnAceptar.addActionListener(e -> {
            String fecha = fechaFinalizacion.getText().trim();
            if (fecha.isEmpty() || fecha.contains("_")) {
                DialogoUtils.mostrarAdvertencia(this, "Debe ingresar una fecha completa y válida.");
                return;
            }
            confirmado = true;
            dispose();
        });
        btnCancelar.addActionListener(e -> dispose());
    }

    /** Indica si el usuario confirmó la finalización (Aceptar) o la canceló. */
    public boolean isConfirmado() {
        return confirmado;
    }

    public String getFecha() {
        return fechaFinalizacion.getText().trim();
    }
}
