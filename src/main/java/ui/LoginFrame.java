package ui;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame{

    private JPanel paneLogin;
    private JLabel logoLogin;
    private JPanel cuadroLogin;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton iniciarSesionButton;
    private JLabel iconUsuario;
    private JLabel iconClave;

    public LoginFrame(){
        setSize(1300,720);
        setVisible(true);

        setContentPane(paneLogin);
        setTitle("INGRESO SISTEMA DE ENTREGA DE LICENCIAS");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon logLogin = new ImageIcon(getClass().getResource("/img/logoLogin2.png"));
        logoLogin.setIcon(logLogin);
        ImageIcon iconoUsuario = new ImageIcon(getClass().getResource("/img/usuario.png"));
        iconUsuario.setIcon(iconoUsuario);
        ImageIcon iconoClave = new ImageIcon(getClass().getResource("/img/clave.png"));
        iconClave.setIcon(iconoClave);

        iniciarSesionButton.setBorder(null);

        textField1.setBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY)
        );
        passwordField1.setBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY)
        );

    }



}
