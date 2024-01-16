package ec.edu.espol.tictactoev1.clas;

import Trees.Tree;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaquinaIntermedia extends Jugadorr {

    private Tree<TicTacToe> brain;
    private TicTacToe juegoActual;
    private Random rd;
    MaquinaDificil maqDif;

    public MaquinaIntermedia(GameSimbol simbol) {
        super(simbol);
        this.setNombre("Maquina intermedia");
        this.rd = new Random();
        this.maqDif = new MaquinaDificil(simbol);

    }

    public void setJuegoActual(TicTacToe juegoActual) {
        this.juegoActual = juegoActual;
    }

    public Tree<TicTacToe> getBrain() {
        return brain;
    }

    public void setBrain(Tree<TicTacToe> brain) {
        this.brain = brain;
    }

    //Forma teniendo las coordenaddas
    public String[] movAleatorioMejor(TicTacToe juego) {

        if (rd.nextBoolean()) {
            maqDif.setJuegoActual(juego);
            return maqDif.getBestMoveCoordenates();
        } else {
            decisionAleatoria(juego);
        }
        return null;
    }

    //Haciendo con lo propuesto en el metodo MovimientosMedio
    public void movimientos(TicTacToe juego) {
        if (rd.nextBoolean()) {
            //decision
            maqDif.setJuegoActual(juego);
            return maqDif.getBestMoveCoordenates();
        } else {
            movimientosMedio(juego);
        }
    }

    private String[] decisionAleatoria(TicTacToe juego) {
        List<String> movimientosDisp = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (juego.getGameSimbol(i, j) == GameSimbol.NONE) {
                    movimientosDisp.add(i + "," + j); //añade las posiciones disponibles
                }
            }
        }
        if (!movimientosDisp.isEmpty()) {
            int indiceAleatorio = rd.nextInt(movimientosDisp.size());
            return movimientosDisp.get(indiceAleatorio).split(",");

        } else {
            return null;
        }
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
