package monopoly;

import java.util.ArrayList;
import java.util.Scanner;

import partida.*;

public class Menu {

    //Atributos
    private ArrayList<Jugador> jugadores; //Jugadores de la partida.
    private ArrayList<Avatar> avatares; //Avatares en la partida.
    private int turno = 0; //Índice correspondiente a la posición en el arrayList del jugador (y el avatar) que tienen el turno
    private int lanzamientos; //Variable para contar el número de lanzamientos de un jugador en un turno.
    private Tablero tablero; //Tablero en el que se juega.
    private Dado dado1; //Dos dados para lanzar y avanzar casillas.
    private Dado dado2;
    private Jugador banca; //El jugador banca.
    private boolean tirado; //Booleano para comprobar si el jugador que tiene el turno ha tirado o no.
    private boolean solvente; //Booleano para comprobar si el jugador que tiene el turno es solvente, es decir, si ha pagado sus deudas.
    private boolean dadosdobles;

    public Menu(){
        this.jugadores = new ArrayList<Jugador>();
        this.avatares = new ArrayList<Avatar>();
        this.banca = new Jugador();
        this.tablero = new Tablero(banca);
        this.dado1 = new Dado();
        this.dado2 = new Dado();
    }

    public ArrayList<Jugador> getJugadores(){
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores){
        this.jugadores = jugadores;
    }

    public ArrayList<Avatar> getAvatares(){
        return avatares;
    }

    public void setAvatares(ArrayList<Avatar> avatares){
        this.avatares = avatares;
    }

    public int getTurno(){
        return turno;
    }

    public void setTurno(int turno){
        this.turno = turno;
    }

    public int getLanzamientos(){
        return lanzamientos;
    }

    public void setLanzamientos(int lanzamientos){
        this.lanzamientos = lanzamientos;
    }

    public Tablero getTablero(){
        return tablero;
    }

    public void setTablero(Tablero tablero){
        this.tablero = tablero;
    }

    public Dado getDado1(){
        return dado1;
    }

    public void setDado1(Dado dado1){
        this.dado1 = dado1;
    }

    public Dado getDado2(){
        return dado2;
    }

    public void setDado2(Dado dado2){
        this.dado2 = dado2;
    }

    public Jugador getBanca(){
        return banca;
    }

    public void setBanca(Jugador banca){
        this.banca = banca;
    }

    public boolean getTirado(){
        return tirado;
    }

    public void setTirado(boolean tirado){
        this.tirado = tirado;
    }

    public boolean getSolvente(){
        return solvente;
    }

    public void setSolvente(boolean solvente){
        this.solvente = solvente;
    }

    // Método para inciar una partida: crea los jugadores y avatares.
    private void iniciarPartida() {
        new Menu();
        jugadores.add(banca);

        Scanner scanner = new Scanner(System.in);
        String comando = scanner.nextLine();
        analizarComando(comando);
    }
    
    /*Método que interpreta el comando introducido y toma la accion correspondiente.
    * Parámetro: cadena de caracteres (el comando).
    */
    private void analizarComando(String comando) {
        String[] palabras = comando.split(" ");
        String metodo = palabras[0] + " " + palabras[1];
        String nombre = palabras[2];
        String avatar = palabras[3];

        switch(metodo){
            case "crear jugador":
                //new Jugador(nombre, ;
                break;
            case "describir jugador":
                descJugador(palabras);
                break;
            case "describir avatar":
                descAvatar(palabras);
                break;
            case "describir":

                break;
            case "lanzar dados":
                lanzarDados();
                break;



        }


        /*if(metodo.equals("crear jugador")){

        }else if(metodo.equals("descJugador")){
            descJugador(palabras);
        }*/
    }

    /*Método que realiza las acciones asociadas al comando 'describir jugador'.
    * Parámetro: comando introducido
     */
    private void descJugador(String[] palabras) {
        for(Jugador jugador:jugadores){
            if((jugador.getNombre()).equals(palabras[2])){
                System.out.println("{\nnombre: " + jugador.getNombre() + ",\navatar: " + jugador.getAvatar() + ",\nfortuna: " + jugador.getFortuna() + ",\npropiedades: " + /*listarPropiedades() +*/ "\nhipotecas: []" + "\nedificios: []" + "\n}\n");
            }
        }
    }

    /*Método que realiza las acciones asociadas al comando 'describir avatar'.
    * Parámetro: id del avatar a describir.
    */
    private void descAvatar(String[] palabras) {
        for(Avatar avatar:avatares){
            if((avatar.getId()).equals(palabras[2])){
                System.out.println("{\nid: " + avatar.getId() + ",\ntipo: " + avatar.getTipo() + ",\ncasilla: " + /*avatar.getCasilla() +*/ ",\njugador: " + avatar.getJugador() + "\n}\n");
            }
        }
    }

    /* Método que realiza las acciones asociadas al comando 'describir nombre_casilla'.
    * Parámetros: nombre de la casilla a describir.
    */
    private void descCasilla(String nombre) {
        Casilla casilla = tablero.encontrar_casilla(nombre);
        System.out.println(casilla); //tengo la funcion hecha en Casilla.java
    }

    //Método que ejecuta todas las acciones relacionadas con el comando 'lanzar dados'.
    private void lanzarDados() {
        int contador=0;
        do{
            dado1.hacerTirada();
            dado2.hacerTirada();

            System.out.println("Dado 1: "+dado1.getValor());
            System.out.println("Dado 2: "+dado2.getValor());
            dadosdobles=dado1.equals(dado2);
            if (dadosdobles) {
                System.out.println("Sacaste dobles");
                contador++;
                if (contador==3) {
                    System.out.println("Sacaste dobles 3 veces, serás enviado a la cárcel");
                    //ircarcel
                    break;
                }
            }else{
                contador=0;
                }
            moverAvatar(tablero,dado1.getValor()+dado2.getValor());     
        //evalur casilla

    }while(dadosdobles);
   }

    /*Método que ejecuta todas las acciones realizadas con el comando 'comprar nombre_casilla'.
    * Parámetro: cadena de caracteres con el nombre de la casilla.
     */
    private void comprar(String nombre) {
        jugadores.get(turno)

    }

    //Método que ejecuta todas las acciones relacionadas con el comando 'salir carcel'. 
    private void salirCarcel() {
    }

    // Método que realiza las acciones asociadas al comando 'listar enventa'.
    private void listarVenta() {
    }

    // Método que realiza las acciones asociadas al comando 'listar jugadores'.
    private void listarJugadores() {
    }

    // Método que realiza las acciones asociadas al comando 'listar avatares'.
    private void listarAvatares() {
    }

    // Método que realiza las acciones asociadas al comando 'acabar turno'.
    private void acabarTurno() {
    }

}
