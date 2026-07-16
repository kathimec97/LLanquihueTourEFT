package cl.duoc.llanquihuetour.model;

import cl.duoc.llanquihuetour.util.CorreoInvalidException;
import cl.duoc.llanquihuetour.util.DireccionInvalidaException;
import cl.duoc.llanquihuetour.util.RutInvalidException;
import cl.duoc.llanquihuetour.util.TelefonoInvalidException;

/**
 * Clase que representa a los guías encargados de dirigir los recorridos turísticos.
 * El constructor recibe sus datos básicos y el idioma que maneja para trabajar
 * y traspasa los datos comunes de la clase padre Contacto
 * @author Katherine Avila
 */
public class Guia extends Contacto{

    private String apellido;
    private String idiomas;

    public Guia(String rut, String nombre, String apellido, String telefono, String correo, String direccion, String idiomas) {
        super(rut, nombre, telefono, correo, direccion);
        this. apellido = apellido;
        this.idiomas = idiomas;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    @Override
    public String toString() {
        return   super.toString() +
                "Apellido: " + apellido + "|" +
                "Idiomas: " + idiomas + "|" ;
    }

    /**
     * Válida que el apellido y los idiomas no estén vacíos antes de registrar.
     * Además de las verificaciones heredadas de la clase Contacto
     * @throws RutInvalidException
     * @throws CorreoInvalidException
     * @throws TelefonoInvalidException
     * @throws DireccionInvalidaException
     */
    @Override
    public  void registrar() throws RutInvalidException, CorreoInvalidException, TelefonoInvalidException, DireccionInvalidaException {
        super.registrar();

        if(this.apellido == null || this.apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("Error de lectura: El guia no tiene apellido");
        }
            if (this.idiomas == null || this.idiomas.trim().isEmpty()) {
                throw  new IllegalArgumentException("Error de lectura: El guia no tiene idiomas");
            }
        }

    /**
     * /**
     * Da formato y presenta la información del guía
     * @return Datos heredados de contacto, el apellido del guía y los idiomas que posee.
     */
    @Override
    public String mostrarDatos() {
        return "RUT: " + getRut() + " | Nombre: " + getNombre() + " | Apellido: " + this.apellido +
                " | Teléfono: " + getTelefono() + " | Correo: " + getCorreo() + " | Dirección: " + getDireccion()  + " | Idiomas: " + this.idiomas;
    }

    /**
     *Convierte los datos del objeto a una línea de texto plano para guardarlo en el archivo.
     * @return una cadena de texto con los atributos del guía separados por comas.
     */
    @Override
    public String cambiarATextoPlano() {
        return getRut() + "," + getNombre() + "," + this.apellido + "," + getTelefono() + "," + getCorreo() + "," + getDireccion() + "," + this.idiomas;
    }
}



