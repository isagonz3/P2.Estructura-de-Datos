package Descartadas.Isabel.EL2b;

import Descartadas.Isabel.TADs.Lista;

public class Nodo implements Comparable<Nodo> {
    protected String dato;
    protected String type;
    Lista<Arco> arcosOrigen;

    public Nodo(String type, String dato) {
        this.type = type;
        this.dato = dato;
        this.arcosOrigen = new Lista<>();
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nodo nodo = (Nodo) o;
        return this.type.equals(nodo.type) && this.dato.equals(nodo.dato);
    }

    @Override
    public int compareTo(Nodo o) {
        return 0;
    }

    @Override
    public String toString() {
        return type + " : " + dato;
    }
}
