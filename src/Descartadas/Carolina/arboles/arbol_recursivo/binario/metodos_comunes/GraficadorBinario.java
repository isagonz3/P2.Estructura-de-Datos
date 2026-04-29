package Descartadas.Carolina.arboles.arbol_recursivo.binario.metodos_comunes;

import Descartadas.Carolina.arboles.arbol_recursivo.binario.nodo.NodoBinario;

public class GraficadorBinario { //clase auxiliar para representar el árbol

    public static <T extends Comparable<T>> String toString(NodoBinario<T> raiz) {
        StringBuilder sb = new StringBuilder();
        toStringRec(raiz, sb, 0); //llamada al metodo recursivo empezando en nivel 0
        return sb.toString(); //devuelve el resultado final
    }

    private static <T extends Comparable<T>> void toStringRec(NodoBinario<T> nodo, StringBuilder sb, int nivel) { //recorre el árbol para dibujarlo lateralmente
        if (nodo == null) return; //si el nodo es nulo no se imprime nada

        toStringRec(nodo.getRight(), sb, nivel + 1); //primero recorre el subárbol derecho

        for (int i = 0; i < nivel; i++) sb.append("   "); //añade espacios según el nivel para simular la profundidad
        sb.append(nodo.getData()).append("\n"); //añade el dato del nodo actual y salta la línea

        toStringRec(nodo.getLeft(), sb, nivel + 1); //después recorre el subárbol izquierdo
    }
}