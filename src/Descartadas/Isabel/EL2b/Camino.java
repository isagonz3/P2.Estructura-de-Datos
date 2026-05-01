package Descartadas.Isabel.EL2b;

import Descartadas.Isabel.TADs.Cola;
import Descartadas.Isabel.TADs.Lista;

public class Camino {
    /*Usamos la búsqueda por anchura para obtener el camino más rápido
    * o más corto entre dos nodos. La generación de caminos por anchura/amplitud
    * en grafos se comporta como una cola, el primer nodo que se visita es
    * el primero que "sale"*/

    public Lista<Nodo> caminoBFS(GrafoDeConocimiento grafo, Nodo origen, Nodo destino) {

        int n = grafo.getNodos().getSize();
        boolean[] nodoVisitado = new boolean[n];
        Cola<Lista<Nodo>> cola = new Cola<>();

        //Lista del camino inicial, marcando el origen como visitado
        Lista<Nodo> inicial = new Lista<>();
        inicial.add(origen);
        cola.add(inicial);

        int indexOrigen = grafo.getIndex(origen);
        if (indexOrigen != -1) {
            nodoVisitado[indexOrigen] = true;
        }

        while (!cola.isEmpty()) {
            Lista<Nodo> camino = cola.dequeue();
            Nodo actual = camino.get(camino.getSize() - 1);

            if(actual.equals(destino)) {
                return camino;
            }

            Lista<Nodo> vecinos = grafo.getVecinos(actual);
            for(int i = 0; i < vecinos.getSize(); i++) {
                Nodo vecino = vecinos.get(i);
                int indexVecino = grafo.getIndex(vecino);

                if(indexVecino != -1 && !nodoVisitado[indexVecino]) {
                    nodoVisitado[indexVecino] = true;
                    Lista<Nodo> nuevoCamino = new Lista<>();
                    nuevoCamino.addAll(camino);
                    nuevoCamino.add(vecino);
                    cola.add(nuevoCamino);
                }
            }
        }
        return null;
    }

    /*Usamos la búsqueda por profundidad para poder obtener cualquiera de los caminos
    * posibles entre dos nodos. La generación de caminos por profundidad se comporta
    * como una pila, aunque también se puede realizar por recursividad. Para hacerlo recursivo,
    * necesitamos crear un metodo auxiliar.*/

    public Lista<Nodo> caminoDFS(GrafoDeConocimiento grafo, Nodo origen, Nodo destino) {

        int n = grafo.getNodos().getSize();
        boolean[] nodoVisitado = new boolean[n];
        Lista<Nodo> camino = new Lista<>();

        if(checkCaminoDFS(grafo, origen, destino, nodoVisitado, camino)) {
            return camino;
        }
        return null;
    }

    private boolean checkCaminoDFS(GrafoDeConocimiento grafo, Nodo actual, Nodo destino, boolean[] visitado, Lista<Nodo> camino) {

        int index = grafo.getIndex(actual);

        if(index == -1 || visitado[index]) {
            return false;
        }
        visitado[index] = true;
        camino.add(actual);

        if(actual.equals(destino)){
            return true;
        }

        Lista<Nodo> vecinos = grafo.getVecinos(actual);
        for(int i = 0; i < vecinos.getSize(); i++) {
            Nodo vecino = vecinos.get(i);
            if(checkCaminoDFS(grafo, vecino, destino, visitado, camino)) {
                return true;
            }
        }
        camino.deleteLast();
        return false;
    }


}
