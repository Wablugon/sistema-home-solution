package gui;

import gui.util.UITheme;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {

    private final PanelManager panelManager;

    public PanelPrincipal(PanelManager panelManager) {
        this.panelManager = panelManager;
        armarFormulario();
    }

    private void armarFormulario() {
        setLayout(new BorderLayout());
        setBackground(UITheme.COLOR_FONDO);

        JLabel titulo = UITheme.titulo("Home Solution");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(40, 0, 10, 0));

        JLabel subtitulo = UITheme.label("Gestión de empleados y proyectos");
        subtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        subtitulo.setForeground(UITheme.COLOR_SECUNDARIO);

        JPanel encabezado = new JPanel();
        encabezado.setLayout(new BoxLayout(encabezado, BoxLayout.Y_AXIS));
        encabezado.setBackground(UITheme.COLOR_FONDO);
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        subtitulo.setAlignmentX(CENTER_ALIGNMENT);
        encabezado.add(titulo);
        encabezado.add(subtitulo);
        encabezado.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));

        JPanel botones = new JPanel(new GridLayout(2, 2, 24, 24));
        botones.setBackground(UITheme.COLOR_FONDO);
        botones.setBorder(BorderFactory.createEmptyBorder(20, 120, 60, 120));

        JButton nuevoEmpleado = UITheme.botonPrimario("Nuevo Empleado");
        JButton gestionEmpleados = UITheme.botonSecundario("Gestión de Empleados");
        JButton nuevoProyecto = UITheme.botonPrimario("Nuevo Proyecto");
        JButton listaProyectos = UITheme.botonSecundario("Lista de Proyectos");

        for (JButton boton : new JButton[]{nuevoEmpleado, gestionEmpleados, nuevoProyecto, listaProyectos}) {
            boton.setPreferredSize(new Dimension(220, 70));
        }

        botones.add(nuevoEmpleado);
        botones.add(gestionEmpleados);
        botones.add(nuevoProyecto);
        botones.add(listaProyectos);

        nuevoEmpleado.addActionListener(e -> panelManager.mostrar(PanelManager.PANTALLA_NUEVO_EMPLEADO));
        gestionEmpleados.addActionListener(e -> panelManager.mostrar(PanelManager.PANTALLA_GESTION_EMPLEADOS));
        nuevoProyecto.addActionListener(e -> panelManager.mostrar(PanelManager.PANTALLA_NUEVO_PROYECTO));
        listaProyectos.addActionListener(e -> panelManager.mostrar(PanelManager.PANTALLA_LISTA_PROYECTOS));

        add(encabezado, BorderLayout.NORTH);
        add(botones, BorderLayout.CENTER);
    }
}
