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

    public static ConsolaNormal consola = new ConsolaNormal();

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
                    if(casilla.getDuenho().equals(banca)){ //chequeamos que la casilla no esté comprada
                        if (casilla instanceof Solar) {
                            Solar solar = (Solar)casilla;
                            solar.setValor(solar.getValor() * 1.05f);
                        }
                    }
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
        arrayCasillasNorte.add(new Especial("parking", 20, banca));
        arrayCasillasNorte.add(new Solar("solar12", 21, 1142440, banca));
        arrayCasillasNorte.add(new AccionSuerte("suerte",22, banca));
        arrayCasillasNorte.add(new Solar("solar13",23, 1142440, banca));
        arrayCasillasNorte.add(new Solar("solar14",24, 1142440, banca));
        arrayCasillasNorte.add(new Transporte("trans3",25, Valor.SUMA_VUELTA, banca));
        arrayCasillasNorte.add(new Solar("solar15",26, 1485172, banca));
        arrayCasillasNorte.add(new Solar("solar16",27, 1485172, banca));
        arrayCasillasNorte.add(new Servicio("serv2",28, (Valor.SUMA_VUELTA)*0.75f, banca));
        arrayCasillasNorte.add(new Solar("solar17",29, 1485172, banca));
        posiciones.add(arrayCasillasNorte);
        Grupo rojo = new Grupo((Solar) arrayCasillasNorte.get(1),(Solar)arrayCasillasNorte.get(3),(Solar) arrayCasillasNorte.get(4), "RED");
        grupos.put("ROJO", rojo);
        Grupo amarillo = new Grupo((Solar)arrayCasillasNorte.get(6),(Solar)arrayCasillasNorte.get(7),(Solar) arrayCasillasNorte.get(9), "YELLOW");
        grupos.put("AMARILLO", amarillo);
    }

    //Método para insertar las casillas del lado sur.
    private void insertarLadoSur() {
        ArrayList<Casilla> arrayCasillasSur = new ArrayList<Casilla>();
        arrayCasillasSur.add(new Especial("salida",0, banca));
        arrayCasillasSur.add(new Solar("solar1",1, 600000, banca));
        arrayCasillasSur.add(new AccionCaja("caja",2, banca));
        arrayCasillasSur.add(new Solar("solar2",3, 600000, banca));
        arrayCasillasSur.add(new Impuesto("imp1",4,(Valor.SUMA_VUELTA)*0.5f, banca));
        arrayCasillasSur.add(new Transporte("trans1",5, Valor.SUMA_VUELTA, banca));
        arrayCasillasSur.add(new Solar("solar3",6, 520000, banca));
        arrayCasillasSur.add(new AccionSuerte("suerte",7, banca));
        arrayCasillasSur.add(new Solar("solar4",8, 520000, banca));
        arrayCasillasSur.add(new Solar("solar5",9, 520000, banca));
        posiciones.add(arrayCasillasSur);
        Grupo negro = new Grupo((Solar)arrayCasillasSur.get(1),(Solar)arrayCasillasSur.get(3),"BLACK");
        grupos.put("NEGRO", negro);
        Grupo cyan = new Grupo((Solar)arrayCasillasSur.get(6),(Solar)arrayCasillasSur.get(8), (Solar)arrayCasillasSur.get(9), "CYAN");
        grupos.put("CYAN", cyan);
    }

    //Método que inserta casillas del lado oeste.
    private void insertarLadoOeste() {
        ArrayList<Casilla> arrayCasillasOeste = new ArrayList<Casilla>();
        arrayCasillasOeste.add(new Especial("carcel", 10, banca));
        arrayCasillasOeste.add(new Solar("solar6", 11, 676000, banca));
        arrayCasillasOeste.add(new Servicio("serv1", 12, (Valor.SUMA_VUELTA)*0.75f, banca));
        arrayCasillasOeste.add(new Solar("solar7", 13, 676000, banca));
        arrayCasillasOeste.add(new Solar("solar8", 14, 676000, banca));
        arrayCasillasOeste.add(new Transporte("trans2", 15, Valor.SUMA_VUELTA, banca));
        arrayCasillasOeste.add(new Solar("solar9", 16, 878800, banca));
        arrayCasillasOeste.add(new AccionCaja("caja", 17, banca));
        arrayCasillasOeste.add(new Solar("solar10", 18, 878800, banca));
        arrayCasillasOeste.add(new Solar("solar11", 19, 878800, banca));
        posiciones.add(arrayCasillasOeste);
        Grupo violeta = new Grupo((Solar)arrayCasillasOeste.get(1),(Solar)arrayCasillasOeste.get(3),(Solar) arrayCasillasOeste.get(4), "PURPLE");
        grupos.put("VIOLETA", violeta);
        Grupo blanco = new Grupo((Solar)arrayCasillasOeste.get(6),(Solar)arrayCasillasOeste.get(8), (Solar)arrayCasillasOeste.get(9), "WHITE");
        grupos.put("BLANCO", blanco);
    }

    //Método que inserta las casillas del lado este.
    private void insertarLadoEste() {
        ArrayList<Casilla> arrayCasillasEste = new ArrayList<Casilla>();
        arrayCasillasEste.add(new Especial("ircarcel",30,banca));
        arrayCasillasEste.add(new Solar("solar18",31, 1930723.6f,banca));
        arrayCasillasEste.add(new Solar("solar19",32, 1930723.6f,banca));
        arrayCasillasEste.add(new AccionCaja("caja",33,banca));
        arrayCasillasEste.add(new Solar("solar20",34, 1930723.6f,banca));
        arrayCasillasEste.add(new Transporte("trans4",35, Valor.SUMA_VUELTA, banca));
        arrayCasillasEste.add(new AccionSuerte("suerte",36,banca));
        arrayCasillasEste.add(new Solar("solar21",37, 3764911.02f,banca));
        arrayCasillasEste.add(new Impuesto("imp2",38,Valor.SUMA_VUELTA,banca));
        arrayCasillasEste.add(new Solar("solar22",39, 3764911.02f,banca));
        posiciones.add(arrayCasillasEste);
        Grupo verde = new Grupo((Solar)arrayCasillasEste.get(1),(Solar)arrayCasillasEste.get(2),(Solar) arrayCasillasEste.get(4), "GREEN");
        grupos.put("VERDE", verde);
        Grupo azul = new Grupo((Solar)arrayCasillasEste.get(7),(Solar)arrayCasillasEste.get(9),"BLUE");
        grupos.put("AZUL", azul);
    }

    //Para imprimir el tablero, modificamos el método toString().
    @Override
    public String toString(){
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
        consola.imprimir("No se ha encontrado la casilla\n");
        return null;
    }
}