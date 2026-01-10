package ui.panels;

import javax.swing.*;
import java.awt.*;

public class GestionUsuariosPanel extends JPanel{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JLabel img;
    private JTable table1;
    private JPanel UsuariosPanel;

    public GestionUsuariosPanel(){
        UsuariosPanel.setPreferredSize(new Dimension(900,600));
        setLayout(new BorderLayout());
        add(UsuariosPanel,BorderLayout.CENTER);
        ImageIcon uss = new ImageIcon(getClass().getResource("/img/GUsuarios.png"));
        img.setIcon(uss);
    }
}
