package Practicas.Test;

import EstructurasP1.IndexedList;
import Practicas.ArbolBinarioBalanceadoEnteros;
import Practicas.ArbolBinarioDeBusquedaEnteros;

public class TestOrdenado {
    public static void main(String[] args) {

        ArbolBinarioDeBusquedaEnteros bst = new ArbolBinarioDeBusquedaEnteros();
        ArbolBinarioBalanceadoEnteros bbst = new ArbolBinarioBalanceadoEnteros();

        //Insertar 0..128 en orden
        for (int i = 0; i <= 128; i++) {
            bst.add(i);
            bbst.add(i);
        }

        System.out.println("===== BST =====");

        //Suma con getSuma()
        System.out.println("Suma getSuma(): " + bst.getSuma());

        //Suma usando recorridos
        int sumaPre = 0;
        IndexedList<Integer> pre = bst.getPreOrder();
        for (int i = 0; i < pre.len(); i++) sumaPre += pre.get(i);
        System.out.println("Suma PreOrder: " + sumaPre);

        int sumaIn = 0;
        IndexedList<Integer> in = bst.getInOrder();
        for (int i = 0; i < in.len(); i++) sumaIn += in.get(i);
        System.out.println("Suma InOrder: " + sumaIn);

        int sumaPost = 0;
        IndexedList<Integer> post = bst.getPostOrder();
        for (int i = 0; i < post.len(); i++) sumaPost += post.get(i);
        System.out.println("Suma PostOrder: " + sumaPost);

        // Suma subárboles
        int sumaIzq = 0;
        IndexedList<Integer> izq = bst.getLeftSubtree().getInOrder();
        for (int i = 0; i < izq.len(); i++) sumaIzq += izq.get(i);

        int sumaDer = 0;
        IndexedList<Integer> der = bst.getRightSubtree().getInOrder();
        for (int i = 0; i < der.len(); i++) sumaDer += der.get(i);

        System.out.println("Suma Subárbol izquierdo: " + sumaIzq);
        System.out.println("Suma Subárbol derecho: " + sumaDer);
        System.out.println("Suma total=sumaIzq+sumaDer+raíz (" +(bst.getSuma() == sumaIzq+sumaDer+bst.getRoot().getData())+")");
        // Altura
        System.out.println("Altura BST: " + bst.getHeight());

        // Camino a 110
        IndexedList<Integer> camino = bst.getPath(110);
        System.out.print("Camino a 110: "); camino.print();
        System.out.println("Longitud camino: " + camino.len());


        System.out.println("===== BBST =====");

        // Repetimos todo para el balanceado
        System.out.println("Suma getSuma(): " + bbst.getSuma());

        sumaPre = 0;
        pre = bbst.getPreOrder();
        for (int i = 0; i < pre.len(); i++) sumaPre += pre.get(i);
        System.out.println("Suma PreOrder: " + sumaPre);

        sumaIn = 0;
        in = bbst.getInOrder();
        for (int i = 0; i < in.len(); i++) sumaIn += in.get(i);
        System.out.println("Suma InOrder: " + sumaIn);

        sumaPost = 0;
        post = bbst.getPostOrder();
        for (int i = 0; i < post.len(); i++) sumaPost += post.get(i);
        System.out.println("Suma PostOrder: " + sumaPost);

        sumaIzq = 0;
        izq = bbst.getLeftSubtree().getInOrder();
        for (int i = 0; i < izq.len(); i++) sumaIzq += izq.get(i);

        sumaDer = 0;
        der = bbst.getRightSubtree().getInOrder();
        for (int i = 0; i < der.len(); i++) sumaDer += der.get(i);

        System.out.println("Suma Subárbol izquierdo: " + sumaIzq);
        System.out.println("Suma Subárbol derecho: " + sumaDer);
        System.out.println("Suma total=sumaIzq+sumaDer+raíz (" +(bbst.getSuma() == sumaIzq+sumaDer+bbst.getRoot().getData())+")");

        System.out.println("Altura BBST: " + bbst.getHeight());

        camino = bbst.getPath(110);
        System.out.print("Camino a 110: "); camino.print();
        System.out.println("Longitud camino: " + camino.len());
    }
}
