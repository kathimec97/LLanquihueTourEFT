package cl.duoc.llanquihuetour.data;

import cl.duoc.llanquihuetour.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona las funcionalidades del menu del sistema Llanquihue Tour
 * permitiendo trabajar con los datos de los diferentes clientes, Guias, Proveedores y Operadores de transporte
 * asi como también el manejo de los diferentes Tours que ofrece la agencia.
 * @author Katherine Avila
 */
public class GestorTours {
    private List<Cliente> listaClientes;
    private List<Guia> listaGuias;
    private List<OperadorDeTransporte> listaTransporte;
    private List<Proveedor> listaProveedor;
    private List<Tour> listaTours;

    public GestorTours() {
        this.listaClientes = new ArrayList<>();
        this.listaGuias = new ArrayList<>();
        this.listaTransporte = new ArrayList<>();
        this.listaProveedor = new ArrayList<>();
        this.listaTours = new ArrayList<>();

    }

    /**
     * Muestra la lista de los tours, en caso de que haya Tours registrados en la lista arroja un mensaje.
     */
    public void mostrarTours() {
        if (this.listaTours.isEmpty()) {
            System.out.println("No hay Tours registrados en el sistema");
            return;
        }

        System.out.println("----LISTA DE TOURS----");
        for (Tour t : listaTours) {
            System.out.println(t.mostrarDatos());
        }
    }

