package monopoly;

import partida.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Casilla {
    // Atributos:
    private String nombre; // Nombre de la casilla
    private String tipo; // Tipo de casilla (Solar, Especial, Transporte, Servicios, Comunidad).
    private float valor; // Valor de esa casilla (en la mayoría será valor de compra, en la casilla
                         // parking se usará como el bote).
    private int posicion; // Posición que ocupa la casilla en el tablero (entero entre 1 y 40).
    private Jugador duenho; // Dueño de la casilla (por defecto sería la banca).
    private Grupo grupo; // Grupo al que pertenece la casilla (si es solar).
    private float impuesto; // Cantidad a pagar por caer en la casilla: el alquiler en
                            // solares/servicios/transportes o impuestos.
    private float hipoteca; // Valor otorgado por hipotecar una casilla
    private ArrayList<Avatar> avatares; // Avatares que están situados en la casilla.
    private Scanner scanner = new Scanner(System.in);

    // Constructores:
    public Casilla() {
    }// Parámetros vacíos

    /*
     * Constructor para casillas tipo Solar, Servicios o Transporte:
     * Parámetros: nombre casilla, tipo (debe ser solar, serv. o transporte),
     * posición en el tablero, valor y dueño.
     */
    public Casilla(String nombre, String tipo, int posicion, float valor, Jugador duenho) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.valor = valor;
        this.duenho = duenho;
    }

    /*
     * Constructor utilizado para inicializar las casillas de tipo IMPUESTOS.
     * Parámetros: nombre, posición en el tablero, impuesto establecido y dueño.
     */
    public Casilla(String nombre, int posicion, float impuesto, Jugador duenho) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.impuesto = impuesto;
        this.duenho = duenho;
    }

    /*
     * Constructor utilizado para crear las otras casillas (Suerte, Caja de
     * comunidad y Especiales):
     * Parámetros: nombre, tipo de la casilla (será uno de los que queda), posición
     * en el tablero y dueño.
     */
    public Casilla(String nombre, String tipo, int posicion, Jugador duenho) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.duenho = duenho;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public Jugador getDuenho() {
        return duenho;
    }

    public void setDuenho(Jugador duenho) {
        this.duenho = duenho;
    }

    public Grupo getGrupo() { // mirar la clase grupo(puse public class en lugar de class a secas)
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public float getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
    }

    public float getHipoteca() {
        return hipoteca;
    }

    public void setHipoteca(float hipoteca) {
        this.hipoteca = hipoteca;
    }

    public ArrayList<Avatar> getAvatares() {
        return avatares;
    }

    public void setAvatares(ArrayList<Avatar> avatares) { // AL SER UN ARRAY TENGO QUE ITERAR CADA UNA DE SUS POSICIONES
                                                          // O ASÍ ESTÁ BIEN?
        this.avatares = avatares;
    }

    @Override
    public String toString() { falta poner si es delujo o parking...
        return "tipo: " + getTipo() + ",\n grupo: " + getGrupo() + ",\n propietario: " + getDuenho() + ",\n valor: "
                + getValor() + ",\n alquiler: " + "220000" +
                ",\n valor hotel: " + "1560000" + ",\n valor casa: " + "1560000" + ",\n valor piscina: " + "1040000"
                + ",\n valor pista de deportes: "
                + "3250000" + ",\n alquiler de una casa: " + "1100000" + ",\n alquiler dos casas: " + "3300000" +
                ",\n alquiler tres casas: " + "7700000" + ",\n alquiler cuatro casas: " + "11000000"
                + ",\n alquiler hotel: " +
                "15400000" + ",\n alquiler piscina: " + "5500000" + ",\n alquiler pista de deporte: " + "5500000";
    }

    // Método utilizado para añadir un avatar al array de avatares en casilla.
    public void anhadirAvatar(Avatar av) {
        this.avatares.add(av);
    }

    // Método utilizado para eliminar un avatar del array de avatares en casilla.
    public void eliminarAvatar(Avatar av) {
        this.avatares.remove(av);
    }

    /*
     * Método para evaluar qué hacer en una casilla concreta. Parámetros:
     * - Jugador cuyo avatar está en esa casilla.
     * - La banca (para ciertas comprobaciones).
     * - El valor de la tirada: para determinar impuesto a pagar en casillas de
     * servicios.
     * Valor devuelto: true en caso de ser solvente (es decir, de cumplir las
     * deudas), y false
     * en caso de no cumplirlas.
     */
    public boolean evaluarCasilla(Jugador actual, Jugador banca, int tirada) {

    }

    /*
     * Método usado para comprar una casilla determinada. Parámetros:
     * - Jugador que solicita la compra de la casilla.
     * - Banca del monopoly (es el dueño de las casillas no compradas aún).
     */

    /*
     * SOLO SE PUEDEN COMPRAR LAS CASILLAS DE TIPO SOLAR,SERVICIOS Y TRANSPORTE,
     * TIENE QUE TENER DINERO SUFICIENTE EL SOLICITANTE, y el dueño tiene que ser la
     * banca
     * ,
     * Al solicitante hay que asignarle la propiedad, quitarle la pasta y sumarle
     * los gastos, a la banca quitarle
     * la propiedad y darle la pasta y hay que asignar el nuevo dueño a la casilla,
     * que será el solicitante
     */
    public void comprarCasilla(Jugador solicitante, Jugador banca) {
        // Comprobamos que sea una casilla "comprable"
        if (((this.tipo == "Solar") || (this.tipo == "Servicios") || (this.tipo == "Transporte"))
                && (solicitante.getFortuna() >= this.valor) && (this.duenho == banca)) {

            // JUGADOR SOLICITANTE

            // "quitar pasta"
            solicitante.setFortuna(solicitante.getFortuna() - this.valor);

            // "sumar gastos"
            solicitante.setGastos(solicitante.getGastos() + this.valor);

            // "asignarle la propiedad"
            // Primero tenemos que cambiarle el dueño a la casilla
            this.duenho = solicitante;
            solicitante.getPropiedades().add(this);

            // BANCA, aunque realmente es innecesario
            banca.setFortuna(banca.getFortuna() + this.valor);

            System.out.println("El jugador " + solicitante.getNombre() + "compra la casilla "
                    + solicitante.getAvatar().getLugar().getNombre() + "por " + this.valor);
            System.out.println("Su fortuna actual es:" + solicitante.getFortuna());

        } else if ((this.tipo != "Solar") && (this.tipo != "Servicios") && (this.tipo != "Transporte")) {
            // En caso de que no sea de ninguno de estos tipos, la propiedad no se podrá
            // comprar
            System.out.println(
                    "Esta propiedad no se puede comprar, para poder comprar una propiedad debe de ser de uno de los siguientes tipos: SOLAR, TRANSPORTE, SERVICIOS\n");
        } else {
            System.out.println("No tienes dinero suficiente como para comprar esta propiedad\n");
        }

    }

    /*
     * Método para añadir valor a una casilla. Utilidad:
     * - Sumar valor a la casilla de parking.
     * - Sumar valor a las casillas de solar al no comprarlas tras cuatro vueltas de
     * todos los jugadores.
     * Este método toma como argumento la cantidad a añadir del valor de la casilla.
     */

    // Si la casilla en la que cae el avatar es Parking, entonces deberá de recibir
    // el bote almacenado por el pago de impuestos o tasas.
    public void sumarValor(float suma) {
        this.valor+=suma;
    }

    /*
     * Método para mostrar información sobre una casilla.
     * Devuelve una cadena con información específica de cada tipo de casilla.
     */
    public String infoCasilla() {
        StringBuilder info = new StringBuilder();
    
        if (this.tipo.equals("solar")) {
            info.append("Nombre de la casilla: ").append(this.nombre).append("\n");
            info.append("Tipo de la casilla: ").append(this.tipo).append("\n");
            info.append("Valor de la casilla: ").append(this.valor).append("\n");
            info.append("Posición de la casilla: ").append(this.posicion).append("\n");
            info.append("Nombre del dueño de la casilla: ").append(this.duenho.getNombre()).append("\n");
            info.append("Grupo de la casilla: ").append(this.grupo.getColorGrupo()).append("\n");
            info.append("Impuesto por caer en la casilla: ").append(this.impuesto).append("\n");
            info.append("Valor de hipoteca: ").append(this.hipoteca).append("\n");
    
            info.append("Avatares en la casilla:\n");
            for (int i = 0; i < this.avatares.size(); i++) {
                info.append("Avatar ").append(i).append(": ").append(this.avatares.get(i).getId()).append("\n");
            }
    
        } else {
            info.append("Nombre de la casilla: ").append(this.nombre).append("\n");
            info.append("Tipo de la casilla: ").append(this.tipo).append("\n");
            info.append("Valor de la casilla: ").append(this.valor).append("\n");
            info.append("Posición de la casilla: ").append(this.posicion).append("\n");
            info.append("Nombre del dueño de la casilla: ").append(this.duenho.getNombre()).append("\n");
            info.append("Impuesto por caer en la casilla: ").append(this.impuesto).append("\n");
            info.append("Valor de hipoteca: ").append(this.hipoteca).append("\n");
    
            info.append("Avatares en la casilla:\n");
            for (int i = 0; i < this.avatares.size(); i++) {
                info.append("Avatar ").append(i).append(": ").append(this.avatares.get(i).getId()).append("\n");
            }
        }
    
        return info.toString();
    }
    

    /*
     * Método para mostrar información de una casilla en venta.
     * Valor devuelto: texto con esa información.
     */
    public String casEnVenta() {
        // Para mostrar la informacion tiene que estar en venta, y para estar en venta,
        // el dueño tiene que ser la banca y cumplir los requisitos del primer if de
        // "comprarCasilla"
        // DOY POR HECHO QUE EL JUGADOR BANCA DE LLAMA "banca", si no habría que pasarle
        // la banca como parámetro a la función
        StringBuilder sb = new StringBuilder();
        if (((this.tipo == "Solar") || (this.tipo == "Servicios") || (this.tipo == "Transporte"))&& (this.duenho.getNombre() == "Banca")) {
            if(this.tipo=="Solar"){

                sb.append(String.format("Nombre de la casilla a la venta: %s", this.getNombre()));
                sb.append(String.format("Tipo de la casilla a la venta: %s", this.getTipo()));
                sb.append(String.format("Valor de la casilla a la venta: %s", this.getValor()));
                sb.append(String.format("Posición de la casilla a la venta: %s", this.getPosicion()));
                sb.append(String.format("Nombre del dueño de la casilla: %s", this.getDuenho().getNombre()));
                sb.append(String.format("Grupo de la casilla a la venta: %s", this.getColorGrupo()));
                sb.append(String.format("Impuesto por caer en la casilla: %s", this.getImpuesto()));
                sb.append(String.format("Valor de hipoteca: %s", this.getHipoteca()));
                sb.append(String.format("Avatares: "));
                for(Avatar avatar:avatares){
                    sb.append(String.format("%s\t", avatar.getId()));
                }
            }else{
                sb.append(String.format("Nombre de la casilla a la venta: %s", this.getNombre()));
                sb.append(String.format("Tipo de la casilla a la venta: %s", this.getTipo()));
                sb.append(String.format("Valor de la casilla a la venta: %s", this.getValor()));
                sb.append(String.format("Posición de la casilla a la venta: %s", this.getPosicion()));
                sb.append(String.format("Nombre del dueño de la casilla: %s", this.getDuenho().getNombre()));
                sb.append(String.format("Impuesto por caer en la casilla: %s", this.getImpuesto()));
                sb.append(String.format("Valor de hipoteca: %s", this.getHipoteca()));
                sb.append(String.format("Avatares: "));
                for(Avatar avatar:avatares){
                    sb.append(String.format("%s\t"));
                }
            }

        }else{
            String.format("Esta casilla no está a la venta\n");
        }
        return sb.toString();
    }


    public String generarCasilla(int posicion) {
        return this.nombre;
    }
}

