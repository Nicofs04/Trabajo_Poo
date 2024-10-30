package monopoly;

public class Edificacion {

    //Atributos

    private String tipo;
    private Casilla casilla;

    

    public Edificacion(Casilla casilla,String tipo){

        this.casilla = casilla;
        this.tipo = tipo;
        
        
    }

    public Casilla getCasilla(){
        return this.casilla;
    }

    public void setCasilla(Casilla casilla){
        this.casilla = casilla;
    }

    public String getTipo(){
        return this.tipo;
    }

    public void setTipo(String tipo){
        this.tipo=tipo;
    }
    










































}
