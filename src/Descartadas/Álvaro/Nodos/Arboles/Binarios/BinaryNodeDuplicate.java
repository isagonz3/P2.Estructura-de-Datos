package Nodos.Arboles.Binarios;

import EstructurasP1.IndexedList;

public class BinaryNodeDuplicate<T> extends AbstractBinaryNode<T,BinaryNodeDuplicate<T>> {
    private IndexedList<T> data;

    //CONSTRUCTORES

    public BinaryNodeDuplicate(T data){
        this.data=new IndexedList<>();
        this.data.append(data);
        this.left=null;
        this.right=null;
    }

    //GETTERS

    @Override
    public T getData() {
        return data.get(0);
    }

    public IndexedList<T> getAllData() {
        return data;
    }

    //MÉTODOS

    public void addData(T element){
        data.append(element);
    }

    @Override
    public IndexedList<T> getDataList(){
        return data;
    }
}
