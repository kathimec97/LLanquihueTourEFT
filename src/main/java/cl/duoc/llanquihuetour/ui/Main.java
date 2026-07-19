package cl.duoc.llanquihuetour.ui;


import cl.duoc.llanquihuetour.data.GestorTours;
import cl.duoc.llanquihuetour.model.Cliente;
import cl.duoc.llanquihuetour.model.Guia;
import cl.duoc.llanquihuetour.model.Tour;
import cl.duoc.llanquihuetour.model.Registrable;
import cl.duoc.llanquihuetour.util.CorreoInvalidException;
import cl.duoc.llanquihuetour.util.DireccionInvalidaException;
import cl.duoc.llanquihuetour.util.RutInvalidException;
import cl.duoc.llanquihuetour.util.TelefonoInvalidException;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner teclado = new Scanner(System.in);
        GestorTours gestor = new GestorTours();
        int opcionSeleccionada = 0;

        //Carga inicial
        gestor.cargarDatosInicio();

        do {
            System.out.println("----LLANQUIHUE TOUR----");
            System.out.println("1. Reservar Tour");
            System.out.println("2. Modificar Tour");
            System.out.println("3. Historial de reservas ");
            System.out.println("4. Guardar y salir");

            opcionSeleccionada = teclado.nextInt();
            teclado.nextLine();

            switch (opcionSeleccionada) {

                case 1:
                    System.out.println("--- lista de Tours ---");
                    gestor.mostrarTours();
                    System.out.println("Por favor, ingrese el ID del Tour que desea reservar");
                    String idAReservar = teclado.nextLine();

                    Tour tourAReservar = gestor.buscarTourPorId(idAReservar);

                    if(tourAReservar == null) {
                        System.out.println("Error: El ID ingresado no corresponde a ningún tour disponible");
                        break;
                    }
                    boolean datosValidos = false;
                    while (!datosValidos) {
                        System.out.println("Ingrese los siguientes datos:");
                        System.out.println();
                        System.out.println("Rut del Cliente");
                        String rutC = teclado.nextLine();
                        System.out.println("Nombre:");
                        String nombreC = teclado.nextLine();
                        System.out.println("Apellido:");
                        String apellidoC = teclado.nextLine();
                        System.out.println("Teléfono: ");
                        String telefonoC = teclado.nextLine();
                        System.out.println("Correo electrónico: ");
                        String correoC = teclado.nextLine();
                        System.out.println("Dirección(Calle, numero, comuna(sin comas ni puntos)");
                        String direccionC = teclado.nextLine();

                        try {
                            Cliente nuevoCliente = new Cliente(rutC, nombreC, apellidoC, telefonoC, correoC, direccionC);

                            nuevoCliente.registrar();

                            gestor.reservarTour(nuevoCliente, tourAReservar);
                            datosValidos = true;
                            System.out.println("Reserva realizada con éxito");

                        } catch (Exception e) {
                            System.out.println("Error en registro: " + e.getMessage());
                            System.out.println("Por favor, ingrese los datos nuevamente.\n");
                        }
                    }
                    break;


                case 2:
                    System.out.println("--- Lista de Tour ---");
                    gestor.mostrarTours();
                    System.out.println("Por favor, ingrese el ID del tour que desea modificar");
                    String idParaModificar = teclado.nextLine();

                    Tour tourModificar = gestor.buscarTourPorId(idParaModificar);

                     if(tourModificar != null){
                         System.out.println("Ingrese el nuevo precio del Tour");
                         int precioAModificar = teclado.nextInt();
                         teclado.nextLine();
                         gestor.modificarPrecioTour(tourModificar, precioAModificar);
                         System.out.println("¡Precio modificado con exito!");
                     }else{
                         System.out.println("Error: no se encontró ningún tour con ese ID");
                     }
                    break;

                case 3:
                    System.out.println("--- Historial de Reservas ---");
                    System.out.println("Ingrese el RUT del cliente (Ej. 12345678-k):");
                    String rutBuscado = teclado.nextLine();

                   gestor.mostrarHistorialDelCliente(rutBuscado);
                    break;

                case 4:

                    System.out.println("Guardando datos... ¡Adios!");
                    try {
                        gestor.guardarYSalir();
                    } catch (IOException e) {
                        System.out.println("Ocurrió un error: no se pudieron guardar los datos");;
                        System.err.println("Detalle técnico: " + e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Por favor ingrese una opcion valida");
                    break;
            }
        }while(opcionSeleccionada != 4);

        }


    }

