package ui.panels;

import model.Solicitante;
import model.Tramite;
import service.TramiteService;

import javax.swing.*;
import java.awt.*;

public class RegistrarExamenesPanel extends JPanel {
    private JPanel panelExamen;
    private JTextField cedula;
    private JSpinner teoria;
    private JSpinner practica;
    private JPanel busqueda;
    private JPanel notas;
    private JButton guardarResultadosButton;
    private JPanel guardar;
    private JLabel imagen;
    private JButton buscarButton;
    private JLabel lupa;
    private JLabel lblNombre;
    private JLabel lblPromedio;
    private JButton promedio;

    private Tramite tramiteActual;

    public RegistrarExamenesPanel(){
        setLayout(new BorderLayout());
        panelExamen.setPreferredSize(new Dimension(900,600));
        add(panelExamen,BorderLayout.CENTER);
        notas.setPreferredSize(new Dimension(900,200));
        guardar.setPreferredSize(new Dimension(900,100));
        practica.setPreferredSize(new Dimension(60,30));
        teoria.setPreferredSize(new Dimension(60,30));
        cedula.setPreferredSize(new Dimension(60,30));
        busqueda.setPreferredSize(new Dimension(900,200));
        ImageIcon rex = new ImageIcon(getClass().getResource("/img/Rexamenes.png"));
        imagen.setIcon(rex);
        ImageIcon lupita = new ImageIcon(getClass().getResource("/img/lupa.png"));
        lupa.setIcon(lupita);

        teoria.setEnabled(false);
        practica.setEnabled(false);
        guardarResultadosButton.setEnabled(false);

        buscarButton.addActionListener(e -> buscarSolicitante());
        guardarResultadosButton.addActionListener(e -> guardarResultados());
        promedio.addActionListener(e -> verPromedio());

    }
    private void buscarSolicitante(){
        String cedul=cedula.getText().trim();

        if(cedul.isEmpty()){
            JOptionPane.showMessageDialog(this,"Debe ingresar una cédula");
            return;
        }
        Solicitante solicitante;
        try{
            solicitante= TramiteService.buscarPorCedula(cedul);
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return;
        }
        if(solicitante == null){
            JOptionPane.showMessageDialog(this, "Solicitante no encontrado");
            return;
        }

        tramiteActual =TramiteService.buscarTramiteEnExamenesPorCedula(cedul);

        if(tramiteActual==null){
            JOptionPane.showMessageDialog(this, "No hay tramite pendiente");
            return;
        }

        lblNombre.setText(solicitante.getNombreSolicitante());

        teoria.setEnabled(true);
        practica.setEnabled(true);
        guardarResultadosButton.setEnabled(true);
        promedio.setEnabled(true);
    }
    private void verPromedio() {

        double notaTeoria = Double.parseDouble(teoria.getValue().toString());
        double notaPractica = Double.parseDouble(practica.getValue().toString());

        double promedio = (notaTeoria + notaPractica) / 2;

        lblPromedio.setText(String.format("%.2f", promedio));
    }
    private void guardarResultados() {

        if (tramiteActual == null) {
            JOptionPane.showMessageDialog(this, "Debe buscar un trámite primero");
            return;
        }

        try {

            double notaTeoria = ((Number) teoria.getValue()).doubleValue();
            double notaPractica = ((Number) practica.getValue()).doubleValue();

            double promedio = TramiteService.registrarExamenes(
                    tramiteActual.getIdTramite(),
                    notaTeoria,
                    notaPractica
            );

            lblPromedio.setText(String.format("%.2f", promedio));

            JOptionPane.showMessageDialog(this, "Resultados guardados correctamente");


            teoria.setValue(0);
            practica.setValue(0);
            teoria.setEnabled(false);
            practica.setEnabled(false);
            guardarResultadosButton.setEnabled(false);
            lblNombre.setText("");
            lblPromedio.setText("");
            tramiteActual = null;

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar resultados");
        }

    }




}
