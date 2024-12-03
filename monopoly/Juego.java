package monopoly;
import java.util.ArrayList;
import java.util.Iterator;
import partida.*;
import monopoly.Excepciones.*;

public class Juego implements Comando{

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
    private boolean partidaEmpezada = false;
    private ArrayList<Trato> tratosTotales;
    private boolean mensajeTrato = true;
    private int idFinalTratos = 0;

    public static ConsolaNormal consola = new ConsolaNormal();
    public Trato claseTrato = new Trato();

    public Juego(){
        this.jugadores = new ArrayList<Jugador>();
        this.avatares = new ArrayList<Avatar>();
        this.banca = new Jugador();
        this.tablero = new Tablero(banca);
        this.dado1 = new Dado();
        this.dado2 = new Dado();
        this.tratosTotales = new ArrayList<Trato>();
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

    public boolean getPartidaEmpezada(){
        return partidaEmpezada;
    }

    public void setPartidaEmpezada(boolean partidaEmpezada){
        this.partidaEmpezada = partidaEmpezada;
    }

    public ArrayList<Trato> getTratosTotales(){
        return tratosTotales;
    }

    public void setTratosTotales(ArrayList<Trato> tratosTotales){
        this.tratosTotales = tratosTotales;
    }

    public int getIdFinalTratos(){
        return idFinalTratos;
    }

    public void setIdFinalTratos(int idFinalTratos){
        this.idFinalTratos = idFinalTratos;
    }

    // Método para inciar una partida: crea los jugadores y avatares.
    private void iniciarPartida() {
    
        while (true) { 
            if (partidaEmpezada && jugadores.size() < 2) {
                consola.imprimir("El jugador " + jugadores.get(0).getNombre() + " ha ganado la partida.");
                break;
            }
        
            if (!partidaEmpezada) {
                consola.imprimir("=====================================\n");
                consola.imprimir("                MENÚ                \n");
                consola.imprimir("=====================================\n");
        
                consola.imprimir("1. Crear un jugador                                    -> Comando: 'crear jugador NombreJugador tipoAvatar'");
                consola.imprimir("2. Empezar partida                                     -> Comando: 'empezar'");
        
                consola.imprimir("=====================================\n");
                consola.imprimir("Selecciona una opción para continuar.\n");
                consola.imprimir("=====================================\n\n");
        
                
                String comando = consola.leer();
                analizarComando(comando);
            }else{
                if(mensajeTrato){ //para que solo se imprima una vez cada turno
                    if(!jugadores.get(turno).getTratosRecibidos().isEmpty()){
                        consola.imprimir("Tienes nuevos tratos.\n");
                    
                        for(Trato tratoAImprimir:jugadores.get(turno).getTratosRecibidos()){
                            if(tratoAImprimir.getFortunaACambiar() < tratoAImprimir.getJugadorOfrece().getFortuna() && tratoAImprimir.getFortunaARecibir() < tratoAImprimir.getJugadorRecibe().getFortuna()){ //si el jugador que recibe el cambio no le llega el dinero, se espera a que tenga suficiente dinero
                                if(!(tratoAImprimir.getAceptado())){ //si el trato ha sido aceptado no se hace nada
                                    consola.imprimir("Tienes un nuevo trato.\n");
                                    claseTrato.manejarTrato(tratoAImprimir, jugadores.get(turno));
                                }
                            }else{
                                consola.imprimir(String.format("Un jugador no tiene suficiente dinero para realizar el trato.\n", tratoAImprimir.getJugadorRecibe().getNombre()));
                            }
                        }
                        mensajeTrato = false; //para que solo se imprima una vez cada turno
                    }
                

                        //si alguno de los tratos ha sido aceptado lo eliminamos del array de recibidos
                    /*if(!(jugadores.get(turno).getTratosRecibidos().isEmpty())){ //chequeamos que el array no esté vacío
                        Iterator<Trato> iteratorRecibidos = jugadores.get(turno).getTratosRecibidos().iterator();
                        boolean aceptadoRecibidos;
                        while(iteratorRecibidos.hasNext()){
                            aceptadoRecibidos = iteratorRecibidos.next().getAceptado();
                            if(aceptadoRecibidos){
                                iteratorRecibidos.remove();
                            }
                        }
                    }*/
        
                        //si alguno de los tratos ha sido aceptado lo eliminamos del array de ofrecidos
                    if(!(jugadores.get(turno).getTratosOfrecidos().isEmpty())){ //chequeamos que el array no esté vacío
                        Iterator<Trato> iteratorOfrecidos = jugadores.get(turno).getTratosOfrecidos().iterator();
                        boolean aceptadoOfrecidos;
                        while(iteratorOfrecidos.hasNext()){
                            aceptadoOfrecidos = iteratorOfrecidos.next().getAceptado();
                            if(aceptadoOfrecidos){
                                iteratorOfrecidos.remove();
                            }
                        }
                    }
                }

                consola.imprimir("\nEl turno es del jugador: "+jugadores.get(turno).getNombre().toUpperCase());
        
                consola.imprimir("=====================================\n");
                consola.imprimir("                MENÚ                \n");
                consola.imprimir("=====================================\n");
        
                consola.imprimir("1. Jugador del turno actual                            -> Comando: 'jugador'");
                consola.imprimir("2. Listar jugadores                                    -> Comando: 'listar jugadores'");
                consola.imprimir("3. Listar avatares                                     -> Comando: 'listar avatares'");
                consola.imprimir("4. Lanzar dados                                        -> Comando: 'lanzar dados'");
                consola.imprimir("5. Acabar turno                                        -> Comando: 'acabar turno'");
                consola.imprimir("6. Salir de la cárcel                                  -> Comando: 'salir carcel'");
                consola.imprimir("7. Describir casilla                                   -> Comando: 'describir nombreCasilla'");
                consola.imprimir("8. Describir jugador                                   -> Comando: 'describir jugador nombreJugador'");
                consola.imprimir("9. Describir avatar                                    -> Comando: 'describir avatar idAvatar'");
                consola.imprimir("10. Comprar casilla                                    -> Comando: 'comprar 'nombreCasilla'");
                consola.imprimir("11. Listar casillas en venta                           -> Comando: 'listarenventa'");
                consola.imprimir("12. Ver tablero                                        -> Comando: 'ver'");
                consola.imprimir("13. Construir un edificio                              -> Comando: 'edificar tipoEdificacion'");
                consola.imprimir("14. Listar edificios construidos                       -> Comando: 'listar edificios'");
                consola.imprimir("15. Listar edificios construidos en grupo              -> Comando: 'listar edificios colorGrupo'");
                consola.imprimir("16. Hipotecar una propiedad                            -> Comando: 'hipotecar'");
                consola.imprimir("17. Declararse en bancarrota                           -> Comando: 'bancarrota'");
                consola.imprimir("18. Deshipotecar una propiedad                         -> Comando: 'deshipotecar'");
                consola.imprimir("19. Vender edificios                                   -> Comando: 'vender tipoEdificacion nombrePropiedad numeroElementosAvender'");
                consola.imprimir("20. Mostrar estadísticas de un jugador                 -> Comando: 'estadisticas nombreJugador'");
                consola.imprimir("21. Mostrar estadísticas del juego                     -> Comando: 'estadisticas'");
                consola.imprimir("22. Cambiar modo de movimiento de los avatares         -> Comando: 'cambiar modo'");
                consola.imprimir("23. Crear trato                                        -> Comando: 'trato'");
                consola.imprimir("24. Listar tratos recibidos                            -> Comando: 'listar tratos'");
        
                consola.imprimir("=====================================\n");
                consola.imprimir("Selecciona una opción para continuar.\n");
                consola.imprimir("=====================================\n\n");
        
                String comando = consola.leer();
                analizarComando(comando);
            }
        }
    }        
    
    /*Método que interpreta el comando introducido y toma la accion correspondiente.
    * Parámetro: cadena de caracteres (el comando).
    */
    private void analizarComando(String comando) {
        
        String[] palabras = comando.split(" ");

        // Si no hay palabras suficientes, no es un comando válido
        if (palabras.length < 1) {
            consola.imprimir("Comando inválido.");
        }

        String metodo = palabras[0];
        try {
            if (!partidaEmpezada) {
                switch (metodo) {
                    case "crear":
                        if(!partidaEmpezada){
                            if (palabras.length >= 4 && palabras[1].equals("jugador")) {
                                crearJugador(palabras);
                            } else {
                                consola.imprimir("Comando incompleto o incorrecto para crear jugador.\n");
                            }
                        }else{
                            consola.imprimir("No se puede crear un jugador cuando la partida ya ha empezado.\n");
                        }
                        break;
                    case "empezar":
                        empezar();
                        break;
                    default:
                        consola.imprimir("Error, comando desconocido.\n");
                        break;
                    }}else{
                        switch (metodo) {    

                            case "jugador":
                                if(jugadores.size()>0){
                                    jugador();
                                }else{
                                    consola.imprimir("No hay ningún jugador creado en la partida");
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
                                    }else if(palabras[1].equals("tratos")){
                                        listarTratos();
                                    }
                                }else if(palabras.length==3){
                                        //listarEdificiosGrupo();
                                }else {
                                    consola.imprimir("Error, comando desconocido.\n");
                                }
                                break;
                            case "lanzar":
                                if (palabras.length == 2 && palabras[1].equals("dados")) {
                                    lanzarDados();
                                
                                } else {
                                    consola.imprimir("Error, comando desconocido.\n");
                                }
                                break;
                            case "acabar":
                                if (palabras.length == 2 && palabras[1].equals("turno")) {
                                    acabarTurno();
                                } else {
                                    consola.imprimir("Error, comando desconocido.\n");
                                }
                                break;
                            case "salir":
                                if(jugadores.size()>0){
                                    if (palabras.length == 2 && palabras[1].equals("carcel")) {
                                        salirCarcel();
                                    } else {
                                        consola.imprimir("Error, comando desconocido.\n");
                                    }
                                }else{
                                    consola.imprimir("No hay jugadores creados en la partida");
                                }
                                break;
                            case "describir":
                                if (palabras.length == 2) {
                                    String nombreCasilla = palabras[1];
                                    descCasilla(nombreCasilla);
                                }else if(palabras.length == 3){
                                    if(palabras[1].equals("jugador")){
                                        String nombreJugador = palabras[2];
                                        descJugador(nombreJugador);
                                    }else if(palabras[1].equals("avatar")){
                                        String[] idAvatar = new String[]{palabras[2]};
                                        descAvatar(idAvatar);
                                }else {
                                    consola.imprimir("Error, comando desconocido.\n");
                                }
                                }
                                break;
                            case "comprar":
                                if (palabras.length == 2) {
                                    String nombreCasilla = palabras[1];
                                    comprar(nombreCasilla);
                                } else {
                                    consola.imprimir("Error, comando desconocido.\n");
                                }
                                break;
                            case "listarenventa":
                                listarVenta();
                                break;
                            case "ver":
                                consola.imprimir(tablero.toString());
                                break;
                            case "edificar":
                                if(palabras.length==2){
                                    String tipoEdificacion=palabras[1];
                                    edificar(tipoEdificacion);
                                }
                                break;
                            case "hipotecar":
                                if(palabras.length==1){
                                    hipotecar(jugadores.get(turno), tablero);
                                
                                }
                                break;
                            case "bancarrota":
                                bancarrotaABanca(jugadores.get(turno), banca, jugadores, avatares);
                                acabarTurno(); //acabamos el turno automáticamente para que sigan jugando el resto
                                consola.imprimir("Jugador eliminado con éxito. El siguiente jugador puede ahora elegir una opción.\n");
                                break;
                            case "deshipotecar":
                                if(palabras.length==1){
                                    deshipotecar(jugadores.get(turno), tablero);
                                }
                                break;    
                            case "vender":
                                if(palabras.length==4){
                                    String tipoEdificacion=palabras[1];
                                    String nombreCasilla=palabras[2];
                                    String numeroventa = palabras[3];
                                    int numvender = Integer.parseInt(numeroventa);
                                    vender(tipoEdificacion,nombreCasilla,numvender);
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
                                cambiar(palabras);
                                break;
                            case "trato":
                                trato();
                                break;
                            default:
                                consola.imprimir("Error, comando desconocido.\n");
                                break;
                }}
        }catch(Excepciones_EmpezarPartida e){
            consola.imprimir("Error: "+e.getMessage());
        }catch(Excepciones_DescAv e){
            consola.imprimir("Error "+ e.getMessage());
        }catch(Excepciones_DescCas e){
            consola.imprimir("Error "+ e.getMessage());

        }catch(Excepciones_DescJug e){
            consola.imprimir("Error "+ e.getMessage());

        }catch(Excepciones_JugadorLanz e){
            consola.imprimir("Error "+ e.getMessage());

        }catch(Excepciones_JugadorSalCar e){
            consola.imprimir("Error "+ e.getMessage());
        }catch(Excepciones_PropComprar e){
            consola.imprimir("Error: "+e.getMessage());
        }catch (Excepciones_PropVenderEdif e){
            consola.imprimir("Error "+e.getMessage());;
        }

    }

    public void crearJugador(String[] palabras) throws Excepciones_EmpezarPartida{
        String nombre = palabras[2];
        String tipoAvatar = palabras[3];
    
        if(palabras[3].equals("sombrero") || palabras[3].equals("esfinge") || palabras[3].equals("coche") || palabras[3].equals("pelota")) {
            if(jugadores.size() <= 5){
                Jugador jugador = new Jugador(nombre,tablero.getPosiciones().get(0).get(0), avatares, tipoAvatar);
                jugadores.add(jugador);
                consola.imprimir("Jugador creado con éxito.\n");    
                consola.imprimir("{\n  Nombre:"+jugador.getNombre()+"\n  Avatar:"+jugador.getAvatar().getId()+"\n}");
            }else{
                throw new Excepciones_EmpezarPartida("Se ha alcanzado el número máximo de jugadores creados\n");
            }
        } else {
            throw new Excepciones_EmpezarPartida("El avatar debe ser de tipo pelota, coche, esfinge o sombrero\n");
        }
    }

    public void jugador(){
        consola.imprimir("\tNombre:"+jugadores.get(turno).getNombre());
        consola.imprimir("\tAvatar: "+jugadores.get(turno).getAvatar().getId());
    }

    public void empezar() throws Excepciones_EmpezarPartida{
        if(jugadores.size() < 2){
            throw new Excepciones_EmpezarPartida("Se deben crear mínimo 2 jugadores para empezar la partida\n");
        }else{
            setPartidaEmpezada(true);
        }
    }

    /*Método que realiza las acciones asociadas al comando 'describir jugador'.
    * Parámetro: comando introducido
     */
    public void descJugador(String jugadornombre) throws Excepciones_DescJug{
        int encontrado=0;
        for(Jugador jugador:jugadores){
                if (jugadornombre.equals(jugador.getNombre())) 
                    {
                        encontrado++;
                        consola.imprimir("{\n" +
                            "  nombre: " + jugador.getNombre() + ",\n" +
                            "  avatar: " + jugador.getAvatar().getId() + ",\n" +
                            "  fortuna: " + jugador.getFortuna() + ",\n" +
                            "  propiedades: [");
                    
                        // Iterar por las propiedades del jugador
                        for (Propiedad propiedad : jugador.getPropiedades()) {
                            if (!propiedad.getHipotecado()) {  
                            consola.imprimir("\t"+propiedad.getNombre() +  ", ");
                            }
                        }
                    
                        consola.imprimir("   ],\n" +
                                "  hipotecas: [");
                    
                        // Mostrar propiedades hipotecadas
                        for (Propiedad propiedad : jugador.getPropiedades()) {
                            if (propiedad.getHipotecado()) {
                                consola.imprimir("\t"+propiedad.getNombre() + ", ");
                            }
                        }
                    
                        consola.imprimir("   ],\n" +
                                "  edificios: [");
                    
                        // Mostrar los edificios del jugador
                        for (Propiedad propiedad : jugador.getPropiedades()) {
                            if (propiedad instanceof Solar) {
                                Solar solar = (Solar) propiedad;
                                for (Edificacion edificio : solar.getEdificacion()) {
                                    consola.imprimir("\t"+edificio.getNombre() + "-" + edificio.getId() + ", ");
                                }
                            }
                        }
                    
                        consola.imprimir("   ]\n}\n");
            }
        }
        if(encontrado==0){
            throw new Excepciones_DescJug("Ese jugador no existe\n");}
    }


    /*Método que realiza las acciones asociadas al comando 'describir avatar'.
    * Parámetro: id del avatar a describir.
    */
    public void descAvatar(String[] palabras) throws Excepciones_DescAv {
        int encontrado=0;
        for(Avatar avatar:avatares){
            if((avatar.getId()).equals(palabras[0])){
                consola.imprimir("{\nid: " + avatar.getId() + ",\ntipo: " + avatar.tipoAvatar() + ",\ncasilla: " + avatar.getLugar().getNombre() + ",\njugador: " + avatar.getJugador().getNombre() + "\n}\n");
                encontrado++;
            }

        }
        if(encontrado==0){
            throw new Excepciones_DescAv("Ese avatar no existe\n");

        }
    }

    /* Método que realiza las acciones asociadas al comando 'describir nombre_casilla'.
    * Parámetros: nombre de la casilla a describir.
    */
    public void descCasilla(String nombre) throws Excepciones_DescCas {
        Casilla casilla = tablero.encontrar_casilla(nombre);
        if (casilla==null){
            throw new Excepciones_DescCas("Esa casilla no existe\n");
        } else{
            casilla.setTablero(tablero);
            consola.imprimir(casilla.infoCasilla());
        }   
    }

    //Método que ejecuta todas las acciones relacionadas con el comando 'lanzar dados'.
    public void lanzarDados() throws Excepciones_JugadorLanz {
        if (getTirado()) {
            throw new Excepciones_JugadorLanz("Ya has lanzado los dados en este turno");
            //return;

        }
        //Si existe una restricción de turnos sin tirar(mov avanzado coche), no podremos lanzar los dados
        //Si es un coche, lo casteamos y gestionamos las restricciones de tiradas
        if(jugadores.get(turno).getAvatar() instanceof Coche){
            Coche coche= (Coche)jugadores.get(turno).getAvatar();
            if(coche.getRestriccionTiradas()!=0){

            //Le restamos 1 a los turnos restantes para poder volver a tirar
            coche.setRestriccionTiradas(coche.getRestriccionTiradas()-1);
            throw new Excepciones_JugadorLanz("Debido a la restricción del mov avanzado de coche, tiene que esperar "+ coche.getRestriccionTiradas()+ " turnos para volver a lanzar los dados");
            //return;
            }
        }
        

        //LANZAR DADOS ALEATORIOS
        /*dado1.hacerTirada();
        dado2.hacerTirada();

        consola.imprimir("Dado 1: " + dado1.getValor());
        consola.imprimir("Dado 2: " + dado2.getValor());*/

        
        
        //LANZAR DADOS MANUAL
        
        
        
        consola.imprimir("Introduce dado1: ");
        int da = Integer.parseInt(consola.leer());
        dado1.setValor(da);
        consola.imprimir("Introduce dado2: ");
        int da2 = Integer.parseInt(consola.leer());
        dado2.setValor(da2);

        
        int sumaDados = dado1.getValor() + dado2.getValor();
        setDadosdobles(dado1.equals(dado2));

        //Si el jugador está en la carcel:
        if (jugadores.get(turno).getEnCarcel()) {
            if (getDadosdobles()) {
                consola.imprimir("Has sacado dobles y sales de la cárcel.");

                jugadores.get(turno).setEnCarcel(false);

                String posicionActual = jugadores.get(turno).getAvatar().getLugar().getNombre();
                
                if(jugadores.get(turno).getAvatar().getAvanzado()==0){
                    jugadores.get(turno).getAvatar().moverBasico(tablero.getPosiciones(), sumaDados);
                }else{
                    jugadores.get(turno).getAvatar().moverAvanzado(tablero.getPosiciones(), sumaDados,this);
                }
                
                String posicionFinal = jugadores.get(turno).getAvatar().getLugar().getNombre();

                //Repintamos el tablero
                consola.imprimir(tablero.toString());

                //Imprimimos el mensaje final:
                consola.imprimir("El avatar: "+jugadores.get(turno).getAvatar().getId()+", avanza "+sumaDados+" posiciones, desde "+posicionActual+" hasta "+posicionFinal);    

            } else {
                jugadores.get(turno).setTiradasCarcel(jugadores.get(turno).getTiradasCarcel() + 1);
                    if (jugadores.get(turno).getTiradasCarcel() >= 3) {

                        consola.imprimir("Has fallado 3 veces.");
                        jugadores.get(turno).setEnCarcel(false);

                        jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna() - jugadores.get(turno).getAvatar().getLugar().valorCarcel(tablero));
                        
                        //Calculamos la posición inicial
                        String posicionActual = jugadores.get(turno).getAvatar().getLugar().getNombre();
                        if(jugadores.get(turno).getAvatar().getAvanzado()==0){
                            jugadores.get(turno).getAvatar().moverBasico(tablero.getPosiciones(), sumaDados);
                        }else{
                            jugadores.get(turno).getAvatar().moverAvanzado(tablero.getPosiciones(), sumaDados,this);
                        }

                        //Calculamos la posición final
                        String posicionFinal = jugadores.get(turno).getAvatar().getLugar().getNombre();

                        //Repintamos el tablero
                        consola.imprimir(tablero.toString());

                        
                        //Imprimimos el mensaje final:
                        consola.imprimir("El avatar: "+jugadores.get(turno).getAvatar().getId()+", avanza "+sumaDados+" posiciones, desde "+posicionActual+" hasta "+posicionFinal);
                    }else{
                        throw new Excepciones_JugadorLanz("No puedes moverte porque estás en la cárcel\n");
                    }
                setTirado(true);
                return;
            }
        //JUGADOR NO ESTÁ EN LA CÁRCEL
        }else{

            jugadores.get(turno).setVecesDados(jugadores.get(turno).getVecesDados() + 1); //sumamos 1 al jugador que lanza los dados en el atributo vecesDados para saber cuantas veces lanzó los dados

            String posicionActual=jugadores.get(turno).getAvatar().getLugar().getNombre();
            int pos_ini = jugadores.get(turno).getAvatar().getLugar().getPosicion();

    
            if(jugadores.get(turno).getAvatar().getAvanzado()==0){
                jugadores.get(turno).getAvatar().moverBasico(tablero.getPosiciones(), sumaDados);
            }else{
                jugadores.get(turno).getAvatar().moverAvanzado(tablero.getPosiciones(), sumaDados,this);
            }

            
            // Evaluar la casilla en la que ha caído
            jugadores.get(turno).getAvatar().getLugar().evaluarCasilla(tablero, jugadores.get(turno), banca, sumaDados,this);

            // Verificar si el jugador ha dado la vuelta al tablero
            if (jugadores.get(turno).getAvatar().getLugar().getPosicion() < sumaDados) {
                //if(avatarActual.getLugar().getNombre().equals("ircarcel")){
                /*if(avatarActual.getLugar().getPosicion() == 30){
                    consola.imprimir("Has caído en la carcel.\n");
                    jugadores.get(turno).encarcelar(tablero.getPosiciones());
                }else{*/
                    jugadores.get(turno).setVueltas(jugadores.get(turno).getVueltas() + 1); //sumamos 1 vuelta a la variable vueltas que nos permite saber si los jugadores han dado 4 vueltas más

                    jugadores.get(turno).setNumVueltas(jugadores.get(turno).getNumVueltas() + 1); //sumamos 1 vuelta al contador total de vueltas

                    consola.imprimir("¡Has pasado por la casilla de salida! Recibes tu recompensa.\n");

                    jugadores.get(turno).sumarFortuna(Valor.SUMA_VUELTA);  //esto se puede cambiar
                    
                    jugadores.get(turno).setDineroCobradoSalida(jugadores.get(turno).getDineroCobradoSalida() + Valor.SUMA_VUELTA); //le sumamos a la estadística del dinero recibido por pasar por inicio

                    tablero.calcularCasillas(jugadores); //calculamos el nuevo valor que reciben los jugadores al pasar por la casilla inicio
            }

            setTirado(true); //El jugador ya ha lanzado los dados en este turn0

            //Sacó dobles y no es mov avanzado de coche
            if (getDadosdobles()&&(jugadores.get(turno).getAvatar() instanceof Coche && getLanzamientos()<4 && jugadores.get(turno).getAvatar().getAvanzado()==1 && sumaDados>4)==false) {
                setTirado(false); // Permitir volver a tirar
                setLanzamientosDobles(getLanzamientosDobles()+1);

                // Si sacó dobles 3 veces, va a la cárcel
                if (getLanzamientosDobles() == 3) {
                    consola.imprimir("Has sacado dobles 3 veces seguidas, vas a la cárcel.");
                    jugadores.get(turno).encarcelar(tablero.getPosiciones());
                    setTirado(true);
                }else{
                    consola.imprimir("Has sacado dobles, puedes lanzar de nuevo.");
                }
            } else {
                setLanzamientosDobles(0); // Resetear el contador de lanzamientos dobles
            }

            //Si el valor de la tirada es>4, es mov avanzado de coche y lanzamientos es  <4 veces, que es lo máximo peromitido-----> permitimos volver a lanzar los dados, es decir, setTirado()=false
            if(jugadores.get(turno).getAvatar() instanceof Coche && getLanzamientos()<4 && jugadores.get(turno).getAvatar().getAvanzado()==1 && sumaDados>4){
                setTirado(false);
                setLanzamientos(getLanzamientos()+1);
            //Si se ha acabado el turno del coche, el valor compras se reestablece a 0
            }else{
                setLanzamientos(0);
                
                //ESTO ES LO QUE FALLA EN MODO NORMAL
                if (jugadores.get(turno).getAvatar() instanceof Coche) {    
                Coche coche=(Coche) jugadores.get(turno).getAvatar();
                coche.setCompras(0);
            }
                
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
            consola.imprimir(tablero.toString());
            //Imprimimos el mensaje final:
            if(this.jugadores.get(turno).getAvatar().getAvanzado()==1&& sumaDados<=4){
                consola.imprimir("El avatar:" +jugadores.get(turno).getAvatar().getId()+", retrocede " +sumaDados+" casillas");

            }else{
                consola.imprimir("El avatar: "+jugadores.get(turno).getAvatar().getId()+", avanza "+num_casillas+" posiciones, desde "+posicionActual+" hasta "+posicionFinal);    
            }

        }

    
}


    /*Método que ejecuta todas las acciones realizadas con el comando 'comprar nombre_casilla'.
    * Parámetro: cadena de caracteres con el nombre de la casilla.
     */
    public void comprar(String nombre) throws Excepciones_PropComprar{
        try{
            if(nombre.equals(jugadores.get(turno).getAvatar().getLugar().getNombre())){
                if(tirado||dadosdobles){
                    Casilla casilla = tablero.encontrar_casilla(nombre);
                    Propiedad propiedad = (Propiedad)casilla;
                    propiedad.comprarCasilla(jugadores.get(turno),banca);
                }else{
                    throw new Excepciones_PropComprar("Primeor debes tirar los dados");
                }
            }else{
                throw new Excepciones_PropComprar("Debes de estar sobre esa casilla para comprarla");
            }
        }catch(Excepciones_PropComprar e){
            consola.imprimir("Error: "+e.getMessage());
        }
        
    }
    //Método que ejecuta todas las acciones relacionadas con el comando 'salir carcel'. 
    public void salirCarcel() throws Excepciones_JugadorSalCar{
        //Si el jugador está en la cárcel
        //if(jugadores.get(turno).getAvatar().getLugar().getNombre()=="carcel"){
        if(jugadores.get(turno).getEnCarcel()){
            //Si está en la cárcel y además le llega el dinero:
            if(jugadores.get(turno).getFortuna()>=jugadores.get(turno).getAvatar().getLugar().valorCarcel(tablero)){
                
                jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna()-jugadores.get(turno).getAvatar().getLugar().valorCarcel(tablero));
                
                jugadores.get(turno).setEnCarcel(false);
            
                jugadores.get(turno).setDineroTasasEImpuestos(jugadores.get(turno).getDineroTasasEImpuestos() + jugadores.get(turno).getAvatar().getLugar().valorCarcel(tablero)); //añadimos al atributo dineroTasasEImpuestos el valor pagado por salir de la cárcel
                consola.imprimir("Sales de la cárcel pagando " +jugadores.get(turno).getAvatar().getLugar().valorCarcel(tablero)+"€");
            

            }else{
                consola.imprimir("No tienes dinero suficiente para salir de la cárcel.");
                throw new Excepciones_JugadorSalCar("No tienes dinero suficiente para salir de la cárcel.\n");
                //falta poner que pierde la partida
            }
        }else{
            //Si el jugador no está en la cárcel
            throw new Excepciones_JugadorSalCar("El jugador no está en la cárcel\n");
        }
    }

