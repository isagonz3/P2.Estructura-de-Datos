package Descartadas.Carolina.arboles.arbol_recursivo.binario;

import Descartadas.Carolina.arboles.arbol_recursivo.binario.metodos_comunes.GraficadorBinario;
import Descartadas.Carolina.arboles.arbol_recursivo.binario.metodos_comunes.Recorridos;
import Descartadas.Carolina.arboles.arbol_recursivo.binario.metodos_comunes.ValidadorBinario;
import Descartadas.Carolina.arboles.arbol_recursivo.binario.nodo.NodoBinario;
import Descartadas.Carolina.arboles.arbol_recursivo.interfaces.Arbol;
import Descartadas.Carolina.estructuras_necesarias.MyList;

public abstract class ArbolBinarioBase<T extends Comparable<T>> implements Arbol<T> { //clase base abstracta para árboles binarios genéricos

    //Atributos
    protected NodoBinario<T> raiz; //raíz del árbol

    //utilidades
    private final Recorridos recorridos = new Recorridos(); //recorridos del árbol
    private final ValidadorBinario validador = new ValidadorBinario(); //validaciones del árbol
    private final GraficadorBinario graficador = new GraficadorBinario(); //representación del árbol

    //Constructores
    public ArbolBinarioBase() {this.raiz = null;} //inicializa árbol vacío

    //Métodos
    @Override
    public boolean isEmpty() {return raiz == null;} //comprueba si el árbol está vacío


    @Override
    public int getAltura() {return altura(raiz);} //devuelve la altura del árbol

    private int altura(NodoBinario<T> nodo) { //calcula la altura del árbol
        if (nodo == null) return -1; //caso base

        int izq = altura(nodo.getLeft()); //altura izquierda
        int der = altura(nodo.getRight()); //altura derecha

        return (izq > der ? izq : der) + 1; //máximo + 1
    }


    @Override
    public boolean isArbolHomogeneo() {return validador.esHomogeneo(raiz);} //árbol homogéneo


    @Override
    public boolean isArbolCompleto() {return validador.esCompleto(raiz);} //árbol completo


    @Override
    public boolean isArbolCasiCompleto() {return validador.esCasiCompleto(raiz);} //árbol casi completo


    @Override
    public MyList<T> getListaPreOrden() {return recorridos.preOrden(raiz);} //preorden


    @Override
    public MyList<T> getListaPostOrden() {return recorridos.postOrden(raiz);} //postorden


    @Override
    public MyList<T> getListaOrdenCentral() {return recorridos.inOrden(raiz);} //inorden


    @Override
    public String toString() {
        return graficador.toGraficarString(raiz); //representación del árbol
    }
}