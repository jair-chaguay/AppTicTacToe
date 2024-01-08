/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.tictactoev1;

import ec.edu.espol.tictactoev1.clas.GameSimbol;
import ec.edu.espol.tictactoev1.clas.GameState;
import ec.edu.espol.tictactoev1.clas.Jugadorr;
import ec.edu.espol.tictactoev1.clas.TicTacToe;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author alexc
 */
public class PlayController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private TicTacToe juego;
    @FXML
    ImageView imgws;

    @FXML
    private Pane background;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Image a = new Image(App.pathImages + "Circulo.png");
        imgws.setImage(a);
        Jugadorr jugador1 = new Jugadorr("Jugador1", GameSimbol.X);
        Jugadorr jugador2 = new Jugadorr("Jugador2", GameSimbol.O);
        juego = new TicTacToe(jugador1, jugador2);
        crearCuadros();
    }

    @FXML
    private void crearCuadros() {
        double size = 92.0;
        double margin = 6;
        double layoutX = 6;
        double layoutY = 6;
        String colorCuadro = "#05001e";

        for (int i = 0; i < 3; i++) {
            layoutX = margin;
            for (int j = 0; j < 3; j++) {
                Pane p = new Pane();
                p.setId("" + i + "," + j);
                System.out.println(p.getId());
                p.setPrefSize(size, size);
                ImageView imgView = new ImageView();
                imgView.setFitWidth(size);
                imgView.setFitHeight(size);
                p.getChildren().add(imgView);
                p.setLayoutX(layoutX);
                p.setLayoutY(layoutY);
                p.setStyle("-fx-background-color: " + colorCuadro + ";");
                eventPane(p, imgView);
                background.getChildren().add(p);
                layoutX += (size + margin);
            }
            layoutY += (size + margin);
        }
    }

    private void eventPane(Pane p, ImageView imgView) {
        p.setCursor(Cursor.HAND);

        p.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            String[] coordenadas = p.getId().split(",");
            int fila = Integer.parseInt(coordenadas[0]);
            int columna = Integer.parseInt(coordenadas[1]);

            if (juego.getGameState() == GameState.NO_WINNER) {
                if (juego.setSimbolo(fila, columna)) {
                    asignarEquisOCirculo(imgView, obtenerImagenParaJugadorActual());
                    juego.cambiarJugador();
                    juego.verificarEstadoJuego();
                    mostrarResultadoDelJuego();
                } else {
                    System.out.println("Movimiento no válido. Intente nuevamente.");
                }
            }
        });
    }

    private String obtenerImagenParaJugadorActual() {
        if (juego.getJugadorActual().getSimbolo() == GameSimbol.X) {
            return "Equis.png";
        } else {
            return "Circulo.png";
        }
    }

    private void asignarEquisOCirculo(ImageView imgv, String imagen) {
        try (FileInputStream input = new FileInputStream(App.pathImages + imagen)) {
            Image image = new Image(input);
            imgv.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarResultadoDelJuego() {
        // Ejemplo de cómo mostrar el resultado del juego
        if (juego.getGameState() == GameState.WIN_X || juego.getGameState() == GameState.WIN_O) {
            System.out.println("¡" + juego.getJugadorActual().getNombre() + " ha ganado!");
        } else if (juego.getGameState() == GameState.DRAW) {
            System.out.println("¡El juego ha terminado en empate!");
        }
    }
}
