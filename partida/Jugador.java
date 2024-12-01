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
    private ArrayList<Propiedad> propiedades; // Propiedades que posee el jugador.
    private int vecesCarcel;
    private float dineroPagadoAlquileres;
    private float dineroCobradoAlquileres;
    private float dineroCobradoSalida;
    private float dineroTasasEImpuestos;
    private float dineroInversionesOBote;
    private float dineroInvertido;
    private int numVueltas;
    private int vecesDados;
    private ArrayList<Trato> tratosOfrecidos;
    private ArrayList<Trato> tratosRecibidos;


    // Constructor vacío. Se usará para crear la banca.
    public Jugador() {
        this.fortuna = 100000000; //Ponemos un valor elevado para que no se acabe el dinero de la banca
        this.avatar = null;
        this.nombre = "banca";
        this.gastos = 0;
        this.enCarcel = false;
        this.tiradasCarcel = 0;
        this.tiradasCarcel = 0;
        this.vueltas = 0;
        this.tratosOfrecidos = new ArrayList<Trato>();
        this.tratosRecibidos = new ArrayList<Trato>();
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

    public Jugador(String nombre, Casilla inicio, ArrayList<Avatar> avCreados, String tipo) {
        this.nombre = nombre;
        this.nombre = nombre;
        switch (tipo) {
            case "pelota":
                this.avatar = new Pelota(this, inicio, avCreados, tipo);                
                break;
            case "coche":
                this.avatar= new Coche(this, inicio, avCreados, tipo);
                break;
            case "sombrero":
                this.avatar= new Sombrero(this,inicio, avCreados, tipo);
                break;
            case "esfinge":
                this.avatar= new Esfinge(this, inicio, avCreados, tipo);
                break;
        
            default:
                break;
        }
        this.fortuna = Valor.FORTUNA_INICIAL;
        this.gastos = 0;
        this.enCarcel = false;
        this.tiradasCarcel = 0;
        this.vueltas = 0;
        this.propiedades = new ArrayList<Propiedad>();
        this.tratosOfrecidos = new ArrayList<Trato>();
        this.tratosRecibidos = new ArrayList<Trato>();

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

    public ArrayList<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(ArrayList<Propiedad> propiedades) {
        this.propiedades = propiedades;
    }

    public int getVecesCarcel(){
        return vecesCarcel;
    }

    public void setVecesCarcel(int vecesCarcel){
        this.vecesCarcel = vecesCarcel;
    }

    public float getDineroPagadoAlquileres(){
        return dineroPagadoAlquileres;
    }

    public void setDineroPagadoAlquileres(float dineroPagadoAlquileres){
        this.dineroPagadoAlquileres = dineroPagadoAlquileres;
    }

    public float getDineroCobradoAlquileres(){
        return dineroCobradoAlquileres;
    }

    public void setDineroCobradoAlquileres(float dineroCobradoAlquileres){
        this.dineroCobradoAlquileres = dineroCobradoAlquileres;
    }

    public float getDineroCobradoSalida(){
        return dineroCobradoSalida;
    }

    public void setDineroCobradoSalida(float dineroCobradoSalida){
        this.dineroCobradoSalida = dineroCobradoSalida;
    }

    public float getDineroTasasEImpuestos(){
        return dineroTasasEImpuestos;
    }

    public void setDineroTasasEImpuestos(float dineroTasasEImpuestos){
        this.dineroTasasEImpuestos = dineroTasasEImpuestos;
    }

    public float getDineroInversionesOBote(){
        return dineroInversionesOBote;
    }

    public void setDineroInversionesOBote(float dineroInversionesOBote){
        this.dineroInversionesOBote = dineroInversionesOBote;
    }

    public float getDineroInvertido(){
        return this.dineroInvertido;
    }

    public void setDineroInvertido(float dineroInvertido){
        this.dineroInvertido = dineroInvertido;
    }

    public int getNumVueltas(){
        return numVueltas;
    }

    public void setNumVueltas(int numVueltas){
        this.numVueltas = numVueltas;
    }

    public int getVecesDados(){
        return vecesDados;
    }

    public void setVecesDados(int vecesDados){
        this.vecesDados = vecesDados;
    }

    public ArrayList<Trato> getTratosRecibidos(){
        return tratosRecibidos;
    }

    public void setTratosRecibidos(ArrayList<Trato> tratosRecibidos){
        this.tratosRecibidos = tratosRecibidos;
    }

    public ArrayList<Trato> getTratosOfrecidos(){
        return tratosOfrecidos;
    }

    public void setTratosOfrecidos(ArrayList<Trato> tratosOfrecidos){
        this.tratosOfrecidos = tratosOfrecidos;
    }
    
    // Otros métodos:
    // Método para añadir una propiedad al jugador. Como parámetro, la casilla a
    // añadir.
    public void anhadirPropiedad(Propiedad propiedad) {
        propiedades.add(propiedad);
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
        //Lo sacamos de la casilla "ircarcel"
        Casilla casilla_vieja = this.getAvatar().getLugar();
        casilla_vieja.getAvatares().remove(this.avatar);
        //Moverlo a la carcel
        Casilla carcel = tablero.get(1).get(0);
        this.avatar.setLugar(carcel);
        carcel.anhadirAvatar(this.avatar); //añadimos el avatar al array de avatares en cárcel
        this.setVecesCarcel(this.getVecesCarcel() + 1);
    }

}