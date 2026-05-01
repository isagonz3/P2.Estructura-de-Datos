package Descartadas.Carolina.arboles.arbol_recursivo.binario.arbol_busqueda;

public class MainBTSEnteros {

    public static void main(String[] args) {

        //árbol bts de números enteros
        System.out.println("----- BTS de enteros -----");

        ArbolBinarioDeBusquedaEnteros bst = new ArbolBinarioDeBusquedaEnteros();

        bst.insertar(10);
        bst.insertar(5);
        bst.insertar(15);
        bst.insertar(3);
        bst.insertar(7);
        bst.insertar(12);
        bst.insertar(18);

        System.out.println("Inorden: " + bst.getListaOrdenCentral());
        System.out.println("Suma elementos BST: " + bst.calcularSumaElementos());


        //árbol balanceado, siempre bts
        System.out.println("\n----- AVL de enteros -----");

        ArbolBalanceadoEnteros avl = new ArbolBalanceadoEnteros();

        avl.insertar(10);
        avl.insertar(5);
        avl.insertar(15);
        avl.insertar(3);
        avl.insertar(7);
        avl.insertar(12);
        avl.insertar(18);

        System.out.println("Inorden: " + avl.getListaOrdenCentral());
        System.out.println("Suma elementos AVL: " + avl.calcularSumaElementos());


        //verificación manual para la suma de enteros
        System.out.println("\n----- Resultado manual -----");

        int esperado = 10 + 5 + 15 + 3 + 7 + 12 + 18;

        System.out.println("Suma esperada: " + esperado);
    }
}