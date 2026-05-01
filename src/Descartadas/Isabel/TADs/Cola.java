package TADs;

public class Cola<T> {
    private ElementoDobleEnlazada<T>first,last;
    int size = 0;

    public boolean isEmpty() {
        return first == null;
    }

    public void add(T elemento) {
        ElementoDobleEnlazada<T> nuevo = new ElementoDobleEnlazada<>(elemento);
        if (isEmpty()) {
            first = last = nuevo;
        }
        else{
            nuevo.before = last;
            last.next = nuevo;
            last = nuevo;
        }
        size++;
    }

    public T dequeue(){
        if (isEmpty()){
            System.out.println("La cola está vacía.");
            return null;
        }
        T elemento = first.elemento;
        first = first.next;
        if (first == null){
            last = null;
        }
        else{
            first.before = null;
        }
        size--;
        return elemento;
    }

    public T top(){
        if (isEmpty()){
            System.out.println("La cola está vacía.");
            return null;
        }
        return last.elemento;
    }

    public void clear(){
        first = null;
        last = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }


}
