package monopoly;

import partida.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;


public class Tablero {
    //Atributos.
    private ArrayList<ArrayList<Casilla>> posiciones; //Posiciones del tablero: se define como un arraylist de arraylists de casillas (uno por cada lado del tablero).
    private HashMap<String, Grupo> grupos; //Grupos del tablero, almacenados como un HashMap con clave String (será el color del grupo).
    private Jugador banca; //Un jugador que será la banca.

    //Constructor: únicamente le pasamos el jugador banca (que se creará desde el menú).
    public Tablero(Jugador banca) {
        this.posiciones = new ArrayList<ArrayList<Casilla>>();
        this.grupos = new HashMap<String, Grupo>();
        this.banca = banca;
        generarCasillas();
    }

    public ArrayList<ArrayList<Casilla>> getPosiciones(){
        return posiciones;
    }

    public void setPosiciones(ArrayList<ArrayList<Casilla>> posiciones){
        this.posiciones = posiciones;
    }

    public HashMap<String, Grupo> getGrupos(){
        return grupos;
    }

    public void setGrupos(HashMap<String,Grupo> grupos){
        this.grupos = grupos;
    }

    public Jugador getBanca(){
        return banca;
    }

    public void setBanca(Jugador banca){
        this.banca = banca;
    }

    public void calcularCasillas(ArrayList<Jugador> jugadores){
        int i;
        boolean todos4vueltas = true;

        for(Jugador jugador:jugadores){
            if(jugador.getVueltas() < 4){
                todos4vueltas = false;
                break; //salimos del if para que un jugador que tenga más de 4 vueltas no pueda cambiar el valor del booleano (tienen que tener todos más de 4 vueltas)
            }
        }

        if(todos4vueltas){
            for(Jugador jugador:jugadores){
                jugador.setVueltas(jugador.getVueltas() - 4);
            }

            for(i = 0; i< 4; i++){
                for(Casilla casilla:posiciones.get(i)){
                    casilla.setValor(casilla.getValor() * 1.05f);
                }
            }

            todos4vueltas = false;
        }
    }
    
    //Método para crear todas las casillas del tablero. Formado a su vez por cuatro métodos (1/lado).
    private void generarCasillas() {
        this.insertarLadoSur();
        this.insertarLadoOeste();
        this.insertarLadoNorte();
        this.insertarLadoEste();
    }


    //Método para insertar las casillas del lado norte.
    private void insertarLadoNorte() {
        ArrayList<Casilla> arrayCasillasNorte = new ArrayList<Casilla>();
        arrayCasillasNorte.add(new Casilla("parking", "especial", 20, banca));
        arrayCasillasNorte.add(new Casilla("solar12","solar", 21, 1142440, banca));
        arrayCasillasNorte.add(new Casilla("suerte", "suerte",22, banca));
        arrayCasillasNorte.add(new Casilla("solar13", "solar",23, 1142440, banca));
        arrayCasillasNorte.add(new Casilla("solar14", "solar",24, 1142440, banca));
        arrayCasillasNorte.add(new Casilla("trans3", "transporte",25, Valor.SUMA_VUELTA, banca));
        arrayCasillasNorte.add(new Casilla("solar15", "solar",26, 1485172, banca));
        arrayCasillasNorte.add(new Casilla("solar16", "solar",27, 1485172, banca));
        arrayCasillasNorte.add(new Casilla("serv2", "servicio",28, (Valor.SUMA_VUELTA)*0.75f, banca));
        arrayCasillasNorte.add(new Casilla("solar17", "solar",29, 1485172, banca));
        posiciones.add(arrayCasillasNorte);
        Grupo rojo = new Grupo(arrayCasillasNorte.get(1),arrayCasillasNorte.get(3), arrayCasillasNorte.get(4), "RED");
        grupos.put("ROJO", rojo);
        Grupo amarillo = new Grupo(arrayCasillasNorte.get(6),arrayCasillasNorte.get(7), arrayCasillasNorte.get(9), "BROWN");
        grupos.put("AMARILLO", amarillo);
    }

