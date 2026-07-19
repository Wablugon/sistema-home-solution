package gui;

import entidades.Tupla;
import gui.util.DialogoUtils;
import gui.util.UITheme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListaProyectos extends JPanel {

    private static final String ESTADO_PENDIENTE = "PENDIENTE";
    private static final String ESTADO_ACTIVO = "ACTIVO";
    private static final String ESTADO_FINALIZADO = "FINALIZADO";

    private final PanelManager panelManager;
    private JRadioButton pendiente;
    private JRadioButton activo;
    private JRadioButton finalizado;
    private JTable tablaProyectos;
    private DefaultTableModel modelTabla;

    public ListaProyectos(PanelManager panelManager) {
        this.panelManager = panelManager;
        armarFormulario();
    }

    private void armarFormulario() {
        setLayout(new BorderLayout());
        setBackground(UITheme.COLOR_FONDO);

        JPanel contenedor = UITheme.panelBase();
        contenedor.setLayout(new BorderLayout(0, 15));
        contenedor.add(UITheme.titulo("Proyectos"), BorderLayout.NORTH);

        JPanel filtro = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        filtro.setBackground(UITheme.COLOR_BLANCO);
        filtro.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.COLOR_BORDE), BorderFactory.createEmptyBorder(12, 12, 12, 12)));

        filtro.add(UITheme.label("Filtrar por estado:"));
        pendiente = new JRadioButton("Pendiente", true);
        activo = new JRadioButton("Activo");
        finalizado = new JRadioButton("Finalizado");
        for (JRadioButton radio : new JRadioButton[]{pendiente, activo, finalizado}) {
            radio.setFont(UITheme.FUENTE_LABEL);
            radio.setBackground(UITheme.COLOR_BLANCO);
        }
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(pendiente);
        grupo.add(activo);
        grupo.add(finalizado);
        filtro.add(pendiente);
        filtro.add(activo);
        filtro.add(finalizado);

        JButton mostrarBtn = UITheme.botonSecundario("Mostrar proyectos");
        filtro.add(mostrarBtn);

        modelTabla = new DefaultTableModel(new Object[]{"Número de Proyecto", "Datos"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaProyectos = UITheme.tablaEstilizada(new JTable(modelTabla));
        JScrollPane scrollPane = new JScrollPane(tablaProyectos);
        scrollPane.setBorder(BorderFactory.createLineBorder(UITheme.COLOR_BORDE));

        JPanel centro = new JPanel(new BorderLayout(0, 10));
        centro.setBackground(UITheme.COLOR_FONDO);
        centro.add(filtro, BorderLayout.NORTH);
        centro.add(scrollPane, BorderLayout.CENTER);
        contenedor.add(centro, BorderLayout.CENTER);

        JButton verSeleccionado = UITheme.botonPrimario("Ver proyecto seleccionado");
        JButton volverPrincipal = UITheme.botonVolver("Volver al menú");
        JPanel piePagina = new JPanel(new FlowLayout(FlowLayout.LEFT));
        piePagina.setBackground(UITheme.COLOR_FONDO);
        piePagina.add(volverPrincipal);
        piePagina.add(verSeleccionado);
        contenedor.add(piePagina, BorderLayout.SOUTH);

        mostrarBtn.addActionListener(e -> cargarTabla());
        verSeleccionado.addActionListener(e -> verSeleccionado());
        volverPrincipal.addActionListener(e -> panelManager.mostrar(PanelManager.PANTALLA_PRINCIPAL));

        add(contenedor, BorderLayout.CENTER);

        cargarTabla();
    }

    private void cargarTabla() {
        String estado = pendiente.isSelected() ? ESTADO_PENDIENTE
                : activo.isSelected() ? ESTADO_ACTIVO
                : ESTADO_FINALIZADO;

        List<Tupla<Integer, String>> proyectos;
        switch (estado) {
            case ESTADO_ACTIVO:
                proyectos = panelManager.sistema().proyectosActivos();
                break;
            case ESTADO_FINALIZADO:
                proyectos = panelManager.sistema().proyectosFinalizados();
                break;
            default:
                proyectos = panelManager.sistema().proyectosPendientes();
                break;
        }

        modelTabla.setRowCount(0);
        if (proyectos != null) {
            for (Tupla<Integer, String> t : proyectos) {
                modelTabla.addRow(new Object[]{t.getValor1(), t.getValor2()});
            }
        }
        if (modelTabla.getRowCount() == 0) {
            DialogoUtils.mostrarInfo(this, "No hay proyectos en ese estado.");
        }
    }

    private void verSeleccionado() {
        int fila = tablaProyectos.getSelectedRow();
        if (fila == -1) {
            DialogoUtils.mostrarAdvertencia(this, "Debe seleccionar un proyecto.");
            return;
        }
        int numero = Integer.parseInt(tablaProyectos.getValueAt(fila, 0).toString());
        panelManager.seleccionar(numero);
        panelManager.mostrar(PanelManager.PANTALLA_GESTION_PROYECTO);
    }
}
