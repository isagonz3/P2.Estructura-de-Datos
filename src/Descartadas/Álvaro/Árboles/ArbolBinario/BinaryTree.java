package Árboles.ArbolBinario;

import EstructurasP1.IndexedList;
import Árboles.Tree;

public interface BinaryTree<T> extends Tree<T> {
    BinaryTree<T> getLeftSubtree();
    BinaryTree<T> getRightSubtree();
    int getDegree();
    IndexedList<T> getPreOrder();
    IndexedList<T> getInOrder();
    IndexedList<T> getPostOrder();
    IndexedList<T> getLevelData(int level);
}
