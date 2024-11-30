package partida;

import java.util.ArrayList;

import monopoly.Casilla;
import partida.Avatar;

public abstract class Esfinge extends Avatar{


    public Esfinge(Jugador jugador, Casilla lugar, ArrayList<Avatar> avCreados){
        super(jugador, lugar, avCreados);
    }
    
}