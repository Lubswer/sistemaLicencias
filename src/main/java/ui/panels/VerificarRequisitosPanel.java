package ui.panels;

import model.Tramite;
import model.Solicitante;
import service.TramiteService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Period;

public class VerificarRequisitosPanel extends JPanel {

    private JPanel panelRequisitos;
    private JTextField ingresarCedula;
    private JCheckBox certificadoMédicoCheckBox;
    private JCheckBox pagoRealizadoCheckBox;
    private JCheckBox sinMultasCheckBox;
    private JPanel panelsito;
    private JPanel verificaciones;
    private JButton aceptarButton;
    private JButton rechazarButton;
    private JButton buscarButton;
    private JTextArea observaciones;

    // Labels que YA existen en tu form
    private JLabel lblCedula;
    private JLabel lblNombre;
    private JLabel lblEdad;
    private JLabel lblTipoLicencia;

    private int idTramiteActual = -1;

    public VerificarRequisitosPanel() {

        setLayout(new BorderLayout());
        panelRequisitos.setPreferredSize(new Dimension(900,600));
        add(panelRequisitos, BorderLayout.CENTER);

        panelsito.setPreferredSize(new Dimension(600,400));
        verificaciones.setPreferredSize(new Dimension(100,100));
        aceptarButton.setPreferredSize(new Dimension(60,30));
        rechazarButton.setPreferredSize(new Dimension(60,30));
        ingresarCedula.setPreferredSize(new Dimension(60,25));
        observaciones.setPreferredSize(new Dimension(100,60));


        buscarButton.addActionListener(e -> {
            String cedula = ingresarCedula.getText().trim();

            if (cedula.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese una cédula");
                return;
            }

            Tramite t = TramiteService.buscarTramitePendientePorCedula(cedula);

            if (t == null) {
                JOptionPane.showMessageDialog(this,
                        "No existe trámite pendiente para esa cédula");
                idTramiteActual = -1;
                limpiarDatos();
                return;
            }


            idTramiteActual = t.getIdTramite();

            Solicitante s = TramiteService.buscarPorCedula(cedula);

            lblCedula.setText(s.getCedulaSolicitante());
            lblNombre.setText(s.getNombreSolicitante());

            int edad = calcularEdad(s.getFechaNacimientoSolicitante());
            lblEdad.setText(String.valueOf(edad));

            lblTipoLicencia.setText(t.getTipoLicencia());
        });

        aceptarButton.addActionListener(e -> procesar());
        rechazarButton.addActionListener(e -> procesar());
    }

    private void procesar() {

        if (idTramiteActual == -1) {
            JOptionPane.showMessageDialog(this,
                    "Primero busque un trámite");
            return;
        }

        TramiteService.verificacionR(
                idTramiteActual,
                certificadoMédicoCheckBox.isSelected(),
                pagoRealizadoCheckBox.isSelected(),
                sinMultasCheckBox.isSelected(),
                observaciones.getText()
        );

        JOptionPane.showMessageDialog(this, "Requisitos guardados");

        idTramiteActual = -1;
        limpiarDatos();
    }

    private void limpiarDatos() {
        lblCedula.setText("");
        lblNombre.setText("");
        lblEdad.setText("");
        lblTipoLicencia.setText("");
    }

    private int calcularEdad(String fechaNacimiento) {
        LocalDate fecha = LocalDate.parse(fechaNacimiento);
        return Period.between(fecha, LocalDate.now()).getYears();
    }
}
