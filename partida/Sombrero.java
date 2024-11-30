package partida;

import java.util.ArrayList;

import monopoly.Casilla;
import partida.Avatar;

public abstract class Sombrero extends Avatar{


    public Sombrero(Jugador jugador, Casilla lugar, ArrayList<Avatar> avCreados){
        super(jugador, lugar, avCreados);
    }

    
}
