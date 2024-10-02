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

    /* Constructor principal. Requiere estos parámetros:
    * Tipo del avatar, jugador al que pertenece, lugar en el que estará ubicado, y un arraylist con los
    * avatares creados (usado para crear un ID distinto del de los demás avatares).
     */
    public Avatar(String tipo, Jugador jugador, Casilla lugar, ArrayList<Avatar> avCreados) {
        this.tipo = tipo;
        this.jugador = jugador;
        this.lugar = lugar;
        generarId(avCreados);
    }

    // Método que permite mover a un avatar a una casilla concreta.
    public void moverAvatar(ArrayList<Casilla> casillas, int valorTirada) {
    // Obtiene la posición actual del lugar del avatar
    int posicionActual = lugar.getPosicion(); // Supone que 'lugar' tiene un método getPosicion()

    // Calcula la nueva posición después de mover
    int nuevaPosicion = (posicionActual + valorTirada) % 40; // Usa el módulo para asegurarte de que vuelva al inicio si excede 39

    // Busca la nueva casilla correspondiente a la nueva posición usando un bucle tradicional
    for (int i = 0; i < casillas.size(); i++) {
        Casilla casilla = casillas.get(i); // Accede a la casilla usando el índice

        if (casilla.getPosicion() == nuevaPosicion) {
            lugar = casilla; // Asigna el nuevo lugar basado en la nueva posición
            break; // Sale del bucle al encontrar la casilla correspondiente
        }
    }

    // Aquí podrías agregar lógica adicional, como efectos al aterrizar en una casilla específica.
}


    /* Método que permite generar un ID para un avatar. Solo lo usamos en esta clase (por ello es privado).
    * El ID generado será una letra mayúscula. Parámetros:
    * - Un arraylist de los avatares ya creados, con el objetivo de evitar que se generen dos ID iguales.
     */
    private void generarId(ArrayList<Avatar> avCreados) {
        Random random = new Random();
        char id;

        while (true) {
            // Genera un ID aleatorio entre 'A' y 'Z'
            id = (char) ('A' + random.nextInt(26)); // 'A' es el 65 en ASCII

            boolean idExistente = false; // Inicializamos la verificación de existencia

            // Comprobamos si el ID ya existe en los avatares creados usando un bucle for clásico
            for (int i = 0; i < avCreados.size(); i++) {
                if (avCreados.get(i).getId().equals(String.valueOf(id))) {
                    idExistente = true; // Si existe, marcamos la bandera
                    break; // Salimos del bucle
                }
            }

            // Si el ID no existe, lo asignamos al avatar actual
            if (!idExistente) {
                this.id = String.valueOf(id); // Asigna el ID al atributo de la clase
                break; // Salimos del bucle
            }
        }
    }

    // Método para obtener el ID del avatar
    public String getId() {
        return id; // Devuelve el ID del avatar
    }
}


//hola