package monopoly;

import java.util.ArrayList;
import java.util.Scanner;

import partida.Jugador;

public class Trato {

    public static ConsolaNormal consola = new ConsolaNormal();
    private Jugador jugadorOfrece;
    private Jugador jugadorRecibe;
    private Propiedad aCambiar;
    private Propiedad aRecibir;
    private float fortunaACambiar = 0;
    private float fortunaARecibir = 0;
    private int tipoTrato;
    private boolean aceptado = false;
    private int id = 0;

    public Jugador getJugadorOfrece(){
        return jugadorOfrece;
    }

    public void setJugadorOfrece(Jugador jugadorOfrece){
        this.jugadorOfrece = jugadorOfrece;
    }

    public Jugador getJugadorRecibe(){
        return jugadorRecibe;
    }

    public void setJugadorRecibe(Jugador jugadorRecibe){
        this.jugadorRecibe = jugadorRecibe;
    }

    public Propiedad getACambiar(){
        return aCambiar;
    }

    public void setACambiar(Propiedad aCambiar){
        this.aCambiar = aCambiar;
    }

    public Propiedad getARecibir(){
        return aRecibir;
    }

    public void setARecibir(Propiedad aRecibir){
        this.aRecibir = aRecibir;
    }

    public float getFortunaACambiar(){
        return fortunaACambiar;
    }

    public void setFortunaACambiar(float fortunaACambiar){
        this.fortunaACambiar = fortunaACambiar;
    }

    public float getFortunaARecibir(){
        return fortunaARecibir;
    }

    public void setFortunaARecibir(float fortunaARecibir){
        this.fortunaARecibir = fortunaARecibir;
    }

    public int getTipoTrato(){
        return tipoTrato;
    }

    public void setTipoTrato(int tipoTrato){
        this.tipoTrato = tipoTrato;
    }

    public boolean getAceptado(){
        return aceptado;
    }

