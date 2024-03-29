
import ec.edu.espol.tictactoev1.clas.GameSimbol;
import ec.edu.espol.tictactoev1.clas.GameState;
import ec.edu.espol.tictactoev1.clas.Jugadorr;
import ec.edu.espol.tictactoev1.clas.MaquinaDificil;
import ec.edu.espol.tictactoev1.clas.TicTacToe;

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
        MaquinaDificil md = new MaquinaDificil(GameSimbol.O);

        Jugadorr j1 = new Jugadorr("j1", GameSimbol.X);
        Jugadorr j2 = new Jugadorr("j2", GameSimbol.O);

        TicTacToe juego = new TicTacToe(j1,j2);
//        juego.setSimbolo(0,0); //x
//        juego.setSimbolo(0,2); //o
//        juego.setSimbolo(0,1); //x
//        juego.setSimbolo(1,1); //o
//        juego.setSimbolo(2,1); //x
//        juego.setSimbolo(0,1); //o
        System.out.println(juego.getGameState());
        juego.mostrarTablero();
        md.setJuegoActual(juego);
        
        while (juego.getGameState() == GameState.NO_WINNER) {
            if (md.getSimbolo() == GameSimbol.X) {
                juego.realizarMovimiento(md.getBestMoveCoordenates());
                juego.realizarMovimiento();
            } else {
                juego.realizarMovimiento();
                juego.realizarMovimiento(md.getBestMoveCoordenates());
            }
        }
//        while (juego.getGameState() == GameState.NO_WINNER) {
//            
//            
//            juego.realizarMovimiento();
//            juego.realizarMovimiento(md.getBestMoveCoordenates());
//        }
        
        
        
        
        
//        juego.realizarMovimiento();
//        juego.mostrarTablero();

//        md.setJuegoActual(juego);
//        String[] move = md.getBestMoveCoordenates();
//        for (String s : move) {
//            System.out.println(s);
//        }


//        md.setBrain(MaquinaDificil.generateAllValidScenarios(juego, GameState.WIN_X));
//        System.out.println(md.getBrain().countLeafs());
//
//        List<TicTacToe> list = md.getBestScenarios();
//        System.out.println(list.size());
//
//        List<ArrayDeque<TicTacToe>> a = md.pathToScenarios(list);
        
        
//        System.out.println(a.size());
        
//        a.get(1).pollLast();
//        a.get(9).peekLast().mostrarTablero();

//        for (ArrayDeque<TicTacToe> ad : a) {
//            ad.peekLast().mostrarTablero();
//            System.out.println(ad.size());
//            System.out.println(ad.peekFirst().getGameState());
//        }

//        for (ArrayDeque<TicTacToe> ad : a) {
//            while(!ad.isEmpty()) {
//                ad.pollLast().mostrarTablero();
//            }
//            System.out.println("--------------------*");
//        }

//        for (TicTacToe t: list) {
//            t.mostrarTablero();
//            System.out.println(t.getGameState());
//        }

//        System.out.println(md.allDrawScenarios(list));
        
        
    }
}
