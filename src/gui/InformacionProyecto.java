package gui;

import gui.util.UITheme;

import javax.swing.*;
import java.awt.*;

/**
 * Diálogo modal de solo lectura que muestra la información detallada
 * de un proyecto puntual.
 */
public class InformacionProyecto extends JDialog {

    public InformacionProyecto(Frame frame, Integer numero, String informacion) {
        super(frame, "Información del Proyecto", true);
        setSize(520, 380);
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        crearComponentes(numero, informacion);
    }

    private void crearComponentes(Integer numero, String informacion) {
        JPanel panel = UITheme.panelBase();
        panel.setLayout(new BorderLayout(0, 15));

        JLabel labelTitulo = UITheme.subtitulo("Proyecto Nº " + numero);
        panel.add(labelTitulo, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea(informacion);
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setFont(UITheme.FUENTE_LABEL);
        textArea.setBackground(UITheme.COLOR_BLANCO);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(BorderFactory.createLineBorder(UITheme.COLOR_BORDE));
        panel.add(scroll, BorderLayout.CENTER);

        JButton aceptar = UITheme.botonPrimario("Cerrar");
        aceptar.addActionListener(e -> dispose());
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setBackground(UITheme.COLOR_FONDO);
        panelBotones.add(aceptar);
        panel.add(panelBotones, BorderLayout.SOUTH);

        setContentPane(panel);
    }
}
