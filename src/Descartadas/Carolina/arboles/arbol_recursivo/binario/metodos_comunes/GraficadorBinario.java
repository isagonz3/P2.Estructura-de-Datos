package Descartadas.Carolina.arboles.arbol_recursivo.binario.metodos_comunes;

import Descartadas.Carolina.arboles.arbol_recursivo.binario.nodo.NodoBinario;

public class GraficadorBinario { //clase que se encarga de representar el árbol en forma de texto

    public <T extends Comparable<T>> String toGraficarString(NodoBinario<T> raiz) { //metodo principal que devuelve el árbol como string

        if (raiz == null) { //si no hay raíz no hay nada que mostrar
            return "(árbol vacío)";
        }

        return graficarRec(raiz, "", true); //empieza desde la raíz sin prefijo y como último nodo
    }

    private <T extends Comparable<T>> String graficarRec(NodoBinario<T> nodo, String prefijo, boolean esUltimo) { //metodo recursivo que construye el dibujo del árbol

        String resultado = ""; //string donde se va acumulando el dibujo

        resultado = resultado + prefijo; //añade los espacios o líneas acumuladas de niveles anteriores

        if (esUltimo) { //si el nodo es el último hijo
            resultado = resultado + "└── "; //se dibuja con este símbolo
        }
        else { //si no es el último
            resultado = resultado + "├── "; //se dibuja con este otro símbolo
        }

        resultado = resultado + nodo.getData() + "\n"; //añade el dato del nodo y salto de línea

        NodoBinario<T> left = nodo.getLeft(); //hijo izquierdo
        NodoBinario<T> right = nodo.getRight(); //hijo derecho

        if (left == null && right == null) { //si es hoja no hay que seguir
            return resultado;
        }

        String nuevoPrefijo; //prefijo que se pasará a los hijos

        if (esUltimo) { //si este nodo era el último
            nuevoPrefijo = prefijo + "    "; //no se dibuja línea vertical
        }
        else {
            nuevoPrefijo = prefijo + "│   "; //se mantiene la línea vertical
        }

        if (right != null && left != null) { //si tiene los dos hijos
            resultado = resultado + graficarRec(right, nuevoPrefijo, false); //primero el derecho
            resultado = resultado + graficarRec(left, nuevoPrefijo, true); //después el izquierdo
        }
        else if (right != null) { //si solo tiene hijo derecho
            resultado = resultado + graficarRec(right, nuevoPrefijo, true);
        }
        else { //si solo tiene hijo izquierdo
            resultado = resultado + graficarRec(left, nuevoPrefijo, true);
        }

        return resultado; //devuelve el string construido
    }
}