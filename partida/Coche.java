package partida;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Scanner;

import monopoly.Casilla;
import monopoly.Juego;
import monopoly.Propiedad;

public class Coche extends Avatar {

    private int compras;// Variable para contabilizar las compras de vivienda que se han realizado con
                        // el mov avanzado de coche
    private int restriccionTiradas; // Cuando se activa se pone a 2, y cada vez que pase un turno se le resta 1
                                    // turno sin tirar restante

    public Coche(Jugador jugador, Casilla lugar, ArrayList<Avatar> avCreados) {
        super(jugador, lugar, avCreados);
        this.compras = 0;
        this.restriccionTiradas = 0;
    }

    public void setCompras(int compras) {
        this.compras = compras;
    }

    public void setRestriccionTiradas(int restriccionTiradas) {
        this.restriccionTiradas = restriccionTiradas;
    }

    public int getCompras() {
        return compras;
    }

    public int getRestriccionTiradas() {
        return restriccionTiradas;
    }

    public void moverAvanzado(ArrayList<ArrayList<Casilla>> tablero, int valorTirada, Juego menu) {
        // Obtener la posición actual del lugar del avatar
        int posicionActual = this.getLugar().getPosicion();
        if (valorTirada > 4) {
            // Avanzamos tantas casillas como valor de la tirada del intento actual
            int nuevaPosicion = (posicionActual + valorTirada) % 40;
            this.getLugar().eliminarAvatar(this);
            for (int i = 0; i < tablero.size(); i++) {
                for (int j = 0; j < tablero.get(i).size(); j++) {
                    if (tablero.get(i).get(j).getPosicion() == nuevaPosicion) {
                        this.setLugar(tablero.get(i).get(j));
                    }
                }
            }
            getLugar().anhadirAvatar(this);
            if (getLugar().estaEnVenta() == true) {
                if (compras <= 1) {
                    consola.imprimir("Quieres comprar la casilla " + getLugar().getNombre() + "?");
                    consola.imprimir("Escribe 'si' si la quieres comprar y 'no' si no la quieres comprar");
                    Scanner scanner = new Scanner(System.in);
                    String respuesta = scanner.nextLine();
                    if (respuesta.equals("si")) {
                        Propiedad propiedad=(Propiedad) getLugar();
                        getJugador().setFortuna(getJugador().getFortuna() - propiedad.getValor());
                        getLugar().setDuenho(getJugador());
                        compras++;
                    }

                } else {
                    consola.imprimir("Ya has comprado una casilla este turno");
                }
            }

            consola.imprimir("Quieres edificar en esta casilla?");
            consola.imprimir("Escribe 'si' si quieres edificar y 'no' si no quieres edificar");
            Scanner scanner = new Scanner(System.in);
            String respuesta = scanner.nextLine();
            if (respuesta.equals("si")) {
                boolean comprobante = false;
                while (comprobante) {
                    Scanner scanner2 = new Scanner(System.in);
                    consola.imprimir("=====================================\n");
                    consola.imprimir("                MENÚ                \n");
                    consola.imprimir("=====================================\n");
                    consola.imprimir("1. Construir una casa                              -> Comando: 'edificar casa'");
                    consola.imprimir("2. Construir un hotel                           -> Comando: 'edificar hotel'");
                    consola.imprimir(
                            "3. Construir una piscina                           -> Comando: 'edificar piscina'");
                    consola.imprimir("4. Construir una pista                           -> Comando: 'edificar pista'");

                    consola.imprimir("=====================================\n");
                    consola.imprimir("Selecciona una opción para continuar.\n");
                    consola.imprimir("=====================================\n\n");

                    String comando = scanner2.nextLine();
                    String[] palabras = comando.split(" ");

                    if (palabras.length <= 1 || palabras.length > 2) {
                        consola.imprimir("Comando inválido.");
                    }
                    String metodo = palabras[0];

                    switch (metodo) {
                        case "edificar":
                            if (palabras.length == 2) {
                                String tipoEdificacion = palabras[1];
                                if (tipoEdificacion.equals("casa")) {
                                    menu.edificarCasa();
                                } else if (tipoEdificacion.equals("hotel")) {
                                    menu.edificarHotel();
                                } else if (tipoEdificacion.equals("piscina")) {
                                    menu.edificarPiscina();
                                } else if (tipoEdificacion.equals("pista")) {
                                    menu.edificarPista();
                                }
                            }
                            break;

                    }

                }
            }

            // El valor de la tirada es 4 o menos, lo mismo que para pelota pero aplicando
            // la restricción
        } else {
            int nuevaPosicion = 0;
            if (menu.getLanzamientos() == 0) {
                // Si no tenemos que pasar por la casilla de salida
                if (posicionActual > valorTirada) {
                    nuevaPosicion = (posicionActual - valorTirada) % 40;
                    getLugar().eliminarAvatar(this);
                    for (int i = 0; i < tablero.size(); i++) {
                        for (int j = 0; j < tablero.get(i).size(); j++) {
                            if (tablero.get(i).get(j).getPosicion() == nuevaPosicion) {
                                setLugar(tablero.get(i).get(j));
                            }
                        }
                    }
                    getLugar().anhadirAvatar(this);
                    // Si tenemos que pasar por la casilla de salida
                } else {
                    // Le restamos al valor de la tirada las posiciones que se tiene que mover para
                    // llegar a la salida
                    valorTirada = valorTirada - posicionActual;
                    // La nueva posición será 40(la salida)- el valor de la tirada actualizado
                    nuevaPosicion = 40 - valorTirada;
                    getLugar().eliminarAvatar(this);
                    for (int i = 0; i < tablero.size(); i++) {
                        for (int j = 0; j < tablero.get(i).size(); j++) {
                            if (tablero.get(i).get(j).getPosicion() == nuevaPosicion) {
                                setLugar(tablero.get(i).get(j));
                            }
                        }
                    }
                    getLugar().anhadirAvatar(this);

                }
                // Marcamos la restriccion que se aplicará en lanzar dados.
                setRestriccionTiradas(2);

            } else {

                // Si no tenemos que pasar por la casilla de salida
                if (posicionActual > valorTirada) {
                    nuevaPosicion = (posicionActual - valorTirada) % 40;
                    getLugar().eliminarAvatar(this);
                    for (int i = 0; i < tablero.size(); i++) {
                        for (int j = 0; j < tablero.get(i).size(); j++) {
                            if (tablero.get(i).get(j).getPosicion() == nuevaPosicion) {
                                setLugar(tablero.get(i).get(j));
                            }
                        }
                    }
                    getLugar().anhadirAvatar(this);
                    // Si tenemos que pasar por la casilla de salida
                } else {
                    // Le restamos al valor de la tirada las posiciones que se tiene que mover para
                    // llegar a la salida
                    valorTirada = valorTirada - posicionActual;
                    // La nueva posición será 40(la salida)- el valor de la tirada actualizado
                    nuevaPosicion = 40 - valorTirada;
                    getLugar().eliminarAvatar(this);
                    for (int i = 0; i < tablero.size(); i++) {
                        for (int j = 0; j < tablero.get(i).size(); j++) {
                            if (tablero.get(i).get(j).getPosicion() == nuevaPosicion) {
                                setLugar(tablero.get(i).get(j));
                            }
                        }
                    }
                    getLugar().anhadirAvatar(this);

                }

            }

        }

    }

}
