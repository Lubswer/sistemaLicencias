package ui;
import model.Usuario;
import ui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private Usuario usuarioLogueado;
    private JPanel PanelAn;
    private JPanel menu;
    private JButton SolicitanteButton;
    private JButton cerrarButton;
    private JButton ExamenesButton;
    private JButton TramitesButton;
    protected JButton UsuariosButton;
    protected JButton EstadisticasButton;
    private JButton RequisitosButton;
    private JButton LicenciaButton;
    private JLabel icono1;
    private JLabel icono2;
    private JLabel RegS;
    private JLabel VerR;
    private JLabel RegE;
    private JLabel Gestr;
    private JLabel GenLic;
    protected JLabel GesUs;
    protected JLabel ReEs;
    private JLabel CeSe;
    private JPanel encabezado;
    private JLabel user;
    private JPanel panelCentral;
    private JPanel panelCambio;

    public Menu(Usuario usuarioLogueado) {
        setContentPane(PanelAn);
        setTitle("INGRESO SISTEMA DE ENTREGA DE LICENCIAS");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 720);
        ImageIcon regSolicitante = new ImageIcon(getClass().getResource("/img/1.png"));
        RegS.setIcon(regSolicitante);
        ImageIcon verRegistro = new ImageIcon(getClass().getResource("/img/2.png"));
        VerR.setIcon(verRegistro);
        ImageIcon regExamen = new ImageIcon(getClass().getResource("/img/2ss.png"));
        RegE.setIcon(regExamen);
        ImageIcon gestramite = new ImageIcon(getClass().getResource("/img/3.png"));
        Gestr.setIcon(gestramite);
        ImageIcon genLicencia = new ImageIcon(getClass().getResource("/img/4.png"));
        GenLic.setIcon(genLicencia);
        ImageIcon cerrar = new ImageIcon(getClass().getResource("/img/5.png"));
        CeSe.setIcon(cerrar);
        ImageIcon usuario = new ImageIcon(getClass().getResource("/img/usuario.png"));
        user.setIcon(usuario);
        ImageIcon ico = new ImageIcon(getClass().getResource("/img/7.png"));
        icono1.setIcon(ico);
        ImageIcon iconopp = new ImageIcon(getClass().getResource("/img/6.png"));
        icono2.setIcon(iconopp);
        ImageIcon gesus = new ImageIcon(getClass().getResource("/img/8.png"));
        GesUs.setIcon(gesus);
        ImageIcon rees = new ImageIcon(getClass().getResource("/img/9.png"));
        ReEs.setIcon(rees);
        SolicitanteButton.setBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 3, Color.white)
        );
        cerrarButton.setBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 3, Color.white)
        );
        ExamenesButton.setBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 3, Color.white)
        );
        RequisitosButton.setBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 3, Color.white)
        );
        LicenciaButton.setBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 3, Color.white)
        );
        TramitesButton.setBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 3, Color.white)
        );
        visibilidad();

        SolicitanteButton.addActionListener( e -> {
            cambiarPanelCentral(new RegistrarSolicitantePanel());
        });



    }
    protected void visibilidad(){
        //para que se oculten los botones que son para el admin
        UsuariosButton.setVisible(false);
        GesUs.setVisible(false);
        EstadisticasButton.setVisible(false);
        ReEs.setVisible(false);

    }
    protected void cambiarPanelCentral(JPanel nuevoPanel) {
        panelCentral.removeAll();              // quita lo anterior
        panelCentral.setLayout(new BorderLayout());
        panelCentral.add(nuevoPanel, BorderLayout.CENTER);
        panelCentral.revalidate();              // recalcula layout
        panelCentral.repaint();                 // repinta
    }

}
