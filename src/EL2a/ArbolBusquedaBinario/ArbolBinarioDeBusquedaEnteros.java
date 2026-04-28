package EL2a.ArbolBusquedaBinario;

public class ArbolBinarioDeBusquedaEnteros extends ArbolBinarioDeBusqueda<Integer> {
    public ArbolBinarioDeBusquedaEnteros() {
        super();
    }

    public int getSuma(){
        return sumaNodos(raiz);
   }

    public int sumaNodos(NodoArbolBinario<Integer> nodo){
        if(nodo == null) return 0;
        return nodo.dato + sumaNodos(nodo.izq) + sumaNodos(nodo.der);
    }

}
