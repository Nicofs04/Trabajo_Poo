package partida;

import monopoly.*;
import java.util.ArrayList;
import java.util.Random;

public class Avatar {

    // Atributos
    private String id; // Identificador: una letra generada aleatoriamente.
    private String tipo; // Sombrero, Esfinge, Pelota, Coche
    private Jugador jugador; // Un jugador al que pertenece ese avatar.
    private Casilla lugar; // Los avatares se sitúan en casillas del tablero.

    // Constructor vacío
    public Avatar() {
    }

    /*
     * Constructor principal. Requiere estos parámetros:
     * Tipo del avatar, jugador al que pertenece, lugar en el que estará ubicado, y
     * un arraylist con los
     * avatares creados (usado para crear un ID distinto del de los demás avatares).
     */
    public Avatar(String tipo, Jugador jugador, Casilla lugar, ArrayList<Avatar> avCreados) {
        this.setTipo(tipo);
        this.setJugador(jugador);
        this.setLugar(lugar);
        generarId(avCreados);
        avCreados.add(this);
    }

    // GETTERS

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Casilla getLugar() {
        return lugar;
    }

    // SETTERS

    public void setId(String ID) {
        this.id = ID;
    }

    public void setTipo(String TIPO) {
        this.tipo = TIPO;
    }

    public void setId(Jugador JUGADOR) {
        this.jugador = JUGADOR;
    }

    public void setLugar(Casilla LUGAR) {
        this.lugar = LUGAR;
    }
    public void setJugador(Jugador jugador){
        this.jugador=jugador;
    }

    // Método que permite mover a un avatar a una casilla concreta.
    public void moverAvatar(ArrayList<ArrayList<Casilla>> tablero, int valorTirada){
    
        // Obtener la posición actual del lugar del avatar
        int posicionActual = lugar.getPosicion();
    
        // Calcula la nueva posición después de mover
        int nuevaPosicion = (posicionActual + valorTirada) % 40; // Usar el módulo para asegurarte de que vuelva al inicio si excede 39

        //Iteramos sobre los lados del tablero con el indice i y sobre las casillas de cada lado con el indice j:
        for(int i=0;i<tablero.size();i++){
            for(int j=0;j<tablero.get(i).size();j++){
                if(tablero.get(i).get(j).getPosicion()==nuevaPosicion){
                    lugar=tablero.get(i).get(j);
                }
            }
        }
    }

    /*
     * Método que permite generar un ID para un avatar. Solo lo usamos en esta clase
     * (por ello es privado).
     * El ID generado será una letra mayúscula. Parámetros:
     * - Un arraylist de los avatares ya creados, con el objetivo de evitar que se
     * generen dos ID iguales.
     */
    private void generarId(ArrayList<Avatar> avCreados) {
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

}
