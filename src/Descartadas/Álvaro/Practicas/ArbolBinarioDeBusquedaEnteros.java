package Practicas;

import EstructurasP1.IndexedList;
import Árboles.ArbolBinario.Simple.BST;

public class ArbolBinarioDeBusquedaEnteros extends BST<Integer> {

    public int getSuma() {
        IndexedList<Integer> lista=getInOrder();
        int suma=0;
        for(int i=0;i<lista.len();i++){
            suma+=lista.get(i);
        }
        return suma;
    }
}
