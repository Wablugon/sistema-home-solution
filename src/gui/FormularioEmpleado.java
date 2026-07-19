package gui;

import gui.util.DialogoUtils;
import gui.util.UITheme;

import javax.swing.*;
import java.awt.*;

public class FormularioEmpleado extends JPanel {

    private final PanelManager panelManager;

    private JTextField textNombreContratado;
    private JTextField textValorHora;

    private JTextField textNombrePermanente;
    private JTextField textValorDia;
    private JComboBox<String> comboCategoria;

    public FormularioEmpleado(PanelManager panelManager) {
        this.panelManager = panelManager;
        armarFormulario();
    }

    private void armarFormulario() {
        setLayout(new BorderLayout());
        setBackground(UITheme.COLOR_FONDO);

        JPanel contenedor = UITheme.panelBase();
        contenedor.setLayout(new BorderLayout(0, 20));

        JLabel titulo = UITheme.titulo("Nuevo Empleado");
        contenedor.add(titulo, BorderLayout.NORTH);

        JTabbedPane pestañas = new JTabbedPane();
        pestañas.setFont(UITheme.FUENTE_LABEL_BOLD);
        pestañas.addTab("Empleado Contratado", panelContratado());
        pestañas.addTab("Empleado de Planta Permanente", panelPermanente());
        contenedor.add(pestañas, BorderLayout.CENTER);

        JButton volver = UITheme.botonVolver("Volver al menú");
        volver.addActionListener(e -> panelManager.mostrar(PanelManager.PANTALLA_PRINCIPAL));
        JPanel piePagina = new JPanel(new FlowLayout(FlowLayout.LEFT));
        piePagina.setBackground(UITheme.COLOR_FONDO);
        piePagina.add(volver);
        contenedor.add(piePagina, BorderLayout.SOUTH);

        add(contenedor, BorderLayout.CENTER);
    }

    private JPanel panelContratado() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(UITheme.COLOR_BLANCO);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        textNombreContratado = UITheme.campoTexto(25);
        textValorHora = UITheme.campoTexto(10);
        JButton agregar = UITheme.botonPrimario("Agregar Contratado");

        panel.add(UITheme.label("Nombre del empleado"), UITheme.gbc(0, 0));
        panel.add(textNombreContratado, UITheme.gbc(1, 0));
        panel.add(UITheme.label("Valor por hora"), UITheme.gbc(0, 1));
        panel.add(textValorHora, UITheme.gbc(1, 1));
        panel.add(agregar, UITheme.gbc(1, 2));

        agregar.addActionListener(e -> registrarContratado());
        return panel;
    }

    private JPanel panelPermanente() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(UITheme.COLOR_BLANCO);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        textNombrePermanente = UITheme.campoTexto(25);
        textValorDia = UITheme.campoTexto(10);
        comboCategoria = new JComboBox<>(new String[]{"INICIAL", "TECNICO", "EXPERTO"});
        comboCategoria.setFont(UITheme.FUENTE_LABEL);
        JButton agregar = UITheme.botonPrimario("Agregar Permanente");

        panel.add(UITheme.label("Nombre del empleado"), UITheme.gbc(0, 0));
        panel.add(textNombrePermanente, UITheme.gbc(1, 0));
        panel.add(UITheme.label("Valor por día"), UITheme.gbc(0, 1));
        panel.add(textValorDia, UITheme.gbc(1, 1));
        panel.add(UITheme.label("Categoría"), UITheme.gbc(0, 2));
        panel.add(comboCategoria, UITheme.gbc(1, 2));
        panel.add(agregar, UITheme.gbc(1, 3));

        agregar.addActionListener(e -> registrarPermanente());
        return panel;
    }

    private void registrarContratado() {
        String nombre = textNombreContratado.getText().trim();
        if (nombre.isEmpty()) {
            DialogoUtils.mostrarAdvertencia(this, "Debe ingresar el nombre del empleado.");
            return;
        }
        try {
            double valorHora = Double.parseDouble(textValorHora.getText().trim());
            panelManager.sistema().registrarEmpleado(nombre, valorHora);
            DialogoUtils.mostrarInfo(this, "Empleado contratado registrado correctamente.");
            textNombreContratado.setText("");
            textValorHora.setText("");
        } catch (NumberFormatException ex) {
            DialogoUtils.mostrarError(this, "El valor por hora debe ser un número válido.");
        } catch (IllegalArgumentException ex) {
            DialogoUtils.mostrarError(this, "Los valores ingresados no son válidos.");
        }
    }

    private void registrarPermanente() {
        String nombre = textNombrePermanente.getText().trim();
        if (nombre.isEmpty()) {
            DialogoUtils.mostrarAdvertencia(this, "Debe ingresar el nombre del empleado.");
            return;
        }
        try {
            double valorDia = Double.parseDouble(textValorDia.getText().trim());
            String categoria = comboCategoria.getSelectedItem().toString();
            panelManager.sistema().registrarEmpleado(nombre, valorDia, categoria);
            DialogoUtils.mostrarInfo(this, "Empleado de planta permanente registrado correctamente.");
            textNombrePermanente.setText("");
            textValorDia.setText("");
            comboCategoria.setSelectedIndex(0);
        } catch (NumberFormatException ex) {
            DialogoUtils.mostrarError(this, "El valor por día debe ser un número válido.");
        } catch (IllegalArgumentException ex) {
            DialogoUtils.mostrarError(this, "Los valores ingresados no son válidos.");
        }
    }
}
