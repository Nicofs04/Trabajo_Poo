package monopoly;
import partida.*;
import monopoly.*;

public class Caja extends Carta {


    public Caja(int numCarta, int posicion){
        super(numCarta,posicion);
    }

    public void accion(Tablero tablero,Juego menu, Jugador jugador){
        int posicion=this.getPosicion();
        int numCarta=this.getNumCarta();
        switch (numCarta) {
            case 1 :
                consola.imprimir("Paga 500000€ por un fin de semana en un balneario de 5 estrellas.");
                if(jugador.getFortuna()>=500000f){
                    jugador.setFortuna(jugador.getFortuna()-500000f);
                    jugador.getAvatar().getLugar().setDineroParking(jugador.getAvatar().getLugar().getDineroParking()+500000f);

                    jugador.setDineroTasasEImpuestos(jugador.getDineroTasasEImpuestos() + 500000f); //añadimos al atributo dineroTasasEImpuestos el valor que recibe

                }else{
                    jugador.getAvatar().getLugar().analizarMenuPequenho(jugador, menu.getBanca(), tablero, menu, jugador.getAvatar().getLugar()); //analizamos el comando escrito
                }
                break;
            case 2:
                consola.imprimir("Te investigan por fraude de identidad. Ve a la Cárcel. Ve directamente sin pasar por la casilla de Salida y sin cobrar la cantidad habitual.\n");
                jugador.encarcelar(tablero.getPosiciones());
                break;  
            case 3:
                consola.imprimir("Colócate en la casilla de Salida. Cobra la cantidad habitual.\n");
                int tirada4=40-posicion;
               
                jugador.getAvatar().moverBasico(tablero.getPosiciones(),tirada4);
                jugador.getAvatar().getLugar().evaluarCasilla(tablero,jugador, menu.getBanca(),tirada4,menu);

                //Le sumamos el valor de la salida
                if(posicion>6 || posicion<0){
                    jugador.setVueltas(jugador.getVueltas() + 1); //sumamos 1 vuelta a la variable vueltas que nos permite saber si los jugadores han dado 4 vueltas más

                    jugador.setFortuna(jugador.getFortuna()+(Valor.SUMA_VUELTA));
                    
                    jugador.setNumVueltas(jugador.getNumVueltas() + 1);

                    jugador.setDineroCobradoSalida(jugador.getDineroCobradoSalida() + (Valor.SUMA_VUELTA));
                    
                    System.out.printf("Recibes %.2f€ por pasar por la salida%n", (Valor.SUMA_VUELTA));
                }
                break;
            case 4:
                consola.imprimir("Tu compañía de Internet obtiene beneficios. Recibe 2000000€");
                jugador.setFortuna(jugador.getFortuna()+2000000f);

                jugador.setDineroInversionesOBote(jugador.getDineroInversionesOBote() + 2000000f); //añadimos al atributo dineroInversionesOBote el valor indicado
                break;
            case 5:
                consola.imprimir("Paga 1000000€ por invitar a todos tus amigos a un viaje a Solar14");
                if(jugador.getFortuna()>=1000000f){
                    jugador.setFortuna(jugador.getFortuna()-1000000f);

                    jugador.setDineroTasasEImpuestos(jugador.getDineroTasasEImpuestos() + 1000000f); //añadimos al atributo dineroTasasEImpuestos el valor que recibe
                }else{
                    jugador.getAvatar().getLugar().analizarMenuPequenho(jugador, menu.getBanca(), tablero, menu, jugador.getAvatar().getLugar()); //analizamos el comando escrito
                }
                break;
            case 6:
                consola.imprimir("Alquilas a tus compañeros una villa en Solar7 durante una semana. Paga 200000€ a cada jugador");
                for(int i=0;i<menu.getJugadores().size();i++){
                    if(i!=menu.getTurno()){
                        //Si el jugador del turno tiene el dinero suficiente...
                        if(jugador.getFortuna() >= 200000f){
                            //Les damos el dinero y se lo quitamos al jugador que le toca la carta.
                            menu.getJugadores().get(i).setFortuna(menu.getJugadores().get(i).getFortuna()+200000f);
                            jugador.setFortuna(jugador.getFortuna()-200000f);

                            jugador.setDineroTasasEImpuestos(jugador.getDineroTasasEImpuestos() + 200000f); //añadimos al atributo dineroTasasEImpuestos el valor que recibe

                        }else{
                            jugador.getAvatar().getLugar().analizarMenuPequenho(jugador, menu.getBanca(), tablero, menu, jugador.getAvatar().getLugar()); //analizamos el comando escrito
                        }
                    }
                }
                break;
            default:
                break;
        }

    }


}