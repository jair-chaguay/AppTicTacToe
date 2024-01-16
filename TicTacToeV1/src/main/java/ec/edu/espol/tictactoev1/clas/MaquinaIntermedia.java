package ec.edu.espol.tictactoev1.clas;

import Trees.Tree;
import java.util.List;
import java.util.Random;

public class MaquinaIntermedia extends Jugadorr {

    public MaquinaIntermedia(GameSimbol simbol) {
        super(simbol);
        this.setNombre("Maquina intermedia");

    }

    public void movimientosMedio(TicTacToe juego) {
        List<int[]> movimientosDisponibles = juego.movimientosDisponibles();

        if (!movimientosDisponibles.isEmpty()) {
            Random random = new Random();
            int indiceAleatorio = random.nextInt(movimientosDisponibles.size());
            int[] movimiento = movimientosDisponibles.get(indiceAleatorio);
            int fila = movimiento[0];
            int columna = movimiento[1];
            juego.getTablero()[fila][columna] = this.getSimbolo();
            juego.cambiarJugador();
        }
    }

}
