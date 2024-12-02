package monopoly;

import java.util.Scanner;

public class ConsolaNormal implements Consola{

    Scanner scan;

    public ConsolaNormal(){

        this.scan = new Scanner(System.in);
    }

    public String leer(){
        String mensaje = this.scan.nextLine();

        return mensaje;
    }

    public void imprimir(String mensaje){
        System.out.println(mensaje);
    }

    public void close(){
        this.scan.close();
    }
    
}