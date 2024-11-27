package monopoly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import partida.Avatar;
import partida.Jugador;

public class Casilla {

    private String nombre; // nombre de la casilla
    private int posicion; // posición en el tablero
    private Jugador duenho; // dueño de la casilla
    private ArrayList<Avatar> avatares; // avatares en la casilla
    private ArrayList<ArrayList<Casilla>> tablero; // el tablero del juego
    private int VecesCaidasGrupal; // total de visitas
    private float dineroCasilla; // dinero acumulado en la casilla
    private float dineroParking; //dinero acumulado en la casilla parking

    public static ConsolaNormal consola = new ConsolaNormal();
    
    // Constructor de Casilla2
    public Casilla(String nombre, int posicion, Jugador duenho) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.duenho = duenho;
        this.avatares = new ArrayList<>();
        this.VecesCaidasGrupal = 0; // inicializa contador de visitas
        this.dineroCasilla = 0.0f; // dinero gastado en la casilla
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public ArrayList<Avatar> getAvatares() {
        return avatares;
    }
    // Método utilizado para añadir un avatar al array de avatares en casilla.
    public void anhadirAvatar(Avatar av) {
        this.avatares.add(av);
    }
    // Método utilizado para eliminar un avatar del array de avatares en casilla.
    public void eliminarAvatar(Avatar av) {
        this.avatares.remove(av);
    }
    public ArrayList<ArrayList<Casilla>> getTablero(){
        return tablero;
    }
    public void setTablero(ArrayList<ArrayList<Casilla>> tablero) {                     
        this.tablero= tablero;
    }
    public float getDineroCasilla(){
        return dineroCasilla;
    }
    public void setDineroCasilla(float dineroCasilla){
        this.dineroCasilla = dineroCasilla;
    }
    public float getDineroParking(){
        return dineroParking;
    }
    public void setDineroParking(float dineroParking){
        this.dineroParking = dineroParking;
    }
    public int getVecesCaidasGrupal(){
        return this.VecesCaidasGrupal;
    }

    public void sumarVecesCaidasGrupal(int VecesCaidasGrupal){
        this.VecesCaidasGrupal += VecesCaidasGrupal;
    }


    public boolean estaEnVenta(){
        if (((this instanceof Solar) || (this instanceof Servicio) || (this instanceof Transporte)) && (this.duenho.getNombre() == "banca")) {
            return true;
        } else {
            return false;
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
        String representacionCasilla;
        if ((this instanceof Solar) == true) {
            String color = ((Solar) this).generarSolar();
            representacionCasilla = String.format("%s%-10s%s%5s",color,this.getNombre(),Valor.RESET,sb);
        }else{
            representacionCasilla = String.format("%-10s%5s",this.getNombre(),sb);    
        }
        return representacionCasilla;
    }


    public void bancarrotaABanca(Jugador actual, Jugador banca, ArrayList<Jugador> jugadores, ArrayList<Avatar> avatares){ //falta mirar si las propiedades van a la banca o a un jugador determinado
        for (Casilla casilla : actual.getPropiedades()) { 
            if (casilla instanceof Solar) { 
                Solar solar = (Solar) casilla;
        
                // Limpias edificaciones
                if (!solar.getEdificacion().isEmpty()) {
                    solar.getEdificacion().clear();
                }
        
                // Deshipotecas y reasignas dueño
                solar.setHipotecado(false);
                solar.setDuenho(banca);
            }     

        banca.setFortuna(banca.getFortuna() + actual.getFortuna()); //pasamos toda la fortuna del jugador a la banca
        actual.setFortuna(0);

        jugadores.remove(actual);
        avatares.remove(actual.getAvatar());
        }
    }
    

    public void bancarrotaAJugador(Jugador actual, Jugador receptor, ArrayList<Jugador> jugadores, ArrayList<Avatar> avatares) {
        // Transferir propiedades al receptor
        for (Casilla casilla : actual.getPropiedades()) {
            if (casilla instanceof Solar) { // Verifica si es una casilla tipo Solar
                Solar solar = (Solar) casilla;
                if (!solar.getEdificacion().isEmpty()) {
                    solar.getEdificacion().clear(); // Limpia edificaciones
                }
                solar.setHipotecado(false); // Deshipoteca la propiedad
                solar.setDuenho(receptor); // Cambia el dueño de la propiedad
            }
        }
    
        // Transferir dinero del jugador actual al receptor
        receptor.setFortuna(receptor.getFortuna() + actual.getFortuna());
        actual.setFortuna(0); // Elimina la fortuna del jugador en bancarrota
    
        // Mover propiedades del jugador actual al receptor
        receptor.getPropiedades().addAll(actual.getPropiedades());
        actual.getPropiedades().clear(); // Limpia las propiedades del jugador actual
    
        // Eliminar al jugador y su avatar
        jugadores.remove(actual);
        avatares.remove(actual.getAvatar());
    }
    
    


    public void analizarMenuPequenho(Jugador actual, Jugador banca, Tablero tablero, Menu menu, Casilla c){
        boolean acabado = false;

        while(!acabado){
            consola.imprimir("=====================================\n");
            consola.imprimir("                MENÚ                \n");
            consola.imprimir("=====================================\n");
            consola.imprimir("1. Hipotecar una propiedad                            -> Comando: 'hipotecar'");
            consola.imprimir("2. Declararse en bancarrota                           -> Comando: 'bancarrota'");


            consola.imprimir("=====================================\n");
            consola.imprimir("Selecciona una opción para continuar.\n");
            consola.imprimir("=====================================\n\n");


            Scanner scanner = new Scanner(System.in);
            String comando = scanner.nextLine();
            
            switch (comando) {
                case "hipotecar":
                    Propiedad act = ((Propiedad) this);
                    act.Hacienda(actual, tablero);
                    acabado = true;
                    break;
                case "bancarrota":
                    if(!c.getDuenho().equals(banca)){
                        bancarrotaAJugador(actual, c.getDuenho(), menu.getJugadores(), menu.getAvatares());
                        menu.setTirado(false); //para que el siguiente jugador pueda seguir tirando
                        menu.setLanzamientos(0);
                        menu.setDadosdobles(false);
                        consola.imprimir("Jugador eliminado con éxito. El siguiente jugador puede escoger ahora una opción.\n");
                        acabado = true;
                    }else if(c.getDuenho().equals(banca)){
                        bancarrotaABanca(actual, c.getDuenho(), menu.getJugadores(), menu.getAvatares());
                        menu.setTirado(false); //para que el siguiente jugador pueda seguir tirando
                        menu.setLanzamientos(0);
                        menu.setDadosdobles(false);
                        consola.imprimir("Jugador eliminado con éxito. El siguiente jugador puede escoger ahora una opción.\n");
                        acabado = true;
                    }
                    break;
                default:
                    consola.imprimir("Error, comando desconocido.\n");
                    break;
                }
        }       
    }




    
    
}
