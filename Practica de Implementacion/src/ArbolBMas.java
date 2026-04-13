import java.util.ArrayList;

public class ArbolBMas {

    private NodoArbolBMas raiz;
    private int orden;

    public ArbolBMas(int orden) {
        this.orden = orden;
        this.raiz = new NodoArbolBMas(true);
    }

    public void insertar(int clave, String valor) {
        int[] claveSubida = new int[1];
        NodoArbolBMas[] nodoSubido = new NodoArbolBMas[1];
        boolean huboDiv = insertarEnNodo(raiz, clave, valor, claveSubida, nodoSubido);

        if (huboDiv) {
            NodoArbolBMas nuevaRaiz = new NodoArbolBMas(false);
            nuevaRaiz.listaClaves.add(claveSubida[0]);
            nuevaRaiz.listaHijos.add(raiz);
            nuevaRaiz.listaHijos.add(nodoSubido[0]);
            raiz = nuevaRaiz;
        }
    }

    private boolean insertarEnNodo(NodoArbolBMas nodo, int clave, String valor, int[] claveSubida, NodoArbolBMas[] nodoSubido) {
        if (nodo.esHoja) {
            insertarEnHoja(nodo, clave, valor);
            if (nodo.getCantidadClaves() >= orden) {
                dividirHijo(nodo, claveSubida, nodoSubido);
                return true;
            }
            return false;
        } else {
            int indice = encontrarIndice(nodo, clave);
            NodoArbolBMas hijo = nodo.listaHijos.get(indice);

            int[] claveTemp = new int[1];
            NodoArbolBMas[] nodoTemp = new NodoArbolBMas[1];
            boolean huboDiv = insertarEnNodo(hijo, clave, valor, claveTemp, nodoTemp);

            if (huboDiv) {
                nodo.listaClaves.add(indice, claveTemp[0]);
                nodo.listaHijos.add(indice + 1, nodoTemp[0]);

                if (nodo.getCantidadClaves() >= orden) {
                    dividirNodoInterno(nodo, claveSubida, nodoSubido);
                    return true;
                }
            }
            return false;
        }
    }

    private void insertarEnHoja(NodoArbolBMas hoja, int clave, String valor) {
        int i = 0;
        while (i < hoja.listaClaves.size() && clave > hoja.listaClaves.get(i)) {
            i++;
        }
        if (i < hoja.listaClaves.size() && clave == hoja.listaClaves.get(i)) {
            hoja.listaValores.set(i, valor);
        } else {
            hoja.listaClaves.add(i, clave);
            hoja.listaValores.add(i, valor);
        }
    }

    private int encontrarIndice(NodoArbolBMas nodo, int clave) {
        int i = 0;
        while (i < nodo.listaClaves.size() && clave >= nodo.listaClaves.get(i)) {
            i++;
        }
        return i;
    }

    public void dividirHijo(NodoArbolBMas hoja, int[] claveSubida, NodoArbolBMas[] nodoSubido) {
        int mitad = orden / 2;
        NodoArbolBMas nuevaHoja = new NodoArbolBMas(true);

        for (int i = mitad; i < hoja.listaClaves.size(); i++) {
            nuevaHoja.listaClaves.add(hoja.listaClaves.get(i));
            nuevaHoja.listaValores.add(hoja.listaValores.get(i));
        }

        nuevaHoja.siguiente = hoja.siguiente;
        hoja.siguiente = nuevaHoja;

        int tam = hoja.listaClaves.size();
        for (int i = tam - 1; i >= mitad; i--) {
            hoja.listaClaves.remove(i);
            hoja.listaValores.remove(i);
        }

        claveSubida[0] = nuevaHoja.listaClaves.get(0);
        nodoSubido[0] = nuevaHoja;
    }

