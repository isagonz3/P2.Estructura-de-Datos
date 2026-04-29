package Descartadas.Carolina.arboles.arbol_recursivo.binario.metodos_comunes;

import Descartadas.Carolina.arboles.arbol_recursivo.binario.nodo.NodoBinario;
import Descartadas.Carolina.estructuras_necesarias.ListSE;

public class ValidadorBinario { //clase auxiliar con métodos estáticos para validar propiedades del árbol

    public static <T extends Comparable<T>> boolean esCompleto(NodoBinario<T> raiz) { //comprueba si el árbol es completo (todas las hojas al mismo nivel)
        int altura = altura(raiz); //obtiene la altura total del árbol
        return esCompletoRec(raiz, 0, altura); //empieza la comprobación desde la raíz en nivel 0
    }

    private static <T extends Comparable<T>> boolean esCompletoRec(NodoBinario<T> nodo, int nivel, int altura) { //comprueba recursivamente si todas las hojas están al mismo nivel
        if (nodo == null) return true; //si el nodo es nulo no afecta

        if (nodo.getLeft() == null && nodo.getRight() == null) { //si es una hoja
            return nivel == altura; //debe estar exactamente en el último nivel
        }

        if (nodo.getLeft() == null || nodo.getRight() == null) return false; //si le falta un hijo ya no es completo

        return esCompletoRec(nodo.getLeft(), nivel + 1, altura) && esCompletoRec(nodo.getRight(), nivel + 1, altura); //comprueba subárbol izquierdo y derecho
    }

    public static <T extends Comparable<T>> boolean esCasiCompleto(NodoBinario<T> raiz) { //comprueba si el árbol es casi completo
        if (raiz == null) return true; //si está vacío se considera válido

        ListSE<NodoBinario<T>> cola = new ListSE<>(); //cola auxiliar para recorrer por niveles
        cola.addLast(raiz); //se empieza por la raíz

        boolean encontradoHueco = false; //indica si ya se ha encontrado un hueco

        while (!cola.isEmpty()) { //mientras haya nodos pendientes
            NodoBinario<T> actual = cola.get(0); //obtiene el primero de la cola
            cola.del(actual); //lo elimina de la cola

            if (actual.getLeft() != null) { //si hay hijo izquierdo
                if (encontradoHueco) return false; //si ya hubo hueco antes, no es casi completo
                cola.addLast(actual.getLeft()); //se añade a la cola
            }
            else {
                encontradoHueco = true; //si no hay hijo izquierdo se marca hueco
            }

            if (actual.getRight() != null) { //si hay hijo derecho
                if (encontradoHueco) return false; //si ya hubo hueco antes, no es válido
                cola.addLast(actual.getRight()); //se añade a la cola
            }
            else {
                encontradoHueco = true; //si no hay hijo derecho se marca hueco
            }
        }

        return true; //si no se rompe la condición es casi completo
    }

    public static <T extends Comparable<T>> boolean esHomogeneo(NodoBinario<T> raiz) { //comprueba si todos los nodos tienen el mismo número de hijos
        int grado = grado(raiz); //obtiene el número de hijos que se espera
        return esHomogeneoRec(raiz, grado); //comprueba recursivamente
    }

    private static <T extends Comparable<T>> boolean esHomogeneoRec(NodoBinario<T> nodo, int grado) { //recorre el árbol comprobando homogeneidad
        if (nodo == null) return true; //si no hay nodo no afecta

        int hijos = 0; //contador de hijos del nodo actual

        if (nodo.getLeft() != null) hijos++; //si tiene hijo izquierdo suma 1
        if (nodo.getRight() != null) hijos++; //si tiene hijo derecho suma 1

        if (hijos != 0 && hijos != grado) return false; //si no es hoja y no coincide con el grado esperado falla

        return esHomogeneoRec(nodo.getLeft(), grado) //comprueba subárbol izquierdo
                && esHomogeneoRec(nodo.getRight(), grado); //comprueba subárbol derecho
    }

    private static <T extends Comparable<T>> int altura(NodoBinario<T> nodo) { //calcula la altura del árbol
        if (nodo == null) return -1; //si es nulo devuelve -1 para que la hoja tenga altura 0

        return 1 + Math.max(altura(nodo.getLeft()), altura(nodo.getRight())); //altura = 1 + mayor de los hijos
    }

    private static <T extends Comparable<T>> int grado(NodoBinario<T> nodo) { //calcula el grado del árbol
        if (nodo == null) return 0; //si no hay nodo no hay grado

        int hijos = 0; //contador de hijos

        if (nodo.getLeft() != null) hijos++; //si tiene hijo izquierdo suma 1
        if (nodo.getRight() != null) hijos++; //si tiene hijo derecho suma 1

        return Math.max(hijos, Math.max(grado(nodo.getLeft()), grado(nodo.getRight()))); //devuelve el máximo entre los hijos del nodo actual y los de sus subárboles
    }
}