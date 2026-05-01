package Nodos.Arboles.Narios;


import EstructurasP1.IndexedList;

public class NaryNode<T>{
    private T data;
    private IndexedList<NaryNode<T>> children;

    //CONSTRUCTORES

    public NaryNode(T data){
        this.data=data;
        this.children=new IndexedList<>();
    }

    //GETTERS

    public T getData() {
        return data;
    }

    public IndexedList<NaryNode<T>> getChildren() {
        return children;
    }

    //MÉTODOS

    public void addChild(NaryNode<T>child){
        children.append(child);
    }
}
