package Descartadas.Carolina.arboles.arbol_recursivo.binario.arbol_busqueda;

import Descartadas.Carolina.arboles.arbol_recursivo.binario.metodos_comunes.Altura;
import Descartadas.Carolina.arboles.arbol_recursivo.binario.nodo.NodoBinario;

public class ArbolBalanceado<T extends Comparable<T>> extends ArbolBSTSinDuplicados<T> { //árbol BST balanceado tipo AVL

    private final Altura<T> altura = new Altura<>(); //utilidad para calcular altura de subárboles

    @Override
    public void insertar(T data) { //inserta un dato manteniendo el balance del árbol
        raiz = insertarAVL(raiz, data); //llama al metodo recursivo desde la raíz
    }

    private NodoBinario<T> insertarAVL(NodoBinario<T> nodo, T data) { //inserción AVL

        if (nodo == null) { //si no hay nodo, se crea uno nuevo
            return new NodoBinario<>(data);
        }

        int comparacion = data.compareTo(nodo.getData()); //compara el dato con el nodo actual

        if (comparacion < 0) { //si es menor, va al subárbol izquierdo
            nodo.setLeft(insertarAVL(nodo.getLeft(), data));
        }
        else if (comparacion > 0) { //si es mayor, va al subárbol derecho
            nodo.setRight(insertarAVL(nodo.getRight(), data));
        }
        else {
            return nodo; //si es igual no se inserta (no duplicados)
        }

        return equilibrarNodo(nodo); //al volver de la recursión se reequilibra el nodo
    }


    @Override
    public void eliminar(T data) { //elimina un nodo manteniendo el balance
        raiz = eliminarAVL(raiz, data); //llama al metodo recursivo desde la raíz
    }

    private NodoBinario<T> eliminarAVL(NodoBinario<T> nodo, T data) { //eliminación AVL

        if (nodo == null) {  //si no existe el nodo no hace nada
            return null;
        }

        int comparacion = data.compareTo(nodo.getData()); //compara el dato con el nodo actual

        if (comparacion < 0) { //si es menor busca en la izquierda
            nodo.setLeft(eliminarAVL(nodo.getLeft(), data));
        }
        else if (comparacion > 0) { //si es mayor busca en la derecha
            nodo.setRight(eliminarAVL(nodo.getRight(), data));
        }
        else { //si encuentra el nodo a eliminar

            if (nodo.getLeft() == null) { //caso: sin hijo izquierdo
                return nodo.getRight();
            }
            if (nodo.getRight() == null) { //caso: sin hijo derecho
                return nodo.getLeft();
            }

            //caso: dos hijos
            NodoBinario<T> sucesor = obtenerMinimo(nodo.getRight()); //se busca el sucesor

            nodo.setData(sucesor.getData()); //se reemplaza el valor del nodo

            nodo.setRight(eliminarAVL(nodo.getRight(), sucesor.getData())); //se elimina el sucesor duplicado
        }

        return equilibrarNodo(nodo); //se reequilibra al volver de la recursión
    }


    @Override
    public NodoBinario<T> buscar(T data) { //busca un nodo en el árbol
        return buscarEnBST(raiz, data); //usa la búsqueda del BST base
    }


    private int calcularFactorBalance(NodoBinario<T> nodo) { //calcula el factor de balance del nodo

        if (nodo == null) return 0; //si no hay nodo, no hay balance

        int izq = altura.calcularAltura(nodo.getLeft()); //altura del subárbol izquierdo
        int der = altura.calcularAltura(nodo.getRight()); //altura del subárbol derecho

        return izq - der; //factor de balance
    }


    private NodoBinario<T> equilibrarNodo(NodoBinario<T> nodo) { //equilibra el nodo si está descompensado

        int fb = calcularFactorBalance(nodo); //factor de balance del nodo

        if (fb > 1) { //caso: pesado a la izquierda

            if (calcularFactorBalance(nodo.getLeft()) < 0) { //caso izquierda-derecha
                nodo.setLeft(rotarIzquierda(nodo.getLeft()));
            }

            return rotarDerecha(nodo); //rotación simple derecha
        }

        if (fb < -1) { //caso: pesado a la derecha

            if (calcularFactorBalance(nodo.getRight()) > 0) { //caso derecha-izquierda
                nodo.setRight(rotarDerecha(nodo.getRight()));
            }

            return rotarIzquierda(nodo); //rotación simple izquierda
        }

        return nodo; //si está equilibrado no se modifica
    }

    private NodoBinario<T> rotarDerecha(NodoBinario<T> y) { //rotación simple a la derecha

        NodoBinario<T> x = y.getLeft(); //nuevo nodo raíz
        NodoBinario<T> temp = x.getRight(); //subárbol intermedio

        x.setRight(y); //recoloca el nodo
        y.setLeft(temp); //reconecta el subárbol

        return x; //devuelve la nueva raíz
    }

    private NodoBinario<T> rotarIzquierda(NodoBinario<T> x) { //rotación simple a la izquierda

        NodoBinario<T> y = x.getRight(); //nuevo nodo raíz
        NodoBinario<T> temp = y.getLeft(); //subárbol intermedio

        y.setLeft(x); //recoloca el nodo
        x.setRight(temp); //reconecta el subárbol

        return y; //devuelve la nueva raíz
    }
}