package monopoly;
import partida.*;
import monopoly.*;
public abstract class Accion extends Casilla {

    public Accion(String nombre, int posicion, Jugador duenho){
        super(nombre,posicion,duenho);
    }
    
}
