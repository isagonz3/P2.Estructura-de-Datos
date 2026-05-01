package Descartadas.Carolina.arboles.arbol_recursivo.binario.metodos_comunes;

import Descartadas.Carolina.arboles.arbol_recursivo.binario.nodo.NodoBinario;

public class MainMetodos {

    public static void main(String[] args) {

        //crea el árbol
        NodoBinario<Integer> raiz = new NodoBinario<>(10);
        raiz.setLeft(new NodoBinario<>(5));
        raiz.setRight(new NodoBinario<>(15));

        raiz.getLeft().setLeft(new NodoBinario<>(3));
        raiz.getLeft().setRight(new NodoBinario<>(7));

        raiz.getRight().setLeft(new NodoBinario<>(12));
        raiz.getRight().setRight(new NodoBinario<>(18));


        //altura
        Altura<Integer> altura = new Altura<>();
        System.out.println("Altura del árbol: " + altura.calcularAltura(raiz));


        //profundidad
        Profundidad<Integer> profundidad = new Profundidad<>();

        System.out.println("Profundidad de 7: " +
                profundidad.calcularProfundidad(raiz, 7));

        System.out.println("Profundidad de 18: " +
                profundidad.calcularProfundidad(raiz, 18));


        //recorridos
        Recorridos rec = new Recorridos();

        System.out.println("\nPreorden: " + rec.preOrden(raiz));
        System.out.println("Inorden: " + rec.inOrden(raiz));
        System.out.println("Postorden: " + rec.postOrden(raiz));


        //representar el árbol
        GraficadorBinario graf = new GraficadorBinario();

        System.out.println("\nÁrbol graficado:\n" + graf.toGraficarString(raiz));


        //validar binario
        ValidadorBinario val = new ValidadorBinario();

        System.out.println("¿Es completo?: " + val.esCompleto(raiz));
        System.out.println("¿Es casi completo?: " + val.esCasiCompleto(raiz));
        System.out.println("¿Es homogéneo?: " + val.esHomogeneo(raiz));
    }
}