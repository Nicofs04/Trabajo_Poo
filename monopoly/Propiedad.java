package monopoly;


import java.util.Scanner;

import partida.Jugador;

public abstract class Propiedad extends Casilla {

    
    private float valor; // valor de la propiedad
    private float impuesto;
    private boolean hipotecado; // estado de hipoteca de la casilla

    // Constructor de Propiedad
    public Propiedad(String nombre, int posicion,  float valor, Jugador duenho) {
        super(nombre, posicion, duenho); // llama al constructor de Casilla2
        this.valor = valor; // asigna el valor de la propiedad
        this.impuesto = valor*0.10f;
        this.hipotecado=false;
    }

    public float getImpuesto() {
        return impuesto;
    }
    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
    }

    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public boolean getHipotecado() {
        return hipotecado;
    }
    public void setHipotecado(boolean hipotecado) {
        this.hipotecado = hipotecado;
    }

    public void hipotecarPropiedad(Propiedad propiedad){
        propiedad.setHipotecado(true);
    }

    public void comprarCasilla (Jugador solicitante, Jugador banca) throws Excepciones_PropComprar{
        // Comprobamos que sea una casilla "comprable"
        if (estaEnVenta()==true) {
            if (solicitante.getFortuna()<this.valor) {
                throw new Excepciones_PropComprar("No tienes dinero suficiente como para comprar esta propiedad");
            }else{
            // JUGADOR SOLICITANTE
            
            // "quitar dinero"
            solicitante.setFortuna(solicitante.getFortuna() - this.valor); //le restamos el valor de la casilla comprada a la fortuna del jugador comprador
            solicitante.setDineroInvertido(solicitante.getDineroInvertido() + this.valor); //sumamos el valor de la casilla al atributo dineroInvertido del comprador

            // "sumar gastos"
            solicitante.setGastos(solicitante.getGastos() + this.valor);

            // "asignarle la propiedad"
            // Primero tenemos que cambiarle el dueño a la casilla
            setDuenho(solicitante);
            solicitante.getPropiedades().add(this); //esto es por casilla2
                
            // BANCA, aunque realmente es innecesario
            banca.setFortuna(banca.getFortuna() + this.valor);

            consola.imprimir("El jugador " + solicitante.getNombre() + " compra la casilla "+ this.getNombre() + " por " + this.valor);
            consola.imprimir("Su fortuna actual es:" + solicitante.getFortuna());
            }
        } else if (estaEnVenta()==false) {
            // En caso de que no sea de ninguno de estos tipos, la propiedad no se podrá
            // comprar
            throw new Excepciones_PropComprar("Esta propiedad no se puede comprar, para poder comprar una propiedad debe de ser de uno de los siguientes tipos: solar, transporte, servicios");
        } else {
            throw new Excepciones_PropComprar("No tienes dinero suficiente como para comprar esta propiedad");

        }
    }


    

    public float valorHipoteca(Propiedad propiedad){
        float valor = 0;
        if (propiedad instanceof Solar) {
            Solar solar = ((Solar) this);
            switch (solar.getGrupo().getColorGrupo()) {
                case "BLACK":
                    valor = 600000;
                    break;
                case "CYAN":
                    valor = 520000;
                    break;
                case "PURPLE":
                    valor = 676000;
                    break;
                case "WHITE":
                    valor = 878800;
                    break;
                case "GREEN":
                    valor = 1930723.6f;
                    break;
                case "BLUE":
                    valor = 3764911.02f;
                    break;
                case "RED":
                    valor = 1142440;
                    break;
                case "YELLOW":
                    valor = 1485172;
                    break;
                default:
                    consola.imprimir("No se ha encontrado el grupo");
                    break;
            }
        }else if (propiedad instanceof Transporte) {
            valor = ((propiedad.getValor())/2);
        }else if (propiedad instanceof Servicio){
            valor = ((propiedad.getValor())/2);
        }
        return valor;
    }
}
