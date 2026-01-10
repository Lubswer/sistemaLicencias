package ui.panels;

import javax.swing.*;
import java.awt.*;

public class VerificarRequisitosPanel extends JPanel{
    private JPanel panelRequisitos;
    private JTextField textField1;
    private JCheckBox certificadoMédicoCheckBox;
    private JCheckBox pagoRealizadoCheckBox;
    private JCheckBox sinMultasCheckBox;
    private JPanel panelsito;
    private JPanel verificaciones;
    private JButton aceptarButton;
    private JButton rechazarButton;
    private JButton aceptarRequisitosButton;
    private JButton rechazarRequisitosButton;

    public VerificarRequisitosPanel(){
        setLayout(new BorderLayout());
        panelRequisitos.setPreferredSize(new Dimension(900,600));
        add(panelRequisitos,BorderLayout.CENTER);
        panelsito.setPreferredSize(new Dimension(600,400));
        verificaciones.setPreferredSize(new Dimension(100,100));
        aceptarButton.setPreferredSize(new Dimension(60,30));
        rechazarButton.setPreferredSize(new Dimension(60,30));
        textField1.setPreferredSize(new Dimension(60,25));
    }
}
