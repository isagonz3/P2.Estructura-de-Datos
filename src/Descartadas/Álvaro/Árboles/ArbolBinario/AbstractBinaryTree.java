package Árboles.ArbolBinario;

import EstructurasP1.IndexedList;
import EstructurasP1.Queue;
import Nodos.Arboles.Binarios.AbstractBinaryNode;
import Árboles.Tree;

public abstract class AbstractBinaryTree<T extends Comparable<T>,N extends AbstractBinaryNode<T,N>> implements Tree<T>, BinaryTree<T> {
    protected N root;

    public N getRoot(){
        return root;
    }

    //Ambos métodos solo copian referencias,no devuelve un subárbol nuevo
    @Override
    public AbstractBinaryTree<T,N> getLeftSubtree() {
        AbstractBinaryTree<T,N> tree=emptyTree();
        if (root!=null) {
            tree.root=root.getLeft();
        }
        return tree;
    }

    @Override
    public AbstractBinaryTree<T,N> getRightSubtree() {
        AbstractBinaryTree<T,N>tree=emptyTree();
        if (root!=null){
            tree.root=root.getRight();
        }
        return tree;
    }

    public void remove(T data){
        if(root!=null){
            root=remove(root,data);
        }
    }

    protected N remove(N node,T data){
        N result=node;
        if(node!=null){
            int cmp=data.compareTo(node.getData());
            if(cmp<0){
                N leftResult=remove(node.getLeft(),data);
                node.setLeft(leftResult);
            }
            else if(cmp>0){
                N rightResult=remove(node.getRight(),data);
                node.setRight(rightResult);
            }
            else{
                if(node.getLeft()==null){
                    result=node.getRight();
                }
                else if(node.getRight()==null){
                    result=node.getLeft();
                }
                else{
                    N successor=node.getRight();
                    while(successor.getLeft()!=null){
                        successor=successor.getLeft();
                    }
                    N newRight=remove(node.getRight(),successor.getData());
                    successor.setLeft(node.getLeft());
                    successor.setRight(newRight);
                    result=successor;
                }
            }
        }
        return result;
    }

    @Override
    public int getHeight() {
        int result=0;
        if(root!=null){
            result=root.getHeight();
        }
        return result;
    }

    @Override
    public IndexedList<T> getPreOrder() {
        IndexedList<T>list=new IndexedList<>();
        if (getRoot()!=null){
            getRoot().getPreOrder(list);
        }
        return list;
    }

    @Override
    public IndexedList<T> getInOrder() {
        IndexedList<T> list=new IndexedList<>();
        if (getRoot()!=null){
            getRoot().getInOrder(list);
        }
        return list;
    }

    public IndexedList<T> getPostOrder(){
        IndexedList<T> list=new IndexedList<>();
        if(getRoot()!=null){
            getRoot().getPostOrder(list);
        }
        return list;
    }


    public IndexedList<T> getLevelData(int level) {
        IndexedList<T>result=new IndexedList<>();
        if (getRoot()!=null && level>=0) {
            Queue<N> queue=new Queue<>();
            queue.enqueue(getRoot());
            int currentLevel=0;
            while (queue.isEmpty()==false && currentLevel<=level){
                int size=queue.size();
                int i=0;
                while(i<size){
                    N current=queue.dequeue();
                    if (currentLevel==level){
                        result.append(current.getData());
                    }
                    if(current.getLeft()!=null){
                        queue.enqueue(current.getLeft());
                    }
                    if(current.getRight()!=null){
                        queue.enqueue(current.getRight());
                    }
                    i++;
                }
                currentLevel++;
            }
        }
        return result;
    }

    public int getDegree() {
        return getDegree(root);
    }

    private int getDegree(N node){
        int result=0;
        if(node!=null){
            int leftDegree=getDegree(node.getLeft());
            int rightDegree=getDegree(node.getRight());
            int currentDegree=0;
            if(node.getLeft()!=null){
                currentDegree++;
            }
            if(node.getRight()!=null){
                currentDegree++;
            }
            result=Math.max(currentDegree,Math.max(leftDegree,rightDegree));
        }
        return result;
    }

