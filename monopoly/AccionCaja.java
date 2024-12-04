package monopoly;


import partida.*;
import java.util.ArrayList;
import java.util.Random;


public class AccionCaja extends Accion {


    public AccionCaja(String nombre, int posicion, Jugador duenho){
        super(nombre, posicion, duenho);

    }

    public void evaluarCasilla(Tablero tablero, Jugador actual, Jugador banca, int tirada, Juego juego){
        //Barajar cartas
        ArrayList<Integer> baraja2=new ArrayList<Integer>();
        baraja2= crearBaraja();
        barajar(baraja2);
        //Elegir carta
        System.out.println("Qué carta desea elegir?");
        int carta2 = Integer.parseInt(consola.leer());
        while(carta2>6 || carta2<1){
            System.out.println("Introduce un número del 1 al 6");
            carta2 = Integer.parseInt(consola.leer());
        }
        int eleccion2=0;
        eleccion2=baraja2.get(carta2);

        
        //BARAJA ALEATORIA
        Random random= new Random();
        carta2 = random.nextInt(6)+1;

        //Realizar acción
        Caja caja= new Caja(carta2,actual.getAvatar().getLugar().getPosicion());
        caja.accion(tablero, juego, actual);
    
    }
    
}