package ui;
import model.Usuario;

import javax.swing.*;
import java.awt.*;

public class MenuAdmin extends Menu{
    public MenuAdmin(Usuario usuarioLogueado) {
        super(usuarioLogueado);
        visibilidad();
        setTitle("Menu Administrador");

    }
    @Override
    protected void visibilidad(){
        UsuariosButton.setVisible(true);
        GesUs.setVisible(true);
        EstadisticasButton.setVisible(true);
        ReEs.setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UsuariosButton.setBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 3, Color.white)
        );
        EstadisticasButton.setBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 3, Color.white)
        );

    }
}
