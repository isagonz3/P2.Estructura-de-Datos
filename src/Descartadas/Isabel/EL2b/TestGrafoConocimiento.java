package Descartadas.Isabel.EL2b;

public class TestGrafoConocimiento {
    public static void main(String[] args) {

        String ruta = "src/EL2b/DatosConsultaGrafo.json";

        GrafoDeConocimiento grafo = new GrafoDeConocimiento();
        ParserJSON.cargarGrafo(ruta, grafo);

        System.out.println("====================================================");
        System.out.println("      CONSULTA GRAFO: Caminos, Einstein, etc.");
        System.out.println("====================================================");

        System.out.println("Nodos: " + grafo.getNodos().getSize()); //Numero de nodos en el grafo
        System.out.println("Arcos: " + grafo.getArcos().getSize()); //Numero de arcos en el grafo

        System.out.println("====================================================");
        System.out.println("          CONSULTA GRAFO: Disjunto o no");
        System.out.println("====================================================");

        System.out.println("¿Es el grafo disjunto? -----> " + grafo.isGrafoDisjunto());
        System.out.println();

        System.out.println("====================================================");
        System.out.println("         CONSULTA GRAFO: Camino más corto");
        System.out.println("====================================================");
        System.out.println("Busquemos el camino más corto entre Albert Einstein y Viena");
        System.out.println();
        printCamino(grafo, "persona:Albert Einstein", "lugar:Viena");
        System.out.println();

        System.out.println("====================================================");
        System.out.println("      CONSULTA GRAFO: Físicos nacidos en Ulm");
        System.out.println("====================================================");
        System.out.println("Busquemos que físicos famosos han nacido en la misma ciudad que Albert Einstein.");
        System.out.println();
        printListaCiudades(grafo);
        System.out.println();

        System.out.println("====================================================");
        System.out.println(" CONSULTA GRAFO: Ciudades de los ganadores del Nobel");
        System.out.println("====================================================");
        System.out.println("Listemos las ciudades natales de cada persona premiada con el Nobel dentro de nuestro archivo.");
        System.out.println();
        printListaNobel(grafo);
        System.out.println();

        System.out.println("====================================================");
        System.out.println("      CONSULTA GRAFO: Tipos de nodos");
        System.out.println("====================================================");
        System.out.println("Tipos de nodo dentro del grafo: ");
        System.out.println(grafo.getTypes());
        System.out.println("====================================================");

    }

    private static void printCamino (GrafoDeConocimiento grafo, String origen, String destino) {
        Nodo nodoOrigen = findNodo(grafo, origen);
        Nodo nodoDestino = findNodo(grafo, destino);

        if(nodoOrigen == null || nodoDestino == null){
            System.out.println("Nodos no encontrados para el grafo");
            System.out.println();
            return;
        }

        Camino findCamino = new Camino();
        Lista<Nodo> caminoNodos = findCamino.caminoBFS(grafo, nodoOrigen, nodoDestino);
        System.out.println("El camino mínimo entre los nodos: \n" + origen + " ------> " + destino);

        if(caminoNodos != null){
            for(int i = 0; i < caminoNodos.getSize(); i++) {
                System.out.println();
                System.out.print(" " + caminoNodos.get(i));
                if(i < caminoNodos.getSize() - 1){
                    System.out.print("--->");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println("El camino tiene una longitud de " + (caminoNodos.getSize() - 1) + " arcos");

        }
        else{
            System.out.println("No se ha encontrado camino");
        }
    }

    private static void printListaCiudades(GrafoDeConocimiento grafo) {

        ConsultaGrafo consulta = new ConsultaGrafo();
        Lista<Nodo> mismaCiudad = consulta.mismaCiudadEinstein(grafo, "Albert Einstein");

        System.out.println("Otros físicos famosos nacidos en Ulm: ");

        if(mismaCiudad == null || mismaCiudad.getSize() <= 0){
            System.out.println("No se han encontrado fisicos nacidos en Ulm.");
            return;
        }
        for(int c = 0; c < mismaCiudad.getSize(); c++){
            Nodo nodoFisico = mismaCiudad.get(c);
            System.out.print(nodoFisico);
            if (c < mismaCiudad.getSize() - 1) System.out.print(", ");
        }
        System.out.println();
    }

    private static void printListaNobel(GrafoDeConocimiento grafo) {
        ConsultaGrafo consulta = new ConsultaGrafo();
        Lista<Nodo> ciudadesNobel = consulta.ciudadesNobel(grafo);

        System.out.println("Ciudades natales de los laureados: ");

        for(int c = 0; c < ciudadesNobel.getSize(); c++){
            Nodo nodoNobel = ciudadesNobel.get(c);
            System.out.print(nodoNobel);
            if (c < ciudadesNobel.getSize() - 1) System.out.print(", ");
        }
        System.out.println();
    }

    private static Nodo findNodo(GrafoDeConocimiento grafo, String dato) {
        String[] parts = dato.split(":");
        if (parts.length != 2) {
            return null;
        }
        for (int i = 0; i < grafo.getNodos().getSize(); i++) {
            Nodo n = grafo.getNodos().get(i);
            if (n.getType().equals(parts[0]) && n.getDato().equals(parts[1])) {
                return n;
            }
        }
        return null;
    }
}

