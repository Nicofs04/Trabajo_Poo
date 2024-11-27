package monopoly;

import partida.Jugador;

public class Transporte extends Propiedad{

    public Transporte(String nombre, int posicion, float valor, Jugador duenho) {
        super(nombre, posicion,valor,duenho); // llama al constructor de Propiedades
    }




    public void evaluarCasilla(Tablero tablero, Jugador actual, Jugador banca, int tirada, Menu menu) {

        Transporte c = this;
        c.sumarVecesCaidasGrupal(1);

        if(!c.getHipotecado()){ //verificamos que la casilla no este hipotecada
            if (!c.getDuenho().equals(actual) && !c.getDuenho().equals(banca) ){

                float costetransporte = Valor.SUMA_VUELTA;
                Jugador duenho = c.getDuenho();
                int contador=0;
                for (Casilla casilla:duenho.getPropiedades()){
                    if (casilla instanceof Transporte) {
                        contador++;
                    }
                }

                if (contador==1) {
                    costetransporte = 0.25f;
                }else if (contador==2) {
                    costetransporte = 0.5f;
                }else if (contador==3) {
                    costetransporte = 0.75f;
                }else if (contador==4) {
                    costetransporte = 1f;
                }

                setImpuesto(costetransporte * Valor.SUMA_VUELTA);

                if (actual.getFortuna() < getImpuesto()) {
                
                    consola.imprimir("El jugador no tiene dinero suficiente para pagar el transporte, por lo que debe declararse en bancarrota o hipotecar alguna propiedad");
                                            
                    analizarMenuPequenho(actual, banca, tablero, menu, c); //analizamos el comando escrito

                    while(actual.getFortuna() < getImpuesto()){
                        if(actual.getPropiedades().isEmpty()){ //nos aseguramos de que pueda seguir teniendo propiedades para hipotecar
                            bancarrotaAJugador(actual, this.getDuenho(), menu.getJugadores(), menu.getAvatares()); //si no llamamos a bancarrota
                            break;
                        }
                        analizarMenuPequenho(actual, banca, tablero, menu, c);
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

                    consola.imprimir("Se han pagado "+getImpuesto() +"€ por el uso del transporte ");
                
                    
                }
            }
        }
    }



}
