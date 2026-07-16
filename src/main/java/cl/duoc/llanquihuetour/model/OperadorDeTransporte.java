package cl.duoc.llanquihuetour.model;

import cl.duoc.llanquihuetour.util.CorreoInvalidException;
import cl.duoc.llanquihuetour.util.DireccionInvalidaException;
import cl.duoc.llanquihuetour.util.RutInvalidException;
import cl.duoc.llanquihuetour.util.TelefonoInvalidException;

/**
 * Clase que representa a los diferentes operadores de transporte que trabajan para la
 * agencia Llanquihue Tour.
 * @author Katherine Avila.
 */
public class OperadorDeTransporte extends Contacto{
    private String tipoDeVehiculo;
    private int capacidadPasajeros;

    public OperadorDeTransporte(String rut, String nombre, String telefono, String correo, String direccion, String tipoDeVehiculo, int capacidadPasajeros) {
        super(rut, nombre, telefono, correo, direccion);
        this.tipoDeVehiculo = tipoDeVehiculo;
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public String getTipoDeVehiculo() {
        return tipoDeVehiculo;
    }

    public void setTipoDeVehiculo(String tipoDeVehiculo) {
        this.tipoDeVehiculo = tipoDeVehiculo;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    @Override
    public String toString() {

        return  super.toString() + "OperadorDeTransporte{" +
                "tipoDeVehiculo='" + tipoDeVehiculo + '\'' +
                ", capacidadPasajeros=" + capacidadPasajeros +
                '}';
    }

    /**
     * Verifica que el tipo de vehículo no esté vacío y que la capacidad de pasajeros sea mayor a 0
     * además de las validaciones heredadas de la clase Contacto
     * @throws RutInvalidException
     * @throws CorreoInvalidException
     * @throws TelefonoInvalidException
     * @throws DireccionInvalidaException
     */
    @Override
    public void registrar() throws RutInvalidException, CorreoInvalidException, TelefonoInvalidException, DireccionInvalidaException {
     super.registrar();
     if(this.tipoDeVehiculo == null || tipoDeVehiculo.trim().isEmpty()) {
         throw new IllegalArgumentException("El tipo de vehiculo no puede estar vacío");
     }
         if(this.capacidadPasajeros <= 0 ) {
             throw new IllegalArgumentException("La capacidad de pasajeros debe ser mayor a 0");
         }
     }

    /**
     * Da formato y presenta la información del operador de transporte.
     * @return datos heredados de contacto, el tipo de vehículo y la capacidad de pasajeros
     */
    @Override
    public String mostrarDatos() {
        return  "RUT: " + getRut() + " | Nombre: " + getNombre()  +
                " | Teléfono: " + getTelefono() + " | Correo: " + getCorreo() + " | Dirección: " + getDireccion() +
                " | Tipo de Vehiculo: " + this.tipoDeVehiculo + " | Capacidad de Pasajeros: " + this.capacidadPasajeros;
    }

    /**
     * Convierte los datos del objeto en una línea de texto plano para guardarlo en el archivo.
     * @return una cadena de texto con los atributos del Operador de Transporte separados por coma.
     */
    @Override
    public String cambiarATextoPlano() {
        return getRut() + "," + getNombre() + "," + getTelefono() + "," + getCorreo() + "," + getDireccion() + "," + this.tipoDeVehiculo + "," + this.capacidadPasajeros;
    }
}
