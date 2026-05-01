package Descartadas.Carolina.arboles.arbol_recursivo.binario.arbol_busqueda;

import Descartadas.Carolina.arboles.arbol_recursivo.binario.metodos_comunes.Profundidad;
import Descartadas.Carolina.arboles.arbol_recursivo.binario.nodo.NodoBinario;
import Descartadas.Carolina.estructuras_necesarias.MyList;

public class ArbolBSTConDuplicados<T extends Comparable<T>> extends ArbolBSTBase<T> { //árbol BST que permite duplicados

    @Override
    public void insertar(T data) { //inserta un dato en el árbol
        raiz = insertarConDuplicados(raiz, data); //llama al metodo recursivo desde la raíz
    }

    private NodoBinario<T> insertarConDuplicados(NodoBinario<T> nodo, T data) { //inserción permitiendo duplicados

        if (nodo == null) {  //si el nodo es nulo, se crea uno nuevo
            return new NodoBinario<>(data);
        }

        int cmp = data.compareTo(nodo.getData()); //compara el dato con el nodo actual

        if (cmp < 0) { //si es menor, va al subárbol izquierdo
            nodo.setLeft(insertarConDuplicados(nodo.getLeft(), data));
        }
        else { //si es mayor o igual, va al subárbol derecho (incluye duplicados)
            nodo.setRight(insertarConDuplicados(nodo.getRight(), data));
        }

        return nodo; //devuelve el nodo actualizado
    }

    @Override
    public NodoBinario<T> buscar(T data) { //busca un dato en el árbol
        return buscarConDuplicados(raiz, data); //llama al metodo recursivo
    }

    private NodoBinario<T> buscarConDuplicados(NodoBinario<T> nodo, T data) { //búsqueda en BST con duplicados

        if (nodo == null) { //si no existe el nodo, devuelve null
            return null;
        }

        int cmp = data.compareTo(nodo.getData()); //compara el dato

        if (cmp == 0) { //si coincide, devuelve el nodo
            return nodo;
        }

        if (cmp < 0) { //si es menor, busca a la izquierda
            return buscarConDuplicados(nodo.getLeft(), data);
        }

        return buscarConDuplicados(nodo.getRight(), data); //si es mayor, busca a la derecha
    }

    @Override
    public void eliminar(T data) { //elimina un nodo del árbol
        raiz = eliminarConDuplicados(raiz, data); //llama al metodo recursivo
    }

    private NodoBinario<T> eliminarConDuplicados(NodoBinario<T> nodo, T data) { //eliminación en BST con duplicados

        if (nodo == null) { //si no existe el nodo, no hace nada
            return null;
        }

        int cmp = data.compareTo(nodo.getData()); //compara el dato

        if (cmp < 0) { //si es menor, busca en la izquierda
            nodo.setLeft(eliminarConDuplicados(nodo.getLeft(), data));
        }
        else if (cmp > 0) { //si es mayor, busca en la derecha
            nodo.setRight(eliminarConDuplicados(nodo.getRight(), data));
        }
        else { //si encuentra el nodo a eliminar

            if (nodo.getLeft() == null) { //caso: sin hijo izquierdo
                return nodo.getRight();
            }
            if (nodo.getRight() == null) { //caso: sin hijo derecho
                return nodo.getLeft();
            }

            NodoBinario<T> sucesor = obtenerMinimo(nodo.getRight()); //se busca el sucesor (mínimo del subárbol derecho)

            nodo.setData(sucesor.getData()); //se reemplaza el valor del nodo

            nodo.setRight(eliminarConDuplicados(nodo.getRight(), sucesor.getData())); //se elimina el sucesor duplicado
        }

        return nodo; //devuelve el nodo actualizado
    }

    private NodoBinario<T> obtenerMinimo(NodoBinario<T> nodo) { //busca el nodo con menor valor
        while (nodo.getLeft() != null) nodo = nodo.getLeft(); //baja siempre por la izquierda
        return nodo; //devuelve el mínimo
    }


    @Override
    public int getGrado() {
        return 0;
    }


    @Override
    public MyList<T> getListaDatosNivel(int nivel) {
        return null;
    }


    @Override
    public MyList<T> getCamino(T data) {
        return null;
    }
}