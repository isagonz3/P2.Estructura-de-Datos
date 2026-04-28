package Carolina.estructuras_necesarias;

public class ListSE<T extends Comparable<T>> implements MyList<T> {

    // Atributos: protegidos para permitir acceso desde clases del mismo paquete o heredadas
    protected ElementSE<T> first; //puntero al primer nodo de la lista
    protected int size; //contador de elementos almacenados en la lista


    // Constructor
    public ListSE() { //inicializa la lista vacía
        this.first = null; //no hay primer nodo
        this.size = 0;     //tamaño inicial 0
    }


    @Override
    public void add(T data) { //inserta un elemento al inicio de la lista (operación típica en listas simples)
        ElementSE<T> nuevo = new ElementSE<>(data);
        nuevo.setNext(first); //el nuevo nodo apunta al antiguo primero
        first = nuevo; //la cabeza pasa a ser el nuevo nodo
        size++; //se incrementa el tamaño
    }

    @Override
    public T get(T data) { //busca un elemento por su valor recorriendo desde la cabeza
        ElementSE<T> actual = first;
        while (actual != null) { //recorrido secuencial
            if (actual.getData().compareTo(data) == 0) {
                return actual.getData(); //si coincide, se devuelve
            }
            actual = actual.getNext(); //avanza al siguiente nodo
        }
        return null; //si no se encuentra, devuelve null
    }

    @Override
    public T del(T data) { //elimina un nodo cuyo valor coincida con "data"

        if (first == null) {
            return null; //lista vacía
        }

        //caso 1: el nodo a eliminar es el primero
        if (first.getData().compareTo(data) == 0) {
            T eliminado = first.getData();
            first = first.getNext(); //la cabeza pasa al siguiente nodo
            size--;
            return eliminado;
        }

        //caso 2: buscar el nodo anterior al que queremos eliminar
        ElementSE<T> actual = first;
        while (actual.getNext() != null &&
                actual.getNext().getData().compareTo(data) != 0) {
            actual = actual.getNext(); //avanza nodo a nodo
        }

        if (actual.getNext() == null) {
            return null; //no encontrado
        }

        T eliminado = actual.getNext().getData(); //se elimina el nodo saltándolo en la cadena
        actual.setNext(actual.getNext().getNext());
        size--;
        return eliminado;
    }

    @Override
    public boolean isEmpty() {
        return first == null; //la lista está vacía si no hay primer nodo
    }

    @Override
    public void addLast(T data) { //inserta un elemento al final de la lista
        ElementSE<T> newNode = new ElementSE<>(data);

        if (isEmpty()) { //si está vacía, el nuevo nodo es la cabeza
            first = newNode;
        } else { //si no, se recorre hasta el último nodo
            ElementSE<T> current = first;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode); //el último apunta al nuevo nodo
        }
        size++;
    }

    @Override
    public int getSize() { //devuelve el número de elementos
        return size;
    }

    @Override
    public MyIterate<T> getIterate() { //devuelve un iterador que recorre la lista desde el primer nodo
        return new ReiterateLSE<>(first);
    }

    @Override
    public void clear() { //vacía completamente la lista
        first = null;
        size = 0;
    }

    @Override
    public T get(int index) { //devuelve el elemento en una posición concreta
        if (index < 0 || index >= size) {
            return null; //índice fuera de rango
        }
        ElementSE<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext(); //recorre nodo a nodo
        }
        return current.getData();
    }

    @Override
    public int indexOf(T data) { //busca la posición de un elemento en la lista
        ElementSE<T> current = first;
        int index = 0;

        while (current != null) {
            if (current.getData().equals(data)) {
                return index; //si coincide, devuelve la posición
            }
            current = current.getNext();
            index++;
        }
        return -1; //no encontrado
    }

    @Override
    public boolean contains(T data) { //comprueba si un elemento está en la lista
        ElementSE<T> current = first;
        while (current != null) {
            if (current.getData().equals(data)) {
                return true; //encontrado
            }
            current = current.getNext();
        }
        return false; //no encontrado
    }


    // Métodos adicionales necesarios en matrices
    public void set(int index, T value) { //modifica el valor de un nodo en una posición concreta
        if (index < 0 || index >= size) return;

        ElementSE<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setData(value);
    }

    public ElementSE<T> getFirst() { //devuelve el primer nodo
        return first;
    }

    @Override
    public String toString() {
        String resultado = "[";

        MyIterate<T> it = this.getIterate();

        while (it.hasNext()) {
            resultado = resultado + it.next();

            if (it.hasNext()) {
                resultado = resultado + ", ";
            }
        }

        resultado = resultado + "]";
        return resultado;
    }

    public int size() {
        int contador = 0;
        ElementSE<T> actual = first;

        while (actual != null) {
            contador++;
            actual = actual.getNext();
        }

        return contador;
    }

}