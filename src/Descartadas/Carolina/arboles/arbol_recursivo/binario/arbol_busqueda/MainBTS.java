package Descartadas.Carolina.arboles.arbol_recursivo.binario.arbol_busqueda;

public class MainBTS {

    public static void main(String[] args) {

        //árbol bts sin duplicados
        System.out.println("----- BTS sin duplicados -----");

        ArbolBSTSinDuplicados<Integer> bstSinDup = new ArbolBSTSinDuplicados<>();

        bstSinDup.insertar(50);
        bstSinDup.insertar(30);
        bstSinDup.insertar(70);
        bstSinDup.insertar(20);
        bstSinDup.insertar(40);
        bstSinDup.insertar(60);
        bstSinDup.insertar(80);

        System.out.println("Inorden: " + bstSinDup.getListaOrdenCentral());

        System.out.println("Buscar 40: " + bstSinDup.buscar(40));
        System.out.println("Buscar 100: " + bstSinDup.buscar(100));

        bstSinDup.eliminar(30);

        System.out.println("Inorden tras eliminar 30: " + bstSinDup.getListaOrdenCentral());


        //árbol bts con duplicados
        System.out.println("\n----- BTS con duplicados -----");

        ArbolBSTConDuplicados<Integer> bstConDup = new ArbolBSTConDuplicados<>();

        bstConDup.insertar(50);
        bstConDup.insertar(30);
        bstConDup.insertar(30);
        bstConDup.insertar(70);
        bstConDup.insertar(70);
        bstConDup.insertar(20);
        bstConDup.insertar(40);

        System.out.println("Inorden: " + bstConDup.getListaOrdenCentral());

        System.out.println("Buscar 70: " + bstConDup.buscar(70));

        bstConDup.eliminar(30);

        System.out.println("Inorden tras eliminar 30: " + bstConDup.getListaOrdenCentral());


        //árbol balanceado, siempre bts
        System.out.println("\n----- AVL-----");

        ArbolBalanceado<Integer> avl = new ArbolBalanceado<>();

        avl.insertar(10);
        avl.insertar(20);
        avl.insertar(30);
        avl.insertar(40);
        avl.insertar(50);
        avl.insertar(25);

        System.out.println("Inorden (AVL): " + avl.getListaOrdenCentral());

        System.out.println("Buscar 25: " + avl.buscar(25));
        System.out.println("Buscar 99: " + avl.buscar(99));

        avl.eliminar(40);

        System.out.println("Inorden tras eliminar 40: " + avl.getListaOrdenCentral());
    }
}