package Descartadas.Carolina.grafos.metodos;

import Descartadas.Carolina.estructuras_necesarias.ListSE;
import Descartadas.Carolina.grafos.componentes.Arista;
import Descartadas.Carolina.grafos.componentes.Grafo;
import Descartadas.Carolina.grafos.componentes.Nodo;

public class ConsultasNobel { //ejercicios sobre personas con premio nobel en el grafo

    //Atributos
    private Grafo grafo; //grafo sobre el que se hacen las consultas


    //Constructor
    public ConsultasNobel(Grafo g) {
        this.grafo = g; //inicializa el grafo
    }


    //Métodos
    public ListSE<String> fisicosMismaCiudadQueEinstein() { //devuelve físicos nobel nacidos en la misma ciudad que Einstein (ejercicio1)

        Nodo fisico = grafo.buscarNodo("persona:Albert Einstein"); //busca el nodo (en este caso Einstein)
        ListSE<String> resultado = new ListSE<>(); //lista que va a contener el resto de nobeles de la misma ciudad

        if (fisico == null) { //si no existe el nodo no se puede hacer nada
            return resultado;
        }

        String ciudadFisico = null; //guardará la ciudad de nacimiento del nodo que se quiere comparar (en este caso Einstein)

        for (int i = 0; i < fisico.aristas.getSize(); i++) { //recorre las relaciones del nodo

            Arista a = fisico.aristas.get(i); //obtiene cada arista del nodo

            if (a.etiqueta.equals("nace_en")) { //si es la relación de nacimiento, guarda el nodo destino que corresponde con la ciudad en la que nace
                ciudadFisico = a.destino.nombre;
            }
        }

        for (int i = 0; i < grafo.nodos.getSize(); i++) { //recorre todos los nodos que hay en el grafo

            Nodo n = grafo.nodos.get(i); //coge cada uno de los nodos

            if (!n.nombre.startsWith("persona:")){ //asegura que solo se seleccionen nodos de personas
                continue;
            }
            if (n.nombre.equals("persona:Albert Einstein")){ //ignora el nodo de Einstein que es el que se busca
                continue;
            }

            boolean esNobel = false; //indica si tiene nobel

            String ciudad = null; //ciudad de nacimiento del nodo actual

            for (int j = 0; j < n.aristas.getSize(); j++) { //recorre todas las aristas del nodo actual

                Arista a = n.aristas.get(j); //coge la arista

                if (a.etiqueta.equals("premio:Nobel")) { //indica si tiene nobel
                    esNobel = true;
                }

                if (a.etiqueta.equals("nace_en")) { //indica si tiene ciudad de nacimiento
                    ciudad = a.destino.nombre;
                }
            }

            if (esNobel && ciudad != null && ciudad.equals(ciudadFisico)) { //si tiene nobel y tiene lugar de nacimiento, se añade al resultado
                resultado.addLast(n.nombre);
            }
        }

        return resultado; //devuelve la lista final con los fisico qcon nobel que hayan nacido donde Einstein
    }


    public ListSE<String> lugaresNacimientoNobel() { //devuelve lugares de nacimiento de personas con nobel (ejercicio2)

        ListSE<String> resultado = new ListSE<>(); //lista resultado

        for (int i = 0; i < grafo.nodos.getSize(); i++) { //recorre todos los nodos del grafo

            Nodo n = grafo.nodos.get(i); //indica el nodo actual

            if (!n.nombre.startsWith("persona:")) { //asegura que solo se seleccionen nodos de personas
                continue;
            }

            boolean esNobel = false; //indica si tiene nobel
            String ciudad = null; //indica el lugar de nacimiento

            for (int j = 0; j < n.aristas.getSize(); j++) { //recorre las aristas

                Arista a = n.aristas.get(j); //declara la arista actual

                if (a.etiqueta.equals("premio:Nobel")) { //marca si tiene premio nobel
                    esNobel = true;
                }
                if (a.etiqueta.equals("nace_en")) { //guarda la ciudad de nacimiento
                    ciudad = a.destino.nombre;
                }
            }

            if (esNobel && ciudad != null) { //si tiene premio nobel y lugar de nacimiento lo añade a la lista
                resultado.addLast(ciudad);
            }
        }

        return resultado; //devuelve la lista final con las ciudades de nacimiento de todos las personas con premio nobel
    }
}