package Nodos.Arboles.Binarios;


import EstructurasP1.IndexedList;

public abstract class AbstractBinaryNode<T,N extends AbstractBinaryNode<T,N>> {
    protected N left;
    protected N right;
    protected int height;


    public AbstractBinaryNode(){
        height=1;
    }

    //GETTERS


    public abstract T getData();

    public IndexedList<T> getDataList(){
        IndexedList<T> list=new IndexedList<>();
        list.append(getData());
        return list;
    }

    public N getLeft() {
        return left;
    }

    public N getRight() {
        return right;
    }

    //SETTERS

    public void setLeft(N left) {
        this.left = left;
    }

    public void setRight(N right) {
        this.right = right;
    }


    //MÉTODOS

    public int getHeight() {
        return height;
    }

    public int getDegree() {
        int degree=0;
        if (left!=null){
            degree++;
        }
        if (right!=null){
            degree++;
        }
        return degree;
    }

    public void updateHeight() {
        int leftHeight=0;
        int rightHeight=0;
        if (left!=null){
            leftHeight=left.getHeight();
        }
        if (right!=null){
            rightHeight=right.getHeight();
        }
        height=1+ Math.max(leftHeight, rightHeight);
    }


    //Métodos necesarios para las funciones PRE,POST,INorder
    public void getPreOrder(IndexedList<T> list) {
        list.append(getData());
        if (left != null) {
            left.getPreOrder(list);
        }

        if (right != null) {
            right.getPreOrder(list);
        }
    }

    public void getInOrder(IndexedList<T> list) {
        if (left!=null) {
            left.getInOrder(list);
        }
        list.append(getData());
        if (right!=null) {
            right.getInOrder(list);
        }
    }

    public void getPostOrder(IndexedList<T> list) {
        if (left!=null) {
            left.getPostOrder(list);
        }
        if (right!=null) {
            right.getPostOrder(list);
        }
        list.append(getData());
    }
}
