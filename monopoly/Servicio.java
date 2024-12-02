package monopoly;

import partida.Jugador;

public class Servicio extends Propiedad{

    static final float FACTOR_SERVICIO = 6506.64f;


    public Servicio(String nombre, int posicion, float valor, Jugador duenho) {
        super(nombre, posicion,valor,duenho); // llama al constructor de Propiedades
        
    }

    public float getFactor(){
        return FACTOR_SERVICIO;
    }



    public void evaluarCasilla(Tablero tablero, Jugador actual, Jugador banca, int tirada, Juego juego) {
        Servicio c = this;
        c.sumarVecesCaidasGrupal(1);
        if(!c.getHipotecado()){ //verificamos que la casilla no este hipotecada
            if(!c.getDuenho().equals(actual) && !c.getDuenho().equals(banca)){
                        Jugador duenho = c.getDuenho();
                        int contador=0;
                        float valortirada = 0;
                        for (Casilla casilla:duenho.getPropiedades()){
                            if (casilla instanceof Servicio ) {
                                contador++;
                            }
                        }

                        if (contador==1) {
                            valortirada = tirada*4;

                        }else if (contador==2) {
                            valortirada = tirada*10;
                        }

                        setImpuesto(FACTOR_SERVICIO*valortirada);
                        
                    
                        if(actual.getFortuna() < (getImpuesto())) {
                        
                            consola.imprimir("El jugador no tiene dinero suficiente para pagar el servicio, por lo que debe declararse en bancarrota o hipotecar alguna propiedad");
                    
                            juego.analizarMenuPequenho(actual, banca, tablero, juego, c); //analizamos el comando escrito

                            while(actual.getFortuna() < getImpuesto()){
                                if(actual.getPropiedades().isEmpty()){ //nos aseguramos de que pueda seguir teniendo propiedades para hipotecar
                                    juego.bancarrotaAJugador(actual, this.getDuenho(), juego.getJugadores(), juego.getAvatares()); //si no llamamos a bancarrota
                                    break;
                                }
                                juego.analizarMenuPequenho(actual, banca, tablero, juego, c);
                            }

                            if (actual.getFortuna() < getImpuesto()) {
                                consola.imprimir(String.format("El jugador %s no tiene dinero suficiente"));
                            }else{
                                actual.setFortuna(actual.getFortuna()-getImpuesto()); //le restamos el alquiler pagado
                                actual.setDineroPagadoAlquileres(actual.getDineroPagadoAlquileres() + getImpuesto()); //sumamos el dinero pagado al atributo dineroPagado del jugador que paga

                                getDuenho().setFortuna((duenho.getFortuna() + getImpuesto())); //le sumamos el alquiler al dueño de la casilla
                                getDuenho().setDineroCobradoAlquileres(getDuenho().getDineroCobradoAlquileres() + getImpuesto()); //sumamos el dinero cobrado al atributo dineroCobrado del jugador que cobra

                                this.setDineroCasilla(this.getDineroCasilla() + getImpuesto()); //le sumamos lo que se paga al atributo que nos indica el dinero total que gana el dueño de la casilla

                                consola.imprimir("El jugador paga "+getImpuesto() +"€");
                            }
                            //Acabaría la partida para este jugador
                        }else{
                            actual.setFortuna(actual.getFortuna() - getImpuesto()); //le restamos el alquiler pagado
                            actual.setDineroPagadoAlquileres(actual.getDineroPagadoAlquileres() + getImpuesto()); //sumamos el dinero pagado al atributo dineroPagado del jugador que paga

                            getDuenho().setFortuna((duenho.getFortuna() + getImpuesto())); //le sumamos el alquiler al dueño de la casilla
                            getDuenho().setDineroCobradoAlquileres(getDuenho().getDineroCobradoAlquileres() + getImpuesto()); //sumamos el dinero cobrado al atributo dineroCobrado del jugador que cobra

                            this.setDineroCasilla(this.getDineroCasilla() + getImpuesto()); //le sumamos lo que se paga al atributo que nos indica el dinero total que gana el dueño de la casilla

                            consola.imprimir("Se han pagado "+getImpuesto() +"€ por la realización del servicio a" +c.getDuenho().getNombre());
                        }
                    }
                }

            }




}
