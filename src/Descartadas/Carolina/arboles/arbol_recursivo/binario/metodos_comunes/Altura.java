package Descartadas.Carolina.arboles.arbol_recursivo.binario.metodos_comunes;

import Descartadas.Carolina.arboles.arbol_recursivo.binario.nodo.NodoBinario;

public class Altura<T extends Comparable<T>> { //calcula altura de un árbol o subárbol

    public int calcularAltura(NodoBinario<T> nodo) { //altura desde cualquier nodo

        if (nodo == null) { //si no hay nodo
            return -1;
        }

        int izq = calcularAltura(nodo.getLeft()); //altura izquierda
        int der = calcularAltura(nodo.getRight()); //altura derecha

        return 1 + Math.max(izq, der); //altura máxima + 1
    }
}