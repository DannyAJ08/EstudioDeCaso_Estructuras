package nodo;

public class Nodo {

    private char dato;
    private Nodo siguiente;

    // Constructor
    public Nodo(char dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    // Getter
    public char getDato() {
        return dato;
    }

    // Setter
    public void setDato(char dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}