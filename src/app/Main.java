package app;

import gui.PanelManager;
import sistema.HomeSolution;
import sistema.IHomeSolution;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }

        SwingUtilities.invokeLater(() -> {
            IHomeSolution homeSolution = new HomeSolution();
            new PanelManager(homeSolution);
        });
    }
}