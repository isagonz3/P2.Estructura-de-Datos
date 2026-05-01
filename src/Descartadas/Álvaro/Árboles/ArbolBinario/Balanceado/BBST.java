package Árboles.ArbolBinario.Balanceado;

import Nodos.Arboles.Binarios.BinaryNode;
import Árboles.ArbolBinario.AbstractBalancedBinaryTree;

public class BBST<T extends Comparable<T>> extends AbstractBalancedBinaryTree<T, BinaryNode<T>> {

    @Override
    protected BBST<T> emptyTree() {
        return new BBST<>();
    }

    @Override
    public void add(T data) {
        root=add(root,data);
    }

    private BinaryNode<T> add(BinaryNode<T> node,T data) {
        BinaryNode<T> result=node;
        if (node==null) {
            result= new BinaryNode<>(data);
        }
        else{
            if (data.compareTo(node.getData())<0) {
                node.setLeft(add(node.getLeft(),data));
            }
            else if (data.compareTo(node.getData())>0) {
                node.setRight(add(node.getRight(),data));
            }
            node.updateHeight();
            result=balance(node);
        }
        return result;
    }
}

