package partida;

import java.util.ArrayList;

import monopoly.*;

public class Jugador {

    // Atributos:
    private String nombre; // Nombre del jugador
    private Avatar avatar; // Avatar que tiene en la partida.
    private float fortuna; // Dinero que posee.
    private float gastos; // Gastos realizados a lo largo del juego.
    private boolean enCarcel; // Será true si el jugador está en la carcel
    private int tiradasCarcel; // Cuando está en la carcel, contará las tiradas sin éxito que ha hecho allí
    // para intentar salir (se usa para limitar el numero de intentos).
    private int vueltas; // Cuenta las vueltas dadas al tablero.
    private ArrayList<Casilla> propiedades; // Propiedades que posee el jugador.

    // Constructor vacío. Se usará para crear la banca.
    public Jugador() {
        this.fortuna = 100000; //Ponemos un valor elevado para que no se acabe el dinero de la banca
        this.avatar = null;
        this.nombre = "banca";
        this.gastos = 0;
        this.enCarcel = false;
        this.tiradasCarcel = 0;
        this.tiradasCarcel = 0;
        this.vueltas = 0;
    }

    /*
     * Constructor principal. Requiere parámetros:
     * Nombre del jugador, tipo del avatar que tendrá, casilla en la que empezará y
     * ArrayList de
     * avatares creados (usado para dos propósitos: evitar que dos jugadores tengan
     * el mismo nombre y
     * que dos avatares tengan mismo ID). Desde este constructor también se crea el
     * avatar.
     */

    public Jugador(String nombre, String tipoAvatar, Casilla inicio, ArrayList<Avatar> avCreados) {
        this.nombre = nombre;
        this.avatar = new Avatar(tipoAvatar, this, inicio, avCreados);
        this.fortuna = Valor.FORTUNA_INICIAL;
        this.gastos = 0;
        this.enCarcel = false;
        this.tiradasCarcel = 0;
        this.vueltas = 0;
        this.propiedades = new ArrayList<Casilla>();

    }

    // SETTERS Y GETTERS

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public float getFortuna() {
        return fortuna;
    }

    public void setFortuna(float fortuna) {
        this.fortuna = fortuna;
    }

    public float getGastos() {
        return gastos;
    }

    public void setGastos(float gastos) {
        this.gastos = gastos;
    }

    public boolean getEnCarcel() {
        return enCarcel;
    }

    public void setEnCarcel(boolean enCarcel) {
        this.enCarcel = enCarcel;
    }

    public int getTiradasCarcel() {
        return tiradasCarcel;
    }

    public void setTiradasCarcel(int tiradasCarcel) {
        this.tiradasCarcel = tiradasCarcel;
    }

    public int getVueltas() {
        return vueltas;
    }

    public void setVueltas(int vueltas) {
        this.vueltas = vueltas;
    }

    public ArrayList<Casilla> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(ArrayList<Casilla> propiedades) {
        this.propiedades = propiedades;
    }

    // Otros métodos:
    // Método para añadir una propiedad al jugador. Como parámetro, la casilla a
    // añadir.
    public void anhadirPropiedad(Casilla casilla) {
        propiedades.add(casilla);
    }

    // Método para eliminar una propiedad del arraylist de propiedades de jugador.
    public void eliminarPropiedad(Casilla casilla) {
        propiedades.remove(casilla);
    }

    // Método para añadir fortuna a un jugador
    // Como parámetro se pide el valor a añadir. Si hay que restar fortuna, se
    // pasaría un valor negativo.
    public void sumarFortuna(float valor) {
        this.fortuna += valor;
    }

    // Método para sumar gastos a un jugador.
    // Parámetro: valor a añadir a los gastos del jugador (será el precio de un
    // solar, impuestos pagados...).
    public void sumarGastos(float valor) {
        this.gastos += valor;
    }

    /*
     * Método para establecer al jugador en la cárcel.
     * Se requiere disponer de las casillas del tablero para ello (por eso se pasan
     * como parámetro).
     */
    public void encarcelar(ArrayList<ArrayList<Casilla>> tablero) {
        //Poner enCarcel como true.
        this.enCarcel = true;
        //Moverlo a la carcel
        Casilla carcel = tablero.get(1).get(0);
        this.avatar.setLugar(carcel);
        carcel.anhadirAvatar(this.getAvatar()); //añadimos el avatar al array de avatares en cárcel
        
    }

}
