package monopoly;

public class Edificacion {

    //Atributos

    private String tipo;
    private Casilla casilla;
    private int id;
    

    public Edificacion(Casilla casilla,String tipo){

        this.casilla = casilla;
        this.tipo = tipo;
        this.id = generarID(casilla, tipo);
        
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

    public int getId(){
        return this.id;
    }

    private int generarID(Casilla casilla, String tipo){
        int casa = casilla.contarCasas();
        int hotel = casilla.contarHoteles();
        int piscina = casilla.contarPiscinas();
        int pista = casilla.contarPistas();
        int id=0;

        switch (tipo) {
            case "casa":
                if (casa==0) {
                    id=casa+1;
                }else if (casa==1) {
                    id=casa+1;
                }else if (casa==2) {
                    id=casa+1;
                }else if (casa==3) {
                    id=casa+1;
                }
                break;
            case "hotel":   
                if (hotel==0) {
                    id=hotel+1;
                }else if (hotel==1) {
                    id=hotel+1;
                }else if (hotel==2) {
                    id=hotel+1;
                }     
                break;
            case "piscina":   
                    if (piscina==0) {
                        id=piscina+1;
                    }else if (piscina==1) {
                        id=piscina+1;
                    }else if (piscina==2) {
                        id=piscina+1;
                    }        
                break;
            case "pista":        
                    if (pista==0) {
                            id=pista+1;
                    }else if (pista==1) {
                        id=pista+1;
                    }else if (pista==2) {
                        id=pista+1;
                    }   
                break;
        }

        return id;

    }
    










































}
