package Descartadas.Carolina.arboles.arbol_recursivo.interfaces;

import Descartadas.Carolina.estructuras_necesarias.MyList;

public interface Arbol<T extends Comparable<T>> { //interfaz base de cualquier árbol

    void add(T data); //añade un elemento al árbol
    boolean isEmpty(); //comprueba si el árbol está vacío

    int getGrado(); //devuelve el grado del árbol
    int getAltura(); //devuelve la altura del árbol

    MyList<T> getListaDatosNivel(int nivel); //devuelve los nodos de un nivel concreto
    MyList<T> getCamino(T data); //devuelve el camino desde la raíz hasta un dato

    boolean isArbolHomogeneo(); //comprueba si todos los nodos siguen la misma estructura de hijos
    boolean isArbolCompleto(); //comprueba si el árbol es completo
    boolean isArbolCasiCompleto(); //comprueba si el árbol es casi completo

    MyList<T> getListaPreOrden(); //recorrido preorden
    MyList<T> getListaPostOrden(); //recorrido postorden
    MyList<T> getListaOrdenCentral(); //recorrido inorden
}