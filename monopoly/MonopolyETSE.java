package monopoly;
import partida.Jugador;
import partida.Avatar;
import partida.Dado;

public class MonopolyETSE {

    public static void main(String[] args) {
        //Menu menu = new Menu();
        //System.out.println(menu);
        Jugador banca = new Jugador();
        Tablero tablero = new Tablero(banca);
        System.out.println(tablero);



    }

}
