/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.tictactoev1.clas;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexc
 */
public class Maquina extends Jugadorr {

    public Maquina(GameSimbol simbolo) {
        super(simbolo);
        this.setNombre("Maquina");
    }

    public void realizarMovimientoMinimax(TicTacToe juego) {
        int[] mejorMovimiento = minimax(juego, this.getSimbolo());
        juego.setSimbolo(mejorMovimiento[0], mejorMovimiento[1]);
        juego.cambiarJugador();
    }

    private int[] minimax(TicTacToe juego, GameSimbol jugador) {
        List<int[]> movimientosDisponibles = obtenerMovimientosDisponibles(juego);

        int mejorPuntaje = (jugador == this.getSimbolo()) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int mejorMovimientoIndex = -1;

        for (int i = 0; i < movimientosDisponibles.size(); i++) {
            int[] movimiento = movimientosDisponibles.get(i);
            juego.setSimbolo(movimiento[0], movimiento[1]);

            if (jugador == this.getSimbolo()) {
                int puntaje = minimaxRecursivo(juego, 0, false);
                if (puntaje > mejorPuntaje) {
                    mejorPuntaje = puntaje;
                    mejorMovimientoIndex = i;
                }
            } else {
                int puntaje = minimaxRecursivo(juego, 0, true);
                if (puntaje < mejorPuntaje) {
                    mejorPuntaje = puntaje;
                    mejorMovimientoIndex = i;
                }
            }

            juego.setSimbolo(movimiento[0], movimiento[1]);
        }

        return movimientosDisponibles.get(mejorMovimientoIndex);
    }

    private int minimaxRecursivo(TicTacToe juego, int profundidad, boolean esMaximizando) {
        GameState estado = juego.getGameState();

        if (estado == GameState.WIN_X) {
            return -1;
        } else if (estado == GameState.WIN_O) {
            return 1;
        } else if (estado == GameState.DRAW) {
            return 0;
        }

        GameSimbol jugadorMax = this.getSimbolo();
        int mejorPuntaje = (esMaximizando) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        List<int[]> movimientosDisponibles = obtenerMovimientosDisponibles(juego);

        for (int[] movimiento : movimientosDisponibles) {
            juego.setSimbolo(movimiento[0], movimiento[1]);

            int puntaje = minimaxRecursivo(juego, profundidad + 1, !esMaximizando);

            mejorPuntaje = (esMaximizando) ? Math.max(mejorPuntaje, puntaje) : Math.min(mejorPuntaje, puntaje);

            juego.setSimbolo(movimiento[0], movimiento[1]);
        }

        return mejorPuntaje;
    }

    private List<int[]> obtenerMovimientosDisponibles(TicTacToe juego) {
        List<int[]> movimientos = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (juego.esMovimientoValido(i, j)) {
                    movimientos.add(new int[]{i, j});
                }
            }
        }

        return movimientos;
    }
}
