/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.tictactoev1;

import ec.edu.espol.tictactoev1.clas.GameSimbol;
import ec.edu.espol.tictactoev1.clas.Jugadorr;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author alexc
 */
public class DificultadController implements Initializable {

    /**
     * Initializes the controller class.
     */
    // fx:controller="ec.edu.espol.tictactoev1.DificultadController"
    @FXML
    private Button btnFacil;

    @FXML
    private Button btnMedio;

    @FXML
    private Button btnDificil;

    @FXML
    private RadioButton radioPrimero;

    @FXML
    private RadioButton radioSegundo;

    @FXML
    private ToggleGroup eleccion;

    public static Jugadorr jugador1 = new Jugadorr();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        btnFacil.setDisable(true);
        btnMedio.setDisable(true);
        btnDificil.setDisable(true);

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
    private void activarBotones(MouseEvent evt) {
        boolean isSelected = radioPrimero.isSelected() || radioSegundo.isSelected();
        btnFacil.setDisable(!isSelected);
        btnMedio.setDisable(!isSelected);
        btnDificil.setDisable(!isSelected);

        if (isSelected) {
            if (radioPrimero.isSelected()) {
                jugador1 = new Jugadorr(GameSimbol.X);
            } else if (radioSegundo.isSelected()) {
                jugador1 = new Jugadorr(GameSimbol.O);
            }
        }
    }

}
