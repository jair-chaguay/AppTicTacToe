/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.tictactoev1.clas;

import Trees.Tree;
import java.util.ArrayDeque;
import java.util.Deque;

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
    
    public static Tree<TicTacToe> generateAllValidScenarios(TicTacToe juego) {
        Tree<TicTacToe> scenarios = new Tree<>(juego);
        TicTacToe nuevoJuego;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println("Hola" + String.valueOf(i) + String.valueOf(j));
                nuevoJuego = juego.copyGame();
                if (nuevoJuego.setSimbolo(i,j) == true)
                    scenarios.setChild(new Tree(nuevoJuego));
               //scenarios.setChild(generateAllValidScenarios(nuevoJuego));               
            }
        }
        
        
        return scenarios;
    }
}
