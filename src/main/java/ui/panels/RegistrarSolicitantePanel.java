package ui.panels;
import model.Usuario;
import service.TramiteService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarSolicitantePanel extends JPanel {

    private JPanel panelRegistro;
    private JLabel titulo;
    private JLabel icono;
    private JTextField cedulaTF;
    private JTextField nombreTF;
    private JTextField fechaTF;
    private JComboBox tipoLicencia;
    private JButton guardarBTN;
    private JButton limpiarBTN;

    public RegistrarSolicitantePanel(Usuario usuario){
        setLayout(new BorderLayout());
        panelRegistro.setPreferredSize(new Dimension(900, 600));
        add(panelRegistro, BorderLayout.CENTER);
        ImageIcon iconSolicitante = new ImageIcon(getClass().getResource("/img/iconSolicitante.png"));
        icono.setIcon(iconSolicitante);
        cedulaTF.setBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY)
        );
        nombreTF.setBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY)
        );

        fechaTF.setBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY)
        );

        guardarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = cedulaTF.getText();
                String nombre = nombreTF.getText();
                String fechaN = fechaTF.getText();
                String licencia = tipoLicencia.getSelectedItem().toString();
                try{
                    TramiteService.solicitante(cedula, nombre, fechaN, licencia, usuario);
                    JOptionPane.showMessageDialog(null,"Usuario Registrado Correctamente");
                }catch (IllegalArgumentException iae){
                    JOptionPane.showMessageDialog(null,iae.getMessage());
                }
            }
        });
        limpiarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cedulaTF.setText("");
                nombreTF.setText("");
                fechaTF.setText("");
                tipoLicencia.setSelectedItem("");
            }
        });



    }

}
