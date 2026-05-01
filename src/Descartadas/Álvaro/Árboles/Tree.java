package Árboles;


import EstructurasP1.IndexedList;

public interface Tree<T> {
    void add(T data);
    void remove(T data);
    boolean isEmpty();
    int getHeight();
    IndexedList<T> getPath(T data);
}