    /**
     * Permite la búsqueda de los tours por ID's específicos
     * @param idBuscado Identificador único del Tour (ej. "T01").
     * @return el objeto Tour que coincide con el ID, o null si no lo encuentra.
     */
    public Tour buscarTourPorId(String idBuscado) {
        for (Tour t : listaTours) {
            if (t.getIdTour().equals(idBuscado)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Permite la busqueda de Clientes por su RUT
     * @param rutBuscado el rut que se quiere encontrar
     * @return el objeto cliente que coincide con el rut, o null si no lo encuentra
     */
    public Cliente buscarClientePorRut(String rutBuscado) {
        for (Cliente c : listaClientes) {
            if (c.getRut().equals(rutBuscado)) {
                return c;
            }

        }
        return null;

    }

    /**
     * Busca a un cliente específico utilizando su RUT y muestra en consola
     * el historial detallado de los tours que ha reservado.
     * Si el cliente no se encuentra registrado o no posee reservas actuales,
     * informa al usuario mediante un mensaje por pantalla.
     *
     * @param rutBuscado El RUT del cliente cuyo historial se desea consultar (Ej. "12345678-k").
     */
    public void mostrarHistorialDelCliente(String rutBuscado) {
        Cliente cliente = buscarClientePorRut(rutBuscado);

        if(cliente == null) {
            System.out.println("Error: no se encontró ningún cliente con ese RUT");
            return;
        }
        System.out.println("---Historial de Reservas para: " + cliente.getNombre()+ "---");

        if(cliente.getToursReservados().isEmpty()) {
            System.out.println(("El cliente no tiene reservas."));
        }else{
            for(Tour t : cliente.getToursReservados()) {
                System.out.println("-" + t.getNombre());
            }
        }
    }



    /**
     * Permite modificar el precio de los tours de la agencia
     * @param tourEncontrado el objeto tour que se desea modificar
     * @param nuevoPrecio el nuevo valor numerico del precio
     * @return el objeto Tour con su precio actualizado.
     */
    public Tour modificarPrecioTour(Tour tourEncontrado, int nuevoPrecio) {
        if (tourEncontrado != null) {

            tourEncontrado.setPrecio(nuevoPrecio);
        }

        return tourEncontrado;
    }

    /**
     * Para la reserva de un tour, al ingresar los datos del cliente que compra el tour, buscará si ya existe registro de ese
     * cliente por el RUT, si el RUT no coincide con un registro previo lo ingresará como nuevo registro en la lista de clientes y le asigna el tour,
     * en cambio, asignará el tour al cliente existente encontrado.
     * @param clienteIngresado El objeto Cliente que está realizando la reserva.
     * @param tourSeleccionado El objeto Tour que el cliente desea reservar
     */
    public void reservarTour(Cliente clienteIngresado, Tour tourSeleccionado) {
        Cliente clienteFinal = null;

        for (Cliente c : this.listaClientes) {
            if (c.getRut().equals(clienteIngresado.getRut())) {
                clienteFinal = c;
                break;

            }
        }
        if(clienteFinal == null) {
            listaClientes.add(clienteIngresado);
            clienteFinal = clienteIngresado;

        }
        clienteFinal.agregarTour(tourSeleccionado);
    }

    /**
     * Se encarga de escribir los datos recibidos en tiempo de ejecucion dentro del archivo correspondiente
     * se asegura de pasar cada objeto a texto plano con el formato correcto para escribirse en el archivo.
     * Si hay un error arroja un mensaje.
     * @param lista lista de objetos que implementan la interfaz Registrable
     * @param nombreArchivo nombre o ruta del archivo donde se guardaran los datos
     * @throws IOException sí ocurre un erro de entrada/salida durante la escritura
     */
    public void guardarListaEnArchivos(List<? extends Registrable> lista, String nombreArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {

            for (Registrable r : lista) {
                String linea = r.cambiarATextoPlano();

                writer.write(linea);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }

    /**
     * Maneja la opcion del menu "Guardar y Salir". Centraliza el guardado de todas las listas
     * del sistema en sus respectivos archivos de texto.
     * @throws IOException si ocurre un erro al escribir en los archivos
     * @see #guardarListaEnArchivos(List, String)
     */
    public void guardarYSalir() throws IOException {
    guardarListaEnArchivos(this.listaClientes, "Cliente.txt");
    guardarListaEnArchivos(this.listaTours, "Tour.txt");
    guardarListaEnArchivos(this.listaGuias, "Guia.txt");
    guardarListaEnArchivos(this.listaProveedor, "Proveedor.txt");
    guardarListaEnArchivos(this.listaTransporte, "Operadordetransporte.txt");
            System.out.println("Datos guardados con éxito. ¡Hasta pronto!");
    }


    //métodos auxiliares que leen los archivo de texto y convierten los datos a objetos para ser gestionados por el sistema
    private void cargarClientesDesdeArchivo(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length == 6) {
                    Cliente c = new Cliente(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5]);
                    this.listaClientes.add(c);
                } else {
                    System.out.println("Linea ignorada por formato incorrecto : " + linea);
                }


            }
        } catch (IOException e) {
            System.out.println("Error al cargar desde archivo Cliente: " + e.getMessage());
        }

    }

    private void cargarGuiasDesdeArchivo(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length == 7) {
                    Guia g = new Guia(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5], partes[6]);
                    this.listaGuias.add(g);
                } else {
                    System.out.println("Linea ignorada por formato incorrecto : " + linea);
                }


            }
        } catch (IOException e) {
            System.out.println("Error al cargar desde archivo Cliente: " + e.getMessage());
        }

    }

    private void cargarTransporteDesdeArchivo(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length == 7) {
                    OperadorDeTransporte t = new OperadorDeTransporte(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5], Integer.parseInt(partes[6]));
                    this.listaTransporte.add(t);
                } else {
                    System.out.println("Linea ignorada por formato incorrecto : " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar desde archivo Operador de Transporte: " + e.getMessage());
        }

    }

    private void cargarProveedorDesdeArchivo(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length == 6) {
                    Proveedor p = new Proveedor(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5]);
                    this.listaProveedor.add(p);
                } else {
                    System.out.println("Linea ignorada por formato incorrecto : " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar desde archivo Proveedor: " + e.getMessage());
        }

    }



    /**
     * Carga los datos desde las diferentes listas al inicio del programa.
     * Centraliza la lógica utilizando métodos auxiliares que procesan la lista de forma independiente
     * <ul>
     * <li>{@link #cargarClientesDesdeArchivo(String)}: lee el archivo de clientes y los convierte a objeto</li>
     * <li>{@link #cargarGuiasDesdeArchivo(String)}: lee el archivo de Guías y los convierte a objeto</li>
     * <li>{@link #cargarTransporteDesdeArchivo(String)}: lee el archivo de Operadores de transporte y los convierte a objeto</li>
     * <li>{@link #cargarProveedorDesdeArchivo(String)}: lee el archivo de los proveedores y los convierte a objeto</li>
     * <li>{@link #cargarTourDesdeArchivo(String)}: lee el archivo de tours y los convierte a objeto</li>
     * </ul>
     */
    public void cargarDatosInicio() {
        cargarGuiasDesdeArchivo("Guia.txt");
        cargarTransporteDesdeArchivo("Operadordetransporte.txt");
        cargarProveedorDesdeArchivo("Proveedor.txt");
        cargarTourDesdeArchivo("Tour.txt");
        cargarClientesDesdeArchivo("Cliente.txt");
    }

    //métodos auxiliares para el funcionamiento del método cargarTourDesdeArchivo
    public OperadorDeTransporte buscarNombreTransporte(String transporteBuscado) {
        for (OperadorDeTransporte ot : listaTransporte) {
            if (ot.getNombre().equals(transporteBuscado)) {
                return ot;
            }
        }
        return null;
    }
    public Guia buscarNombreGuia(String guiaBuscado) {
        for (Guia g : listaGuias) {
            if (g.getNombre().equals(guiaBuscado)) {
                return g;
            }
        }
        return null;
    }
    public Guia buscarRutGuia(String rutBuscado) {
        for(Guia g : listaGuias) {
            if(g.getRut().equals(rutBuscado)) {
                return g;
            }
        }
        return null;
    }
    public Proveedor buscarNombreProveedor(String proveedorBuscado) {
        for (Proveedor pro : listaProveedor) {
            if (pro.getNombre().equals(proveedorBuscado)) {
                return pro;
            }
        }
        return null;
    }

    /**
     * Lee el archivo de tours y reconstruye los objetos tours en memoria. Utiliza los métodos de búsqueda auxiliares para asociar
     * cada tour con su respectivo Guia, Operador, Transporte y proveedor basándose en los datos leídos
     * @param nombreArchivo Nombre o ruta del archivo de texto de los tours
     */
    private void cargarTourDesdeArchivo(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length == 6) {

                    Guia rutGuia = buscarRutGuia(partes[3]);
                    OperadorDeTransporte nombreOperador = buscarNombreTransporte(partes[4]);
                    Proveedor nombreProveedor = buscarNombreProveedor(partes[5]);
                    Tour tour = new Tour(partes[0], partes[1], Integer.parseInt(partes[2]), rutGuia, nombreOperador, nombreProveedor);
                    this.listaTours.add(tour);
                } else {
                    System.out.println("Linea ignorada por formato incorrecto : " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar desde archivo Tour " + e.getMessage());
        }

    }



}

