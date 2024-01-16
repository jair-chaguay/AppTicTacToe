package ec.edu.espol.tictactoev1.clas;

import java.util.List;
import java.util.Random;

public class MaquinaFacil extends Jugadorr {

    private TicTacToe juegoActual;

    public MaquinaFacil(GameSimbol simbol) {
        super(simbol);
        this.setNombre("Maquina facil");

    }

    public TicTacToe getJuegoActual() {
        return juegoActual;
    }

    public void setJuegoActual(TicTacToe juegoActual) {
        this.juegoActual = juegoActual;
    }

    public void movimientosFacil(TicTacToe juego) {
        List<int[]> movimientosDisponibles = juego.movimientosDisponibles();

        if (!movimientosDisponibles.isEmpty()) {
            Random random = new Random();
            int indiceAleatorio = random.nextInt(movimientosDisponibles.size());
            int[] movimiento = movimientosDisponibles.get(indiceAleatorio);
            juego.setSimbolo(movimiento[0], movimiento[1]);

            juego.cambiarJugador();
 
     

        } else {
            System.out.println("No hay movimientos disponibles");
        }
    }
    
    

}
