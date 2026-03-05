package listaEnlazadaSimple;

import colaDinamica.Ticket;

public class ListaEnlazadaSimple {

    //atributo
    private NodoLista primero;

    //Metodo/constructor

    public void ListaEnlazadaSimple(NodoLista primero){
        this.primero = null;
    }

    public NodoLista getPrimero() {
        return primero;
    }

    public void setPrimero(NodoLista primero) {
        this.primero = primero;
    }

    //operaciones

    public void insertarNodoInicio(Ticket ticket, NodoLista siguiente){
        NodoLista nuevo = new NodoLista(ticket,siguiente);
        nuevo.setSiguiente(primero);
        setPrimero(nuevo);
    }

    private boolean estaVacia(){
        return primero==null;
    }

    public void insertarNodoFinal(Ticket ticket){
        NodoLista nuevo = new NodoLista(ticket, null);

        if (estaVacia()){
            setPrimero(nuevo);
            return;
        }

        NodoLista temp = primero;
        while (temp.getSiguiente() != null){
            temp = temp.getSiguiente();
        }

        temp.setSiguiente(nuevo);
    }

    public NodoLista buscar(byte id){
        if (estaVacia()){
            System.out.println("La lista esta vacia o esta pendiente");
            return null;
        }

        NodoLista temp = primero;

        while (temp.getSiguiente() != null && temp.getTicket().getId() != id){
            temp = temp.getSiguiente();
        }

        if (temp==null){
            System.out.println("El nombre no se encontro en la lista. \n");
            return temp;
        }else{
            System.out.println("El nombre se encontro en la lista. \n");
            return temp;
        }



    }

    public void mostrarLista() {
        if (estaVacia()) {
            System.out.println("La lista esta vacía. \n");
            return;
        }
        NodoLista temp = primero;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.getSiguiente();

        }

    }

    public NodoLista eliminar(String nombre, byte id) {
        if (estaVacia()) {
            System.out.println("La lista esta vacía. \n");
            return null;
        }

        NodoLista temp = primero;
        NodoLista anteriorTemp = temp;


        while (temp != null && temp != null && temp.getTicket().getId() != id) {
            anteriorTemp = temp;  //Pone al dia al anterior con respecto al temporal antes de moverlo
            temp = temp.getSiguiente(); // pasa la referencia del temporal al siguiente

        }
        return temp;
    }

}
