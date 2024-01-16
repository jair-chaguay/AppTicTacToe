package ec.edu.espol.tictactoev1.clas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicTacToe implements Serializable {
    

    private GameSimbol[][] tablero;
    private GameState gameState;
    private Jugadorr jugador1;
    private Jugadorr jugador2;
    private Jugadorr jugadorActual;
    private boolean partidaGuardada;
   

    public TicTacToe(Jugadorr jugador1, Jugadorr jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.jugadorActual = jugador1;
        this.tablero = new GameSimbol[3][3];

        // Inicializar el tablero con GameSimbol.NONE
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = GameSimbol.NONE;
            }
        }

        this.gameState = GameState.NO_WINNER;

    }

    public void resetTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = GameSimbol.NONE;
            }
        }
        gameState = GameState.NO_WINNER;
    }

    public boolean setSimbolo(int i, int j) {
        if (gameState == GameState.NO_WINNER) {
            if (esMovimientoValido(i, j)) {
                tablero[i][j] = jugadorActual.getSimbolo() != null ? jugadorActual.getSimbolo() : GameSimbol.NONE;
                cambiarJugador();
                verificarEstadoJuego();
                return true;
            }
        }
        return false;
    }

    public void verificarEstadoJuego() {
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2] && tablero[i][0] != GameSimbol.NONE) {
                gameState = getGameStateOfGameSimbol(tablero[i][0]);
                return;
            }
            if (tablero[0][i] == tablero[1][i] && tablero[1][i] == tablero[2][i] && tablero[0][i] != GameSimbol.NONE) {
                gameState = getGameStateOfGameSimbol(tablero[0][i]);
                return;
            }
        }

        if (tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2] && tablero[0][0] != GameSimbol.NONE) {
            gameState = getGameStateOfGameSimbol(tablero[0][0]);
            return;
        }
        if (tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0] && tablero[0][2] != GameSimbol.NONE) {
            gameState = getGameStateOfGameSimbol(tablero[0][2]);
            return;
        }

        boolean hayCasillasDisponibles = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == GameSimbol.NONE) {
                    hayCasillasDisponibles = true;
                    break;
                }
            }
        }

        if (!hayCasillasDisponibles) {
            gameState = GameState.DRAW;
        }
    }

    private GameState getGameStateOfGameSimbol(GameSimbol gs) {
        if (gs == GameSimbol.X) {
            return GameState.WIN_X;
        }
        return GameState.WIN_O;
    }

    public Jugadorr getJugadorActual() {
        return jugadorActual;
    }

    static int c = 0;
    public void cambiarJugador() {
        System.out.println(c++);
        if (jugadorActual == jugador1) {
            jugadorActual = jugador2;
        } else {
            jugadorActual = jugador1;
        }
    }

    public void mostrarTablero() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public void realizarMovimiento() {
        Scanner scanner = new Scanner(System.in);
        int fila, columna;

        do {
            System.out.println(jugadorActual.getNombre() + ", ingrese la fila (0-2) y columna (0-2) separadas por espacio:");
            while (!scanner.hasNextInt()) {
                manejarError("Entrada no válida. Intente nuevamente.");
                scanner.next();
            }
            fila = scanner.nextInt();
            while (!scanner.hasNextInt()) {
                manejarError("Entrada no válida. Intente nuevamente.");
                scanner.next();
            }
            columna = scanner.nextInt();
        } while (!esMovimientoValido(fila, columna));

        setSimbolo(fila, columna);
        mostrarTablero();
        verificarEstadoJuego();
    }

    public void realizarMovimiento(String[] movimiento) {
        int fila = Integer.parseInt(movimiento[0]);
        int columna = Integer.parseInt(movimiento[1]);
        setSimbolo(fila, columna);
        mostrarTablero();
        verificarEstadoJuego();
    }

    boolean esMovimientoValido(int fila, int columna) {
        if (fila >= 0 && fila < 3 && columna >= 0 && columna < 3 && tablero[fila][columna] == GameSimbol.NONE) {
            return true;
        } else {
            //manejarError("Movimiento no válido. Intente nuevamente.");
            return false;
        }
    }

    public List<int[]> movimientosDisponibles() {
        List<int[]> movimientos = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (esMovimientoValido(i, j)) {
                    movimientos.add(new int[]{i, j});
                }
            }
        }

        return movimientos;
    }

    private void manejarError(String mensaje) {
        System.out.println("Error: " + mensaje);
    }

    void finalizarJuego() {
        mostrarTablero();
        if (gameState == GameState.WIN_X || gameState == GameState.WIN_O) {
            if (jugadorActual == null) {
                System.out.println("¡" + "Maquina" + " ha ganado!");
            } else {
                System.out.println("¡" + "Jugador1" + " ha ganado!");
            }

        } else if (gameState == GameState.DRAW) {
            System.out.println("¡El juego ha terminado en empate!");
        }
    }

    public void reiniciarJuego() {
        resetTablero();
        jugadorActual = jugador1;
        gameState = GameState.NO_WINNER;
        System.out.println("¡Nuevo juego comenzado!");
        mostrarTablero();
    }

    //LLENA  EL TXT
    public void guardarJuego(String archivo) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/" + archivo, true))) {
            if (jugador1.getNombre() == null) {
                writer.write("Maquina" + "/" + jugador1.getSimbolo() + "/");
                writer.write(jugador2.getNombre() + "/" + jugador2.getSimbolo() + "/");
            } else if (jugador2.getNombre() == null) {
                writer.write(jugador1.getNombre() + "/" + jugador1.getSimbolo() + "/");
                writer.write("Maquina" + "/" + jugador2.getSimbolo() + "/");
            } else {
                writer.write(jugador1.getNombre() + "/" + jugador1.getSimbolo() + "/");
                writer.write(jugador2.getNombre() + "/" + jugador2.getSimbolo() + "/");
            }
            for (GameSimbol[] fila : tablero) {
                for (GameSimbol casilla : fila) {
                    writer.write(casilla.toString() + "/");
                }
            }
            writer.write(jugadorActual.getNombre() + "/");
            writer.write(gameState.toString());       
            writer.newLine();
            writer.close();

            System.out.println("Juego guardado en: " + archivo);
            partidaGuardada = true;

        } catch (IOException e) {
            System.out.println("error de guardar");
        }

    }

    public boolean isPartidaGuardada() {
        return partidaGuardada;
    }

    public TicTacToe copyGame() {
        Jugadorr j1 = new Jugadorr(GameSimbol.X);
        Jugadorr j2 = new Jugadorr(GameSimbol.O);
        Jugadorr jActual = new Jugadorr();
        TicTacToe nuevoJuego = new TicTacToe(j1, j2);
        if (this.jugador1.equals(this.jugadorActual)) {
            nuevoJuego.setJugadorActual(j1);
        } else {
            nuevoJuego.setJugadorActual(j2);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                nuevoJuego.getTablero()[i][j] = this.getTablero()[i][j];
            }
        }
        nuevoJuego.verificarEstadoJuego();
        return nuevoJuego;
    }

    public GameSimbol[][] getTablero() {
        return tablero;
    }

    public void setGameSimbolInTablero(int i, int j, GameSimbol gameSimbol) {
        this.tablero[i][j] = gameSimbol;
    }

    public GameSimbol getGameSimbol(int i, int j) {
        return tablero[i][j];
    }

    public Jugadorr getJugador1() {
        return jugador1;
    }

    public Jugadorr getJugador2() {
        return jugador2;
    }

    public void setTablero(GameSimbol[][] tablero) {
        this.tablero = tablero;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setJugador1(Jugadorr jugador1) {
        this.jugador1 = jugador1;
    }

    public void setJugador2(Jugadorr jugador2) {
        this.jugador2 = jugador2;
    }

    public void setJugadorActual(Jugadorr jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TicTacToe)) {
            return false;
        }

        TicTacToe other = (TicTacToe) o;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.getTablero()[i][j] != other.getTablero()[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
