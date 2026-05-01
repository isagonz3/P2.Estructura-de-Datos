package Árboles.ArbolBinario;

import Nodos.Arboles.Binarios.AbstractBinaryNode;

public abstract class AbstractBalancedBinaryTree<T extends Comparable<T>,N extends AbstractBinaryNode<T,N>> extends AbstractBinaryTree<T,N> {

    protected N balance(N node) {
        N result=node;
        int balanceFactor= getBalance(node);
        // Caso LL
        if (balanceFactor>1 && getBalance(node.getLeft())>=0) {
            result=rotateRight(node);
        }
        // Caso LR
        else if(balanceFactor>1 && getBalance(node.getLeft())<0) {
            node.setLeft(rotateLeft(node.getLeft()));
            result=rotateRight(node);
        }
        // Caso RR
        else if(balanceFactor<-1 && getBalance(node.getRight())<=0) {
            result=rotateLeft(node);
        }
        // Caso RL
        else if(balanceFactor<-1 && getBalance(node.getRight())>0) {
            node.setRight(rotateRight(node.getRight()));
            result=rotateLeft(node);
        }
        return result;
    }

    protected N rotateRight(N y) {
        N x=y.getLeft();
        N t2=x.getRight();
        x.setRight(y);
        y.setLeft(t2);
        y.updateHeight();
        x.updateHeight();
        return x;
    }

    protected N rotateLeft(N x) {
        N y=x.getRight();
        N t2=y.getLeft();
        y.setLeft(x);
        x.setRight(t2);
        x.updateHeight();
        y.updateHeight();
        return y;
    }

    protected int getBalance(N node) {
        int result = 0;
        if (node!=null){
            int left=0;
            int right=0;
            if (node.getLeft()!=null) {
                left=node.getLeft().getHeight();
            }
            if (node.getRight()!=null) {
                right=node.getRight().getHeight();
            }

            result=left-right;
        }
        return result;
    }

    @Override
    public void remove(T data){
        if(root!=null){
            root=remove(root,data);
        }
    }

    @Override
    public N remove(N node, T data){
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
            if(result!=null){
                result=balance(result);   // Balancea el nodo después de eliminar
                result.updateHeight();    // Actualiza la altura
            }
        }
        return result;
    }
}
