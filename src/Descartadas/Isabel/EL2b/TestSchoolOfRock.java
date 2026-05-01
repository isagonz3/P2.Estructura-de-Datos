package EL2b;

import TADs.Lista;

//Grafo basado en la película School Of Rock (2003), protagonizada por Jack Black
public class TestSchoolOfRock {
    public static void main(String[] args) {
        String ruta = "src/EL2b/SchoolOfRock.json";

        GrafoDeConocimiento grafo = new GrafoDeConocimiento();
        ParserJSON.cargarGrafo(ruta, grafo);

        System.out.println("====================================================");
        System.out.println("        GRAFO SCHOOL OF ROCK - CONSULTA");
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
        System.out.println("Busquemos el camino más corto entre R&B y Motorhead");
        System.out.println();
        printCamino(grafo, "Genre:R&B", "Artist:Motorhead");
        imprimirArbolASCII(grafo);

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

    private static void imprimirArbolASCII(GrafoDeConocimiento grafo) {
        System.out.println("\n====================================================");
        System.out.println("             GRAFO SCHOOL OF ROCK - ÁRBOL");
        System.out.println("====================================================\n");

        Lista<Nodo> raices = new Lista<>();
        for (int i = 0; i < grafo.getNodos().getSize(); i++) {
            Nodo n = grafo.getNodos().get(i);
            if (n.getType().equals("Genre") || n.getType().equals("SubGenre")) {
                raices.add(n);
            }
        }

        for (int i = 0; i < raices.getSize(); i++) {
            imprimirNodo(grafo, raices.get(i), "", i == raices.getSize() - 1, new Lista<>());
            System.out.println();
        }
    }

    private static void imprimirNodo(GrafoDeConocimiento grafo, Nodo nodo, String prefijo, boolean esUltimo, Lista<Nodo> visitados) {

        if (visitados.contains(nodo)) {
            System.out.println(prefijo + (esUltimo ? "└── " : "├── ") + nodo);
            return;
        }
        visitados.add(nodo);

        System.out.println(prefijo + (esUltimo ? "└── " : "├── ") + nodo);

        String childPrefijo = prefijo + (esUltimo ? "    " : "│   ");

        Lista<Arco> hijosInfluenced = new Lista<>();
        Lista<Arco> hijosIncludes = new Lista<>();
        Lista<Arco> hijosInspired = new Lista<>();

        for (int i = 0; i < grafo.getArcos().getSize(); i++) {
            Arco a = grafo.getArcos().get(i);
            if (a.getOrigen().equals(nodo)) {
                switch (a.getPredicate()) {
                    case "influenced": hijosInfluenced.add(a); break;
                    case "includes":   hijosIncludes.add(a);   break;
                    case "inspired":   hijosInspired.add(a);   break;
                }
            }
        }

        int totalHijos = hijosInfluenced.getSize() + hijosIncludes.getSize() + hijosInspired.getSize();
        int idx = 0;

        for (int i = 0; i < hijosIncludes.getSize(); i++) {
            boolean ultimo = (++idx == totalHijos);
            Arco a = hijosIncludes.get(i);
            System.out.println(childPrefijo + (ultimo ? "└── " : "├── ") + "★ " + a.getDestino().getDato());
        }

        for (int i = 0; i < hijosInspired.getSize(); i++) {
            boolean ultimo = (++idx == totalHijos);
            Arco a = hijosInspired.get(i);
            System.out.println(childPrefijo + (ultimo ? "└── " : "├── ") + "▲ inspired → " + a.getDestino().getDato());
        }

        for (int i = 0; i < hijosInfluenced.getSize(); i++) {
            boolean ultimo = (++idx == totalHijos);
            Arco a = hijosInfluenced.get(i);
            imprimirNodo(grafo, a.getDestino(), childPrefijo, ultimo, visitados);
        }
    }

}
