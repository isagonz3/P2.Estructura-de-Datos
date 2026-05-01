package Practicas.Test;

import EstructurasP1.IndexedList;
import Nodos.Arboles.Binarios.BinaryNodeDuplicate;
import Practicas.Coche;
import Árboles.ArbolBinario.Simple.BSTD;
import Árboles.ArbolBinario.Balanceado.BBSTD;

public class TestArbolesDuplicados {

    public static void main(String[] args) {

        BSTD<Coche> bst = new BSTD<>();
        BBSTD<Coche> bbst = new BBSTD<>();

        // ===== INSERCIÓN DE COCHES CON DUPLICADOS =====
        IndexedList<Coche> coches = new IndexedList<>();

        coches.append(new Coche(5,"AA1"));
        coches.append(new Coche(30,"BB2"));
        coches.append(new Coche(70,"CC3"));
        coches.append(new Coche(10,"DD4"));
        coches.append(new Coche(40,"EE5"));
        coches.append(new Coche(60,"FF6"));
        coches.append(new Coche(80,"GG7"));
        coches.append(new Coche(30,"HH8"));
        coches.append(new Coche(20,"II9"));
        coches.append(new Coche(70,"JJ10"));
        coches.append(new Coche(10,"KK11"));
        coches.append(new Coche(25,"LL12"));
        coches.append(new Coche(35,"MM13"));
        coches.append(new Coche(45,"NN14"));
        coches.append(new Coche(55,"OO15"));
        coches.append(new Coche(65,"PP16"));
        coches.append(new Coche(10,"QQ17"));
        coches.append(new Coche(60,"RR18"));
        coches.append(new Coche(75,"SS19"));
        coches.append(new Coche(70,"TT20"));
        coches.append(new Coche(80,"UU21"));
        int i=0;
        while(i<coches.len()){
            bst.add(coches.get(i));
            bbst.add(coches.get(i));
            i=i+1;
        }

        // ===== BSTD =====
        System.out.println("===== BSTD =====");

        System.out.print("InOrder BSTD: ");
        IndexedList<Coche> in = bst.getInOrder();
        in.print();

        System.out.println("Altura BSTD: " + bst.getHeight());

        // ===== DUPLICADOS (VALOR 10) =====
        System.out.println("Duplicados valor 10 BSTD:");

        BinaryNodeDuplicate<Coche> node = bst.search(new Coche(10,"A1"));

        if(node != null){
            IndexedList<Coche> d1 = node.getAllData();
            d1.print();
            System.out.println("Cantidad: " + d1.len());
        }
        else {
            System.out.println("Nodo no encontrado");
        }

        // ===== CAMINO =====
        IndexedList<Coche> path = bst.getPath(new Coche(20,"E5"));
        System.out.print("Camino a 20 BSTD: ");
        path.print();

        // ===== BBSTD =====
        System.out.println("\n===== BBSTD =====");

        System.out.print("InOrder BBSTD: ");
        IndexedList<Coche> in2 = bbst.getInOrder();
        in2.print();

        System.out.println("Altura BBSTD: " + bbst.getHeight());

        // ===== DUPLICADOS (VALOR 10) =====
        System.out.println("Duplicados valor 10 BBSTD:");

        BinaryNodeDuplicate<Coche> node2 = bbst.search(new Coche(10,"A1"));

        if(node2 != null){
            IndexedList<Coche> d2 = node2.getAllData();
            d2.print();
            System.out.println("Cantidad: " + d2.len());
        }
        else {
            System.out.println("Nodo no encontrado");
        }

        // ===== CAMINO =====
        IndexedList<Coche> path2 = bbst.getPath(new Coche(20,"E5"));
        System.out.print("Camino a 20 BBSTD: ");
        path2.print();

        // ===== COMPARACIÓN FINAL =====
        System.out.println("\n===== COMPARACIÓN =====");

        System.out.println("Altura BSTD: " + bst.getHeight());
        System.out.println("Altura BBSTD: " + bbst.getHeight());
    }
}