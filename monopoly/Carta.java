package monopoly;

import partida.*;


public  abstract class Carta{
    private int numCarta;
    private int posicion;

    public static ConsolaNormal consola = new ConsolaNormal();

    public Carta(int numCarta, int posicion){
        this.numCarta=numCarta;
        this.posicion=posicion;
    }

    public int getNumCarta(){
        return this.numCarta;
    }

    public int getPosicion(){
        return this.posicion;
    }

    public void setNumCarta(int numCarta){
        this.numCarta=numCarta;
    }

    public void setPosicion(int posicion){
        this.posicion=posicion;
    }


    public abstract void accion(Tablero tablero,Juego menu,Jugador jugador);
}