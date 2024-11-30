package monopoly;

import java.util.Scanner;
import partida.*;
import monopoly.*;

public class AccionCaja extends Accion {


    public AccionCaja(String nombre, int posicion, Jugador duenho){
        super(nombre, posicion, duenho);

    }

    public void evaluarCasilla(Tablero tablero, Jugador actual, Jugador banca, int tirada, Juego juego){
        //Barajar cartas
        //ArrayList<Integer> baraja2=new ArrayList<Integer>();
        //baraja2= crearBaraja();
        //barajar(baraja2);
        //Elegir carta
        System.out.println("Qué carta desea elegir?");
        Scanner scanner2 = new Scanner(System.in);
        int carta2 = scanner2.nextInt();
        while(carta2>6 || carta2<1){
            System.out.println("Introduce un número del 1 al 6");
            carta2 = scanner2.nextInt();
        }
        //int eleccion2=0;
        //eleccion2=baraja2.get(carta2);
        //Realizar acción
        Caja caja= new Caja(carta2,actual.getAvatar().getLugar().getPosicion());
        caja.accion(tablero, juego, banca);
    
    }
    
}