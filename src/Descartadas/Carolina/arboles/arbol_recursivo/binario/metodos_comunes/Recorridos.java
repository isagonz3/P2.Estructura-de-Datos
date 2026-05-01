package Descartadas.Carolina.arboles.arbol_recursivo.binario.metodos_comunes;

import Descartadas.Carolina.arboles.arbol_recursivo.binario.nodo.NodoBinario;
import Descartadas.Carolina.estructuras_necesarias.ListSE;
import Descartadas.Carolina.estructuras_necesarias.MyList;

public class Recorridos { //clase auxiliar para realizar los distintos recorridos del árbol

    public <T extends Comparable<T>> MyList<T> preOrden(NodoBinario<T> raiz) { //recorrido en preorden (nodo -> izquierda -> derecha)
        MyList<T> lista = new ListSE<>(); //lista donde se guardan los resultados

        preOrdenRec(raiz, lista); //llamada al metodo recursivo
        return lista; //devuelve la lista final
    }


    private <T extends Comparable<T>> void preOrdenRec(NodoBinario<T> nodo, MyList<T> lista) { //recorre el árbol en preorden
        if (nodo == null){
            return; //si el nodo es nulo no hace nada
        }

        lista.addLast(nodo.getData()); //primero se procesa el nodo actual
        preOrdenRec(nodo.getLeft(), lista); //después se recorre el subárbol izquierdo
        preOrdenRec(nodo.getRight(), lista); //por último el subárbol derecho
    }


    public <T extends Comparable<T>> MyList<T> inOrden(NodoBinario<T> raiz) { //recorrido en inorden (izquierda -> nodo -> derecha)
        MyList<T> lista = new ListSE<>(); //lista donde se guardan los resultados

        inOrdenRec(raiz, lista); //llamada al metodo recursivo
        return lista; //devuelve la lista final
    }

    private <T extends Comparable<T>> void inOrdenRec(NodoBinario<T> nodo, MyList<T> lista) { //recorre el árbol en inorden
        if (nodo == null) {
            return; //si el nodo es nulo no hace nada
        }

        inOrdenRec(nodo.getLeft(), lista); //primero se recorre el subárbol izquierdo
        lista.addLast(nodo.getData()); //después se procesa el nodo actual
        inOrdenRec(nodo.getRight(), lista); //por último el subárbol derecho
    }


    public <T extends Comparable<T>> MyList<T> postOrden(NodoBinario<T> raiz) { //recorrido en postorden (izquierda -> derecha -> nodo)
        MyList<T> lista = new ListSE<>(); //lista donde se guardan los resultados

        postOrdenRec(raiz, lista); //llamada al metodo recursivo
        return lista; //devuelve la lista final
    }

    private <T extends Comparable<T>> void postOrdenRec(NodoBinario<T> nodo, MyList<T> lista) { //recorre el árbol en postorden
        if (nodo == null) {
            return; //si el nodo es nulo no hace nada
        }

        postOrdenRec(nodo.getLeft(), lista); //primero se recorre el subárbol izquierdo
        postOrdenRec(nodo.getRight(), lista); //después el subárbol derecho
        lista.addLast(nodo.getData()); //por último se procesa el nodo actual
    }
}