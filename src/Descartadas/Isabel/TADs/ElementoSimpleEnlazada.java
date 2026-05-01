package Descartadas.Isabel.TADs;

public class ElementoSimpleEnlazada<T> {
    T elemento;
    public ElementoSimpleEnlazada<T> next;

    public ElementoSimpleEnlazada(T elemento) {
        this.elemento = elemento;
        this.next = null;
    }

}

