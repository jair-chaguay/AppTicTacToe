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
        this.brain = new Tree<>();
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
    
    public static Tree<TicTacToe> generateAllValidScenarios(TicTacToe juego, GameState ContraryGameState) {
        Tree<TicTacToe> scenarios = new Tree<>(juego);
        TicTacToe nuevoJuego;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {               
                nuevoJuego = juego.copyGame();
                nuevoJuego.verificarEstadoJuego();
                boolean movRealizado = nuevoJuego.setSimbolo(i,j);
                if ((movRealizado && nuevoJuego.getGameState() != ContraryGameState))
                    scenarios.setChild(generateAllValidScenarios(nuevoJuego, ContraryGameState));
               //scenarios.setChild(generateAllValidScenarios(nuevoJuego));               
            }
        }
        return scenarios;
    }
    
    public List<TicTacToe> getBestScenarios() {
        List<TicTacToe> leafs = brain.getLeafs();
        ArrayList<TicTacToe> result = new ArrayList<>();
        for (TicTacToe t : leafs) {
            if ((t.getGameState() == getGameStateOfGameSimbol(this.getSimbolo()) || t.getGameState() == GameState.DRAW) && !result.contains(t) ) {
                result.add(t);
            }
        }
        return result;
    }
    
    public List<ArrayDeque<TicTacToe>> pathToScenarios(List<TicTacToe> scenarios) {
        ArrayList<ArrayDeque<TicTacToe>> result = new ArrayList<>();
        ArrayDeque<TicTacToe> path;
        for (TicTacToe game : scenarios) {
            path = brain.pathTo(game);
            if (!path.isEmpty()) {
                result.add(path);
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
