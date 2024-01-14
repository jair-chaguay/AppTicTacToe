/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.tictactoev1.clas;

import Trees.Tree;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author User
 */
public class MaquinaDificil extends Jugadorr{
    
    private Tree<TicTacToe> brain;
    private TicTacToe juegoActual;
    
    public MaquinaDificil(GameSimbol simbol) {
        super(simbol);
        this.setNombre("Maquina dificil");
    }
    
    public void setJuegoActual(TicTacToe juegoActual) {
        this.juegoActual = juegoActual;
    }
    
    public static Tree<TicTacToe> generateAllValidScenarios(TicTacToe juego, GameState ContraryGameState) {
        Tree<TicTacToe> scenarios = new Tree<>(juego);
        TicTacToe nuevoJuego;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {               
                nuevoJuego = juego.copyGame();
                nuevoJuego.verificarEstadoJuego();
                if (nuevoJuego.setSimbolo(i,j) == true && nuevoJuego.getGameState() != ContraryGameState)
                    scenarios.setChild(generateAllValidScenarios(nuevoJuego, ContraryGameState));
               //scenarios.setChild(generateAllValidScenarios(nuevoJuego));               
            }
        }        
        return scenarios;
    }
    
    public List<TicTacToe> getBestScenarios(Tree<TicTacToe> scenarios, TicTacToe juego, GameState desiredState) {
        List<TicTacToe> leafs = scenarios.getLeafs();
        ArrayList<TicTacToe> result = new ArrayList<>();
        for (TicTacToe t : leafs) {
            if ((t.getGameState() == desiredState || t.getGameState() == GameState.DRAW) && !result.contains(t) ) {
                result.add(t);
            }
        }
        return result;
    }
    
    public boolean allDrawScenarios(List<TicTacToe> juegos) {
        for (TicTacToe t : juegos) {
            if (t.getGameState() != GameState.DRAW) {
                return false;
            }
        }
        return true;
    }
    
//    public TicTacToe picAGame(List<TicTacToe> juegos) {
//        if (!this.allDrawScenarios(juegos)) {
//            for ()
//        }
//    }
    
    private static GameState getGameStateOfGameSimbol(GameSimbol gs) {
        return gs == GameSimbol.X ? GameState.WIN_X : GameState.WIN_O;
    }
}
