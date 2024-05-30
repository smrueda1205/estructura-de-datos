import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.List;

public class Main {
    public static ArrayList<Estudiante> Estudiantes = new ArrayList<>();
    public static String fichero = "estudiantes.dat";

    public static ArrayList<Evento> Eventos = new ArrayList<>();
    public static String eventoFile = "eventos.dat";

    public static void main(String[] args) {
        cargarUsuarios();
        cargarEventos();

        String inicio = JOptionPane.showInputDialog(null, "Hola usuario.\nSi desea registrarse escriba 1. Si desea ingresar, escriba 2.");

        switch (inicio) {
            case "1":
                registrarUsuario();
                break;
            case "2":
                Estudiante estudianteLogueado = login();
                if (estudianteLogueado != null) {
                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso. Bienvenido, " + estudianteLogueado.getNombre() + "!");

                    // Después del inicio de sesión, ofrecer las opciones
                    mostrarMenu(estudianteLogueado);
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas. Por favor, inténtelo de nuevo.");
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
                break;
        }
    }

    public static void mostrarMenu(Estudiante estudiante) {
        String mensaje = "¿Qué deseas hacer?\n";
        mensaje += "1. Ver horas libres\n";
        mensaje += "2. Agregar horas libres\n";
        mensaje += "3. Eliminar horas libres\n";
        mensaje += "4. Ver promedio de horas libres por semana\n";
        mensaje += "5. Ver promedio de horas libres por mes\n";
        mensaje += "6. Ver promedio de horas libres por año\n";
        mensaje += "7. Ver promedio de horas libres por semestre\n";
        mensaje += "8. Ver horas libres que faltan\n";
        mensaje += "9. Ver Eventos Disponibles\n";
        mensaje += "10. Crear un nuevo evento\n";
        mensaje += "11. Dar de baja un evento\n";
        mensaje += "12. Eliminar eventos\n";
        mensaje += "13. Inscribirse a eventos\n";
        mensaje += "14. Ver Eventos Inscritos\n";
        mensaje += "15. Salir de la aplicación\n";

        mensaje += "Por favor, ingresa el número de tu elección:";

        while (true) {
            String opcion = JOptionPane.showInputDialog(null, mensaje, "Menú Principal", JOptionPane.QUESTION_MESSAGE);

            switch (opcion) {
                case "1":
                    verHorasLibres(estudiante);
                    break;
                case "2":
                    agregarHorasLibres(estudiante);
                    guardarUsuarios();
                    break;
                case "3":
                    // Lógica para eliminar horas libres

                    eliminarHorasLibres(estudiante);
                    guardarUsuarios();
                    break;
                case "4":

                    JOptionPane.showMessageDialog(null,"A la semana realiza : " + (double)estudiante.getHorasLibres()/168 + " horas");
                    break;


                case "5":
                    JOptionPane.showMessageDialog(null,"Al mes realiza : " + (double)estudiante.getHorasLibres()/730 + " horas");
                    break;
                case "6":
                    JOptionPane.showMessageDialog(null,"Al año realiza : " + (double)estudiante.getHorasLibres()/8760 + " horas");

                    break;
                case "7":
                    JOptionPane.showMessageDialog(null,"Al semestre: " + (double)estudiante.getHorasLibres()/4380 + " horas");

                    break;
                case "8":
                    if(estudiante.getHorasLibres() < 90){
                        JOptionPane.showMessageDialog(null,"Para graduarte necesitas " +  (90 - (double)estudiante.getHorasLibres() ) + " horas");

                    }else{
                        JOptionPane.showMessageDialog(null,"Ya completaste las horas");
                    }

                    break;

                case "15":
                    JOptionPane.showMessageDialog(null,"Hasta pronto");
                    System.exit(0);
                case "9":
                    mostrarEventos();

                    break;
                case "10":
                    agregarEvento();
                    guardarEventos();

                    break;
                case "11":
                    darDeBajaEvento(estudiante);
                    guardarUsuarios();
                    break;
                case "12":
                    eliminarEvento();

                    guardarEventos();

                    break;

                case "13":
                    inscribirEnEvento(estudiante);
                    guardarUsuarios();
                    break;
                    case "14":
                    mostrarEventosInscritos(estudiante);

                    break;

                default:
                    JOptionPane.showMessageDialog(null, "No es una opción válida");
                    break;
            }
        }
    }

    public static void verHorasLibres(Estudiante estudiante) {
        // Obtener la lista de horas libres del estudiante
        int horasLibres = estudiante.getHorasLibres();

        // Mostrar las horas libres al usuario
        JOptionPane.showMessageDialog(null, "Horas Libres:\n" + horasLibres);
    }



    public static void eliminarHorasLibres(Estudiante estudiante) {
        int totalHoras = estudiante.getHorasLibres(); // Obtener el total de horas libres del estudiante
        if (totalHoras > 0) {
            int horasARestar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de horas libres a eliminar:"));
            if (horasARestar <= totalHoras) {
                // Restar las horas libres
                estudiante.setHorasLibres(totalHoras - horasARestar);
                JOptionPane.showMessageDialog(null, "Horas libres eliminadas correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No hay suficientes horas libres para eliminar.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "El estudiante no tiene horas libres para eliminar.");
        }
    }


    public static void agregarHorasLibres(Estudiante estudiante) {
        int nuevasHoras = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de horas libres a agregar:"));
        estudiante.agregarHorasLibres(nuevasHoras);
        JOptionPane.showMessageDialog(null, "Horas libres agregadas correctamente.");
    }

    public static void registrarUsuario() {
        String id = JOptionPane.showInputDialog("Ingrese su ID:");
        String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
        String contrasena = JOptionPane.showInputDialog("Ingrese su contraseña:");

        Estudiante nuevoEstudiante = new Estudiante(id, nombre, contrasena);
        Estudiantes.add(nuevoEstudiante);

        guardarUsuarios(); // Guardar la lista de estudiantes actualizada en el archivo
    }

    public static void guardarUsuarios() {
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
            for (Estudiante estudiante : Estudiantes) {
                salida.writeObject(estudiante);
            }
            salida.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarEventosInscritos(Estudiante estudiante) {
        String eventosInscritos = estudiante.obtenerEventosInscritos();
        JOptionPane.showMessageDialog(null, eventosInscritos, "Eventos Inscritos", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void cargarEventos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(eventoFile))) {
            Eventos = (ArrayList<Evento>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de eventos.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Estudiante> cargarUsuarios() {
        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero));
            Object aux;
            while ((aux = ois.readObject()) != null) {
                if (aux instanceof Estudiante) {
                    Estudiante estudiante = (Estudiante) aux;
                    // Inicializamos eventosInscritos si es null
                    if (estudiante.getEventosInscritos() == null) {
                        estudiante.setEventosInscritos(new ArrayList<String>());
                    }
                    Estudiantes.add(estudiante);
                }
            }
            ois.close();
        } catch (EOFException e) {
            // End of file reached
        } catch (FileNotFoundException e) {
            try {
                File archivo = new File(fichero);
                archivo.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return estudiantes;
    }

    public static void eliminarEvento() {
        if (Eventos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay eventos para eliminar.");
            return;
        }

        String nombreEvento = JOptionPane.showInputDialog("Ingrese el nombre del evento a eliminar:");
        Evento eventoEliminar = null;
        for (Evento evento : Eventos) {
            if (evento.getNombre().equalsIgnoreCase(nombreEvento)) {
                eventoEliminar = evento;
                break;
            }
        }

        if (eventoEliminar != null) {
            Eventos.remove(eventoEliminar);
            guardarEventos();
            JOptionPane.showMessageDialog(null, "Evento eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "El evento no fue encontrado.");
        }
    }
    public static void guardarEventos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(eventoFile))) {
            oos.writeObject(Eventos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void agregarEvento() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del evento:");
        String horas = JOptionPane.showInputDialog("Ingrese la cantidad de horas del evento:");

        Evento evento = new Evento(nombre, horas);
        Eventos.add(evento);
        guardarEventos();
        JOptionPane.showMessageDialog(null, "Evento agregado correctamente.");
    }


    public static void mostrarEventos() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de Eventos:\n");
        for (Evento evento : Eventos) {
            sb.append(evento.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public static Estudiante login() {
        String id = JOptionPane.showInputDialog("Ingrese su ID:");
        String contrasena = JOptionPane.showInputDialog("Ingrese su contraseña:");

        for (Estudiante estudiante : Estudiantes) {
            if (estudiante.getId().equals(id) && estudiante.getContrasena().equals(contrasena)) {
                return estudiante;
            }
        }
        return null; // Si las credenciales no coinciden con ningún estudiante registrado
    }

    public static void inscribirEnEvento(Estudiante estudiante) {
        // Creamos un array de Strings para almacenar los nombres de los eventos disponibles
        String[] nombresEventos = new String[Eventos.size()];
        for (int i = 0; i < Eventos.size(); i++) {
            nombresEventos[i] = Eventos.get(i).getNombre();
        }

        // Mostramos un cuadro de diálogo para que el estudiante elija un evento
        String eventoSeleccionado = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione un evento para inscribirse:",
                "Inscripción en Evento",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombresEventos,
                nombresEventos[0]);

        // Si el estudiante cancela la selección o cierra la ventana, salimos del método
        if (eventoSeleccionado == null) {
            return;
        }

        // Buscamos el evento seleccionado por el estudiante
        Evento evento = null;
        for (Evento e : Eventos) {
            if (e.getNombre().equals(eventoSeleccionado)) {
                evento = e;
                break;
            }
        }

        // Inscribimos al estudiante en el evento seleccionado
        if (evento != null) {
            estudiante.inscribirEnEvento(evento.getNombre());
            guardarUsuarios(); // Guardamos los estudiantes actualizados

        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el evento seleccionado.");
        }
    }
    public static void darDeBajaEvento(Estudiante estudiante) {
        List<String> eventosInscritos = estudiante.getEventosInscritos();
        if (eventosInscritos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No estás inscrito en ningún evento.");
            return;
        }

        String[] opciones = eventosInscritos.toArray(new String[0]);
        String nombreEvento = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el evento del que desea darse de baja:",
                "Dar de Baja Evento",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (nombreEvento != null) {
            estudiante.darDeBajaEvento(nombreEvento);
            JOptionPane.showMessageDialog(null, "Te has dado de baja del evento: " + nombreEvento);
        }
    }
}