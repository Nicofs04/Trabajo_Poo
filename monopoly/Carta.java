package monopoly;

import partida.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Iterator;


public  abstract class Carta{
    private int numCarta;
    private int posicion;

    public static ConsolaNormal consola = new ConsolaNormal();

    public Carta(int numCarta, int posicion){
        this.numCarta=numCarta;
        this.posicion=posicion;
    }

    public int getNumCarta(){
        return this.numCarta;
    }

    public int getPosicion(){
        return this.posicion;
    }

    public void setNumCarta(int numCarta){
        this.numCarta=numCarta;
    }

    public void setPosicion(int posicion){
        this.posicion=posicion;
    }


    private void accion(Tablero tablero,Juego menu,Jugador jugador){


        
    }
}