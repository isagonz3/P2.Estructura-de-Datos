package Descartadas.Carolina.arbol.arbol_iterativo;

public class NodoArbol<T> {

    //Atributos
    public T dato;
    public NodoArbol<T> hijoIzquierdo; //a diferencia de las listas, en un árbol se necesita un puntero para el nodo de la derecha y otro para el de la izquierda
    public NodoArbol<T> hijoDerecho;


    //Constructores
    public NodoArbol(T dato) { //constructor por defecto que inicializa los atributos
        this.dato = dato;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }
}