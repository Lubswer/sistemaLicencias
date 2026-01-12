package ui.panels;

import model.*;
import service.AdminService;
import service.Exportaciones;

import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportesEstadisticasPanel extends JPanel {
    private JPanel reportesPanel;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JComboBox cmbEstado;
    private JComboBox cmbTipoLicencia;
    private JTextField txtCedula;
    private JButton limpiarBTN;
    private JButton buscarBTN;
    private JButton exportarCómoCSVButton;
    private JButton verDetalleBTN;
    private JTable tableReportes;
    private JLabel Ntramites;
    private JLabel Npendientes;
    private JLabel Nexamenes;
    private JLabel Naprobados;
    private JLabel Nreprobados;
    private JLabel Nemitidas;

    public ReportesEstadisticasPanel(){
        setLayout(new BorderLayout());
        reportesPanel.setPreferredSize(new Dimension(900, 600));
        add(reportesPanel, BorderLayout.CENTER);
        initTable();
        Ntramites.setVisible(false);
        Npendientes.setVisible(false);
        Nexamenes.setVisible(false);
        Naprobados.setVisible(false);
        Nreprobados.setVisible(false);
        Nemitidas.setVisible(false);

// / //////////////////////////////////////////////////////////////////////////////////
        buscarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    // Leer filtros desde la UI
                    String fechaInicio = txtFechaInicio.getText();
                    String fechaFin = txtFechaFin.getText();
                    String estado = cmbEstado.getSelectedItem().toString();
                    String tipoLicencia = cmbTipoLicencia.getSelectedItem().toString();
                    String cedula = txtCedula.getText();

                    // Llamar al service
                    List<ReporteTramiteRow> lista = AdminService.buscarTramitesReporte(
                            txtFechaInicio.getText(),
                            txtFechaFin.getText(),
                            cmbEstado.getSelectedItem().toString(),
                            cmbTipoLicencia.getSelectedItem().toString(),
                            txtCedula.getText()
                    );

                    // Limpiar tabla
                    DefaultTableModel model = (DefaultTableModel) tableReportes.getModel();
                    model.setRowCount(0);

                    // Llenar tabla
                    for (ReporteTramiteRow r : lista) {
                        model.addRow(new Object[]{
                                r.getIdTramite(),
                                r.getCedula(),
                                r.getNombre(),
                                r.getTipoLicencia(),
                                r.getEstado(),
                                r.getFechaSolicitud(),
                                r.getFechaEmision()
                        });
                    }
                    // Calcular estadísticas desde la lista
                    int total = lista.size();
                    int pendientes = 0;
                    int enExamenes = 0;
                    int aprobados = 0;
                    int reprobados = 0;
                    int licenciasEmitidas = 0;

                    for (ReporteTramiteRow t : lista) {
                        switch (t.getEstado()) {
                            case "PENDIENTE" -> pendientes++;
                            case "EN_EXAMENES" -> enExamenes++;
                            case "APROBADO" -> aprobados++;
                            case "REPROBADO" -> reprobados++;
                            case "LICENCIA_EMITIDA" -> licenciasEmitidas++;
                        }
                    }

                    // Mostrar estadísticas
                    Ntramites.setText(String.valueOf(total));
                    Npendientes.setText(String.valueOf(pendientes));
                    Nexamenes.setText(String.valueOf(enExamenes));
                    Naprobados.setText(String.valueOf(aprobados));
                    Nreprobados.setText(String.valueOf(reprobados));
                    Nemitidas.setText(String.valueOf(licenciasEmitidas));

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
// / //////////////////////////////////////////////////////////////////////////////////
        exportarCómoCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try  {
                    Exportaciones.exportarCSV(tableReportes);
                    JOptionPane.showMessageDialog(null, "CSV exportado correctamente");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        verDetalleBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ntramites.setVisible(true);
                Npendientes.setVisible(true);
                Nexamenes.setVisible(true);
                Naprobados.setVisible(true);
                Nreprobados.setVisible(true);
                Nemitidas.setVisible(true);
            }
        });
        limpiarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ntramites.setVisible(false);
                Npendientes.setVisible(false);
                Nexamenes.setVisible(false);
                Naprobados.setVisible(false);
                Nreprobados.setVisible(false);
                Nemitidas.setVisible(false);
                txtFechaInicio.setText("");
                txtFechaFin.setText("");
                txtCedula.setText("");
                DefaultTableModel model = (DefaultTableModel) tableReportes.getModel();
                model.setRowCount(0);

            }
        });

    }
    private void initTable() {

        DefaultTableModel model = new DefaultTableModel(
                new Object[]{
                        "ID Trámite",
                        "Cédula",
                        "Nombre",
                        "Tipo Licencia",
                        "Estado",
                        "Fecha Solicitud",
                        "Fecha Emisión"
                }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tabla solo lectura
            }
        };

        tableReportes.setModel(model);

        tableReportes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        tableReportes.setBackground(Color.WHITE);
        tableReportes.setForeground(Color.BLACK);
        tableReportes.setGridColor(Color.LIGHT_GRAY);
        tableReportes.setFillsViewportHeight(true); // Rellena el color aunque no haya datos

        TableColumnModel cm = tableReportes.getColumnModel();
        cm.getColumn(0).setPreferredWidth(70);   // ID
        cm.getColumn(1).setPreferredWidth(120);  // Cédula
        cm.getColumn(2).setPreferredWidth(207);  // Nombre
        cm.getColumn(3).setPreferredWidth(90);   // Tipo
        cm.getColumn(4).setPreferredWidth(150);  // Estado
        cm.getColumn(5).setPreferredWidth(130);  // Fecha solicitud
        cm.getColumn(6).setPreferredWidth(130);  // Fecha emisión
    }


}
