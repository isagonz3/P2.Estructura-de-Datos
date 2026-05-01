package Descartadas.Carolina.grafos.componentes;

import Descartadas.Carolina.estructuras_necesarias.ListSE;
import Descartadas.Carolina.grafos.json.Tripleta;

public class Grafo { //estructura principal del grafo

    //Atributos
    public ListSE<Nodo> nodos; //lista de nodos del grafo
    public ListSE<Tripleta> tripletas = new ListSE<>(); //lista de tripletas (sujeto, predicado, objeto) para formato RDF


    //Constructor
    public Grafo() {
        nodos = new ListSE<>(); //inicializa la lista de nodos
    }


    //Métodos
    public Nodo buscarNodo(String nombre_buscado) { //busca un nodo por su nombre
        for (int i = 0; i < nodos.getSize(); i++) { //recorre toda la lista de nodos

            Nodo n = nodos.get(i); //obtiene el nodo en la posición i

            if (n.nombre.equals(nombre_buscado)) { //si el nombre del nodo en el que estamos coincide con el nombre que buscamos, devuelve el nodo
                return n;
            }
        }
        return null; //si no lo encuentra devuelve null
    }


    public Nodo obtenerNodo(String nombre) { //devuelve un nodo si existe y si no existe lo crea

        Nodo n = buscarNodo(nombre); //busca el nodo directamente utilizando el metodo anterior

        if (n == null) { //si no existe el nodo, lo crea y lo añade a la lista de nodos
            n = new Nodo(nombre);
            nodos.addLast(n);
        }

        return n; //si existe entonces lo devuelve
    }


    public void agregarTripleta(String sujeto, String predicado, String objeto) { //añade una relación al grafo

        Nodo s = obtenerNodo(sujeto); //obtiene o crea el nodo sujeto utilizando el metodo anterior
        Nodo o = obtenerNodo(objeto); //obtiene o crea el nodo objeto utilizando el metodo anterior

        Arista a = new Arista(predicado, o); //crea la arista que conecta el nodo objeto

        s.aristas.addLast(a); //añade la arista al nodo sujeto para conseguir la estructura (sujeto,predicado,objeto) del nodo

        tripletas.addLast(new Tripleta(sujeto, predicado, objeto)); //guarda la tripleta en la lista que hemos creado
    }


    public void cargarDesdeTripletas(ListSE<Tripleta> lista) { //carga un grafo desde una lista de tripletas para mantener la estructura

        for (int i = 0; i < lista.getSize(); i++) { //recorre todas las tripletas de la lista

            Tripleta t = lista.get(i); //obtiene la tripleta actual

            agregarTripleta(t.s, t.p, t.o); //la añade al grafo utilizando el metodo anterior
        }
    }


    public void imprimirComoArbol() { //imprime el grafo con forma de "árbol" para que sea más visual

        for (int i = 0; i < nodos.getSize(); i++) { //recorre todos los nodos del grafo

            Nodo n = nodos.get(i); //declara el nodo actual

            if (n.aristas.getSize() > 0) { //si el nodo tiene conexiones

                imprimirNodoComoArbol(n, ""); //empieza a imprimir desde ese nodo
                System.out.println(); //salto de línea entre árboles
            }
        }
    }

    private void imprimirNodoComoArbol(Nodo nodo, String prefijo) { //imprime un nodo y sus conexiones en forma de "árbol" para que sea más visual

        System.out.println(prefijo + nodo.nombre); //imprime el nodo actual con su prefijo

        int total = nodo.aristas.getSize(); //número de conexiones del nodo

        for (int i = 0; i < total; i++) { //recorre todas las aristas del nodo

            Arista a = nodo.aristas.get(i); //arista actual

            boolean esUltima = (i == total - 1); //comprueba si es la última conexión

            String conector = esUltima ? "└── " : "├── "; //elige el símbolo de dibujo
            String nuevoPrefijo = prefijo + (esUltima ? "    " : "│   "); //actualiza el prefijo para la siguiente profundidad

            System.out.println(prefijo + conector + a.etiqueta + " → " + a.destino.nombre); //imprime la conexión

            if (a.destino.aristas.getSize() > 0) { //si el nodo destino tiene más conexiones
                imprimirNodoComoArbol(a.destino, nuevoPrefijo); //utiliza la recursion para seguir bajando
            }
        }
    }
}