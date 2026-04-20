import java.util.ArrayList;

// Clase que representa un montículo mínimo (min-heap)
public class MinHeap {

    // Arreglo donde guardamos los elementos del heap
    private ArrayList<Integer> heap;

    // Constructor
    public MinHeap() {
        heap = new ArrayList<>();
    }

    // Retorna el índice del padre de un nodo
    private int padre(int i) {
        return (i - 1) / 2;
    }

    // Retorna el índice del hijo izquierdo
    private int hijoIzq(int i) {
        return 2 * i + 1;
    }

    // Retorna el índice del hijo derecho
    private int hijoDer(int i) {
        return 2 * i + 2;
    }

    // Intercambia dos elementos en el arreglo
    private void intercambiar(int a, int b) {
        int temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }

    // Sube el elemento en la posición i hasta donde corresponda
    private void upHeapify(int i) {
        while (i > 0 && heap.get(i) < heap.get(padre(i))) {
            intercambiar(i, padre(i));
            i = padre(i);
        }
    }

    // Baja el elemento en la posición i hasta donde corresponda
    private void downHeapify(int i) {
        int menor = i;
        int izq = hijoIzq(i);
        int der = hijoDer(i);

        if (izq < heap.size() && heap.get(izq) < heap.get(menor)) {
            menor = izq;
        }

        if (der < heap.size() && heap.get(der) < heap.get(menor)) {
            menor = der;
        }

        if (menor != i) {
            intercambiar(i, menor);
            downHeapify(menor);
        }
    }

    // Inserta un nuevo valor en el heap
    public void insertar(int valor) {
        heap.add(valor);
        upHeapify(heap.size() - 1);
        System.out.println("Se insertó el valor " + valor);
    }

    // Elimina y retorna el valor mínimo (la raíz)
    public int eliminarMin() {
        if (heap.isEmpty()) {
            System.out.println("El heap está vacío");
            return -1;
        }

        int min = heap.get(0);
        int ultimo = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, ultimo);
            downHeapify(0);
        }

        return min;
    }

    // Retorna el mínimo sin eliminarlo
    public int peek() {
        if (heap.isEmpty()) {
            System.out.println("El heap está vacío");
            return -1;
        }
        return heap.get(0);
    }

    // Convierte un arreglo desordenado en un heap válido
    public void heapify(ArrayList<Integer> arreglo) {
        heap = new ArrayList<>(arreglo);

        for (int i = padre(heap.size() - 1); i >= 0; i--) {
            downHeapify(i);
        }

        System.out.println("Arreglo convertido en heap: " + heap);
    }

    // Imprime el contenido del heap
    public void imprimir() {
        if (heap.isEmpty()) {
            System.out.println("El heap está vacío");
        } else {
            System.out.println("Contenido del heap: " + heap);
        }
    }

    // Retorna true si el heap no tiene elementos
    public boolean estaVacio() {
        return heap.isEmpty();
    }
}
