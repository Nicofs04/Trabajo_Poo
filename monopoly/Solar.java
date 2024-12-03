package monopoly;

import java.util.ArrayList;
import java.util.Iterator;

import partida.Jugador;
import monopoly.Excepciones.*;



public class Solar extends Propiedad{

    private ArrayList<Edificacion> edificaciones;
    private ArrayList<Integer> Vecescaidas; // estadísticas de visitas
    private Grupo grupo; // grupo de propiedades al que pertenece

   public Solar(String nombre, int posicion, float valor, Jugador duenho) {
        super(nombre, posicion,valor,duenho); // llama al constructor de Propiedades
        this.edificaciones = new ArrayList<>();
        this.Vecescaidas = new ArrayList<>(); //array de veces caids por un jugador
        inicializarVeces(Vecescaidas);
        
    }   

    
    public Grupo getGrupo() { 
        return grupo;
    }
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public void incrementarVeces(int jugadorIndex) {
        int caidasActuales = Vecescaidas.get(jugadorIndex);
        Vecescaidas.set(jugadorIndex, caidasActuales + 1);
    }
    
    // Método para obtener el número de caídas de un jugador específico
    public int getVeces(int jugadorIndex) {
        return Vecescaidas.get(jugadorIndex);
    }

    public void inicializarVeces(ArrayList<Integer> Vecescaidas) {
        if (Vecescaidas.isEmpty()) {
            for(int i = 0; i<6 ; i++){
                Vecescaidas.add(0);
            }
        }
    }

    public void anhadirEdificacion(Edificacion edificacion){
        this.edificaciones.add(edificacion);
    }
    public ArrayList<Edificacion> getEdificacion(){
        return this.edificaciones;
    }


    public void eliminarEdificacion(String nombre){
        for(Edificacion edificacion: getEdificacion()){
            switch (nombre) {
                case "casa":
                    if (edificacion instanceof Casa)
                        edificaciones.remove(edificacion);
                    break;
                case "hotel":
                    if (edificacion instanceof Hotel)
                    edificaciones.remove(edificacion);
                    break;
                case "pisicna":
                    if (edificacion instanceof Hotel)
                        edificaciones.remove(edificacion);
                    break;
                case "pistadeporte":
                    if (edificacion instanceof Hotel)
                    edificaciones.remove(edificacion);                
                    break;
                default:
                    break;
            }
            break;
            }
        }
    


