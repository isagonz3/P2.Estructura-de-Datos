package EL2b;

import TADs.Lista;

public class GrafoDeConocimiento<T extends Comparable<T>> {
    Lista<Nodo<T>> nodos;
    Lista<Arco<T>> arcos;

    public GrafoDeConocimiento() {
        this.nodos = new Lista<>();
        this.arcos = new Lista<>();
    }

    public void addNodo(Nodo<T> nodo) {
        if(!nodos.contains(nodo)) this.nodos.add(nodo);

    }

    public int getIndex(Nodo<T> nodo) {

        for (int i = 0; i < this.nodos.getSize(); i++) {
            if (this.nodos.get(i).equals(nodo)) {
                return i;
            }
        }
        return -1;
    }

    public void addArco(String predicate, Nodo<T> subject, Nodo<T> object) {

        Nodo<T> subjectNuevo = getNodo(subject.getType(), subject.getDato());
        Nodo<T> objectNuevo = getNodo(object.getType(), object.getDato());

        if(!nodos.contains(subjectNuevo)) {
            nodos.add(subjectNuevo);
        }
        if(!nodos.contains(objectNuevo)) {
            nodos.add(objectNuevo);
        }

        Arco<T> arco = new Arco<>(predicate, subjectNuevo, objectNuevo);
        if(!arcos.contains(arco)) {
            this.arcos.add(arco);
            subjectNuevo.getArcos().add(arco);
        }

    }

    public Nodo<T> getNodo(String type, T dato) {
        for (int i = 0; i < this.nodos.getSize(); i++) {
            Nodo<T> aux = this.nodos.get(i);
            if(aux.getDato().equals(dato) && aux.getType().equals(type)) {
                return aux; //Si el nodo ya existe en el grafo, lo devolvemos como está
            }
        }
        //Si el nodo no existe en el grafo, creamos un nuevo nodo
        Nodo<T> nuevoNodo = new Nodo<>(type, dato);
        this.nodos.add(nuevoNodo);
        return nuevoNodo;
    }

    public Arco<T> getArco(String predicate, Nodo<T> subject, Nodo<T> object) {
        for (int i = 0; i < this.arcos.getSize(); i++) {
            Arco<T> aux = this.arcos.get(i);
            if(aux.getSubject().equals(subject) && aux.getObject().equals(object) && aux.getPredicate().equals(predicate)){
                    return aux;
            }
        }
        return null;
    }

    public Lista<Nodo<T>> getNodos() {
        return nodos;
    }

    public Lista<Arco<T>> getArcos() {
        return arcos;
    }

    public Lista<Nodo<T>> getVecinos(Nodo<T> nodo) {
        Lista<Nodo<T>> vecinos = new Lista<>();

        for (int i = 0; i < arcos.getSize(); i++) {
            Arco<T> arco = arcos.get(i);

            if(arco.getSubject().equals(nodo)){
                vecinos.add(arco.getObject());
            }
        }
        return vecinos;
    }

    public Lista<Nodo<T>> getObjects(String predicate, Nodo<T> subject) {
        Lista<Nodo<T>> objects = new Lista<>();

        for(int i = 0; i < this.arcos.getSize(); i++){
            Arco<T> arco = arcos.get(i);

            if(arco.getSubject().equals(subject) && arco.getPredicate().equals(predicate)){
                objects.add(arco.getObject());
            }
        }
        return objects;
    }

    public boolean isGrafoDisjunto(GrafoDeConocimiento<T> grafo) {
        int n = grafo.getNodos().getSize();

        if(n == 0){
            return false;
        }

        boolean[] nodoVisitado = new boolean[n];
        Nodo<T> origen = grafo.getNodos().get(0);
        DFSNodos(grafo,origen,nodoVisitado);

        for(int i = 0; i < n; i++){
            if(!nodoVisitado[i]){
                return true;
            }
        }
        return false;
    }

    //Creamos un metodo auxiliar DFS para marcar todos los nodos que se pueden
    //visitar. Si en el grafo hay nodos no visitables, es disjunto.

    private void DFSNodos(GrafoDeConocimiento<T> grafo, Nodo<T> actual, boolean[] nodoVisitado) {

        int index = grafo.getIndex(actual);
        if(index == -1 || nodoVisitado[index]) { //Si no hay nodo o ya se ha visitado antes
            return;                              //no podemos visitar el nodo actual
        }

        nodoVisitado[index] = true;
        Lista<Nodo<T>> vecinos = grafo.getVecinos(actual);
        for(int i = 0; i < vecinos.getSize(); i++){
            DFSNodos(grafo, vecinos.get(i), nodoVisitado);
        }
    }

}
