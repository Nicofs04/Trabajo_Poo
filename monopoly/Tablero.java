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
        arrayCasillasNorte.add(new Casilla("solar12","solar", 21, 0, banca));
        arrayCasillasNorte.add(new Casilla("suerte", "suerte",22, banca));
        arrayCasillasNorte.add(new Casilla("solar13", "solar",23, 0, banca));
        arrayCasillasNorte.add(new Casilla("solar14", "solar",24, 0, banca));
        arrayCasillasNorte.add(new Casilla("trans3", "transporte",25, 0, banca));
        arrayCasillasNorte.add(new Casilla("solar15", "solar",26, 0, banca));
        arrayCasillasNorte.add(new Casilla("solar16", "solar",27, 0, banca));
        arrayCasillasNorte.add(new Casilla("serv2", "servicio",28, 0, banca));
        arrayCasillasNorte.add(new Casilla("solar17", "solar",29, 0, banca));
        posiciones.add(arrayCasillasNorte);
        Grupo rojo = new Grupo(arrayCasillasNorte.get(1),arrayCasillasNorte.get(3), arrayCasillasNorte.get(4), "RED");
        grupos.put("ROJO", rojo);
        Grupo amarillo = new Grupo(arrayCasillasNorte.get(6),arrayCasillasNorte.get(7), arrayCasillasNorte.get(9), "YELLOW");
        grupos.put("AMARILLO", amarillo);
    }

    //Método para insertar las casillas del lado sur.
    private void insertarLadoSur() {
        ArrayList<Casilla> arrayCasillasSur = new ArrayList<Casilla>();
        arrayCasillasSur.add(new Casilla("salida","especial",0, banca));
        arrayCasillasSur.add(new Casilla("solar1","solar",1,0, banca));
        arrayCasillasSur.add(new Casilla("caja","caja",2, banca));
        arrayCasillasSur.add(new Casilla("solar2","solar",3,87030, banca));
        arrayCasillasSur.add(new Casilla("imp1",4,0, banca));
        arrayCasillasSur.add(new Casilla("trans1","transporte",5,87030, banca));
        arrayCasillasSur.add(new Casilla("solar3","solar",6,0, banca));
        arrayCasillasSur.add(new Casilla("suerte","suerte",7, banca));
        arrayCasillasSur.add(new Casilla("solar4","solar",8,0, banca));
        arrayCasillasSur.add(new Casilla("solar5","solar",9,0, banca));
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
        arrayCasillasOeste.add(new Casilla("solar6", "solar", 11, 0, banca));
        arrayCasillasOeste.add(new Casilla("serv1", "servicio", 12, 0, banca));
        arrayCasillasOeste.add(new Casilla("solar7", "solar", 13, 0, banca));
        arrayCasillasOeste.add(new Casilla("solar8", "solar", 14, 0, banca));
        arrayCasillasOeste.add(new Casilla("trans2", "transporte", 15, 0, banca));
        arrayCasillasOeste.add(new Casilla("solar9", "solar", 16, 0, banca));
        arrayCasillasOeste.add(new Casilla("caja", "caja", 17, banca));
        arrayCasillasOeste.add(new Casilla("solar10", "solar", 18, 0, banca));
        arrayCasillasOeste.add(new Casilla("solar11", "solar", 19, 0, banca));
        posiciones.add(arrayCasillasOeste);
        Grupo violeta = new Grupo(arrayCasillasOeste.get(1),arrayCasillasOeste.get(3), arrayCasillasOeste.get(4), "PURPLE");
        grupos.put("VIOLETA", violeta);
        Grupo blanco = new Grupo(arrayCasillasOeste.get(6),arrayCasillasOeste.get(8), arrayCasillasOeste.get(9), "WHITE");
        grupos.put("BLANCO", blanco);
    }

    //Método que inserta las casillas del lado este.
    private void insertarLadoEste() {
        ArrayList<Casilla> arrayCasillasEste = new ArrayList<Casilla>();
        arrayCasillasEste.add(new Casilla("ircarcel","especial",30,banca));
        arrayCasillasEste.add(new Casilla("solar18","solar",31,0,banca));
        arrayCasillasEste.add(new Casilla("solar19","solar",32,0,banca));
        arrayCasillasEste.add(new Casilla("caja","caja",33,banca));
        arrayCasillasEste.add(new Casilla("solar20","solar",34,0,banca));
        arrayCasillasEste.add(new Casilla("trans4","transporte",35,0,banca));
        arrayCasillasEste.add(new Casilla("suerte","suerte",36,banca));
        arrayCasillasEste.add(new Casilla("solar21","solar",37,0,banca));
        arrayCasillasEste.add(new Casilla("imp2",38,0,banca));
        arrayCasillasEste.add(new Casilla("solar22","solar",39,0,banca));
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
        sb.append("\n—————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————\n");
    
        //LADO NORTE    

        sb.append("│");

        sb.append(posiciones.get(2).get(0).generarCasilla());

        sb.append("│");

        sb.append(posiciones.get(2).get(1).generarCasilla());

        sb.append("│");

        sb.append(posiciones.get(2).get(2).generarCasilla());
        
        sb.append("│");

        sb.append(posiciones.get(2).get(3).generarCasilla());

        sb.append("│");

        sb.append(posiciones.get(2).get(4).generarCasilla());

        sb.append("│");

        sb.append(posiciones.get(2).get(5).generarCasilla());
        
        sb.append("│");

        sb.append(posiciones.get(2).get(6).generarCasilla());

        sb.append("│");

        sb.append(posiciones.get(2).get(7).generarCasilla());
        
        sb.append("│");

        sb.append(posiciones.get(2).get(8).generarCasilla());
        
        sb.append("│");

        sb.append(posiciones.get(2).get(9).generarCasilla());
        

        //LADO OESTE Y ESTE

        sb.append("│");

        sb.append(posiciones.get(3).get(0).generarCasilla());

        sb.append("│");

        sb.append("\n—————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————\n");
        
        sb.append("│");

        sb.append(posiciones.get(1).get(9).generarCasilla());

        sb.append("│");

        sb.append("                                                                                                                                               ");

        sb.append("│");
        
        sb.append(posiciones.get(3).get(1).generarCasilla());

        sb.append("│");

        sb.append("\n—————————————————                                                                                                                                               —————————————————\n");

        
        sb.append("│");

        sb.append(posiciones.get(1).get(8).generarCasilla());

        sb.append("│");

        sb.append("                                                                                                                                               ");

        sb.append("│");
        
        sb.append(posiciones.get(3).get(2).generarCasilla());

        sb.append("│");

        sb.append("\n—————————————————                                                                                                                                               —————————————————\n");
        
        //tercer piso        

        sb.append("│");

        sb.append(posiciones.get(1).get(7).generarCasilla());

        sb.append("│");

        sb.append("                                                                                                                                               ");

        sb.append("│");
        
        sb.append(posiciones.get(3).get(3).generarCasilla());

        sb.append("│");

        sb.append("\n—————————————————                                                                                                                                               —————————————————\n");

        //cuarto piso        

        sb.append("│");

        sb.append(posiciones.get(1).get(6).generarCasilla());

        sb.append("│");

        sb.append("                                                                                                                                               ");

        sb.append("│");
        
        sb.append(posiciones.get(3).get(4).generarCasilla());

        sb.append("│");

        sb.append("\n—————————————————                                                                                                                                               —————————————————\n");

         
        //quinto piso        

         sb.append("│");

         sb.append(posiciones.get(1).get(5).generarCasilla());
 
         sb.append("│");
 
         sb.append("                                                                                                                                               ");
 
         sb.append("│");
         
         sb.append(posiciones.get(3).get(5).generarCasilla());
 
         sb.append("│");
 
         sb.append("\n—————————————————                                                                                                                                               —————————————————\n");

          //sexto piso        

        sb.append("│");

        sb.append(posiciones.get(1).get(4).generarCasilla());

        sb.append("│");

        sb.append("                                                                                                                                               ");

        sb.append("│");
        
        sb.append(posiciones.get(3).get(6).generarCasilla());

        sb.append("│");

        sb.append("\n—————————————————                                                                                                                                               —————————————————\n");

         //septimo piso        

         sb.append("│");

         sb.append(posiciones.get(1).get(3).generarCasilla());
 
         sb.append("│");
 
         sb.append("                                                                                                                                               ");
 
         sb.append("│");
         
         sb.append(posiciones.get(3).get(7).generarCasilla());
 
         sb.append("│");
 
         sb.append("\n—————————————————                                                                                                                                               —————————————————\n");

          //octavo piso        

        sb.append("│");

        sb.append(posiciones.get(1).get(2).generarCasilla());

        sb.append("│");

        sb.append("                                                                                                                                               ");

        sb.append("│");
        
        sb.append(posiciones.get(3).get(8).generarCasilla());

        sb.append("│");

        sb.append("\n—————————————————                                                                                                                                               —————————————————\n");

         //noveno piso        

         sb.append("│");

         sb.append(posiciones.get(1).get(1).generarCasilla());
 
         sb.append("│");
 
         sb.append("                                                                                                                                               ");
 
         sb.append("│");
         
         sb.append(posiciones.get(3).get(9).generarCasilla());
 
         sb.append("│");
 
         
        //LADO SUR

        sb.append("\n—————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————\n");
    

        sb.append("│");

        sb.append(posiciones.get(1).get(0).generarCasilla());

        sb.append("│");

        sb.append(posiciones.get(0).get(9).generarCasilla());

        sb.append("│");

        sb.append(posiciones.get(0).get(8).generarCasilla());

        sb.append("│");

        sb.append(posiciones.get(0).get(7).generarCasilla());
        
        sb.append("│");

        sb.append(posiciones.get(0).get(6).generarCasilla());

        sb.append("│");

        sb.append(posiciones.get(0).get(5).generarCasilla());

        sb.append("│");

        sb.append(posiciones.get(0).get(4).generarCasilla());
        
        sb.append("│");

        sb.append(posiciones.get(0).get(3).generarCasilla());

        sb.append("│");

        sb.append(posiciones.get(0).get(2).generarCasilla());
        
        sb.append("│");

        sb.append(posiciones.get(0).get(1).generarCasilla());
        
        sb.append("│");

        sb.append(posiciones.get(0).get(0).generarCasilla());

        sb.append("│");
        
        sb.append("\n—————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————\n");

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