    private void dividirNodoInterno(NodoArbolBMas nodo, int[] claveSubida, NodoArbolBMas[] nodoSubido) {
        int mitad = orden / 2;
        claveSubida[0] = nodo.listaClaves.get(mitad);

        NodoArbolBMas nuevoNodo = new NodoArbolBMas(false);
        for (int i = mitad + 1; i < nodo.listaClaves.size(); i++) {
            nuevoNodo.listaClaves.add(nodo.listaClaves.get(i));
        }
        for (int i = mitad + 1; i < nodo.listaHijos.size(); i++) {
            nuevoNodo.listaHijos.add(nodo.listaHijos.get(i));
        }

        int tamClaves = nodo.listaClaves.size();
        for (int i = tamClaves - 1; i >= mitad; i--) {
            nodo.listaClaves.remove(i);
        }
        int tamHijos = nodo.listaHijos.size();
        for (int i = tamHijos - 1; i >= mitad + 1; i--) {
            nodo.listaHijos.remove(i);
        }

        nodoSubido[0] = nuevoNodo;
    }

    public String buscar(int clave) {
        NodoArbolBMas hoja = encontrarHoja(raiz, clave);
        for (int i = 0; i < hoja.listaClaves.size(); i++) {
            if (hoja.listaClaves.get(i) == clave) {
                return hoja.listaValores.get(i);
            }
        }
        return null;
    }

    private NodoArbolBMas encontrarHoja(NodoArbolBMas nodo, int clave) {
        if (nodo.esHoja) {
            return nodo;
        }
        int indice = encontrarIndice(nodo, clave);
        return encontrarHoja(nodo.listaHijos.get(indice), clave);
    }

    public boolean eliminar(int clave) {
        boolean resultado = eliminarEnNodo(raiz, clave, null, -1);
        if (!raiz.esHoja && raiz.listaClaves.isEmpty()) {
            raiz = raiz.listaHijos.get(0);
        }
        return resultado;
    }

    private boolean eliminarEnNodo(NodoArbolBMas nodo, int clave, NodoArbolBMas padre, int indicePadre) {
        if (nodo.esHoja) {
            int indice = -1;
            for (int i = 0; i < nodo.listaClaves.size(); i++) {
                if (nodo.listaClaves.get(i) == clave) {
                    indice = i;
                    break;
                }
            }
            if (indice == -1) return false;

            nodo.listaClaves.remove(indice);
            nodo.listaValores.remove(indice);

            if (padre != null) {
                int minClaves = (int) Math.ceil(orden / 2.0) - 1;
                if (nodo.listaClaves.size() < minClaves) {
                    rebalancear(nodo, padre, indicePadre);
                }
            }
            return true;
        } else {
            int indiceHijo = encontrarIndice(nodo, clave);
            NodoArbolBMas hijo = nodo.listaHijos.get(indiceHijo);
            boolean resultado = eliminarEnNodo(hijo, clave, nodo, indiceHijo);

            if (padre != null) {
                int minClaves = (int) Math.ceil(orden / 2.0) - 1;
                if (nodo.listaClaves.size() < minClaves) {
                    rebalancear(nodo, padre, indicePadre);
                }
            }
            return resultado;
        }
    }

    private void rebalancear(NodoArbolBMas nodo, NodoArbolBMas padre, int indicePadre) {
        int minClaves = (int) Math.ceil(orden / 2.0) - 1;

        if (indicePadre > 0) {
            NodoArbolBMas hermanoIzq = padre.listaHijos.get(indicePadre - 1);
            if (hermanoIzq.listaClaves.size() > minClaves) {
                redistribuirDesdeIzquierda(nodo, hermanoIzq, padre, indicePadre);
                return;
            }
        }

        if (indicePadre < padre.listaHijos.size() - 1) {
            NodoArbolBMas hermanoDer = padre.listaHijos.get(indicePadre + 1);
            if (hermanoDer.listaClaves.size() > minClaves) {
                redistribuirDesdeDerecha(nodo, hermanoDer, padre, indicePadre);
                return;
            }
        }

        if (indicePadre > 0) {
            NodoArbolBMas hermanoIzq = padre.listaHijos.get(indicePadre - 1);
            fusionar(hermanoIzq, nodo, padre, indicePadre - 1);
        } else {
            NodoArbolBMas hermanoDer = padre.listaHijos.get(indicePadre + 1);
            fusionar(nodo, hermanoDer, padre, indicePadre);
        }
    }

