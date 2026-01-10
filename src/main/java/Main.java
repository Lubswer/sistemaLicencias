import org.mindrot.jbcrypt.BCrypt;
import ui.LoginFrame;
import model.*;
import ui.Menu;
import ui.MenuAdmin;

public class Main {
    public static void main(String[] args){
        //new LoginFrame();
        new MenuAdmin(new Usuario()).setVisible(true);

    }
}
