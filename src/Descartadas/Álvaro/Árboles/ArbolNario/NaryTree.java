package Árboles.ArbolNario;

import EstructurasP1.IndexedList;
import Nodos.Arboles.Narios.NaryNode;
import Árboles.Tree;

public class NaryTree<T extends Comparable<T>> implements Tree<T> {
    protected NaryNode<T> root;

    public NaryTree() {
        root = null;
    }

    public NaryNode<T> getRoot() {
        return root;
    }

    @Override
    public void add(T data) {
        NaryNode<T> newNode = new NaryNode<>(data);
        if (root == null) {
            root = newNode;
        } else {
            root.addChild(newNode);
        }
    }

    public void add(T data, T parentData) {
        NaryNode<T> newNode = new NaryNode<>(data);
        if (root == null) {
            if (parentData == null) {
                root = newNode;
            }
        } else {
            NaryNode<T> parent = getNode(root, parentData);
            if (parent != null) {
                parent.addChild(newNode);
            }
        }
    }

    private NaryNode<T> getNode(NaryNode<T> current, T data) {
        NaryNode<T> result = null;
        if (current != null) {
            if (current.getData().compareTo(data) == 0) {
                result = current;
            } else {
                int i = 0;
                while (i < current.getChildren().len() && result == null) {
                    result = getNode(current.getChildren().get(i), data);
                    i++;
                }
            }
        }

        return result;
    }

    @Override
    public boolean isEmpty() {
        boolean result = false;
        if (root == null) {
            result = true;
        }
        return result;
    }

    @Override
    public IndexedList<T> getPath(T data) {
        IndexedList<T> result = new IndexedList<>();
        if (root != null) {
            if (getPath(root, data, result) == false) {
                result.clear();
            }
        }
        return result;
    }

    private boolean getPath(NaryNode<T> current, T data, IndexedList<T> path) {
        boolean found = false;
        if (current != null) {
            path.append(current.getData());
            if (current.getData().compareTo(data) == 0) {
                found = true;
            } else {
                int i = 0;
                while (i < current.getChildren().len() && found == false) {
                    found = getPath(current.getChildren().get(i), data, path);
                    i++;
                }
            }
            if (found == false) {
                path.delete(path.len() - 1);
            }
        }
        return found;
    }

    public NaryNode<T> search(T data) {
        NaryNode<T> result = null;
        if (root != null) {
            result = search(root, data);
        }
        return result;
    }

    private NaryNode<T> search(NaryNode<T> node, T data) {
        NaryNode<T> result = null;
        if (node.getData().compareTo(data) == 0) {
            result = node;
        } else {
            int i = 0;
            while (i < node.getChildren().len() && result == null) {
                result = search(node.getChildren().get(i), data);
                i++;
            }
        }
        return result;
    }

    public IndexedList<T> searchWithPath(T data) {
        IndexedList<T> path = new IndexedList<>();
        boolean found=false;
        if (root != null) {
            found=searchWithPath(root, data, path);
            if(found==false) {
                path.clear();
            }
        }
        return path;
    }

    private boolean searchWithPath(NaryNode<T> node,T data, IndexedList<T> path) {
        boolean found=false;
        if (node!=null) {
            path.append(node.getData());
            if (node.getData().compareTo(data)==0) {
                found=true;
            }
            else{
                int i=0;
                while (i<node.getChildren().len() && found==false) {
                    boolean childFound=searchWithPath(node.getChildren().get(i),data,path);
                    if (childFound==true){
                        found=true;
                    }
                    i=i+1;
                }
            }
            if (found==false){
                path.delete(path.len()-1);
            }
        }
        return found;
    }

    //Elimina el subarbol entero
    @Override
    public void remove(T data) {
        if (root != null) {
            if (root.getData().compareTo(data) == 0) {
                root = null;
            } else {
                remove(root, data);
            }
        }
    }

    private void remove(NaryNode<T> node, T data) {
        if (node != null) {
            int i = node.getChildren().len() - 1;
            while (i >= 0) {
                NaryNode<T> child = node.getChildren().get(i);
                if (child.getData().compareTo(data) == 0) {
                    node.getChildren().delete(i);
                } else {
                    remove(child, data);
                }
                i--;
            }
        }
    }


    @Override
    public int getHeight() {
        int result = 0;
        if (root != null) {
            result = calculateHeight(root);
        }
        return result;
    }

    private int calculateHeight(NaryNode<T> node) {
        int result = 0;
        if (node != null) {
            int maxChildHeight = 0;
            for (int i = 0; i < node.getChildren().len(); i++) {
                NaryNode<T> child = node.getChildren().get(i);
                int childHeight = calculateHeight(child);
                if (childHeight > maxChildHeight) {
                    maxChildHeight = childHeight;
                }
            }
            result = 1 + maxChildHeight;
        }
        return result;
    }

    public IndexedList<T> getPreOrder() {
        IndexedList<T> result = new IndexedList<>();
        if (root != null) {
            preOrder(root, result);
        }
        return result;
    }

    private void preOrder(NaryNode<T> node, IndexedList<T> list) {
        if (node != null) {
            list.append(node.getData());
            for (int i = 0; i < node.getChildren().len(); i++) {
                preOrder(node.getChildren().get(i), list);
            }
        }
    }

    public IndexedList<T> getPostOrder() {
        IndexedList<T> result = new IndexedList<>();
        if (root != null) {
            postOrder(root, result);
        }
        return result;
    }

    private void postOrder(NaryNode<T> node, IndexedList<T> list) {
        if (node != null) {
            for (int i = 0; i < node.getChildren().len(); i++) {
                postOrder(node.getChildren().get(i), list);
            }
            list.append(node.getData());
        }
    }

    public String print(){
        String result="";
        if(root!=null){
            result=printNode(root,"",true);
        }
        return result;
    }

    private String printNode(NaryNode<T> node, String prefix, boolean isLast) {
        String result="";
        result=result+prefix;
        if(isLast==true) {
            result=result+"└── ";
        }
        else {
            result=result+"├── ";
        }
        result=result+node.getData().toString()+"\n";
        String newPrefix=prefix;
        if(isLast==true) {
            newPrefix=newPrefix+"    ";
        }
        else {
            newPrefix=newPrefix+"│   ";
        }
        int i=0;
        int size=node.getChildren().len();
        while (i<size) {
            NaryNode<T> child=node.getChildren().get(i);
            if (child!=null) {
                boolean last=false;
                if(i==size-1) {
                    last = true;
                }
                result=result+printNode(child,newPrefix,last);
            }
            i++;
        }
        return result;
    }
}

