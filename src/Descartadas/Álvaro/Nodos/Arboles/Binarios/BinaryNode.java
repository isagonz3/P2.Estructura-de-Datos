package Nodos.Arboles.Binarios;

public class BinaryNode<T> extends AbstractBinaryNode<T,BinaryNode<T>> {

    private T data;

    //CONSTRUCTORES

    public BinaryNode(T data){
        this.data=data;
        this.left=null;
        this.right=null;
    }

    //GETTERS

    @Override
    public T getData() {
        return data;
    }
}