    public void evaluarCasilla(Tablero tablero, Jugador actual, Jugador banca, int tirada, Juego juego) {

        Solar c = this;
        c.sumarVecesCaidasGrupal(1);

        int jugadorIndex = juego.getTurno(); // Asumiendo que cada jugador tiene un método para obtener su índice único
        incrementarVeces(jugadorIndex);            
        
        if(!getHipotecado()){ //verificamos que la casilla no este hipotecada
            if (!getDuenho().equals(actual) && !getDuenho().equals(banca) ) {
                
                if (actual.getFortuna() < devolverImpuesto(c,getDuenho())+sumarImpuestoedificios()) {
                    
                    consola.imprimir("El jugador no tiene dinero suficiente para pagar el alquiler, por lo que debe declararse en bancarrota o hipotecar alguna propiedad");
                    
                    juego.analizarMenuPequenho(actual, banca, tablero, juego, c); //analizamos el comando escrito
                    
                    while(actual.getFortuna() < devolverImpuesto(c, getDuenho())+sumarImpuestoedificios()){
                        consola.imprimir(String.format("El jugador %s no tiene dinero suficiente, elija una opción:\n", actual.getNombre()));
                        if(actual.getPropiedades().isEmpty()){ //nos aseguramos de que pueda seguir teniendo propiedades para hipotecar
                            juego. bancarrotaAJugador(actual, getDuenho(), juego.getJugadores(), juego.getAvatares()); //si no llamamos a bancarrota
                            break;
                        }
                        juego.analizarMenuPequenho(actual, banca, tablero, juego, c);
                    }

                    if (actual.getFortuna() < devolverImpuesto(c, c.getDuenho())+sumarImpuestoedificios()) {
                        //return false;
                    }else{
                        actual.setFortuna(actual.getFortuna()-(devolverImpuesto(c, c.getDuenho())+sumarImpuestoedificios())); //le restamos el alquiler pagado
                        actual.setDineroPagadoAlquileres(actual.getDineroPagadoAlquileres() + (devolverImpuesto(c, c.getDuenho())+sumarImpuestoedificios())); //sumamos el dinero pagado al atributo dineroPagado del jugador que paga

                        getDuenho().setFortuna(getDuenho().getFortuna() + (devolverImpuesto(c, c.getDuenho())+sumarImpuestoedificios())); //le sumamos el alquiler al dueño de la casilla
                        getDuenho().setDineroCobradoAlquileres(getDuenho().getDineroCobradoAlquileres() + (devolverImpuesto(c, c.getDuenho())+sumarImpuestoedificios())); //sumamos el dinero cobrado al atributo dineroCobrado del jugador que cobra

                        this.setDineroCasilla(this.getDineroCasilla() + (devolverImpuesto(c, c.getDuenho())+sumarImpuestoedificios())); //le sumamos lo que se paga al atributo que nos indica el dinero total que gana el dueño de la casilla

                        consola.imprimir("El jugador paga "+(devolverImpuesto(c, c.getDuenho())+sumarImpuestoedificios()) +"€");
                        //return true;
                    }
                    //Acabaría la partida para este jugador
                }else{
                        actual.setFortuna(actual.getFortuna()-(devolverImpuesto(c, c.getDuenho())+sumarImpuestoedificios())); //le restamos el alquiler pagado
                        actual.setDineroPagadoAlquileres(actual.getDineroPagadoAlquileres() + (devolverImpuesto(c, c.getDuenho())+sumarImpuestoedificios())); //sumamos el dinero pagado al atributo dineroPagado del jugador que paga

                        getDuenho().setFortuna(getDuenho().getFortuna() + (devolverImpuesto(c, c.getDuenho())+sumarImpuestoedificios())); //le sumamos el alquiler al dueño de la casilla
                        getDuenho().setDineroCobradoAlquileres(getDuenho().getDineroCobradoAlquileres() + (devolverImpuesto(c, c.getDuenho())+sumarImpuestoedificios())); //sumamos el dinero cobrado al atributo dineroCobrado del jugador que cobra

                        this.setDineroCasilla(this.getDineroCasilla() + (devolverImpuesto(c, c.getDuenho())+sumarImpuestoedificios())); //le sumamos lo que se paga al atributo que nos indica el dinero total que gana el dueño de la casilla

                        consola.imprimir("El jugador paga "+(devolverImpuesto(c, c.getDuenho())+sumarImpuestoedificios()) +"€");
                        //return true;
                }      
            }
        }


    }

    public float devolverImpuesto(Solar solar, Jugador jugador){
        Grupo grupo = getGrupo();

        if (grupo.esDuenhoGrupo(jugador)) {
            return (getImpuesto()*2);
        }else{
            return (getImpuesto());
        }
    }

    public float sumarImpuestoedificios(){
        float suma=0;
        int casas=0,hotel=0,piscina=0,pista=0;
        casas = contarCasas();
        hotel=contarHoteles();
        piscina=contarPiscinas();
        pista=contarPistas();
    
        if (casas==1) {
            suma += getImpuesto()*5;
        }
        else if (casas==2) {
            suma += getImpuesto()*15;
        }
        else if (casas==3) {
            suma += getImpuesto()*35;
        }
        else if (casas==4) {
            suma += getImpuesto()*50;
        }
        if (hotel >= 1) {
            suma += getImpuesto() * 70 * hotel;
        }
        if (piscina >= 1) {
            suma += getImpuesto() * 25 * piscina;
        }
        if (pista >= 1) {
            suma += getImpuesto() * 25 * pista;
        }   
        return suma;
        }


