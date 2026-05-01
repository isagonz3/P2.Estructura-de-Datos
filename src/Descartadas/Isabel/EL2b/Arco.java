package Descartadas.Isabel.EL2b;

public class Arco implements Comparable<Arco> {
    protected String predicate;
    protected Nodo origen;
    protected Nodo destino;

    public Arco(String predicate, Nodo origen, Nodo destino) {
        this.origen = origen;
        this.destino = destino;
        this.predicate = predicate;
    }

    public String getPredicate() {
        return predicate;
    }

    public Nodo getOrigen() {
        return origen;
    }

    public Nodo getDestino() {
        return destino;
    }


    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Arco)){
            return false;
        }
        Arco arco = (Arco) o;
        return this.predicate.equals(arco.predicate) && this.origen.equals(arco.origen)  && this.destino.equals(arco.destino);

    }

    @Override
    public int compareTo(Arco o) {
        return  this.predicate.compareTo(o.predicate);
    }

    @Override
    public String toString() {
        return origen + "---[ " + predicate + " ]---> " + destino;
    }
}
