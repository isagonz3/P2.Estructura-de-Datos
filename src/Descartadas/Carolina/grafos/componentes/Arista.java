package Descartadas.Carolina.grafos.componentes;

public class Arista implements Comparable<Arista> { //arista que une dos nodos del grafo

    //Atributos
    public String etiqueta; //nombre de la arista
    public Nodo destino; //nodo al que apunta la arista


    //Constructores
    public Arista(String etiqueta, Nodo destino) {
        this.etiqueta = etiqueta; //asigna el nombre
        this.destino = destino; //asigna el nodo destino
    }


    //Métodos
    @Override
    public int compareTo(Arista o) { //la arista implementa el metodo compareTo porque la ListSE que utilizamos lo exige
        return 0; //devuelve 0 porque en nuestro caso no hace falta ordenar nada
    }
}