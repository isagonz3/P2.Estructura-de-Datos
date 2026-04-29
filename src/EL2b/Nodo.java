package EL2b;

import TADs.Lista;

public class Nodo<T extends Comparable<T>> implements Comparable<Nodo<T>> {
    protected String type;
    protected T dato;
    protected Lista<Arco<T>> arcos;

    public Nodo(String type, T dato) {
        this.type = type;
        this.dato = dato;
        this.arcos = new Lista<>();
    }

    public String getType() {
        return type;
    }

    public T getDato() {
        return dato;
    }

    public Lista<Arco<T>> getArcos() {
        return arcos;
    }

    @Override
    public int compareTo(Nodo<T> nodo) {
        if(nodo == null){
            throw new NullPointerException();
        }
        int cmp = type.compareTo(nodo.type);
        if (cmp != 0) return cmp;
        return dato.compareTo(nodo.dato);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null  || getClass() != o.getClass()) return false;
        Nodo<T> nodo = (Nodo<T>) o;
        return type.equals(nodo.type) && dato.equals(nodo.dato);
    }

}
