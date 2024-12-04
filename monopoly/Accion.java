package monopoly;
import partida.*;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Accion extends Casilla {

    public Accion(String nombre, int posicion, Jugador duenho){
        super(nombre,posicion,duenho);
    }

    public static ArrayList<Integer> crearBaraja() {
        ArrayList<Integer> baraja = new ArrayList<>();

        // Llenar el ArrayList con los valores 1, 2, 3, 4, 5 y 6
        for (int i = 1; i < 7; i++) {
            baraja.add(i);
        }

        return baraja;
    }

    public void barajar(ArrayList<Integer> baraja){
        Collections.shuffle(baraja);
    }
    
}
