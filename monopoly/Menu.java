package monopoly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import partida.*;

public class Menu {

    //Atributos
    private ArrayList<Jugador> jugadores; //Jugadores de la partida.
    private ArrayList<Avatar> avatares; //Avatares en la partida.
    private int turno = 0; //Índice correspondiente a la posición en el arrayList del jugador (y el avatar) que tienen el turno
    private int lanzamientos; //Variable para contar el número de lanzamientos de un jugador en un turno.
    private int lanzamientosDobles;//Num de veces seguidas que se han lanzado dobles
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
    public int getLanzamientosDobles(){
        return lanzamientosDobles;
    }
    public void setLanzamientosDobles(int lanzamientosDobles){
        this.lanzamientosDobles=lanzamientosDobles;
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
        System.out.println("=====================================\n");
        System.out.println("                MENÚ                \n");
        System.out.println("=====================================\n");

        System.out.println("1. Crear un jugador                                    -> Comando: 'crear jugador NombreJugador tipoAvatar'");
        System.out.println("2. Jugador del turno actual                            -> Comando: 'jugador'");
        System.out.println("3. Listar jugadores                                    -> Comando: 'listar jugadores'");
        System.out.println("4. Listar avatares                                     -> Comando: 'listar avatares'");
        System.out.println("5. Lanzar dados                                        -> Comando: 'lanzar dados'");
        System.out.println("6. Acabar turno                                        -> Comando: 'acabar turno'");
        System.out.println("7. Salir de la cárcel                                  -> Comando: 'salir carcel'");
        System.out.println("8. Describir casilla                                   -> Comando: 'describir nombreCasilla'");
        System.out.println("9. Describir jugador                                   -> Comando: 'describir jugador nombreJugador'");
        System.out.println("10. Describir avatar                                   -> Comando: 'describir avatar idAvatar'");
        System.out.println("11. Comprar casilla                                    -> Comando: 'comprar 'nombreCasilla'");
        System.out.println("12. Listar casillas en venta                           -> Comando: 'listarenventa'");
        System.out.println("13. Ver tablero                                        -> Comando: 'ver'");
        System.out.println("14. Construir un edificio                              -> Comando: 'edificar tipoEdificacion'");
        System.out.println("15. Listar edificios construidos                       -> Comando: 'listar edificios'");
        System.out.println("16. Listar edificios construidos en grupo              -> Comando: 'listar edificios colorGrupo'");
        System.out.println("17. Hipotecar una propiedad                            -> Comando: 'hipotecar nombrePropiedad'");
        System.out.println("18. Declararse en bancarrota                           -> Comando: 'bancarrota'");
        System.out.println("19. Deshipotecar una propiedad                         -> Comando: 'deshipotecar nombrePropiedad'");
        System.out.println("20. Vender edificios                                   -> Comando: 'vender tipoEdificacion nombrePropiedad numeroElementosAvender'");
        System.out.println("21. Mostrar estadísticas de un jugador                 -> Comando: 'estadisticas nombreJugador'");
        System.out.println("22. Mostrar estadísticas del juego                     -> Comando: 'estadisticas'");
        System.out.println("23. Cambiar modo de movimiento de los avatares         -> Comando: 'cambiar modo'\n");
               

        System.out.println("=====================================\n");
        System.out.println("Selecciona una opción para continuar.\n");
        System.out.println("=====================================\n\n");

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
                String tipoAvatar = new String();
    
                if(palabras[3].equals("sombrero") || palabras[3].equals("esfinge") || palabras[3].equals("coche") || palabras[3].equals("pelota")) {
                    tipoAvatar = palabras[3];
                    Jugador jugador = new Jugador(nombre, tipoAvatar, tablero.getPosiciones().get(0).get(0), avatares);
                    jugadores.add(jugador);
                    System.out.println("Jugador creado con éxito.\n");
                } else {
                    System.out.println("El avatar debe ser del tipo pelota, esfinge, coche o sombrero.\n");
                }
            } else {
                System.out.println("Comando incompleto o incorrecto para crear jugador.\n");
            }
            break;
    
