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
        System.out.println("8. Describir casilla              -> Comando: 'describir nombreCasilla'");
        System.out.println("9. Describir jugador              -> Comando: 'describir jugador nombreJugador'");
        System.out.println("10. Describir avatar              -> Comando: 'describir avatar idAvatar'");
        System.out.println("11. Comprar casilla               -> Comando: 'comprar 'nombreCasilla'");
        System.out.println("12. Listar casillas en venta      -> Comando: 'listarenventa'");
        System.out.println("13. Ver tablero                   -> Comando: 'ver'\n");
        System.out.println("14. Construir un edificio         -> Comando: 'edificar tipoEdificacion'\n");
        System.out.println("15. Listar edificios construidos  -> Comando: 'listar edificios'\n");
        System.out.println("16. Listar edificios construidos en grupo        -> Comando: 'listar edificios colorGrupo'\n");
        System.out.println("17. Hipotecar una propiedad       -> Comando: 'hipotecar nombrePropiedad'\n");
        System.out.println("18. Declararse en bancarrota      -> Comando: 'bancarrota'\n");
        System.out.println("19. Deshipotecar una propiedad    -> Comando: 'deshipotecar nombrePropiedad'\n");
        System.out.println("20. Vender edificios              -> Comando: 'vender tipoEdificacion nombrePropiedad numeroElementosAvender'\n");
        System.out.println("21. Mostrar estadísticas de un jugador         -> Comando: 'estadisticas nombreJugador'\n");
        System.out.println("22. Mostrar estadísticas del juego        -> Comando: 'estadisticas'\n");
        System.out.println("23. Cambiar modo de movimiento de los avatares         -> Comando: 'cambiar modo'\n");
               

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
                }else if(palabras[1].equals("edificios")){
                    //Completar con listarEdificios
                    //listarEdificios();
                }else if(palabras.length==2){
                    //listarEdificiosGrupo();
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
        case "edificar":
            if(palabras.length==2){
                String tipoEdificacion=palabras[1];
                if(tipoEdificacion.equals("casa")){
                    edificarCasa();
                }else if(tipoEdificacion.equals("hotel")){
                    edificarHotel();
                }else if(tipoEdificacion.equals("piscina")){
                    edificarPiscina();

                }else if(tipoEdificacion.equals("deporte")){
                    edificarPista();
                }
            }
            break;
        case "hipotecar":
            if(palabras.length==2){
                //hipotecar();

            }else{
                System.out.println("Error, comando desconocido");
            }
            break;
        case "bancarrota":
            //bancarrota();

            break;
        case "deshipotecar":
            if(palabras.length==2){
              //  deshipotecar();
            }else{
                System.out.println("Error, comando desconocido");
            }
            break;    
        case "vender":
            if(palabras.length==4){
                //venderEdificios();
            }else{
                System.out.println("Error, comando desconocido");

            }

            break;
        case "estadisticas":
            if(palabras.length==1){
                //estadisticasJuego();
            }else if(palabras.length==2){
                //estadisticasJugador();
            }else{
                System.out.println("Error, comando desconocido");
            }
        case "cambiar":
            if(palabras.length==2){
                //cambiarModoMovimiento();
            }else{
                System.out.println("Error, comando desconocido");

            }

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


        int sumaDados = dado1.getValor() + dado2.getValor();
        setDadosdobles(dado1.equals(dado2));

        //Si el jugador está en la carcel:
        if (jugadores.get(turno).getEnCarcel()) {
            if (getDadosdobles()) {
                System.out.println("Has sacado dobles y sales de la cárcel.");

                jugadores.get(turno).setEnCarcel(false);

                String posicionActual = jugadores.get(turno).getAvatar().getLugar().getNombre();
                
                jugadores.get(turno).getAvatar().moverAvatar(tablero.getPosiciones(), sumaDados);

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
                        jugadores.get(turno).getAvatar().moverAvatar(tablero.getPosiciones(), sumaDados);

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
        }else{ //Si el jugador no está en la cárcel
            String posicionActual=jugadores.get(turno).getAvatar().getLugar().getNombre();
            int pos_ini = jugadores.get(turno).getAvatar().getLugar().getPosicion();

    
            Avatar avatarActual = jugadores.get(turno).getAvatar();
            avatarActual.moverAvatar(tablero.getPosiciones(), sumaDados);


            
            // Evaluar la casilla en la que ha caído
            jugadores.get(turno).getAvatar().getLugar().evaluarCasilla(tablero, jugadores.get(turno), banca, sumaDados);

            // Verificar si el jugador ha dado la vuelta al tablero
            if (avatarActual.getLugar().getPosicion() < sumaDados) {
                //if(avatarActual.getLugar().getNombre().equals("ircarcel")){
                /*if(avatarActual.getLugar().getPosicion() == 30){
                    System.out.println("Has caído en la carcel.\n");
                    jugadores.get(turno).encarcelar(tablero.getPosiciones());
                }else{*/
                    jugadores.get(turno).setVueltas(jugadores.get(turno).getVueltas() + 1);

                    System.out.println("¡Has pasado por la casilla de salida! Recibes tu recompensa.\n");

                    jugadores.get(turno).sumarFortuna(jugadores.get(turno).getAvatar().getLugar().valorSalida(tablero.getPosiciones())); 
                    
                    tablero.calcularCasillas(jugadores); //calculamos el nuevo valor que reciben los jugadores al pasar por la casilla inicio
            }


            setTirado(true); //El jugador ya ha lanzado los dados en este turno

            // Si sacó dobles, puede volver a tirar
            if (getDadosdobles()) {
                setTirado(false); // Permitir volver a tirar
                setLanzamientos(getLanzamientos()+1);

                // Si sacó dobles 3 veces, va a la cárcel
                if (getLanzamientos() == 3) {
                    System.out.println("Has sacado dobles 3 veces seguidas, vas a la cárcel.");
                    jugadores.get(turno).encarcelar(tablero.getPosiciones());
                    setTirado(true);
                }else{
                    System.out.println("Has sacado dobles, puedes lanzar de nuevo.");
                }
            } else {
                setLanzamientos(0); // Resetear el contador de lanzamientos dobles
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
            if(tirado){
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



    private int contarCasasActuales() {
        return jugadores.get(turno).getAvatar().getLugar().contarCasas();
    }

    private int contarHotelesActuales() {
        return jugadores.get(turno).getAvatar().getLugar().contarHoteles();
    }

    private int contarPiscinasActuales() {
        return jugadores.get(turno).getAvatar().getLugar().contarPiscinas();
    }

    private int contarPistasActuales() {
        return jugadores.get(turno).getAvatar().getLugar().contarPistas();
    }
    
    private ArrayList<Integer> limiteGrupo() {
        int numCasillas = jugadores.get(turno).getAvatar().getLugar().getGrupo().getNumCasillas();
        ArrayList<Integer> limit = new ArrayList<>();
        
        if (numCasillas == 2) {
            // Límite de casas, hoteles, piscinas y pistas para un grupo de 2 casillas.
            limit.add(2); // Máximo de hoteles
            limit.add(2); // Máximo de piscinas
            limit.add(2); // Máximo de pistas
        } else if (numCasillas == 3) {
            // Límite de casas, hoteles, piscinas y pistas para un grupo de 3 casillas.
            limit.add(3); // Máximo de hoteles
            limit.add(3); // Máximo de piscinas
            limit.add(3); // Máximo de pistas
        } else {
            // Manejo de otros casos o grupos más grandes si es necesario.
            System.out.println("Número de casillas en el grupo no soportado.");
        }
        
        return limit;
    }
    





    private void edificarCasa() {
        Edificacion casa = new Edificacion(jugadores.get(turno).getAvatar().getLugar(), "casa");
        int contarCasas = contarCasasActuales();
        ArrayList<Integer> limite = limiteGrupo();
        if (limite == null) {
            System.out.println("No se pudo obtener el límite del grupo.");
            return;
        }

        if (contarCasas >= 4) { // Verifica el límite de casas
            System.out.println("Has alcanzado el número máximo de casas que puedes construir en este grupo.");
            return;
        }

        jugadores.get(turno).getAvatar().getLugar().anhadirEdificacion(casa);
        System.out.println("Se ha construido una casa correctamente en la casilla " + jugadores.get(turno).getAvatar().getLugar().getNombre() + ". Hay " + (contarCasas + 1) + " casas construidas.");
    }
    
    private void edificarHotel(){
        
        Edificacion hotel = new Edificacion(jugadores.get(turno).getAvatar().getLugar(), "hotel");
        int contarcasas=contarCasasActuales();
        int contarhoteles=contarHotelesActuales();
        ArrayList<Integer> limite = limiteGrupo();


        if (contarhoteles >= limite.get(0)) {
            System.out.println("No puedes edificar un hotel hasta que tengas construidas al menos 4 casas");
        }
        if (contarcasas==4 && contarhoteles < limite.get(0)) {
            jugadores.get(turno).getAvatar().getLugar().anhadirEdificacion(hotel);
            System.out.println("Se ha construido el hotel correctamente, en la casilla "+ jugadores.get(turno).getAvatar().getLugar().getNombre()+"hay "+contarcasas+1+"casas construidas");   
            jugadores.get(turno).getAvatar().getLugar(). //eliminar casas
        }            
        }


    private void edificarPiscina(){}
    private void edificarPista(){
        Edificacion pista = new Edificacion(jugadores.get(turno).getAvatar().getLugar(), "pista");

        
    }





}
