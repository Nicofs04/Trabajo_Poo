package partida;

import java.util.ArrayList;
import java.util.Scanner;

import monopoly.AccionCaja;
import monopoly.AccionSuerte;
import monopoly.Casilla;
import monopoly.Juego;
import monopoly.Propiedad;
import monopoly.Suerte;
import partida.Avatar;

public class Pelota extends Avatar {



    public Pelota(Jugador jugador, Casilla lugar, ArrayList<Avatar> avCreados, String tipo) {
        this.setJugador(jugador);
        this.setLugar(lugar);
        this.generarId(avCreados);
        this.getLugar().anhadirAvatar(this);
        this.setAvanzado(0);
        avCreados.add(this);

    }
    public void moverAvanzado(ArrayList<ArrayList<Casilla>> tablero, int valorTirada, Juego menu) {
        // Obtener la posición actual del lugar del avatar
        int posicionActual = this.getLugar().getPosicion();

        if (valorTirada > 4) {
            // Movimiento hacia adelante
            int nuevaPosicion = (posicionActual + 4) % 40; // Ajustar con módulo para volver al inicio si excede 39
            this.getLugar().eliminarAvatar(this);

            for (int i = 0; i < tablero.size(); i++) {
                for (int j = 0; j < tablero.get(i).size(); j++) {
                    if (tablero.get(i).get(j).getPosicion() == nuevaPosicion) {
                        this.setLugar(tablero.get(i).get(j)); 
                    }
                }
            }
            this.getLugar().anhadirAvatar(this);

            for (int I = 1; I < (valorTirada - 3); I++) {
                if (I % 2 != 0) {
                    // Índices impares
                    nuevaPosicion = (nuevaPosicion + 1) % 40;
                    this.getLugar().eliminarAvatar(this);
                    for (int k = 0; k < tablero.size(); k++) {
                        for (int l = 0; l < tablero.get(k).size(); l++) {
                            if (tablero.get(k).get(l).getPosicion() == nuevaPosicion) {
                                this.setLugar(tablero.get(k).get(l));
                            }
                        }
                    }
                    this.getLugar().anhadirAvatar(this);

                    // Evaluación de la casilla
                    if (this.getLugar().estaEnVenta()) {
                        consola.imprimir("Quieres comprar la casilla " + this.getLugar().getNombre() + "?");
                        consola.imprimir("Escribe 'si' si la quieres comprar y 'no' si no la quieres comprar");
                        Scanner scanner = new Scanner(System.in);
                        String respuesta = scanner.nextLine();
                        if (respuesta.equals("si")) {
                            Propiedad propiedad= (Propiedad) this.getLugar();
                            propiedad.comprarCasilla(this.getJugador(), menu.getBanca());
                        }
                    } else {
                        if (I != valorTirada - 4) {
                            this.getLugar().evaluarCasilla(menu.getTablero(), this.getJugador(), menu.getBanca(),
                                    valorTirada, menu);

                            if (this.getLugar() instanceof AccionSuerte|| this.getLugar() instanceof AccionCaja) {
                                if (this.getLugar().getPosicion() == 10) {
                                    return; // Salimos si va a la cárcel
                                }
                            }

                            if (this.getLugar().getPosicion() == 30) {
                                consola.imprimir("Has pisado la casilla irCárcel, vas a la cárcel");
                                this.getJugador().encarcelar(tablero);
                                return; // Salimos si va a la cárcel
                            }
                        }
                    }
                } else {
                    // Índices pares
                    if (I == valorTirada - 4) {
                        nuevaPosicion = (nuevaPosicion + 1) % 40;
                        this.getLugar().eliminarAvatar(this);
                        for (int k = 0; k < tablero.size(); k++) {
                            for (int l = 0; l < tablero.get(k).size(); l++) {
                                if (tablero.get(k).get(l).getPosicion() == nuevaPosicion) {
                                    this.setLugar(tablero.get(k).get(l));
                                }
                            }
                        }
                        this.getLugar().anhadirAvatar(this);

                        if (this.getLugar().estaEnVenta()) {
                            consola.imprimir("Quieres comprar la casilla " + this.getLugar().getNombre() + "?");
                            consola.imprimir("Escribe 'si' si la quieres comprar y 'no' si no la quieres comprar");
                            Scanner scanner = new Scanner(System.in);
                            String respuesta = scanner.nextLine();
                            if (respuesta.equals("si")) {
                                Propiedad propiedad=(Propiedad) this.getLugar();
                                propiedad.comprarCasilla(this.getJugador(), menu.getBanca());
                            }
                        }

                    } else {
                        nuevaPosicion = (nuevaPosicion + 1) % 40;
                        this.getLugar().eliminarAvatar(this);
                        for (int k = 0; k < tablero.size(); k++) {
                            for (int l = 0; l < tablero.get(k).size(); l++) {
                                if (tablero.get(k).get(l).getPosicion() == nuevaPosicion) {
                                    this.setLugar(tablero.get(k).get(l));
                                }
                            }
                        }
                        this.getLugar().anhadirAvatar(this);
                    }
                }
            }

        } else {
            // Movimiento hacia atrás
            int nuevaPosicion = posicionActual;
            for (int I = 1; I <= valorTirada; I++) {
                if (I % 2 != 0) {
                    // Índices impares
                    if (nuevaPosicion != 0) {
                        nuevaPosicion = (nuevaPosicion - 1) % 40;
                    } else {
                        nuevaPosicion = 39;
                    }
                    this.getLugar().eliminarAvatar(this);
                    for (int i = 0; i < tablero.size(); i++) {
                        for (int j = 0; j < tablero.get(i).size(); j++) {
                            if (tablero.get(i).get(j).getPosicion() == nuevaPosicion) {
                                this.setLugar(tablero.get(i).get(j));
                            }
                        }
                    }
                    this.getLugar().anhadirAvatar(this);

                    // Evaluación de la casilla
                    if (this.getLugar().estaEnVenta()) {
                        consola.imprimir("Quieres comprar la casilla " + this.getLugar().getNombre() + "?");
                        consola.imprimir("Escribe 'si' si la quieres comprar y 'no' si no la quieres comprar");
                        Scanner scanner = new Scanner(System.in);
                        String respuesta = scanner.nextLine();
                        if (respuesta.equals("si")) {
                            Propiedad propiedad=(Propiedad) this.getLugar();
                            propiedad.comprarCasilla(this.getJugador(), menu.getBanca());
                        }
                    } else if (I != valorTirada) {
                        this.getLugar().evaluarCasilla(menu.getTablero(), this.getJugador(), menu.getBanca(),
                                valorTirada, menu);

                        if (this.getLugar() instanceof AccionSuerte || this.getLugar()instanceof AccionCaja) {
                            if (this.getLugar().getPosicion() == 10) {
                                return; // Salimos si va a la cárcel
                            }
                        }
                        if (this.getLugar().getPosicion() == 30) {
                            consola.imprimir("Has pisado la casilla irCárcel, vas a la cárcel");
                            this.getJugador().encarcelar(tablero);
                            return; // Salimos si va a la cárcel
                        }

                    }

                } else {
                    // Es la ultima
                    if (I == valorTirada) {
                        if (nuevaPosicion != 0) {
                            nuevaPosicion = (nuevaPosicion - 1) % 40;
                        } else {
                            nuevaPosicion = 39;
                        }
                        this.getLugar().eliminarAvatar(this);
                        for (int i = 0; i < tablero.size(); i++) {
                            for (int j = 0; j < tablero.get(i).size(); j++) {
                                if (tablero.get(i).get(j).getPosicion() == nuevaPosicion) {
                                    this.setLugar(tablero.get(i).get(j));
                                }
                            }
                        }
                        this.getLugar().anhadirAvatar(this);

                        // Evaluación de la casilla
                        if (this.getLugar().estaEnVenta()) {
                            consola.imprimir("Quieres comprar la casilla " + this.getLugar().getNombre() + "?");
                            consola.imprimir("Escribe 'si' si la quieres comprar y 'no' si no la quieres comprar");
                            Scanner scanner = new Scanner(System.in);
                            String respuesta = scanner.nextLine();
                            if (respuesta.equals("si")) {
                                Propiedad propiedad=(Propiedad) this.getLugar();
                                propiedad.comprarCasilla(this.getJugador(), menu.getBanca());
                            }
                        }

                        // No es la ultima
                    } else {
                        if (nuevaPosicion != 0) {
                            nuevaPosicion = (nuevaPosicion - 1) % 40;
                        } else {
                            nuevaPosicion = 39;
                        }
                        this.getLugar().eliminarAvatar(this);
                        for (int i = 0; i < tablero.size(); i++) {
                            for (int j = 0; j < tablero.get(i).size(); j++) {
                                if (tablero.get(i).get(j).getPosicion() == nuevaPosicion) {
                                    this.setLugar(tablero.get(i).get(j));
                                }
                            }
                        }
                        this.getLugar().anhadirAvatar(this);

                    }
                }
            }
        }
    }
}
