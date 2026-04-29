package EL2b;

public class Arco<T extends Comparable<T>> implements Comparable<Arco<T>> {
    protected String predicate;
    protected Nodo<T> subject;
    protected Nodo<T> object;

    public Arco(String predicate, Nodo<T> subject, Nodo<T> object) {
        this.predicate = predicate;
        this.subject = subject;
        this.object = object;
    }

    public String getPredicate() {
        return predicate;
    }

    public Nodo<T> getSubject() {
        return subject;
    }

    public Nodo<T> getObject() {
        return object;
    }

    @Override
    public String toString(){
        return subject.getType() + ":" + subject.getDato()
                + "---- " + predicate + " ---->" + object.getType() + ":" + object.getDato();
    }

    @Override
    public int compareTo(Arco<T> arco) {
        if(arco == null){
            throw new NullPointerException();
        }

        int aux1 = subject.compareTo(arco.subject);
        if (aux1 != 0) return aux1;

        int aux2 = object.compareTo(arco.object);
        if (aux2 != 0) return aux2;

        return predicate.compareTo(arco.predicate);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null  || getClass() != o.getClass()) return false;
        Arco<T> arco = (Arco<T>) o;
        return subject.equals(arco.subject) && object.equals(arco.object) && predicate.equals(arco.predicate);
    }
}