    // Método que realiza las acciones asociadas al comando 'listar enventa'.
    public void listarVenta() {
        int i;
        StringBuilder sb = new StringBuilder();

        for(i = 0; i < 4; i++){
            for(Casilla casilla:tablero.getPosiciones().get(i)){
                if(casilla.getDuenho().getNombre().equals("banca")){
                    if (casilla instanceof Propiedad) {
                    Propiedad propiedad = (Propiedad)casilla;
                    if(propiedad instanceof Solar){
                        Solar solar = (Solar)propiedad;
                        sb.append(String.format("{\n tipo: solar,\n"));
                        sb.append(String.format("\n grupo: %s,\n", solar.getGrupo().getColorGrupo()));
                        sb.append(String.format("\n valor: %s,\n", solar.getValor()));
                        sb.append("},\n");
                    }else if(propiedad instanceof Servicio){
                        Servicio servicio = (Servicio)propiedad;
                        sb.append(String.format("{\n tipo: servicio,\n"));
                        sb.append(String.format("\n valor: %s,\n", servicio.getValor()));
                        sb.append("},\n");
                    }else if(propiedad instanceof Transporte){
                        Transporte transporte = (Transporte)propiedad;
                        sb.append(String.format("{\n tipo: transporte,\n"));
                        sb.append(String.format("\n valor: %s,\n", transporte.getValor()));
                        sb.append("},\n");

                    }
                }
                }
            }
        }
        consola.imprimir(sb.toString());
    }


