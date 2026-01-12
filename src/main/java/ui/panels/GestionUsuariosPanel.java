package ui.panels;

import model.Usuario;
import service.UsuarioService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GestionUsuariosPanel extends JPanel {

    private JTextField txtNombreUsuario;
    private JTextField txtCedulaUsuario;
    private JTextField txtClaveUsuario;
    private JComboBox cmbEstadoUsuario;
    private JComboBox cmbRolUsuario;
    private JTable tablaUsuarios;
    private JTextField txtBuscarUsuarioCedula;

    private JButton guardarCambiosButton;
    private JButton crearUsuarioButton;
    private JButton limpiarButton;
    private JButton cargarButton;
    private JButton actualizarTablaButton;

    private JPanel UsuariosPanel;
    private JPanel panelsito;
    private JPanel tablaa;
    private JLabel img;
    private JTextField txtUserName;

    private DefaultTableModel modeloTabla;

    public GestionUsuariosPanel() {

        UsuariosPanel.setPreferredSize(new Dimension(900, 600));
        setLayout(new BorderLayout());
        add(UsuariosPanel, BorderLayout.CENTER);

        ImageIcon uss = new ImageIcon(getClass().getResource("/img/GUsuarios.png"));
        img.setIcon(uss);

        panelsito.setPreferredSize(new Dimension(900, 210));
        tablaUsuarios.setPreferredSize(new Dimension(800, 300));
        tablaa.setPreferredSize(new Dimension(800, 280));

        configurarTabla();
        configurarAcciones();


        cargarUsuariosEnTabla();
    }

    // ---------------- TABLA ----------------

    private void configurarTabla() {

        String[] titulos = {"ID", "Cédula", "Nombre", "Username", "Rol", "Estado"};

        modeloTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaUsuarios.setModel(modeloTabla);
    }

    private void cargarUsuariosEnTabla() {

        modeloTabla.setRowCount(0);

        List<Usuario> usuarios = UsuarioService.listarUsuarios();

        for (Usuario u : usuarios) {
            modeloTabla.addRow(new Object[]{
                    u.getIdUsuario(),
                    u.getCedula(),
                    u.getNombre(),
                    u.getUsername(),
                    u.getRol(),
                    u.getEstado()
            });
        }
    }

    // ---------------- BOTONES ----------------

    private void configurarAcciones() {

        // ACTUALIZAR TABLA
        actualizarTablaButton.addActionListener(e -> cargarUsuariosEnTabla());

        // LIMPIAR CAMPOS
        limpiarButton.addActionListener(e -> limpiarCampos());

        // CARGAR USUARIO POR CÉDULA
        cargarButton.addActionListener(e -> cargarUsuarioPorCedula());

        // CREAR USUARIO
        crearUsuarioButton.addActionListener(e -> crearUsuario());

        // GUARDAR CAMBIOS
        guardarCambiosButton.addActionListener(e -> actualizarUsuario());
    }

    // ---------------- MÉTODOS ----------------

    private void limpiarCampos() {

        txtCedulaUsuario.setText("");
        txtNombreUsuario.setText("");
        txtClaveUsuario.setText("");
        txtBuscarUsuarioCedula.setText("");

        cmbRolUsuario.setSelectedIndex(0);
        cmbEstadoUsuario.setSelectedIndex(0);
    }

    private void cargarUsuarioPorCedula() {

        String cedula = txtBuscarUsuarioCedula.getText().trim();

        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese una cédula");
            return;
        }

        Usuario u = UsuarioService.buscarPorCedula(cedula);

        if (u == null) {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado");
            return;
        }

        txtCedulaUsuario.setText(u.getCedula());
        txtNombreUsuario.setText(u.getNombre());
        txtUserName.setText(u.getUsername());
        cmbRolUsuario.setSelectedItem(u.getRol());
        cmbEstadoUsuario.setSelectedItem(u.getEstado());
        txtClaveUsuario.setText("");

    }

    private void crearUsuario() {

        try {
            UsuarioService.crearUsuario(
                    txtCedulaUsuario.getText(),
                    txtNombreUsuario.getText(),
                    txtUserName.getText(),
                    txtClaveUsuario.getText(),
                    cmbRolUsuario.getSelectedItem().toString(),
                    cmbEstadoUsuario.getSelectedItem().toString()
            );

            JOptionPane.showMessageDialog(this, "Usuario creado correctamente");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void actualizarUsuario() {

        try {
            UsuarioService.actualizarUsuario(
                    txtCedulaUsuario.getText(),
                    txtNombreUsuario.getText(),
                    txtUserName.getText(),
                    txtClaveUsuario.getText(),
                    cmbRolUsuario.getSelectedItem().toString(),
                    cmbEstadoUsuario.getSelectedItem().toString()
            );


            JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
