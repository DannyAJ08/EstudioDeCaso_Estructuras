import java.util.ArrayList;

public class NodoArbolBMas {

    ArrayList<Integer> listaClaves;
    boolean esHoja;
    ArrayList<NodoArbolBMas> listaHijos;
    NodoArbolBMas siguiente;
    ArrayList<String> listaValores;

    public NodoArbolBMas(boolean esHoja) {
        this.esHoja = esHoja;
        this.listaClaves = new ArrayList<Integer>();
        this.listaHijos = new ArrayList<NodoArbolBMas>();
        this.listaValores = new ArrayList<String>();
        this.siguiente = null;
    }

    public int getCantidadClaves() {
        return listaClaves.size();
    }
}
