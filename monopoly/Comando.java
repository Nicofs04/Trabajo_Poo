package monopoly;

import java.util.ArrayList;

import partida.Avatar;
import partida.Jugador;

public interface Comando {

    public void crearJugador(String[] palabras);

    public void jugador();

    public void listarJugadores();

    public void listarAvatares();

    public void listarEdificios();

    public void lanzarDados();

    public void acabarTurno();

    public void salirCarcel();

    public void descCasilla(String nombreCasilla);

    public void descJugador(String[] nombreJugador);

    public void descAvatar(String[] idAvatar);

    public void comprar(String nombreCasilla);

    public void listarVenta();

    public void edificarCasa();

    public void edificarHotel();
    
    public void edificarPiscina();

    public void edificarPista();

    public int Hacienda(Jugador jugador, Tablero tablero);

    public int deshipotecar(Jugador jugador, Tablero tablero);

    public void venderCasa(String nombreCasilla, int numvender);

    public void venderHotel(String nombreCasilla, int numvender);
    
    public void venderPiscina(String nombreCasilla, int numvender);

    public void venderPista(String nombreCasilla, int numvender);

    public void estadisticasJuego();

    public void estadisticasJugador(String nombre);

    public void cambiar(String[] palabras);
    
    public void bancarrotaAJugador(Jugador actual, Jugador receptor, ArrayList<Jugador> jugadores, ArrayList<Avatar> avatares);

    public void bancarrotaABanca(Jugador actual, Jugador banca, ArrayList<Jugador> jugadores, ArrayList<Avatar> avatares);
}