    //Método que realaiza las acciones asociadas al comando 'listar jugadores'.
public void listarJugadores() {
    for (Jugador jugador : jugadores) {
        consola.imprimir("{\n" +
                "  nombre: " + jugador.getNombre() + ",\n" +
                "  avatar: " + jugador.getAvatar().getId() + ",\n" +
                "  fortuna: " + jugador.getFortuna() + ",\n" +
                "  propiedades: [");

        // Iterar por las propiedades del jugador
        for (Propiedad propiedad : jugador.getPropiedades()) {
            if (!propiedad.getHipotecado()) {  
            consola.imprimir("\t"+propiedad.getNombre() +  ", ");
            }
        }

        consola.imprimir("   ],\n" +
                "  hipotecas: [");

        // Mostrar propiedades hipotecadas
        for (Propiedad propiedad : jugador.getPropiedades()) {
            if (propiedad.getHipotecado()) {
                consola.imprimir("\t"+propiedad.getNombre() + ", ");
            }
        }

        consola.imprimir("   ],\n" +
                "  edificios: [");

        // Mostrar los edificios del jugador
        for (Propiedad propiedad : jugador.getPropiedades()) {
            if (propiedad instanceof Solar) {
                Solar solar = (Solar) propiedad;
                for (Edificacion edificio : solar.getEdificacion()) {
                    consola.imprimir("\t"+edificio.getNombre() + "-" + edificio.getId() + ", ");
                }
            }
        }

        consola.imprimir("   ]\n}\n");
    }
}


