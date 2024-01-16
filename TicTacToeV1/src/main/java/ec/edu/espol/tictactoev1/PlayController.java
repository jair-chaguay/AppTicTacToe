package ec.edu.espol.tictactoev1;

import ec.edu.espol.tictactoev1.clas.GameSimbol;
import ec.edu.espol.tictactoev1.clas.GameState;
import ec.edu.espol.tictactoev1.clas.Jugadorr;
import ec.edu.espol.tictactoev1.clas.MaquinaDificil;
import ec.edu.espol.tictactoev1.clas.MaquinaFacil;
import ec.edu.espol.tictactoev1.clas.MaquinaIntermedia;
import ec.edu.espol.tictactoev1.clas.TicTacToe;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PlayController implements Initializable {

    public TicTacToe juego;
    public static String dificultad = "";

    @FXML
    private ImageView imgJugador1;
    @FXML
    private ImageView imgJugador2;
    @FXML
    private Pane background;
    @FXML
    private ImageView atras;

    private boolean juegoFacil = false;
    private boolean juegoMedio = false;
    private boolean juegoDificil = false;

    public static Jugadorr jugador1 = new Jugadorr("Jugador1", GameSimbol.X);
    public static Jugadorr jugador2 = new Jugadorr("Jugador2", GameSimbol.O);

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        juego = new TicTacToe(jugador1, jugador2);
        crearCuadros();
        iniciarPartida(juego,dificultad);
        System.out.println(juegoFacil);
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

    @FXML
    private void guardarPartida(MouseEvent ev) {
        juego.guardarJuego("partidas.txt");

    }

    public void reanudarGuardado(TicTacToe juegoGuardado) {
        this.juego = juegoGuardado;
        actualizarTablero();
        mostrarResultadoDelJuego();
    }

    private void movFacil(TicTacToe juegoTmp) {
        MaquinaFacil mf = new MaquinaFacil(GameSimbol.O);
        mf.setJuegoActual(juegoTmp);
        mf.movimientosFacil(juegoTmp);
        juegoTmp.cambiarJugador();
        juegoFacil = true;

    }

    private void movMedio(TicTacToe juegoTmp) {
        MaquinaIntermedia mi = new MaquinaIntermedia(GameSimbol.O);
        juegoTmp.realizarMovimiento(mi.movAleatorioMejor(juego));
        juegoTmp.cambiarJugador();
        juegoMedio = true;

    }

    private void movDificil(TicTacToe juegoTmp) {

        MaquinaDificil md = new MaquinaDificil(GameSimbol.O);
        juegoTmp.realizarMovimiento(md.getBestMoveCoordenates());
        juegoTmp.cambiarJugador();
        juegoDificil = true;

    }

    public void iniciarPartida(TicTacToe juego, String nivel) {
        if (nivel.equals("facil")) {
            movFacil(juego);
//            juegoFacil = true;
//            juego.cambiarJugador();
        }
        if (nivel.equals("medio")) {
            movMedio(juego);
        }
        if (nivel.equals("dificil")) {
            movDificil(juego);
        }
//        juego.cambiarJugador();
        actualizarTablero();
        
    }
    
    private void eventPane(Pane p, ImageView imgView) {
        p.setCursor(Cursor.HAND);

        p.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            String[] coordenadas = p.getId().split(",");
            int fila = Integer.parseInt(coordenadas[0]);
            int columna = Integer.parseInt(coordenadas[1]);

            if (juego.getGameState() == GameState.NO_WINNER) {
                if (juego.setSimbolo(fila, columna)) {
                    juego.cambiarJugador();

                    asignarEquisOCirculo(imgView, obtenerImagenParaJugadorActual());
                    juego.verificarEstadoJuego();
                    mostrarResultadoDelJuego();
                    
                    //FACIL
                    if (juegoFacil) {
                        if (juego.getGameState() == GameState.NO_WINNER) {
                            MaquinaFacil mf = new MaquinaFacil(GameSimbol.O);
                            mf.setJuegoActual(juego);
                            mf.movimientosFacil(juego);
                            actualizarTablero();
                            juego.cambiarJugador();
                            mostrarResultadoDelJuego();
                        }
                    }
                    
                    //MEDIO
                    if (juegoMedio) {
                        if (juego.getGameState() == GameState.NO_WINNER) {
                            //
                        }
                    }
                    
                    //DIFICIL
                    if (juegoDificil) {
                        if (juego.getGameState() == GameState.NO_WINNER) {
                            MaquinaDificil md = new MaquinaDificil(GameSimbol.O);
                            juego.realizarMovimiento(md.getBestMoveCoordenates());
                            juego.realizarMovimiento();
                            actualizarTablero();
                            juego.cambiarJugador();
                            mostrarResultadoDelJuego();
                        }
                    }

                } else {
                    System.out.println("Movimiento no válido. Intente nuevamente.");
                }
            }
        });
    }


    @FXML
    private void inicio(MouseEvent evt) {
        if (!juego.isPartidaGuardada()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("¿Desea salir sin guardar la partida?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                juegoFacil = false;
                juegoMedio = false;
                juegoDificil = false;
                dificultad = "";
                try {                   
                    App.setRoot("Inicio");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                alert.close();
            }           
        } else {
            juegoFacil = false;
            juegoMedio = false;
            juegoDificil = false;
            dificultad = "";
            try {
                App.setRoot("Inicio");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void ocultar() {

        atras.setVisible(false);
    }

    
    private String obtenerImagenParaJugadorActual() {
        if (juego.getJugadorActual().getSimbolo() == GameSimbol.X) {
            juego.cambiarJugador();

            return "Equis.png";
        } else {
            juego.cambiarJugador();
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
        if (juego.getGameState() == GameState.WIN_X || juego.getGameState() == GameState.WIN_O) {
            juego.cambiarJugador();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("¡" + juego.getJugadorActual().getNombre() + " ha ganado!");
            alert.show();
        } else if (juego.getGameState() == GameState.DRAW) {
            System.out.println("¡El juego ha terminado en empate!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("¡El juego ha terminado en empate!");
            alert.show();
        }
    }

    private void actualizarTablero() {
        GameSimbol[][] tablero = juego.getTablero();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String id = i + "," + j;
                Pane pane = (Pane) background.lookup("#" + id);
                if (tablero[i][j] == GameSimbol.X) {
                    asignarEquisOCirculo((ImageView) pane.getChildren().get(0), "Equis.png");
                } else if (tablero[i][j] == GameSimbol.O) {
                    asignarEquisOCirculo((ImageView) pane.getChildren().get(0), "Circulo.png");
                }
            }
        }
    }

}
