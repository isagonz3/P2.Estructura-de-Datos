package EL2b;

import TADs.Lista;

public class GrafoDeConocimiento {
    Lista<Nodo> nodos;
    Lista<Arco> arcos;

    public GrafoDeConocimiento() {
        this.nodos = new Lista<>();
        this.arcos = new Lista<>();
    }

    public void addNodo(Nodo nodo) {
        if(!nodos.contains(nodo)) this.nodos.add(nodo);
    }

    public int getIndex(Nodo nodo) {

        for (int i = 0; i < this.nodos.getSize(); i++) {
            if (this.nodos.get(i).equals(nodo)) {
                return i;
            }
        }
        return -1;
    }

    public void addArco(String predicate, Nodo subject, Nodo object) {

        Nodo subjectNuevo = getNodo(subject.getType(), subject.getDato());
        Nodo objectNuevo = getNodo(object.getType(), object.getDato());

        if(!nodos.contains(subjectNuevo)) {
            nodos.add(subjectNuevo);
        }
        if(!nodos.contains(objectNuevo)) {
            nodos.add(objectNuevo);
        }

        Arco arco = new Arco(predicate, subjectNuevo, objectNuevo);
        if(!arcos.contains(arco)) {
            this.arcos.add(arco);
            subjectNuevo.getArcosOrigen().add(arco);
        }
    }

    public Nodo getNodo(String type, String dato) {
        for (int i = 0; i < this.nodos.getSize(); i++) {
            Nodo aux = this.nodos.get(i);
            if(aux.getDato().equals(dato) && aux.getType().equals(type)) {
                return aux; //Si el nodo ya existe en el grafo, lo devolvemos como está
            }
        }
        //Si el nodo no existe en el grafo, creamos un nuevo nodo
        Nodo nuevoNodo = new Nodo(type, dato);
        this.nodos.add(nuevoNodo);
        return nuevoNodo;
    }

    public Arco getArco(String predicate, Nodo subject, Nodo object) {
        for (int i = 0; i < this.arcos.getSize(); i++) {
            Arco aux = this.arcos.get(i);
            if(aux.getOrigen().equals(subject) && aux.getDestino().equals(object) && aux.getPredicate().equals(predicate)){
                    return aux;
            }
        }
        return null;
    }

    public Lista<Nodo> getNodos() {
        return nodos;
    }

    public Lista<Arco> getArcos() {
        return arcos;
    }

    public Lista<Nodo> getVecinos(Nodo nodo) {
        Lista<Nodo> vecinos = new Lista<>();

        for (int i = 0; i < arcos.getSize(); i++) {
            Arco arco = arcos.get(i);

            if(arco.getOrigen().equals(nodo)){
                vecinos.add(arco.getDestino());
            }
        }
        return vecinos;
    }

    public Lista<Nodo> getObjects(String predicate, Nodo subject) {
        Lista<Nodo> objects = new Lista<>();

        for(int i = 0; i < this.arcos.getSize(); i++){
            Arco arco = arcos.get(i);

            if(arco.getOrigen().equals(subject) && arco.getPredicate().equals(predicate)){
                objects.add(arco.getDestino());
            }
        }
        return objects;
    }

    public boolean isGrafoDisjunto() {
        int n = nodos.getSize();
        if(n == 0){
            return false;
        }

        boolean[] nodoVisitado = new boolean[n];

        DFSNodos(0,nodoVisitado);

        for(int i = 0; i < n; i++){
            if(!nodoVisitado[i]){
                return true;
            }
        }
        return false;
    }

    //Creamos un metodo auxiliar DFS para marcar todos los nodos que se pueden
    //visitar. Si en el grafo hay nodos no visitables, es disjunto.

    private void DFSNodos(int index, boolean[] nodoVisitado) {

        if(index < 0 || index > nodos.getSize()) {
            return;
        }

        nodoVisitado[index] = true;

        Lista<Nodo> vecinos = getVecinos(nodos.get(index));
        for(int i = 0; i < vecinos.getSize(); i++){
            int indexVecino = getIndex(vecinos.get(i));
            if(indexVecino != -1){
                DFSNodos(indexVecino, nodoVisitado);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nodos: ").append(nodos).append("\n");
        sb.append("Arcos: ").append(arcos).append("\n");
        return sb.toString();
    }

}
