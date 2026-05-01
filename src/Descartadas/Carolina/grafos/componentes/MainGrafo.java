package Descartadas.Carolina.grafos.componentes;

import Descartadas.Carolina.estructuras_necesarias.ListSE;
import Descartadas.Carolina.grafos.json.ArchivosJson;
import Descartadas.Carolina.grafos.json.Tripleta;

public class MainGrafo { //main de prueba del grafo básico

    public static void main(String[] args) {

        //cargar JSON
        ArchivosJson loader = new ArchivosJson("datos.json");
        ListSE<Tripleta> datos = loader.cargar();


        //construir grafo
        Grafo g = new Grafo();
        g.cargarDesdeTripletas(datos);


        //mostrar grafo en formato árbol
        System.out.println("----- Representación del grafo -----");
        g.imprimirComoArbol();


        //tripletas
        System.out.println("----- Tripletas en formato RDF -----");

        for (int i = 0; i < g.tripletas.getSize(); i++) {

            Tripleta t = g.tripletas.get(i);

            System.out.println( //imprime formato rdf
                    "<" + t.s + "> " +
                            "<" + t.p + "> " +
                            "<" + t.o + "> ."
            );
        }


        //buscar nodo
        System.out.println("\n----- Buscar un nodo -----");

        Nodo buscado = g.buscarNodo("persona:Albert Einstein");

        if (buscado != null) {

            System.out.println("Nodo encontrado: " + buscado.nombre);

            for (int i = 0; i < buscado.aristas.getSize(); i++) {

                Arista a = buscado.aristas.get(i);

                System.out.println(
                        "<" + buscado.nombre + "> " +
                                "<" + a.etiqueta + "> " +
                                "<" + a.destino.nombre + "> ."
                );
            }

        }
        else {
            System.out.println("Nodo no encontrado");
        }
    }
}