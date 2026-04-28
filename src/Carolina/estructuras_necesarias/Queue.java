package Carolina.estructuras_necesarias;

public class Queue<T> {

    //Atributos
    private ElementSE<T> first; //primer nodo de la cola, desde donde se desencolan los elementos
    private ElementSE<T> last;  //último nodo de la cola, donde se encolan los nuevos elementos


    //Constructor
    public Queue() { //constructor por defecto donde se inicializa la cola vacía
        first = null; //no hay primer elemento
        last = null;  //no hay último elemento
    }


    //Métodos
    public void enqueue(T valor) { //metodo para encolar un elemento, equivalente a añadirlo al final de la cola
        ElementSE<T> aux = new ElementSE<>(valor); //se crea un nuevo nodo con el valor recibido
        if (first == null) { //si la cola está vacía
            first = aux; //el nuevo nodo pasa a ser el primero
            last = aux;  //y también el último
        } else { //si ya hay elementos en la cola
            last.setNext(aux); //se enlaza el último nodo actual con el nuevo nodo
            last = aux;        //el nuevo nodo pasa a ser el último de la cola
        }
    }

    public T dequeue() { //metodo para desencolar, devuelve el primer elemento que entró en la cola
        if (first == null) { //si no hay elementos en la cola
            System.out.println("La cola está vacía");
            return null;
        }
        T value = first.getData(); //se obtiene el dato almacenado en el primer nodo
        first = first.getNext();   //se avanza el puntero al siguiente nodo de la cola
        if (first == null) {       //si después de avanzar ya no queda ningún nodo
            last = null;           //la cola queda completamente vacía
        }
        return value; //se devuelve el valor desencolado
    }

    public T peek() { //devuelve el primer elemento de la cola sin eliminarlo
        if (first == null) { //si la cola está vacía
            System.out.println("La cola está vacía");
            return null;
        }
        return first.getData(); //se devuelve el dato del primer nodo
    }

    public boolean isEmpty() {return first == null;} //la cola está vacía si no hay primer nodo

    public ElementSE<T> getFirst() { //devuelve el nodo inicial de la cola (útil para estructuras que la usan internamente)
        return first;
    }
}