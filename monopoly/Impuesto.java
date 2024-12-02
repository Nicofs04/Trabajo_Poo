package monopoly;

import partida.Jugador;

public class Impuesto extends Casilla {
    
    
    public float impuesto; //impuesto a cobrar de la casilla
    
    
    public Impuesto(String nombre, int posicion, float impuesto, Jugador duenho) {
        super(nombre, posicion, duenho);
        this.impuesto = impuesto;
    }

    public float getImpuesto(){
        return this.impuesto;
    }

    public void evaluarCasilla(Tablero tablero, Jugador actual, Jugador banca, int tirada, Juego juego) {
        Impuesto c = this;
        c.sumarVecesCaidasGrupal(1);
        if (actual.getFortuna() < this.impuesto) {
            consola.imprimir("El jugador no tiene dinero suficiente para pagar los impuestos, por lo que debe declararse en bancarrota o hipotecar alguna propiedad");
            analizarMenuPequenho(actual, banca, tablero, juego, c); //analizamos el comando escrito
            //Acabaría la partida para este jugador
        }else{
            actual.setFortuna(actual.getFortuna() - this.impuesto);
            //Le pagamos a la banca:
            banca.setFortuna(banca.getFortuna() + this.impuesto);
            consola.imprimir("El jugador paga "+this.impuesto +"€");

            setDineroParking(getDineroParking()+this.impuesto); //sumamos al bote del parking lo cobrado por las casilla impuestos
            actual.setDineroTasasEImpuestos(actual.getDineroTasasEImpuestos() + this.impuesto); //le sumamos al atributo dineroTasasEImpuestos el impuesto de la casillas impuesto
        }

    }

    
}