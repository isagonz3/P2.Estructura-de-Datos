package Descartadas.Carolina.arboles.arbol_iterativo;

public class ArbolBusquedaBinariaEnteros extends ArbolBusquedaBinaria<Integer> { //árbol de enteros que hereda del árbol genérico

    //Constructores
    public ArbolBusquedaBinariaEnteros() {
        super(); //llama al constructor del padre
    }

    //Métodos
    public int getSuma() { //devuelve la suma total de todos los nodos del árbol
        return sumaTotal(raiz); //empieza a sumar desde la raíz
    }

    private int sumaTotal(NodoArbol<Integer> nodo) { //suma todos los valores del árbol
        if (nodo == null) { //si el nodo es nulo no aporta nada a la suma
            return 0;
        }

        int sumaIzq = sumaTotal(nodo.hijoIzquierdo); //suma el subarbol izquierdo
        int sumaDer = sumaTotal(nodo.hijoDerecho); //suma el subarbol derecho

        return nodo.dato + sumaIzq + sumaDer; //devuelve el valor del nodo más lo que suman sus hijos
    }
}