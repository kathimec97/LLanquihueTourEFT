package cl.duoc.llanquihuetour.model;

import cl.duoc.llanquihuetour.util.CorreoInvalidException;
import cl.duoc.llanquihuetour.util.DireccionInvalidaException;
import cl.duoc.llanquihuetour.util.RutInvalidException;
import cl.duoc.llanquihuetour.util.TelefonoInvalidException;

/**
 * Contrato común entre las clases que la implementen para mostrar los datos de los objetos creados
 * para registrar y validar datos nuevos y mediante otro metodo devolver las variables unidas por comas para guardar en los archivos.
 * @author Katherine Avila
 */
public interface Registrable {
    //se encarga de verificar rut, correo, y teléfono al momento del registro
    void registrar() throws RutInvalidException, CorreoInvalidException, TelefonoInvalidException, DireccionInvalidaException, IllegalArgumentException;

    //Devuelve el texto ordenado para la opción de consultar detalles de los tours
    String mostrarDatos();

    //Devuelve las variables unidas por comas para guardar en los archivos
    String cambiarATextoPlano();
}
