package Seleccionadas.estructuras_necesarias;

public class ReiterateLSE <T> implements MyIterate<T> {

    //Atributos
    private ElementSE<T> actual; //nodo actual que se está recorriendo en la lista simple


    //Constructor
    public ReiterateLSE(ElementSE<T> first) { //el iterador comienza en el primer nodo de la lista simplemente enlazada
        this.actual=first;
    }


    //Métodos
    @Override
    public boolean hasNext() {
        return actual != null;//hay siguiente mientras el nodo actual no sea nulo
    }

    @Override
    public T next() {
        T data = actual.getData(); //se obtiene el dato almacenado en el nodo actual
        actual = actual.getNext(); //se avanza al siguiente nodo usando getNext de la lista simple
        return data; //se devuelve el dato leído
    }
}