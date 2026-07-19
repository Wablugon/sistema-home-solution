package gui;

import entidades.Tupla;
import gui.util.DialogoUtils;
import gui.util.UITheme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GestionEmpleados extends JPanel {

    private final PanelManager panelManager;
    private JTable tablaEmpleados;

    public GestionEmpleados(PanelManager panelManager) {
        this.panelManager = panelManager;
        armarFormulario();
    }

    private void armarFormulario() {
        setLayout(new BorderLayout());
        setBackground(UITheme.COLOR_FONDO);

        JPanel contenedor = UITheme.panelBase();
        contenedor.setLayout(new BorderLayout(0, 15));

        JLabel titulo = UITheme.titulo("Empleados");
        contenedor.add(titulo, BorderLayout.NORTH);

        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Legajo", "Nombre", "Tiene retrasos"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaEmpleados = UITheme.tablaEstilizada(new JTable(modelo));
        cargarTabla(modelo);
        JScrollPane scrollPane = new JScrollPane(tablaEmpleados);
        scrollPane.setBorder(BorderFactory.createLineBorder(UITheme.COLOR_BORDE));
        contenedor.add(scrollPane, BorderLayout.CENTER);

        JButton verRetrasos = UITheme.botonSecundario("Ver retrasos del empleado seleccionado");
        JButton volverPrincipal = UITheme.botonVolver("Volver al menú");

        JPanel piePagina = new JPanel(new FlowLayout(FlowLayout.LEFT));
        piePagina.setBackground(UITheme.COLOR_FONDO);
        piePagina.add(volverPrincipal);
        piePagina.add(verRetrasos);
        contenedor.add(piePagina, BorderLayout.SOUTH);

        volverPrincipal.addActionListener(e -> panelManager.mostrar(PanelManager.PANTALLA_PRINCIPAL));
        verRetrasos.addActionListener(e -> mostrarRetrasos());

        add(contenedor, BorderLayout.CENTER);
    }

    private void mostrarRetrasos() {
        int fila = tablaEmpleados.getSelectedRow();
        if (fila == -1) {
            DialogoUtils.mostrarAdvertencia(this, "Debe seleccionar un empleado.");
            return;
        }
        int legajo = Integer.parseInt(tablaEmpleados.getValueAt(fila, 0).toString());
        int retrasos = panelManager.sistema().consultarCantidadRetrasosEmpleado(legajo);
        String mensaje = retrasos == 0 ? "El empleado no tiene retrasos." : "El empleado tiene " + retrasos + " retraso(s).";
        DialogoUtils.mostrarInfo(this, mensaje);
    }

    private void cargarTabla(DefaultTableModel modelo) {
        List<Tupla<Integer, String>> empleados = panelManager.sistema().empleados();
        modelo.setRowCount(0);
        if (empleados != null) {
            for (Tupla<Integer, String> t : empleados) {
                boolean tieneRetrasos = panelManager.sistema().tieneRestrasos(t.getValor1());
                modelo.addRow(new Object[]{t.getValor1(), t.getValor2(), tieneRetrasos ? "Sí" : "No"});
            }
        }
    }
}
