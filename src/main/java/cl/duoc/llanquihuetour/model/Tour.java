package cl.duoc.llanquihuetour.model;

import cl.duoc.llanquihuetour.util.CorreoInvalidException;
import cl.duoc.llanquihuetour.util.DireccionInvalidaException;
import cl.duoc.llanquihuetour.util.RutInvalidException;
import cl.duoc.llanquihuetour.util.TelefonoInvalidException;

/**
 * Clase que representa los viajes y los paquetes turísticos de la agencia
 * @author Katherine Avila
 */
public class Tour implements Registrable{

    private String idTour;
    private String nombre;
    private int precio;
    private Guia guiaAsignado;

    public Tour(String idTour, String nombre, int precio, Guia guiaAsignado) {
        this.idTour = idTour;
        this.nombre = nombre;
        this.precio = precio;
        this.guiaAsignado = guiaAsignado;
    }


    public String getIdTour() {
        return idTour;
    }

    public void setIdTour(String idTour) {
        this.idTour = idTour;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Guia getGuiaAsignado() {
        return guiaAsignado;
    }

    public void setGuiaAsignado(Guia guiaAsignado) {
        this.guiaAsignado = guiaAsignado;
    }


    @Override
    public String toString() {
        return
                "ID: " + idTour + "|" +
                "Nombre: " + nombre + "|" +
                "Precio: " + precio + "|" +
                "Guia Asignado: " + guiaAsignado;
    }

    /**
     * Válida el correcto registro del ID y del precio y
     * arroja excepciones con mensajes personalizados si hay error en el registro
     * @throws RutInvalidException
     * @throws CorreoInvalidException
     * @throws TelefonoInvalidException
     */
    @Override
    public void registrar() throws RutInvalidException, CorreoInvalidException, TelefonoInvalidException, DireccionInvalidaException {
        if(idTour== null || !idTour.trim().matches("^T\\d+$")) {
            throw new IllegalArgumentException(("Error: El ID del tour debe comenzar con T seguido de números(Ej: T01)"));
        }

        if (this.precio <= 0) {
            throw new IllegalArgumentException("El precio del tour debe ser mayor a 0");
        }
    }

    /**
     * Verifica que haya un guía asignado al tour y muestra su nombre, apellido junto con los demás datos del tour.
     */
    @Override
    public String mostrarDatos() {
        String nombreGuia = (guiaAsignado != null) ? guiaAsignado.getNombre() + " " + guiaAsignado.getApellido() : "sin guia asignado";
        return "(ID:"  + this.idTour + ")" + "Tour: " + this.nombre + "| " + "Precio: $" + this.precio + "| " + "Guia: " + nombreGuia ;
     }

    /**
     * Convierte los datos del objeto a una línea de texto plano para guardarlo en el archivo.
     * @return id del tour, nombre del tour, precio, y el RUT del guía.
     */
    @Override
    public String cambiarATextoPlano() {
        String rutGuia = (this.guiaAsignado != null) ? this.guiaAsignado.getRut() : "S/G";

        return this.idTour + "," + this.nombre + "," + this.precio + "," + rutGuia;
    }
}
