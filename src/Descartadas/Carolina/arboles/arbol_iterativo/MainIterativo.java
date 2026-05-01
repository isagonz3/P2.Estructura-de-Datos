package Descartadas.Carolina.arboles.arbol_iterativo;

import Descartadas.Carolina.estructuras_necesarias.ListSE;

public class MainIterativo {

    public static void main(String[] args) {

        System.out.println("===== Parte 1: insercción ordenada de los elementos 0..128 =====");
        pruebaOrdenada();

        System.out.println("\n\n===== Prueba 2: insercción aleatoria de los elementos 0..128 =====");
        pruebaAleatoria();
    }


    public static void pruebaOrdenada() {

        ArbolBusquedaBinariaEnteros arbol = new ArbolBusquedaBinariaEnteros();

        for (int i = 0; i <= 128; i++) { //metodo1: insertar los números en orden
            arbol.add(i);
        }

        int suma = arbol.getSuma(); //suma total
        System.out.println("Suma total del árbol: " + suma);

        int sumaPre = sumarLista(arbol.getListaPreOrden()); //sumas en función de los recorridos
        int sumaIn = sumarLista(arbol.getListaOrdenCentral());
        int sumaPost = sumarLista(arbol.getListaPostOrden());

        System.out.println("Suma PreOrden: " + sumaPre);
        System.out.println("Suma InOrden: " + sumaIn);
        System.out.println("Suma PostOrden: " + sumaPost);

        System.out.println("¿Las 3 sumas coinciden? " + (suma == sumaPre && suma == sumaIn && suma == sumaPost)); //verificación de igualdad en los recorridos

        int sumaIzq = sumaSubarbol(arbol.raiz.hijoIzquierdo); //suma por subárboles
        int sumaDer = sumaSubarbol(arbol.raiz.hijoDerecho);
        System.out.println("Suma subárbol izquierdo: " + sumaIzq);
        System.out.println("Suma subárbol derecho: " + sumaDer);
        System.out.println("¿Coincide sumaIzq + sumaDer + raiz? " + (sumaIzq + sumaDer + arbol.raiz.dato == suma));

        System.out.println("Altura del árbol: " + arbol.getAltura()); //altura
        System.out.println("(En inserción ordenada, el árbol se vuelve una lista enlazada)");

        ListSE<Integer> camino = arbol.getCamino(110); //camino hasta los 110
        System.out.print("Camino hasta 110: ");
        imprimirLista(camino);

        System.out.println("Longitud del camino: " + (camino.size() - 1));
    }


    public static void pruebaAleatoria() {

        ArbolBusquedaBinariaEnteros arbol = new ArbolBusquedaBinariaEnteros();

        int[] numeros = new int[129]; //metodo2: permutación aleatoria sin repetir
        for (int i = 0; i <= 128; i++) numeros[i] = i;

        mezclar(numeros);

        for (int n : numeros) { //insertar en el árbol
            arbol.add(n);
        }

        int suma = arbol.getSuma(); //suma total
        System.out.println("Suma total del árbol: " + suma);


        int sumaPre = sumarLista(arbol.getListaPreOrden());
        int sumaIn = sumarLista(arbol.getListaOrdenCentral());
        int sumaPost = sumarLista(arbol.getListaPostOrden());

        System.out.println("Suma PreOrden: " + sumaPre);
        System.out.println("Suma InOrden: " + sumaIn);
        System.out.println("Suma PostOrden: " + sumaPost);

        System.out.println("¿Las 3 sumas coinciden? " + (suma == sumaPre && suma == sumaIn && suma == sumaPost));

        int sumaIzq = sumaSubarbol(arbol.raiz.hijoIzquierdo);
        int sumaDer = sumaSubarbol(arbol.raiz.hijoDerecho);
        System.out.println("Suma subárbol izquierdo: " + sumaIzq);
        System.out.println("Suma subárbol derecho: " + sumaDer);
        System.out.println("¿Coincide sumaIzq + sumaDer + raiz? " +
                (sumaIzq + sumaDer + arbol.raiz.dato == suma));

        System.out.println("Altura del árbol: " + arbol.getAltura());
        System.out.println("(En inserción aleatoria, la altura depende del orden aleatorio)");

        ListSE<Integer> camino = arbol.getCamino(110);
        System.out.print("Camino hasta 110: ");
        imprimirLista(camino);

        System.out.println("Longitud del camino: " + (camino.size() - 1));
    }

    //Métodos necesarios para el probador
    private static int sumarLista(ListSE<Integer> lista) {
        int suma = 0;
        for (int i = 0; i < lista.size(); i++) {
            suma += lista.get(i);
        }
        return suma;
    }

    private static int sumaSubarbol(NodoArbol<Integer> nodo) {
        if (nodo == null) return 0;
        return nodo.dato + sumaSubarbol(nodo.hijoIzquierdo) + sumaSubarbol(nodo.hijoDerecho);
    }

    private static void imprimirLista(ListSE<Integer> lista) {
        for (int i = 0; i < lista.size(); i++) {
            System.out.print(lista.get(i) + " ");
        }
        System.out.println();
    }

    private static void mezclar(int[] array) {
        java.util.Random r = new java.util.Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            int aux = array[i];
            array[i] = array[j];
            array[j] = aux;
        }
    }
}