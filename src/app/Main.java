package app;

import gui.PanelManager;
import sistema.HomeSolution;
import sistema.IHomeSolution;

import javax.swing.*;

/**
 * Punto de entrada del sistema "Home Solution".
 * Su única responsabilidad es inicializar el contexto (backend) y lanzar
 * la interfaz gráfica; no contiene lógica de negocio ni de presentación.
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            // Si el Look & Feel del sistema no está disponible, se continúa con el
            // predeterminado de Swing; no es un error crítico para el arranque.
        }

        SwingUtilities.invokeLater(() -> {
            IHomeSolution homeSolution = new HomeSolution();
            new PanelManager(homeSolution);
        });
    }
}