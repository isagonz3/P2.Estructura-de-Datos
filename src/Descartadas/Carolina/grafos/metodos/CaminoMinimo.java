package Descartadas.Carolina.grafos.metodos;

import Descartadas.Carolina.estructuras_necesarias.ListSE;
import Descartadas.Carolina.estructuras_necesarias.Queue;
import Descartadas.Carolina.grafos.componentes.Grafo;
import Descartadas.Carolina.grafos.componentes.Nodo;

public class CaminoMinimo { //calcula un camino mínimo usando la búsqueda en anchura (bfs)

    //Atributos
    private Grafo grafo; //grafo con el que se trabaja
    private ListSE<Nodo> visitados; //lista de nodos visitados
    private ListSE<Nodo> padres; //lista para guardar los nodos padres


    //Constructor
    public CaminoMinimo(Grafo g) {
        this.grafo = g; //inicializa el grafo
        this.visitados = new ListSE<>(); //inicializa la lista vacía de los nodos visitados
        this.padres = new ListSE<>(); //inicializa la lista vacía de los nodos padres
    }


    //Métodos
    public ListSE<Nodo> obtenerCamino(String inicio, String fin) { //devuelve el camino que hay entre dos nodos

        Nodo nodoInicio = grafo.buscarNodo(inicio); //busca el nodo de inicio en el grafo
        Nodo nodoFin = grafo.buscarNodo(fin); //busca el nodo final en el grafo

        ListSE<Nodo> caminoVacio = new ListSE<>(); //lista vacía auxiliar por si no se puede trazar un camino entre los nodos

        if (nodoInicio == null || nodoFin == null) { // si alguno de los nodos no existe entonces no puede hacer camino
            return caminoVacio;
        }

        Queue<Nodo> cola = new Queue<>(); //cola auxiliar que permite utilizar la búsqueda en anchura

        visitados = new ListSE<>(); //incia la lista de nodos visitados vacía
        padres = new ListSE<>(); //inicia la lista de nodos padres vacía

        cola.enqueue(nodoInicio); //mete el nodo inicial en la cola
        visitados.addLast(nodoInicio); //marca como visitado el nodo inicial
        padres.addLast(null); //el nodo inicial no tiene padre

        boolean encontrado = false; //declara si hemos llegado al destino o aún no

        while (!cola.isEmpty() && !encontrado) { //mientras haya nodos y no se haya encontrado el nodo fin

            Nodo actual = cola.dequeue(); //saca el siguiente nodo

            if (actual.nombre.equals(nodoFin.nombre)) { //si el nombre del nodo final coincide con el nombre del nodo actual, hemos llegado al final del camino
                encontrado = true;
            }

            for (int i = 0; i < actual.aristas.getSize() && !encontrado; i++) { //recorre todas las conexiones del nodo actual

                Nodo siguiente = actual.aristas.get(i).destino; //obtiene el nodo vecino

                if (!esta_visitado(siguiente)) { //si el nodo vecino no se ha visitado, se mete en la cola y se marca como visitado
                    cola.enqueue(siguiente);
                    visitados.addLast(siguiente);
                    padres.addLast(actual); //se guarda el nodo padre del que viene

                    if (siguiente.nombre.equals(nodoFin.nombre)) { //si el nombre del nodo final coincide con el nombre del nodo vecino, hemos llegado al final del camino
                        encontrado = true;
                    }
                }
            }
        }

        return reconstruirCamino(nodoInicio, nodoFin); //reconstruye el camino final
    }


    private boolean esta_visitado(Nodo n) { //comprueba si un nodo está en visitados

        for (int i = 0; i < visitados.getSize(); i++) { //recorre la lista de nodos visitados

            if (visitados.get(i).nombre.equals(n.nombre)) { //si coincide el nombre del nodo, ya ha sido visitado
                return true;
            }
        }

        return false; //si no se encuentra entonces no está visitado
    }


    private ListSE<Nodo> reconstruirCamino(Nodo inicio, Nodo fin) { //reconstruye el camino usando la lista de nodos padres

        ListSE<Nodo> camino = new ListSE<>(); //lista final del camino

        int pos = -1; //inicializa la posición del nodo final en visitados

        for (int i = 0; i < visitados.getSize(); i++) { //recorre la lista de nodos visitados entera

            if (visitados.get(i).nombre.equals(fin.nombre)) { //cuando los nombres coinciden devuelve la posición en la que se encuentra
                pos = i;
                break;
            }
        }

        if (pos == -1) return camino; //si no se encontró el nodo eso quiere decir que no hay camino

        Nodo actual = fin; //como usa una cola, empieza por el final

        ListSE<Nodo> caminoReves = new ListSE<>(); //lista auxiliar para obtener el camino al revés

        while (actual != null) { //mientras haya nodos padres

            caminoReves.addLast(actual); //se añade el nodo actual a la lista del camino al revés

            int index = indiceDeNodo(actual); //busca el índice del nodo
            if (index == -1) { //si no está en la lista finaliza
                break;
            }

            actual = padres.get(index); //accede del nodo en el que se encuentra al nodo padre
        }

        for (int i = caminoReves.getSize() - 1; i >= 0; i--) { //invierte el camino
            camino.addLast(caminoReves.get(i));
        }

        return camino; //devuelve el camino de la forma correcta
    }

    private int indiceDeNodo(Nodo n) { //devuelve el índice de un nodo que ha sido visitado

        for (int i = 0; i < visitados.getSize(); i++) { //recorre la lista de nodos visitados

            if (visitados.get(i).nombre.equals(n.nombre)){  //si coincide el nombre del nodo con uno que esté visitado devuelve el indice
                return i;
            }
        }
        return -1; //si el nodo no está visitado no la devuelve
    }
}