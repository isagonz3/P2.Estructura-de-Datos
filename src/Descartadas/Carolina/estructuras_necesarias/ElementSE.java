package Descartadas.Carolina.estructuras_necesarias;

public class ElementSE<T> {

    //Atributos
    private T data; //dato que almacena el nodo
    private ElementSE<T> next; // declaramos el siguiente elemento al que nos encontramos


    //Constructor
    public ElementSE(T data) {
        this.data = data; //se guarda el dato recibido
        this.next = null; //al crearse un nodo nuevo no apunta a ningún siguiente
    }


    //Métodos
    public T getData() {return data;} //devuelve el dato almacenado en el nodo

    public ElementSE<T> getNext() {return next;} //getters: permite leer y devolver el valor de un atributo privado

    public void setNext(ElementSE<T> next) {this.next=next;} // setters: permite modificar el valor de un atributo privado

    public void setData(T data) { this.data = data; }
}