    // Método que realiza las acciones asociadas al comando 'listar avatares'.
    public void listarAvatares() {
        for(int i=0;i<avatares.size();i++){
            consola.imprimir("{\nid: " + avatares.get(i).getId() + ",\ntipo: " + avatares.get(i).tipoAvatar() + ",\ncasilla: " + avatares.get(i).getLugar().getNombre() + ",\njugador: " + avatares.get(i).getJugador().getNombre()+ "\n}\n");
        }
    }


    public void edificar(String tipo){
        try{
            if (jugadores.get(turno).getAvatar().getLugar() instanceof Solar) {
                Solar solar = (Solar)jugadores.get(turno).getAvatar().getLugar();
            switch (tipo) {
                case "casa":
                    solar.edificarCasa(jugadores.get(turno),turno);
                    break;
                case "hotel":
                    solar.edificarHotel(jugadores.get(turno),turno);
                    break;
                case "piscina":
                    solar.edificarPiscina(jugadores.get(turno),turno);
                    break;
                case "pista":
                    solar.edificarPista(jugadores.get(turno),turno);
                    break;
                default:
                    break;
                }
            }
        }catch(Excepciones_PropConstruir e){
            consola.imprimir("Error: "+e.getMessage());;
        }
    }



    public void vender(String tipo, String nombreCasilla, int numeroventa) throws Excepciones_PropVenderEdif{
        Casilla casilla = tablero.encontrar_casilla(nombreCasilla);
        if (casilla instanceof Solar) {
            Solar solar = (Solar)casilla;
            switch (tipo) {
                case "casa":
                    solar.venderCasa(solar, numeroventa, jugadores.get(turno));
                    break;
                case "hotel":
                    solar.venderHotel(solar,numeroventa,jugadores.get(turno));
                    break;
                case "piscina":
                    solar.venderPiscina(solar,numeroventa,jugadores.get(turno));
                    break;
                case "pista":
                    solar.venderPista(solar,numeroventa,jugadores.get(turno));
                    break;
                default:
                    break;
                }
            }

    }


