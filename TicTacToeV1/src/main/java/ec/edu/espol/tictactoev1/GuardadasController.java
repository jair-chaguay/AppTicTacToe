
package ec.edu.espol.tictactoev1;

import ec.edu.espol.tictactoev1.clas.GameSimbol;
import ec.edu.espol.tictactoev1.clas.Jugadorr;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author alexc
 */
public class GuardadasController implements Initializable {

@FXML
    private ScrollPane medioSP;
@FXML
    private Label labelCantidad;


    public static Jugadorr jugador1 = new Jugadorr();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        

    }

    @FXML
    private void jugar(MouseEvent evt) {
        try {
            App.setRoot("Play");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void inicio(MouseEvent evt) {
        try {
            App.setRoot("Inicio");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
