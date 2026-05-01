package Árboles.ArbolBinario.Simple;

import EstructurasP1.IndexedList;
import Nodos.Arboles.Binarios.BinaryNodeDuplicate;
import Árboles.ArbolBinario.AbstractBinaryTree;
import Árboles.DuplicateSearchTree;

public class BSTD<T extends Comparable<T>> extends AbstractBinaryTree<T,BinaryNodeDuplicate<T>> implements DuplicateSearchTree<T> {

    @Override
    protected BSTD<T> emptyTree() {
        return new BSTD<>();
    }

    @Override
    public BinaryNodeDuplicate<T> getRoot() {
        return root;
    }


    @Override
    public void add(T data) {
        root=add(root,data);
    }

    private BinaryNodeDuplicate<T> add(BinaryNodeDuplicate<T> node, T data) {
        BinaryNodeDuplicate<T> result;

        if (node == null) {
            result = new BinaryNodeDuplicate<>(data);
        }
        else {
            if (data.compareTo(node.getData()) < 0) {
                node.setLeft(add(node.getLeft(), data));
            }
            else if (data.compareTo(node.getData()) > 0) {
                node.setRight(add(node.getRight(), data));
            }
            else {
                node.addData(data);
            }

            node.updateHeight();   // <- ESTO ES LO QUE TE FALTABA
            result = node;
        }

        return result;
    }

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
