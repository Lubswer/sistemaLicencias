package ui.panels;
import model.Solicitante;
import model.Tramite;
import model.Usuario;
import service.TramiteService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JLabel tipoLicenciaLB;
    private JLabel estadoLB;
    private JLabel fechaSolicitudLB;
    private JLabel numeroLicenciaLB;
    private JLabel fechaEmisionLB;
    private JLabel fechaVencimientoLB;
    private JLabel cedulaLB;

    public GenerarLicenciaPanel(Usuario usuarioLogueado){
        setLayout(new BorderLayout());
        panelGenerarLicencia.setPreferredSize(new Dimension(900, 600));
        add(panelGenerarLicencia, BorderLayout.CENTER);
        ImageIcon iconoLicencia = new ImageIcon(getClass().getResource("/img/iconGenerarLicencia.png"));
        iconLicencia.setIcon(iconoLicencia);
        ImageIcon iconLupa = new ImageIcon(getClass().getResource("/img/lupa.png"));
        iconBuscar.setIcon(iconLupa );

        buscarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = cedulaBuscar.getText();
                try{
                    Solicitante solicitante1 = TramiteService.buscarPorCedula(cedula);
                    Tramite tramiteSolicitante = TramiteService.obtenerTramiteSolicitante(solicitante1.getIdSolicitante());
                    JOptionPane.showMessageDialog(null,"Solicitante encontrado y cargado");
                    nombreLB.setText(solicitante1.getNombreSolicitante());
                    cedulaLB.setText(solicitante1.getCedulaSolicitante());
                    tipoLicenciaLB.setText(tramiteSolicitante.getTipoLicencia());
                    estadoLB.setText( tramiteSolicitante.getEstado());
                    fechaSolicitudLB.setText(tramiteSolicitante.getCreatedAt());

                }
                catch (NumberFormatException nep){
                    JOptionPane.showMessageDialog(null, "Formato de datos incorrecto");
                }
                catch (IllegalArgumentException iae){
                    JOptionPane.showMessageDialog(null,iae.getMessage());
                }catch (Exception  ex ){
                    JOptionPane.showMessageDialog(null,"Error inesperado: " + ex.getMessage());
                }

            }
        });
        generarLicenciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = cedulaLB.getText();
                try{
                    Solicitante solicitante1 = TramiteService.buscarPorCedula(cedula);
                    Tramite tramiteSolicitante = TramiteService.obtenerTramiteSolicitante(solicitante1.getIdSolicitante());
                    TramiteService.generarLicencia(tramiteSolicitante.getIdTramite(),
                            numeroLicenciaLB.getText(),fechaEmisionLB.getText(),
                            fechaVencimientoLB.getText(),usuarioLogueado.getIdUsuario());
                    JOptionPane.showMessageDialog(null,"Licencia Generada Exitosamente!");

                }
                catch (NumberFormatException nep){
                    JOptionPane.showMessageDialog(null, "Formato de datos incorrecto");
                }
                catch (IllegalArgumentException iae){
                    JOptionPane.showMessageDialog(null,iae.getMessage());
                }catch (Exception  ex ){
                    JOptionPane.showMessageDialog(null,"Error inesperado: " + ex.getMessage());
                }

            }
        });

    }
}
