package Descartadas.Carolina.arboles.arbol_recursivo.binario;

import Descartadas.Carolina.arboles.arbol_recursivo.binario.nodo.NodoBinario;

public class ArbolBinario<T extends Comparable<T>> extends ArbolBinarioBase<T> { //implementación de un árbol binario genérico

    @Override
    public void add(T data) { //añade un dato al árbol
        raiz = insertarRec(raiz, data); //llama al metodo recursivo y actualiza la raíz por si estaba vacía
    }

    private NodoBinario<T> insertarRec(NodoBinario<T> nodo, T data) { //inserta un nodo (la estructura no debe ser un árbol de búsqueda)
        if (nodo == null) return new NodoBinario<>(data); //si llegamos a un hueco se crea el nodo

        if (nodo.getLeft() == null) { //si el hijo izquierdo está libre
            nodo.setLeft(new NodoBinario<>(data)); //se inserta ahí directamente
        }
        else if (nodo.getRight() == null) { //si el izquierdo está ocupado pero el derecho libre
            nodo.setRight(new NodoBinario<>(data)); //se inserta en el derecho
        }
        else {
            nodo.setLeft(insertarRec(nodo.getLeft(), data)); //si ambos están ocupados se sigue bajando por la izquierda
        }

        return nodo; //devuelve el nodo actual
    }
}