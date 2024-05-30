import java.io.Serializable;

public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String cantHoras;

    public Evento(String nombre, String cantHoras) {
        this.nombre = nombre;
        this.cantHoras = cantHoras;
    }

    // Getters y setters para nombre y cantHoras...
    public String getNombre() {
        return nombre;
    }

    // Setter para el nombre del evento
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para la cantidad de horas del evento
    public String getCantHoras() {
        return cantHoras;
    }

    // Setter para la cantidad de horas del evento
    public void setCantHoras(String cantHoras) {
        this.cantHoras = cantHoras;
    }
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Horas: " + cantHoras;
    }
}





