/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.tictactoev1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author alexc
 */
public class InicioController implements Initializable {

    //fx:controller="ec.edu.espol.tictactoev1.InicioController"
    /**
     * Initializes the controller class.
     */
    @FXML
    private Button buttonPlay;
    @FXML
    private Button buttonDifficult;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    

    @FXML
    private void difficult(MouseEvent evt) {
        try {
            App.setRoot("dificultad");
        } catch (IOException ex) {
        }
    }

    @FXML
    private void jugador(MouseEvent evt) {

        try {
            App.setRoot("Play");
            
        } catch (IOException ex) {
        }
    }
}
