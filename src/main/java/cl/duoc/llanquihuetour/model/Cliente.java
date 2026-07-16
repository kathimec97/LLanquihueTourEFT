package cl.duoc.llanquihuetour.model;

import cl.duoc.llanquihuetour.util.CorreoInvalidException;
import cl.duoc.llanquihuetour.util.DireccionInvalidaException;
import cl.duoc.llanquihuetour.util.RutInvalidException;
import cl.duoc.llanquihuetour.util.TelefonoInvalidException;

import java.util.ArrayList;

/**
 * Clase que representa a las personas que compran los tours de la agencia.
 * El constructor recibe su apellido, crea su lista de viaje y traspasa los datos comunes de la clase
 * padre Contacto.
 * @author Katherine Avila
 */
public class Cliente extends Contacto {

    private String apellido;
    private ArrayList<Tour> toursReservados;



    public Cliente(String rut, String nombre, String apellido, String telefono, String correo, String direccion) {
        super(rut, nombre, telefono, correo, direccion);
        this.apellido = apellido;

        this.toursReservados = new ArrayList<>();
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void agregarTour(Tour tour) {
        if(tour != null) {
            this.toursReservados.add(tour);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "  Apellido: " + apellido + "\n" +
                toursReservados.size();

    }

    /**
     * Verifica que el apellido no este vacío además de las verificaciones heredadas de la clase
     * Contacto al registrar un nuevo cliente
     * @throws RutInvalidException
     * @throws CorreoInvalidException
     * @throws TelefonoInvalidException
     */
    @Override
    public void registrar() throws RutInvalidException, CorreoInvalidException, TelefonoInvalidException, DireccionInvalidaException {
super.registrar();
if(this.apellido == null || this.apellido.trim().isEmpty()) {
    throw new IllegalArgumentException("El apellido del cliente no puede estar vacío");
}
    }

    /**
     *Da formato y presenta la información del cliente
     * @return Datos heredados de contacto, el apellido del cliente y los tours contratados.
     */
    @Override
    public String mostrarDatos() {
        return "RUT: " + getRut() + " | Nombre: " + getNombre() + " | Apellido: " + this.apellido +
                " | Teléfono: " + getTelefono() + "| Correo: " + getCorreo() + " | Dirección: " + getDireccion() +
                " | Tours Reservados: " + this.toursReservados.size();

    }

    /**
     *Convierte los datos del objeto a una línea de texto plano para guardarlo en el archivo.
     * @return una cadena de texto con los atributos del cliente separados por comas.
     */
    @Override
    public String cambiarATextoPlano() {
        return getRut() + "," + getNombre() + "," + this.apellido + "," + getTelefono() + "," + getCorreo() + "," + getDireccion() ;
    }
}
