package Practicas.Test;

import Grafos.RDF;
import EstructurasP1.IndexedList;

public class TestRDF{

    public static void main(String[] args) {

        RDF rdf=new RDF();

        //CARGA DEL GRAFO DESDE JSON RDF N-TRIPLES
        System.out.println("===== CARGA DEL GRAFO =====");
        rdf.loadFromFile("datos.json");
        System.out.println("Nodos: " + rdf.getGraph().getNodes().len());
        System.out.println("Aristas: " + rdf.getGraph().getEdges().len());

        //CAMINO MÍNIMO ENTRE DOS ENTIDADES
        System.out.println("\n===== CAMINO MINIMO =====");
        IndexedList<String> path = rdf.getGraph().shortestPath("persona:Albert Einstein", "persona:Max Planck");
        System.out.print("Camino Einstein → Planck: ");
        path.print();
        System.out.println("Longitud: " + path.len());

        //GRAFO DISJUNTO (COMPONENTES CONEXAS)
        System.out.println("\n===== GRAFO DISJUNTO =====");
        boolean disjunto = rdf.getGraph().isDisjoint();
        System.out.println("¿Es disjunto?: " + disjunto);

        //CONSULTA RDF (MISMA CIUDAD QUE EINSTEIN)
        System.out.println("\n===== MISMA CIUDAD QUE EINSTEIN =====");
        IndexedList<String> sameCity = rdf.getNodesWithSamePredicateAndObject("persona:Albert Einstein", "nace_en");
        System.out.print("Físicos misma ciudad: ");
        sameCity.print();

        //INSERCIÓN DE NUEVA TRIPLA
        System.out.println("\n===== INSERCION DE TRIPLA =====");
        rdf.addTriplet("persona:Antonio", "nace_en", "lugar:Villarrubia de los Caballeros");
        System.out.println("Tripleta añadida correctamente");

        //LISTAR LUGARES DE NACIMIENTO DE PREMIOS NOBEL
        System.out.println("\n===== LUGARES NACIMIENTO NOBEL =====");

        IndexedList<String> nobelBirthPlaces = rdf.getSubjectsByPredicate("premio:Nobel");
        System.out.print("Sujetos asociados a Nobel: ");
        nobelBirthPlaces.print();


        //VERIFICACIÓN DE CONSULTA RDF GENERAL
        System.out.println("\n===== VERIFICACION RDF =====");
        IndexedList<String> bornInUlm = rdf.getSubjects("lugar:Ulm");
        System.out.print("Personas nacidas en Ulm: ");
        bornInUlm.print();
    }
}
