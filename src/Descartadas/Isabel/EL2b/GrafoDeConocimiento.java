package Descartadas.Isabel.EL2b;

import Descartadas.Isabel.TADs.Lista;

public class GrafoDeConocimiento {
    Lista<Nodo> nodos;
    Lista<Arco> arcos;

    public GrafoDeConocimiento() {
        this.nodos = new Lista<>();
        this.arcos = new Lista<>();
    }

    //Metodo que permite encontrar la posicion de un nodo dentro de la lista
    public int getIndex(Nodo nodo) {

        for (int i = 0; i < this.nodos.getSize(); i++) {
            if (this.nodos.get(i).equals(nodo)) {
                return i;
            }
        }
        return -1;
    }

    //Metodo para añadir nuevos arcos
    public void addArco(Nodo origen, String predicate, Nodo destino) {

        Nodo origenNuevo = getNodo(origen.getType(), origen.getDato());
        Nodo destinoNuevo = getNodo(destino.getType(), destino.getDato());

        Arco arco = new Arco(origenNuevo, predicate, destinoNuevo);
        if(!arcos.contains(arco)) {
            this.arcos.add(arco);
            origenNuevo.getArcosOrigen().add(arco);
        }
    }

    //Metodo para añadir nuevos triples. LLamamos a addArco para guardar los datos dentro del grafo
    //en forma de nodo, tal que el nuevo sujeto pasa a ser un nodo origen para el nuevo arco, etc.
    public void addTriple(String subject, String predicate, String object) {
        Nodo subjectNuevo = parseNodo(subject);
        Nodo objectNuevo = parseNodo(object);

        addArco(subjectNuevo,predicate, objectNuevo);
    }

    //Metodo auxiliar para separar la informacion dentro de un nodo, obteniendo
    //el tipo y el dato por separado.
    private Nodo parseNodo(String nodo){
        int indexPts = nodo.indexOf(':');

        if(indexPts > 0 && indexPts < nodo.length()-1){
            String type = nodo.substring(0, indexPts);
            String dato = nodo.substring(indexPts + 1);
            return getNodo(type,dato);
        }
        return getNodo("", nodo);
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

    public Lista<Nodo> getNodos() {
        return nodos;
    }

    public Lista<Arco> getArcos() {
        return arcos;
    }

    //Metodo para encontrar los nodos adyacentes a un nodo
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

    public Lista<String> getTypes(){
        Lista<String> listaTypes = new Lista<>();
        for (int i = 0; i < this.nodos.getSize(); i++) {
            Nodo nodo = this.nodos.get(i);
            String nodoType = nodo.getType();

            if(!listaTypes.contains(nodoType)){
                listaTypes.add(nodoType);
            }
        }
        return listaTypes;
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

    //Metodo para consultar si el grafo es disjunto o no. Usamos un metodo auxiliar de
    //recorrido por profundidad y comprobamos (mediante nodoVisitado) si para todos los nodos
    //del grafo, todos son accesibles desde otro. Si no lo son, el grafo es disjunto.
    public boolean isGrafoDisjunto() {
        int n = nodos.getSize();
        if(n == 0){
            return false;
        }

        boolean[] nodoVisitado = new boolean[n];
        int nodosConexos = 0;

        for(int i = 0; i < n; i++){
            if(!nodoVisitado[i]){
                nodosConexos++;
                if(nodosConexos > 1) {
                    return true;
                }
                DFSNodos(i, nodoVisitado);
            }
        }
        return false;
    }

    //Creamos un metodo auxiliar DFS para marcar todos los nodos que se pueden
    //visitar. Si en el grafo hay nodos no visitables, es disjunto.

    private void DFSNodos(int index, boolean[] nodoVisitado) {

        if(index < 0 || index >= nodos.getSize()) {
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
