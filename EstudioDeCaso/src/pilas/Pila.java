package pilas;
import nodo.Nodo;


public class Pila {

    private Nodo cima;

    // Metodos/Constructor
    public Pila() {
        cima = null;
    }

    //operaxiones
    public boolean estaVacia() {
        return cima == null;
    }


    public void push(char dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.setSiguiente(cima);
        cima = nuevo;
    }


    public char pop() {
        if (estaVacia()) {
            return '\0';
        }
        char dato = cima.getDato();
        cima = cima.getSiguiente();
        return dato;
    }


    public char peek() {
        if (estaVacia()) {
            return '\0';
        }
        return cima.getDato();
    }
}