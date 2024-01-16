/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.tictactoev1;

import ec.edu.espol.tictactoev1.clas.GameSimbol;
import ec.edu.espol.tictactoev1.clas.Jugadorr;
import ec.edu.espol.tictactoev1.clas.TicTacToe;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DificultadController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        btnFacil.setDisable(true);
        btnMedio.setDisable(true);
        btnDificil.setDisable(true);

    }

    @FXML
    private void inicio(MouseEvent evt) {
        try {
            App.setRoot("Inicio");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void nivelFacil(MouseEvent evt) {
        
        PlayController.dificultad = "facil";

        try {
            App.setRoot("Play");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void nivelMedio(MouseEvent evt) {
        PlayController.dificultad = "medio";

        try {
            App.setRoot("Play");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void nivelDificil(MouseEvent evt) {

        PlayController.dificultad = "dificil";
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
                PlayController.turno = "primero";
                PlayController.jugador2.setNombre("Maquina");
            } else if (radioSegundo.isSelected()) {
                PlayController.turno = "segundo";
                PlayController.jugador1.setNombre("Maquina");

            }
        }
    }

}
