package Descartadas.Carolina.arboles.arbol_recursivo;

import Descartadas.Carolina.arboles.arbol_recursivo.binario.arbol_busqueda.ArbolBinarioDeBusquedaEnteros;
import Descartadas.Carolina.estructuras_necesarias.MyIterate;
import Descartadas.Carolina.estructuras_necesarias.MyList;

import java.util.Random;

public class MainRecursivo {

    public static void main(String[] args) {

        ArbolBinarioDeBusquedaEnteros arbolOrdenado =
                new ArbolBinarioDeBusquedaEnteros();

        for (int i = 0; i <= 128; i++) {
            arbolOrdenado.insertar(i);
        }

        System.out.println("Caso 1: inserción ordenada");
        probarArbol(arbolOrdenado);

        ArbolBinarioDeBusquedaEnteros arbolAleatorio =
                new ArbolBinarioDeBusquedaEnteros();

        int[] nums = new int[129];
        for (int i = 0; i <= 128; i++) nums[i] = i;

        mezclar(nums);

        for (int n : nums) {
            arbolAleatorio.insertar(n);
        }

        System.out.println("\nCaso 2: inserción aleatoria");
        probarArbol(arbolAleatorio);
    }

    private static void probarArbol(ArbolBinarioDeBusquedaEnteros arbol) {

        System.out.println("Suma total: " + arbol.calcularSumaElementos());
        System.out.println("Suma preorden: " + sumar(arbol.getListaPreOrden()));
        System.out.println("Suma inorden: " + sumar(arbol.getListaOrdenCentral()));
        System.out.println("Suma postorden: " + sumar(arbol.getListaPostOrden()));
        System.out.println("Altura: " + arbol.getAltura());

        MyList<Integer> camino = arbol.getCamino(110);

        System.out.print("Camino desde el 110: ");
        imprimir(camino);

        System.out.println("Longitud del camino: " + (camino.getSize() - 1));
    }

    private static int sumar(MyList<Integer> lista) {

        if (lista == null) return 0;

        MyIterate<Integer> it = lista.getIterate();
        int suma = 0;

        while (it.hasNext()) {
            suma += it.next();
        }

        return suma;
    }

    private static void imprimir(MyList<Integer> lista) {

        if (lista == null) {
            System.out.println("lista null");
            return;
        }

        MyIterate<Integer> it = lista.getIterate();
        boolean first = true;

        while (it.hasNext()) {

            if (!first) System.out.print(" -> ");

            System.out.print(it.next());
            first = false;
        }

        System.out.println();
    }

    private static void mezclar(int[] arr) {

        Random r = new Random();

        for (int i = arr.length - 1; i > 0; i--) {

            int j = r.nextInt(i + 1);

            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}