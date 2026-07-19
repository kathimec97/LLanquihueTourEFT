package cl.duoc.llanquihuetour.model;

import cl.duoc.llanquihuetour.util.CorreoInvalidException;
import cl.duoc.llanquihuetour.util.DireccionInvalidaException;
import cl.duoc.llanquihuetour.util.RutInvalidException;
import cl.duoc.llanquihuetour.util.TelefonoInvalidException;

/**
 * Clase que representa los datos de los diferentes proveedores en el sistema de Llanquihue Tour
 * @author Katherine Avila
 */
public class Proveedor extends Contacto implements Registrable {
     private String tipoDeServicio;


     public Proveedor(String rut, String nombre, String telefono, String correo, String direccion,String tipoDeServicio ) {
          super(rut, nombre, telefono, correo, direccion);
          this.tipoDeServicio = tipoDeServicio;
     }

     public String getTipoDeservicio() {
          return tipoDeServicio;
     }

     public void setTipoDeservicio(String tipoDeServicio) {
     this.tipoDeServicio = tipoDeServicio;
     }

     @Override
     public String toString() {
          return "Proveedor{" +
                  "tipoDeservicio='" + tipoDeServicio + '\'' +
                  '}';
     }

     /**
      * Verifica que el tipo de servicio no esté vacío, además de las validaciónes heredadas de la clase Contacto
      * @throws RutInvalidException
      * @throws CorreoInvalidException
      * @throws TelefonoInvalidException
      * @throws DireccionInvalidaException
      */

     @Override
     public void registrar() throws RutInvalidException, CorreoInvalidException, TelefonoInvalidException, DireccionInvalidaException {
     super.registrar();
     if(this.tipoDeServicio == null || this.tipoDeServicio.trim().isEmpty()) {
          throw new IllegalArgumentException("El campo Tipo de servicio no puede estar vacío");
     }
     }

     /**
      * Da formato y presenta la información del proveedor.
      * @return datos heredados de contacto y el tipo de servicio.
      */
     @Override
     public String mostrarDatos() {

          return   "RUT: " + getRut() + " | Nombre: " + getNombre()  +
                  " | Teléfono: " + getTelefono() + " | Correo: " + getCorreo() + " | Dirección: " + getDireccion() + " | Tipo de servicio: " + this.tipoDeServicio;
     }

     /**
      * Convierte los datos del objeto en una línea de texto plano para guardarlo en un archivo.
      * @return una cadena de texto con los atributos del proveedor separados por coma.
      */
     @Override
     public String cambiarATextoPlano() {
          return getRut() + "," + getNombre() + "," + getTelefono() + "," + getCorreo() + "," + getDireccion() + "," + this.tipoDeServicio;
     }
}
