import java.util.ArrayList;
import java.util.Scanner;

// Clase principal con el menú del programa
public class Main {

    public static void main(String[] args) {

        MinHeap heap = new MinHeap();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println(" Min-Heap ");

        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Insertar elemento");
            System.out.println("2. Eliminar minimo");
            System.out.println("3. Ver minimo (peek)");
            System.out.println("4. Heapify (cargar arreglo)");
            System.out.println("5. Imprimir heap");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = scanner.nextInt();

            if (opcion == 1) {
                System.out.print("Ingrese el valor: ");
                int valor = scanner.nextInt();
                heap.insertar(valor);

            } else if (opcion == 2) {
                int min = heap.eliminarMin();
                if (min != -1) {
                    System.out.println("Minimo eliminado: " + min);
                }

            } else if (opcion == 3) {
                int min = heap.peek();
                if (min != -1) {
                    System.out.println("El minimo es: " + min);
                }

            } else if (opcion == 4) {
                System.out.print("Cuantos numeros quiere ingresar? ");
                int cantidad = scanner.nextInt();
                ArrayList<Integer> arreglo = new ArrayList<>();
                for (int i = 0; i < cantidad; i++) {
                    System.out.print("Numero " + (i + 1) + ": ");
                    arreglo.add(scanner.nextInt());
                }
                heap.heapify(arreglo);

            } else if (opcion == 5) {
                heap.imprimir();

            } else if (opcion == 0) {
                System.out.println("Hasta luego!");

            } else {
                System.out.println("Opcion no valida");
            }

        } while (opcion != 0);

        scanner.close();
    }
}