    //Método para insertar las casillas del lado sur.
    private void insertarLadoSur() {
        ArrayList<Casilla> arrayCasillasSur = new ArrayList<Casilla>();
        arrayCasillasSur.add(new Casilla("salida","especial",0, banca));
        arrayCasillasSur.add(new Casilla("solar1","solar",1, 600000, banca));
        arrayCasillasSur.add(new Casilla("caja","caja",2, banca));
        arrayCasillasSur.add(new Casilla("solar2","solar",3, 600000, banca));
        arrayCasillasSur.add(new Casilla("imp1",4,0, banca));
        arrayCasillasSur.add(new Casilla("trans1","transporte",5, Valor.SUMA_VUELTA, banca));
        arrayCasillasSur.add(new Casilla("solar3","solar",6, 520000, banca));
        arrayCasillasSur.add(new Casilla("suerte","suerte",7, banca));
        arrayCasillasSur.add(new Casilla("solar4","solar",8, 520000, banca));
        arrayCasillasSur.add(new Casilla("solar5","solar",9, 520000, banca));
        posiciones.add(arrayCasillasSur);
        Grupo negro = new Grupo(arrayCasillasSur.get(1),arrayCasillasSur.get(3),"BLACK");
        grupos.put("NEGRO", negro);
        Grupo cyan = new Grupo(arrayCasillasSur.get(6),arrayCasillasSur.get(7), arrayCasillasSur.get(9), "CYAN");
        grupos.put("CYAN", cyan);
    }

    //Método que inserta casillas del lado oeste.
    private void insertarLadoOeste() {
        ArrayList<Casilla> arrayCasillasOeste = new ArrayList<Casilla>();
        arrayCasillasOeste.add(new Casilla("carcel", "especial", 10, banca));
        arrayCasillasOeste.add(new Casilla("solar6", "solar", 11, 676000, banca));
        arrayCasillasOeste.add(new Casilla("serv1", "servicio", 12, (Valor.SUMA_VUELTA)*0.75f, banca));
        arrayCasillasOeste.add(new Casilla("solar7", "solar", 13, 676000, banca));
        arrayCasillasOeste.add(new Casilla("solar8", "solar", 14, 676000, banca));
        arrayCasillasOeste.add(new Casilla("trans2", "transporte", 15, Valor.SUMA_VUELTA, banca));
        arrayCasillasOeste.add(new Casilla("solar9", "solar", 16, 878800, banca));
        arrayCasillasOeste.add(new Casilla("caja", "caja", 17, banca));
        arrayCasillasOeste.add(new Casilla("solar10", "solar", 18, 878800, banca));
        arrayCasillasOeste.add(new Casilla("solar11", "solar", 19, 878800, banca));
        posiciones.add(arrayCasillasOeste);
        Grupo violeta = new Grupo(arrayCasillasOeste.get(1),arrayCasillasOeste.get(3), arrayCasillasOeste.get(4), "PURPLE");
        grupos.put("VIOLETA", violeta);
        Grupo blanco = new Grupo(arrayCasillasOeste.get(6),arrayCasillasOeste.get(7), arrayCasillasOeste.get(9), "WHITE");
        grupos.put("BLANCO", blanco);
    }

    //Método que inserta las casillas del lado este.
    private void insertarLadoEste() {
        ArrayList<Casilla> arrayCasillasEste = new ArrayList<Casilla>();
        arrayCasillasEste.add(new Casilla("ircarcel","especial",30,banca));
        arrayCasillasEste.add(new Casilla("solar18","solar",31, 1930723.6f,banca));
        arrayCasillasEste.add(new Casilla("solar19","solar",32, 1930723.6f,banca));
        arrayCasillasEste.add(new Casilla("caja","caja",33,banca));
        arrayCasillasEste.add(new Casilla("solar20","solar",34, 1930723.6f,banca));
        arrayCasillasEste.add(new Casilla("trans4","transporte",35, Valor.SUMA_VUELTA, banca));
        arrayCasillasEste.add(new Casilla("suerte","suerte",36,banca));
        arrayCasillasEste.add(new Casilla("solar21","solar",37, 3764911.02f,banca));
        arrayCasillasEste.add(new Casilla("imp2",38,0,banca));
        arrayCasillasEste.add(new Casilla("solar22","solar",39, 3764911.02f,banca));
        posiciones.add(arrayCasillasEste);
        Grupo verde = new Grupo(arrayCasillasEste.get(1),arrayCasillasEste.get(3), arrayCasillasEste.get(4), "GREEN");
        grupos.put("VERDE", verde);
        Grupo azul = new Grupo(arrayCasillasEste.get(6),arrayCasillasEste.get(7),"BLUE");
        grupos.put("AZUL", azul);
    }

