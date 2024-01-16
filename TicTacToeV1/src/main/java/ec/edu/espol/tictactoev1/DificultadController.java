/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.tictactoev1;

import ec.edu.espol.tictactoev1.clas.GameSimbol;
import ec.edu.espol.tictactoev1.clas.Jugadorr;
import ec.edu.espol.tictactoev1.clas.MaquinaFacil;
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

    public static Jugadorr jugador1 = new Jugadorr();
    public static Jugadorr jugador2 = new Jugadorr();

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
//
//        TicTacToe juego = new TicTacToe(jugador1, jugador2);
//        cargarVista(juego, "facil");
        try {
            App.setRoot("Play");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void nivelMedio(MouseEvent evt) {

        TicTacToe juego = new TicTacToe(jugador1, jugador2);
        cargarVista(juego, "medio");

    }

    @FXML
    private void nivelDificil(MouseEvent evt) {

        TicTacToe juego = new TicTacToe(jugador1, jugador2);
        cargarVista(juego, "dificil");

    }

    private void cargarVista(TicTacToe juego, String nivel) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Play.fxml"));
            Parent root = loader.load();
            PlayController playController = loader.getController();
            playController.iniciarPartida(juego, nivel);
            playController.ocultar();
            Stage currentStage = (Stage) btnFacil.getScene().getWindow();
            currentStage.hide(); 
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                currentStage.show(); 
            });
            stage.show();
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
