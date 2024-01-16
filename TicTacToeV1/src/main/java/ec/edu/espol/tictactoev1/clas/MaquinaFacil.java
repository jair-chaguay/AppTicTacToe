package ec.edu.espol.tictactoev1.clas;

import Trees.Tree;
import java.util.List;
import java.util.Random;

public class MaquinaFacil extends Jugadorr {

    public MaquinaFacil(GameSimbol simbol) {
        super(simbol);
        this.setNombre("Maquina facil");

    }

    public void movimientosFacil(TicTacToe juego) {
        List<int[]> movimientosDisponibles = juego.movimientosDisponibles();

        if (!movimientosDisponibles.isEmpty()) {
            Random random = new Random();
            int indiceAleatorio = random.nextInt(movimientosDisponibles.size());
            int[] movimiento = movimientosDisponibles.get(indiceAleatorio);
            juego.setSimbolo(movimiento[0], movimiento[1]);
            juego.cambiarJugador();
        }
    }

}
