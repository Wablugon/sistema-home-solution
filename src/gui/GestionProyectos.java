package gui;

import entidades.Tupla;
import gui.util.DialogoUtils;
import gui.util.UITheme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Vista de gestión de un proyecto puntual: administración de sus tareas
 * (asignación, reasignación, retrasos, alta y finalización) y del proyecto
 * en su conjunto (costo, empleados asignados, finalización).
 */
public class GestionProyectos extends JPanel {

    private final PanelManager panelManager;
    private final Integer numeroProyecto;

    private JComboBox<Object> comboTareasSinAsignar;
    private JComboBox<Object> comboTodasLasTareas;

    private JButton asignarEmpleado;
    private JButton asignarEmpleadoEficiente;
    private JButton reasignarEmpleado;
    private JButton reasignarEmpleadoEficiente;
    private JButton registrarRetraso;
    private JButton finalizarTarea;
    private JButton agregarTarea;
    private JButton finalizarProyecto;

    public GestionProyectos(PanelManager panelManager) {
        this.panelManager = panelManager;
        this.numeroProyecto = panelManager.consultarSeleccionado();
        armarFormulario();
    }

    private void armarFormulario() {
        setLayout(new BorderLayout());
        setBackground(UITheme.COLOR_FONDO);

        JPanel contenedor = UITheme.panelBase();
        contenedor.setLayout(new BorderLayout(0, 15));

        String domicilio = panelManager.sistema().consultarDomicilioProyecto(numeroProyecto);
        JLabel titulo = UITheme.titulo("Proyecto Nº " + numeroProyecto + " — " + domicilio);
        contenedor.add(titulo, BorderLayout.NORTH);

        JPanel cuerpo = new JPanel();
        cuerpo.setLayout(new BoxLayout(cuerpo, BoxLayout.Y_AXIS));
        cuerpo.setBackground(UITheme.COLOR_FONDO);
        cuerpo.add(panelTareasSinAsignar());
        cuerpo.add(Box.createVerticalStrut(15));
        cuerpo.add(panelTodasLasTareas());
        cuerpo.add(Box.createVerticalStrut(15));
        cuerpo.add(panelProyecto());

        JScrollPane scrollGeneral = new JScrollPane(cuerpo);
        scrollGeneral.setBorder(null);
        scrollGeneral.getVerticalScrollBar().setUnitIncrement(16);
        contenedor.add(scrollGeneral, BorderLayout.CENTER);

        JButton volverPrincipal = UITheme.botonVolver("Volver al menú");
        volverPrincipal.addActionListener(e -> panelManager.mostrar(PanelManager.PANTALLA_PRINCIPAL));
        JPanel piePagina = new JPanel(new FlowLayout(FlowLayout.LEFT));
        piePagina.setBackground(UITheme.COLOR_FONDO);
        piePagina.add(volverPrincipal);
        contenedor.add(piePagina, BorderLayout.SOUTH);

        add(contenedor, BorderLayout.CENTER);

        refrescarTareas();
        actualizarEstadoBotones();
    }

    private JPanel panelTareasSinAsignar() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(UITheme.COLOR_BLANCO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.COLOR_BORDE), BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        GridBagConstraints gbcTitulo = UITheme.gbc(0, 0);
        gbcTitulo.gridwidth = 3;
        panel.add(UITheme.subtitulo("Tareas sin asignar"), gbcTitulo);

        comboTareasSinAsignar = new JComboBox<>();
        comboTareasSinAsignar.setFont(UITheme.FUENTE_LABEL);
        asignarEmpleado = UITheme.botonPrimario("Asignar empleado");
        asignarEmpleadoEficiente = UITheme.botonSecundario("Asignar con menos retrasos");

        panel.add(UITheme.label("Tarea:"), UITheme.gbc(0, 1));
        panel.add(comboTareasSinAsignar, UITheme.gbc(1, 1));
        panel.add(asignarEmpleado, UITheme.gbc(0, 2));
        panel.add(asignarEmpleadoEficiente, UITheme.gbc(1, 2));

