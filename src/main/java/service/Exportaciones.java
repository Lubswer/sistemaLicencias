package service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class Exportaciones {

    public static void exportarLicenciaPDF(
            String numeroLicencia,
            String nombre,
            String cedula,
            String tipoLicencia,
            String fechaEmision,
            String fechaVencimiento
    ) throws FileNotFoundException, DocumentException {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Guardar Licencia PDF");

            if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
                return;
            }

            String ruta = chooser.getSelectedFile().getAbsolutePath() + ".pdf";

            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(ruta));
            document.open();

            Font titulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Font texto = new Font(Font.FontFamily.HELVETICA, 12);

            document.add(new Paragraph("LICENCIA DE CONDUCCIÓN\n\n", titulo));

            document.add(new Paragraph("Número de Licencia: " + numeroLicencia, texto));
            document.add(new Paragraph("Nombre: " + nombre, texto));
            document.add(new Paragraph("Cédula: " + cedula, texto));
            document.add(new Paragraph("Tipo de Licencia: " + tipoLicencia, texto));
            document.add(new Paragraph("Fecha de Emisión: " + fechaEmision, texto));
            document.add(new Paragraph("Fecha de Vencimiento: " + fechaVencimiento, texto));

            document.close();


    }

    public static void exportarCSV(JTable table) throws Exception {

        DefaultTableModel model = (DefaultTableModel) table.getModel();

        if (model.getRowCount() == 0) {
            throw new Exception("Error, la tabla está vacía");
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Guardar como CSV");

        if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = chooser.getSelectedFile();
        String sep = ";"; // separador para Excel en español

        try (
                FileOutputStream fos = new FileOutputStream(file + ".csv");
                OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                BufferedWriter bw = new BufferedWriter(osw)
        ) {
            // BOM para que Excel lea bien UTF-8 (tildes)
            bw.write("\uFEFF");

            // Encabezados
            for (int i = 0; i < model.getColumnCount(); i++) {
                bw.write(escapeCSV(model.getColumnName(i), sep));
                if (i < model.getColumnCount() - 1) bw.write(sep);
            }
            bw.write("\n");

            // Filas
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    Object value = model.getValueAt(row, col);
                    String text = (value == null) ? "" : value.toString();

                    bw.write(escapeCSV(text, sep));
                    if (col < model.getColumnCount() - 1) bw.write(sep);
                }
                bw.write("\n");
            }
        }
    }

    private static String escapeCSV(String value, String sep) {
        if (value == null) return "";

        // Excel se vuelve loco con saltos de línea dentro de celdas
        value = value.replace("\r", " ").replace("\n", " ");

        boolean mustQuote =
                value.contains(sep) || value.contains("\"") || value.contains(",");

        if (mustQuote) {
            value = value.replace("\"", "\"\"");
            return "\"" + value + "\"";
        }
        return value;
    }

}
