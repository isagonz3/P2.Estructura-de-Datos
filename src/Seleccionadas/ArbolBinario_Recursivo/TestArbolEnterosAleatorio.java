package Descartadas.Isabel.EL2a;

import Descartadas.Isabel.TADs.Lista;

public class TestArbolEnterosAleatorio {
    public static void main(String[] args) {

        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();

        //Añadimos los numeros de forma aleatoria mediante Fisher-Yates
        int[] numsAleat = FishYat.aleatorio(129);

        for(int num : numsAleat){
            arbol.add(num);
        }

        //Calculamos la suma total de los nodos
        int sumaEnteros = arbol.getSuma();
        System.out.println("Suma: " + sumaEnteros);
        System.out.println("--------------------");

        //Comprobamos que la suma es igual para inorden, preorden y postorden

        //Suma de nodos en el recorrido Inorden
        int sumaInorden = 0;
        Lista<NodoArbolBinario<Integer>> nodoInorden = arbol.getListaInorden();
        for(int i = 0; i < arbol.getListaInorden().getSize(); i++){
            sumaInorden += nodoInorden.get(i).getDato();
        }
        System.out.println("Suma inorden: " + sumaInorden);
        System.out.println("--------------------");

        //Suma de nodos en el recorrido Preorden
        int sumaPreorden = 0;
        Lista<NodoArbolBinario<Integer>> nodoPreorden = arbol.getListaPreorden();
        for(int i = 0; i < arbol.getListaPreorden().getSize(); i++){
            sumaPreorden += nodoPreorden.get(i).getDato();
        }
        System.out.println("Suma preorden: " + sumaPreorden);
        System.out.println("--------------------");

        //Suma de nodos en el recorrido Postorden
        int sumaPostorden = 0;
        Lista<NodoArbolBinario<Integer>> nodoPostorden = arbol.getListaPostorden();
        for(int i = 0; i < arbol.getListaPostorden().getSize(); i++){
            sumaPostorden += nodoPostorden.get(i).getDato();
        }
        System.out.println("Suma postorden: " + sumaPostorden);
        System.out.println("--------------------");

        //Comprobamos que el resultado de la suma coincide para todos los casos
        if (sumaEnteros == sumaInorden && sumaEnteros == sumaPreorden && sumaEnteros == sumaPostorden) {
            System.out.println("El resultado es igual en todos los recorridos");
            System.out.println("--------------------");
        } else {
            System.out.println("El resultado no coincide");
        }

        //Comprobamos que el resultado de la suma coincide sumando los elementos de los
        //subarboles
        int sumaSubIzq = arbol.sumaNodos(arbol.raiz.getIzq());
        int sumaSubDer = arbol.sumaNodos(arbol.raiz.getDer());
        int sumaSubArboles = arbol.raiz.getDato() + sumaSubIzq + sumaSubDer;

        System.out.println("Suma por subarboles: " + sumaSubArboles);
        System.out.println("--------------------");

        if (sumaSubArboles == sumaEnteros) {
            System.out.println("El resultado coincide sumando por subarboles");
            System.out.println("--------------------");
        } else {
            System.out.println("El resultado no coincide");
            System.out.println("--------------------");
        }

        //Obtener la altura del arbol
        int altura = arbol.getAltura();
        System.out.println("Altura: " + altura);
        System.out.println("--------------------");

        //Obtener el camino hasta el valor 110
        Lista<Integer> camino = arbol.getCamino(arbol.raiz, 110);
        System.out.println("Camino hasta el valor 110: " + camino);
        System.out.println("--------------------");

        //Obtener la longitud del camino hasta el valor 110
        int longCamino = camino.getSize() - 1;
        System.out.println("Longitud del camino hasta el valor 110: " + longCamino);
        System.out.println("--------------------");

        /*Los valores de las sumas no cambian con el orden aleatorio, pero la altura,
        * el camino, y la longitud del camino cambian segun la posicion aleatoria de los
        * valores.*/

    }
}