        asignarEmpleado.addActionListener(e -> ejecutarSobreTareaSinAsignar((numero, titulo) -> {
            panelManager.sistema().asignarResponsableEnTarea(numero, titulo);
            DialogoUtils.mostrarInfo(this, "Empleado asignado correctamente.");
        }));
        asignarEmpleadoEficiente.addActionListener(e -> ejecutarSobreTareaSinAsignar((numero, titulo) -> {
            panelManager.sistema().asignarResponsableMenosRetraso(numero, titulo);
            DialogoUtils.mostrarInfo(this, "Empleado con menos retrasos asignado correctamente.");
        }));

        return panel;
    }

    private JPanel panelTodasLasTareas() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(UITheme.COLOR_BLANCO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.COLOR_BORDE), BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        GridBagConstraints gbcTitulo = UITheme.gbc(0, 0);
        gbcTitulo.gridwidth = 4;
        panel.add(UITheme.subtitulo("Todas las tareas del proyecto"), gbcTitulo);

        comboTodasLasTareas = new JComboBox<>();
        comboTodasLasTareas.setFont(UITheme.FUENTE_LABEL);
        reasignarEmpleado = UITheme.botonSecundario("Cambiar empleado responsable");
        reasignarEmpleadoEficiente = UITheme.botonSecundario("Cambiar por el de menos retrasos");
        registrarRetraso = UITheme.botonSecundario("Registrar retraso");
        finalizarTarea = UITheme.botonSecundario("Marcar como finalizada");
        agregarTarea = UITheme.botonPrimario("Agregar nueva tarea");

        panel.add(UITheme.label("Tarea:"), UITheme.gbc(0, 1));
        GridBagConstraints gbcCombo = UITheme.gbc(1, 1);
        gbcCombo.gridwidth = 2;
        panel.add(comboTodasLasTareas, gbcCombo);
        panel.add(agregarTarea, UITheme.gbc(3, 1));

        panel.add(reasignarEmpleado, UITheme.gbc(0, 2));
        panel.add(reasignarEmpleadoEficiente, UITheme.gbc(1, 2));
        panel.add(registrarRetraso, UITheme.gbc(2, 2));
        panel.add(finalizarTarea, UITheme.gbc(3, 2));

        reasignarEmpleado.addActionListener(e -> reasignarEmpleado());
        reasignarEmpleadoEficiente.addActionListener(e -> ejecutarSobreTareaCualquiera((numero, titulo) -> {
            panelManager.sistema().reasignarEmpleadoConMenosRetraso(numero, titulo);
            DialogoUtils.mostrarInfo(this, "Empleado reasignado correctamente.");
        }));
        registrarRetraso.addActionListener(e -> registrarRetraso());
        finalizarTarea.addActionListener(e -> finalizarTareaSeleccionada());
        agregarTarea.addActionListener(e -> agregarTarea());

        return panel;
    }

    private JPanel panelProyecto() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(UITheme.COLOR_BLANCO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.COLOR_BORDE), BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        GridBagConstraints gbcTitulo = UITheme.gbc(0, 0);
        gbcTitulo.gridwidth = 4;
        panel.add(UITheme.subtitulo("Proyecto"), gbcTitulo);

        JButton costoActual = UITheme.botonSecundario("Consultar costo actual");
        JButton empleadosAsignados = UITheme.botonSecundario("Ver empleados asignados");
        JButton verInformacion = UITheme.botonSecundario("Ver información completa");
        finalizarProyecto = UITheme.botonPeligro("Marcar proyecto como finalizado");

        panel.add(costoActual, UITheme.gbc(0, 1));
        panel.add(empleadosAsignados, UITheme.gbc(1, 1));
        panel.add(verInformacion, UITheme.gbc(2, 1));
        panel.add(finalizarProyecto, UITheme.gbc(3, 1));

        costoActual.addActionListener(e -> {
            double costo = panelManager.sistema().costoProyecto(numeroProyecto);
            DialogoUtils.mostrarInfo(this, "El costo actual del proyecto es: $" + costo);
        });
        empleadosAsignados.addActionListener(e -> mostrarEmpleadosAsignados());
        verInformacion.addActionListener(e -> {
            String info = panelManager.sistema().consultarProyecto(numeroProyecto);
            new InformacionProyecto(panelManager.getFrame(), numeroProyecto, info).setVisible(true);
        });
        finalizarProyecto.addActionListener(e -> finalizarProyecto());

        return panel;
    }

    // -------------------- Acciones --------------------

    private interface AccionSobreTarea {
        void ejecutar(Integer numero, String titulo) throws Exception;
    }

    private void ejecutarSobreTareaSinAsignar(AccionSobreTarea accion) {
        Object seleccion = comboTareasSinAsignar.getSelectedItem();
        if (seleccion == null) {
            DialogoUtils.mostrarAdvertencia(this, "No hay tarea seleccionada.");
            return;
        }
        try {
            accion.ejecutar(numeroProyecto, seleccion.toString());
            refrescarTareas();
        } catch (Exception exception) {
            DialogoUtils.mostrarError(this, "No hay empleados disponibles, la tarea quedará pendiente.");
        }
    }

    private void ejecutarSobreTareaCualquiera(AccionSobreTarea accion) {
        Object seleccion = comboTodasLasTareas.getSelectedItem();
        if (seleccion == null) {
            DialogoUtils.mostrarAdvertencia(this, "No hay tarea seleccionada.");
            return;
        }
        try {
            accion.ejecutar(numeroProyecto, seleccion.toString());
            refrescarTareas();
        } catch (Exception exception) {
            DialogoUtils.mostrarError(this, "No hay empleados disponibles para reasignar.");
        }
    }

    private void reasignarEmpleado() {
        Object seleccion = comboTodasLasTareas.getSelectedItem();
        if (seleccion == null) {
            DialogoUtils.mostrarAdvertencia(this, "No hay tarea seleccionada.");
            return;
        }
        Object[] noAsignados = panelManager.sistema().empleadosNoAsignados();
        if (noAsignados == null || noAsignados.length == 0) {
            DialogoUtils.mostrarAdvertencia(this, "No hay empleados disponibles para reasignar.");
            return;
        }
        JComboBox<Object> comboEmpleados = new JComboBox<>(noAsignados);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Seleccione un empleado:"));
        panel.add(comboEmpleados);
        int resultado = JOptionPane.showConfirmDialog(this, panel, "Empleados no asignados",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (resultado != JOptionPane.OK_OPTION) {
            return;
        }
        try {
            Integer legajo = Integer.parseInt(comboEmpleados.getSelectedItem().toString().trim());
            panelManager.sistema().reasignarEmpleadoEnProyecto(numeroProyecto, legajo, seleccion.toString());
            DialogoUtils.mostrarInfo(this, "Empleado reasignado correctamente.");
            refrescarTareas();
        } catch (NumberFormatException ex) {
            DialogoUtils.mostrarError(this, "No se pudo identificar al empleado seleccionado.");
        } catch (Exception ex) {
            DialogoUtils.mostrarError(this, "No hay un empleado asignado previamente en esa tarea.");
        }
    }

    private void registrarRetraso() {
        Object seleccion = comboTodasLasTareas.getSelectedItem();
        if (seleccion == null) {
            DialogoUtils.mostrarAdvertencia(this, "No hay tarea seleccionada.");
            return;
        }
        Double dias = DialogoUtils.pedirNumero(this, "Ingrese la cantidad de días de retraso:");
        if (dias == null) {
            return;
        }
        try {
            panelManager.sistema().registrarRetrasoEnTarea(numeroProyecto, seleccion.toString(), dias);
            DialogoUtils.mostrarInfo(this, "Retraso registrado correctamente.");
        } catch (IllegalArgumentException ex) {
            DialogoUtils.mostrarError(this, "Los valores ingresados no son válidos.");
        }
    }

    private void finalizarTareaSeleccionada() {
        Object seleccion = comboTodasLasTareas.getSelectedItem();
        if (seleccion == null) {
            DialogoUtils.mostrarAdvertencia(this, "No hay tarea seleccionada.");
            return;
        }
        try {
            panelManager.sistema().finalizarTarea(numeroProyecto, seleccion.toString());
            DialogoUtils.mostrarInfo(this, "Tarea finalizada correctamente.");
            refrescarTareas();
        } catch (Exception ex) {
            DialogoUtils.mostrarError(this, "La tarea ya estaba finalizada.");
        }
    }

    private void agregarTarea() {
        FormularioTarea formulario = new FormularioTarea(panelManager.getFrame());
        formulario.setVisible(true);
        if (!formulario.isConfirmado()) {
            return;
        }
        try {
            double dias = Double.parseDouble(formulario.getDias());
            panelManager.sistema().agregarTareaEnProyecto(numeroProyecto, formulario.getTitulo(),
                    formulario.getDescripcion(), dias);
            DialogoUtils.mostrarInfo(this, "Tarea agregada correctamente.");
            refrescarTareas();
        } catch (IllegalArgumentException ex) {
            DialogoUtils.mostrarError(this, "Los valores ingresados no son válidos, o el proyecto ya está finalizado.");
        }
    }

    private void mostrarEmpleadosAsignados() {
        List<Tupla<Integer, String>> empleados = panelManager.sistema().empleadosAsignadosAProyecto(numeroProyecto);
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Legajo", "Nombre"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        if (empleados != null) {
            for (Tupla<Integer, String> t : empleados) {
                modelo.addRow(new Object[]{t.getValor1(), t.getValor2()});
            }
        }
        JTable tabla = UITheme.tablaEstilizada(new JTable(modelo));
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setPreferredSize(new Dimension(350, 180));
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.add(new JLabel("Empleados asignados al proyecto:"), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        JOptionPane.showMessageDialog(this, panel, "Empleados Asignados", JOptionPane.PLAIN_MESSAGE);
    }

    private void finalizarProyecto() {
        if (!DialogoUtils.confirmar(this, "¿Confirma que desea marcar el proyecto como finalizado?")) {
            return;
        }
        FormularioProyectoFinalizado formulario = new FormularioProyectoFinalizado(panelManager.getFrame());
        formulario.setVisible(true);
        if (!formulario.isConfirmado()) {
            return;
        }
        try {
            panelManager.sistema().finalizarProyecto(numeroProyecto, formulario.getFecha());
            DialogoUtils.mostrarInfo(this, "Proyecto finalizado correctamente.");
            panelManager.mostrar(PanelManager.PANTALLA_GESTION_PROYECTO);
        } catch (IllegalArgumentException ex) {
            DialogoUtils.mostrarError(this, "La fecha ingresada no es válida.");
        }
    }

    // -------------------- Utilidades internas --------------------

    private void refrescarTareas() {
        Object[] sinAsignar = panelManager.sistema().tareasProyectoNoAsignadas(numeroProyecto);
        Object[] todas = panelManager.sistema().tareasDeUnProyecto(numeroProyecto);
        comboTareasSinAsignar.setModel(new DefaultComboBoxModel<>(sinAsignar == null ? new Object[0] : sinAsignar));
        comboTareasSinAsignar.setSelectedIndex(-1);
        comboTodasLasTareas.setModel(new DefaultComboBoxModel<>(todas == null ? new Object[0] : todas));
        comboTodasLasTareas.setSelectedIndex(-1);
    }

    private void actualizarEstadoBotones() {
        if (panelManager.sistema().estaFinalizado(numeroProyecto)) {
            asignarEmpleado.setEnabled(false);
            asignarEmpleadoEficiente.setEnabled(false);
            reasignarEmpleado.setEnabled(false);
            reasignarEmpleadoEficiente.setEnabled(false);
            registrarRetraso.setEnabled(false);
            finalizarTarea.setEnabled(false);
            agregarTarea.setEnabled(false);
            finalizarProyecto.setEnabled(false);
        }
    }
}
