package EL2a.ArbolBusquedaBinario;

import java.util.List;

public class TestArbolEnterosEnOrden {
    public static void main(String[] args) {

        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();

        for(int i=0; i<=128; i++){
            arbol.add(i);
        }
        //Calculamos la suma total de los nodos
        int sumaEnteros = arbol.getSuma();
        System.out.println("Suma: " + sumaEnteros);
        System.out.println("--------------------");

        //Comprobamos que la suma es igual para inorden, preorden y postorden

        //Suma de nodos en el recorrido Inorden
        int sumaInorden = 0;
        for(NodoArbolBinario<Integer> nodo : arbol.getListaInorden()){
            sumaInorden += nodo.getDato();
        }
        System.out.println("Suma inorden: " + sumaInorden);
        System.out.println("--------------------");

        //Suma de nodos en el recorrido Preorden
        int sumaPreorden = 0;
        for(NodoArbolBinario<Integer> nodo : arbol.getListaPreorden()){
            sumaPreorden += nodo.getDato();
        }
        System.out.println("Suma preorden: " + sumaPreorden);
        System.out.println("--------------------");

        //Suma de nodos en el recorrido Postorden
        int sumaPostorden = 0;
        for(NodoArbolBinario<Integer> nodo : arbol.getListaPostorden()){
            sumaPostorden += nodo.getDato();
        }
        System.out.println("Suma postorden: " + sumaPostorden);
        System.out.println("--------------------");

        //Comprobamos que el resultado de la suma coincide para todos los casos
        if(sumaEnteros == sumaInorden && sumaEnteros == sumaPreorden && sumaEnteros == sumaPostorden){
            System.out.println("El resultado es igual en todos los recorridos");
            System.out.println("--------------------");
        }
        else{
            System.out.println("El resultado no coincide");
        }

        //Comprobamos que el resultado de la suma coincide sumando los elementos de los
        //subarboles
        int sumaSubIzq = arbol.sumaNodos(arbol.raiz.getIzq());
        int sumaSubDer = arbol.sumaNodos(arbol.raiz.getDer());
        int sumaSubArboles = arbol.raiz.getDato() + sumaSubIzq + sumaSubDer;

        System.out.println("Suma por subarboles: " + sumaSubArboles);
        System.out.println("--------------------");

        if(sumaSubArboles == sumaEnteros){
            System.out.println("El resultado coincide sumando por subarboles");
            System.out.println("--------------------");
        }
        else{
            System.out.println("El resultado no coincide");
            System.out.println("--------------------");
        }

        //Obtener la altura del arbol
        int altura = arbol.getAltura();
        System.out.println("Altura: " + altura);
        System.out.println("--------------------");

        //Obtener el camino hasta el valor 110
        List<Integer> camino = arbol.getCamino(arbol.raiz, 110);
        System.out.println("Camino hasta el valor 110: " + camino);
        System.out.println("--------------------");

        //Obtener la longitud del camino hasta el valor 110
        int longCamino = camino.size() -1;
        System.out.println("Longitud del camino hasta el valor 110: " + longCamino);
        System.out.println("--------------------");

    }
}