    //Para imprimir el tablero, modificamos el método toString().
    @Override
    public String toString(){
        int i;
        StringBuilder sb = new StringBuilder();
        sb.append("——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————\n");
    
        //LADO NORTE    

        sb.append(posiciones.get(2).get(0).generarCasilla());

        sb.append(posiciones.get(2).get(1).generarCasilla());
        
        if(posiciones.get(2).get(2).getAvatares().isEmpty() ){
                sb.append(String.format("│%s        │", posiciones.get(2).get(2).getNombre()));
            }else{
                sb.append(String.format("│%s      &%s│", posiciones.get(2).get(2).getNombre(), posiciones.get(2).get(2).getAvatares()));
            }
        if(posiciones.get(2).get(3).getAvatares().isEmpty() ){
            sb.append(String.format("│%s%s      %s│", Valor.RED, posiciones.get(2).get(3).getNombre(), Valor.RESET));
                }else{
                    sb.append(String.format("│%s%s    %s&%s│",Valor.RED, posiciones.get(2).get(3).getNombre(),Valor.RESET ,posiciones.get(2).get(3).getAvatares()));
                }
    
        if(posiciones.get(2).get(4).getAvatares().isEmpty() ){
            sb.append(String.format("│%s%s      %s│", Valor.RED, posiciones.get(2).get(4).getNombre(), Valor.RESET));
                }else{
                    sb.append(String.format("│%s%s    %s&%s│",Valor.RED, posiciones.get(2).get(4).getNombre(),Valor.RESET ,posiciones.get(2).get(4).getAvatares()));
                }
    
        if(posiciones.get(2).get(5).getAvatares().isEmpty() ){
                    sb.append(String.format("│%s       │", posiciones.get(2).get(5).getNombre()));
                }else{
                    sb.append(String.format("│%s    &%s│", posiciones.get(2).get(5).getNombre(), posiciones.get(2).get(5).getAvatares()));
                }
        if(posiciones.get(2).get(6).getAvatares().isEmpty() ){
                sb.append(String.format("│%s%s      %s│", Valor.YELLOW, posiciones.get(2).get(6).getNombre(), Valor.RESET));
                    }else{
                        sb.append(String.format("│%s%s    %s&%s│",Valor.YELLOW, posiciones.get(2).get(6).getNombre(),Valor.RESET ,posiciones.get(2).get(6).getAvatares()));
                    }
        
        if(posiciones.get(2).get(7).getAvatares().isEmpty() ){
            sb.append(String.format("│%s%s      %s│", Valor.YELLOW, posiciones.get(2).get(7).getNombre(), Valor.RESET));
        }else{
            sb.append(String.format("│%s%s    %s&%s│",Valor.YELLOW, posiciones.get(2).get(7).getNombre(),Valor.RESET ,posiciones.get(2).get(7).getAvatares()));
        }
        if(posiciones.get(2).get(8).getAvatares().isEmpty() ){
                sb.append(String.format("│%s        │", posiciones.get(2).get(8).getNombre()));
        }else{
                sb.append(String.format("│%s      &%s│", posiciones.get(2).get(8).getNombre(), posiciones.get(2).get(8).getAvatares()));
                  }
            
        if(posiciones.get(2).get(9).getAvatares().isEmpty() ){
                sb.append(String.format("│%s%s      %s│", Valor.YELLOW, posiciones.get(2).get(9).getNombre(), Valor.RESET));
        }else{
                sb.append(String.format("│%s%s    %s&%s│",Valor.YELLOW, posiciones.get(2).get(9).getNombre(),Valor.RESET ,posiciones.get(2).get(9).getAvatares()));
                        }


        //LADO OESTE Y ESTE

        if(posiciones.get(3).get(0).getAvatares().isEmpty() ){
        sb.append(String.format("│%s     |\n", posiciones.get(3).get(0).getNombre()));
        }else{
            sb.append(String.format("|%s   &%s│\n", posiciones.get(3).get(0).getNombre(), posiciones.get(3).get(0).getAvatares()));
        }
        sb.append("——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————\n");
        
        if(posiciones.get(1).get(9).getAvatares().isEmpty()){
            sb.append(String.format("│%s%s      %s│", Valor.WHITE, posiciones.get(1).get(9).getNombre(), Valor.RESET));
        }else{
            sb.append(String.format("│%s%s    %s&%s│",Valor.WHITE, posiciones.get(1).get(9).getNombre(),Valor.RESET ,posiciones.get(1).get(9).getAvatares()));

        }
        
        sb.append("                                                                                                                                        ");


        if (posiciones.get(3).get(1).getAvatares().isEmpty()) {
            sb.append(String.format("│%s%s      %s│", Valor.GREEN, posiciones.get(3).get(1).getNombre(), Valor.RESET));
        }else{
            sb.append(String.format("│%s%s    %s&%s│",Valor.GREEN, posiciones.get(3).get(1).getNombre(),Valor.RESET ,posiciones.get(3).get(1).getAvatares()));

        }
        
        sb.append("\n———————————————                                                                                                                                        ——————————————— \n");

        if(posiciones.get(1).get(8).getAvatares().isEmpty()){
            sb.append(String.format("│%s%s      %s│", Valor.WHITE, posiciones.get(1).get(8).getNombre(), Valor.RESET));
        }else{
            sb.append(String.format("│%s%s    %s&%s│",Valor.WHITE, posiciones.get(1).get(8).getNombre(),Valor.RESET ,posiciones.get(1).get(8).getAvatares()));

        }
        
        sb.append("                                                                                                                                        ");

        if (posiciones.get(3).get(2).getAvatares().isEmpty()) {
            sb.append(String.format("│%s%s      %s│", Valor.GREEN, posiciones.get(3).get(2).getNombre(), Valor.RESET));
        }else{
            sb.append(String.format("│%s%s    %s&%s│",Valor.GREEN, posiciones.get(3).get(2).getNombre(),Valor.RESET ,posiciones.get(3).get(2).getAvatares()));

        }
        
        sb.append("\n———————————————                                                                                                                                        ——————————————— \n");
        
        if(posiciones.get(1).get(7).getAvatares().isEmpty()){
            sb.append(String.format("│%s         │", posiciones.get(1).get(7).getNombre()));
        }else{
            sb.append(String.format("│%s       &%s│", posiciones.get(1).get(7).getNombre(), posiciones.get(1).get(7).getAvatares()));

        }
        
        sb.append("                                                                                                                                        ");

        if (posiciones.get(3).get(3).getAvatares().isEmpty()) {
            sb.append(String.format("│%s         │\n", posiciones.get(3).get(3).getNombre()));
        }else{
            sb.append(String.format("│%s       &%s│\n", posiciones.get(3).get(3).getNombre(),posiciones.get(3).get(3).getAvatares()));

        }
        
        sb.append("———————————————                                                                                                                                        ——————————————— \n");
        
        if(posiciones.get(1).get(6).getAvatares().isEmpty()){
            sb.append(String.format("│%s%s       %s│", Valor.WHITE, posiciones.get(1).get(6).getNombre(), Valor.RESET));
        }else{
            sb.append(String.format("│%s%s    %s&%s│",Valor.WHITE, posiciones.get(1).get(6).getNombre(),Valor.RESET ,posiciones.get(1).get(6).getAvatares()));

        }
        
        sb.append("                                                                                                                                        ");
        
        if (posiciones.get(3).get(4).getAvatares().isEmpty()) {
            sb.append(String.format("│%s%s      %s│", Valor.GREEN, posiciones.get(3).get(4).getNombre(), Valor.RESET));
        }else{
            sb.append(String.format("│%s%s    %s&%s│",Valor.GREEN, posiciones.get(3).get(4).getNombre(),Valor.RESET ,posiciones.get(3).get(4).getAvatares()));
        }
        
        sb.append("\n———————————————                                                                                                                                        ——————————————— \n");
        
        if(posiciones.get(1).get(5).getAvatares().isEmpty()){
            sb.append(String.format("│%s       │", posiciones.get(1).get(5).getNombre()));
        }else{
            sb.append(String.format("│%s     &%s│", posiciones.get(1).get(5).getNombre(), posiciones.get(1).get(5).getAvatares()));

        }

        sb.append("                                                                                                                                        ");
       
        if (posiciones.get(3).get(5).getAvatares().isEmpty()) {
            sb.append(String.format("│%s       │\n", posiciones.get(3).get(5).getNombre()));
        }else{
            sb.append(String.format("│%s     &%s│\n", posiciones.get(3).get(5).getNombre(),posiciones.get(3).get(5).getAvatares()));
        }
        
        sb.append("———————————————                                                                                                                                        ——————————————— \n");
        
        if(posiciones.get(1).get(4).getAvatares().isEmpty()){
            sb.append(String.format("│%s%s       %s│", Valor.PURPLE, posiciones.get(1).get(4).getNombre(), Valor.RESET));
        }else{
            sb.append(String.format("│%s%s    %s&%s│",Valor.PURPLE, posiciones.get(1).get(4).getNombre(),Valor.RESET ,posiciones.get(1).get(4).getAvatares()));

        }

        sb.append("                                                                                                                                        ");
       
        if (posiciones.get(3).get(6).getAvatares().isEmpty()) {
            sb.append(String.format("│%s       │\n", posiciones.get(3).get(6).getNombre()));
        }else{
            sb.append(String.format("│%s     &%s│\n", posiciones.get(3).get(6).getNombre(),posiciones.get(3).get(6).getAvatares()));
        }

        sb.append("———————————————                                                                                                                                        ——————————————— \n");

        if(posiciones.get(1).get(3).getAvatares().isEmpty()){
            sb.append(String.format("│%s%s       %s│", Valor.PURPLE, posiciones.get(1).get(3).getNombre(), Valor.RESET));
        }else{
            sb.append(String.format("│%s%s    %s&%s│",Valor.PURPLE, posiciones.get(1).get(3).getNombre(),Valor.RESET ,posiciones.get(1).get(3).getAvatares()));

        }

        sb.append("                                                                                                                                        ");
       
        if (posiciones.get(3).get(7).getAvatares().isEmpty()) {
            sb.append(String.format("│%s%s      %s│", Valor.BLUE, posiciones.get(3).get(7).getNombre(), Valor.RESET));
        }else{
            sb.append(String.format("│%s%s    %s&%s│",Valor.BLUE, posiciones.get(3).get(7).getNombre(),Valor.RESET ,posiciones.get(3).get(7).getAvatares()));
        }

        sb.append("\n———————————————                                                                                                                                        ——————————————— \n");

        if(posiciones.get(1).get(2).getAvatares().isEmpty()){
            sb.append(String.format("│%s        │", posiciones.get(1).get(2).getNombre()));
        }else{
            sb.append(String.format("│%s      &%s│", posiciones.get(1).get(2).getNombre(), posiciones.get(1).get(2).getAvatares()));

        }

        sb.append("                                                                                                                                        ");
       
        if (posiciones.get(3).get(8).getAvatares().isEmpty()) {
            sb.append(String.format("│%s         │\n", posiciones.get(3).get(8).getNombre()));
        }else{
            sb.append(String.format("│%s        &%s│\n", posiciones.get(3).get(8).getNombre(),posiciones.get(3).get(8).getAvatares()));
        }

        sb.append("———————————————                                                                                                                                        ——————————————— \n");

        if(posiciones.get(1).get(1).getAvatares().isEmpty()){
            sb.append(String.format("│%s%s       %s│", Valor.PURPLE, posiciones.get(1).get(1).getNombre(), Valor.RESET));
        }else{
            sb.append(String.format("│%s%s    %s&%s│",Valor.PURPLE, posiciones.get(1).get(1).getNombre(),Valor.RESET ,posiciones.get(1).get(1).getAvatares()));

        }

        sb.append("                                                                                                                                        ");
       
        if (posiciones.get(3).get(9).getAvatares().isEmpty()) {
            sb.append(String.format("│%s%s      %s│", Valor.BLUE, posiciones.get(3).get(9).getNombre(), Valor.RESET));
                }else{
                    sb.append(String.format("│%s%s    %s&%s│",Valor.BLUE, posiciones.get(3).get(9).getNombre(),Valor.RESET ,posiciones.get(3).get(9).getAvatares()));
                }
        

        sb.append("\n——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————\n");

        if(posiciones.get(1).get(0).getAvatares().isEmpty() ){
            sb.append(String.format("|%s       │", posiciones.get(1).get(0).getNombre()));
        }else{
            sb.append(String.format("|%s     &%s│", posiciones.get(1).get(0).getNombre(), posiciones.get(1).get(0).getAvatares()));
            }
        

        //LADO SUR

        if(posiciones.get(0).get(9).getAvatares().isEmpty() ){
            sb.append(String.format("│%s%s        %s│", Valor.CYAN, posiciones.get(0).get(9).getNombre(), Valor.RESET));           }
            else{
                sb.append(String.format("│%s%s     %s&%s│",Valor.CYAN, posiciones.get(1).get(1).getNombre(),Valor.RESET ,posiciones.get(0).get(9).getAvatares()));
            }
    
             if(posiciones.get(0).get(8).getAvatares().isEmpty() ){
                sb.append(String.format("│%s%s        %s│", Valor.CYAN, posiciones.get(0).get(8).getNombre(), Valor.RESET));
            }else{
                sb.append(String.format("│%s%s    %s&%s│",Valor.CYAN, posiciones.get(0).get(8).getNombre(),Valor.RESET ,posiciones.get(0).get(8).getAvatares()));
            }
    
            if(posiciones.get(0).get(7).getAvatares().isEmpty() ){
                    sb.append(String.format("│%s       │", posiciones.get(0).get(7).getNombre()));
                }else{
                    sb.append(String.format("│%s     &%s│", posiciones.get(0).get(7).getNombre(), posiciones.get(0).get(7).getAvatares()));
                }
            if(posiciones.get(0).get(6).getAvatares().isEmpty() ){
                sb.append(String.format("│%s%s       %s│", Valor.CYAN, posiciones.get(0).get(6).getNombre(), Valor.RESET));
            }else{
                sb.append(String.format("│%s%s    %s&%s│",Valor.CYAN, posiciones.get(0).get(6).getNombre(),Valor.RESET ,posiciones.get(0).get(6).getAvatares()));
            }
        
            if(posiciones.get(0).get(5).getAvatares().isEmpty() ){
                    sb.append(String.format("│%s       │", posiciones.get(0).get(5).getNombre()));
                    }else{
                        sb.append(String.format("│%s     &%s│", posiciones.get(0).get(5).getNombre(), posiciones.get(0).get(5).getAvatares()));
                    }
        
            if(posiciones.get(0).get(4).getAvatares().isEmpty() ){
                        sb.append(String.format("│%s         │", posiciones.get(0).get(4).getNombre()));
                    }else{
                        sb.append(String.format("│%s       &%s│", posiciones.get(0).get(4).getNombre(), posiciones.get(0).get(4).getAvatares()));
                    }
            if(posiciones.get(0).get(3).getAvatares().isEmpty() ){
                sb.append(String.format("│%s%s      %s│", Valor.BLACK, posiciones.get(0).get(3).getNombre(), Valor.RESET));
                                    }else{
                                        sb.append(String.format("│%s%s    %s&%s│",Valor.BLACK, posiciones.get(0).get(3).getNombre(),Valor.RESET ,posiciones.get(0).get(3).getAvatares()));                        }
            
            if(posiciones.get(0).get(2).getAvatares().isEmpty() ){
                            sb.append(String.format("│%s         │", posiciones.get(0).get(2).getNombre()));
                        }else{
                            sb.append(String.format("│%s       &%s│", posiciones.get(0).get(2).getNombre(), posiciones.get(0).get(2).getAvatares()));
                        }
            if(posiciones.get(0).get(1).getAvatares().isEmpty() ){
                sb.append(String.format("│%s%s       %s│", Valor.BLACK, posiciones.get(0).get(1).getNombre(), Valor.RESET));
                    }else{
                        sb.append(String.format("│%s%s    %s&%s│",Valor.BLACK, posiciones.get(0).get(1).getNombre(),Valor.RESET ,posiciones.get(0).get(1).getAvatares()));                        }                            
                    
                        sb.append(posiciones.get(0).get(0).generarCasilla());
        sb.append("\n ——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————\n");
        return sb.toString();
    }
    
    //Método usado para buscar la casilla con el nombre pasado como argumento:
    public Casilla encontrar_casilla(String nombre){
        for(ArrayList<Casilla> lados:posiciones){
            for (Casilla casilla:lados){
                if (nombre.equals(casilla.getNombre())){
                    return casilla;
                }
            }
        }
        System.out.println("No se ha encontrado la casilla\n");
        return null;
    }
}