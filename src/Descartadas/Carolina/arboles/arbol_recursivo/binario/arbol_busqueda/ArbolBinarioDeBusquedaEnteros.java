package Descartadas.Carolina.arboles.arbol_recursivo.binario.arbol_busqueda;

import Descartadas.Carolina.arboles.arbol_recursivo.binario.nodo.NodoBinario;
import Descartadas.Carolina.estructuras_necesarias.ListSE;
import Descartadas.Carolina.estructuras_necesarias.MyList;

public class ArbolBinarioDeBusquedaEnteros extends ArbolBSTSinDuplicados<Integer> {

    public int calcularSumaElementos() { //calcula la suma de todos los elementos del árbol
        return sumarRec(raiz); //empieza desde la raíz
    }

    private int sumarRec(NodoBinario<Integer> nodo) { //recorrido recursivo del árbol

        if (nodo == null) {  //caso base
            return 0;
        }

        return nodo.getData() //valor del nodo actual
                + sumarRec(nodo.getLeft()) //suma subárbol izquierdo
                + sumarRec(nodo.getRight()); //suma subárbol derecho
    }


    @Override
    public MyList<Integer> getCamino(Integer data) { //devuelve el camino desde la raíz hasta un dato

        MyList<Integer> camino = new ListSE<>(); //lista donde se guarda el recorrido
        NodoBinario<Integer> actual = raiz; //empezamos desde la raíz

        while (actual != null) { //mientras haya nodos

            camino.add(actual.getData()); //añade el nodo actual al camino

            if (data.compareTo(actual.getData()) == 0) { //si se encuentra el dato devuelve el camino completo
                return camino;
            }

            if (data.compareTo(actual.getData()) < 0) { //si el dato es menor
                actual = actual.getLeft(); //va al subárbol izquierdo
            }
            else { //si el dato es mayor
                actual = actual.getRight(); //va al subárbol derecho
            }
        }

        return new ListSE<>(); //si no se encuentra devuelve lista vacía
    }
}