package monopoly;
import partida.*;

public class Suerte extends Carta {
    


    public Suerte(int numCarta, int posicion){
        super(numCarta,posicion);
    }

    public void accion(Tablero tablero,Juego menu,Jugador jugador){
        int posicion= this.getPosicion();
        int numCarta= this.getNumCarta();
        switch (numCarta) {
            //Moverse a trans1 y cobrar la salida si pasamos por ella
            case 1 :
                consola.imprimir("Ve al Transportes1 y coge un avión. Si pasas por la casilla de Salida, cobra la cantidad habitual.");
                int tirada1;
                if(5>posicion){
                    tirada1=(5-posicion);
                }else{
                    tirada1=(40-posicion)+5;
                }

                jugador.getAvatar().moverBasico(tablero.getPosiciones(),tirada1);
                jugador.getAvatar().getLugar().evaluarCasilla(tablero,jugador, menu.getBanca(),tirada1,menu);


                //Si pasa por la casilla de salida le sumamos el valor:
                if(posicion>5 || posicion<0){
                    jugador.setVueltas(jugador.getVueltas() + 1); //sumamos 1 vuelta a la variable vueltas que nos permite saber si los jugadores han dado 4 vueltas más
                            
                    jugador.setNumVueltas(jugador.getNumVueltas() + 1);

                    jugador.setDineroCobradoSalida(jugador.getDineroCobradoSalida() + (Valor.SUMA_VUELTA));
                            
                    System.out.printf("Recibes %.2f€ por pasar por la salida\n", (Valor.SUMA_VUELTA));

                }
                break;
            //Moverse a solar15 sin cobrar la salida aunque pasemos por ella
            case 2:
                consola.imprimir("Decides hacer un viaje de placer. Avanza hasta Solar15 directamente, sin pasar por la casilla de Salida\n"); 
                int tirada2;
                if(26>posicion){
                    tirada2=(26-posicion);
                }else{
                    tirada2=(40-posicion)+26;
                }

                jugador.getAvatar().moverBasico(tablero.getPosiciones(),tirada2);
                jugador.getAvatar().getLugar().evaluarCasilla(tablero,jugador,menu.getBanca(),tirada2,menu);

                break;
            //Se vende el billete de avion a solar 17, por lo que se reciben 500000€
            case 3:
                consola.imprimir("Vendes tu billete de avión para Solar17 en una subasta por Internet. Cobra 500000€.");
                jugador.setFortuna(jugador.getFortuna()+500000f);

                jugador.setDineroInversionesOBote(jugador.getDineroInversionesOBote() + 500000f); //añadimos al atributo dineroInversionesOBote el valor indicado

                break;
            case 4:
                consola.imprimir("Ve a Solar3. Si pasas por la casilla de Salida, cobra la cantidad habitual.");
                int tirada3;
                if(6>posicion){
                    tirada3=(6-posicion);
                }else{
                    tirada3=(40-posicion)+6;
                }

                jugador.getAvatar().moverBasico(tablero.getPosiciones(),tirada3);
                jugador.getAvatar().getLugar().evaluarCasilla(tablero,jugador,menu.getBanca(),tirada3,menu);

                //Si pasa por la casilla de salida le sumamos el valor:
                if(posicion>6 || posicion<0){
                    jugador.setVueltas(jugador.getVueltas() + 1); //sumamos 1 vuelta a la variable vueltas que nos permite saber si los jugadores han dado 4 vueltas más
                            
                    jugador.setNumVueltas(jugador.getNumVueltas() + 1);

                    jugador.setDineroCobradoSalida(jugador.getDineroCobradoSalida() + Valor.SUMA_VUELTA);
                            
                    System.out.printf("Recibes %.2f€ por pasar por la salida%n", Valor.SUMA_VUELTA);

                }
                break;
            case 5:
                consola.imprimir("Los acreedores te persiguen por impago. Ve a la Cárcel. Ve directamente sin pasar por la casilla de Salida y sin cobrar la cantidad habitual.");
                jugador.encarcelar(tablero.getPosiciones());
                break;
            case 6:
                consola.imprimir("Has ganado el bote de la lotería! Recibe 1000000€.");
                jugador.setFortuna(jugador.getFortuna()+1000000f);

                jugador.setDineroInversionesOBote(jugador.getDineroInversionesOBote() + 1000000f); //añadimos al atributo dineroInversionesOBote el valor indicado

                break;
            default:
                break;
        }

    }
    
}
