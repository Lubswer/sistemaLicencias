package ui;

public class MenuAdmin extends Menu{
    public MenuAdmin() {
        super();
        visibilidad();
        setTitle("Menu Administrador");

    }
    @Override
    protected void visibilidad(){
        UsuariosButton.setVisible(true);
        GesUs.setVisible(true);
        EstadisticasButton.setVisible(true);
        ReEs.setVisible(true);

    }
}
