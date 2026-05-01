package Descartadas.Carolina.arboles.arbol_recursivo.binario;

import Descartadas.Carolina.arboles.arbol_recursivo.binario.metodos_comunes.Altura;
import Descartadas.Carolina.arboles.arbol_recursivo.binario.metodos_comunes.Profundidad;
import Descartadas.Carolina.arboles.arbol_recursivo.binario.nodo.NodoBinario;
import Descartadas.Carolina.estructuras_necesarias.ListSE;
import Descartadas.Carolina.estructuras_necesarias.MyList;

public class ArbolBinario<T extends Comparable<T>> extends ArbolBinarioBase<T> {

    @Override
    public void add(T data) { //inserta un nodo en el árbol
        raiz = insertarRec(raiz, data); //llamada recursiva desde la raíz
    }


    private NodoBinario<T> insertarRec(NodoBinario<T> nodo, T data) { //inserción recursiva

        if (nodo == null) { //si está vacío crea nodo
            return new NodoBinario<>(data);
        }

        int cmp = data.compareTo(nodo.data); //comparación con nodo actual

        if (cmp < 0) { //si es menor
            nodo.setLeft(insertarRec(nodo.getLeft(), data)); //va a la izquierda
        }
        else { //si es mayor o igual
            nodo.setRight(insertarRec(nodo.getRight(), data)); //va a la derecha
        }

        return nodo; //devuelve nodo actualizado
    }


    Altura<T> altura = new Altura<>(); //objeto para calcular altura

    @Override
    public int getAltura() { //devuelve altura del árbol
        return altura.calcularAltura(raiz); //usa clase Altura
    }


    @Override
    public MyList<T> getCamino(T data) { //camino desde raíz hasta un nodo

        MyList<T> camino = new ListSE<>(); //lista del recorrido
        NodoBinario<T> actual = raiz; //empieza en la raíz

        while (actual != null) { //mientras haya nodos

            camino.add(actual.data); //añade nodo actual

            int cmp = data.compareTo(actual.data); //comparación

            if (cmp == 0) { //si se encuentra se devuelve el camino
                return camino;
            }

            if (cmp < 0) { //si es menor
                actual = actual.getLeft(); //izquierda
            }
            else { //si es mayor
                actual = actual.getRight(); //derecha
            }
        }

        return new ListSE<>(); //si no existe devuelve vacío
    }

    Profundidad<T> prof = new Profundidad<>(); //objeto para profundidad


    @Override
    public int getGrado() { //grado del árbol
        return 0; //árbol binario estándar (máx 2, pero aquí no se calcula)
    }


    @Override
    public MyList<T> getListaDatosNivel(int nivel) { //nodos por nivel
        return new ListSE<>(); //sin implementar en esta clase
    }
}