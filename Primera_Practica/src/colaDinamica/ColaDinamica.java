package colaDinamica;

import java.util.ArrayList;

public class ColaDinamica {

    //atributos

    private ArrayList<Ticket> tickets;

    //constructor

    public ColaDinamica(){
        tickets = new ArrayList<>();
    }

    private boolean estaVacia(){
        return tickets.isEmpty();
    }

    public void insertar(byte id, String descripcion , String nombreCompleto, String fechaCreacion, String fechaResolucion, byte prioridad){ //me estaba dando un error debido a que el nombre piezas no puede ser el objeto y el array a la vez
        Ticket ticket = new Ticket(id,descripcion,nombreCompleto,fechaCreacion,fechaResolucion,prioridad);
        int i;
        for (i= 0; i < tickets.size(); ++i){
            if (prioridad > tickets.get(i).getPrioridad()) break;
        }

        tickets.add(i, ticket);
    }

    public void remover(){
        if (estaVacia()){
            System.out.println("La cola esta vacia");
            return;
        }

        tickets.removeFirst();

    }

    public Ticket verFrente(){
        if (estaVacia()){
            System.out.println("La cola esta vacia");
            return null;
        }

        return tickets.getFirst();


    }

}

