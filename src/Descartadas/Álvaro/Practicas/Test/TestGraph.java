package Practicas.Test;

import EstructurasP1.IndexedList;
import Grafos.Graph;

public class TestGraph {

    public static void main(String[] args) {

        Graph<String, Void> g = new Graph<>();

        System.out.println("===== CREACIÓN DEL GRAFO =====");

        //CONSTRUCCIÓN
        g.addEdge("A", "B", null);
        g.addEdge("A", "C", null);
        g.addEdge("B", "D", null);
        g.addEdge("C", "D", null);
        g.addEdge("D", "E", null);
        g.addEdge("E", "F", null);

        //Nodo aislado (para probar disjunto)
        g.addNode("Z");
        System.out.println("Nodos: " + g.getNodes().len());
        System.out.println("Aristas: " + g.getEdges().len());

        //BFS
        System.out.println("\n===== BFS =====");
        IndexedList<String> bfs = g.BFS("A");
        System.out.print("BFS desde A: ");
        bfs.print();

        //DFS
        System.out.println("\n===== DFS =====");
        IndexedList<String> dfs = g.DFS("A");
        System.out.print("DFS desde A: ");
        dfs.print();

        //NEIGHBORS
        System.out.println("\n===== NEIGHBORS =====");
        IndexedList<?> neighA = g.neighbors("A");
        System.out.println("Vecinos de A: " + neighA);

        //EDGES FROM
        System.out.println("\n===== EDGES FROM =====");
        System.out.println("Aristas desde A: " + g.edgesFrom("A"));


        //EDGES TO
        System.out.println("\n===== EDGES TO =====");
        System.out.println("Aristas hacia D: " + g.edgesTo("D"));

        //SHORTEST PATH
        System.out.println("\n===== CAMINO MÍNIMO =====");
        IndexedList<String> path = g.shortestPath("A", "E");
        System.out.print("A -> E: ");
        path.print();
        System.out.println("Longitud: " + path.len());

        //DISJUNTO
        System.out.println("\n===== GRAFO DISJUNTO =====");
        System.out.println("¿Es disjunto?: " + g.isDisjoint());

        //CASO NODO AISLADO
        System.out.println("\n===== NODO AISLADO =====");
        System.out.println("Vecinos de Z: " + g.neighbors("Z"));
        System.out.println("BFS desde Z: " + g.BFS("Z"));

        //CASO NO EXISTENTE
        System.out.println("\n===== CASO NULL =====");
        System.out.println("BFS inexistente: " + g.BFS("X"));
        System.out.println("Camino inexistente: " + g.shortestPath("A", "X"));


        //BORRADO

        System.out.println("\n===== BORRADO =====");
        g.removeNode("C");
        System.out.println("Nodos tras borrar C: " + g.getNodes().len());
        System.out.println("Aristas tras borrar C: " + g.getEdges().len());

        System.out.println("\n===== FINAL =====");
        System.out.println("Nodos: " + g.getNodes().len());
        System.out.println("Aristas: " + g.getEdges().len());
    }
}