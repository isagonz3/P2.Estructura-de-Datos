package EL2b;

import TADs.Lista;

public class ConsultaGrafo<T extends Comparable<T>> {

    private Nodo<T> getCiudad(GrafoDeConocimiento<T> grafo, Nodo<T> persona) {
        for (int i = 0; i < grafo.getArcos().getSize(); i++) {
           Arco<T> arco = grafo.getArcos().get(i);

           if(arco.getSubject().equals(persona) && arco.getPredicate().equals("nace_en")) {
               return arco.getObject();
           }
        }
        return null;
    }

    private Nodo<T> getNodoPersona(GrafoDeConocimiento<T> grafo, Nodo<T> persona) {
        for (int i = 0; i < grafo.getNodos().getSize(); i++) {
            Nodo<T> nodo = grafo.getNodos().get(i);
            if(nodo.getType().equals(persona.getType()) && nodo.getDato().equals(persona.getDato())) {
                return nodo;
            }
        }
        return null;
    }

    private boolean tieneNobel(GrafoDeConocimiento<T> grafo, Nodo<T> persona) {
        for (int i = 0; i < grafo.getArcos().getSize(); i++) {
            Arco<T> arco = grafo.getArcos().get(i);

            if(arco.getSubject().equals(persona) && arco.getPredicate().equals("premio") && arco.getObject().getDato().equals("Nobel")) {
                return true;
            }
        }
        return false;
    }

    public Lista<Nodo<T>> mismaCiudadEinstein(GrafoDeConocimiento<T> grafo, Nodo<T> persona) {

        Lista<Nodo<T>> mismaCiudadEinstein = new Lista<>();

        for (int i = 0; i < grafo.getArcos().getSize(); i++) {
            Nodo<T> nodo = grafo.getNodos().get(i);
            if(nodo.getType().equals("persona")){
                Nodo<T> ciudad = getCiudad(grafo, nodo);
                if(ciudad != null && ciudad.getDato().equals("Ulm")) {
                    mismaCiudadEinstein.add(persona);
                }
            }
        }
        return mismaCiudadEinstein;
    }

    public Lista<Nodo<T>> ciudadesNobel(GrafoDeConocimiento<T> grafo){
        Lista<Nodo<T>> ciudadNobel = new Lista<>();

        for (int i = 0; i < grafo.getNodos().getSize(); i++) {
            Nodo<T> nodo = grafo.getNodos().get(i);
            if(nodo.getType().equals("persona") && tieneNobel(grafo, nodo)) {
                    Nodo<T> ciudad = getCiudad(grafo, nodo);

                    if(ciudad != null && !ciudadNobel.contains(ciudad)) {
                        ciudadNobel.add(ciudad);
                    }
            }
        }
        return ciudadNobel;
    }

}
