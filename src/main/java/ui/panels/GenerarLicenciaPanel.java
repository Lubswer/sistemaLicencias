package ui.panels;
import javax.swing.*;
import java.awt.*;

public class GenerarLicenciaPanel extends JPanel {
    private JPanel panelGenerarLicencia;
    private JLabel iconLicencia;
    private JButton buscarBTN;
    private JTextField cedulaBuscar;
    private JLabel iconBuscar;
    private JButton generarLicenciaButton;
    private JButton exportanPDFButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel nombreLB;
    private JLabel cedulaLB;
    private JLabel tipoLicenciaLB;
    private JLabel estadoLB;
    private JLabel fechaSolicitudLB;
    private JLabel numeroLicenciaLB;
    private JLabel fechaEmisionLB;
    private JLabel fechaVencimientoLB;

    public GenerarLicenciaPanel(){
        setLayout(new BorderLayout());
        panelGenerarLicencia.setPreferredSize(new Dimension(900, 600));
        add(panelGenerarLicencia, BorderLayout.CENTER);
        ImageIcon iconoLicencia = new ImageIcon(getClass().getResource("/img/iconGenerarLicencia.png"));
        iconLicencia.setIcon(iconoLicencia);
        ImageIcon iconLupa = new ImageIcon(getClass().getResource("/img/lupa.png"));
        iconBuscar.setIcon(iconLupa );
    }
}
