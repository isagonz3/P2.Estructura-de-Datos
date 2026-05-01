package Descartadas.Carolina.arboles.arbol_recursivo.binario.metodos_comunes;

import Descartadas.Carolina.arboles.arbol_recursivo.binario.nodo.NodoBinario;

public class Profundidad<T extends Comparable<T>> { //clase para calcular la profundidad de un nodo

    public int calcularProfundidad(NodoBinario<T> raiz, T data) { //devuelve nivel de un nodo
        return profundidadRec(raiz, data, 0); //empieza desde nivel 0
    }

    private int profundidadRec(NodoBinario<T> nodo, T data, int nivel) { //recursión de búsqueda

        if (nodo == null) { //si no existe el nodo
            return -1;
        }

        int cmp = data.compareTo(nodo.getData()); //comparación con el nodo actual

        if (cmp == 0) { //si se encuentra, devuelve nivel
            return nivel;
        }

        if (cmp < 0) { //si es menor, va izquierda
            return profundidadRec(nodo.getLeft(), data, nivel + 1);
        }

        return profundidadRec(nodo.getRight(), data, nivel + 1); //si es mayor, derecha
    }
}