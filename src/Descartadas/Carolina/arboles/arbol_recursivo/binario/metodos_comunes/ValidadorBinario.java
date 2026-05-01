package Descartadas.Carolina.arboles.arbol_recursivo.binario.metodos_comunes;

import Descartadas.Carolina.arboles.arbol_recursivo.binario.nodo.NodoBinario;
import Descartadas.Carolina.estructuras_necesarias.ListSE;

public class ValidadorBinario { //clase auxiliar con métodos para validar propiedades del árbol

    public <T extends Comparable<T>> boolean esCompleto(NodoBinario<T> raiz) { //comprueba si el árbol es completo (todas las hojas al mismo nivel)

        if (raiz == null) { //si no hay árbol es válido
            return true;
        }

        Altura<T> alt = new Altura<>(); //instancia de la clase altura

        int altura = alt.calcularAltura(raiz); //obtiene la altura del árbol

        return esCompletoRec(raiz, 0, altura); //empieza la comprobación desde la raíz en nivel 0
    }

    private <T extends Comparable<T>> boolean esCompletoRec(NodoBinario<T> nodo, int nivel, int altura) { //comprobación recursiva de completitud

        if (nodo == null) { //si el nodo es nulo no afecta
            return true;
        }

        if (nodo.getLeft() == null && nodo.getRight() == null) { //si es hoja
            return nivel == altura; //debe estar en el último nivel
        }

        if (nodo.getLeft() == null || nodo.getRight() == null) { //si falta un hijo no es completo
            return false;
        }

        return esCompletoRec(nodo.getLeft(), nivel + 1, altura) //subárbol izquierdo
                && esCompletoRec(nodo.getRight(), nivel + 1, altura); //subárbol derecho
    }


    public <T extends Comparable<T>> boolean esCasiCompleto(NodoBinario<T> raiz) { //comprueba si el árbol es casi completo

        if (raiz == null) return true; //si está vacío es válido

        ListSE<NodoBinario<T>> cola = new ListSE<>(); //cola para recorrido por niveles
        cola.addLast(raiz); //se empieza por la raíz

        boolean encontradoHueco = false; //marca si ya apareció un hueco

        while (!cola.isEmpty()) { //mientras haya nodos

            NodoBinario<T> actual = cola.get(0); //primer elemento de la cola
            cola.del(actual); //se elimina de la cola

            if (actual.getLeft() != null) { //si hay hijo izquierdo
                if (encontradoHueco) {
                    return false; //si ya hubo hueco no es válido
                }
                cola.addLast(actual.getLeft()); //se añade a la cola
            }
            else {
                encontradoHueco = true; //marca hueco
            }

            if (actual.getRight() != null) { //si hay hijo derecho
                if (encontradoHueco) {
                    return false; //si ya hubo hueco no es válido
                }
                cola.addLast(actual.getRight()); //se añade a la cola
            }
            else {
                encontradoHueco = true; //marca hueco
            }
        }

        return true; //si no se rompe la regla es casi completo
    }


    public <T extends Comparable<T>> boolean esHomogeneo(NodoBinario<T> raiz) { //comprueba si todos los nodos tienen el mismo número de hijos
        int grado = grado(raiz); //obtiene el grado del árbol
        return esHomogeneoRec(raiz, grado); //comprobación recursiva
    }

    private <T extends Comparable<T>> boolean esHomogeneoRec(NodoBinario<T> nodo, int grado) { //recorrido para homogeneidad

        if (nodo == null){
            return true; //si no hay nodo no afecta
        }

        int hijos = 0; //contador de hijos

        if (nodo.getLeft() != null){
            hijos++; //hijo izquierdo
        }
        if (nodo.getRight() != null) {
            hijos++; //hijo derecho
        }

        if (hijos != 0 && hijos != grado) { //si no es hoja y no coincide con grado falla
            return false;
        }

        return esHomogeneoRec(nodo.getLeft(), grado) //subárbol izquierdo
                && esHomogeneoRec(nodo.getRight(), grado); //subárbol derecho
    }


    private <T extends Comparable<T>> int grado(NodoBinario<T> nodo) { //calcula el grado del árbol

        if (nodo == null) { //caso base
            return 0;
        }

        int hijos = 0; //contador de hijos

        if (nodo.getLeft() != null) {
            hijos++; //hijo izquierdo
        }
        if (nodo.getRight() != null) {
            hijos++; //hijo derecho
        }

        return Math.max(hijos, Math.max(grado(nodo.getLeft()), grado(nodo.getRight()))); //máximo global
    }
}