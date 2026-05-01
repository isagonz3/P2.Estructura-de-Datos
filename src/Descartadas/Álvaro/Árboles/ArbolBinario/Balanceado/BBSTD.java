package Árboles.ArbolBinario.Balanceado;

import EstructurasP1.IndexedList;
import Nodos.Arboles.Binarios.BinaryNodeDuplicate;
import Árboles.ArbolBinario.AbstractBalancedBinaryTree;
import Árboles.DuplicateSearchTree;

public class BBSTD<T extends Comparable<T>> extends AbstractBalancedBinaryTree<T, BinaryNodeDuplicate<T>> implements DuplicateSearchTree<T> {

    @Override
    protected BBSTD<T> emptyTree() {
        return new BBSTD<>();
    }

    @Override
    public void add(T data) {
        root=add(root, data);
    }

    private BinaryNodeDuplicate<T> add(BinaryNodeDuplicate<T> node, T data) {
        BinaryNodeDuplicate<T> result;
        if(node==null){
            result=new BinaryNodeDuplicate<>(data);
        }
        else{
            if(data.compareTo(node.getData())<0) {
                node.setLeft(add(node.getLeft(),data));
                node.updateHeight();
                result=balance(node);
            }
            else if(data.compareTo(node.getData()) > 0) {
                node.setRight(add(node.getRight(),data));
                node.updateHeight();
                result=balance(node);
            }
            else{
                node.addData(data);
                result=node;
            }
        }
        return result;
    }

    //Codigo idéntico al de BSTD
    @Override
    public void removeOne(T data){
        BinaryNodeDuplicate<T> node=search(data);
        if(node!=null){
            IndexedList<T> list=node.getAllData();
            if(list.len()>1){
                list.delete(0);
            }
            else {
                remove(data);
            }
        }
    }
}