    private void redistribuirDesdeIzquierda(NodoArbolBMas nodo, NodoArbolBMas hermanoIzq, NodoArbolBMas padre, int indicePadre) {
        if (nodo.esHoja) {
            int ultimo = hermanoIzq.listaClaves.size() - 1;
            nodo.listaClaves.add(0, hermanoIzq.listaClaves.remove(ultimo));
            nodo.listaValores.add(0, hermanoIzq.listaValores.remove(ultimo));
            padre.listaClaves.set(indicePadre - 1, nodo.listaClaves.get(0));
        } else {
            nodo.listaClaves.add(0, padre.listaClaves.get(indicePadre - 1));
            padre.listaClaves.set(indicePadre - 1, hermanoIzq.listaClaves.remove(hermanoIzq.listaClaves.size() - 1));
            nodo.listaHijos.add(0, hermanoIzq.listaHijos.remove(hermanoIzq.listaHijos.size() - 1));
        }
    }

    private void redistribuirDesdeDerecha(NodoArbolBMas nodo, NodoArbolBMas hermanoDer, NodoArbolBMas padre, int indicePadre) {
        if (nodo.esHoja) {
            nodo.listaClaves.add(hermanoDer.listaClaves.remove(0));
            nodo.listaValores.add(hermanoDer.listaValores.remove(0));
            padre.listaClaves.set(indicePadre, hermanoDer.listaClaves.get(0));
        } else {
            nodo.listaClaves.add(padre.listaClaves.get(indicePadre));
            padre.listaClaves.set(indicePadre, hermanoDer.listaClaves.remove(0));
            nodo.listaHijos.add(hermanoDer.listaHijos.remove(0));
        }
    }

    private void fusionar(NodoArbolBMas izquierdo, NodoArbolBMas derecho, NodoArbolBMas padre, int indiceIzq) {
        if (izquierdo.esHoja) {
            izquierdo.listaClaves.addAll(derecho.listaClaves);
            izquierdo.listaValores.addAll(derecho.listaValores);
            izquierdo.siguiente = derecho.siguiente;
        } else {
            izquierdo.listaClaves.add(padre.listaClaves.get(indiceIzq));
            izquierdo.listaClaves.addAll(derecho.listaClaves);
            izquierdo.listaHijos.addAll(derecho.listaHijos);
        }
        padre.listaClaves.remove(indiceIzq);
        padre.listaHijos.remove(indiceIzq + 1);
    }

    public void recorrerRango(int clave, int n) {
        NodoArbolBMas hoja = encontrarHoja(raiz, clave);
        int inicio = 0;
        while (inicio < hoja.listaClaves.size() && clave > hoja.listaClaves.get(inicio)) {
            inicio++;
        }

        int count = 0;
        NodoArbolBMas actual = hoja;
        int i = inicio;

        System.out.println("\nRecorrido de rango desde clave " + clave + ", mostrando " + n + " elemento(s):");
        System.out.println("--------------------------------------------");
        while (actual != null && count < n) {
            if (i >= actual.listaClaves.size()) {
                actual = actual.siguiente;
                i = 0;
                continue;
            }
            System.out.println("Clave: " + actual.listaClaves.get(i) + " | Valor: " + actual.listaValores.get(i));
            i++;
            count++;
        }
        if (count == 0) {
            System.out.println("No se encontraron elementos desde esa clave.");
        }
        System.out.println("--------------------------------------------");
        System.out.println("Elementos mostrados: " + count);
    }

    public void mostrarHojas() {
        NodoArbolBMas actual = raiz;
        while (!actual.esHoja) {
            actual = actual.listaHijos.get(0);
        }

        System.out.println("\nContenido del arbol B+:");
        System.out.println("--------------------------------------------");
        if (actual.listaClaves.isEmpty()) {
            System.out.println("El arbol esta vacio.");
            System.out.println("--------------------------------------------");
            return;
        }

        int total = 0;
        while (actual != null) {
            for (int i = 0; i < actual.listaClaves.size(); i++) {
                System.out.println("Clave: " + actual.listaClaves.get(i) + " | Valor: " + actual.listaValores.get(i));
                total++;
            }
            actual = actual.siguiente;
        }
        System.out.println("--------------------------------------------");
        System.out.println("Total de registros: " + total);
    }

    public boolean estaVacio() {
        return raiz.listaClaves.isEmpty();
    }

    public int getOrden() {
        return orden;
    }
}
