package monopoly;

import partida.Jugador;

public class Especial extends Casilla {


    public Especial(String nombre, int posicion, Jugador duenho) {
            super(nombre,posicion,duenho); // llama al constructor de Propiedades
        
    }




    public void evaluarCasilla(Tablero tablero, Jugador actual, Jugador banca, int tirada, Menu menu) {

        Especial c = this;
        c.sumarVecesCaidasGrupal(1);

//ns puede usar this.valor porque las casillas especiales no tienen valor, hacer lo del bote del parking

        if ((c instanceof Especial) && getPosicion() == 20) {
            actual.setFortuna(actual.getFortuna() + getDineroParking());
            float bote=getDineroParking()
            setDineroParking(0);
            System.out.println("El jugador recibe"+bote+ " el bote de la banca.");
            actual.setDineroInversionesOBote(actual.getDineroInversionesOBote() + bote); //añadimos al atributo dineroInversionesOBote el valor indicado
            //return true;
            
            //IR A CARCEL
        } else if ((c instanceof Especial) && getPosicion() == 30) {
            actual.encarcelar(tablero.getPosiciones());
            System.out.println("El avatar se coloca en la casilla de Cárcel.");
            //return true;
        } else if (c instanceof Especial && getPosicion() == 0) {
            //eturn true;
        }else if (c instanceof Especial && getPosicion() == 10 && !actual.getEnCarcel()) {
            System.out.println("Has caído en la cárcel pero solo de visita, no estás encarcelado");
            //return true;
        }
        break;

    
    }
}