    public String generarSolar(){
        String color = "";     
            switch (this.getGrupo().getColorGrupo()) {
                case "RED":
                    color = Valor.RED;
                    break;
                case "GREEN":
                    color =  Valor.GREEN;
                    break;
                case "YELLOW":
                    color = Valor.YELLOW;
                    break;
                case "BLUE":
                    color = Valor.BLUE; 
                    break;
                case "PURPLE":
                    color = Valor.PURPLE;
                    break;
                case "CYAN":
                    color = Valor.CYAN;
                    break;
                case "BLACK":
                    color = Valor.BLACK;
                    break;
                case "WHITE":
                    color = Valor.WHITE;
                    break;
                default:
                    color = "RESET";
                    break;
                }
        return color;
    }




    public void cambiarcasas(){
        int casa = contarCasas();
        if (casa == 4) {
            Iterator<Edificacion> iterator = edificaciones.iterator();
            while (iterator.hasNext()) {
                Edificacion edificacion = iterator.next();
                if (edificacion instanceof Casa) {
                    iterator.remove();
                }
            }
        }
    }
    

public int contarCasas(){
        int contador=0;
        for (Edificacion edificacion: edificaciones){
            if(edificacion instanceof Casa){
                contador++;
            }
        }
        return contador;
    }


public int contarHoteles(){
    int contador=0;
    for (Edificacion edificacion: edificaciones){
        if(edificacion instanceof Hotel){
            contador++;
        }
    }
    return contador;
}

public int contarPiscinas(){
    int contador=0;
    for (Edificacion edificacion: edificaciones){
        if(edificacion instanceof Piscina){
            contador++;
        }
    }
    return contador;
}

public int contarPistas(){
    int contador=0;
    for (Edificacion edificacion: edificaciones){
        if(edificacion instanceof PistaDeporte){
            contador++;
        }
    }
    return contador;
}




public void edificarCasa(Jugador jugador, int index) throws Excepciones_PropConstruir{
    Edificacion casa = new Casa(this);
    

    int limiteGrupo = this.getGrupo().getNumCasillas();
    int numCasas = this.contarCasas();
    int hotelgrupo = this.getGrupo().contarHotelesGrupo();
    int casasGrupo = this.getGrupo().contarCasasGrupo();


    if (!getHipotecado()) {    
        if (!this.getGrupo().esDuenhoGrupo(jugador) && !(getVeces(index)>2)) {
            throw new Excepciones_PropConstruir("No cumples los requisitos, has de ser dueño de todo el grupo o haber caido al menos tres veces en la casilla para edificar, aparte, la casilla no puede estar hipotecada");
        }
    }else{
        throw new Excepciones_PropConstruir("No puedes construir en un solar hipotecado");
    }
    
        if (numCasas < 4) {
            if (hotelgrupo==limiteGrupo) {
                if (casasGrupo<limiteGrupo) {
                    if (!(jugador.getFortuna()< (getValor()*0.6f))) {
                        anhadirEdificacion(casa);
                        jugador.setFortuna(jugador.getFortuna()-(getValor()*0.6f));
                        jugador.setDineroInvertido(jugador.getDineroInvertido() + (getValor()*0.6f)); //sumamos el valor de la casilla al atributo dineroInvertido del comprador


                        consola.imprimir("Se han pagado"+ getValor()*0.6f + "por la construcción de una casa. La fortuna restante es de "+jugador.getFortuna());
                        consola.imprimir("Se ha construido una casa correctamente en la casilla " + getNombre() + ". Hay " + (numCasas+1) + " casas construidas.");
                    }else{
                        consola.imprimir("No dispones del dinero necesario para construir la edificación");
                        throw new Excepciones_PropConstruir("No dispones del dinero necesario para construir la edificación");
                    }
                }else{
                    throw new Excepciones_PropConstruir("Se alcanzó el límite máximo de casas a construir");
                }
            }else{
                if (!(jugador.getFortuna()< (getValor()*0.6f))) {
                    anhadirEdificacion(casa);
                    jugador.setFortuna(jugador.getFortuna()-(getValor()*0.6f));
                    jugador.setDineroInvertido(jugador.getDineroInvertido() + (getValor()*0.6f)); //sumamos el valor de la casilla al atributo dineroInvertido del comprador
                    

                    consola.imprimir("Se han pagado"+ getValor()*0.6f + "por la construcción de una casa. La fortuna restante es de "+jugador.getFortuna());
                    consola.imprimir("Se ha construido una casa correctamente en la casilla " + getNombre() + ". Hay " + (numCasas+1) + " casas construidas.");
                }else{
                    consola.imprimir("No dispones del dinero necesario para construir la edificación");
                    throw new Excepciones_PropConstruir("Se alcanzó el límite máximo de casas a construir");
                }
            }
        }else{
            throw new Excepciones_PropConstruir("Se alcanzó el límite máximo de casas a construir");
        }    
        
    }


