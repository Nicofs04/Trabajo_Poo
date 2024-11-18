package partida;

import monopoly.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Avatar {

    // Atributos
    private String id; // Identificador: una letra generada aleatoriamente.
    private String tipo; // sombrero, esfinge, pelota, coche
    private Jugador jugador; // Un jugador al que pertenece ese avatar.
    private Casilla lugar; // Los avatares se sitúan en casillas del tablero.
    private int avanzado = 0; // 1 si el movimiento del avatar está en modo avanzado y 0 si no lo está
    private int compras = 0;// Variable para contabilizar las compras de vivienda que se han realizado con el mov avanzado de coche
    private int restriccionTiradas=0; //Cuando se activa se pone a 2, y cada vez que pase un turno se le resta 1 turno sin tirar restante

    // Constructor vacío
    public Avatar() {
    }

    /*
     * Constructor principal. Requiere estos parámetros:
     * Tipo del avatar, jugador al que pertenece, lugar en el que estará ubicado, y
     * un arraylist con los
     * avatares creados (usado para crear un ID distinto del de los demás avatares).
     */
    public Avatar(String tipo, Jugador jugador, Casilla lugar, ArrayList<Avatar> avCreados) {
        this.tipo = tipo;
        this.jugador = jugador;
        this.lugar = lugar;
        generarId(avCreados);
        avCreados.add(this);
        lugar.anhadirAvatar(this);
    }

    // GETTERS

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Casilla getLugar() {
        return lugar;
    }

    public int getAvanzado(){
        return avanzado;
    }
    public int getCompras(){
        return compras;
    }
    public int getRestriccionTiradas(){
        return restriccionTiradas;
    }
    // SETTERS

    public void setId(String ID) {
        this.id = ID;
    }

    public void setTipo(String TIPO) {
        this.tipo = TIPO;
    }

    public void setId(Jugador JUGADOR) {
        this.jugador = JUGADOR;
    }

    public void setLugar(Casilla LUGAR) {
        this.lugar = LUGAR;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    public void setCompras(int compras){
        this.compras=compras;
    }
    public void setRestriccionTiradas(int restriccionTiradas){
        this.restriccionTiradas=restriccionTiradas;
    }

    public void setAvanzado(int avanzado){
        this.avanzado=avanzado;
    }

    // Método que permite mover a un avatar a una casilla concreta.
    public void moverAvatar(ArrayList<ArrayList<Casilla>> tablero, int valorTirada, Menu menu) {
        switch (this.avanzado) {
            // MOVIMIENTO AVANZADO
            case 1:
                String tipo = new String();
                tipo = this.tipo;
                if (tipo.equals("pelota")) {
                    // Obtener la posición actual del lugar del avatar
                    int posicionActual = lugar.getPosicion();
                    // Si valorTirada>4 va para delante y a partir del cuarto desplazamiento se para
                    // a evaluar cada casilla impar
                    // Si valorTirada<=4 va para atrás
                    if (valorTirada > 4) {
                        // Primero nos movemos 4 posiciones hacia delante seguidas:
                        int nuevaPosicion = (posicionActual + 4) % 40; // Usar el módulo para asegurarte de que vuelva
                                                                       // al inicio si excede 39
                        lugar.eliminarAvatar(this);
                        for (int i = 0; i < tablero.size(); i++) {
                            for (int j = 0; j < tablero.get(i).size(); j++) {
                                if (tablero.get(i).get(j).getPosicion() == nuevaPosicion) {
                                    lugar = tablero.get(i).get(j);
                                }
                            }
                        }
                        lugar.anhadirAvatar(this);
                        for (int I = 1; I < (valorTirada - 3); I++) {
                            // Si es un índice impar evaluamos la casilla.
                            if (I % 2 != 0) {
                                nuevaPosicion = (nuevaPosicion + 1) % 40;
                                lugar.eliminarAvatar(this);
                                for (int k = 0; k < tablero.size(); k++) {
                                    for (int l = 0; l < tablero.get(k).size(); l++) {
                                        if (tablero.get(k).get(l).getPosicion() == nuevaPosicion) {
                                            lugar = tablero.get(k).get(l);
                                        }
                                    }
                                }
                                lugar.anhadirAvatar(this);
                                /*
                                 * No necesitamos llamar a evaluarCasilla porque simplemente hay dos opciones:
                                 * 1.Pagar el impuesto correspondiente si la casilla es propiedad de un jugador
                                 * 2.Decidir si comprar o pasar de largo si la casilla es propiedad de la banca
                                 */
                                // 2.
                                if (this.lugar.estaEnVenta() == true) {

                                    System.out.println("Quieres comprar la casilla "+this.lugar.getNombre()+"?");
                                    System.out.println("Escribe 'si' si la quieres comprar y 'no' si no la quieres comprar");
                                    Scanner scanner = new Scanner(System.in);
                                    String respuesta = scanner.nextLine();
                                    if (respuesta.equals("si")) {
                                        this.lugar.comprarCasilla(this.jugador, menu.getBanca());
                                    }

                                // 1.
                                } else {
                                    // el arg valorTirada de momento no lo usamos en evaluarCasilla
                                    String auxiliar = new String();
                                    auxiliar= this.getLugar().getTipo();

                                    this.getLugar().evaluarCasilla(menu.getTablero(), this.getJugador(),menu.getBanca(), valorTirada, menu);
                                    //Después de evaluarCasilla tenemos que verificar si está en la cárcel debido a una carta para salir de la función
                                    if(auxiliar.equals("suerte")|| auxiliar.equals("caja")){
                                        if (this.lugar.getPosicion() == 10) {
                                            //Salimos de la funcion
                                            return;
                                        }

                                    }

                                }

                                if (this.lugar.getPosicion() == 30) {
                                    System.out.println("Has pisado la casilla irCárcel, vas a la cárcel");
                                    this.jugador.encarcelar(tablero);
                                    //Salimos de la funcion
                                    return;
                                }
                                // Si es un índice par, no evaluamos la casilla,solo avanzamos una posicion a menos que sea la última del movimiento que si que la evaluaremos
                            } else{
                                //Si es la ultima:
                                if(I==valorTirada-4){
                                    nuevaPosicion = (nuevaPosicion + 1) % 40;
                                    lugar.eliminarAvatar(this);
                                    for (int k = 0; k < tablero.size(); k++) {
                                        for (int l = 0; l < tablero.get(k).size(); l++) {
                                            if (tablero.get(k).get(l).getPosicion() == nuevaPosicion) {
                                                lugar = tablero.get(k).get(l);
                                            }
                                        }
                                    }
                                    lugar.anhadirAvatar(this);
                                    /*
                                     * No necesitamos llamar a evaluarCasilla porque simplemente hay dos opciones:
                                     * 1.Pagar el impuesto correspondiente si la casilla es propiedad de un jugador
                                     * 2.Decidir si comprar o pasar de largo si la casilla es propiedad de la banca
                                     */
                                    // 2.

                                    if (this.lugar.estaEnVenta() == true) {
    
                                        System.out.println("Quieres comprar la casilla "+this.lugar.getNombre()+"?");
                                        System.out.println("Escribe 'si' si la quieres comprar y 'no' si no la quieres comprar");
                                        Scanner scanner = new Scanner(System.in);
                                        String respuesta = scanner.nextLine();
                                        if (respuesta.equals("si")) {
                                            this.lugar.comprarCasilla(this.jugador, menu.getBanca());
                                        }
    
                                    // 1.
                                    } else {
                                        // el arg valorTirada de momento no lo usamos en evaluarCasilla
                                        this.getLugar().evaluarCasilla(menu.getTablero(), this.getJugador(),menu.getBanca(), valorTirada, menu);
                                        //Después de evaluarCasilla tenemos que verificar si está en la cárcel debido a una carta para salir de la función
                                        if (this.lugar.getPosicion() == 30) {
                                            System.out.println("Has pisado la casilla irCárcel, vas a la cárcel");
                                            this.jugador.encarcelar(tablero);
                                            //Salimos de la funcion
                                            return;
                                        }
    
                                    }

                                    
                                //Si no es la última
                                }else{
                                    nuevaPosicion = (nuevaPosicion + 1) % 40;
                                    lugar.eliminarAvatar(this);
                                    for (int k = 0; k < tablero.size(); k++) {
                                        for (int l = 0; l < tablero.get(k).size(); l++) {
                                            if (tablero.get(k).get(l).getPosicion() == nuevaPosicion) {
                                                lugar = tablero.get(k).get(l);
                                            }
                                        }
                                    }
                                    lugar.anhadirAvatar(this);


                                }



                            }

                        }
                        // Movimiento hacia atrás, la casilla en la que cae ya se evalua sola en lanzar dados
                    } else {
                        int nuevaPosicion=0; 
                        //Si no tenemos que pasar por la casilla de salida
                        if(posicionActual>valorTirada){
                            nuevaPosicion= (posicionActual - valorTirada) % 40;
                            lugar.eliminarAvatar(this);
                            for (int i = 0; i < tablero.size(); i++) {
                                for (int j = 0; j < tablero.get(i).size(); j++) {
                                    if (tablero.get(i).get(j).getPosicion() == nuevaPosicion) {
                                        lugar = tablero.get(i).get(j);
                                    }
                                }
                            }
                            lugar.anhadirAvatar(this);
                        //Si tenemos que pasar por la casilla de salida
                        }else{
                            //Le restamos al valor de la tirada las posiciones que se tiene que mover para llegar a la salida
                            valorTirada=valorTirada-posicionActual;
                            //La nueva posición será 40(la salida)- el valor de la tirada actualizado
                            nuevaPosicion=40-valorTirada;
                            lugar.eliminarAvatar(this);
                            for (int i = 0; i<tablero.size(); i++) {
                                for (int j = 0; j< tablero.get(i).size(); j++) {
                                    if (tablero.get(i).get(j).getPosicion() == nuevaPosicion) {
                                        lugar = tablero.get(i).get(j);
                                    }
                                }
                            }
                            lugar.anhadirAvatar(this);

                        }

                    }
                }else if (tipo.equals("coche")) {
                    // Obtener la posición actual del lugar del avatar
                    int posicionActual = lugar.getPosicion();
                    if (valorTirada > 4) {
                        // Avanzamos tantas casillas como valor de la tirada del intento actual
                        int nuevaPosicion = (posicionActual + valorTirada) % 40; 
                        lugar.eliminarAvatar(this);
                        for (int i = 0; i < tablero.size(); i++) {
                            for (int j = 0; j < tablero.get(i).size(); j++) {
                                if (tablero.get(i).get(j).getPosicion() == nuevaPosicion) {
                                    lugar = tablero.get(i).get(j);
                                }
                            }
                        }
                        lugar.anhadirAvatar(this);
                        if (this.lugar.getDuenho().equals("banca") && this.lugar.estaEnVenta() == true) {
                            if (compras <= 1) {
                                System.out.println("Quieres comprar la casilla %s?" + this.lugar.getNombre());
                                System.out.println("Escribe 'si' si la quieres comprar y 'no' si no la quieres comprar");
                                Scanner scanner = new Scanner(System.in);
                                String respuesta = scanner.nextLine();
                                if (respuesta.equals("si")) {
                                    this.jugador.setFortuna(this.jugador.getFortuna() - this.lugar.getValor());
                                    this.lugar.setDuenho(jugador);
                                    compras++;
                                }

                            } else {
                                System.out.println("Ya has comprado una casilla este turno");
                            }
                        }

                        System.out.println("Quieres edificar en esta casilla?");
                        System.out.println("Escribe 'si' si quieres edificar y 'no' si no quieres edificar");
                        Scanner scanner = new Scanner(System.in);
                        String respuesta = scanner.nextLine();
                        if (respuesta.equals("si")) {
                            boolean comprobante = false;
                            while (comprobante) {
                                Scanner scanner2 = new Scanner(System.in);
                                System.out.println("=====================================\n");
                                System.out.println("                MENÚ                \n");
                                System.out.println("=====================================\n");
                                System.out.println("1. Construir una casa                              -> Comando: 'edificar casa'");
                                System.out.println("2. Construir un hotel                           -> Comando: 'edificar hotel'");
                                System.out.println("3. Construir una piscina                           -> Comando: 'edificar piscina'");
                                System.out.println("4. Construir una pista                           -> Comando: 'edificar pista'");
        

                                System.out.println("=====================================\n");
                                System.out.println("Selecciona una opción para continuar.\n");
                                System.out.println("=====================================\n\n");
                                

                                String comando = scanner2.nextLine();
                                String[] palabras = comando.split(" ");

                                if (palabras.length <= 1 || palabras.length > 2) {
                                    System.out.println("Comando inválido.");
                                }
                                String metodo = palabras[0];

                                switch (metodo) {   
                                    case "edificar":
                                        if(palabras.length==2){
                                            String tipoEdificacion=palabras[1];
                                                if(tipoEdificacion.equals("casa")){
                                                    menu.edificarCasa();
                                                }else if(tipoEdificacion.equals("hotel")){
                                                    menu.edificarHotel();
                                                }else if(tipoEdificacion.equals("piscina")){
                                                    menu.edificarPiscina();
                                                }else if(tipoEdificacion.equals("pista")){
                                                    menu.edificarPista();
                                                }
                                        }
                                        break;
                                    
                                }
                                
                            }
                        }

                    //El valor de la tirada es 4 o menos, lo mismo que para pelota pero aplicando la restricción
                    }else{
                        int nuevaPosicion=0; 
                        //Si no tenemos que pasar por la casilla de salida
                        if(posicionActual>valorTirada){
                            nuevaPosicion= (posicionActual - valorTirada) % 40;
                            lugar.eliminarAvatar(this);
                            for (int i = 0; i < tablero.size(); i++) {
                                for (int j = 0; j < tablero.get(i).size(); j++) {
                                    if (tablero.get(i).get(j).getPosicion() == nuevaPosicion) {
                                        lugar = tablero.get(i).get(j);
                                    }
                                }
                            }
                            lugar.anhadirAvatar(this);
                        //Si tenemos que pasar por la casilla de salida
                        }else{
                            //Le restamos al valor de la tirada las posiciones que se tiene que mover para llegar a la salida
                            valorTirada=valorTirada-posicionActual;
                            //La nueva posición será 40(la salida)- el valor de la tirada actualizado
                            nuevaPosicion=40-valorTirada;
                            lugar.eliminarAvatar(this);
                            for (int i = 0; i<tablero.size(); i++) {
                                for (int j = 0; j< tablero.get(i).size(); j++) {
                                    if (tablero.get(i).get(j).getPosicion() == nuevaPosicion) {
                                        lugar = tablero.get(i).get(j);
                                    }
                                }
                            }
                            lugar.anhadirAvatar(this);

                        }
                        //Marcamos la restriccion que se aplicará en lanzar dados.
                        setRestriccionTiradas(2);

                    }
                }
                break;
            // MOVIMIENTO REGULAR
            case 0:
                // Obtener la posición actual del lugar del avatar
                int posicionActual = lugar.getPosicion();

                // Calcula la nueva posición después de mover
                int nuevaPosicion3 = (posicionActual + valorTirada) % 40; // Usar el módulo para asegurarte de que
                                                                          // vuelva al inicio si excede 39
                lugar.eliminarAvatar(this);

                /*
                 * if (nuevaPosicion == 30) {
                 * System.out.println("Has caído en la carcel.\n");
                 * lugar=tablero.get(1).get(0);
                 * 
                 * }else{
                 */

                // Iteramos sobre los lados del tablero con el indice i y sobre las casillas de
                // cada lado con el indice j:
                for (int i = 0; i < tablero.size(); i++) {
                    for (int j = 0; j < tablero.get(i).size(); j++) {
                        if (tablero.get(i).get(j).getPosicion() == nuevaPosicion3) {
                            lugar = tablero.get(i).get(j);
                        }
                    }
                }
                lugar.anhadirAvatar(this);

                break;
            default:
                break;

        }
    }


    /*
     * Método que permite generar un ID para un avatar. Solo lo usamos en esta clase
     * (por ello es privado).
     * El ID generado será una letra mayúscula. Parámetros:
     * - Un arraylist de los avatares ya creados, con el objetivo de evitar que se
     * generen dos ID iguales.
     */
    private void generarId(ArrayList<Avatar> avCreados) {
        Random random = new Random();
        char id;

        while (true) {
            // Genera un ID aleatorio entre 'A' y 'Z'
            id = (char) ('A' + random.nextInt(26)); // 'A' es el 65 en ASCII

            boolean idExistente = false;

            // Comprobamos si el ID ya existe en los avatares 
            for (int i = 0; i < avCreados.size(); i++) {
                if (avCreados.get(i).getId().equals(String.valueOf(id))) {
                    idExistente = true; 
                    break; 
                }
            }

            // Si el ID no existe, lo asignamos al avatar actual
            if (!idExistente) {
                this.id = String.valueOf(id); 
                break; 
            }
        }
    }

}
