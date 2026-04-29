package EL2b;

import TADs.Cola;
import TADs.Lista;


public class Camino<T extends Comparable<T>> {
    /*Usamos la búsqueda por anchura para obtener el camino más rápido
    * o más corto entre dos nodos. La generación de caminos por anchura/amplitud
    * en grafos se comporta como una cola, el primer nodo que se visita es
    * el primero que "sale"*/

    public Lista<Nodo<T>> caminoBFS(GrafoDeConocimiento<T> grafo, Nodo<T> origen, Nodo<T> destino) {

        int n = grafo.getNodos().getSize();
        boolean[] nodoVisitado = new boolean[n];
        Cola<Lista<Nodo<T>>> cola = new Cola<>();

        //Lista del camino inicial, marcando el origen como visitado
        Lista<Nodo<T>> inicial = new Lista<>();
        inicial.add(origen);
        cola.add(inicial);

        int indexOrigen = grafo.getIndex(origen);
        if (indexOrigen != -1) {
            nodoVisitado[indexOrigen] = true;
        }

        while (!cola.isEmpty()) {
            Lista<Nodo<T>> camino = cola.dequeue();
            Nodo<T> actual = camino.get(camino.getSize() - 1);

            if(actual.equals(destino)) {
                return camino;
            }

            Lista<Nodo<T>> vecinos = grafo.getVecinos(actual);
            for(int i = 0; i < vecinos.getSize(); i++) {
                Nodo<T> vecino = vecinos.get(i);
                int indexVec = grafo.getIndex(vecino);

                if(!nodoVisitado[indexVec]) {
                    nodoVisitado[indexVec] = true;
                    Lista<Nodo<T>> aux = new Lista<>();
                    aux.addAll(camino);
                    aux.add(vecino);
                    cola.add(aux);
                }
            }
        }
        return null;
    }

    /*Usamos la búsqueda por profundidad para poder obtener cualquiera de los caminos
    * posibles entre dos nodos. La generación de caminos por profundidad se comporta
    * como una pila, aunque también se puede realizar por recursividad. Para hacerlo recursivo,
    * necesitamos crear un metodo auxiliar.*/

    public Lista<Nodo<T>> caminoDFS(GrafoDeConocimiento<T> grafo, Nodo<T> origen, Nodo<T> destino) {

        int n = grafo.getNodos().getSize();
        boolean[] nodoVisitado = new boolean[n];
        Lista<Nodo<T>> camino = new Lista<>();

        if(checkCaminoDFS(grafo, origen, destino, nodoVisitado, camino)) {
            return camino;
        }
        return null;
    }

    public boolean checkCaminoDFS(GrafoDeConocimiento<T> grafo, Nodo<T> actual, Nodo<T> destino, boolean[] visitado, Lista<Nodo<T>> camino) {

        int index = grafo.getIndex(actual);

        if(index == -1 || visitado[index]) {
            return false;
        }
        visitado[index] = true;
        camino.add(actual);

        if(actual.equals(destino)){
            return true;
        }

        Lista<Nodo<T>> vecinos = grafo.getVecinos(actual);
        for(int i = 0; i < vecinos.getSize(); i++) {
            Nodo<T> vecino = vecinos.get(i);
            if(checkCaminoDFS(grafo, vecino, destino, visitado, camino)) {
                return true;
            }
        }
        camino.deleteLast();
        return false;
    }


}
