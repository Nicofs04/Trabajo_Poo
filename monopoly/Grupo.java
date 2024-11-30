package monopoly;

import partida.*;
import java.util.ArrayList;


public class Grupo {

    //Atributos
    private ArrayList<Solar> miembros; //Casillas miembros del grupo.
    private String colorGrupo; //Color del grupo
    private int numCasillas; //Número de casillas del grupo.

    //Constructor vacío.
    public Grupo() {
    }

    /*Constructor para cuando el grupo está formado por DOS CASILLAS:
    * Requiere como parámetros las dos casillas miembro y el color del grupo.
     */
    public Grupo(Solar cas1, Solar cas2, String colorGrupo) {
        this.miembros = new ArrayList<Solar>();
        miembros.add(cas1);
        miembros.add(cas2);
        this.numCasillas=2;
        this.colorGrupo = colorGrupo;
        cas1.setGrupo(this);
        cas2.setGrupo(this);
    }

    /*Constructor para cuando el grupo está formado por TRES CASILLAS:
    * Requiere como parámetros las tres casillas miembro y el color del grupo.
     */
    public Grupo(Solar cas1, Solar cas2, Solar cas3, String colorGrupo) {
        this.miembros = new ArrayList<Solar>();
        miembros.add(cas1);
        miembros.add(cas2);
        miembros.add(cas3);
        this.numCasillas=3;
        this.colorGrupo = colorGrupo;
        cas1.setGrupo(this);
        cas2.setGrupo(this);
        cas3.setGrupo(this);
    }

    public ArrayList<Solar> getMiembros(){
        return miembros;
    }

    public void setMiembros(ArrayList<Solar> miembros){
        this.miembros = miembros;
    }

    public String getColorGrupo(){
        return colorGrupo;
    }

    public void setColorGrupo(String colorGrupo){
        this.colorGrupo = colorGrupo;
    }

    public int getNumCasillas(){
        return numCasillas;
    }

    public void setNumCasillas(int numCasillas){
        this.numCasillas = numCasillas;
    }

    /* Método que añade una casilla al array de casillas miembro de un grupo.
    * Parámetro: casilla que se quiere añadir.
     */
    public void anhadirCasilla(Solar miembro) {
        //hacemos doble asignación, añadimos miembro al array y a su vez hacemos que el grupo de miembro sea el propio objeto
        this.miembros.add(miembro);
        miembro.setGrupo(this);

    }

    /*Método que comprueba si el jugador pasado tiene en su haber todas las casillas del grupo:
    * Parámetro: jugador que se quiere evaluar.
    * Valor devuelto: true si es dueño de todas las casillas del grupo, false en otro caso.
     */
    public boolean esDuenhoGrupo(Jugador jugador) {
        
        for (Casilla casilla : this.miembros){
                if (!jugador.getPropiedades().contains(casilla)) {
                    return false;
                }
            }
        return true;
    }

    public int contarCasasGrupo(){
        int contador=0;
        for (Solar solar : this.getMiembros()){
                for(Edificacion edificacion : solar.getEdificacion()){
                    if (edificacion instanceof Casa) {
                        contador++;
                    }
                }
        }
        return contador;
    }
    
    public int contarHotelesGrupo(){
        int contador=0;
        for (Solar solar : this.getMiembros()){
                for(Edificacion edificacion : solar.getEdificacion()){
                    if (edificacion instanceof Hotel) {
                        contador++;
                    }
                }
        }
        return contador;
    }
    
    public int contarPiscinasGrupo(){
        int contador=0;
        for (Solar solar : this.getMiembros()){
                for(Edificacion edificacion : solar.getEdificacion()){
                    if (edificacion instanceof Piscina) {
                        contador++;
                    }
                }
        }
        return contador;
    }
    
    public int contarPistasGrupo(){
        int contador=0;
        for (Solar solar : this.getMiembros()){
            for(Edificacion edificacion : solar.getEdificacion()){
                if (edificacion instanceof PistaDeporte) {
                    contador++;
                }
            }
        }
        return contador;
    }


}
