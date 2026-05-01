package Descartadas.Carolina.arboles.arbol_recursivo.binario.arbol_busqueda;

import Descartadas.Carolina.arboles.arbol_recursivo.binario.metodos_comunes.Profundidad;
import Descartadas.Carolina.arboles.arbol_recursivo.binario.nodo.NodoBinario;
import Descartadas.Carolina.estructuras_necesarias.MyList;

public class ArbolBSTSinDuplicados<T extends Comparable<T>> extends ArbolBSTBase<T> { //árbol BST sin permitir valores repetidos

    @Override
    public void insertar(T data) { //inserta un dato en el árbol sin duplicados
        raiz = insertarEnBST(raiz, data); //llama al metodo recursivo desde la raíz
    }

    private NodoBinario<T> insertarEnBST(NodoBinario<T> nodo, T data) { //inserción típica de BST sin duplicados

        if (nodo == null) { //si el nodo es nulo, se crea uno nuevo
            return new NodoBinario<>(data);
        }

        int cmp = data.compareTo(nodo.getData()); //compara el dato con el nodo actual

        if (cmp < 0) { //si es menor, va al subárbol izquierdo
            nodo.setLeft(insertarEnBST(nodo.getLeft(), data));
        }
        else if (cmp > 0) { //si es mayor, va al subárbol derecho
            nodo.setRight(insertarEnBST(nodo.getRight(), data));
        }
        //si es igual, no se inserta (evita duplicados)

        return nodo; //devuelve el nodo actualizado
    }


    @Override
    public NodoBinario<T> buscar(T data) { //busca un dato en el árbol
        return buscarEnBST(raiz, data); //llama al metodo recursivo
    }

    protected NodoBinario<T> buscarEnBST(NodoBinario<T> nodo, T data) { //búsqueda en BST sin duplicados

        if (nodo == null) { //si no existe el nodo, devuelve null
            return null;
        }

        int cmp = data.compareTo(nodo.getData()); //compara el dato

        if (cmp == 0) { //si coincide, devuelve el nodo
            return nodo;
        }

        if (cmp < 0) { //si es menor, busca en la izquierda
            return buscarEnBST(nodo.getLeft(), data);
        }

        return buscarEnBST(nodo.getRight(), data); //si es mayor, busca en la derecha
    }


    @Override
    public void eliminar(T data) { //elimina un nodo del árbol
        raiz = eliminarEnBST(raiz, data); //llama al metodo recursivo
    }

    private NodoBinario<T> eliminarEnBST(NodoBinario<T> nodo, T data) { //eliminación en BST sin duplicados

        if (nodo == null) { //si no existe el nodo, no hace nada
            return null;
        }

        int cmp = data.compareTo(nodo.getData()); //compara el dato

        if (cmp < 0) { //si es menor, busca en la izquierda
            nodo.setLeft(eliminarEnBST(nodo.getLeft(), data));
        }
        else if (cmp > 0) { //si es mayor, busca en la derecha
            nodo.setRight(eliminarEnBST(nodo.getRight(), data));
        }
        else { //si encuentra el nodo a eliminar

            if (nodo.getLeft() == null) { //caso: sin hijo izquierdo
                return nodo.getRight();
            }
            if (nodo.getRight() == null) { //caso: sin hijo derecho
                return nodo.getLeft();
            }

            NodoBinario<T> sucesor = obtenerMinimo(nodo.getRight()); //busca el sucesor (mínimo del subárbol derecho)

            nodo.setData(sucesor.getData()); //reemplaza el valor del nodo por el sucesor

            nodo.setRight(eliminarEnBST(nodo.getRight(), sucesor.getData())); //elimina el sucesor duplicado
        }

        return nodo; //devuelve el nodo actualizado
    }

    protected NodoBinario<T> obtenerMinimo(NodoBinario<T> nodo) { //busca el nodo con menor valor
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