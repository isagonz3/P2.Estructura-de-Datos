package Descartadas.Carolina.arboles.arbol_recursivo;

import Descartadas.Carolina.arboles.arbol_recursivo.binario.arbol_busqueda.ArbolBinarioDeBusquedaEnteros;
import Descartadas.Carolina.estructuras_necesarias.MyIterate;
import Descartadas.Carolina.estructuras_necesarias.MyList;

import java.util.Random;

public class MainRecursivo {

    public static void main(String[] args) {

        ArbolBinarioDeBusquedaEnteros arbolOrdenado = new ArbolBinarioDeBusquedaEnteros();
        for (int i = 0; i <= 128; i++) {
            arbolOrdenado.add(i);
        }
        System.out.println("Caso 1: inserción ordenada");
        probarArbol(arbolOrdenado);

        ArbolBinarioDeBusquedaEnteros arbolAleatorio = new ArbolBinarioDeBusquedaEnteros();
        int[] nums = new int[129];
        for (int i = 0; i <= 128; i++) nums[i] = i;
        mezclar(nums);

        for (int n : nums) {
            arbolAleatorio.add(n);
        }

        System.out.println("\nPrograma 2: inserción aleatoria");
        probarArbol(arbolAleatorio);
    }

    private static void probarArbol(ArbolBinarioDeBusquedaEnteros arbol) {
        int suma = arbol.getSuma();
        System.out.println("Suma total: " + suma);

        MyList<Integer> pre = arbol.getListaPreOrden();
        MyList<Integer> in = arbol.getListaOrdenCentral();
        MyList<Integer> post = arbol.getListaPostOrden();

        System.out.println("Suma preorden: " + sumarLista(pre));
        System.out.println("Suma inorden: " + sumarLista(in));
        System.out.println("Suma postorden: " + sumarLista(post));

        System.out.println("Altura: " + arbol.getAltura());

        MyList<Integer> camino110 = arbol.getCamino(110);
        System.out.print("Camino hasta 110: ");
        imprimirLista(camino110);
        System.out.println("Longitud del camino: " + (camino110.getSize() - 1));
    }

    private static int sumarLista(MyList<Integer> lista) {
        MyIterate<Integer> it = lista.getIterate();
        int suma = 0;
        while (it.hasNext()) suma += it.next();
        return suma;
    }

    private static void imprimirLista(MyList<Integer> lista) {
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