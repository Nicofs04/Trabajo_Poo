package monopoly;

import java.util.ArrayList;
import java.util.Iterator;

import partida.Jugador;



public class Solar extends Propiedad{

    private ArrayList<Edificacion> edificaciones;
    private ArrayList<Integer> Vecescaidas; // estadísticas de visitas
    private Grupo grupo; // grupo de propiedades al que pertenece

   public Solar(String nombre, int posicion, float valor, Jugador duenho) {
        super(nombre, posicion,valor,duenho); // llama al constructor de Propiedades
        this.Vecescaidas = new ArrayList<>(); //array de veces caids por un jugador
        
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


    public void evaluarCasilla(Tablero tablero, Jugador actual, Jugador banca, int tirada, Juego juego) {

        Solar c = this;
        c.sumarVecesCaidasGrupal(1);

        int jugadorIndex = juego.getTurno(); // Asumiendo que cada jugador tiene un método para obtener su índice único
        incrementarVeces(jugadorIndex);            
        
        if(!getHipotecado()){ //verificamos que la casilla no este hipotecada
            if (!getDuenho().equals(actual) && !getDuenho().equals(banca) ) {
                
                if (actual.getFortuna() < devolverImpuesto(c,getDuenho())/*+sumarImpuestoedificios()*/) {
                    
                    consola.imprimir("El jugador no tiene dinero suficiente para pagar el alquiler, por lo que debe declararse en bancarrota o hipotecar alguna propiedad");
                    
                    analizarMenuPequenho(actual, banca, tablero, juego, c); //analizamos el comando escrito
                    
                    while(actual.getFortuna() < devolverImpuesto(c, getDuenho())/*+sumarImpuestoedificios()*/){
                        consola.imprimir(String.format("El jugador %s no tiene dinero suficiente, elija una opción:\n", actual.getNombre()));
                        if(actual.getPropiedades().isEmpty()){ //nos aseguramos de que pueda seguir teniendo propiedades para hipotecar
                            bancarrotaAJugador(actual, getDuenho(), juego.getJugadores(), juego.getAvatares()); //si no llamamos a bancarrota
                            break;
                        }
                        analizarMenuPequenho(actual, banca, tablero, juego, c);
                    }

                    if (actual.getFortuna() < devolverImpuesto(c, c.getDuenho())/*+sumarImpuestoedificios()*/) {
                        //return false;
                    }else{
                        actual.setFortuna(actual.getFortuna()-(devolverImpuesto(c, c.getDuenho())/*+sumarImpuestoedificios()*/)); //le restamos el alquiler pagado
                        actual.setDineroPagadoAlquileres(actual.getDineroPagadoAlquileres() + (devolverImpuesto(c, c.getDuenho())/*+sumarImpuestoedificios()*/)); //sumamos el dinero pagado al atributo dineroPagado del jugador que paga

                        getDuenho().setFortuna(getDuenho().getFortuna() + (devolverImpuesto(c, c.getDuenho())/*+sumarImpuestoedificios()*/)); //le sumamos el alquiler al dueño de la casilla
                        getDuenho().setDineroCobradoAlquileres(getDuenho().getDineroCobradoAlquileres() + (devolverImpuesto(c, c.getDuenho())/*+sumarImpuestoedificios()*/)); //sumamos el dinero cobrado al atributo dineroCobrado del jugador que cobra

                        this.setDineroCasilla(this.getDineroCasilla() + (devolverImpuesto(c, c.getDuenho())/*+sumarImpuestoedificios()*/)); //le sumamos lo que se paga al atributo que nos indica el dinero total que gana el dueño de la casilla

                        consola.imprimir("El jugador paga "+(devolverImpuesto(c, c.getDuenho())/*+sumarImpuestoedificios()*/) +"€");
                        //return true;
                    }
                    //Acabaría la partida para este jugador
                }else{
                        actual.setFortuna(actual.getFortuna()-(devolverImpuesto(c, c.getDuenho())/*+sumarImpuestoedificios()*/)); //le restamos el alquiler pagado
                        actual.setDineroPagadoAlquileres(actual.getDineroPagadoAlquileres() + (devolverImpuesto(c, c.getDuenho())/*+sumarImpuestoedificios()*/)); //sumamos el dinero pagado al atributo dineroPagado del jugador que paga

                        getDuenho().setFortuna(getDuenho().getFortuna() + (devolverImpuesto(c, c.getDuenho())/*+sumarImpuestoedificios()*/)); //le sumamos el alquiler al dueño de la casilla
                        getDuenho().setDineroCobradoAlquileres(getDuenho().getDineroCobradoAlquileres() + (devolverImpuesto(c, c.getDuenho())/*+sumarImpuestoedificios()*/)); //sumamos el dinero cobrado al atributo dineroCobrado del jugador que cobra

                        this.setDineroCasilla(this.getDineroCasilla() + (devolverImpuesto(c, c.getDuenho())/*+sumarImpuestoedificios()*/)); //le sumamos lo que se paga al atributo que nos indica el dinero total que gana el dueño de la casilla

                        consola.imprimir("El jugador paga "+(devolverImpuesto(c, c.getDuenho())/*+sumarImpuestoedificios()*/) +"€");
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
        if(edificacion instanceof Pista){
            contador++;
        }
    }
    return contador;
}


//a edificar se podría llamar con Jugador jugador, Solar solar



public void edificar(Jugador jugador, String tipo){

    switch (tipo) {
        case "casa":
            edificarCasa();
            break;
        case "hotel":
            edificarHotel();
            break;
        case "piscina":
            edificarPiscina();
            break;
        case "pista":
            edificarPista();
            break;
        default:
            break;
    }

}



private boolean comprobar(){

    if (!solar.getHipotecado()) {    
        if (actual.getGrupo().esDuenhoGrupo(jugadores.get(turno)) || actual.getVeces(turno)>2) {
            return true;   
        }else{
            consola.imprimir("No cumples los requisitos, has de ser dueño de todo el grupo o haber caido al menos tres veces en la casilla para edificar, aparte, la casilla no puede estar hipotecada");
        return false;
        }
    }
    consola.imprimir("No puedes construir en un solar hipotecado");
    return false;
}

public void edificarCasa() {
    Edificacion casa = new Casa(this);
    
    if (!(actual.getTipo()== "solar")) {
        return;
    }
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


                        consola.imprimir("Se han pagado"+ actual.getValor()*0.6f + "por la construcción de una casa. La fortuna restante es de "+jugadores.get(turno).getFortuna());
                        consola.imprimir("Se ha construido una casa correctamente en la casilla " + actual.getNombre() + ". Hay " + (numCasas+1) + " casas construidas.");
                    }else{
                        consola.imprimir("No dispones del dinero necesario para construir la edificación");
                    }
                }else{
                    consola.imprimir("Se alcanzó el límite máximo de casas a construir en el grupo");
                }
            }else{
                if (!(jugadores.get(turno).getFortuna()< (actual.getValor()*0.6f))) {
                    actual.anhadirEdificacion(casa);
                    jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna()-(actual.getValor()*0.6f));
                    jugadores.get(turno).setDineroInvertido(jugadores.get(turno).getDineroInvertido() + (actual.getValor()*0.6f)); //sumamos el valor de la casilla al atributo dineroInvertido del comprador
                    

                    consola.imprimir("Se han pagado"+ actual.getValor()*0.6f + "por la construcción de una casa. La fortuna restante es de "+jugadores.get(turno).getFortuna());
                    consola.imprimir("Se ha construido una casa correctamente en la casilla " + actual.getNombre() + ". Hay " + (numCasas+1) + " casas construidas.");
                }else{
                    consola.imprimir("No dispones del dinero necesario para construir la edificación");
                }
            }
        }else{
            consola.imprimir("Se alcanzó el límite máximo de casas a construir");
        }    
        }
    }


    public void edificarHotel() {
        Edificacion hotel = new Edificacion(jugadores.get(turno).getAvatar().getLugar(), "hotel");
        Casilla actual = jugadores.get(turno).getAvatar().getLugar();
        int limiteGrupo = actual.getGrupo().getNumCasillas();
        int numCasas = actual.contarCasas();
        int hotelgrupo = actual.getGrupo().contarHotelesGrupo();
        int contarHoteles = actual.contarHoteles();
        
        if (comprobar()) {
            
        
            if (hotelgrupo < limiteGrupo) {
                if (numCasas == 4) {
                    if (jugadores.get(turno).getFortuna() >= (actual.getValor() * 0.6f)) {
                        actual.anhadirEdificacion(hotel);
                        jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna() - (actual.getValor() * 0.6f));
                        jugadores.get(turno).setDineroInvertido(jugadores.get(turno).getDineroInvertido() + (actual.getValor()*0.6f)); //sumamos el valor de la casilla al atributo dineroInvertido del comprador


                        consola.imprimir("Se han pagado " + actual.getValor() * 0.6f + " por la construcción de un hotel. La fortuna restante es de " + jugadores.get(turno).getFortuna() + "\n");
                        consola.imprimir("Se ha construido el hotel y se han quitado las 4 casas\n");
                        consola.imprimir("Se ha construido un hotel correctamente en la casilla " + actual.getNombre() + ". Hay " + (contarHoteles + 1) + " hoteles construidos.\n");
                        actual.cambiarcasas();
                    } else {
                        consola.imprimir("No dispones del dinero necesario para construir la edificación\n");
                    }
                } else {
                    consola.imprimir("No hay 4 casas en la casilla como para construir el hotel\n");
                }
            } else {
                consola.imprimir("Has alcanzado el límite máximo de hoteles en el grupo\n");
            }
        }
    }
    
    public void edificarPiscina() {
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

                    consola.imprimir("Se han pagado " + actual.getValor() * 0.4f + " por la construcción de una piscina. La fortuna restante es de " + jugadores.get(turno).getFortuna());
                    contarpiscinas = actual.contarPiscinas();
                    consola.imprimir("Se ha construido una piscina correctamente en la casilla " + actual.getNombre() + ". Hay " + contarpiscinas + " piscinas construidas.");
                } else {
                    consola.imprimir("No dispones del dinero necesario para construir la edificación");
                }
            } else {
                consola.imprimir("Para construir una piscina necesitas 1 hotel y 2 casas o 2 o más hoteles");
            }
        } else {
            consola.imprimir("Has alcanzado el máximo de piscinas en el grupo");
        }
    }
    
    public void edificarPista() {
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


                    consola.imprimir("Se han pagado " + actual.getValor() * 1.25f + " por la construcción de una pista. La fortuna restante es de " + jugadores.get(turno).getFortuna());
                    consola.imprimir("Se ha construido una pista correctamente en la casilla " + actual.getNombre() + ". Hay " + (contarpistas + 1) + " pistas construidas.");
                } else {
                    consola.imprimir("No dispones del dinero necesario para construir la edificación");
                }
            } else {
                consola.imprimir("Necesitas al menos 2 hoteles para construir una pista de deportes");
            }
        } else {
            consola.imprimir("Has alcanzado el máximo de pistas de deporte en el grupo");
        }
    }

    private void venderCasa(String nombreCasilla, int numvender) {
        Casilla actual = tablero.encontrar_casilla(nombreCasilla);
        if (actual == null || !(actual.getDuenho().equals(jugadores.get(turno)))) {
            consola.imprimir("La casilla especificada no existe o no pertenece a este jugador.");
            return;
        }
        int numCasas = actual.contarCasas();
        if (numCasas < numvender)
        {
            consola.imprimir("No puedes vender más casas de las que hay construidas");
            return;
        }
        
        for(int i = 0; i<numvender; i++){
        if (numCasas > 0) {
            float valorVenta = actual.getValor() * 0.6f / 2;
            actual.eliminarEdificacion("casa");
            jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna() + valorVenta);
            consola.imprimir("Se ha vendido una casa por " + valorVenta + ". La fortuna actual es de " + jugadores.get(turno).getFortuna());
            numCasas = actual.contarCasas();
            consola.imprimir("Se ha eliminado una casa en la casilla " + actual.getNombre() + ". Quedan " + (numCasas) + " casas construidas.");
        } else {
            consola.imprimir("No hay casas para vender en esta casilla.");
            return;
            }
        }
    }
    
    private void venderHotel(String nombreCasilla, int numvender) {
        Casilla actual = tablero.encontrar_casilla(nombreCasilla);
        int numHoteles = actual.contarHoteles();

        if (actual == null || actual.getDuenho().equals(jugadores.get(turno))) {
            consola.imprimir("La casilla especificada no existe o no pertenece a este jugador.");
            return;
        }
        
        if (numHoteles < numvender)
        {
            consola.imprimir("No puedes vender más hoteles de los que hay construidos");
            return;
        }
        
        for(int i = 0; i<numvender; i++){
    
        if (numHoteles > 0) {
            float valorVenta = actual.getValor() * 0.6f / 2;
            actual.eliminarEdificacion("hotel");
            jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna() + valorVenta);
            consola.imprimir("Se ha vendido un hotel por " + valorVenta + ". La fortuna actual es de " + jugadores.get(turno).getFortuna());
            numHoteles = actual.contarHoteles();
            consola.imprimir("Se ha eliminado un hotel en la casilla " + actual.getNombre() + ". Quedan " + (numHoteles) + " hoteles construidos.");
        } else {
            consola.imprimir("No hay hoteles para vender en esta casilla.");
            return;
        }
        }
    }
    
    private void venderPiscina(String nombreCasilla, int numvender) {
        Casilla actual = tablero.encontrar_casilla(nombreCasilla);
        int numPiscinas = actual.contarPiscinas();
    
        if (actual == null || actual.getDuenho().equals(jugadores.get(turno))) {
            consola.imprimir("La casilla especificada no existe o no es de este jugador.");
            return;
        }
        
        if (numPiscinas < numvender)
        {
            consola.imprimir("No puedes vender más casas de las que hay construidas");
            return;
        }
        
        for(int i = 0; i<numvender; i++){

        if (numPiscinas > 0) {
            float valorVenta = actual.getValor() * 0.4f / 2;
            actual.eliminarEdificacion("piscina");
            jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna() + valorVenta);
            consola.imprimir("Se ha vendido una piscina por " + valorVenta + ". La fortuna actual es de " + jugadores.get(turno).getFortuna());
            numPiscinas = actual.contarPiscinas();
            consola.imprimir("Se ha eliminado una piscina en la casilla " + actual.getNombre() + ". Quedan " + (numPiscinas) + " piscinas construidas.");
        } else {
            consola.imprimir("No hay piscinas para vender en esta casilla.");
            return;
        }
        }
    }
    
    private void venderPista(String nombreCasilla, int numvender) {
        Casilla actual = tablero.encontrar_casilla(nombreCasilla);
        int numPistas = actual.contarPistas();
        
        if (actual == null || actual.getDuenho().equals(jugadores.get(turno))) {
            consola.imprimir("La casilla especificada no existe o no es de este jugador.");
            return;
        }
        if (numPistas < numvender)
        {
            consola.imprimir("No puedes vender más casas de las que hay construidas");
            return;
        }
        
        for(int i = 0; i<numvender; i++){

        if (numPistas > 0) {
            float valorVenta = actual.getValor() * 1.25f / 2;
            actual.eliminarEdificacion("pista");
            jugadores.get(turno).setFortuna(jugadores.get(turno).getFortuna() + valorVenta);
            consola.imprimir("Se ha vendido una pista por " + valorVenta + ". La fortuna actual es de " + jugadores.get(turno).getFortuna());
            numPistas = actual.contarPistas();
            consola.imprimir("Se ha eliminado una pista en la casilla " + actual.getNombre() + ". Quedan " + (numPistas) + " pistas construidas.");
        } else {
            consola.imprimir("No hay pistas para vender en esta casilla.");
            return;
        }
        }
    }
    

    
}
