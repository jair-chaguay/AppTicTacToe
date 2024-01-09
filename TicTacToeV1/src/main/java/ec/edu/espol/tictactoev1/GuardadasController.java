package ec.edu.espol.tictactoev1;

import ec.edu.espol.tictactoev1.clas.GameSimbol;
import ec.edu.espol.tictactoev1.clas.Jugadorr;
import ec.edu.espol.tictactoev1.clas.TicTacToe;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<String> listaPartidas = TicTacToe.juegosGuardados;
        crearContenedores(listaPartidas);
        labelCantidad.setText(String.valueOf(listaPartidas.size()));

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

    private void crearContenedores(List<String> lista) {
        VBox contenedorPartidas = new VBox();
        contenedorPartidas.setSpacing(10);

        for (int i = 0; i < lista.size(); i++) {
            String partida = lista.get(i);
            String[] datosPartida = partida.split("/");

            String jugador1 = datosPartida[0];
            String simbolo1 = datosPartida[1];
            String jugador2 = datosPartida[2];
            String simbolo2 = datosPartida[3];
            String estado = datosPartida[datosPartida.length - 1];

            VBox contenedorPartida = new VBox();
            Label etiquetaTitulo = new Label("Partida Guardada #" + (i + 1));
            Label etiquetaDetalle = new Label(jugador1 + " (" + simbolo1 + ") vs " + jugador2 + " (" + simbolo2 + ") - Estado: " + estado);

            contenedorPartida.getChildren().addAll(etiquetaTitulo, etiquetaDetalle);
            contenedorPartidas.getChildren().add(contenedorPartida);
        }

        medioSP.setContent(contenedorPartidas);
    }

}
