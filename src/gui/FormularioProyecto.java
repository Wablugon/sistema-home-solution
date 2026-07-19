package gui;

import gui.util.DialogoUtils;
import gui.util.UITheme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import excepciones.HomeSolutionException;

import java.awt.*;
import java.text.ParseException;

public class FormularioProyecto extends JPanel {

    private final PanelManager panelManager;
    private DefaultTableModel modelTabla;
    private JTable tableTareas;

    private JTextField textTituloTarea;
    private JTextField textDescTarea;
    private JTextField textDiasTarea;

    private JTextField textClienteNombre;
    private JTextField textClienteMail;
    private JTextField textClienteTelefono;
    private JTextField textDomicilio;
    private JFormattedTextField textFechaInicio;
    private JFormattedTextField textFechaFin;

    public FormularioProyecto(PanelManager panelManager) {
        this.panelManager = panelManager;
        armarFormulario();
    }

    private void armarFormulario() {
        setLayout(new BorderLayout());
        setBackground(UITheme.COLOR_FONDO);

        JPanel contenedor = UITheme.panelBase();
        contenedor.setLayout(new BorderLayout(0, 15));
        contenedor.add(UITheme.titulo("Nuevo Proyecto"), BorderLayout.NORTH);

        JPanel cuerpo = new JPanel();
        cuerpo.setLayout(new BoxLayout(cuerpo, BoxLayout.Y_AXIS));
        cuerpo.setBackground(UITheme.COLOR_FONDO);
        cuerpo.add(panelTareas());
        cuerpo.add(Box.createVerticalStrut(15));
        cuerpo.add(panelDatosProyecto());

        JScrollPane scrollGeneral = new JScrollPane(cuerpo);
        scrollGeneral.setBorder(null);
        scrollGeneral.getVerticalScrollBar().setUnitIncrement(16);
        contenedor.add(scrollGeneral, BorderLayout.CENTER);

        JButton crearProyecto = UITheme.botonPrimario("Crear Proyecto");
        JButton volverPrincipal = UITheme.botonVolver("Volver al menú");
        JPanel piePagina = new JPanel(new FlowLayout(FlowLayout.LEFT));
        piePagina.setBackground(UITheme.COLOR_FONDO);
        piePagina.add(volverPrincipal);
        piePagina.add(crearProyecto);
        contenedor.add(piePagina, BorderLayout.SOUTH);

        volverPrincipal.addActionListener(e -> panelManager.mostrar(PanelManager.PANTALLA_PRINCIPAL));
        crearProyecto.addActionListener(e -> crearProyecto());

        add(contenedor, BorderLayout.CENTER);
    }

    private JPanel panelTareas() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(UITheme.COLOR_BLANCO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.COLOR_BORDE), BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        panel.add(UITheme.subtitulo("Tareas del proyecto"), BorderLayout.NORTH);

        JPanel formTarea = new JPanel(new GridBagLayout());
        formTarea.setBackground(UITheme.COLOR_BLANCO);
        textTituloTarea = UITheme.campoTexto(18);
        textDescTarea = UITheme.campoTexto(24);
        textDiasTarea = UITheme.campoTexto(6);
        JButton agregar = UITheme.botonSecundario("Agregar tarea");

        formTarea.add(UITheme.label("Título"), UITheme.gbc(0, 0));
        formTarea.add(textTituloTarea, UITheme.gbc(1, 0));
        formTarea.add(UITheme.label("Descripción"), UITheme.gbc(2, 0));
        formTarea.add(textDescTarea, UITheme.gbc(3, 0));
        formTarea.add(UITheme.label("Días"), UITheme.gbc(4, 0));
        formTarea.add(textDiasTarea, UITheme.gbc(5, 0));
        formTarea.add(agregar, UITheme.gbc(6, 0));

