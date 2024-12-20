package monopoly;

public class Edificacion {

    // Atributos
    private Solar solar;
    private String nombre;
    private int id;
    private static int ultimoId = 0; // Variable estática para el último ID generado

    public static ConsolaNormal consola = new ConsolaNormal();

    public Edificacion(Solar solar) {
        this.solar = solar;
        this.id = generarId(); // Generar el ID al crear la edificación
    }

    // Método para generar un ID único
    private int generarId() {
        return ++ultimoId; // Incrementa y retorna el nuevo ID
    }

    // Getters y setters
    public Solar getSolar() {
        return this.solar;
    }

    public void setSolar(Solar solar) {
        this.solar = solar;
    }

    public int getId() {
        return this.id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return this.nombre;
    }
}