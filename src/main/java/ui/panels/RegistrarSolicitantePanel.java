package ui.panels;

import javax.swing.*;
import java.awt.*;

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

    public RegistrarSolicitantePanel(){
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



    }

}
