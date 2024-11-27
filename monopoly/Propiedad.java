package monopoly;


import java.util.Scanner;

import partida.Jugador;

public class Propiedad extends Casilla {

    
    private float valor; // valor de la propiedad
    private float impuesto;
    private boolean hipotecado; // estado de hipoteca de la casilla

    // Constructor de Propiedad
    public Propiedad(String nombre, int posicion,  float valor, Jugador duenho) {
        super(nombre, posicion, duenho); // llama al constructor de Casilla2
        this.valor = valor; // asigna el valor de la propiedad
        this.impuesto = valor*0.10f;
        this.hipotecado=false;
    }

    public float getImpuesto() {
        return impuesto;
    }
    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
    }

    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public boolean getHipotecado() {
        return hipotecado;
    }
    public void setHipotecado(boolean hipotecado) {
        this.hipotecado = hipotecado;
    }

    public void hipotecarPropiedad(Propiedad propiedad){
        propiedad.setHipotecado(true);
    }

    public void comprarCasilla (Jugador solicitante, Jugador banca){
        // Comprobamos que sea una casilla "comprable"
        if (estaEnVenta()==true) {
            if (solicitante.getFortuna()<this.valor) {
                System.out.println("No tienes dinero  suficiente para comprar esta casilla");
            }else{
            // JUGADOR SOLICITANTE
            
            // "quitar dinero"
            solicitante.setFortuna(solicitante.getFortuna() - this.valor); //le restamos el valor de la casilla comprada a la fortuna del jugador comprador
            solicitante.setDineroInvertido(solicitante.getDineroInvertido() + this.valor); //sumamos el valor de la casilla al atributo dineroInvertido del comprador

            // "sumar gastos"
            solicitante.setGastos(solicitante.getGastos() + this.valor);

            // "asignarle la propiedad"
            // Primero tenemos que cambiarle el dueño a la casilla
            setDuenho(solicitante);
            solicitante.getPropiedades().add(this); //esto es por casilla2
                
            // BANCA, aunque realmente es innecesario
            banca.setFortuna(banca.getFortuna() + this.valor);

            System.out.println("El jugador " + solicitante.getNombre() + " compra la casilla "+ this.getNombre() + " por " + this.valor);
            System.out.println("Su fortuna actual es:" + solicitante.getFortuna());
            }
        } else if (estaEnVenta()==false) {
            // En caso de que no sea de ninguno de estos tipos, la propiedad no se podrá
            // comprar
            System.out.println("Esta propiedad no se puede comprar, para poder comprar una propiedad debe de ser de uno de los siguientes tipos: SOLAR, TRANSPORTE, SERVICIOS\n");
        } else {
            System.out.println("No tienes dinero suficiente como para comprar esta propiedad\n");
        }
    }


    

    public float valorHipoteca(Propiedad propiedad){
        float valor = 0;
        if (propiedad instanceof Solar) {
            Solar solar = ((Solar) this);
            switch (solar.getGrupo().getColorGrupo()) {
                case "BLACK":
                    valor = 600000;
                    break;
                case "CYAN":
                    valor = 520000;
                    break;
                case "PURPLE":
                    valor = 676000;
                    break;
                case "WHITE":
                    valor = 878800;
                    break;
                case "GREEN":
                    valor = 1930723.6f;
                    break;
                case "BLUE":
                    valor = 3764911.02f;
                    break;
                case "RED":
                    valor = 1142440;
                    break;
                case "YELLOW":
                    valor = 1485172;
                    break;
                default:
                    System.out.println("No se ha encontrado el grupo");
                    break;
            }
        }else if (propiedad instanceof Transporte) {
            valor = ((propiedad.getValor())/2);
        }else if (propiedad instanceof Servicio){
            valor = ((propiedad.getValor())/2);
        }
        return valor;
    }



    public int Hacienda(Jugador jugador, Tablero tablero){

        if((!jugador.getPropiedades().isEmpty())){ //mientras el jugador tenga propiedades
    
            System.out.println("¿Qué casilla desea hipotecar?");
            Scanner scanner = new Scanner(System.in);
            String nombre = scanner.nextLine();
    
            if(tablero.encontrar_casilla(nombre) == null){ //verificamos que la casilla exista
                System.out.println("No se ha podido encontrar la casilla.\n");
                return 0;
            }else{
                for(Casilla casilla:jugador.getPropiedades()){ //verificamos que el jugador tenga la casilla comprada
                    if (casilla instanceof Propiedad) {
                        if(casilla.getNombre().equals(nombre)){
                            Propiedad propiedad = ((Propiedad)casilla);
                            if (propiedad instanceof Solar) {
                                Solar solar = ((Solar)propiedad);
                                    if(solar.getEdificacion().isEmpty()){
                                        if(!solar.getHipotecado()){
                                            jugador.setFortuna(jugador.getFortuna() + (valorHipoteca(solar)/2));
    
                                            hipotecarPropiedad(solar);
    
                                            System.out.println(String.format("La casilla %s ha sido hipotecada con éxito.\n", nombre));
    
                                            System.out.println(String.format("%s recibe %f por hipotecar %s. Ahora no puedes recibir alquileres ni edificar en la casilla %s.\n", jugador.getNombre(), valorHipoteca(solar)/2, casilla.getNombre(), casilla.getNombre()));
                            
                                            return 1;
                                        }else{
                                            System.out.println("La casilla ya ha sido hipotecada.\n");
                                        }
                                }else{
                                    System.out.println("La casilla tiene edificaciones, debes venderlas antes de poder hipotecar la casilla.\n");
                                    return 0;
                                }
                        }else{
                            if (!propiedad.getHipotecado()) {
                                jugador.setFortuna(jugador.getFortuna()+valorHipoteca(propiedad));    
                                hipotecarPropiedad(propiedad);
                                System.out.println(String.format("La casilla %s ha sido hipotecada con éxito.\n", nombre));
                                System.out.println(String.format("%s recibe %f por hipotecar %s. Ahora no puedes recibir alquileres ni edificar en la casilla %s.\n", jugador.getNombre(), valorHipoteca(propiedad), casilla.getNombre(), casilla.getNombre()));
                                return 1;
                            }else{
                                System.out.println("La casilla ya ha sido hipotecada");
                                return 0;
                            }
                        }
                        }
                    }
                    System.out.println(String.format("La casilla %s no pertenece al jugador %s.\n", nombre, jugador.getNombre()));
                    return 0;
                }
                return 0;
            }
        }else{
                System.out.println("No tienes más propiedades para hipotecar.\n");
                return 0;
            }
        }
    
    
    public int deshipotecar(Jugador jugador, Tablero tablero){
        System.out.println("¿Qué casilla desea deshipotecar?");
        Scanner scanner = new Scanner(System.in);
        String nombre = scanner.nextLine();
    
        if(tablero.encontrar_casilla(nombre) == null){
            System.out.println("No se ha podido encontrar la casilla.\n");
            return 0;
        }else{
            for(Casilla casilla:jugador.getPropiedades()){
                if(casilla.getNombre().equals(nombre)){
                    if (casilla instanceof Propiedad) {
                        Propiedad propiedad = ((Propiedad)casilla);
                        if (propiedad instanceof Solar) {
                            Solar solar = ((Solar)propiedad);
                                if(solar.getHipotecado()){
                                    if(jugador.getFortuna() > ((valorHipoteca(solar)/2)*1.1f)){
                                        jugador.setFortuna(jugador.getFortuna() - ((valorHipoteca(solar)/2)*1.1f));
                                        solar.setHipotecado(false);
                                        System.out.println(String.format("La casilla %s ha sido deshipotecada con éxito.\n", nombre));
                                        System.out.println(String.format("%s paga %f por deshipotecar %s. Ahora puedes recibir alquileres y edificar en la casilla %s.\n", jugador.getNombre(), ((valorHipoteca(solar)/2)*1.1f), casilla.getNombre(), casilla.getNombre()));
                                        return 1;
                                    }else{
                                        System.out.println(String.format("El jugador %s no tiene suficiente dinero para poder deshipotecar la casilla %s.\n", jugador.getNombre(), casilla.getNombre()));
                                        return 0;
                                    }
                                }else{
                                    System.out.println(String.format("La casilla %s no está hipotecada.\n", casilla.getNombre()));
                                    return 0;
                                }
                        }else{
                            if (propiedad.getHipotecado()) {
                                if (jugador.getFortuna()>((valorHipoteca(propiedad)*1.1))){
                                    jugador.setFortuna(jugador.getFortuna() - ((valorHipoteca(propiedad))*1.1f));
                                    propiedad.setHipotecado(false);
                                    System.out.println(String.format("La casilla %s ha sido deshipotecada con éxito.\n", nombre));
                                    System.out.println(String.format("%s paga %f por deshipotecar %s. Ahora puedes recibir alquileres y edificar en la casilla %s.\n", jugador.getNombre(), ((valorHipoteca(propiedad))*1.1f), casilla.getNombre(), casilla.getNombre()));
                                    return 1;
                                }else{
                                    System.out.println(String.format("El jugador %s no tiene suficiente dinero para poder deshipotecar la casilla %s.\n", jugador.getNombre(), casilla.getNombre()));
                                    return 0;
                                }
                            }else{
                                System.out.println(String.format("La casilla %s no está hipotecada.\n", casilla.getNombre()));
                                return 0;
                                }
                            }
                        }else{
                            System.out.println("Para hipotecar uan propiedad, esta ha de ser un Solar, un Servicio o un Transporte");
                            return 0;
                        }
                }
                System.out.println(String.format("La casilla %s no pertenece al jugador %s.\n", nombre, jugador.getNombre()));
                return 0;
            }
            return 0;
        }
    }




}
