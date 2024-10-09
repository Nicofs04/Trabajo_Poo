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
        iniciarPartida();
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

    public boolean getDadosdobles(){
        return dadosdobles;
    }

    public void setDadosdobles(boolean dadosdobles){
        this.dadosdobles = dadosdobles;
    }

    // Método para inciar una partida: crea los jugadores y avatares.
    private void iniciarPartida() {
    
    while (true) {
        System.out.println("=====================================");
        System.out.println("                MENÚ                ");
        System.out.println("=====================================\n");

        System.out.println("1. Crear un jugador               -> Comando: 'crear jugador NombreJugador tipoAvatar'");
        System.out.println("2. Jugador del turno actual       -> Comando: 'jugador'");
        System.out.println("3. Listar jugadores               -> Comando: 'listar jugadores'");
        System.out.println("4. Listar avatares                -> Comando: 'listar avatares'");
        System.out.println("5. Lanzar dados                   -> Comando: 'lanzar dados'");
        System.out.println("6. Acabar turno                   -> Comando: 'acabar turno'");
        System.out.println("7. Salir de la cárcel             -> Comando: 'salir carcel'");
        System.out.println("8. Describir casilla              -> Comando: 'describir casilla'");
        System.out.println("9. Describir jugador              -> Comando: 'describir jugador'");
        System.out.println("10. Describir avatar              -> Comando: 'describir avatar'");
        System.out.println("11. Comprar casilla               -> Comando: 'comprar 'nombreCasilla'");
        System.out.println("12. Listar casillas en venta      -> Comando: 'listarenventa'");
        System.out.println("13. Ver tablero                   -> Comando: 'ver'\n");

        System.out.println("=====================================");
        System.out.println("Selecciona una opción para continuar.");
        System.out.println("=====================================\n");

        Scanner scanner = new Scanner(System.in);
        String comando = scanner.nextLine();
        analizarComando(comando);
        
    }
}
    
    /*Método que interpreta el comando introducido y toma la accion correspondiente.
    * Parámetro: cadena de caracteres (el comando).
    */
    private void analizarComando(String comando) {
        
    String[] palabras = comando.split(" ");

    // Si no hay palabras suficientes, no es un comando válido
    if (palabras.length < 1) {
        System.out.println("Comando inválido.");
    }

    String metodo = palabras[0];

    switch (metodo) {
        case "crear":
            if (palabras.length >= 4 && palabras[1].equals("jugador")) {
                String nombre = palabras[2];
                String tipoAvatar = palabras[3];
                Jugador jugador= new Jugador(nombre, tipoAvatar, tablero.getPosiciones().get(0).get(0), avatares);
                jugadores.add(jugador);
                System.out.println("Jugador creado con éxito.\n");
            } else {
                System.out.println("Comando incompleto o incorrecto para crear jugador.\n");
            }
            break;
        case "jugador":
            System.out.println("Nombre:"+jugadores.get(turno).getNombre());
            System.out.println("Avatar: "+jugadores.get(turno).getAvatar().getId());
            break;
        case "listar":
            if (palabras.length == 2) {
                if (palabras[1].equals("jugadores")) {
                    listarJugadores();
                } else if (palabras[1].equals("avatares")) {
                    listarAvatares();
                } else {
                    System.out.println("Error, comando desconocido.\n");
                }
            } else {
                System.out.println("Error, comando desconocido.\n");
            }
            break;
        case "lanzar":
            if (palabras.length == 2 && palabras[1].equals("dados")) {
                lanzarDados();
                
            } else {
                System.out.println("Error, comando desconocido.\n");
            }
            break;
        case "acabar":
            if (palabras.length == 2 && palabras[1].equals("turno")) {
                acabarTurno();
            } else {
                System.out.println("Error, comando desconocido.\n");
            }
            break;
        case "salir":
            if (palabras.length == 2 && palabras[1].equals("carcel")) {
                salirCarcel();
            } else {
                System.out.println("Error, comando desconocido.\n");
            }
            break;
        case "describir":
            if (palabras.length == 2) {
                String nombreCasilla = palabras[1];
                descCasilla(nombreCasilla);
            }else if(palabras.length == 3){
                if(palabras[1].equals("jugador")){
                    String[] nombreJugador = new String[]{palabras[2]};
                    descJugador(nombreJugador);
                }else if(palabras[1].equals("avatar")){
                    String[] idAvatar = new String[]{palabras[2]};
                    descAvatar(idAvatar);
                }
            }else {
                System.out.println("Error, comando desconocido.\n");
            }
            break;
        case "comprar":
            if (palabras.length == 2) {
                String nombreCasilla = palabras[1];
                comprar(nombreCasilla);
            } else {
                System.out.println("Error, comando desconocido.\n");
            }
            break;
        case "listarenventa":
            listarVenta();
            break;
        case "ver":
            System.out.println(tablero.toString());
            break;

        default:
            System.out.println("Error, comando desconocido.\n");
            break;
    }
    }

    /*Método que realiza las acciones asociadas al comando 'describir jugador'.
    * Parámetro: comando introducido
     */
    private void descJugador(String[] palabras) {
        for(Jugador jugador:jugadores){
            if((jugador.getNombre()).equals(palabras[0])){
                System.out.println("{\nnombre: " + jugador.getNombre() + ",\navatar: " + jugador.getAvatar().getId() + ",\nfortuna: " + jugador.getFortuna() + ",\npropiedades: " + jugador.getPropiedades() + "\nhipotecas: []" + "\nedificios: []" + "\n}\n");
            }
        }
    }

    /*Método que realiza las acciones asociadas al comando 'describir avatar'.
    * Parámetro: id del avatar a describir.
    */
    private void descAvatar(String[] palabras) {
        for(Avatar avatar:avatares){
            if((avatar.getId()).equals(palabras[0])){
                System.out.println("{\nid: " + avatar.getId() + ",\ntipo: " + avatar.getTipo() + ",\ncasilla: " + avatar.getLugar() + ",\njugador: " + avatar.getJugador() + "\n}\n");
            }
        }
    }

    /* Método que realiza las acciones asociadas al comando 'describir nombre_casilla'.
    * Parámetros: nombre de la casilla a describir.
    */
    private void descCasilla(String nombre) {
        Casilla casilla = tablero.encontrar_casilla(nombre);
        casilla.infoCasilla(); //tengo la funcion hecha en Casilla.java
    }

    //Método que ejecuta todas las acciones relacionadas con el comando 'lanzar dados'.
    private void lanzarDados() {
        if (getTirado()) {
            System.out.println("Ya has lanzado los dados en este turno.");
            return;
        }
        
        dado1.hacerTirada();
        dado2.hacerTirada();

        System.out.println("Dado 1: " + dado1.getValor());
        System.out.println("Dado 2: " + dado2.getValor());

        int sumaDados = dado1.getValor() + dado2.getValor();
        setDadosdobles(dado1.equals(dado2));

        //Si el jugador está en la carcel:
        if (jugadores.get(turno).getEnCarcel()) {
            if (getDadosdobles()) {
                System.out.println("Has sacado dobles y sales de la cárcel.");
                jugadores.get(turno).setEnCarcel(false);
            } else {
                jugadores.get(turno).setTiradasCarcel(jugadores.get(turno).getTiradasCarcel() + 1);
                    if (jugadores.get(turno).getTiradasCarcel() >= 3) {
                        System.out.println("Has fallado 3 veces.");
                        jugadores.get(turno).setEnCarcel(false); // Sale de la cárcel después de pagar
                    }
                setTirado(true);
                return;
        }
    }

    String posicionActual=jugadores.get(turno).getAvatar().getLugar().getNombre();

    
    Avatar avatarActual = jugadores.get(turno).getAvatar();
    avatarActual.moverAvatar(tablero.getPosiciones(), sumaDados);

    String posicionFinal=jugadores.get(turno).getAvatar().getLugar().getNombre();

    // Verificar si el jugador ha dado la vuelta al tablero
    if (avatarActual.getLugar().getPosicion() < sumaDados) {
        if(avatarActual.getLugar().getNombre().equals("ircarcel")){
            System.out.println("Has caído en la carcel.\n");
            jugadores.get(turno).encarcelar(tablero.getPosiciones());
        }else{
            jugadores.get(turno).setVueltas(jugadores.get(turno).getVueltas() + 1);
            System.out.println("¡Has pasado por la casilla de salida! Recibes tu recompensa.\n");
            jugadores.get(turno).sumarFortuna(jugadores.get(turno).getAvatar().getLugar().valorSalida(tablero.getPosiciones())); // Suponiendo que reciben 200 por pasar la salida
        }
    }

    // Evaluar la casilla en la que ha caído
    jugadores.get(turno).getAvatar().getLugar().evaluarCasilla(jugadores.get(turno), banca, sumaDados);

    setTirado(true); //El jugador ya ha lanzado los dados en este turno

    // Si sacó dobles, puede volver a tirar
    if (getDadosdobles()) {
        System.out.println("Has sacado dobles, puedes lanzar de nuevo.");
        setTirado(false); // Permitir volver a tirar
        setLanzamientos(getLanzamientos()+1);

        // Si sacó dobles 3 veces, va a la cárcel
        if (getLanzamientos() == 3) {
            System.out.println("Has sacado dobles 3 veces seguidas, vas a la cárcel.");
            jugadores.get(turno).encarcelar(tablero.getPosiciones());
            setTirado(true);
        }
    } else {
        setLanzamientos(0); // Resetear el contador de lanzamientos dobles

    }
    //Repintamos el tablero
    tablero.toString();
    //Imprimimos el mensaje final:
    System.out.println("El avatar: "+jugadores.get(turno).getAvatar().getId()+", avanza "+sumaDados+" posiciones, desde "+posicionActual+" hasta "+posicionFinal);

    
}

    /*Método que ejecuta todas las acciones realizadas con el comando 'comprar nombre_casilla'.
    * Parámetro: cadena de caracteres con el nombre de la casilla.
     */
    private void comprar(String nombre) {
        if(nombre==jugadores.get(turno).getAvatar().getLugar().getNombre()){
            tablero.encontrar_casilla(nombre).comprarCasilla(jugadores.get(turno),banca);
            System.out.println("Casilla comprada con éxito.\n");
        }else{
            System.out.println("No puedes comprar una casilla en la que no estás");
        }
    }
    //Método que ejecuta todas las acciones relacionadas con el comando 'salir carcel'. 
    private void salirCarcel() {
        //Si el jugador está en la cárcel
        if(jugadores.get(turno).getAvatar().getLugar().getNombre()=="carcel"){
            jugadores.get(turno).setEnCarcel(false);
            //Si está en la cárcel y además le llega el dinero:
            if(jugadores.get(turno).getFortuna()>=jugadores.get(turno).getAvatar().getLugar().valorCarcel(tablero.getPosiciones())){
                jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna()-jugadores.get(turno).getAvatar().getLugar().valorCarcel(tablero.getPosiciones()));
            }else{
                System.out.println("No tienes dinero suficiente para salir de la cárcel.");
            }
        }else{
            //Si el jugador no está en la cárcel
            System.out.println("El jugador no está en la cárcel\n");
        }
    }

    // Método que realiza las acciones asociadas al comando 'listar enventa'.
    private void listarVenta() {
        int i;
        StringBuilder sb = new StringBuilder();

        for(i = 0; i < 4; i++){
            for(Casilla casilla:tablero.getPosiciones().get(i)){
                if(casilla.getDuenho().getNombre().equals("banca")){
                    if(casilla.getTipo().equals("solar")){
                        sb.append(String.format("{\n tipo: %s,\n", casilla.getTipo()));
                        sb.append(String.format("{\n grupo: %s,\n", casilla.getGrupo().getColorGrupo()));
                        sb.append(String.format("{\n valor: %s,\n", casilla.getValor()));
                        sb.append("},\n");
                    }else{
                        sb.append(String.format("{\n tipo: %s,\n", casilla.getTipo()));
                        sb.append(String.format("{\n valor: %s,\n", casilla.getValor()));
                        sb.append("},\n");
                    }
                }
            }
        }
        System.out.println(sb.toString());
    }

    // Método que realiza las acciones asociadas al comando 'listar jugadores'.
    private void listarJugadores() {
        for(Jugador jugador:jugadores){
            System.out.println("{\nnombre: " + jugador.getNombre() + ",\navatar: " + jugador.getAvatar().getId() + ",\nfortuna: " + jugador.getFortuna() + ",\npropiedades: " + jugador.getPropiedades() + "\nhipotecas: []" + "\nedificios: []" + "\n}\n");
        }
    }

    // Método que realiza las acciones asociadas al comando 'listar avatares'.
    private void listarAvatares() {
        for(int i=0;i<avatares.size();i++){
            System.out.println("{\nid: " + avatares.get(i).getId() + ",\ntipo: " + avatares.get(i).getTipo() + ",\ncasilla: " + avatares.get(i).getLugar().getNombre() + ",\njugador: " + avatares.get(i).getJugador().getNombre()+ "\n}\n");
        }
    }

    // Método que realiza las acciones asociadas al comando 'acabar turno'.
    private void acabarTurno() {
        
        turno++;

        if (turno>jugadores.size()-1) {
            turno=0;
        }

        setTirado(false);
        setLanzamientos(0);
        setDadosdobles(false);
        
    }
}
