package TADs;

public class Pila<T extends Comparable<T>> {
    private ElementoSimpleEnlazada<T> first;
    private int size;

    public boolean isEmpty() {
        return first == null;
    }

    public void push(T elemento) {
        ElementoSimpleEnlazada<T> nuevo = new ElementoSimpleEnlazada<>(elemento);
        nuevo.elemento = elemento;
        nuevo.next = first;
        first = nuevo;
        size++;
    }

    public T pop() {
        if(isEmpty()) {
            System.out.println("La pila está vacía.");
            return null;
        }
        T  elemento = first.elemento;
        first = first.next;
        size--;
        return elemento;
    }

    public T top() {
        if (isEmpty()) {
            System.out.println("La pila está vacía.");
            return null;
        }
        return first.elemento;
    }

    public void clear() {
        first = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

}

