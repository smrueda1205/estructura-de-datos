import java.io.Serializable;
import java.util.ArrayList;

public class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private String contrasena;
    private int horasLibres;
    private ArrayList<String> eventosInscritos;

    public Estudiante(String id, String nombre, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.horasLibres = 0;
        this.eventosInscritos = new ArrayList<>();
    }

    public ArrayList<String> getEventosInscritos() {
        return eventosInscritos;
    }

    public void setEventosInscritos(ArrayList<String> eventosInscritos) {
        this.eventosInscritos = eventosInscritos;
    }

    public void inscribirEnEvento(String nombreEvento) {
        eventosInscritos.add(nombreEvento);
    }

    public void darDeBajaEvento(String nombreEvento) {
        eventosInscritos.remove(nombreEvento);
    }

    public void agregarHorasLibres(int horas) {
        horasLibres+=horas;
    }

    public void eliminarHorasLibres(int indice) {
            horasLibres-=indice;
    }


    // Getters y setters para id, nombre y contraseña
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // Getters y setters para horasLibres
    public int getHorasLibres() {
        return horasLibres;
    }

    public void setHorasLibres(int horasLibres) {
        this.horasLibres = horasLibres;
    }



}