package ec.edu.espol.tictactoev1;

import ec.edu.espol.tictactoev1.clas.GameSimbol;
import ec.edu.espol.tictactoev1.clas.GameState;
import ec.edu.espol.tictactoev1.clas.Jugadorr;
import ec.edu.espol.tictactoev1.clas.TicTacToe;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;



public class GuardadasController implements Initializable {

    public static List<TicTacToe> juegosGuardados = new ArrayList();
    

    @FXML
    private ScrollPane medioSP;
    @FXML
    private Label labelCantidad;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<String> listaLineas = leeJuegos("partidas.txt");
        juegosGuardados = cargaPartidas(listaLineas);
        System.out.println(juegosGuardados.size());
        crearContenedores(juegosGuardados);
        labelCantidad.setText(String.valueOf(juegosGuardados.size()));
        

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

    private void crearContenedores(List<TicTacToe> lista) {
        VBox contenedorPartidas = new VBox();
        contenedorPartidas.setSpacing(10);

        for (int i = 0; i < lista.size(); i++) {
            
            final int indice = i;

            TicTacToe partida = lista.get(i);
            VBox contenedor = new VBox();
            contenedor.setStyle(
                    "-fx-border-color: black; "
                    + "-fx-border-width: 1px; "
                    + "-fx-padding: 5px; "
                    + "-fx-background-color: white; "
                    + "-fx-spacing: 5px;"
            );
            contenedor.setPrefSize(335, 40); // TAMANO DEL CONTENEDOR 
            contenedor.setAlignment(Pos.CENTER_LEFT); //ALINEACION IZQ CENTRO

            Label etiquetaTitulo = new Label("Partida Guardada #" + (i + 1));
            etiquetaTitulo.setStyle(
                    "-fx-text-fill: gray; "
                    + "-fx-font-weight: bold;"
            );

            Label etiquetaDetalle = new Label(partida.getJugador1().getNombre() + " (" + partida.getJugador1().getSimbolo()
                    + ") vs " + partida.getJugador2().getNombre() + " (" + partida.getJugador2().getSimbolo() + ") - Estado: "
                    + partida.getGameState());
            contenedor.getChildren().addAll(etiquetaTitulo, etiquetaDetalle);
            contenedor.setOnMouseClicked(event -> {
                reanudarPartida(partida, indice);

            });
            contenedorPartidas.getChildren().add(contenedor);
        }

        medioSP.setContent(contenedorPartidas);
    }


    private void reanudarPartida(TicTacToe juegoGuardado, int i) {
        PlayController.numero = i;
        PlayController.guardada = "si";
        try {
            App.setRoot("Play");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<String> leeJuegos(String archivo) {
        List<String> listaCargada = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                listaCargada.add(linea);
            }
        } catch (IOException e) {
            System.out.println("error de carga");
        }

        return listaCargada;

    }

    public List<TicTacToe> cargaPartidas(List<String> listaLineas) {
        List<TicTacToe> lista = new ArrayList<>();

        for (String linea : listaLineas) {
            String[] datos = linea.split("/");

            String nombre1 = datos[0];
            GameSimbol simbolo1 = GameSimbol.valueOf(datos[1]);
            Jugadorr jugador1 = new Jugadorr(nombre1, simbolo1);
            String nombre2 = datos[2];
            GameSimbol simbolo2 = GameSimbol.valueOf(datos[3]);
            Jugadorr jugador2 = new Jugadorr(nombre2, simbolo2);
            TicTacToe partida = new TicTacToe(jugador1, jugador2);
            GameSimbol[][] tablero = new GameSimbol[3][3];
            
            int index = 4;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tablero[i][j] = GameSimbol.valueOf(datos[index]);
                    index++;
                }
            }

            partida.setTablero(tablero);
            if (datos[index].equals(jugador1.getNombre())) {
                partida.setJugadorActual(jugador1);
            } else {
                partida.setJugadorActual(jugador2);
            }
            partida.setGameState(GameState.valueOf(datos[index + 1]));
            lista.add(partida);
           
        }

        return lista;
    }

}
