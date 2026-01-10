package ui.panels;

import javax.swing.*;
import java.awt.*;

public class RegistrarExamenesPanel extends JPanel {
    private JPanel panelExamen;
    private JTextField cedula;
    private JSpinner teoria;
    private JSpinner practica;
    private JPanel busqueda;
    private JPanel notas;
    private JButton guardarResultadosButton;
    private JPanel guardar;
    private JLabel imagen;

    public RegistrarExamenesPanel(){
        setLayout(new BorderLayout());
        panelExamen.setPreferredSize(new Dimension(900,600));
        add(panelExamen,BorderLayout.CENTER);
        notas.setPreferredSize(new Dimension(900,200));
        guardar.setPreferredSize(new Dimension(900,100));
        practica.setPreferredSize(new Dimension(60,30));
        teoria.setPreferredSize(new Dimension(60,30));
        cedula.setPreferredSize(new Dimension(60,30));
        ImageIcon rex = new ImageIcon(getClass().getResource("/img/Rexamenes.png"));
        imagen.setIcon(rex);
    }
}
