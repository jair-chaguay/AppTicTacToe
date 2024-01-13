
import ec.edu.espol.tictactoev1.clas.GameSimbol;
import ec.edu.espol.tictactoev1.clas.Jugadorr;
import ec.edu.espol.tictactoev1.clas.MaquinaDificil;
import ec.edu.espol.tictactoev1.clas.TicTacToe;
import java.util.List;

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
        juego.mostrarTablero();
        
//        System.out.println(MaquinaDificil.generateAllValidScenarios(juego).countLeafs());

        juego.setSimbolo(0,0);
        juego.setSimbolo(1,1);
        List<TicTacToe> list = MaquinaDificil.generateAllValidScenarios(juego).getLeafs();
        for (TicTacToe t : list) {
            t.mostrarTablero();
        }
    }
}
