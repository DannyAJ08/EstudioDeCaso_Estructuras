package listaEnlazadaSimple;

import colaDinamica.Ticket;


public class NodoLista {

    private Ticket ticket;
    private NodoLista siguiente;


    public NodoLista(Ticket ticket, NodoLista siguiente){
        this.ticket = ticket;
        this.siguiente = null;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public NodoLista getSiguiente(){
        return siguiente;
    }

    public void setTicket(Ticket ticket){
        this.ticket = ticket;
    }

    public void setSiguiente(NodoLista siguiente) {
        this.siguiente = siguiente;
    }
}
