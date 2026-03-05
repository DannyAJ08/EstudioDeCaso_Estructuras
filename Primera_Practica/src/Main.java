//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import colaDinamica.ColaDinamica;
import colaDinamica.Ticket;
import listaEnlazadaSimple.ListaEnlazadaSimple;
import listaEnlazadaSimple.NodoLista;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ColaDinamica cola = new ColaDinamica();
        ListaEnlazadaSimple listaResueltos = new ListaEnlazadaSimple();

        boolean salir = false;
        byte id = 1;

        while (!salir) {

            System.out.println("===== Sistema de Tickets =====");
            System.out.println("1. Menu Usuario");
            System.out.println("2. Menu Administrador");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = sc.nextInt(); // eto es para leer la ipcion que esocgio
            sc.nextLine();

            switch (opcion) {


                case 1:

                    System.out.println("\n--- MENU USUARIO ---");
                    System.out.println("1. Crear Ticket");
                    System.out.println("2. Buscar Ticket Resuelto");
                    System.out.print("Seleccione opcion: ");

                    int opcUsuario = sc.nextInt();
                    sc.nextLine();

                    switch (opcUsuario) {


                        case 1:

                            System.out.print("Descripcion: ");
                            String descripcion = sc.nextLine();

                            System.out.print("Nombre completo: ");
                            String nombre = sc.nextLine();

                            System.out.print("Fecha creacion: ");
                            String fechaCreacion = sc.nextLine();

                            System.out.print("Prioridad (1-5): ");
                            byte prioridad = sc.nextByte();
                            sc.nextLine();

                            cola.insertar(id, descripcion, nombre, fechaCreacion, null, prioridad);

                            System.out.println("Ticket creado con ID: " + id);

                            id++;

                            break;


                        case 2:

                            System.out.print("Ingrese el ID del ticket: ");
                            byte idBuscar = sc.nextByte();

                            NodoLista encontrado = listaResueltos.buscar(idBuscar);

                            if (encontrado != null) {
                                System.out.println("Ticket encontrado:");
                                System.out.println(encontrado.getTicket());
                            }

                            break;
                    }

                    break;


                case 2:

                    System.out.println("\n--- MENU ADMINISTRADOR ---");
                    System.out.println("1. Ver ticket al frente");
                    System.out.println("2. Resolver ticket");
                    System.out.print("Seleccione opcion: ");

                    int opcAdmin = sc.nextInt();
                    sc.nextLine();

                    switch (opcAdmin) {


                        case 1:

                            Ticket frente = cola.verFrente();

                            if (frente != null) {
                                System.out.println("Ticket al frente:");
                                System.out.println(frente);
                            }

                            break;


                        case 2:

                            Ticket resolver = cola.verFrente();

                            if (resolver == null) {
                                break;
                            }

                            System.out.print("Ingrese fecha de resolucion: ");
                            String fechaResolucion = sc.nextLine();

                            resolver.setFechaResolucion(fechaResolucion);

                            listaResueltos.insertarNodoFinal(resolver);

                            cola.remover();

                            System.out.println("Ticket resuelto y enviado a la lista.");

                            break;
                    }

                    break;

                case 3:
                    salir = true;
                    break;
            }
        }

        sc.close();
    }
}