    public IndexedList<T> getPath(T data){
        IndexedList<T> result=new IndexedList<>();
        N current=root;
        boolean found=false;
        while(current!=null && found==false){
            result.append(current.getData());
            int cmp=data.compareTo(current.getData());
            if(cmp==0){
                found=true;
            }
            else if(cmp<0){
                current=current.getLeft();
            }
            else{
                current=current.getRight();
            }
        }
        if(found==false){
            result.clear();
        }
        return result;
    }

    public IndexedList<IndexedList<T>> getPathDuplicates(T data){
        IndexedList<IndexedList<T>> result=new IndexedList<>();
        N current=root;
        boolean found=false;
        while(current!=null && found==false){
            result.append(current.getDataList().duplicate());
            int cmp=data.compareTo(current.getData());
            if(cmp==0){
                found=true;
            }
            else if(cmp<0){
                current=current.getLeft();
            }
            else{
                current=current.getRight();
            }
        }
        if(found==false){
            result.clear();
        }
        return result;
    }

    public N search(T data){
        return search(root,data);
    }

    private N search(N node,T data) {
        N result=null;
        if (node!=null) {
            int cmp=data.compareTo(node.getData());
            if (cmp==0){
                result=node;
            }
            else if (cmp<0) {
                result=search(node.getLeft(),data);
            }
            else {
                result=search(node.getRight(),data);
            }
        }
        return result;
    }

    @Override
    public boolean isEmpty(){
        boolean result=false;
        if(getRoot()==null){
            result=true;
        }
        return result;
    }

    public boolean isHomogeneousTree(){
        int degree=getDegree();
        return isHomogeneousTree(root,degree);
    }

    private boolean isHomogeneousTree(N node,int degree){
        boolean result=true;
        if(node!=null){
            if(node.getDegree()!=degree){
                result=false;
            }
            boolean leftResult=isHomogeneousTree(node.getLeft(),degree);
            boolean rightResult=isHomogeneousTree(node.getRight(),degree);
            if(leftResult==false || rightResult==false){
                result=false;
            }
        }
        return result;
    }

    public boolean isCompleteTree() {
        return isCompleteTree(root);
    }

    private boolean isCompleteTree(N node) {
        boolean result=true;
        if(node!=null){
            int leftHeight=0;
            int rightHeight=0;
            if(node.getLeft()!=null) {
                leftHeight=node.getLeft().getHeight();
            }
            if(node.getRight()!=null) {
                rightHeight=node.getRight().getHeight();
            }
            if (leftHeight!=rightHeight) {
                result=false;
            }
            boolean leftResult=isCompleteTree(node.getLeft());
            boolean rightResult=isCompleteTree(node.getRight());
            if (leftResult==false || rightResult==false) {
                result=false;
            }
        }
        return result;
    }

    public boolean isAlmostCompleteTree() {
        return isAlmostCompleteTree(root);
    }

    private boolean isAlmostCompleteTree(N node) {
        boolean result=true;
        if(node!=null) {
            Queue<N>queue=new Queue<>();
            queue.enqueue(node);
            boolean gap=false;
            while (queue.isEmpty()==false) {
                N current=queue.dequeue();
                if (current.getLeft()!=null) {
                    if(gap==true){
                        result=false;
                    }
                    queue.enqueue(current.getLeft());
                }
                else {
                    gap=true;
                }
                if(current.getRight()!=null){
                    if(gap==true){
                        result=false;
                    }
                    queue.enqueue(current.getRight());
                }
                else{
                    gap=true;
                }
            }
        }
        return result;
    }


    public abstract void add(T data);

    //"Constructor" de un árbol vacío
    protected abstract AbstractBinaryTree<T,N> emptyTree();
}