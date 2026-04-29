package EL2b;

import TADs.Lista;

public class ConsultaGrafo {

    private Nodo getCiudad(GrafoDeConocimiento grafo, Nodo persona) {
        for (int i = 0; i < grafo.getArcos().getSize(); i++) {
           Arco arco = grafo.getArcos().get(i);

           if(arco.getOrigen().equals(persona) && arco.getPredicate().equals("nace_en")) {
               return arco.getDestino();
           }
        }
        return null;
    }

    private Nodo getNodoPersona(GrafoDeConocimiento grafo, String nombrePersona) {
        for (int i = 0; i < grafo.getNodos().getSize(); i++) {
            Nodo nodo = grafo.getNodos().get(i);
            if(nodo.getType().equals("persona") && nodo.getDato().equals(nombrePersona)) {
                return nodo;
            }
        }
        return null;
    }

    private boolean tieneNobel(GrafoDeConocimiento grafo, Nodo persona) {
        for (int i = 0; i < grafo.getArcos().getSize(); i++) {
            Arco arco = grafo.getArcos().get(i);

            if(arco.getOrigen().equals(persona) && arco.getPredicate().equals("premio") && arco.getDestino().getDato().equals("Nobel")) {
                return true;
            }
        }
        return false;
    }

    public Lista<Nodo> mismaCiudadEinstein(GrafoDeConocimiento grafo, Nodo einstein) {

        Lista<Nodo> mismaCiudadEinstein = new Lista<>();
        Nodo ciudadEinstein = getCiudad(grafo, einstein);

        if (ciudadEinstein == null) {
            return mismaCiudadEinstein;
        }

        for (int i = 0; i < grafo.getArcos().getSize(); i++) {
            Nodo nodo = grafo.getNodos().get(i);
            if(nodo.getType().equals("persona") && !nodo.equals(einstein)) {
                Nodo ciudad = getCiudad(grafo, nodo);
                if (ciudad != null && ciudad.equals(ciudadEinstein)) {
                    mismaCiudadEinstein.add(nodo);
                }
            }
        }
        return mismaCiudadEinstein;
    }

    public Lista<Nodo> ciudadesNobel(GrafoDeConocimiento grafo){
        Lista<Nodo> ciudadNobel = new Lista<>();

        for (int i = 0; i < grafo.getNodos().getSize(); i++) {
            Nodo nodo = grafo.getNodos().get(i);
            if(nodo.getType().equals("persona") && tieneNobel(grafo, nodo)) {
                    Nodo ciudad = getCiudad(grafo, nodo);

                    if(ciudad != null && !ciudadNobel.contains(ciudad)) {
                        ciudadNobel.add(ciudad);
                    }
            }
        }
        return ciudadNobel;
    }

}