    public void setAceptado(boolean aceptado){
        this.aceptado = aceptado;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

public void trucoOTrato(Jugador actual, Jugador banca, Juego juego){
    Jugador recibidor = buscarJugador(juego);

    if(recibidor == null){
        consola.imprimir("El jugador no existe.\n");
    }else{
        consola.imprimir("Elija el modo de trato:\n\n");
        consola.imprimir("1. Cambiar <propiedad1> por <propiedad2>                                       -> Comando: 'cambiar 1'\n");
        consola.imprimir("2. Cambiar <propiedad1> por <cantidad_dinero>                                  -> Comando: 'cambiar 2'\n");
        consola.imprimir("3. Cambiar <cantidad_dinero> por <propiedad1>                                  -> Comando: 'cambiar 3'\n");
        consola.imprimir("4. Cambiar <propiedad1> por <propiedad2> y <cantidad_dinero>                   -> Comando: 'cambiar 4'\n");
        consola.imprimir("5. Cambiar <propiedad1> y <cantidad_dinero> por <propiedad2>                   -> Comando: 'cambiar 5'\n\n");

        consola.imprimir("=====================================\n");
        consola.imprimir("Selecciona una opción para continuar.\n");
        consola.imprimir("=====================================\n\n");

        Scanner scanner = new Scanner(System.in);
        String comando = scanner.nextLine();
        String opcion[] = comando.split(" ");

        consola.imprimir(opcion[1]);

        switch(opcion[1]){
            case "1":
                cambiar1(actual, banca, recibidor, juego);
                break;
            case "2":
                cambiar2(actual, banca, recibidor, juego);
                break;
            case "3":
                cambiar3(actual, banca, recibidor, juego);
                break;
            case "4":
                cambiar4(actual, banca, recibidor, juego);
                break;
            case "5":
                cambiar5(actual, banca, recibidor, juego);
                break;
            default:
                consola.imprimir("Modo de trato desconocido.\n");
        }
    scanner.close();
    }
}

public void cambiar1(Jugador actual, Jugador banca, Jugador recibidor, Juego juego){
    Scanner scanner = new Scanner(System.in);
    boolean mensajeRec = true;
    boolean mensajeCam = true;

    consola.imprimir("Escriba la casilla que desea cambiar:");
    String casillaCambiar = scanner.nextLine();

    for(Propiedad propiedad:actual.getPropiedades()){
        if(propiedad.getNombre().equals(casillaCambiar)){
            this.aCambiar = propiedad;
            mensajeCam = false;

            consola.imprimir("Escriba la casilla que desea recibir:");
            String casillaRecibir = scanner.nextLine();

            for(Propiedad Propiedad:recibidor.getPropiedades()){
                if(Propiedad.getNombre().equals(casillaRecibir)){
                    this.aRecibir = Propiedad;
                    
                    this.tipoTrato = 1;
                    this.jugadorOfrece = actual;
                    this.jugadorRecibe = recibidor;
                    this.id = id++;

                    actual.getTratosOfrecidos().add(this);
                    recibidor.getTratosRecibidos().add(this); //añadimos el trato al array de tratos que tiene cada jugador
                    juego.getTratosTotales().add(this);

                    consola.imprimir(String.format("Trato enviado con éxito, esperando respuesta del jugador %s.\n", recibidor.getNombre()));
                    mensajeRec = false;
                    break;
                }
            }
            break;
        }
    }

    if(mensajeCam){
        consola.imprimir(String.format("El jugador %s no es dueño de la casilla.\n", actual.getNombre()));
    }

    if(mensajeRec){
        consola.imprimir(String.format("El jugador %s no es dueño de la casilla.\n", recibidor.getNombre()));
    }
    scanner.close();
}

public void cambiar2(Jugador actual, Jugador banca, Jugador recibidor, Juego juego){
    Scanner scanner = new Scanner(System.in);
    boolean mensaje = true;

    consola.imprimir("Escriba la casilla que desea cambiar:");
    String casillaCambiar = scanner.nextLine();

    for(Propiedad propiedad:actual.getPropiedades()){
        if(propiedad.getNombre().equals(casillaCambiar)){
            this.aCambiar = propiedad;
            
            consola.imprimir("Escriba la cantidad de dinero que desea recibir:");
            this.fortunaARecibir = scanner.nextFloat();
            
            this.tipoTrato = 2;
            this.jugadorOfrece = actual;
            this.jugadorRecibe = recibidor;
            this.id = id++;

            actual.getTratosOfrecidos().add(this);
            recibidor.getTratosRecibidos().add(this); //añadimos el trato al array de tratos que tiene cada jugador
            juego.getTratosTotales().add(this);
                    
            consola.imprimir(String.format("Trato enviado con éxito, esperando respuesta del jugador %s.\n", recibidor.getNombre()));
            mensaje = false;
            break;
        }
    }

    if(mensaje){
        consola.imprimir(String.format("El jugador %s no es dueño de la casilla %s.\n", actual.getNombre(), casillaCambiar));
    }
    scanner.close();
}

public void cambiar3(Jugador actual, Jugador banca, Jugador recibidor, Juego juego){
    Scanner scanner = new Scanner(System.in);
    boolean mensaje = true;

    consola.imprimir("Escriba la cantidad de dinero que desea cambiar:");
    this.fortunaACambiar = scanner.nextFloat();

    consola.imprimir("Escriba la casilla que desea recibir:");
    String casillaRecibir = scanner.nextLine();

    for(Propiedad propiedad:recibidor.getPropiedades()){
        if(propiedad.getNombre().equals(casillaRecibir)){
            this.aRecibir = propiedad;

            this.tipoTrato = 3;
            this.jugadorOfrece = actual;
            this.jugadorRecibe = recibidor;
            this.id = id++;

            actual.getTratosOfrecidos().add(this);
            recibidor.getTratosRecibidos().add(this); //añadimos el trato al array de tratos que tiene cada jugador
            juego.getTratosTotales().add(this);

            consola.imprimir(String.format("Trato enviado con éxito, esperando respuesta del jugador %s.\n", recibidor.getNombre()));
            mensaje = false;
            break;
        }
    }

    if(mensaje){
        consola.imprimir(String.format("El jugador %s no es dueño de la casilla %s.\n", recibidor.getNombre(), casillaRecibir));
    }
    scanner.close();
}

public void cambiar4(Jugador actual, Jugador banca, Jugador recibidor, Juego juego){
    Scanner scanner = new Scanner(System.in);
    boolean mensajeCam = false;
    boolean mensajeRec = false;

    consola.imprimir("Escriba la casilla que desea cambiar:");
    String casillaCambiar = scanner.nextLine();

    for(Propiedad propiedad:actual.getPropiedades()){
        if(propiedad.getNombre().equals(casillaCambiar)){
            this.aCambiar = propiedad;
            mensajeCam = true;

            consola.imprimir("Escriba la casilla que desea recibir:");
            String casillaRecibir = scanner.nextLine();

            for(Propiedad Propiedad:recibidor.getPropiedades()){
                if(Propiedad.getNombre().equals(casillaRecibir)){
                    this.aRecibir = Propiedad;

                    consola.imprimir("Escriba la cantidad de dinero que desea recibir:");
                    this.fortunaARecibir = scanner.nextFloat();
                    
                    this.tipoTrato = 4;
                    this.jugadorOfrece = actual;
                    this.jugadorRecibe = recibidor;
                    this.id = id++;

                    actual.getTratosOfrecidos().add(this);
                    recibidor.getTratosRecibidos().add(this); //añadimos el trato al array de tratos que tiene cada jugador
                    juego.getTratosTotales().add(this);

                    consola.imprimir(String.format("Trato enviado con éxito, esperando respuesta del jugador %s.\n", recibidor.getNombre()));
                    mensajeRec = true;
                    break;
                }
            }
            break;
        }
    }

    if(mensajeCam){
        consola.imprimir(String.format("El jugador %s no es dueño de la casilla.\n", actual.getNombre()));
    }

    if(mensajeRec){
        consola.imprimir(String.format("El jugador %s no es dueño de la casilla.\n", recibidor.getNombre()));
    }
    scanner.close();
}

public void cambiar5(Jugador actual, Jugador banca, Jugador recibidor, Juego juego){
    Scanner scanner = new Scanner(System.in);
    boolean mensajeCam = true;
    boolean mensajeRec = true;

    consola.imprimir("Escriba la casilla que desea cambiar:");
    String casillaCambiar = scanner.nextLine();

    for(Propiedad propiedad:actual.getPropiedades()){
        if(propiedad.getNombre().equals(casillaCambiar)){
            this.aCambiar = propiedad;
            mensajeCam = false;
            
            consola.imprimir("Escriba la cantidad de dinero que desea cambiar:");
            this.fortunaACambiar = scanner.nextFloat();

            consola.imprimir("Escriba la casilla que desea recibir:");
            String casillaRecibir = scanner.nextLine();

            for(Propiedad Propiedad:recibidor.getPropiedades()){
                if(Propiedad.getNombre().equals(casillaRecibir)){
                    this.aRecibir = Propiedad;

                    this.tipoTrato = 5;
                    this.jugadorOfrece = actual;
                    this.jugadorRecibe = recibidor;
                    this.id = id++;

                    actual.getTratosOfrecidos().add(this);
                    recibidor.getTratosRecibidos().add(this); //añadimos el trato al array de tratos que tiene cada jugador
                    juego.getTratosTotales().add(this);
                    
                    consola.imprimir(String.format("Trato enviado con éxito, esperando respuesta del jugador %s.\n", recibidor.getNombre()));
                    mensajeRec = false;
                    break;
                }
            }
            break;
        }
    }

    if(mensajeCam){
        consola.imprimir(String.format("El jugador %s no es dueño de la casilla.\n", actual.getNombre()));
    }

    if(mensajeRec){
        consola.imprimir(String.format("El jugador %s no es dueño de la casilla.\n", recibidor.getNombre()));
    }
    scanner.close();
}

public Jugador buscarJugador(Juego juego){
    Scanner scanner = new Scanner(System.in);

    consola.imprimir("Escriba el jugador al que le quiere proponer un trato:");
    String jugadorABuscar = scanner.nextLine();

    for(Jugador jugador:juego.getJugadores()){
        if(jugador.getNombre().equals(jugadorABuscar)){
            scanner.close();

            return jugador;
        }
    }
    scanner.close();

    return null;
}

public void manejarTrato(Trato trato, Jugador recibidor){
    Jugador ofreceTrato = trato.getJugadorOfrece();
    Jugador recibeTrato = trato.getJugadorRecibe();

    int tipoTrato = trato.getTipoTrato();

    switch(tipoTrato){
        case 1:
            casoPrimero(trato, ofreceTrato, recibeTrato);
            break;
        case 2:
            casoSegundo(trato, ofreceTrato, recibeTrato);
            break;
        case 3:
            casoTercero(trato, ofreceTrato, recibeTrato);
            break;
        case 4:
            casoCuarto(trato, ofreceTrato, recibeTrato);
            break;
        case 5:
            casoQuinto(trato, ofreceTrato, recibeTrato);
            break;
        default:
            consola.imprimir("Tipo de trato desconocido.\n");
    }
}

public void casoPrimero(Trato trato, Jugador ofreceTrato, Jugador recibeTrato){
    Scanner scanner = new Scanner(System.in);
    consola.imprimir(String.format("Trato %s: %s te doy %s y recibo %s.\n", ofreceTrato.getNombre(), recibeTrato.getNombre(), trato.getACambiar().getNombre(), trato.getARecibir().getNombre()));

    consola.imprimir("Aceptas o rechazas? (acepto, rechazo)");
    String respuesta = scanner.nextLine();

    while((!respuesta.equals("acepto")) && (!respuesta.equals("rechazo"))){
        consola.imprimir("Comando desconocido.\n");

        consola.imprimir("Aceptas o rechazas? (acepto, rechazo)");
        respuesta = scanner.nextLine();
    }

    if(respuesta.equals("acepto")){
        if(trato.aCambiar.getHipotecado()){
            consola.imprimir(String.format("Tener en cuenta que la casilla %s está hipotecada.\n", trato.getACambiar().getNombre()));
        }
    
        if(trato.aRecibir.getHipotecado()){
            consola.imprimir(String.format("Tener en cuenta que la casilla %s está hipotecada.\n", trato.getARecibir().getNombre()));
        }
    }

    String respuestaConfirmacion;
    
    consola.imprimir("Aceptas o rechazas? (acepto, rechazo)");
    respuestaConfirmacion = scanner.nextLine();

    while((!respuestaConfirmacion.equals("acepto")) && (!respuestaConfirmacion.equals("rechazo"))){
        consola.imprimir("Comando desconocido.\n");

        consola.imprimir("Aceptas o rechazas? (acepto, rechazo)");
        respuestaConfirmacion = scanner.nextLine();
    }

    if(respuestaConfirmacion.equals("acepto")){
        //cambiamos la casilla a cambiar de dueño
        ofreceTrato.getPropiedades().remove(trato.getACambiar());
        trato.getACambiar().setDuenho(recibeTrato);
        recibeTrato.getPropiedades().add(trato.getACambiar());

        //cambiamos la casilla a recibir de dueño
        recibeTrato.getPropiedades().remove(trato.aRecibir);
        trato.getARecibir().setDuenho(ofreceTrato);
        ofreceTrato.getPropiedades().add(trato.getARecibir());

        this.aceptado = true;
    }else{
        consola.imprimir("Trato rechazado.\n");
        //no se elimina
    }
    scanner.close();
}

public void casoSegundo(Trato trato, Jugador ofreceTrato, Jugador recibeTrato){
    Scanner scanner = new Scanner(System.in);
    consola.imprimir(String.format("Trato %s: %s te doy %s y recibo %d.\n", ofreceTrato.getNombre(), recibeTrato.getNombre(), trato.getACambiar().getNombre(), trato.getFortunaARecibir()));

    consola.imprimir("Aceptas o rechazas? (acepto, rechazo)");
    String respuesta = scanner.nextLine();

    while((!respuesta.equals("acepto")) && (!respuesta.equals("rechazo"))){
        consola.imprimir("Comando desconocido.\n");

        consola.imprimir("Aceptas o rechazas? (acepto, rechazo)");
        respuesta = scanner.nextLine();
    }

    if(respuesta.equals("acepto")){
        if(trato.aCambiar.getHipotecado()){
            consola.imprimir(String.format("Tener en cuenta que la casilla %s está hipotecada.\n", trato.getACambiar().getNombre()));
        }
    }

    String respuestaConfirmacion;

    consola.imprimir("Aceptas o rechazas? (acepto, rechazo)");
    respuestaConfirmacion = scanner.nextLine();

    while((!respuestaConfirmacion.equals("acepto")) && (!respuestaConfirmacion.equals("rechazo"))){
        consola.imprimir("Comando desconocido.\n");

        consola.imprimir("Aceptas o rechazas? (acepto, rechazo)");
        respuestaConfirmacion = scanner.nextLine();
    }

    if(respuestaConfirmacion.equals("acepto")){
        //sumamos la fortuna
        ofreceTrato.setFortuna(ofreceTrato.getFortuna() + trato.getFortunaARecibir());

        //cambiamos de dueño la casilla
        ofreceTrato.getPropiedades().remove(trato.aCambiar);
        trato.aCambiar.setDuenho(recibeTrato);
        recibeTrato.getPropiedades().add(trato.aRecibir);

        //restamos la fortuna
        recibeTrato.setFortuna(recibeTrato.getFortuna() - trato.getFortunaACambiar());

        this.aceptado = true;
    }else{
        consola.imprimir("Trato rechazado.\n");
        //no se elimina
    }
    scanner.close();
}

public void casoTercero(Trato trato, Jugador ofreceTrato, Jugador recibeTrato){
    Scanner scanner = new Scanner(System.in);
    consola.imprimir(String.format("Trato %s: %s te doy %d y recibo %s.\n", ofreceTrato.getNombre(), recibeTrato.getNombre(), trato.getFortunaARecibir(), trato.getARecibir().getNombre()));

    consola.imprimir("Aceptas o rechazas? (acepto, rechazo)");
    String respuesta = scanner.nextLine();

    while((!respuesta.equals("acepto")) && (!respuesta.equals("rechazo"))){
        consola.imprimir("Comando desconocido.\n");

        consola.imprimir("Aceptas o rechazas? (acepto, rechazo)");
        respuesta = scanner.nextLine();
    }

    if(respuesta.equals("acepto")){
        if(trato.aRecibir.getHipotecado()){
            consola.imprimir(String.format("Tener en cuenta que la casilla %s está hipotecada.\n", trato.getARecibir().getNombre()));
        }
    }

    String respuestaConfirmacion;

    consola.imprimir("Aceptas o rechazas? (acepto, rechazo)");
    respuestaConfirmacion = scanner.nextLine();

    while((!respuestaConfirmacion.equals("acepto")) && (!respuestaConfirmacion.equals("rechazo"))){
        consola.imprimir("Comando desconocido.\n");

        consola.imprimir("Aceptas o rechazas? (acepto, rechazo)");
        respuestaConfirmacion = scanner.nextLine();
    }

    if(respuestaConfirmacion.equals("acepto")){
        //restamos la fortuna
        ofreceTrato.setFortuna(ofreceTrato.getFortuna() - trato.getFortunaARecibir());
        
        //cambiamos de dueño la casilla
        recibeTrato.getPropiedades().remove(trato.aRecibir);
        trato.aRecibir.setDuenho(ofreceTrato);
        ofreceTrato.getPropiedades().add(trato.aRecibir);

        //sumamos la fortuna
        recibeTrato.setFortuna(recibeTrato.getFortuna() + trato.getFortunaACambiar());

        this.aceptado = true;
    }else{
        consola.imprimir("Trato rechazado.\n");
        //no se elimina
    }
    scanner.close();
}

public void casoCuarto(Trato trato, Jugador ofreceTrato, Jugador recibeTrato){
    Scanner scanner = new Scanner(System.in);
    consola.imprimir(String.format("Trato %s: %s te doy %s y recibo %s y %d.\n", ofreceTrato.getNombre(), recibeTrato.getNombre(), trato.getACambiar().getNombre(), trato.getARecibir().getNombre(), trato.getFortunaARecibir()));

    consola.imprimir("Aceptas o rechazas? (acepto, rechazo)");
    String respuesta = scanner.nextLine();

    while((!respuesta.equals("acepto")) && (!respuesta.equals("rechazo"))){
        consola.imprimir("Comando desconocido.\n");

        consola.imprimir("Aceptas o rechazas? (acepto, rechazo)");
        respuesta = scanner.nextLine();
    }

    if(respuesta.equals("acepto")){
        if(trato.aCambiar.getHipotecado()){
            consola.imprimir(String.format("Tener en cuenta que la casilla %s está hipotecada.\n", trato.getACambiar().getNombre()));
        }
    
        if(trato.aRecibir.getHipotecado()){
            consola.imprimir(String.format("Tener en cuenta que la casilla %s está hipotecada.\n", trato.getARecibir().getNombre()));
        }
    }

    String respuestaConfirmacion;

    consola.imprimir("Estás seguro de querer aceptar? (acepto, rechazo)");
    respuestaConfirmacion = scanner.nextLine();

    while((!respuestaConfirmacion.equals("acepto")) && (!respuestaConfirmacion.equals("rechazo"))){
        consola.imprimir("Comando desconocido.\n");

        consola.imprimir("Aceptas o rechazas? (acepto, rechazo)");
        respuestaConfirmacion = scanner.nextLine();
    }

    if(respuestaConfirmacion.equals("acepto")){
        //sumamos la fortuna
        ofreceTrato.setFortuna(ofreceTrato.getFortuna() + trato.getFortunaARecibir());

        //cambiamos la casilla a cambiar de dueño
        ofreceTrato.getPropiedades().remove(trato.getACambiar());
        trato.getACambiar().setDuenho(recibeTrato);
        recibeTrato.getPropiedades().add(trato.getACambiar());

        //cambiamos la casilla a recibir de dueño
        recibeTrato.getPropiedades().remove(trato.aRecibir);
        trato.getARecibir().setDuenho(ofreceTrato);
        ofreceTrato.getPropiedades().add(trato.getARecibir());

        //restamos la fortuna
        recibeTrato.setFortuna(recibeTrato.getFortuna() - trato.getFortunaACambiar());

        this.aceptado = true;
    }else{
        consola.imprimir("Trato rechazado.\n");
        //no se elimina
    }
    scanner.close();
}

public void casoQuinto(Trato trato, Jugador ofreceTrato, Jugador recibeTrato){
    Scanner scanner = new Scanner(System.in);
    consola.imprimir(String.format("Trato %s: %s te doy %s y %d y recibo %s.\n", ofreceTrato.getNombre(), recibeTrato.getNombre(), trato.getACambiar().getNombre(), trato.getFortunaACambiar(), trato.getARecibir().getNombre()));

    consola.imprimir("Aceptas o rechazas? (acepto, rechazo)");
    String respuesta = scanner.nextLine();

    while((!respuesta.equals("acepto")) && (!respuesta.equals("rechazo"))){
        consola.imprimir("Comando desconocido.\n");

        consola.imprimir("Aceptas o rechazas? (acepto, rechazo)");
        respuesta = scanner.nextLine();
    }

    if(respuesta.equals("acepto")){
        if(trato.aCambiar.getHipotecado()){
            consola.imprimir(String.format("Tener en cuenta que la casilla %s está hipotecada.\n", trato.getACambiar().getNombre()));
        }
    
        if(trato.aRecibir.getHipotecado()){
            consola.imprimir(String.format("Tener en cuenta que la casilla %s está hipotecada.\n", trato.getARecibir().getNombre()));
        }
    }

    String respuestaConfirmacion;

    consola.imprimir("Estás seguro de querer aceptar? (acepto, rechazo)");
    respuestaConfirmacion = scanner.nextLine();

    while((!respuestaConfirmacion.equals("acepto")) && (!respuestaConfirmacion.equals("rechazo"))){
        consola.imprimir("Comando desconocido.\n");

        consola.imprimir("Aceptas o rechazas? (acepto, rechazo)");
        respuestaConfirmacion = scanner.nextLine();
    }

    if(respuestaConfirmacion.equals("acepto")){
        //restamos la fortuna
        ofreceTrato.setFortuna(ofreceTrato.getFortuna() - trato.getFortunaARecibir());

        //cambiamos la casilla a cambiar de dueño
        ofreceTrato.getPropiedades().remove(trato.getACambiar());
        trato.getACambiar().setDuenho(recibeTrato);
        recibeTrato.getPropiedades().add(trato.getACambiar());

        //cambiamos la casilla a recibir de dueño
        recibeTrato.getPropiedades().remove(trato.aRecibir);
        trato.getARecibir().setDuenho(ofreceTrato);
        ofreceTrato.getPropiedades().add(trato.getARecibir());

        //sumamos la fortuna
        recibeTrato.setFortuna(recibeTrato.getFortuna() + trato.getFortunaACambiar());

        this.aceptado = true;
    }else{
        consola.imprimir("Trato rechazado.\n");
        //no se elimina
    }
    scanner.close();
}

public void listarTratos(Jugador jugador){
    StringBuilder sb = new StringBuilder();
    for(Trato trato:jugador.getTratosRecibidos()){
        sb.append(String.format("{\n  id: trato%d", trato.getId()));
        sb.append(String.format("\n   jugadorPropone: %s", trato.getJugadorOfrece().getNombre()));
        sb.append(String.format("\n   trato: cambiar (%s, %s)", trato.getACambiar().getNombre(), trato.getARecibir().getNombre()));
        sb.append("\n},\n");
    }
    consola.imprimir(sb.toString());
}

}