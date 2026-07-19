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
    private OperadorDeTransporte transporteAsignado;
    private Proveedor proveedorAsignado;

    public Tour(String idTour, String nombre, int precio, Guia guiaAsignado, OperadorDeTransporte transporteAsignado, Proveedor proveedorAsignado) {
        this.idTour = idTour;
        this.nombre = nombre;
        this.precio = precio;
        this.guiaAsignado = guiaAsignado;
        this.transporteAsignado = transporteAsignado;
        this.proveedorAsignado = proveedorAsignado;

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

    public OperadorDeTransporte getTransporteAsignado() {
        return transporteAsignado;
    }

    public void setTransporteAsignado(OperadorDeTransporte transporteAsignado) {
        this.transporteAsignado = transporteAsignado;
    }

    public Proveedor getProveedorAsignado() {
        return proveedorAsignado;
    }

    public void setProveedorAsignado(Proveedor proveedorAsignado) {
        this.proveedorAsignado = proveedorAsignado;
    }

    @Override
    public String toString() {
        return
                "ID: " + idTour + "|" +
                "Nombre: " + nombre + "|" +
                "Precio: " + precio + "|" +
                "Guia Asignado: " + guiaAsignado +
                "Transporte Asignado: " + transporteAsignado +
                "Proveedor Asignado: " + proveedorAsignado;
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
     * Revisa si hay un guía, un transporte y un proveedor asignado al tour,
     * dejando un aviso en el caso de que no tengan alguno asignado.
     * Da formato y presenta toda la información de los tours.
     */
    @Override
    public String mostrarDatos() {
        String nombreGuia = (guiaAsignado != null) ? guiaAsignado.getNombre() + " " + guiaAsignado.getApellido() : "sin guia asignado";
        String nombreTransporte = (transporteAsignado != null) ? transporteAsignado.getNombre() : "No aplica";
        String nombreProveedor = (proveedorAsignado !=null) ? proveedorAsignado.getNombre(): "No aplica";
        return "(ID:"  + this.idTour + ")" + " | Tour: " + this.nombre + " | " + "Precio: $" + this.precio + " | " + "Guia: " + nombreGuia + " | " + "Transporte: " + nombreTransporte + " | " + "Proveedor: " + nombreProveedor;
     }

    /**
     * Convierte los datos del objeto a una línea de texto plano para guardarlo en el archivo.
     * @return id del tour, nombre del tour, precio, y el RUT del guía.
     */
    @Override
    public String cambiarATextoPlano() {
        String rutGuia = (this.guiaAsignado != null) ? this.guiaAsignado.getRut() : "S/G";
        String nombreTransporte = (this.transporteAsignado != null) ? this.transporteAsignado.getNombre() : "S/T";
        String nombreProveedor = (this.proveedorAsignado != null) ? this.proveedorAsignado.getNombre() : "S/P";
        return this.idTour + "," + this.nombre + "," + this.precio + "," + rutGuia + "," + nombreTransporte + "," + nombreProveedor;
    }
}