    public void edificarHotel(Jugador jugador, int index) throws Excepciones_PropConstruir {
        Hotel hotel = new Hotel(this);
        int limiteGrupo = getGrupo().getNumCasillas();
        int numCasas = contarCasas();
        int hotelgrupo = getGrupo().contarHotelesGrupo();
        int contarHoteles = contarHoteles();



        if (!getHipotecado()) {    
            if (!this.getGrupo().esDuenhoGrupo(jugador) && !(getVeces(index)>2)) {
                throw new Excepciones_PropConstruir("No cumples los requisitos, has de ser dueño de todo el grupo o haber caido al menos tres veces en la casilla para edificar, aparte, la casilla no puede estar hipotecada");
            }
        }else{
            throw new Excepciones_PropConstruir("No puedes construir en un solar hipotecado");
        }
        
        
            if (hotelgrupo < limiteGrupo) {
                if (numCasas == 4) {
                    if (jugador.getFortuna() >= (getValor() * 0.6f)) {
                        anhadirEdificacion(hotel);
                        jugador.setFortuna(jugador.getFortuna() - (getValor() * 0.6f));
                        jugador.setDineroInvertido(jugador.getDineroInvertido() + (getValor()*0.6f)); //sumamos el valor de la casilla al atributo dineroInvertido del comprador


                        consola.imprimir("Se han pagado " + getValor() * 0.6f + " por la construcción de un hotel. La fortuna restante es de " + jugador.getFortuna() + "\n");
                        consola.imprimir("Se ha construido el hotel y se han quitado las 4 casas\n");
                        consola.imprimir("Se ha construido un hotel correctamente en la casilla " + getNombre() + ". Hay " + (contarHoteles + 1) + " hoteles construidos.\n");
                        cambiarcasas();
                    } else {
                        throw new Excepciones_PropConstruir("No dispones del dinero necesario para construir la edificación");
                    }
                } else {
                    throw new Excepciones_PropConstruir("No hay 4 casas en la casilla como para construir el hotel");
                }
            } else {
                throw new Excepciones_PropConstruir("Has alcanzado el límite máximo de hoteles en el grupo");
                
            }
        
    }
    
