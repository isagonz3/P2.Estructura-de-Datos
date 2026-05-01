package Descartadas.Carolina.grafos.json;

public class Tripleta implements Comparable<Tripleta> { //representa una tripleta rdf (sujeto, predicado, objeto)

    //Atributos
    public String s; //sujeto
    public String p; //predicado
    public String o; //objeto


    //Constructores
    public Tripleta(String sujeto, String predicado, String objeto) {
        this.s = sujeto; //asigna sujeto
        this.p = predicado; //asigna predicado
        this.o = objeto; //asigna objeto
    }


    //Métodos
    @Override
    public String toString() { //devuelve la tripleta en el formato RDF: <sujeto><predicado><objeto>.
        return "<" + s + "> <" + p + "> <" + o + "> .";
    }

    @Override
    public int compareTo(Tripleta o) { //la tripleta implementa el metodo compareTo porque la ListSE que utilizamos lo exige
        return 0; //devuelve 0 porque en nuestro caso no hace falta ordenar nada
    }
}