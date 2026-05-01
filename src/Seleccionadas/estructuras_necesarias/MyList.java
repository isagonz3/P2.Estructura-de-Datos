package Seleccionadas.estructuras_necesarias;

public interface MyList <T extends Comparable <T>> {

    void add(T data);
    T get(T data);
    T del(T data);
    boolean isEmpty();

    void addLast(T data);

    int getSize();
    MyIterate<T> getIterate();

    void clear();
    T get(int index);
    int indexOf(T data);
    boolean contains(T data);
}