    public void edificarPiscina(Jugador jugador, int index) throws Excepciones_PropConstruir{
        Piscina piscina = new Piscina(this);
        int limiteGrupo = getGrupo().getNumCasillas();
        int numCasas = contarCasas();
        int hotel = contarHoteles();
        int piscinagrupo = getGrupo().contarPiscinasGrupo();
        int contarpiscinas = contarPiscinas();

        if (!getHipotecado()) {    
            if (!this.getGrupo().esDuenhoGrupo(jugador) && !(getVeces(index)>2)) {
                throw new Excepciones_PropConstruir("No cumples los requisitos, has de ser dueño de todo el grupo o haber caido al menos tres veces en la casilla para edificar, aparte, la casilla no puede estar hipotecada");
            }
        }else{
            throw new Excepciones_PropConstruir("No puedes construir en un solar hipotecado");
        }
    
        if (piscinagrupo < limiteGrupo) {
            if ((hotel >= 1 && numCasas > 1) || (hotel >= 2)) {
                if (jugador.getFortuna() >= (getValor() * 0.4f)) {
                    anhadirEdificacion(piscina);
                    jugador.setFortuna(jugador.getFortuna() - (getValor() * 0.4f));
                    jugador.setDineroInvertido(jugador.getDineroInvertido() + (getValor()*0.4f)); //sumamos el valor de la casilla al atributo dineroInvertido del comprador

                    consola.imprimir("Se han pagado " + getValor() * 0.4f + " por la construcción de una piscina. La fortuna restante es de " + jugador.getFortuna());
                    contarpiscinas = contarPiscinas();
                    consola.imprimir("Se ha construido una piscina correctamente en la casilla " + getNombre() + ". Hay " + contarpiscinas + " piscinas construidas.");
                } else {
                    throw new Excepciones_PropConstruir("No dispones del dinero necesario para construir la edificación");
                }
            } else {
                throw new Excepciones_PropConstruir("Para constuir una piscina necesitas 1 hotel y 2 casas o más de 2 hoteles");
            }
        } else {
            throw new Excepciones_PropConstruir("Has alcanzado el máximo de piscinas en el grupo");
        }
    }
    
    public void edificarPista(Jugador jugador, int index) throws Excepciones_PropConstruir {
        Edificacion pista = new PistaDeporte(this);
        int limiteGrupo = getGrupo().getNumCasillas();
        int hotel = contarHoteles();
        int pistasgrupo = getGrupo().contarPistasGrupo();
        int contarpistas = contarPistas();

        if (!getHipotecado()) {    
            if (!this.getGrupo().esDuenhoGrupo(jugador) && !(getVeces(index)>2)) {
                throw new Excepciones_PropConstruir("No cumples los requisitos, has de ser dueño de todo el grupo o haber caido al menos tres veces en la casilla para edificar, aparte, la casilla no puede estar hipotecada");
            }
        }else{
            throw new Excepciones_PropConstruir("No puedes construir en un solar hipotecado");
        }
    
        if (pistasgrupo < limiteGrupo) {
            if (hotel >= 2) {
                if (jugador.getFortuna() >= (getValor() * 1.25f)) {
                    anhadirEdificacion(pista);
                    jugador.setFortuna(jugador.getFortuna() - (getValor() * 1.25f));
                    jugador.setDineroInvertido(jugador.getDineroInvertido() + (getValor()*1.25f)); //sumamos el valor de la casilla al atributo dineroInvertido del comprador


                    consola.imprimir("Se han pagado " + getValor() * 1.25f + " por la construcción de una pista. La fortuna restante es de " + jugador.getFortuna());
                    consola.imprimir("Se ha construido una pista correctamente en la casilla " + getNombre() + ". Hay " + (contarpistas + 1) + " pistas construidas.");
                } else {
                    throw new Excepciones_PropConstruir("No dispones del dinero necesario para construir la edificación");
                }
            } else {
                throw new Excepciones_PropConstruir("Necesitas al menos 2 hoteles para construir una pista de deportes");
            }
        } else {
            consola.imprimir("Has alcanzado el máximo de pistas de deporte en el grupo");
            throw new Excepciones_PropConstruir("Has alcanzado el máximo de pistas de deporte en el grupo");
        }
    }

    public void venderCasa(Solar solar, int numvender, Jugador jugador) throws Excepciones_PropVenderEdif {

        if (solar == null || !(solar.getDuenho().equals(jugador))) {

            throw new Excepciones_PropVenderEdif("La casilla especificada no existe o no pertenece a este jugador");
            
        }
        int numCasas = solar.contarCasas();
        if (numCasas < numvender)
        {
            throw new Excepciones_PropVenderEdif("No puedes vender más casas de las que hay construidas");
        }
        
        for(int i = 0; i<numvender; i++){
        if (numCasas > 0) {
            float valorVenta = solar.getValor() * 0.6f / 2;
            solar.eliminarEdificacion("casa");
            jugador.setFortuna(jugador.getFortuna() + valorVenta);
            System.out.println("Se ha vendido una casa por " + valorVenta + ". La fortuna actual es de " + jugador.getFortuna());
            numCasas = solar.contarCasas();
            System.out.println("Se ha eliminado una casa en la casilla " + solar.getNombre() + ". Quedan " + (numCasas) + " casas construidas.");
        } else {
            throw new Excepciones_PropVenderEdif("No hay casas para vender en esta casilla");
            }
        }
    }
    
