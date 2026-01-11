package ui.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class ReportesEstadisticasPanel extends JPanel {
    private JPanel reportesPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox estadoCB;
    private JComboBox tipoLicenciaCB;
    private JTextField textField3;
    private JButton limpiarBTN;
    private JButton buscarBTN;
    private JButton exportarCómoCSVButton;
    private JButton exportarCómoExelButton;
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
        cm.getColumn(2).setPreferredWidth(260);  // Nombre
        cm.getColumn(3).setPreferredWidth(90);   // Tipo
        cm.getColumn(4).setPreferredWidth(150);  // Estado
        cm.getColumn(5).setPreferredWidth(130);  // Fecha solicitud
        cm.getColumn(6).setPreferredWidth(130);  // Fecha emisión
    }

}