        case "jugador":
            if(jugadores.size()>0){
                System.out.println("Nombre:"+jugadores.get(turno).getNombre());
                System.out.println("Avatar: "+jugadores.get(turno).getAvatar().getId());

            }else{
                System.out.println("No hay ningún jugador creado en la partida");
            }
            break;
        case "listar":
            if (palabras.length == 2) {
                if (palabras[1].equals("jugadores")) {
                    listarJugadores();
                } else if (palabras[1].equals("avatares")) {
                    listarAvatares();
                }else if(palabras[1].equals("edificios")){
                    listarEdificios();
                }
            }else if(palabras.length==3){
                    //listarEdificiosGrupo();
            }else {
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
            if(jugadores.size()>0){
                if (palabras.length == 2 && palabras[1].equals("carcel")) {
                    salirCarcel();
                } else {
                    System.out.println("Error, comando desconocido.\n");
                }
            }else{
                System.out.println("No hay jugadores creados en la partida");
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
            }else {
                System.out.println("Error, comando desconocido.\n");
            }
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
        case "edificar":
            if(palabras.length==2){
                String tipoEdificacion=palabras[1];
                if(tipoEdificacion.equals("casa")){
                    edificarCasa();
                }else if(tipoEdificacion.equals("hotel")){
                    edificarHotel();
                }else if(tipoEdificacion.equals("piscina")){
                    edificarPiscina();
                }else if(tipoEdificacion.equals("pista")){
                    edificarPista();
                }
            }
            break;
        case "hipotecar":
            if(palabras.length==1){
                jugadores.get(turno).getAvatar().getLugar().Hacienda(jugadores.get(turno), tablero);

            }
            break;
        case "bancarrota":
            //bancarrota();
            break;
        case "deshipotecar":
            if(palabras.length==1){
                jugadores.get(turno).getAvatar().getLugar().deshipotecar(jugadores.get(turno), tablero);
            }
            break;    
        case "vender":
            if(palabras.length==4){
                String tipoEdificacion=palabras[1];
                String nombreCasilla=palabras[2];
                String numeroventa = palabras[3];
                int numvender = Integer.parseInt(numeroventa);
                if(tipoEdificacion.equals("casa")){
                    venderCasa(nombreCasilla,numvender);
                }else if(tipoEdificacion.equals("hotel")){
                    venderHotel(nombreCasilla,numvender);
                }else if(tipoEdificacion.equals("piscina")){
                    venderPiscina(nombreCasilla,numvender);
                }else if(tipoEdificacion.equals("deporte")){
                    venderPista(nombreCasilla,numvender);
                }
            }    
            break;
        case "estadisticas":
            if(palabras.length==1){
                estadisticasJuego();
            }else if(palabras.length==2){
                estadisticasJugador(palabras[1]);
            }
            break;
        case "cambiar":
            if(palabras.length==2){
                jugadores.get(turno).getAvatar().setAvanzado(1);
            }
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
                System.out.println("{\n\tNombre: " + jugador.getNombre() + 
                   ",\n\tAvatar: " + jugador.getAvatar().getId() + 
                   ",\n\tFortuna: " + jugador.getFortuna() + 
                   ",\n\tHipotecas: []"+
                   ",\n\tPropiedades: ");
                    for(Casilla casilla : jugador.getPropiedades()){ 
                        System.out.println("\t\t"+ casilla.getNombre().toUpperCase()+
                                           "\n\t\t{\tcasas: " + casilla.contarCasas() + 
                                           "\n\t\t\thoteles: " + casilla.contarHoteles() + 
                                           "\n\t\t\tpiscinas: " + casilla.contarPiscinas() + 
                                           "\n\t\t\tpistas de deporte: " + casilla.contarPistas()+
                                           "\n\t\t}"); 
                            System.out.println("}"); }
                   
            }
        }
    }

    /*Método que realiza las acciones asociadas al comando 'describir avatar'.
    * Parámetro: id del avatar a describir.
    */
    private void descAvatar(String[] palabras) {
        for(Avatar avatar:avatares){
            if((avatar.getId()).equals(palabras[0])){
                System.out.println("{\nid: " + avatar.getId() + ",\ntipo: " + avatar.getTipo() + ",\ncasilla: " + avatar.getLugar().getNombre() + ",\njugador: " + avatar.getJugador().getNombre() + "\n}\n");
            }
        }
    }

    /* Método que realiza las acciones asociadas al comando 'describir nombre_casilla'.
    * Parámetros: nombre de la casilla a describir.
    */
    private void descCasilla(String nombre) {
        Casilla casilla = tablero.encontrar_casilla(nombre);
        if (casilla==null){
            System.out.println("Esa casilla no existe");
        } else{
            casilla.setTablero(tablero.getPosiciones());
            System.out.println(casilla.infoCasilla());
        }   
    }

    //Método que ejecuta todas las acciones relacionadas con el comando 'lanzar dados'.
    private void lanzarDados() {
        if (getTirado()) {
            System.out.println("Ya has lanzado los dados en este turno.");
            return;
        }
        //Si existe una restricción de turnos sin tirar(mov avanzado coche), no podremos lanzar los dados
        if(jugadores.get(turno).getAvatar().getRestriccionTiradas()!=0){
            System.out.println("Debido a la restricción del mov avanzado de coche, tiene que esperar %d turnos para volver a lanzar los dados"+jugadores.get(turno).getAvatar().getRestriccionTiradas());
            //Le restamos 1 a los turnos restantes para poder volver a tirar
            jugadores.get(turno).getAvatar().setRestriccionTiradas(jugadores.get(turno).getAvatar().getRestriccionTiradas()-1);
            return;
        }
        

        //LANZAR DADOS ALEATORIOS
        /*dado1.hacerTirada();
        dado2.hacerTirada();

        System.out.println("Dado 1: " + dado1.getValor());
        System.out.println("Dado 2: " + dado2.getValor());*/

        
        
        //LANZAR DADOS MANUAL
        
        
        Scanner scanner = new Scanner(System.in);    
        System.out.println("Introduce dado1: ");
        int da = scanner.nextInt();
        dado1.setValor(da);
        System.out.println("Introduce dado2: ");
        int da2 = scanner.nextInt();
        dado2.setValor(da2);

        jugadores.get(turno).setVecesDados(jugadores.get(turno).getVecesDados() + 1); //sumamos 1 al jugador que lanza los dados en el atributo vecesDados para saber cuantas veces lanzó los dados

        int sumaDados = dado1.getValor() + dado2.getValor();
        setDadosdobles(dado1.equals(dado2));

        //Si el jugador está en la carcel:
        if (jugadores.get(turno).getEnCarcel()) {
            if (getDadosdobles()) {
                System.out.println("Has sacado dobles y sales de la cárcel.");

                jugadores.get(turno).setEnCarcel(false);

                String posicionActual = jugadores.get(turno).getAvatar().getLugar().getNombre();
                
                jugadores.get(turno).getAvatar().moverAvatar(tablero.getPosiciones(), sumaDados,this);

                String posicionFinal = jugadores.get(turno).getAvatar().getLugar().getNombre();

                //Repintamos el tablero
                System.out.println(tablero.toString());

                //Imprimimos el mensaje final:
                System.out.println("El avatar: "+jugadores.get(turno).getAvatar().getId()+", avanza "+sumaDados+" posiciones, desde "+posicionActual+" hasta "+posicionFinal);    

            } else {
                jugadores.get(turno).setTiradasCarcel(jugadores.get(turno).getTiradasCarcel() + 1);
                    if (jugadores.get(turno).getTiradasCarcel() >= 3) {

                        System.out.println("Has fallado 3 veces.");
                        jugadores.get(turno).setEnCarcel(false);
                        
                        //Calculamos la posición inicial
                        String posicionActual = jugadores.get(turno).getAvatar().getLugar().getNombre();
                        jugadores.get(turno).getAvatar().moverAvatar(tablero.getPosiciones(), sumaDados,this);

                        //Calculamos la posición final
                        String posicionFinal = jugadores.get(turno).getAvatar().getLugar().getNombre();

                        //Repintamos el tablero
                        System.out.println(tablero.toString());

                        
                        //Imprimimos el mensaje final:
                        System.out.println("El avatar: "+jugadores.get(turno).getAvatar().getId()+", avanza "+sumaDados+" posiciones, desde "+posicionActual+" hasta "+posicionFinal);
                    }else{
                        System.out.println("No puedes moverte porque estás en la cárcel");
                    }
                setTirado(true);
                return;
            }
        //JUGADOR NO ESTÁ EN LA CÁRCEL
        }else{ 
            String posicionActual=jugadores.get(turno).getAvatar().getLugar().getNombre();
            int pos_ini = jugadores.get(turno).getAvatar().getLugar().getPosicion();

    
            Avatar avatarActual = jugadores.get(turno).getAvatar();
            avatarActual.moverAvatar(tablero.getPosiciones(), sumaDados,this);


            
            // Evaluar la casilla en la que ha caído
            jugadores.get(turno).getAvatar().getLugar().evaluarCasilla(tablero, jugadores.get(turno), banca, sumaDados,this);

            // Verificar si el jugador ha dado la vuelta al tablero
            if (avatarActual.getLugar().getPosicion() < sumaDados) {
                //if(avatarActual.getLugar().getNombre().equals("ircarcel")){
                /*if(avatarActual.getLugar().getPosicion() == 30){
                    System.out.println("Has caído en la carcel.\n");
                    jugadores.get(turno).encarcelar(tablero.getPosiciones());
                }else{*/
                    jugadores.get(turno).setVueltas(jugadores.get(turno).getVueltas() + 1); //sumamos 1 vuelta a la variable vueltas que nos permite saber si los jugadores han dado 4 vueltas más

                    jugadores.get(turno).setNumVueltas(jugadores.get(turno).getNumVueltas() + 1); //sumamos 1 vuelta al contador total de vueltas

                    System.out.println("¡Has pasado por la casilla de salida! Recibes tu recompensa.\n");

                    jugadores.get(turno).sumarFortuna(Valor.SUMA_VUELTA);  //esto se puede cambiar
                    
                    jugadores.get(turno).setDineroCobradoSalida(jugadores.get(turno).getDineroCobradoSalida() + Valor.SUMA_VUELTA); //le sumamos a la estadística del dinero recibido por pasar por inicio

                    tablero.calcularCasillas(jugadores); //calculamos el nuevo valor que reciben los jugadores al pasar por la casilla inicio
            }

            setTirado(true); //El jugador ya ha lanzado los dados en este turn0

            //Sacó dobles y no es mov avanzado de coche
            if (getDadosdobles()&&(jugadores.get(turno).getAvatar().getTipo().equals("coche")==true && getLanzamientos()<4 && jugadores.get(turno).getAvatar().getAvanzado()==1 && sumaDados>4)==false) {
                setTirado(false); // Permitir volver a tirar
                setLanzamientosDobles(getLanzamientosDobles()+1);

                // Si sacó dobles 3 veces, va a la cárcel
                if (getLanzamientosDobles() == 3) {
                    System.out.println("Has sacado dobles 3 veces seguidas, vas a la cárcel.");
                    jugadores.get(turno).encarcelar(tablero.getPosiciones());
                    setTirado(true);
                }else{
                    System.out.println("Has sacado dobles, puedes lanzar de nuevo.");
                }
            } else {
                setLanzamientosDobles(0); // Resetear el contador de lanzamientos dobles
            }

            //Si el valor de la tirada es>4, es mov avanzado de coche y lanzamientos es  <4 veces, que es lo máximo peromitido-----> permitimos volver a lanzar los dados, es decir, setTirado()=false
            if(jugadores.get(turno).getAvatar().getTipo().equals("coche")==true && getLanzamientos()<4 && jugadores.get(turno).getAvatar().getAvanzado()==1 && sumaDados>4){
                setTirado(false);
                setLanzamientos(getLanzamientos()+1);
            //Si se ha acabado el turno del coche, el valor compras se reestablece a 0
            }else{
                setLanzamientos(0);
                jugadores.get(turno).getAvatar().setCompras(0);
                
            }

            String posicionFinal=jugadores.get(turno).getAvatar().getLugar().getNombre(); //definimos aquí la posiciónFinal por si el jugador va a la cárcel

            
            int pos_fin = jugadores.get(turno).getAvatar().getLugar().getPosicion();
            int num_casillas;
            //calculamos las casillas avanzadas
            if(pos_ini < pos_fin){
                num_casillas = pos_fin - pos_ini;
            }else{ //si pasamos la casilla inicio
                num_casillas = (40 - pos_ini) + pos_fin; //40 es el num total de casillas, a este num le restamos la posición inicial para saber cuantas casillas nos quedaban hasta la casilla inicio y, después le sumamos las que avanzamos desde la casilla inicio
            }

            //Repintamos el tablero
            System.out.println(tablero.toString());
            //Imprimimos el mensaje final:
            System.out.println("El avatar: "+jugadores.get(turno).getAvatar().getId()+", avanza "+num_casillas+" posiciones, desde "+posicionActual+" hasta "+posicionFinal);    

        }

    
}


    /*Método que ejecuta todas las acciones realizadas con el comando 'comprar nombre_casilla'.
    * Parámetro: cadena de caracteres con el nombre de la casilla.
     */
    private void comprar(String nombre) {
        if(nombre.equals(jugadores.get(turno).getAvatar().getLugar().getNombre())){
            if(tirado||dadosdobles){
                tablero.encontrar_casilla(nombre).comprarCasilla(jugadores.get(turno),banca);
            }else{
                System.out.println("Primero debes tirar los dados");
            }
        }else{
            System.out.println("Debes de estar sobre esa casilla para comprarla");
        }
        
    }
    //Método que ejecuta todas las acciones relacionadas con el comando 'salir carcel'. 
    private void salirCarcel() {
        //Si el jugador está en la cárcel
        //if(jugadores.get(turno).getAvatar().getLugar().getNombre()=="carcel"){
        if(jugadores.get(turno).getAvatar().getLugar().getPosicion() == 10){
            //Si está en la cárcel y además le llega el dinero:
            if(jugadores.get(turno).getFortuna()>=jugadores.get(turno).getAvatar().getLugar().valorCarcel(tablero.getPosiciones())){
                
                jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna()-jugadores.get(turno).getAvatar().getLugar().valorCarcel(tablero.getPosiciones()));
                
                jugadores.get(turno).setEnCarcel(false);
            
                jugadores.get(turno).setDineroTasasEImpuestos(jugadores.get(turno).getDineroTasasEImpuestos() + jugadores.get(turno).getAvatar().getLugar().valorCarcel(tablero.getPosiciones())); //añadimos al atributo dineroTasasEImpuestos el valor pagado por salir de la cárcel
            

            }else{
                System.out.println("No tienes dinero suficiente para salir de la cárcel.");
                //falta poner que pierde la partida
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
                        sb.append(String.format("\n grupo: %s,\n", casilla.getGrupo().getColorGrupo()));
                        sb.append(String.format("\n valor: %s,\n", casilla.getValor()));
                        sb.append("},\n");
                    }else if(casilla.getTipo().equals("servicio")){
                        sb.append(String.format("{\n tipo: %s,\n", casilla.getTipo()));
                        sb.append(String.format("\n valor: %s,\n", casilla.getValor()));
                        sb.append("},\n");
                    }else if(casilla.getTipo().equals("transporte")){
                        sb.append(String.format("{\n tipo: %s,\n", casilla.getTipo()));
                        sb.append(String.format("\n valor: %s,\n", casilla.getValor()));
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

    private void listarEdificios(){//PREGUNTAR SI ES ESTRICTAMENTE NECESARIO UN ID Y INTRODUCIR EL VALOR A LA HORA DE CREARLO (seguramente si)
        for(ArrayList<Casilla> lados:tablero.getPosiciones()){
            for (Casilla casilla:lados){
                if (casilla.getEdificacion().isEmpty()){
                    
                }else{                                 
                    System.out.println("{\n\tid: " +
                           ",\n\tpropietario: " + casilla.getDuenho().getNombre() +
                           ",\n\tcasilla: " + casilla.getNombre() +
                           ",\n\tgrupo: " + casilla.getGrupo().getColorGrupo() +
                           ",\n\tcoste: " + casilla.getValor() +
                           "\n}");
                }
            }
        }
    }


    // Método que realiza las acciones asociadas al comando 'acabar turno'.
    public void acabarTurno() {
        
        turno++;

        if (turno>jugadores.size()-1) {
            turno=0;
        }

        setTirado(false);
        setLanzamientos(0);
        setLanzamientosDobles(0);
        setDadosdobles(false);
        
    }

    private boolean comprobar(){
        Casilla actual = jugadores.get(turno).getAvatar().getLugar();
    
        if (actual.getGrupo().esDuenhoGrupo(jugadores.get(turno)) || actual.getVeces(turno)>2) {
            return true;   
        }else{
            System.out.println("No cumples los requisitos, has de ser dueño de todo el grupo o haber caido al menos tres veces en la casilla para edificar");
           return false;
        }
    }



//comprobar si hay que meter la comprobación solo en edificarcasa o no (tendría sentido que si ya que si quieres edificar otra cosa siempre vas a necesitar una casa)

    private void edificarCasa() {
        Edificacion casa = new Edificacion(jugadores.get(turno).getAvatar().getLugar(), "casa");
        Casilla actual = jugadores.get(turno).getAvatar().getLugar();
        int limiteGrupo = jugadores.get(turno).getAvatar().getLugar().getGrupo().getNumCasillas();
        int numCasas = actual.contarCasas();
        int hotelgrupo = actual.getGrupo().contarHotelesGrupo();
        int casasGrupo = actual.getGrupo().contarCasasGrupo();

        if (comprobar()) {
            if (numCasas < 4) {
                if (hotelgrupo==limiteGrupo) {
                    if (casasGrupo<limiteGrupo) {
                        if (!(jugadores.get(turno).getFortuna()< (actual.getValor()*0.6f))) {
                            actual.anhadirEdificacion(casa);
                            jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna()-(actual.getValor()*0.6f));
                            jugadores.get(turno).setDineroInvertido(jugadores.get(turno).getDineroInvertido() + (actual.getValor()*0.6f)); //sumamos el valor de la casilla al atributo dineroInvertido del comprador


                            System.out.println("Se han pagado"+ actual.getValor()*0.6f + "por la construcción de una casa. La fortuna restante es de "+jugadores.get(turno).getFortuna());
                            System.out.println("Se ha construido una casa correctamente en la casilla " + actual.getNombre() + ". Hay " + (numCasas+1) + " casas construidas.");
                        }else{
                            System.out.println("No dispones del dinero necesario para construir la edificación");
                        }
                    }else{
                        System.out.println("Se alcanzó el límite máximo de casas a construir en el grupo");
                    }
                }else{
                    if (!(jugadores.get(turno).getFortuna()< (actual.getValor()*0.6f))) {
                        actual.anhadirEdificacion(casa);
                        jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna()-(actual.getValor()*0.6f));
                        jugadores.get(turno).setDineroInvertido(jugadores.get(turno).getDineroInvertido() + (actual.getValor()*0.6f)); //sumamos el valor de la casilla al atributo dineroInvertido del comprador
                        

                        System.out.println("Se han pagado"+ actual.getValor()*0.6f + "por la construcción de una casa. La fortuna restante es de "+jugadores.get(turno).getFortuna());
                        System.out.println("Se ha construido una casa correctamente en la casilla " + actual.getNombre() + ". Hay " + (numCasas+1) + " casas construidas.");
                    }else{
                        System.out.println("No dispones del dinero necesario para construir la edificación");
                    }
                }
            }else{
                System.out.println("Se alcanzó el límite máximo de casas a construir");
            }    
            }
        }


        private void edificarHotel() {
            Edificacion hotel = new Edificacion(jugadores.get(turno).getAvatar().getLugar(), "hotel");
            Casilla actual = jugadores.get(turno).getAvatar().getLugar();
            int limiteGrupo = actual.getGrupo().getNumCasillas();
            int numCasas = actual.contarCasas();
            int hotelgrupo = actual.getGrupo().contarHotelesGrupo();
            int contarHoteles = actual.contarHoteles();
        
            if (hotelgrupo < limiteGrupo) {
                if (numCasas == 4) {
                    if (jugadores.get(turno).getFortuna() >= (actual.getValor() * 0.6f)) {
                        actual.anhadirEdificacion(hotel);
                        jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna() - (actual.getValor() * 0.6f));
                        jugadores.get(turno).setDineroInvertido(jugadores.get(turno).getDineroInvertido() + (actual.getValor()*0.6f)); //sumamos el valor de la casilla al atributo dineroInvertido del comprador


                        System.out.println("Se han pagado " + actual.getValor() * 0.6f + " por la construcción de un hotel. La fortuna restante es de " + jugadores.get(turno).getFortuna() + "\n");
                        System.out.println("Se ha construido el hotel y se han quitado las 4 casas\n");
                        System.out.println("Se ha construido un hotel correctamente en la casilla " + actual.getNombre() + ". Hay " + (contarHoteles + 1) + " hoteles construidos.\n");
                        actual.cambiarcasas();
                    } else {
                        System.out.println("No dispones del dinero necesario para construir la edificación\n");
                    }
                } else {
                    System.out.println("No hay 4 casas en la casilla como para construir el hotel\n");
                }
            } else {
                System.out.println("Has alcanzado el límite máximo de hoteles en el grupo\n");
            }
        }
        
        private void edificarPiscina() {
            Edificacion piscina = new Edificacion(jugadores.get(turno).getAvatar().getLugar(), "piscina");
            Casilla actual = jugadores.get(turno).getAvatar().getLugar();
            int limiteGrupo = actual.getGrupo().getNumCasillas();
            int numCasas = actual.contarCasas();
            int hotel = actual.contarHoteles();
            int piscinagrupo = actual.getGrupo().contarPiscinasGrupo();
            int contarpiscinas = actual.contarPiscinas();
        
            if (piscinagrupo < limiteGrupo) {
                if ((hotel >= 1 && numCasas > 1) || (hotel >= 2)) {
                    if (jugadores.get(turno).getFortuna() >= (actual.getValor() * 0.4f)) {
                        actual.anhadirEdificacion(piscina);
                        jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna() - (actual.getValor() * 0.4f));
                        jugadores.get(turno).setDineroInvertido(jugadores.get(turno).getDineroInvertido() + (actual.getValor()*0.4f)); //sumamos el valor de la casilla al atributo dineroInvertido del comprador

                        System.out.println("Se han pagado " + actual.getValor() * 0.4f + " por la construcción de una piscina. La fortuna restante es de " + jugadores.get(turno).getFortuna());
                        contarpiscinas = actual.contarPiscinas();
                        System.out.println("Se ha construido una piscina correctamente en la casilla " + actual.getNombre() + ". Hay " + contarpiscinas + " piscinas construidas.");
                    } else {
                        System.out.println("No dispones del dinero necesario para construir la edificación");
                    }
                } else {
                    System.out.println("Para construir una piscina necesitas 1 hotel y 2 casas o 2 o más hoteles");
                }
            } else {
                System.out.println("Has alcanzado el máximo de piscinas en el grupo");
            }
        }
        
        private void edificarPista() {
            Edificacion pista = new Edificacion(jugadores.get(turno).getAvatar().getLugar(), "pista");
            Casilla actual = jugadores.get(turno).getAvatar().getLugar();
            int limiteGrupo = actual.getGrupo().getNumCasillas();
            int hotel = actual.contarHoteles();
            int pistasgrupo = actual.getGrupo().contarPistasGrupo();
            int contarpistas = actual.contarPistas();
        
            if (pistasgrupo < limiteGrupo) {
                if (hotel >= 2) {
                    if (jugadores.get(turno).getFortuna() >= (actual.getValor() * 1.25f)) {
                        actual.anhadirEdificacion(pista);
                        jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna() - (actual.getValor() * 1.25f));
                        jugadores.get(turno).setDineroInvertido(jugadores.get(turno).getDineroInvertido() + (actual.getValor()*1.25f)); //sumamos el valor de la casilla al atributo dineroInvertido del comprador


                        System.out.println("Se han pagado " + actual.getValor() * 1.25f + " por la construcción de una pista. La fortuna restante es de " + jugadores.get(turno).getFortuna());
                        System.out.println("Se ha construido una pista correctamente en la casilla " + actual.getNombre() + ". Hay " + (contarpistas + 1) + " pistas construidas.");
                    } else {
                        System.out.println("No dispones del dinero necesario para construir la edificación");
                    }
                } else {
                    System.out.println("Necesitas al menos 2 hoteles para construir una pista de deportes");
                }
            } else {
                System.out.println("Has alcanzado el máximo de pistas de deporte en el grupo");
            }
        }

        private void venderCasa(String nombreCasilla, int numvender) {
            Casilla actual = tablero.encontrar_casilla(nombreCasilla);
            if (actual == null || !(actual.getDuenho().equals(jugadores.get(turno)))) {
                System.out.println("La casilla especificada no existe o no pertenece a este jugador.");
                return;
            }
            int numCasas = actual.contarCasas();
            if (numCasas < numvender)
            {
                System.out.println("No puedes vender más casas de las que hay construidas");
                return;
            }
            
            for(int i = 0; i<numvender; i++){
            if (numCasas > 0) {
                float valorVenta = actual.getValor() * 0.6f / 2;
                actual.eliminarEdificacion("casa");
                jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna() + valorVenta);
                System.out.println("Se ha vendido una casa por " + valorVenta + ". La fortuna actual es de " + jugadores.get(turno).getFortuna());
                numCasas = actual.contarCasas();
                System.out.println("Se ha eliminado una casa en la casilla " + actual.getNombre() + ". Quedan " + (numCasas) + " casas construidas.");
            } else {
                System.out.println("No hay casas para vender en esta casilla.");
                return;
                }
            }
        }
        
        private void venderHotel(String nombreCasilla, int numvender) {
            Casilla actual = tablero.encontrar_casilla(nombreCasilla);
            int numHoteles = actual.contarHoteles();

            if (actual == null || actual.getDuenho().equals(jugadores.get(turno))) {
                System.out.println("La casilla especificada no existe o no pertenece a este jugador.");
                return;
            }
            
            if (numHoteles < numvender)
            {
                System.out.println("No puedes vender más hoteles de los que hay construidos");
                return;
            }
            
            for(int i = 0; i<numvender; i++){
        
            if (numHoteles > 0) {
                float valorVenta = actual.getValor() * 0.6f / 2;
                actual.eliminarEdificacion("hotel");
                jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna() + valorVenta);
                System.out.println("Se ha vendido un hotel por " + valorVenta + ". La fortuna actual es de " + jugadores.get(turno).getFortuna());
                numHoteles = actual.contarHoteles();
                System.out.println("Se ha eliminado un hotel en la casilla " + actual.getNombre() + ". Quedan " + (numHoteles) + " hoteles construidos.");
            } else {
                System.out.println("No hay hoteles para vender en esta casilla.");
                return;
            }
            }
        }
        
        private void venderPiscina(String nombreCasilla, int numvender) {
            Casilla actual = tablero.encontrar_casilla(nombreCasilla);
            int numPiscinas = actual.contarPiscinas();
        
            if (actual == null || actual.getDuenho().equals(jugadores.get(turno))) {
                System.out.println("La casilla especificada no existe o no es de este jugador.");
                return;
            }
            
            if (numPiscinas < numvender)
            {
                System.out.println("No puedes vender más casas de las que hay construidas");
                return;
            }
            
            for(int i = 0; i<numvender; i++){

            if (numPiscinas > 0) {
                float valorVenta = actual.getValor() * 0.4f / 2;
                actual.eliminarEdificacion("piscina");
                jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna() + valorVenta);
                System.out.println("Se ha vendido una piscina por " + valorVenta + ". La fortuna actual es de " + jugadores.get(turno).getFortuna());
                numPiscinas = actual.contarPiscinas();
                System.out.println("Se ha eliminado una piscina en la casilla " + actual.getNombre() + ". Quedan " + (numPiscinas) + " piscinas construidas.");
            } else {
                System.out.println("No hay piscinas para vender en esta casilla.");
                return;
            }
            }
        }
        
        private void venderPista(String nombreCasilla, int numvender) {
            Casilla actual = tablero.encontrar_casilla(nombreCasilla);
            int numPistas = actual.contarPistas();
            
            if (actual == null || actual.getDuenho().equals(jugadores.get(turno))) {
                System.out.println("La casilla especificada no existe o no es de este jugador.");
                return;
            }
            if (numPistas < numvender)
            {
                System.out.println("No puedes vender más casas de las que hay construidas");
                return;
            }
            
            for(int i = 0; i<numvender; i++){

            if (numPistas > 0) {
                float valorVenta = actual.getValor() * 1.25f / 2;
                actual.eliminarEdificacion("pista");
                jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna() + valorVenta);
                System.out.println("Se ha vendido una pista por " + valorVenta + ". La fortuna actual es de " + jugadores.get(turno).getFortuna());
                numPistas = actual.contarPistas();
                System.out.println("Se ha eliminado una pista en la casilla " + actual.getNombre() + ". Quedan " + (numPistas) + " pistas construidas.");
            } else {
                System.out.println("No hay pistas para vender en esta casilla.");
                return;
            }
            }
        }
        


    /*Implementa la accion según el tipo de carta que sea y según la carta que sea dentro de cada tipo.
    Tiene que calcularse de forma que si la posicion de la casilla a la que queremos ir es mayor que la del jugador que avance 
    la distancia que hay entre las dos y si no que avance la distancia hasta llegar a la salida y luego que avance
    hasta la posicion a la que queremos ir
    
    LAS CARTAS QUE REQUIEREN PAGAR ALGO NECESITAN LA IMPLEMENTACIÓN DE LA FUNCIÓN 'bancarrota()', mientras se deja indicado en los elses
    correspondientes mediante un comentario*/
    public void accionCarta(String tipo,int num){
        switch (tipo) {
            case "suerte":
                switch (num) {
                    //Moverse a trans1 y cobrar la salida si pasamos por ella
                    case 1 :
                        System.out.println("Ve al Transportes1 y coge un avión. Si pasas por la casilla de Salida, cobra la cantidad habitual.");
                        int posicion1=jugadores.get(turno).getAvatar().getLugar().getPosicion();
                        int tirada1;
                        if(5>posicion1){
                            tirada1=(5-posicion1);
                        }else{
                            tirada1=(40-posicion1)+5;
                        }
                        this.jugadores.get(turno).getAvatar().moverAvatar(tablero.getPosiciones(),tirada1,this);
                        this.jugadores.get(turno).getAvatar().getLugar().evaluarCasilla(tablero,jugadores.get(turno), banca,tirada1,this);
                        //Si pasa por la casilla de salida le sumamos el valor:
                        if(posicion1>5 || posicion1<0){
                            jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna()+tablero.getPosiciones().get(0).get(0).valorSalida(tablero.getPosiciones()));
                            
                            jugadores.get(turno).setDineroCobradoSalida(jugadores.get(turno).getDineroCobradoSalida() + tablero.getPosiciones().get(0).get(0).valorSalida(tablero.getPosiciones()));
                            
                            System.out.printf("Recibes %.2f€ por pasar por la salida%n", tablero.getPosiciones().get(0).get(0).valorSalida(tablero.getPosiciones()));

                        }
                        break;
                    //Moverse a solar15 sin cobrar la salida aunque pasemos por ella
                    case 2:
                        System.out.println("Decides hacer un viaje de placer. Avanza hasta Solar15 directamente, sin pasar por la casilla de Salida\n"); 
                        int tirada2;
                        int posicion2=this.jugadores.get(turno).getAvatar().getLugar().getPosicion();
                        if(26>posicion2){
                            tirada2=(26-posicion2);
                        }else{
                            tirada2=(40-posicion2)+26;
                        }
                        this.jugadores.get(turno).getAvatar().moverAvatar(tablero.getPosiciones(), tirada2,this);
                        this.jugadores.get(turno).getAvatar().getLugar().evaluarCasilla(tablero,jugadores.get(turno),banca,tirada2,this);
                
                        
                        break;
                    //Se vende el billete de avion a solar 17, por lo que se reciben 500000€
                    case 3:
                        System.out.println("Vendes tu billete de avión para Solar17 en una subasta por Internet. Cobra 500000€.");
                        this.jugadores.get(turno).setFortuna(this.jugadores.get(turno).getFortuna()+500000f);

                        jugadores.get(turno).setDineroInversionesOBote(jugadores.get(turno).getDineroInversionesOBote() + 500000f); //añadimos al atributo dineroInversionesOBote el valor indicado

                        break;
                    case 4:
                        System.out.println("Ve a Solar3. Si pasas por la casilla de Salida, cobra la cantidad habitual.");
                        int posicion3=jugadores.get(turno).getAvatar().getLugar().getPosicion();
                        int tirada3;
                        if(6>posicion3){
                            tirada3=(6-posicion3);
                        }else{
                            tirada3=(40-posicion3)+6;
                        }
                        this.jugadores.get(turno).getAvatar().moverAvatar(tablero.getPosiciones(), tirada3,this);
                        this.jugadores.get(turno).getAvatar().getLugar().evaluarCasilla(tablero,jugadores.get(turno),banca,tirada3,this);
                        //Si pasa por la casilla de salida le sumamos el valor:
                        if(posicion3>6 || posicion3<0){
                            jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna()+tablero.getPosiciones().get(0).get(0).valorSalida(tablero.getPosiciones()));
                            System.out.printf("Recibes %.2f€ por pasar por la salida%n", tablero.getPosiciones().get(0).get(0).valorSalida(tablero.getPosiciones()));

                        }

                        break;
                    case 5:
                        System.out.println("Los acreedores te persiguen por impago. Ve a la Cárcel. Ve directamente sin pasar por la casilla de Salida y sin cobrar la cantidad habitual.");
                        this.jugadores.get(turno).encarcelar(tablero.getPosiciones());

                        break;
                    case 6:
                        System.out.println("Has ganado el bote de la lotería! Recibe 1000000€.");
                        this.jugadores.get(turno).setFortuna(this.jugadores.get(turno).getFortuna()+1000000f);

                        jugadores.get(turno).setDineroInversionesOBote(jugadores.get(turno).getDineroInversionesOBote() + 1000000f); //añadimos al atributo dineroInversionesOBote el valor indicado

                        break;
                    default:
                        break;
                }
                
                break;
            case "caja":
            switch (num) {
                case 1 :
                    System.out.println("Paga 500000€ por un fin de semana en un balneario de 5 estrellas.");
                    if(jugadores.get(turno).getFortuna()>=500000f){
                        jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna()-500000f);

                        jugadores.get(turno).setDineroTasasEImpuestos(jugadores.get(turno).getDineroTasasEImpuestos() + 500000f); //añadimos al atributo dineroTasasEImpuestos el valor que recibe

                    }else{
                        //bancarrota();
                    }
                    break;
                case 2:
                    System.out.println("Te investigan por fraude de identidad. Ve a la Cárcel. Ve directamente sin pasar por la casilla de Salida y sin cobrar la cantidad habitual\n");
                    this.jugadores.get(turno).encarcelar(tablero.getPosiciones());
                    break;  
                case 3:
                    System.out.println("Colócate en la casilla de Salida. Cobra la cantidad habitual");
                    int posicion4=jugadores.get(turno).getAvatar().getLugar().getPosicion();
                    int tirada4=40-posicion4;
                    this.jugadores.get(turno).getAvatar().moverAvatar(tablero.getPosiciones(), tirada4,this);
                    this.jugadores.get(turno).getAvatar().getLugar().evaluarCasilla(tablero,jugadores.get(turno),banca,tirada4,this);
                    //Le sumamos el valor de la salida
                    if(posicion4>6 || posicion4<0){
                        jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna()+tablero.getPosiciones().get(0).get(0).valorSalida(tablero.getPosiciones()));
                        System.out.printf("Recibes %.2f€ por pasar por la salida%n", tablero.getPosiciones().get(0).get(0).valorSalida(tablero.getPosiciones()));

                    }
                    break;
                case 4:
                    System.out.println("Tu compañía de Internet obtiene beneficios. Recibe 2000000€");
                    jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna()+2000000f);

                    jugadores.get(turno).setDineroInversionesOBote(jugadores.get(turno).getDineroInversionesOBote() + 2000000f); //añadimos al atributo dineroInversionesOBote el valor indicado
                    
                    break;
                case 5:
                    System.out.println("Paga 1000000€ por invitar a todos tus amigos a un viaje a Solar14");
                    if(jugadores.get(turno).getFortuna()>=1000000f){
                        jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna()-1000000f);

                        jugadores.get(turno).setDineroTasasEImpuestos(jugadores.get(turno).getDineroTasasEImpuestos() + 1000000f); //añadimos al atributo dineroTasasEImpuestos el valor que recibe


                    }else{
                        //bancarrota();
                    }
                    break;
                case 6:
                    System.out.println("Alquilas a tus compañeros una villa en Solar7 durante una semana. Paga 200000€ a cada jugador");
                    for(int i=0;i<jugadores.size();i++){
                        if(i!=turno){
                            //Si el jugador del turno tiene el dinero suficiente...
                            if(jugadores.get(turno).getFortuna()>=200000f){
                                //Les damos el dinero y se lo quitamos al jugador que le toca la carta.
                                jugadores.get(i).setFortuna(jugadores.get(i).getFortuna()+200000f);
                                jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna()-200000f);

                                jugadores.get(turno).setDineroTasasEImpuestos(jugadores.get(turno).getDineroTasasEImpuestos() + 200000f); //añadimos al atributo dineroTasasEImpuestos el valor que recibe

                            }else{
                                //bancarrota();

                            }

                        }

                    }
                    break;
                default:
                    break;
            }

            default:
                break;
        }

    }

    public ArrayList<String> jugadorMasVueltas(){
        int numVueltas = 0;
        ArrayList<String> array = new ArrayList<String>();

        for(Jugador jugador:jugadores){ //recorremos una vez el array de jugadores para ver cual es el nº mayor de vueltas
            if(numVueltas < jugador.getNumVueltas()){
                numVueltas = jugador.getNumVueltas();
            }
        }
        for(Jugador jugador:jugadores){ //recorremos una segunda vez el array de jugadores para ver que jugadores tienen el nº máximo de vueltas
            if(numVueltas == jugador.getNumVueltas()){
                array.add(jugador.getNombre());
            }
        }
        return array;
    }

    public String recorridoJugadorMasVueltas(ArrayList<String> array){
        if(array != null && !array.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(String nombre:array){
                sb.append(nombre).append(",");
            }
            return sb.substring(0, sb.length() - 1); //devolvemos todo el Stringbuilder menos la última coma
        }else{
            return "Error, no existen jugadores";
        }
    }

    public ArrayList<String> casillaMasFrecuentada(Tablero tablero){
        int numVeces = 0;
        ArrayList<String> array = new ArrayList<String>();

        for(ArrayList<Casilla> lado:tablero.getPosiciones()){ //recorremos una vez el tablero para ver cual es el nº máximo de caídas
            for(Casilla casilla:lado){
                if(numVeces < casilla.getVeces_global()){
                    numVeces = casilla.getVeces_global();
                }
            }
        }
        for(ArrayList<Casilla> lado:tablero.getPosiciones()){ //recorremos una segunda vez el tablero para ver que casillas tienen el nº máximo de caídas
            for(Casilla casilla:lado){
                if(numVeces == casilla.getVeces_global()){
                    array.add(casilla.getNombre());
                }
            }
        }
        return array;
    }

    public String recorridoCasillaMasFrecuentada(ArrayList<String> array){
        if(array != null && !array.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(String nombre:array){
                sb.append(nombre).append(",");
            }
            return sb.substring(0, sb.length() - 1); //devolvemos todo el Stringbuilder menos la última coma
        }else{
            return "Error, no hay casillas en el array";
        }
    }

    public ArrayList<String> jugadorMasVecesDados(){
        int vecesDados = 0;
        ArrayList<String> array = new ArrayList<String>();

        for(Jugador jugador:jugadores){ //recorremos una vez el array de jugadores para ver cual es el nº máximo de dados lanzados
            if(vecesDados < jugador.getVecesDados()){
                vecesDados = jugador.getVecesDados();
            }
        }
        for(Jugador jugador:jugadores){ //recorremos una segunda vez el tablero para ver que jugadores tienen el nº máximo de dados lanzados
            if(vecesDados == jugador.getVecesDados()){
                array.add(jugador.getNombre());
            }
        }
        return array;
    }

    public String recorridoJugadorMasVecesDados(ArrayList<String> array){
        if(array != null && !array.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(String nombre:array){
                sb.append(nombre).append(",");
            }
            return sb.substring(0, sb.length() - 1); //devolvemos todo el Stringbuilder menos la última coma
        }else{
            return "Error, no existen jugadores";
        }
    }

    public ArrayList<String> jugadorEnCabeza(){
        int posicionMax = 0;
        Jugador aux = new Jugador();
        ArrayList<String> array = new ArrayList<String>();
        ArrayList<String> arraySolucion = new ArrayList<String>();
        
        array = jugadorMasVueltas();
        if(array.size() > 0){
            for(String nombre:array){ //buscamos las clases Jugador que estén en el array de nombres y obtenemos su posición
                for(Jugador jugador:jugadores){
                    if(jugador.getNombre().equals(nombre)){
                        aux = jugador;
                    }
                    if(posicionMax < (aux.getAvatar().getLugar().getPosicion())){ //buscamos la posición máxima de los jugadores que estén dentro del array
                        posicionMax = aux.getAvatar().getLugar().getPosicion();
                    }
                }
            }
            for(String nombre:array){ //volvemos a recorrer el array de jugadores y comparamos la posición máxima con las posiciones que tienen todos los jugadores, nos quedamos con el que coincida
                for(Jugador jugador:jugadores){
                    if(jugador.getNombre().equals(nombre)){
                        if(jugador.getAvatar().getLugar().getPosicion() == posicionMax){
                            arraySolucion.add(jugador.getNombre());
                        }
                    }
                }
            }
        }
        return arraySolucion;
    }

    public String recorridoJugadorEnCabeza(ArrayList<String> array){
        if(array != null && !array.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(String nombre:array){
                sb.append(nombre).append(",");
            }
            return sb.substring(0, sb.length() - 1); //devolvemos todo el Stringbuilder menos la última coma
        }else{
            return "Error, no existen jugadores";
        }
    }

    public String casillaMasRentable(){ //no creo un Array porque no va a haber más de una casilla con el mismo valor
        float dineroMax = 0;

        for(ArrayList<Casilla> lado:tablero.getPosiciones()){
            for(Casilla casilla:lado){
                if((!casilla.getTipo().equals("solar")) && (!casilla.getTipo().equals("servicio")) && (!casilla.getTipo().equals("transporte"))){
                    if(dineroMax < casilla.getDineroCasilla()){
                        dineroMax = casilla.getDineroCasilla();
                    }
                }
            }
        }

        for(ArrayList<Casilla> lado:tablero.getPosiciones()){
            for(Casilla casilla:lado){
                if((!casilla.getTipo().equals("solar")) && (!casilla.getTipo().equals("servicio")) && (!casilla.getTipo().equals("transporte"))){
                    if(dineroMax == casilla.getDineroCasilla()){
                        return casilla.getNombre();
                    }
                }
            }
        }
        return "Error";
    }

    public String grupoMasRentable(){
        float dineroMax = 0, dineroAux = 0;

        for(ArrayList<Casilla> lado:tablero.getPosiciones()){ //recorremos todas las casillas
            for(Casilla casilla:lado){
                if(casilla.getTipo().equals("solar")){
                    ArrayList<Casilla> array = casilla.getGrupo().getMiembros();
                    for(Casilla casillaArray:array){ //sumamos todo el dinero de cada casilla del grupo
                        dineroAux += casillaArray.getDineroCasilla();
                    }

                    if(dineroMax < dineroAux){ //si el dinero máximo es menor que el dinero total del grupo, guardamos el nuevo valor máximo
                        dineroMax = dineroAux;
                    }
                    dineroAux = 0; //reseteamos la variable de dinero auxiliar para poder volver a sumar el dinero total de otro grupo
                }
            }
        }
        for(ArrayList<Casilla> lado:tablero.getPosiciones()){
            for(Casilla casilla:lado){
                if(casilla.getTipo().equals("solar")){
                    ArrayList<Casilla> array = casilla.getGrupo().getMiembros();
                    for(Casilla casillaArray:array){ //sumamos todo el dinero de cada casilla del grupo
                        dineroAux += casillaArray.getDineroCasilla();
                    }
                    if(dineroAux == dineroMax){
                        return casilla.getGrupo().getColorGrupo();
                    }else{
                        dineroAux = 0;
                    }
                }
            }
        }
        System.out.println("No hay un grupo más rentable\n");
        return null;
    }

    public void estadisticasJuego(){
        StringBuilder sb = new StringBuilder();


        sb.append("{\n");
        sb.append(String.format("casillaMasRentable: %s,\n", casillaMasRentable()));
        sb.append(String.format("grupoMasRentable: %s,\n", grupoMasRentable()));
        sb.append(String.format("casillaMasFrecuentada: %s,\n", recorridoCasillaMasFrecuentada(casillaMasFrecuentada(tablero))));
        sb.append(String.format("jugadorMasVueltas: %s,\n", recorridoJugadorMasVueltas(jugadorMasVueltas())));
        sb.append(String.format("jugadorMasVecesDados: %s,\n", recorridoJugadorMasVecesDados(jugadorMasVecesDados())));
        sb.append(String.format("jugadorEnCabeza: %s\n", recorridoJugadorEnCabeza(jugadorEnCabeza())));
        sb.append("}\n");

        System.out.println(sb.toString());
    }

    public int estadisticasJugador(String nombre){
        Jugador jugador = null;

        for(Jugador aux:jugadores){
            if(aux.getNombre().equals(nombre)){
                jugador = aux;
            }
        }
        if(jugador == null){
            System.out.println("Error, no existe el jugador\n");
            return 1;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append(String.format(" dineroInvertido: %f,\n", jugador.getDineroInvertido()));
        sb.append(String.format(" pagoTasasEImpuestos: %f,\n", jugador.getDineroTasasEImpuestos()));
        sb.append(String.format(" pagoDeAlquileres: %f,\n", jugador.getDineroPagadoAlquileres()));
        sb.append(String.format(" cobroDeAlquileres: %f,\n", jugador.getDineroCobradoAlquileres()));
        sb.append(String.format(" pasarPorCasillaDeSalida: %f,\n", jugador.getDineroCobradoSalida()));
        sb.append(String.format(" premiosInversionesOBote: %f, \n", jugador.getDineroInversionesOBote()));
        sb.append(String.format(" vecesEnLaCarcel: %d\n", jugador.getVecesCarcel()));
        sb.append("}\n");

        System.out.println(sb.toString());
        return 0;
    }

    public void bancarrota(){ //falta mirar si las propiedades van a la banca o a un jugador determinado
        Jugador jugador = jugadores.get(turno);
        for(Casilla casilla:jugador.getPropiedades()){ //pasamos todas las propiedades del jugador a la banca
            casilla.setDuenho(banca);
        }

        banca.setFortuna(banca.getFortuna() + jugador.getFortuna()); //pasamos toda la fortuna del jugador a la banca
        jugador.setFortuna(0);
    
        jugadores.remove(turno);
        avatares.remove(turno);
    }

}