    public void venderHotel(Solar solar, int numVender, Jugador jugador) throws Excepciones_PropVenderEdif{

        if (solar == null || !(solar.getDuenho().equals(jugador))) {
            throw new Excepciones_PropVenderEdif("La casilla especificada no existe o no pertenece a este jugador");
        }
        int numHoteles = solar.contarHoteles();
        if (numHoteles < numVender) {
            throw new Excepciones_PropVenderEdif("No puedes vender más hoteles de los que hay construidos");

        }
    
        for (int i = 0; i < numVender; i++) {
            if (numHoteles > 0) {
                float valorVenta = solar.getValor() * 0.6f;
                solar.eliminarEdificacion("hotel");
                jugador.setFortuna(jugador.getFortuna() + valorVenta);
                System.out.println("Se ha vendido un hotel por " + valorVenta + ". La fortuna actual es de " + jugador.getFortuna());
                numHoteles = solar.contarHoteles();
                System.out.println("Se ha eliminado un hotel en la casilla " + solar.getNombre() + ". Quedan " + numHoteles + " hoteles construidos.");
            } else {
                throw new Excepciones_PropVenderEdif("No hay hoteles para vender en esta casilla");
            }
        }
    }
    
    public void venderPiscina(Solar solar, int numVender, Jugador jugador) throws Excepciones_PropVenderEdif {
    
        if (solar == null || !(solar.getDuenho().equals(jugador))) {
            throw new Excepciones_PropVenderEdif("La casilla especificada no existe o no pertenece a este jugador");

        }
        int numPiscinas = solar.contarPiscinas();
        if (numPiscinas < numVender) {
            throw new Excepciones_PropVenderEdif("No puedes vender más piscinas de las que hay construidas");

        }
    
        for (int i = 0; i < numVender; i++) {
            if (numPiscinas > 0) {
                float valorVenta = solar.getValor() * 0.8f / 2;
                solar.eliminarEdificacion("piscina");
                jugador.setFortuna(jugador.getFortuna() + valorVenta);
                System.out.println("Se ha vendido una piscina por " + valorVenta + ". La fortuna actual es de " + jugador.getFortuna());
                numPiscinas = solar.contarPiscinas();
                System.out.println("Se ha eliminado una piscina en la casilla " + solar.getNombre() + ". Quedan " + numPiscinas + " piscinas construidas.");
            } else {
                throw new Excepciones_PropVenderEdif("No hay piscinas para vender en esta casilla");

            }
        }
    }
    
    public void venderPista(Solar solar, int numVender, Jugador jugador) throws Excepciones_PropVenderEdif {
    
        if (solar == null || !(solar.getDuenho().equals(jugador))) {
            throw new Excepciones_PropVenderEdif("La casilla especificada no existe o no pertenece a este jugador");

        }
        int numPistas = solar.contarPistas();
        if (numPistas < numVender) {
            throw new Excepciones_PropVenderEdif("No puedes vender más pistas de las que hay construidas");

        }
    
        for (int i = 0; i < numVender; i++) {
            if (numPistas > 0) {
                float valorVenta = solar.getValor() * 0.7f / 2;
                solar.eliminarEdificacion("pista");
                jugador.setFortuna(jugador.getFortuna() + valorVenta);
                System.out.println("Se ha vendido una pista por " + valorVenta + ". La fortuna actual es de " + jugador.getFortuna());
                numPistas = solar.contarPistas();
                System.out.println("Se ha eliminado una pista en la casilla " + solar.getNombre() + ". Quedan " + numPistas + " pistas construidas.");
            } else {
                throw new Excepciones_PropVenderEdif("No hay pistas para vender en esta casilla");

            }
        }
    }
}    