    public void listarEdificios(){
        for(ArrayList<Casilla> lados:tablero.getPosiciones()){
            for (Casilla casilla:lados){
                if (casilla instanceof Solar) {
                        Solar solar = (Solar)casilla;
                    if (!solar.getEdificacion().isEmpty()){
                        for(Edificacion edificacion : solar.getEdificacion()){
                            float valor=0;
                            if ((edificacion instanceof Casa) || (edificacion instanceof Hotel)) {
                                valor=0.6f;
                            }else if ((edificacion instanceof Piscina)) {
                                valor=0.4f;
                            }else if ((edificacion instanceof PistaDeporte)) {
                                valor=1.25f;
                            }

                            consola.imprimir("{\n\tid: "+edificacion.getNombre()+"-"+edificacion.getId()+
                                ",\n\tpropietario: " + casilla.getDuenho().getNombre() +
                                ",\n\tcasilla: " + casilla.getNombre() +
                                ",\n\tgrupo: " + solar.getGrupo().getColorGrupo() +
                                ",\n\tcoste: " + solar.getValor()*valor +
                                "\n}");
                        }
                    }
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

        mensajeTrato = true;  //reiniciamos la variable a false para que se vuelva a imprimir cada turno los tratos
    }


    public void cambiar(String[] palabras){
        if(palabras.length==2 && palabras[1].equals("modo")){
            if(jugadores.get(turno).getAvatar().getAvanzado()==1){
                jugadores.get(turno).getAvatar().setAvanzado(0);
                consola.imprimir("Movimiento avanzado desactivado\n");
            }else{
                jugadores.get(turno).getAvatar().setAvanzado(1);
                consola.imprimir("Movimiento avanzado activado\n");
            }
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
                if(numVeces < casilla.getVecesCaidasGrupal()){
                    numVeces = casilla.getVecesCaidasGrupal();
                }
            }
        }
        for(ArrayList<Casilla> lado:tablero.getPosiciones()){ //recorremos una segunda vez el tablero para ver que casillas tienen el nº máximo de caídas
            for(Casilla casilla:lado){
                if(numVeces == casilla.getVecesCaidasGrupal()){
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
                }
                if(aux != null){
                    if(posicionMax < (aux.getAvatar().getLugar().getPosicion())){ //buscamos la posición máxima de los jugadores que estén dentro del array
                        posicionMax = aux.getAvatar().getLugar().getPosicion();
                    }
                }else{
                    consola.imprimir("No se ha podido encontrar al jugador.\n");
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
                if(!(casilla instanceof Propiedad)){
                    if(dineroMax < casilla.getDineroCasilla()){
                        dineroMax = casilla.getDineroCasilla();
                    }
                }
            }
        }

        for(ArrayList<Casilla> lado:tablero.getPosiciones()){
            for(Casilla casilla:lado){
                if(!(casilla instanceof Propiedad)){
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
                if(casilla instanceof Solar){
                    Solar solar = (Solar)casilla;
                    ArrayList<Solar> array = solar.getGrupo().getMiembros();
                    for(Solar solarArray:array){ //sumamos todo el dinero de cada casilla del grupo
                        dineroAux += solarArray.getDineroCasilla();
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
                if(casilla instanceof Solar){
                    Solar solar = (Solar)casilla;
                    ArrayList<Solar> array = solar.getGrupo().getMiembros();
                    for(Solar solarArray:array){ //sumamos todo el dinero de cada casilla del grupo
                        dineroAux += solarArray.getDineroCasilla();
                    }
                    if(dineroAux == dineroMax){
                        return solar.getGrupo().getColorGrupo();
                    }else{
                        dineroAux = 0;
                    }
                }
            }
        }
        consola.imprimir("No hay un grupo más rentable\n");
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

        consola.imprimir(sb.toString());
    }

    public void estadisticasJugador(String nombre){
        Jugador jugador = null;

        for(Jugador aux:jugadores){
            if(aux.getNombre().equals(nombre)){
                jugador = aux;
            }
        }
        if(jugador == null){
            consola.imprimir("Error, no existe el jugador\n");
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

        consola.imprimir(sb.toString());
    }

    public void trato(){
        Trato trato = new Trato();
        trato.crearTrato(jugadores.get(turno), banca, this);
    }

    public void listarTratos(){
        claseTrato.listarTratos(jugadores.get(turno));
    }

    public int hipotecar(Jugador jugador, Tablero tablero) {

        if (!jugador.getPropiedades().isEmpty()) { // mientras el jugador tenga propiedades
            consola.imprimir("¿Qué casilla desea hipotecar?");
            String nombre = consola.leer();
    
            if (tablero.encontrar_casilla(nombre) == null) { // verificamos que la casilla exista
                consola.imprimir("No se ha podido encontrar la casilla.\n");
                return 0;
            } else {
                for (Propiedad propiedad : jugador.getPropiedades()) { // verificamos que el jugador tenga la casilla comprada
                    if (propiedad.getNombre().equals(nombre)) {
                        if (propiedad instanceof Solar) {
                            Solar solar = (Solar) propiedad;
                            if (solar.getEdificacion().isEmpty()) {
                                if (!solar.getHipotecado()) {
                                    jugador.setFortuna(jugador.getFortuna() + (solar.valorHipoteca(solar) / 2));
                                    solar.hipotecarPropiedad(solar);
    
                                    consola.imprimir(String.format("La casilla %s ha sido hipotecada con éxito.\n", nombre));
                                    consola.imprimir(String.format("%s recibe %f por hipotecar %s. Ahora no puedes recibir alquileres ni edificar en la casilla %s.\n",
                                            jugador.getNombre(), solar.valorHipoteca(solar) / 2, solar.getNombre(), solar.getNombre()));
                                    return 1;
                                } else {
                                    consola.imprimir("La casilla ya ha sido hipotecada.\n");

                                }
                            } else {
                                consola.imprimir("La casilla tiene edificaciones, debes venderlas antes de poder hipotecar la casilla.\n");
                                return 0;
                            }
                        } else {
                            if (!propiedad.getHipotecado()) {
                                jugador.setFortuna(jugador.getFortuna() + (propiedad.valorHipoteca(propiedad) / 2));
                                propiedad.hipotecarPropiedad(propiedad);
    
                                consola.imprimir(String.format("La casilla %s ha sido hipotecada con éxito.\n", nombre));
                                consola.imprimir(String.format("%s recibe %f por hipotecar %s. Ahora no puedes recibir alquileres ni edificar en la casilla %s.\n",
                                        jugador.getNombre(), propiedad.valorHipoteca(propiedad) / 2, propiedad.getNombre(), propiedad.getNombre()));
                                return 1;
                            }
                        }
                    }
                }
                consola.imprimir(String.format("La casilla %s no pertenece al jugador %s.\n", nombre, jugador.getNombre()));
                return 0;
            }
        }
        consola.imprimir("No tienes propiedades para hipotecar.\n");
        return 0;
    }
    

    public int deshipotecar(Jugador jugador, Tablero tablero) {

        if (!jugador.getPropiedades().isEmpty()) { // mientras el jugador tenga propiedades
            consola.imprimir("¿Qué casilla desea deshipotecar?");
            String nombre = consola.leer();
    
            if (tablero.encontrar_casilla(nombre) == null) { // verificamos que la casilla exista
                consola.imprimir("No se ha podido encontrar la casilla.\n");
                return 0;
            } else {
                for (Propiedad propiedad : jugador.getPropiedades()) { // verificamos que el jugador tenga la casilla comprada
                    if (propiedad.getNombre().equals(nombre)) {
                        if (propiedad.getHipotecado()) { // verificamos si la casilla está hipotecada
                            float costoDeshipotecar = (propiedad.valorHipoteca(propiedad) / 2) * 1.1f;
                            if (jugador.getFortuna() >= costoDeshipotecar) { // verificamos si el jugador tiene dinero suficiente
                                jugador.setFortuna(jugador.getFortuna() - costoDeshipotecar);
                                propiedad.setHipotecado(false);
    
                                consola.imprimir(String.format("La casilla %s ha sido deshipotecada con éxito.\n", nombre));
                                consola.imprimir(String.format("%s paga %f por deshipotecar %s. Ahora puedes recibir alquileres y edificar en la casilla %s.\n",
                                        jugador.getNombre(), costoDeshipotecar, propiedad.getNombre(), propiedad.getNombre()));
                                return 1;
                            } else {
                                consola.imprimir(String.format("El jugador %s no tiene suficiente dinero para deshipotecar la casilla %s.\n",
                                        jugador.getNombre(), propiedad.getNombre()));
                                return 0;
                            }
                        } else {
                            consola.imprimir(String.format("La casilla %s no está hipotecada.\n", propiedad.getNombre()));
                            return 0;
                        }
                    }
                }
                consola.imprimir(String.format("La casilla %s no pertenece al jugador %s.\n", nombre, jugador.getNombre()));
                return 0;
            }
        }
    
        consola.imprimir("No tienes propiedades para deshipotecar.\n");
        return 0;
    }
    

public void bancarrotaABanca(Jugador actual, Jugador banca, ArrayList<Jugador> jugadores, ArrayList<Avatar> avatares){ //falta mirar si las propiedades van a la banca o a un jugador determinado
    for(Propiedad propiedad:actual.getPropiedades()){ //pasamos todas las propiedades del jugador a la banca
        if (propiedad instanceof Solar) {
            Solar solar = (Solar)propiedad;
        if(!(solar.getEdificacion().isEmpty())){
            solar.getEdificacion().clear();
            }
        }
        propiedad.setHipotecado(false);
        propiedad.setDuenho(banca);
    }
    
    banca.setFortuna(banca.getFortuna() + actual.getFortuna()); //pasamos toda la fortuna del jugador a la banca

    actual.setFortuna(0);

    jugadores.remove(actual);
    avatares.remove(actual.getAvatar());
}

public void bancarrotaAJugador(Jugador actual, Jugador receptor, ArrayList<Jugador> jugadores, ArrayList<Avatar> avatares){

    for(Propiedad propiedad:actual.getPropiedades()){ //pasamos todas las propiedades del jugador a la banca
        if (propiedad instanceof Solar) {
            Solar solar = (Solar)propiedad;
        if(!(solar.getEdificacion().isEmpty())){
            solar.getEdificacion().clear();
            }
        }
        propiedad.setHipotecado(false);
        propiedad.setDuenho(receptor);
    }
    /*for(Casilla casilla:actual.getPropiedades()){ //pasamos todas las propiedades del jugador que llama bancarrota al jugador que las recibe
        receptor.getPropiedades().add(casilla);
        actual.getPropiedades().remove(casilla);
    }*/

    Iterator<Propiedad> iterator = actual.getPropiedades().iterator();
    while(iterator.hasNext()){ //pasamos todas las propiedades del jugador que llama bancarrota al jugador que las recibe
        Propiedad propiedad = iterator.next();
        receptor.getPropiedades().add(propiedad);
        iterator.remove();
    }

    receptor.setFortuna(receptor.getFortuna() + actual.getFortuna()); //pasamos el dinero del jugador que llama bancarrota al jugador que lo recibe

    actual.setFortuna(0);

    jugadores.remove(actual); //eliminamos al jugador del ArrayList de jugadores
    avatares.remove(actual.getAvatar()); //eliminamos el avatar del jugador del ArrayList de avatares

}


public void analizarMenuPequenho(Jugador actual, Jugador banca, Tablero tablero, Juego juego, Casilla c){
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


        
        String comando = consola.leer();

        switch (comando) {
            case "hipotecar":
                    hipotecar(actual, tablero);
                    acabado = true;
                    break;
            case "bancarrota":
                if(!c.getDuenho().equals(banca)){
                    bancarrotaAJugador(actual, c.getDuenho(), juego.getJugadores(), juego.getAvatares());
                    juego.setTirado(false); //para que el siguiente jugador pueda seguir tirando
                    juego.setLanzamientos(0);
                    juego.setDadosdobles(false);
                    System.out.println("Jugador eliminado con éxito. El siguiente jugador puede escoger ahora una opción.\n");
                    acabado = true;
                }else if(c.getDuenho().equals(banca)){
                    bancarrotaABanca(actual, c.getDuenho(), juego.getJugadores(), juego.getAvatares());
                    juego.setTirado(false); //para que el siguiente jugador pueda seguir tirando
                    juego.setLanzamientos(0);
                    juego.setDadosdobles(false);
                    System.out.println("Jugador eliminado con éxito. El siguiente jugador puede escoger ahora una opción.\n");
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