        modelTabla = new DefaultTableModel(new Object[]{"Título", "Descripción", "Días"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableTareas = UITheme.tablaEstilizada(new JTable(modelTabla));
        JScrollPane scrollTabla = new JScrollPane(tableTareas);
        scrollTabla.setPreferredSize(new Dimension(600, 140));
        scrollTabla.setBorder(BorderFactory.createLineBorder(UITheme.COLOR_BORDE));

        panel.add(formTarea, BorderLayout.CENTER);
        panel.add(scrollTabla, BorderLayout.SOUTH);

        agregar.addActionListener(e -> agregarTareaATabla());
        return panel;
    }

    private JPanel panelDatosProyecto() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(UITheme.COLOR_BLANCO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.COLOR_BORDE), BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        MaskFormatter formatterFecha = crearFormatterFecha();

        textClienteNombre = UITheme.campoTexto(20);
        textClienteMail = UITheme.campoTexto(20);
        textClienteTelefono = UITheme.campoTexto(12);
        textDomicilio = UITheme.campoTexto(30);
        textFechaInicio = new JFormattedTextField(formatterFecha);
        textFechaFin = new JFormattedTextField(crearFormatterFecha());
        UITheme.estilizarCampo(textFechaInicio);
        UITheme.estilizarCampo(textFechaFin);

        GridBagConstraints gbcTitulo = UITheme.gbc(0, 0);
        gbcTitulo.gridwidth = 4;
        panel.add(UITheme.subtitulo("Datos del cliente y del proyecto"), gbcTitulo);

        panel.add(UITheme.label("Nombre del cliente"), UITheme.gbc(0, 1));
        panel.add(textClienteNombre, UITheme.gbc(1, 1));
        panel.add(UITheme.label("eMail del cliente"), UITheme.gbc(2, 1));
        panel.add(textClienteMail, UITheme.gbc(3, 1));

        panel.add(UITheme.label("Teléfono del cliente"), UITheme.gbc(0, 2));
        panel.add(textClienteTelefono, UITheme.gbc(1, 2));
        panel.add(UITheme.label("Domicilio"), UITheme.gbc(2, 2));
        panel.add(textDomicilio, UITheme.gbc(3, 2));

        panel.add(UITheme.label("Fecha de inicio (AAAA-MM-DD)"), UITheme.gbc(0, 3));
        panel.add(textFechaInicio, UITheme.gbc(1, 3));
        panel.add(UITheme.label("Fecha de finalización (AAAA-MM-DD)"), UITheme.gbc(2, 3));
        panel.add(textFechaFin, UITheme.gbc(3, 3));

        return panel;
    }

    private MaskFormatter crearFormatterFecha() {
        try {
            MaskFormatter formatter = new MaskFormatter("####-##-##");
            formatter.setPlaceholderCharacter('_');
            return formatter;
        } catch (ParseException e) {
            return null;
        }
    }

    private void agregarTareaATabla() {
        String titulo = textTituloTarea.getText().trim();
        String descripcion = textDescTarea.getText().trim();
        String dias = textDiasTarea.getText().trim();

        if (titulo.isEmpty()) {
            DialogoUtils.mostrarAdvertencia(this, "Debe ingresar el título de la tarea.");
            return;
        }
        try {
            Double.parseDouble(dias);
        } catch (NumberFormatException ex) {
            DialogoUtils.mostrarAdvertencia(this, "La cantidad de días debe ser un número válido.");
            return;
        }
        modelTabla.addRow(new Object[]{titulo, descripcion, dias});
        textTituloTarea.setText("");
        textDescTarea.setText("");
        textDiasTarea.setText("");
    }

    private void crearProyecto() {
        if (modelTabla.getRowCount() == 0) {
            DialogoUtils.mostrarAdvertencia(this, "Debe agregar al menos una tarea al proyecto.");
            return;
        }
        String[] titulos = new String[modelTabla.getRowCount()];
        String[] descripciones = new String[modelTabla.getRowCount()];
        double[] dias = new double[modelTabla.getRowCount()];
        for (int i = 0; i < modelTabla.getRowCount(); i++) {
            titulos[i] = modelTabla.getValueAt(i, 0).toString().trim();
            descripciones[i] = modelTabla.getValueAt(i, 1).toString();
            dias[i] = Double.parseDouble(modelTabla.getValueAt(i, 2).toString());
        }
        String[] cliente = {
                textClienteNombre.getText().trim(),
                textClienteMail.getText().trim(),
                textClienteTelefono.getText().trim()
        };
        try {
            panelManager.sistema().registrarProyecto(titulos, descripciones, dias, textDomicilio.getText().trim(),
                    cliente, textFechaInicio.getText().trim(), textFechaFin.getText().trim());
            DialogoUtils.mostrarInfo(this, "Proyecto creado correctamente.");
            panelManager.reiniciarFormularioProyecto();
            panelManager.mostrar(PanelManager.PANTALLA_LISTA_PROYECTOS);
        } catch (HomeSolutionException exception) {
            DialogoUtils.mostrarError(this, exception.getMessage());
        }
    }
}
