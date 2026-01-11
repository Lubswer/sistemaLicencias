package ui.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionUsuariosPanel extends JPanel{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox comboBox2;
    private JLabel img;
    private JPanel UsuariosPanel;
    private JComboBox comboBox1;
    private JButton guardarCambiosButton;
    private JButton crearUsuarioButton;
    private JPanel panelsito;
    private JTable tablaUsuarios;
    private JPanel tablaa;
    private JButton limpiarButton;
    private DefaultTableModel modeloTabla;
    public GestionUsuariosPanel(){
        UsuariosPanel.setPreferredSize(new Dimension(900,600));
        setLayout(new BorderLayout());
        add(UsuariosPanel,BorderLayout.CENTER);
        ImageIcon uss = new ImageIcon(getClass().getResource("/img/GUsuarios.png"));
        img.setIcon(uss);
        panelsito.setPreferredSize(new Dimension(900,210));
        configurarTabla();
        tablaUsuarios.setPreferredSize(new Dimension(300,300));
        tablaa.setPreferredSize(new Dimension(300,280));
    }
    private void configurarTabla() {
        // los nombres de las columnas
        String[] titulos = {"ID", "Cédula", "Nombre", "Username", "Rol", "Estado"};

        modeloTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Esto evita que el usuario edite la tabla con doble clic
            }
        };
        tablaUsuarios.setModel(modeloTabla);
}
}
