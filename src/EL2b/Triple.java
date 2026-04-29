package EL2b;

public class Triple implements Comparable<Triple> {

    protected String subject;
    protected String predicate;
    protected String object;

    public Triple(){
        this.subject = null;
        this.predicate = null;
        this.object = null;
    }

    public Triple(String subject, String predicate, String object){
        this.subject = subject;
        this.predicate = predicate;
        this.object = object;
    }

    public String getSubject() {
        return subject;
    }

    public String getPredicate() {
        return predicate;
    }

    public String getObject() {
        return object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triple triple = (Triple) o;
        return this.subject.equals(triple.subject) && this.predicate.equals(triple.predicate) && this.object.equals(triple.object);
    }


    @Override
    public int compareTo(Triple o) {
        int cmpSubject = this.subject.compareTo(o.subject);
        if (cmpSubject != 0) return cmpSubject;
        int cmpPredicate = this.predicate.compareTo(o.predicate);
        if (cmpPredicate != 0) return cmpPredicate;
        return this.object.compareTo(o.object);
    }

    @Override
    public String toString() {
        return "Triple{" + "subject=" + subject + ", predicate=" + predicate + ", object=" + object + '}';
    }
}
