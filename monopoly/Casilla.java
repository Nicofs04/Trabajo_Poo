package monopoly;

import java.util.ArrayList;
import partida.Avatar;
import partida.Jugador;

public abstract class Casilla {

    private String nombre; // nombre de la casilla
    private int posicion; // posición en el tablero
    private Jugador duenho; // dueño de la casilla
    private ArrayList<Avatar> avatares; // avatares en la casilla
    private Tablero tablero; // el tablero del juego
    private int VecesCaidasGrupal; // total de visitas
    private float dineroCasilla; // dinero acumulado en la casilla
    private static float dineroParking; //dinero acumulado en la casilla parking

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
    public Tablero getTablero(){
        return tablero;
    }
    public void setTablero(Tablero tablero) {                     
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
    public void setDineroParking(float dinero){
        dineroParking = dinero;
    }
    public int getVecesCaidasGrupal(){
        return this.VecesCaidasGrupal;
    }

    public void sumarVecesCaidasGrupal(int VecesCaidasGrupal){
        this.VecesCaidasGrupal += VecesCaidasGrupal;
    }

    public abstract void evaluarCasilla(Tablero tablero, Jugador actual, Jugador banca, int tirada, Juego juego);

    public boolean estaEnVenta(){
        if ((this instanceof Propiedad) && (this.duenho.getNombre() == "banca")) {
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

    @Override
    public String toString() {
        if (this instanceof Solar) {
            Solar solar = (Solar) this;
            StringBuilder detalles = new StringBuilder();
            detalles.append("\nnombre: ").append(getNombre())
                    .append(",\n\ttipo: solar")
                    .append(",\n\tGrupo: ").append(solar.getGrupo().getColorGrupo())
                    .append(",\n\tPropietario: ").append(solar.getDuenho().getNombre())
                    .append(",\n\tvalor: ").append(solar.getValor())
                    .append(",\n\talquiler: ").append(solar.getImpuesto() + solar.sumarImpuestoedificios()) //alquiler acutal del solar (Caso de dueño del grupo)
                    .append(",\n\tvalor casa: ").append(solar.getValor() * 0.6f)
                    .append(",\n\tvalor hotel: ").append(solar.getValor() * 0.6f)
                    .append(",\n\tvalor piscina: ").append(solar.getValor() * 0.4f)
                    .append(",\n\tvalor pista de deportes: ").append(solar.getValor() * 1.25f)
                    .append(",\n\talquiler de una casa: ").append(solar.getImpuesto() * 5)
                    .append(",\n\talquiler dos casas: ").append(solar.getImpuesto() * 15)
                    .append(",\n\talquiler tres casas: ").append(solar.getImpuesto() * 35)
                    .append(",\n\talquiler cuatro casas: ").append(solar.getImpuesto() * 50)
                    .append(",\n\talquiler hotel (por casa hotel): ").append(solar.getImpuesto() * 70)
                    .append(",\n\talquiler piscina (por cada piscina): ").append(solar.getImpuesto() * 25)
                    .append(",\n\talquiler pista de deporte (por cada pista de deporte): ").append(solar.getImpuesto() * 25);
    
            if (!solar.getEdificacion().isEmpty()) {
                detalles.append(",\n\tEdificaciones: [");
                for (Edificacion edificacion : solar.getEdificacion()) {
                    detalles.append("\n\t\t{tipo: ").append(edificacion.getNombre())
                            .append(", id: ").append(edificacion.getId()).append("},");
                }
                detalles.deleteCharAt(detalles.length() - 1); // Eliminar la última coma
                detalles.append("\n\t]");
            } else {
                detalles.append(",\n\tEdificaciones: []");
            }
            return detalles.toString();
    
        } else if (this instanceof Transporte) {
            Transporte transporte = (Transporte) this;
            return "nombre: " + getNombre() + 
                   ",\n\ttipo: transporte" +
                   ",\n\tvalor: " + transporte.getValor() +
                   ",\n\tPropietario: " + transporte.getDuenho().getNombre() +
                   ",\n\tPosición: " + transporte.getPosicion() +
                   ",\n\tImpuesto (1 propiedad): " + transporte.getImpuesto();
    
        } else if (this instanceof Servicio) {
            Servicio servicio = (Servicio) this;
            return "nombre: " + getNombre() + 
                   ",\n\ttipo: servicio" +
                   ",\n\tvalor: " + servicio.getValor() +
                   ",\n\tPropietario: " + servicio.getDuenho().getNombre() +
                   ",\n\tPosición: " + servicio.getPosicion() +
                   ",\n\tImpuesto (factor de servicio): " + servicio.getFactor();
    
        } else if (this instanceof Impuesto) {
            Impuesto impuesto = (Impuesto) this;
            return "tipo: impuesto,\n\tImpuesto: " + impuesto.getImpuesto();
    
        } else if (this instanceof Especial) {
            StringBuilder jugadoresEnCasilla = new StringBuilder();
            for (int i = 0; i < this.avatares.size(); i++) {
                jugadoresEnCasilla.append(avatares.get(i).getJugador().getNombre()).append(", ");
            }
    
            // Eliminar la última coma y espacio
            if (jugadoresEnCasilla.length() > 0) {
                jugadoresEnCasilla.setLength(jugadoresEnCasilla.length() - 2);
            }
    
            if (this.getPosicion() == 20) { // PARKING
                return "bote: " + getDineroParking() + 
                       ",\n\tjugadores: [" + jugadoresEnCasilla + "]";
    
            } else if (this.getPosicion() == 10) { // CÁRCEL
                return "salir: " + valorCarcel(this.tablero) + 
                       ",\n\tjugadores: [" + jugadoresEnCasilla + "]";
    
            } else if (this.getPosicion() == 0) { // SALIDA
                return "nombre: " + getNombre() + 
                       ",\n\tjugadores: [" + jugadoresEnCasilla + "]";
            }
        }
    
        return "Tipo o posición desconocida\n";
    }
    


    public String infoCasilla() {
        String informacion = new String();
        informacion = this.toString();
        return informacion;
    }


    public float valorCarcel(Tablero tablero){
        float valorCarcel;
        valorCarcel = Valor.SUMA_VUELTA * (25.0f / 100.0f);
        return valorCarcel;
    }
    
}
