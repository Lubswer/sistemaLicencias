package ui.panels;

import javax.swing.*;
import java.awt.*;

public class GestionTramitesPanel extends JPanel{
    private JTextField textField1;
    private JComboBox comboBox1;
    private JTable table1;
    private JButton buscarButton;
    private JButton buscarButton1;
    private JPanel TramitesPanel;
    private JPanel panelsito;
    private JPanel tablaa;
    private JLabel traa;

    public GestionTramitesPanel(){
        TramitesPanel.setPreferredSize(new Dimension(900,600));
        setLayout(new BorderLayout());
        add(TramitesPanel,BorderLayout.CENTER);
        ImageIcon tra = new ImageIcon(getClass().getResource("/img/GTramites.png"));
        traa.setIcon(tra);
    }
}
