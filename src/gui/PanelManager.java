package gui;

import sistema.IHomeSolution;
import gui.util.UITheme;

import javax.swing.*;
import java.awt.*;

public class PanelManager {

    public static final int PANTALLA_PRINCIPAL = 1;
    public static final int PANTALLA_NUEVO_EMPLEADO = 2;
    public static final int PANTALLA_GESTION_EMPLEADOS = 3;
    public static final int PANTALLA_NUEVO_PROYECTO = 4;
    public static final int PANTALLA_LISTA_PROYECTOS = 5;
    public static final int PANTALLA_GESTION_PROYECTO = 6;

    private final JFrame jFrame;
    private final IHomeSolution homeSolution;
    private Integer seleccionado;

    private PanelPrincipal principal;
    private FormularioProyecto formularioProyecto;
    private FormularioEmpleado formularioEmpleado;

    public PanelManager(IHomeSolution h) {
        this.homeSolution = h;
        jFrame = new JFrame("Home Solution");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.getContentPane().setBackground(UITheme.COLOR_FONDO);
        jFrame.setMinimumSize(new Dimension(900, 600));
        principal = new PanelPrincipal(this);
        mostrar(principal);
    }

    /** Reemplaza el contenido actual de la ventana por el panel indicado. */
    public void mostrar(JPanel panel) {
        jFrame.getContentPane().removeAll();
        jFrame.getContentPane().add(panel, BorderLayout.CENTER);
        jFrame.getContentPane().revalidate();
        jFrame.getContentPane().repaint();
        jFrame.setResizable(true);
        jFrame.pack();
        if (jFrame.getWidth() < 900 || jFrame.getHeight() < 600) {
            jFrame.setSize(Math.max(900, jFrame.getWidth()), Math.max(600, jFrame.getHeight()));
        }
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public void mostrar(int codigoPantalla) {
        switch (codigoPantalla) {
            case PANTALLA_PRINCIPAL:
                mostrar(principal);
                break;
            case PANTALLA_NUEVO_EMPLEADO:
                formularioEmpleado = formularioEmpleado == null ? new FormularioEmpleado(this) : formularioEmpleado;
                mostrar(formularioEmpleado);
                break;
            case PANTALLA_GESTION_EMPLEADOS:
                mostrar(new GestionEmpleados(this));
                break;
            case PANTALLA_NUEVO_PROYECTO:
                formularioProyecto = formularioProyecto == null ? new FormularioProyecto(this) : formularioProyecto;
                mostrar(formularioProyecto);
                break;
            case PANTALLA_LISTA_PROYECTOS:
                mostrar(new ListaProyectos(this));
                break;
            case PANTALLA_GESTION_PROYECTO:
                mostrar(new GestionProyectos(this));
                break;
            default:
                // Código de pantalla desconocido: no se realiza ninguna acción.
                break;
        }
    }

    /** Fuerza la recreación del formulario de nuevo proyecto (para limpiar su estado tras crear uno). */
    public void reiniciarFormularioProyecto() {
        formularioProyecto = null;
    }

    /** Fuerza la recreación del formulario de nuevo empleado (para limpiar su estado tras un alta). */
    public void reiniciarFormularioEmpleado() {
        formularioEmpleado = null;
    }

    public IHomeSolution sistema() {
        return homeSolution;
    }

    public JFrame getFrame() {
        return jFrame;
    }

    public void seleccionar(Integer numeroProyecto) {
        this.seleccionado = numeroProyecto;
    }

    public Integer consultarSeleccionado() {
        return seleccionado;
    }
}
