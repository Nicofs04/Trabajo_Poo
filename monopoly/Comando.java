package monopoly;

import java.util.ArrayList;

import partida.Avatar;
import partida.Jugador;

public interface Comando {

    public void crearJugador(String[] palabras) throws Excepciones_EmpezarPartida;

    public void jugador();

    public void listarJugadores();

    public void listarAvatares();

    public void listarEdificios();

    public void lanzarDados() throws Excepciones_JugadorLanz;

    public void acabarTurno();

    public void salirCarcel() throws Excepciones_JugadorSalCar;

    public void descCasilla(String nombreCasilla) throws Excepciones_DescCas;

    public void descJugador(String nombreJugador) throws Excepciones_DescJug;

    public void descAvatar(String[] idAvatar) throws Excepciones_DescAv;

    public void comprar(String nombreCasilla) throws Excepciones_PropComprar;

    public void listarVenta();

    public void edificar(String tipo) throws Excepciones_PropConstruir;

    public int hipotecar(Jugador jugador, Tablero tablero);

    public int deshipotecar(Jugador jugador, Tablero tablero);

    public void vender(String tipo, String nombreCasilla, int numeroventa) throws Excepciones_PropVenderEdif;

    public void estadisticasJuego();

    public void estadisticasJugador(String nombre);

    public void cambiar(String[] palabras);

    public void trato();

    public void listarTratos();
    
    public void bancarrotaAJugador(Jugador actual, Jugador receptor, ArrayList<Jugador> jugadores, ArrayList<Avatar> avatares);

    public void bancarrotaABanca(Jugador actual, Jugador banca, ArrayList<Jugador> jugadores, ArrayList<Avatar> avatares);
}