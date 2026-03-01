import java.util.Scanner;
import pilas.Pila;
import nodo.Nodo;


public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean salir = false;

        while (!salir) {

            System.out.println("\n===== ANALISIS DE EXPRESIONES =====");
            System.out.println("1. Verificar balanceo de parentesis");
            System.out.println("2. Convertir expresion a postfija");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("Ingrese la expresion: ");
                    String exp1 = scanner.nextLine();

                    if (estaBalanceada(exp1)) {
                        System.out.println("La expresion esta correctamente balanceada.");
                    } else {
                        System.out.println("La expresion NO esta balanceada.");
                    }
                    break;

                case 2:
                    System.out.print("Ingrese la expresion: ");
                    String exp2 = scanner.nextLine();
                    System.out.println("Expresion en postfija: " + convertirPostfija(exp2));
                    break;

                case 3:
                    salir = true;
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }


    public static boolean estaBalanceada(String expresion) {

        Pila pila = new Pila();

        for (int i = 0; i < expresion.length(); i++) {

            char c = expresion.charAt(i);

            if (c == '(') {
                pila.push(c);
            } else if (c == ')') {
                if (pila.estaVacia()) {
                    return false;
                }
                pila.pop();
            }
        }

        return pila.estaVacia();
    }


    public static int precedencia(char operador) {
        if (operador == '+' || operador == '-') return 1;
        if (operador == '*' || operador == '/') return 2;
        return 0;
    }


    public static String convertirPostfija(String expresion) {

        Pila pila = new Pila();
        String resultado = "";

        for (int i = 0; i < expresion.length(); i++) {

            char c = expresion.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                resultado += c;
            }
            else if (c == '(') {
                pila.push(c);
            }
            else if (c == ')') {

                while (!pila.estaVacia() && pila.peek() != '(') {
                    resultado += pila.pop();
                }

                pila.pop(); // eliminar '('
            }
            else {

                while (!pila.estaVacia() &&
                        precedencia(c) <= precedencia(pila.peek())) {

                    resultado += pila.pop();
                }

                pila.push(c);
            }
        }

        while (!pila.estaVacia()) {
            resultado += pila.pop();
        }

        return resultado;
    }
}