//AVATAR

package partida;

import monopoly.*;
import monopoly.Excepciones.Excepciones_PropConstruir;

import java.util.ArrayList;
import java.util.Random;

public abstract class Avatar {

    // Atributos
    private String id; // Identificador: una letra generada aleatoriamente.
    private Jugador jugador; // Un jugador al que pertenece ese avatar.
    private Casilla lugar; // Los avatares se sitúan en casillas del tablero.
    private int avanzado; // 1 si el movimiento del avatar está en modo avanzado y 0 si no lo está
    public static ConsolaNormal consola = new ConsolaNormal();
    // Constructor vacío
    
    public Avatar(Jugador jugador, Casilla lugar, ArrayList<Avatar> avCreados) {
        this.setJugador(jugador);
        this.setLugar(lugar);
        this.generarId(avCreados);
        this.getLugar().anhadirAvatar(this);
        this.setAvanzado(0);
        avCreados.add(this);
    }

    /*
     * Constructor principal. Requiere estos parámetros:
     * Tipo del avatar, jugador al que pertenece, lugar en el que estará ubicado, y
     * un arraylist con los
     * avatares creados (usado para crear un ID distinto del de los demás avatares).
     */


    // GETTERS

    public String getId() {
        return id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Casilla getLugar() {
        return lugar;
    }

    public int getAvanzado() {
        return avanzado;
    }

    // SETTERS

    public void setId(String ID) {
        this.id = ID;
    }


    public void setId(Jugador JUGADOR) {
        this.jugador = JUGADOR;
    }

    public void setLugar(Casilla LUGAR) {
        this.lugar = LUGAR;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setAvanzado(int avanzado) {
        this.avanzado = avanzado;
    }


    public void moverBasico(ArrayList<ArrayList<Casilla>> tablero, int valorTirada){
        // Obtener la posición actual del lugar del avatar
        int posicionActual = lugar.getPosicion();
    
        // Calcula la nueva posición después de mover
        int nuevaPosicion3 = (posicionActual + valorTirada) % 40; // Usar el módulo para asegurarte de que
                                                                                      // vuelva al inicio si excede 39
        lugar.eliminarAvatar(this);
            
        /*
        * if (nuevaPosicion == 30) {
        * consola.imprimir("Has caído en la carcel.\n");
        * lugar=tablero.get(1).get(0);
        * 
        * }else{
        */
            
        // Iteramos sobre los lados del tablero con el indice i y sobre las casillas de
        // cada lado con el indice j:
        for (int i = 0; i < tablero.size(); i++) {
            for (int j = 0; j < tablero.get(i).size(); j++) {
                if (tablero.get(i).get(j).getPosicion() == nuevaPosicion3) {
                    lugar = tablero.get(i).get(j);
                }
            }
        }
        lugar.anhadirAvatar(this);

    }



    public abstract void moverAvanzado(ArrayList<ArrayList<Casilla>> tablero, int valorTirada, Juego menu);

    /*
     * Método que permite generar un ID para un avatar. Solo lo usamos en esta clase
     * (por ello es privado).
     * El ID generado será una letra mayúscula. Parámetros:
     * - Un arraylist de los avatares ya creados, con el objetivo de evitar que se
     * generen dos ID iguales.
     */
    public void generarId(ArrayList<Avatar> avCreados) {
        Random random = new Random();
        char id;

        while (true) {
            // Genera un ID aleatorio entre 'A' y 'Z'
            id = (char) ('A' + random.nextInt(26)); // 'A' es el 65 en ASCII

            boolean idExistente = false;

            // Comprobamos si el ID ya existe en los avatares
            for (int i = 0; i < avCreados.size(); i++) {
                if (avCreados.get(i).getId().equals(String.valueOf(id))) {
                    idExistente = true;
                    break;
                }
            }

            // Si el ID no existe, lo asignamos al avatar actual
            if (!idExistente) {
                this.id = String.valueOf(id);
                break;
            }
        }
    }



    //Devuelve el tipo del avatar según su objeto
    public String tipoAvatar(){

        if(this instanceof Pelota){
            return "pelota";

        }else if(this instanceof Coche){
            return "coche";


        }else if(this instanceof Sombrero){
            return "sombrero";


        }else if(this instanceof Esfinge){
            return "esfinge";

        }else{
            return "";
        }

    }

}
