package ui;

import javax.swing.*;

public class Administrador extends JFrame {
    private JButton registrarSolicitanteButton;
    private JButton verificarRequisitosButton;
    private JButton registrarExámenesButton;
    private JButton gestiónDeTrámitesButton;
    private JButton generarLicenciaButton;
    private JButton cerrarSesiónButton;
    private JLabel icono2;
    private JLabel icono1;
    private JLabel RegS;
    private JLabel VerR;
    private JLabel RegE;
    private JLabel Gestr;
    private JLabel GenLic;
    private JLabel CeSe;
    private JPanel pane;
    private JPanel encabezado;
    private JLabel user;
    private JTextField textField5;
    private JTextField textField2;
    private JTextField textField1;
    private JTextField textField6;
    private JTextField textField3;
    private JTextField textField4;
    private JPanel PanelAd;
    private JButton gestiónDeUsuariosButton;
    private JButton reportesYEstadísticasButton;
    private JLabel ReEs;
    private JLabel GesUs;

    public Administrador() {
        setSize(1300, 720);
        setVisible(true);

        setContentPane(PanelAd);
        setTitle("INGRESO SISTEMA DE ENTREGA DE LICENCIAS");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        ImageIcon iconop = new ImageIcon(getClass().getResource("/img/7.png"));
        icono1.setIcon(iconop);
        ImageIcon iconopp = new ImageIcon(getClass().getResource("/img/6.png"));
        icono2.setIcon(iconopp);
        ImageIcon gesus = new ImageIcon(getClass().getResource("/img/8.png"));
        GesUs.setIcon(gesus);
        ImageIcon rees = new ImageIcon(getClass().getResource("/img/9.png"));
        ReEs.setIcon(rees);

    }
}
