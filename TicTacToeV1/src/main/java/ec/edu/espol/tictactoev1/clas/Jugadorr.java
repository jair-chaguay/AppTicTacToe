/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.tictactoev1.clas;

/**
 *
 * @author alexc
 */
public class Jugadorr {
    private String nombre;
    private GameSimbol simbolo;

    public Jugadorr(String nombre, GameSimbol simbolo) {
        this.nombre = nombre;
        this.simbolo = simbolo;
    }

    public Jugadorr(GameSimbol simbolo) {
        this.simbolo = simbolo;
    }

    public Jugadorr() {
        // Constructor vacío
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public GameSimbol getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(GameSimbol simbolo) {
        this.simbolo = simbolo;
    }
}
