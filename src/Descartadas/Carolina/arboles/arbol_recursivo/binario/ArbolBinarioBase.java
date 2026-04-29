package Descartadas.Carolina.arboles.arbol_recursivo.binario;

import Descartadas.Carolina.arboles.arbol_recursivo.binario.metodos_comunes.GraficadorBinario;
import Descartadas.Carolina.arboles.arbol_recursivo.binario.metodos_comunes.Recorridos;
import Descartadas.Carolina.arboles.arbol_recursivo.binario.metodos_comunes.ValidadorBinario;
import Descartadas.Carolina.arboles.arbol_recursivo.binario.nodo.NodoBinario;
import Descartadas.Carolina.arboles.arbol_recursivo.interfaces.Arbol;
import Descartadas.Carolina.estructuras_necesarias.ListSE;
import Descartadas.Carolina.estructuras_necesarias.MyList;

public abstract class ArbolBinarioBase<T extends Comparable<T>> implements Arbol<T> { //clase base abstracta para árboles binarios genéricos

    //Atributos
    protected NodoBinario<T> raiz; //raíz del árbol


    //Constructores
    public ArbolBinarioBase() {this.raiz = null;}


    //Métodos
    @Override
    public boolean isEmpty() {return raiz == null;} //comprueba si el árbol está vacío

    @Override
    public int getGrado() { //devuelve el grado del árbol, es decir, el máximo número de hijos de cualquier nodo
        return grado(raiz); //empieza desde la raíz
    }

    private int grado(NodoBinario<T> nodo) { //calcula el grado del árbol
        if (nodo == null) return 0; //el nodo no tiene hijos

        int hijos = 0; //se inicializa un contador para los hijos en total

        if (nodo.getLeft() != null) hijos = hijos + 1; //el nodo tiene un hijo izquierdo
        if (nodo.getRight() != null) hijos = hijos + 1; //el nodo tiene un hijo derecho

        int izq = grado(nodo.getLeft());
        int der = grado(nodo.getRight());

        if (izq > hijos) hijos = izq;
        if (der > hijos) hijos = der;

        return hijos;
    }

    @Override
    public int getAltura() {return altura(raiz);} //devuelve la altura del árbol

    private int altura(NodoBinario<T> nodo) { //calcula la altura del árbol de forma recursiva
        if (nodo == null) return -1; //si no hay nodo no cuenta

        int izq = altura(nodo.getLeft()); //altura izquierda
        int der = altura(nodo.getRight()); //altura derecha

        if (izq > der) { //compara a mano
            return izq + 1;
        } else {
            return der + 1;
        }
    }

    @Override
    public MyList<T> getListaDatosNivel(int nivel) { //devuelve los nodos que están en un nivel concreto
        MyList<T> lista = new ListSE<>(); //lista donde se guarda el resultado
        getDatosNivelRec(raiz, 0, nivel, lista); //llamada al metodo recursivo empezando en nivel 0
        return lista; //devuelve la lista
    }

    private void getDatosNivelRec(NodoBinario<T> nodo, int actual, int objetivo, MyList<T> lista) { //recorre el árbol buscando el nivel
        if (nodo == null) return; //si no hay nodo no se hace nada

        if (actual == objetivo) { //si estamos en el nivel buscado
            lista.addLast(nodo.getData()); //añadimos el dato a la lista
        }
        else { //seguimos bajando aumentando el nivel
            getDatosNivelRec(nodo.getLeft(), actual + 1, objetivo, lista);
            getDatosNivelRec(nodo.getRight(), actual + 1, objetivo, lista);
        }
    }

    @Override
    public boolean isArbolHomogeneo() {return ValidadorBinario.esHomogeneo(raiz);} //comprueba si el árbol es homogéneo

    @Override
    public boolean isArbolCompleto() {return ValidadorBinario.esCompleto(raiz);} //comprueba si el árbol es completo

    @Override
    public boolean isArbolCasiCompleto() {return ValidadorBinario.esCasiCompleto(raiz);} //comprueba si el árbol es casi completo

    @Override
    public MyList<T> getCamino(T data) { //devuelve el camino desde la raíz hasta un dato
        ListSE<T> camino = new ListSE<>(); //lista donde se guarda el camino

        if (buscarCamino(raiz, data, camino)) { //si encuentra el camino
            return camino; //devuelve la lista
        }

        return new ListSE<>(); //si no lo encuentra devuelve lista vacía
    }

    private boolean buscarCamino(NodoBinario<T> nodo, T data, ListSE<T> camino) { //busca el camino de forma recursiva
        if (nodo == null) return false; //si no hay nodo no se encuentra ningún camino

        camino.addLast(nodo.getData()); //añade el nodo actual al camino

        int cmp = data.compareTo(nodo.getData()); //compara el dato buscado con el actual

        if (cmp == 0) return true; //si son iguales, ya se ha encontrado

        if (cmp < 0) { //si es menor va al subárbol izquierdo
            if (buscarCamino(nodo.getLeft(), data, camino)) return true;
        }
        else { //si es mayor va al subárbol derecho
            if (buscarCamino(nodo.getRight(), data, camino)) return true;
        }

        camino.del(nodo.getData()); //si no era el camino correcto se elimina del camino
        return false; //no se ha encontrado por esta rama
    }

    @Override
    public MyList<T> getListaPreOrden() {return Recorridos.preOrden(raiz);} //recorrido preorden

    @Override
    public MyList<T> getListaPostOrden() {return Recorridos.postOrden(raiz);} //recorrido postorden

    @Override
    public MyList<T> getListaOrdenCentral() {return Recorridos.inOrden(raiz);} //recorrido inorden

    @Override
    public String toString() {return GraficadorBinario.toString(raiz);} //representación en texto del árbol
}