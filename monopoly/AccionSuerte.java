package monopoly;
import partida.Jugador;


public class AccionSuerte extends Accion {


    public AccionSuerte(String nombre, int posicion, Jugador duenho){
        super(nombre, posicion, duenho);

    }


    public void evaluarCasilla(Tablero tablero, Jugador actual, Jugador banca, int tirada, Juego juego){
        //Barajar cartas
        //ArrayList<Integer> baraja2=new ArrayList<Integer>();
        //baraja2= crearBaraja();
        //barajar(baraja2);
        //Elegir carta
        System.out.println("Qué carta desea elegir?");
        int carta2 = Integer.parseInt(consola.leer());
        while(carta2>6 || carta2<1){
            System.out.println("Introduce un número del 1 al 6");
            carta2 = Integer.parseInt(consola.leer());
        }
        //int eleccion2=0;
        //eleccion2=baraja2.get(carta2);
        //Realizar acción
        Suerte suerte=new Suerte(carta2,actual.getAvatar().getLugar().getPosicion());
        suerte.accion(tablero, juego, actual);
    
    }
    
}



