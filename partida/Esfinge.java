package partida;

import java.util.ArrayList;
import monopoly.Casilla;
import monopoly.Juego;


public class Esfinge extends Avatar{


    public Esfinge(Jugador jugador, Casilla lugar, ArrayList<Avatar> avCreados){
        super(jugador, lugar, avCreados);
    }


    //Implementamos auxiliarmente el movAvanzado de esfinge porque si no no se puede instanciar por ser una clase abstracta.
    public void moverAvanzado(ArrayList<ArrayList<Casilla>> tablero, int valorTirada, Juego menu){
        consola.imprimir("Movimiento no implementado");



    }
    
}