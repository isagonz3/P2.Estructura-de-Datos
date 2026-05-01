package Descartadas.Carolina.estructuras_necesarias;

public class Stack <T> {

    private ElementSE<T> top; //nodo que representa la cima de la pila, desde donde se apilan y desapilan los elementos


    public Stack() {top = null;} //constructor por defecto donde la pila se inicializa vacía


    public void push(T valor) { //metodo para apilar un elemento, equivalente a colocarlo en la parte superior de la pila
        ElementSE<T> aux = new ElementSE<>(valor); //se crea un nuevo nodo con el valor recibido
        aux.setNext(top); //el nuevo nodo apunta al nodo que antes era la cima
        top = aux; //la cima de la pila pasa a ser el nuevo nodo
    }

    public T pop() { //metodo para desapilar, devuelve el elemento que está en la cima de la pila
        if (top == null) { //si no hay ningún nodo, la pila está vacía
            System.out.println("La pila está vacía");
            return null;
        }
        T value = top.getData(); //se obtiene el dato almacenado en la cima
        top = top.getNext(); //la cima pasa a ser el siguiente nodo de la lista
        return value; //se devuelve el valor desapilado
    }

    public T peek() { //devuelve el elemento de la cima sin eliminarlo
        if (top == null) { //si la pila está vacía, no hay nada que consultar
            System.out.println("La pila está vacía");
            return null;
        }
        return top.getData(); //se devuelve el dato del nodo superior
    }

    public boolean isEmpty() {return top == null;} //la pila está vacía si no hay nodo en la cima
}