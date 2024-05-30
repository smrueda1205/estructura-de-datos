import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private String contrasena;
    private int horasLibres;
    private List<String> eventosInscritos;

    public Estudiante(String id, String nombre, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.horasLibres = 0;
        this.eventosInscritos = new ArrayList<>();
    }

    public List<String> getEventosInscritos() {
        return eventosInscritos;
    }

    public void setEventosInscritos(List<String> eventosInscritos) {
        this.eventosInscritos = eventosInscritos;
    }

    public void inscribirEnEvento(String nombreEvento) {
        if (this.eventosInscritos == null) {
            this.eventosInscritos = new ArrayList<>();
        }
        if (!eventosInscritos.contains(nombreEvento)) {
            eventosInscritos.add(nombreEvento);
            JOptionPane.showMessageDialog(null, "Inscripción en el evento '" + nombreEvento + "' exitosa.");
        } else {
            JOptionPane.showMessageDialog(null,"Ya estás inscrito en este evento.");
        }
    }

    public void darDeBajaEvento(String nombreEvento) {
        eventosInscritos.remove(nombreEvento);
    }

    public void agregarHorasLibres(int horas) {
        horasLibres+=horas;
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

    public String obtenerEventosInscritos() {
        if (eventosInscritos.isEmpty()) {
            return "No está inscrito en ningún evento.";
        } else {
            StringBuilder sb = new StringBuilder("Eventos inscritos:\n");
            for (String evento : eventosInscritos) {
                sb.append("- ").append(evento).append("\n");
            }
            return sb.toString();
        }
    }



}