package Árboles.ArbolBinario.Simple;

import Nodos.Arboles.Binarios.BinaryNode;
import Árboles.ArbolBinario.AbstractBinaryTree;

public class BST<T extends Comparable<T>> extends AbstractBinaryTree<T, BinaryNode<T>> {

    @Override
    protected BST<T> emptyTree() {
        return new BST<>();
    }

    @Override
    public void add(T data) {
        root=add(root, data);
    }

    private BinaryNode<T> add(BinaryNode<T> node, T data) {
        BinaryNode<T> result;
        if (node==null) {
            result=new BinaryNode<>(data);
        }
        else{
            if(data.compareTo(node.getData())<0) {
                node.setLeft(add(node.getLeft(),data));
            }
            else if(data.compareTo(node.getData())>0) {
                node.setRight(add(node.getRight(),data));
            }
            node.updateHeight();
            result=node;
        }
        return result;
    }
}
