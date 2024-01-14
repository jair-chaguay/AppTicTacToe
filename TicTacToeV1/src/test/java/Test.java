
import Trees.Tree;
import ec.edu.espol.tictactoev1.clas.GameSimbol;
import ec.edu.espol.tictactoev1.clas.GameState;
import ec.edu.espol.tictactoev1.clas.Jugadorr;
import ec.edu.espol.tictactoev1.clas.MaquinaDificil;
import ec.edu.espol.tictactoev1.clas.TicTacToe;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class Test {
    public static void main(String[] args) {
        MaquinaDificil md = new MaquinaDificil(GameSimbol.X);

        Jugadorr j1 = new Jugadorr("j1", GameSimbol.X);
        Jugadorr j2 = new Jugadorr("j2", GameSimbol.O);
        
        TicTacToe juego = new TicTacToe(j1,j2);
        juego.setSimbolo(0,0); //x
        juego.setSimbolo(0,2); //o
        juego.setSimbolo(1,1); //x
//        juego.setSimbolo(1,1); //o
//        juego.setSimbolo(2,1); //x
//        juego.setSimbolo(2,2); //o
       
        juego.mostrarTablero();
        
        
//        System.out.println(MaquinaDificil.generateAllValidScenarios(juego).countLeafs());
       
        Tree<TicTacToe> tree = MaquinaDificil.generateAllValidScenarios(juego, GameState.WIN_X);
//        System.out.println(tree.countLeafs());
        List<TicTacToe> list = md.getBestScenarios(tree, juego, GameState.WIN_O);
        System.out.println(list.size());
        
        for (TicTacToe t: list) {
            t.mostrarTablero();
            System.out.println(t.getGameState());
        }
        
        System.out.println(md.allDrawScenarios(list));
//        System.out.println(c);131184
    }
}
