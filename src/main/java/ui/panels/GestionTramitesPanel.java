package ui.panels;

import model.ReporteTramiteRow;
import model.Tramite;
import model.Solicitante;
import service.TramiteService;
import service.AdminService;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import dao.TramiteDao;

public class GestionTramitesPanel extends JPanel {

    private JTextField textField1;   // cédula
    private JComboBox<String> comboBox1; // estado
    private JTable table1;
    private JButton buscarButton;
    private JButton buscarButton1;
    private JPanel TramitesPanel;
    private JPanel panelsito;
    private JPanel tablaa;
    private JLabel traa;

    // Requisitos
    private JCheckBox certificadoMédicoCheckBox;
    private JCheckBox pagoRealizadoCheckBox;
    private JCheckBox sinMultasCheckBox;
    private JTextField observacionesField;

    // Exámenes
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JLabel lblPromedio;

    // Licencia
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;

    private JButton guardarRequisitosButton;
    private JButton guardarExámenesButton;
    private JButton generarLicenciaButton;
    private JButton promedioButton;


    private int idTramiteSeleccionado = -1;

    public GestionTramitesPanel() {

        TramitesPanel.setPreferredSize(new Dimension(900, 600));
        setLayout(new BorderLayout());
        add(TramitesPanel, BorderLayout.CENTER);

        ImageIcon tra = new ImageIcon(getClass().getResource("/img/GTramites.png"));
        traa.setIcon(tra);

        inicializarTabla();
        cargarEstados();

        buscarButton.addActionListener(e -> cargarTabla());
        buscarButton1.addActionListener(e -> cargarTabla());
        promedioButton.addActionListener(e -> {
            double teoria = ((Number) spinner1.getValue()).doubleValue();
            double practica = ((Number) spinner2.getValue()).doubleValue();
            double promedio = (teoria + practica) / 2;
            lblPromedio.setText(String.format("%.2f", promedio));
        });


        // Cuando se selecciona una fila
        table1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table1.getSelectedRow() != -1) {
                int fila = table1.getSelectedRow();
                idTramiteSeleccionado = Integer.parseInt(
                        table1.getValueAt(fila, 0).toString()
                );
                cargarDetalle();
            }
        });

        guardarRequisitosButton.addActionListener(e -> guardarRequisitos());
        guardarExámenesButton.addActionListener(e -> guardarExamenes());
        generarLicenciaButton.addActionListener(e -> generarLicencia());
    }

    private void inicializarTabla() {
        String[] columnas = {"ID", "CÉDULA", "NOMBRE", "TIPO", "FECHA", "ESTADO"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table1.setModel(modelo);
        table1.setRowHeight(25);
    }

    private void cargarEstados() {
        comboBox1.removeAllItems();
        comboBox1.addItem("TODOS");
        comboBox1.addItem("PENDIENTE");
        comboBox1.addItem("EN_EXAMENES");
        comboBox1.addItem("APROBADO");
        comboBox1.addItem("REPROBADO");
        comboBox1.addItem("LICENCIA_EMITIDA");
    }

    private void cargarTabla() {
        try {
            String cedula = textField1.getText().trim();
            String estado = comboBox1.getSelectedItem().toString();

            DefaultTableModel modelo = (DefaultTableModel) table1.getModel();
            modelo.setRowCount(0);

            List<ReporteTramiteRow> lista = AdminService.buscarTramitesReporte(
                    null,
                    null,
                    estado.equals("TODOS") ? null : estado,
                    null,
                    cedula.isEmpty() ? null : cedula
            );

            for (ReporteTramiteRow r : lista) {
                modelo.addRow(new Object[]{
                        r.getIdTramite(),
                        r.getCedula(),
                        r.getNombre(),
                        r.getTipoLicencia(),
                        r.getFechaSolicitud(),
                        r.getEstado()
                });
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }


    // ------------------ CARGAR DETALLE ------------------

    private void cargarDetalle() {
        try {
            int fila = table1.getSelectedRow();
            int idTramite = Integer.parseInt(table1.getValueAt(fila, 0).toString());

            Tramite t = TramiteService.obtenerTramitePorId(idTramite);
            Solicitante s = TramiteService.buscarPorIdSolicitante(t.getIdSolicitante());


            idTramiteSeleccionado = idTramite;

            // REQUISITOS
            certificadoMédicoCheckBox.setSelected(t.isCertificadoMedico());
            pagoRealizadoCheckBox.setSelected(t.isPagoRealizado());
            sinMultasCheckBox.setSelected(t.isSinMultas());
            observacionesField.setText(t.getObservaciones());

            // EXÁMENES
            double teoria = t.getNotaTeorica() == null ? 0 : t.getNotaTeorica().doubleValue();
            double practica = t.getNotaPractica() == null ? 0 : t.getNotaPractica().doubleValue();

            spinner1.setValue(teoria);
            spinner2.setValue(practica);
            lblPromedio.setText(String.format("%.2f", (teoria + practica) / 2));

            // LICENCIA
            //textField3.setText(t.getNumeroLicencia());
            //textField4.setText(t.getFechaEmision());
            //textField5.setText(t.getFechaVencimiento());

            // BOTONES
            guardarRequisitosButton.setEnabled(true);
            guardarExámenesButton.setEnabled(t.getEstado().equals("EN_EXAMENES"));
            generarLicenciaButton.setEnabled(t.getEstado().equals("APROBADO"));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }




    // ------------------ GUARDAR ------------------

    private void guardarRequisitos() {
        TramiteService.verificacionR(
                idTramiteSeleccionado,
                certificadoMédicoCheckBox.isSelected(),
                pagoRealizadoCheckBox.isSelected(),
                sinMultasCheckBox.isSelected(),
                observacionesField.getText()
        );
        JOptionPane.showMessageDialog(this, "Requisitos guardados");
        cargarTabla();
    }

    private void guardarExamenes() {
        double teoria = ((Number) spinner1.getValue()).doubleValue();
        double practica = ((Number) spinner2.getValue()).doubleValue();

        TramiteService.registrarExamenes(idTramiteSeleccionado, teoria, practica);
        JOptionPane.showMessageDialog(this, "Exámenes guardados");
        cargarTabla();
    }

    private void generarLicencia() {
        try {
            TramiteService.generarLicencia(
                    idTramiteSeleccionado,
                    textField3.getText(),
                    textField4.getText(),
                    textField5.getText(),
                    1, // idUsuario (puedes luego usar el real)
                    "APROBADO"
            );

            TramiteService.cambiarEstadoLicencia("LICENCIA_EMITIDA", idTramiteSeleccionado);

            JOptionPane.showMessageDialog(this, "Licencia generada");
            cargarTabla();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

}



