package Descartadas.Isabel.EL2b;

import Descartadas.Isabel.TADs.Lista;

public class Nodo implements Comparable<Nodo> {
    protected String dato;
    protected String type;
    Lista<Arco> arcosOrigen;
    Lista<Arco> arcosDestino;

    public Nodo(String type, String dato) {
        this.type = type;
        this.dato = dato;
        this.arcosOrigen = new Lista<>();
        this.arcosDestino = new Lista<>();
    }

    public String getDato() {
        return dato;
    }

    public String getType() {
        return type;
    }

    public Lista<Arco> getArcosOrigen() {
        return arcosOrigen;
    }

    public Lista<Arco> getArcosDestino() {
        return arcosDestino;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nodo nodo = (Nodo) o;
        return this.type.equals(nodo.type) && this.dato.equals(nodo.dato);
    }

    @Override
    public int compareTo(Nodo o) {
        int cmp = this.type.compareTo(o.type);
        if (cmp == 0) {
            return this.dato.compareTo(o.dato);
        }
        return cmp;
    }

    @Override
    public String toString() {
        return type + " : " + dato;
    }
}
