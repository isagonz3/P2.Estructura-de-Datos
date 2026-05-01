package Descartadas.Carolina.arboles.arbol_recursivo.binario.nodo;

public class NodoBinario<T extends Comparable<T>> implements Comparable<NodoBinario<T>> { //nodo base de un árbol binario

    //Atributos
    public T data; //dato que guarda el nodo
    public NodoBinario<T> left; //hijo izquierdo
    public NodoBinario<T> right; //hijo derecho


    //Constructor
    public NodoBinario(T data) { //constructor del nodo
        this.data = data; //asigna el dato
        this.left = null; //inicialmente sin hijo izquierdo
        this.right = null; //inicialmente sin hijo derecho
    }


    //Métodos
    public T getData() {return data;} //devuelve el dato del nodo

    public void setData(T data) {this.data = data;} //modifica el dato del nodo


    public NodoBinario<T> getLeft() {return left;} //devuelve el hijo izquierdo

    public void setLeft(NodoBinario<T> left) {this.left = left;} //asigna un hijo izquierdo


    public NodoBinario<T> getRight() { return right; } //devuelve el hijo derecho

    public void setRight(NodoBinario<T> right) { this.right = right; } //asigna un hijo derecho


    public boolean isLeaf() { //comprueba si el nodo es hoja
        return left == null && right == null; //no tiene hijos
    }


    @Override
    public int compareTo(NodoBinario<T> o) {
        return 0; //siempre devuelve 0 porque no se usa directamente
    }


    @Override
    public String toString() { //representación legible del nodo
        return String.valueOf(data); //muestra solo el valor del nodo
    }
}