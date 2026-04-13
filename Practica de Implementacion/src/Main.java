import java.util.Scanner;

public class Main{

    static ArbolBMas arbol;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Sistema de Arbol B+ ===");
        System.out.print("Ingrese el orden del arbol (minimo 3): ");
        int orden = scanner.nextInt();
        while (orden < 3) {
            System.out.print("El orden debe ser al menos 3: ");
            orden = scanner.nextInt();
        }
        arbol = new ArbolBMas(orden);
        System.out.println("Arbol creado con orden " + orden);
        menu();
    }

    public static void menu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Insertar");
            System.out.println("2. Buscar");
            System.out.println("3. Eliminar");
            System.out.println("4. Recorrer por rango");
            System.out.println("5. Mostrar todo");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = scanner.nextInt();

            if (opcion == 1) {
                insertar();
            } else if (opcion == 2) {
                buscar();
            } else if (opcion == 3) {
                eliminar();
            } else if (opcion == 4) {
                recorrerRango();
            } else if (opcion == 5) {
                arbol.mostrarHojas();
            } else if (opcion == 0) {
                System.out.println("Hasta luego.");
            } else {
                System.out.println("Opcion no valida.");
            }
        }
    }

    static void insertar() {
        System.out.print("Clave (entero): ");
        int clave = scanner.nextInt();
        System.out.print("Valor: ");
        String valor = scanner.next();
        arbol.insertar(clave, valor);
        System.out.println("Insertado correctamente.");
    }

    static void buscar() {
        if (arbol.estaVacio()) {
            System.out.println("El arbol esta vacio.");
            return;
        }
        System.out.print("Clave a buscar: ");
        int clave = scanner.nextInt();
        String resultado = arbol.buscar(clave);
        if (resultado != null) {
            System.out.println("Encontrado -> Clave: " + clave + " | Valor: " + resultado);
        } else {
            System.out.println("No se encontro la clave " + clave);
        }
    }

    static void eliminar() {
        if (arbol.estaVacio()) {
            System.out.println("El arbol esta vacio.");
            return;
        }
        System.out.print("Clave a eliminar: ");
        int clave = scanner.nextInt();
        boolean ok = arbol.eliminar(clave);
        if (ok) {
            System.out.println("Clave " + clave + " eliminada.");
        } else {
            System.out.println("No se encontro la clave " + clave);
        }
    }

    static void recorrerRango() {
        if (arbol.estaVacio()) {
            System.out.println("El arbol esta vacio.");
            return;
        }
        System.out.print("Clave de inicio: ");
        int clave = scanner.nextInt();
        System.out.print("Cuantos elementos mostrar: ");
        int n = scanner.nextInt();
        arbol.recorrerRango(clave, n);
    }
}
