package Descartadas.Carolina.grafos.componentes;

import Descartadas.Carolina.estructuras_necesarias.ListSE;

public class Nodo implements Comparable<Nodo> { //nodo que contiene la información del grafo

    //Atributos
    public String nombre; //nombre del nodo
    public ListSE<Arista> aristas; //lista con las aristas del nodo


    //Constructores
    public Nodo(String nombre) {
        this.nombre = nombre; //asigna el nombre
        this.aristas = new ListSE<>(); //inicializa la lista de aristas vacía
    }


    //Métodos
    @Override
    public int compareTo(Nodo o) { //el nodo implementa el metodo compareTo porque la ListSE que utilizamos lo exige
        return 0; //devuelve 0 porque en nuestro caso no hace falta ordenar nada
    }
}