package monopoly;

import java.util.Scanner;

public class ConsolaNormal implements Consola{

    public String leer(){
        Scanner scanner = new Scanner(System.in);
        String mensaje = scanner.nextLine();

        return mensaje;
    }

    public void imprimir(String mensaje){
        System.out.println(mensaje);
    }
}