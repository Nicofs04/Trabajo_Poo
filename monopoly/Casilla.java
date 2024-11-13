package monopoly;

import partida.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Iterator;

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

    private ArrayList<ArrayList<Casilla>> tablero; // TABLERO, NECESARIO PARA LA FUNCION EVALUARCASILLA
    private ArrayList<Edificacion> edificaciones;
    
    private boolean hipotecado;
    private ArrayList<Integer> Vecescaidas;
    private int Veces_global;

    private float dineroCasilla;


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
        this.avatares = new ArrayList<>();
        this.edificaciones = new ArrayList<Edificacion>();
        this.Vecescaidas = new ArrayList<Integer>();
        this.impuesto = (valor)*(0.10f);

        inicializarVeces(Vecescaidas);
    }

    /*
     * Constructor utilizado para inicializar las casillas de tipo IMPUESTOS.
     * Parámetros: nombre, posición en el tablero, impuesto establecido y dueño.
     */
    public Casilla(String nombre, int posicion, float impuesto, Jugador duenho) {
        this.nombre = nombre;
        this.tipo="impuesto";
        this.posicion = posicion;
        this.impuesto = impuesto;
        this.duenho = duenho;
        this.avatares = new ArrayList<>();
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
        this.avatares = new ArrayList<>();
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

    public ArrayList<ArrayList<Casilla>> getTablero(){
        return tablero;
    }

    public void setTablero(ArrayList<ArrayList<Casilla>> tablero) {                     
        this.tablero= tablero;
    }

    public void anhadirEdificacion(Edificacion edificacion){
        this.edificaciones.add(edificacion);
    }

    public ArrayList<Edificacion> getEdificacion(){
        return this.edificaciones;
    }

    public boolean getHipotecado(){
        return hipotecado;
    }

    public void setHipotecado(boolean hipotecado){
        this.hipotecado = hipotecado;
    }

    public int getVeces_global(){
        return this.Veces_global;
    }

    public void sumarVeces_global(int Veces_global){
        this.Veces_global += Veces_global;
    }

    public void incrementarVeces(int jugadorIndex) {
        int caidasActuales = Vecescaidas.get(jugadorIndex);
        Vecescaidas.set(jugadorIndex, caidasActuales + 1);
    }
    
    // Método para obtener el número de caídas de un jugador específico
    public int getVeces(int jugadorIndex) {
        return Vecescaidas.get(jugadorIndex);
    }

    public void inicializarVeces(ArrayList<Integer> Vecescaidas) {
        if (Vecescaidas.isEmpty()) {
            for(int i = 0; i<6 ; i++){
                Vecescaidas.add(0);
            }
        }
    }

    public float getDineroCasilla(){
        return dineroCasilla;
    }

    public void setDineroCasilla(float dineroCasilla){
        this.dineroCasilla = dineroCasilla;
    }

    public void eliminarEdificacion(String tipoEdificacion) {
        
        
        for (int i = 0; i < edificaciones.size(); i++) {
            if (edificaciones.get(i).getTipo().equals(tipoEdificacion)) {
                edificaciones.remove(i);
                break;
            }
        }
    }

    //Devuelve el valor que se le suma a los jugadores por pasar por la casilla de salida
    public float valorSalida(ArrayList<ArrayList<Casilla>> tablero){
        float valorSalida;
        float suma=0;
        int contador_iteraciones=0;
        for(int i=0;i<tablero.size();i++){
            for(int j=0;j<tablero.get(i).size();j++){
                if(tablero.get(i).get(j).getTipo()=="solar"){
                    suma+=tablero.get(i).get(j).getValor();
                }
                contador_iteraciones++;
            }
        }
        valorSalida=suma/contador_iteraciones;
        return valorSalida;
    }

    //Devuelve el valor que hay que pagar para salir de la carcel
    public float valorCarcel(ArrayList<ArrayList<Casilla>> tablero){
        float valorCarcel;
        valorCarcel = valorSalida(tablero) * (25.0f / 100.0f);
        return valorCarcel;
    }

    @Override
    public String toString() {
        if (this.tipo == "solar") {
            return "\nnombre: " + getNombre() +
            ",\n\ttipo: " + getTipo() +
            ",\n\tvalor: " + getValor() +
            ",\n\tPropietario: " + getDuenho().getNombre() +
            ",\n\tPosición: " + getPosicion() +
            ",\n\tGrupo: " + getGrupo().getColorGrupo() +
            ",\n\tImpuesto: " + getImpuesto() +
            ",\n\talquiler: " + (getImpuesto() + sumarImpuestoedificios()) +
            ",\n\tvalor casa: " + (getValor() * 0.6f) +
            ",\n\tvalor hotel: " + (getValor() * 0.6f) +
            ",\n\tvalor piscina: " + (getValor() * 0.4f) +
            ",\n\tvalor pista de deportes: " + (getValor() * 1.25f) +
            ",\n\talquiler de una casa: " + (getImpuesto() * 5) +
            ",\n\talquiler dos casas: " + (getImpuesto() * 15) +
            ",\n\talquiler tres casas: " + (getImpuesto() * 35) +
            ",\n\talquiler cuatro casas: " + (getImpuesto() * 50) +
            ",\n\talquiler hotel (por casa hotel): " + (getImpuesto() * 70) +
            ",\n\talquiler piscina (por cada pisina): " + (getImpuesto() * 25) +
            ",\n\talquiler pista de deporte (por cada pista de deporte): " + (getImpuesto() * 25);
     
        } else if (this.tipo == "transporte" || this.tipo == "servicio") {
            return "nombre: " + getNombre() + ",\n tipo: " + getTipo() + ",\n valor: " + getValor()
                    + ",\n Propietario: "
                    + getDuenho().getNombre() + ",\n Posición:" + getPosicion() + ",\n Impuesto:" + getImpuesto();
        } else if (this.tipo == "impuesto") {
            return "tipo: " + getTipo() + ",\n Impuesto:" + getImpuesto();

        } else if (this.tipo == "especial" && this.posicion == 20) {
            // LISTA DE LOS JUGADORES
            StringBuilder jugadoresEnCasilla = new StringBuilder();
            for (int i = 0; i < this.avatares.size(); i++) {
                jugadoresEnCasilla.append(avatares.get(i).getJugador().getNombre()).append(", "); // Cambia getNombre
                                                                                                  // por el método que
                                                                                                  // devuelva el nombre
                                                                                                  // del jugador
            }

            // Eliminar la última coma y espacio
            if (jugadoresEnCasilla.length() > 0) {
                jugadoresEnCasilla.setLength(jugadoresEnCasilla.length() - 2);
            }

            return "bote: " + getValor() + ",\n jugadores:" + jugadoresEnCasilla;
        } else if (this.tipo == "especial" && this.posicion == 10) {
            StringBuilder jugadoresEnCasilla = new StringBuilder();
            for (int i = 0; i < this.avatares.size(); i++) {
                jugadoresEnCasilla.append(avatares.get(i).getJugador().getNombre()).append(", "); // Cambia getNombre
                                                                                                  // por el método que
                                                                                                  // devuelva el nombre
                                                                                                  // del jugador
            }

            // Eliminar la última coma y espacio
            if (jugadoresEnCasilla.length() > 0) {
                jugadoresEnCasilla.setLength(jugadoresEnCasilla.length() - 2);
            }

            return "salir: " +valorCarcel(this.tablero)+ ",\n jugadores:" + jugadoresEnCasilla;
        } else if (this.tipo == "especial" && this.posicion == 0) {
            // LISTA DE LOS JUGADORES
            StringBuilder jugadoresEnCasilla = new StringBuilder();
            for (int i = 0; i < this.avatares.size(); i++) {
                jugadoresEnCasilla.append(avatares.get(i).getJugador().getNombre()).append(", "); // Cambia getNombre
                                                                                                  // devuelva el nombre
                                                                                                  // del jugador
            }

            // Eliminar la última coma y espacio
            if (jugadoresEnCasilla.length() > 0) {
                jugadoresEnCasilla.setLength(jugadoresEnCasilla.length() - 2);
            }
            return "nombre: " + getNombre() + ",\n jugadores:" + jugadoresEnCasilla;
        }
        return "Tipo o posición desconocida\n";
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

    // FALTAN HACER RETOQUES A TABLERO PARA QUE FUNCIONE
    public boolean evaluarCasilla(Tablero tablero, Jugador actual, Jugador banca, int tirada,Menu menu) {
        //NO EVALUAMOS EN ESTA FUNCION LAS CASILLAS: Salida(especial), Carcel(especial)
        //PARKING, en este caso siempre va a ser true ya que la recaudacion de impuestos siempre va a ser >=0
        Casilla c = actual.getAvatar().getLugar();
        c.sumarVeces_global(1);

        switch (c.getTipo()) {
            case "solar":   
                int jugadorIndex = menu.getTurno(); // Asumiendo que cada jugador tiene un método para obtener su índice único
                incrementarVeces(jugadorIndex);            
                if(!c.getHipotecado()){ //verificamos que la casilla no este hipotecada
                    if (!c.getDuenho().equals(actual) && !c.getDuenho().equals(banca) ) {
                        
                        if (actual.getFortuna() < this.impuesto+sumarImpuestoedificios()) {
                            
                            System.out.println("El jugador no tiene dinero suficiente para pagar el alquiler, por lo que debe declararse en bancarrota o hipotecar alguna propiedad");
                            
                            analizarMenuPequenho(actual, banca, tablero, menu, c); //analizamos el comando escrito

                            return false;
                            //Acabaría la partida para este jugador
                        }else{
                            actual.setFortuna(actual.getFortuna()-this.impuesto); //le restamos el alquiler pagado
                            actual.setDineroPagadoAlquileres(actual.getDineroPagadoAlquileres() + this.impuesto+sumarImpuestoedificios()); //sumamos el dinero pagado al atributo dineroPagado del jugador que paga

                            this.duenho.setFortuna((duenho.getFortuna() + this.impuesto+sumarImpuestoedificios())); //le sumamos el alquiler al dueño de la casilla
                            this.duenho.setDineroCobradoAlquileres(this.duenho.getDineroCobradoAlquileres() + this.impuesto+sumarImpuestoedificios()); //sumamos el dinero cobrado al atributo dineroCobrado del jugador que cobra

                            this.setDineroCasilla(this.getDineroCasilla() + this.impuesto+sumarImpuestoedificios()); //le sumamos lo que se paga al atributo que nos indica el dinero total que gana el dueño de la casilla

                            System.out.println("El jugador paga "+this.impuesto+sumarImpuestoedificios() +"€");
                            return true;
                        }      
                    }
                }
                break;
            case "servicio":
                if(!c.getHipotecado()){ //verificamos que la casilla no este hipotecada
                    if(!c.getDuenho().equals(actual) && !c.duenho.equals(banca)){
                    
                        if(actual.getFortuna() < (this.impuesto)) {
                        
                            System.out.println("El jugador no tiene dinero suficiente para pagar el servicio, por lo que debe declararse en bancarrota o hipotecar alguna propiedad");
                    
                            analizarMenuPequenho(actual, banca, tablero, menu, c); //analizamos el comando escrito

                            return false;
                            //Acabaría la partida para este jugador
                    }else{
                        actual.setFortuna(actual.getFortuna() - this.impuesto); //le restamos el alquiler pagado
                        actual.setDineroPagadoAlquileres(actual.getDineroPagadoAlquileres() + this.impuesto); //sumamos el dinero pagado al atributo dineroPagado del jugador que paga

                        this.duenho.setFortuna((duenho.getFortuna() + this.impuesto)); //le sumamos el alquiler al dueño de la casilla
                        this.duenho.setDineroCobradoAlquileres(this.duenho.getDineroCobradoAlquileres() + this.impuesto); //sumamos el dinero cobrado al atributo dineroCobrado del jugador que cobra

                        this.setDineroCasilla(this.getDineroCasilla() + this.impuesto); //le sumamos lo que se paga al atributo que nos indica el dinero total que gana el dueño de la casilla

                        System.out.println("Se han pagado "+this.impuesto +"€ por la realización del servicio");
                    
                        return true;
                        }
                    }
                }
                break;
            case "transporte":
                if(!c.getHipotecado()){ //verificamos que la casilla no este hipotecada
                    if (!c.getDuenho().equals(actual) && !c.getDuenho().equals(banca) ){

                        if (actual.getFortuna() < this.impuesto) {
                            
                            System.out.println("El jugador no tiene dinero suficiente para pagar el transporte, por lo que debe declararse en bancarrota o hipotecar alguna propiedad");
                                                        
                            analizarMenuPequenho(actual, banca, tablero, menu, c); //analizamos el comando escrito

                            return false;
                            //Acabaría la partida para este jugador
                        }else{
                            actual.setFortuna(actual.getFortuna() - this.impuesto); //le restamos el alquiler pagado
                            actual.setDineroPagadoAlquileres(actual.getDineroPagadoAlquileres() + this.impuesto); //sumamos el dinero pagado al atributo dineroPagado del jugador que paga

                            this.duenho.setFortuna((duenho.getFortuna() + this.impuesto)); //le sumamos el alquiler al dueño de la casilla
                            this.duenho.setDineroCobradoAlquileres(this.duenho.getDineroCobradoAlquileres() + this.impuesto); //sumamos el dinero cobrado al atributo dineroCobrado del jugador que cobra

                            this.setDineroCasilla(this.getDineroCasilla() + this.impuesto); //le sumamos lo que se paga al atributo que nos indica el dinero total que gana el dueño de la casilla

                            System.out.println("Se han pagado "+this.impuesto +"€ por el uso del transporte");
                            
                            return true;
                        }
                    }
                }
                break;
            //No es para esta entrega
            case "suerte":
                //Barajar cartas
                ArrayList<Integer> baraja1=new ArrayList<Integer>();
                baraja1= crearBaraja();
                barajar(baraja1);
                //Elegir carta
                System.out.println("Qué carta desea elegir?");
                Scanner scanner1 = new Scanner(System.in);
                int carta1 = scanner1.nextInt();
                while(carta1>6 || carta1<1){
                    System.out.println("Introduce un número del 1 al 6");
                    carta1 = scanner1.nextInt();
                }
                int eleccion1=0;
                eleccion1=baraja1.get(carta1-1);
                //Realizar acción
                menu.accionCarta(c.getTipo(),eleccion1);

                break;
            //No es para esta entrega
            case "caja":
                //Barajar cartas
                ArrayList<Integer> baraja2=new ArrayList<Integer>();
                baraja2= crearBaraja();
                barajar(baraja2);
                //Elegir carta
                System.out.println("Qué carta desea elegir?");
                Scanner scanner2 = new Scanner(System.in);
                int carta2 = scanner2.nextInt();
                while(carta2>6 || carta2<1){
                    System.out.println("Introduce un número del 1 al 6");
                    carta2 = scanner2.nextInt();
                }
                int eleccion2=0;
                eleccion2=baraja2.get(carta2);
                //Realizar acción
                menu.accionCarta(c.getTipo(),eleccion2);
                break;
            case "impuesto":
                if (actual.getFortuna() < this.impuesto) {
                    System.out.println("El jugador no tiene dinero suficiente para pagar los impuestos, por lo que debe declararse en bancarrota o hipotecar alguna propiedad");
                    
                    analizarMenuPequenho(actual, banca, tablero, menu, c); //analizamos el comando escrito

                    return false;
                    //Acabaría la partida para este jugador
                }else{
                    actual.setFortuna(actual.getFortuna() - this.impuesto);
                    //Le pagamos a la banca:
                    banca.setFortuna(banca.getFortuna() + this.impuesto);

                    System.out.println("El jugador paga "+this.impuesto +"€");

                    actual.setDineroTasasEImpuestos(actual.getDineroTasasEImpuestos() + this.impuesto); //le sumamos al atributo dineroTasasEImpuestos el impuesto de la casillas impuesto

                    return true;
                }
            case "especial":
                //PARKING
                if ((this.tipo == "especial" && this.posicion == 20)) {
                    actual.setFortuna(actual.getFortuna() + this.valor);

                    float bote=this.valor;

                    this.valor = 0;

                    System.out.println("El jugador recibe"+bote+ " el bote de la banca.");

                    actual.setDineroInversionesOBote(actual.getDineroInversionesOBote() + bote); //añadimos al atributo dineroInversionesOBote el valor indicado

                    return true;
                    //IR A CARCEL
                } else if (this.tipo == "especial" && this.posicion == 30) {
                    actual.encarcelar(tablero.getPosiciones());
                    System.out.println("El avatar se coloca en la casilla de Cárcel.");
                    return true;
                } else if (this.tipo == "especial" && this.posicion == 0) {
                    return true;
                }else if (this.tipo == "especial" && this.posicion == 10 && !actual.getEnCarcel()) {
                    System.out.println("Has caído en la cárcel pero solo de visita, no estás encarcelado");
                    return true;
                }
                break;
            default:
                System.out.println("El tipo de la casilla está mal definido");
                break;
        }
    return false;
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
        public void comprarCasilla (Jugador solicitante, Jugador banca){
            // Comprobamos que sea una casilla "comprable"
            if (this.estaEnVenta()==true) {
                if (solicitante.getFortuna()<this.valor) {
                    System.out.println("No tienes dinero  suficiente para comprar esta casilla");
                }else{
                // JUGADOR SOLICITANTE
                
                // "quitar dinero"
                solicitante.setFortuna(solicitante.getFortuna() - this.valor); //le restamos el valor de la casilla comprada a la fortuna del jugador comprador
                solicitante.setDineroInvertido(solicitante.getDineroInvertido() + this.valor); //sumamos el valor de la casilla al atributo dineroInvertido del comprador
    
                // "sumar gastos"
                solicitante.setGastos(solicitante.getGastos() + this.valor);
    
                // "asignarle la propiedad"
                // Primero tenemos que cambiarle el dueño a la casilla
                this.duenho = solicitante;
                solicitante.getPropiedades().add(this);
                    
                // BANCA, aunque realmente es innecesario
                banca.setFortuna(banca.getFortuna() + this.valor);
    
                System.out.println("El jugador " + solicitante.getNombre() + " compra la casilla "+ this.getNombre() + " por " + this.valor);
                System.out.println("Su fortuna actual es:" + solicitante.getFortuna());
                }
            } else if ((this.tipo != "solar") && (this.tipo != "servicio") && (this.tipo != "transporte")) {
                // En caso de que no sea de ninguno de estos tipos, la propiedad no se podrá
                // comprar
                System.out.println("Esta propiedad no se puede comprar, para poder comprar una propiedad debe de ser de uno de los siguientes tipos: SOLAR, TRANSPORTE, SERVICIOS\n");
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
/*
    public void sumarValor(float suma) {
        this.valor += suma;
    }*/

    /*
     * Método para mostrar información sobre una casilla.
     * Devuelve una cadena con información específica de cada tipo de casilla.
     */
    public String infoCasilla() {
        String informacion = new String();
        informacion = this.toString();
        return informacion;
    }

    
    public boolean estaEnVenta(){
        if (((this.tipo == "solar") || (this.tipo == "servicio") || (this.tipo == "transporte"))
                && (this.duenho.getNombre() == "banca")) {
            return true;
        } else {
            return false;

        }
}

    /*
     * Método para mostrar información de una casilla en venta.
     * Valor devuelto: texto con esa información.
     */
    public String casEnVenta() {
        // Para mostrar la informacion tiene que estar en venta, y para estar en venta,
        // el dueño tiene que ser la banca y cumplir los requisitos del primer if de
        // "comprarCasilla"
        if (((this.tipo == "Solar") || (this.tipo == "Servicios") || (this.tipo == "Transporte"))
                && (this.duenho.getNombre() == "Banca")) {
            String texto = this.toString();
            return texto;
        } else {
            String texto = String.format("Esta casilla no está a la venta\n");
            return texto;
        }
    }


    public float devolverImpuesto(Casilla casilla, Jugador jugador){
        Grupo grupo = casilla.getGrupo();

        if (grupo.esDuenhoGrupo(jugador)) {
            return this.impuesto*2;
        }else{
            return this.impuesto;
        }
    }

    public String generarCasilla() {
        
        StringBuilder sb = new StringBuilder();
        
        if (!avatares.isEmpty()){
            sb.append("&");
            for(Avatar avatar:avatares){
                  avatar.getId();
                  sb.append(avatar.getId());
            }
        }

        String color = "";
            if (this.getGrupo() != null) {
                String colorGrupo = this.getGrupo().getColorGrupo(); 
                    switch (colorGrupo) {
                        case "RED":
                            color = Valor.RED;
                            break;
                        case "GREEN":
                           color =  Valor.GREEN;
                            break;
                        case "YELLOW":
                            color = Valor.YELLOW;
                            break;
                        case "BLUE":
                            color = Valor.BLUE; 
                            break;
                        case "PURPLE":
                            color = Valor.PURPLE;
                            break;
                        case "CYAN":
                            color = Valor.CYAN;
                            break;
                        case "BLACK":
                            color = Valor.BLACK;
                            break;
                        case "WHITE":
                            color = Valor.WHITE;
                            break;
                        default:
                            color = "RESET";
                            break;
        }
    }


        String representacionCasilla;

        if (this.getGrupo() == null) {
            representacionCasilla = String.format("%-10s%5s",this.getNombre(),sb);    
        }else{
    
            representacionCasilla = String.format("%s%-10s%s%5s",color,this.getNombre(),Valor.RESET,sb);
        }

        return representacionCasilla;
    }

    public static ArrayList<Integer> crearBaraja() {
        ArrayList<Integer> baraja = new ArrayList<>();

        // Llenar el ArrayList con los valores 1, 2, 3, 4, 5 y 6
        for (int i = 1; i < 7; i++) {
            baraja.add(i);
        }

        return baraja;
    }

    public void barajar(ArrayList<Integer> baraja){
        Collections.shuffle(baraja);
    }

    
    public int elegirCarta() {
        int num = 0; // Inicializar 'num' para que no haya error en la primera comprobación
        Scanner scanner = new Scanner(System.in);

        // Bucle que se repetirá hasta que el usuario ingrese un número válido
        while (num < 1 || num > 6) {
            System.out.println("Introduce un número del 1 al 6:");
            if (scanner.hasNextInt()) { // Verificar que la entrada sea un entero
                num = scanner.nextInt();
            } else {
                System.out.println("Entrada no válida. Por favor, introduce un número.");
                scanner.next(); // Limpiar la entrada inválida del escáner
            }
        }
        return num;
    }

    public void cambiarcasas(){
        int casa = contarCasas();
        if (casa == 4) {
            Iterator<Edificacion> iterator = edificaciones.iterator();
            while (iterator.hasNext()) {
                Edificacion edificacion = iterator.next();
                if (edificacion.getTipo().equals("casa")) {
                    iterator.remove();
                }
            }
        }
    }
    

    public int contarCasas(){
    int contador=0;
    for (Edificacion edificacion: edificaciones){
        if(edificacion.getTipo().equals("casa")){
            contador++;
        }
    }
    return contador;
}

public int contarHoteles(){
    int contador=0;
    for (Edificacion edificacion: edificaciones){
        if(edificacion.getTipo().equals("hotel")){
            contador++;
        }
    }
    return contador;
}

public int contarPiscinas(){
    int contador=0;
    for (Edificacion edificacion: edificaciones){
        if(edificacion.getTipo().equals("piscinas")){
            contador++;
        }
    }
    return contador;
}

public int contarPistas(){
    int contador=0;
    for (Edificacion edificacion: edificaciones){
        if(edificacion.getTipo().equals("pistas")){
            contador++;
        }
    }
    return contador;
}

    public float sumarImpuestoedificios(){
        float suma=0;
        int casas=0,hotel=0,piscina=0,pista=0;
        casas = contarCasas();
        hotel=contarHoteles();
        piscina=contarPiscinas();
        pista=contarPistas();
    
        if (casas==1) {
            suma += this.impuesto*5;
        }
        else if (casas==2) {
            suma += this.impuesto*15;
        }
        else if (casas==3) {
            suma += this.impuesto*35;
        }
        else if (casas==4) {
            suma += this.impuesto*50;
        }
        if (hotel >= 1) {
            suma += this.impuesto * 70 * hotel;
        }
        if (piscina >= 1) {
            suma += this.impuesto * 25 * piscina;
        }
        if (pista >= 1) {
            suma += this.impuesto * 25 * pista;
        }   
        return suma;
        }



public void hipotecarPropiedad(Casilla casilla){
    casilla.setHipotecado(true);
}

public float valorHipoteca(Casilla casilla){
    float valor = 0;
    switch (casilla.getGrupo().getColorGrupo()) {
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
            System.out.println("No se ha encontrado el grupo");
            break;
    }
    return valor;
}

public int Hacienda(Jugador jugador, Tablero tablero){

    if((!jugador.getPropiedades().isEmpty())){ //mientras el jugador tenga propiedades

        System.out.println("¿Qué casilla desea hipotecar?");
        Scanner scanner = new Scanner(System.in);
        String nombre = scanner.nextLine();

        if(tablero.encontrar_casilla(nombre) == null){ //verificamos que la casilla exista
            System.out.println("No se ha podido encontrar la casilla.\n");
            return 0;
        }else{
            for(Casilla casilla:jugador.getPropiedades()){ //verificamos que el jugador tenga la casilla comprada
                if(casilla.getNombre().equals(nombre)){
                    if(casilla.getEdificacion().isEmpty()){
                        jugador.setFortuna(jugador.getFortuna() + 50000f/*(valorHipoteca(casilla)/2)*/);

                        hipotecarPropiedad(casilla);

                        System.out.println(String.format("La casilla %s ha sido hipotecada con éxito.\n", nombre));

                        System.out.println(String.format("%s recibe %f por hipotecar %s. Ahora no puedes recibir alquileres y edificar en el grupo %s.\n", jugador.getNombre(), valorHipoteca(casilla)/2, casilla.getNombre(), casilla.getGrupo().getColorGrupo()));
                        
                        return 1;
                    }else{
                        System.out.println("La casilla tiene edificaciones, debes venderlas antes de poder hipotecar la casilla.\n");
                        return 0;
                    }
                }
            }
            System.out.println(String.format("La casilla %s no pertenece al jugador %s.\n", nombre, jugador.getNombre()));
            return 0;
        }
    }else{
        System.out.println("No tienes más propiedades para hipotecar.\n");
        return 0;
    }
}

public int deshipotecar(Jugador jugador, Tablero tablero){
    
    System.out.println("¿Qué casilla desea deshipotecar?");
    Scanner scanner = new Scanner(System.in);
    String nombre = scanner.nextLine();

    if(tablero.encontrar_casilla(nombre) == null){
        System.out.println("No se ha podido encontrar la casilla.\n");
        return 0;
    }else{
        for(Casilla casilla:jugador.getPropiedades()){
            if(casilla.getNombre().equals(nombre)){
                if(casilla.getHipotecado()){
                    if(jugador.getFortuna() > (valorHipoteca(casilla)*1.1f)){

                        jugador.setFortuna(jugador.getFortuna() - (valorHipoteca(casilla)*1.1f));
                        
                        casilla.setHipotecado(false);
                        
                        System.out.println(String.format("La casilla %s ha sido deshipotecada con éxito.\n", nombre));

                        System.out.println(String.format("%s paga %f por deshipotecar %s. Ahora puedes recibir alquileres y edificar en el grupo %s.\n", jugador.getNombre(), valorHipoteca(casilla)*1.1f, casilla.getNombre(), casilla.getGrupo().getColorGrupo()));
                        return 1;
                    }else{
                        System.out.println(String.format("El jugador %s no tiene suficiente dinero para poder deshipotecar la casilla %s.\n", jugador.getNombre(), casilla.getNombre()));
                        return 0;
                    }
                }else{
                    System.out.println(String.format("La casilla %s no está hipotecada.\n", casilla.getNombre()));
                    return 0;
                }
            }
        }
        System.out.println(String.format("La casilla %s no pertenece al jugador %s.\n", nombre, jugador.getNombre()));
        return 0;
    }
}

public void bancarrotaABanca(Jugador actual, Jugador banca, ArrayList<Jugador> jugadores, ArrayList<Avatar> avatares){ //falta mirar si las propiedades van a la banca o a un jugador determinado
    for(Casilla casilla:actual.getPropiedades()){ //pasamos todas las propiedades del jugador a la banca
        casilla.setDuenho(banca);
    }

    banca.setFortuna(banca.getFortuna() + actual.getFortuna()); //pasamos toda la fortuna del jugador a la banca
    actual.setFortuna(0);

    jugadores.remove(actual);
    avatares.remove(actual.getAvatar());
}

public void bancarrotaAJugador(Jugador actual, Jugador receptor, ArrayList<Jugador> jugadores, ArrayList<Avatar> avatares){

    for(Casilla casilla:actual.getPropiedades()){ //nos aseguramos de que todas las casillas estén libres de edificaciones
        if(!casilla.getEdificacion().isEmpty()){
            casilla.getEdificacion().clear();
        }
    }
    /*for(Casilla casilla:actual.getPropiedades()){ //pasamos todas las propiedades del jugador que llama bancarrota al jugador que las recibe
        receptor.getPropiedades().add(casilla);
        actual.getPropiedades().remove(casilla);
    }*/

    Iterator<Casilla> iterator = actual.getPropiedades().iterator();
    while(iterator.hasNext()){ //pasamos todas las propiedades del jugador que llama bancarrota al jugador que las recibe
        Casilla casilla = iterator.next();
        receptor.getPropiedades().add(casilla);
        iterator.remove();
    }

    receptor.setFortuna(receptor.getFortuna() + actual.getFortuna()); //pasamos el dinero del jugador que llama bancarrota al jugador que lo recibe

    actual.setFortuna(0);

    jugadores.remove(actual); //eliminamos al jugador del ArrayList de jugadores
    avatares.remove(actual.getAvatar()); //eliminamos el avatar del jugador del ArrayList de avatares
    
}

public void analizarMenuPequenho(Jugador actual, Jugador banca, Tablero tablero, Menu menu, Casilla c){
    boolean acabado = false;

    while(!acabado){
        System.out.println("=====================================\n");
        System.out.println("                MENÚ                \n");
        System.out.println("=====================================\n");
        System.out.println("1. Hipotecar una propiedad                            -> Comando: 'hipotecar'");
        System.out.println("2. Declararse en bancarrota                           -> Comando: 'bancarrota'");
        

        System.out.println("=====================================\n");
        System.out.println("Selecciona una opción para continuar.\n");
        System.out.println("=====================================\n\n");


        Scanner scanner = new Scanner(System.in);
        String comando = scanner.nextLine();

        switch (comando) {
            case "hipotecar":
                Hacienda(actual, tablero);
                acabado = true;
                break;        
            case "bancarrota":
                if(!c.getDuenho().equals(banca)){
                    bancarrotaAJugador(actual, c.getDuenho(), menu.getJugadores(), menu.getAvatares());
                    menu.acabarTurno(); //acabamos el turno automáticamente para que sigan jugando el resto
                    System.out.println("Jugador eliminado con éxito. El siguiente jugador puede ahora elegir una opción.\n");
                    acabado = true;
                }else if(c.getDuenho().equals(banca)){
                    bancarrotaABanca(actual, c.getDuenho(), menu.getJugadores(), menu.getAvatares());
                    menu.acabarTurno(); //acabamos el turno automáticamente para que sigan jugando el resto
                    System.out.println("Jugador eliminado con éxito. El siguiente jugador puede ahora elegir una opción.\n");
                    acabado = true;
                }
                break;
            default:
                System.out.println("Error, comando desconocido.\n");
                break;
        }
    }
}

}