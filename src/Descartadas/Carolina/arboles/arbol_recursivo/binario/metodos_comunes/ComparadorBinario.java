package Descartadas.Carolina.arboles.arbol_recursivo.binario.metodos_comunes;

public class ComparadorBinario { //clase auxiliar para comparar datos genéricos

    public static <T extends Comparable<T>> int comparar(T a, T b) { //compara dos valores
        if (a == null && b == null) return 0; //si ambos son nulos se consideran iguales
        if (a == null) return -1; //si solo a es nulo se considera menor
        if (b == null) return 1; //si solo b es nulo entonces a es mayor
        return a.compareTo(b); //si ninguno es nulo se usa la comparación natural
    }
}