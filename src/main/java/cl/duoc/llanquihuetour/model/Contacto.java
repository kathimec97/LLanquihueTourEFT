package cl.duoc.llanquihuetour.model;

import cl.duoc.llanquihuetour.util.CorreoInvalidException;
import cl.duoc.llanquihuetour.util.DireccionInvalidaException;
import cl.duoc.llanquihuetour.util.RutInvalidException;
import cl.duoc.llanquihuetour.util.TelefonoInvalidException;


/**
 *Clase base que agrupa los datos comunes de todas las personas y entidades.
 * @author katherine Avila
 */
public class Contacto implements Registrable{


    private String rut;
    private String nombre;
    private String telefono;
    private String correo;
    private String direccion;


    public Contacto( String rut, String nombre, String telefono, String correo, String direccion) {

        this.rut = rut;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }




    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    @Override
    public String toString() {
        return
                "RUT: " + rut + "\n" +
                        "Nombre: " + nombre + "\n" +
                        "Teléfono: " + telefono + "\n" +
                        "Correo: " + correo + "\n" +
                        "Dirección: " + direccion;

    }

    /**
     * Válida que el formato del contacto sea el correcto del RUT, correo, telefono del contacto y la dirección.
     */

    @Override
    public void registrar() throws RutInvalidException, CorreoInvalidException, TelefonoInvalidException, DireccionInvalidaException, IllegalArgumentException {


        if(!getRut().matches("[0-9]+-[0-9kK]")) {
            throw new RutInvalidException("Formato de Rut no valido (sin puntos, con guion)");

        }
        if(correo == null || !correo.contains("@") || (!correo.endsWith(".cl") && !correo.endsWith(".com"))) {
            throw new CorreoInvalidException("El correo debe contener @ y terminar en .cl o .com");
        }

            if (telefono == null || telefono.length()!= 9){
                throw  new TelefonoInvalidException("El telefono debe contener exactamente 9 digitos ( sin puntos ni guion)");
            }

            if (this.direccion == null || this.direccion.trim().isEmpty()) {
                throw new DireccionInvalidaException("La dirección no puede estar vacía");
            }

            String regexDireccion = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑüÜ\\s.]+$";

                if(!this.direccion.matches(regexDireccion)) {
                    throw new DireccionInvalidaException("La dirección debe tener el siguiente formato: solos puntos, sin comas. (Ej: Av. El Bosque 123 Llanquihue");
                }
            }

    /**
     * Da formato y presenta la información de Contacto
     * @return datos comunes de contacto: rut, nombre, teléfono, correo, dirección.
     */
    @Override
    public String mostrarDatos() {
        return "RUT: " + rut + " | Nombre: " + nombre + " | Teléfono: " + telefono +
                " | Correo: " + correo + " | Dirección: " + direccion;
    }
    /**
     * Convierte los datos del objeto a una línea de texto plano para guardar.
     *  @return una cadena de texto con los atributos de la clase separados por comas.
     */
    @Override
    public String cambiarATextoPlano() {
        return rut + "," + nombre + "," + telefono + "," + correo + "," + direccion;
    }
}
