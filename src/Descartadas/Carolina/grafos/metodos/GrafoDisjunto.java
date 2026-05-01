package Descartadas.Carolina.grafos.metodos;

import Descartadas.Carolina.estructuras_necesarias.ListSE;
import Descartadas.Carolina.estructuras_necesarias.Queue;
import Descartadas.Carolina.grafos.componentes.Grafo;
import Descartadas.Carolina.grafos.componentes.Nodo;

public class GrafoDisjunto { //clase para contar componentes conexas del grafo

    //Atributos
    private Grafo grafo; //grafo sobre el que se trabaja


    //Constructor
    public GrafoDisjunto(Grafo g) {
        this.grafo = g;
    }


    //Métodos
    public int contarComponentes() { //devuelve cuántos componentes conexos hay

        ListSE<Nodo> visitados = new ListSE<>(); //lista de nodos ya visitados
        int componentes = 0; //contador de componentes

        for (int i = 0; i < grafo.nodos.getSize(); i++) { //recorre todos los nodos del grafo

            Nodo n = grafo.nodos.get(i); //nodo actual

            if (!contiene(visitados, n)) { //si no se ha visitado todavía se suma al contador de los componenetes del grafo
                componentes++;
                recorrerNodo(n, visitados); //recorre el componente
            }
        }

        return componentes; //devuelve el número total de componentes
    }

    private void recorrerNodo(Nodo inicio, ListSE<Nodo> visitados) { // recorre un componente con bfs

        Queue<Nodo> cola = new Queue<>(); //cola para recorrer en anchura
        cola.enqueue(inicio); //mete el nodo inicial y lo marca como visitado
        visitados.addLast(inicio);

        while (!cola.isEmpty()) { //mientras haya nodos por visitar

            Nodo actual = cola.dequeue(); //saca el siguiente nodo

            for (int i = 0; i < actual.aristas.getSize(); i++) { //recorre todas las conexiones

                Nodo sig = actual.aristas.get(i).destino; //nodo vecino

                if (!contiene(visitados, sig)) { //si no está visitado, lo marca como visitado y lo añade a la cola
                    visitados.addLast(sig);
                    cola.enqueue(sig);
                }
            }
        }
    }

    private boolean contiene(ListSE<Nodo> lista, Nodo n) { //comprueba si un nodo está en la lista

        for (int i = 0; i < lista.getSize(); i++) { //recorre la lista

            if (lista.get(i) == n) { //si es el mismo nodo lo encuentra
                return true;
            }
        }

        return false; // si no está
    }
}