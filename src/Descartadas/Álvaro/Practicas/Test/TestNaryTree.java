package Practicas.Test;


import EstructurasP1.IndexedList;
import Árboles.ArbolNario.NaryTree;

public class TestNaryTree {

    public static void main(String[] args) {

        NaryTree<String> tree = new NaryTree<>();

        //CREACIÓN DE CARPETAS

        tree.add("Principal");

        tree.add("Documentos", "Principal");
        tree.add("Descargas", "Principal");
        tree.add("Imagenes", "Principal");

        tree.add("Trabajo", "Documentos");
        tree.add("Personal", "Documentos");

        tree.add("Java", "Trabajo");
        tree.add("Python", "Trabajo");

        tree.add("Vacaciones", "Imagenes");

        //ARCHIVOS
        tree.add("cv.txt", "Trabajo");
        tree.add("tareas.txt", "Trabajo");
        tree.add("notas.txt", "Personal");
        tree.add("foto1.txt", "Vacaciones");
        tree.add("foto2.txt", "Vacaciones");
        tree.add("descarga1.txt", "Descargas");

        //IMPRIMIR ÁRBOL

        System.out.println("\n=== ESTRUCTURA DEL ÁRBOL ===");
        System.out.println(tree.print());

        //PATH A UN NODO

        System.out.println("\n=== PATH A 'Python' ===");
        IndexedList<String> path = tree.getPath("Python");
        path.print();


        //NUEVO: SEARCH CON RUTA

        System.out.println("\n=== SEARCH WITH PATH 'Python' ===");
        IndexedList<String> searchPath = tree.searchWithPath("Python");
        searchPath.print();


        //ALTURA

        System.out.println("\n=== ALTURA ===");
        System.out.println(tree.getHeight());

        //ELIMINAR SUBÁRBOL

        System.out.println("\n=== ELIMINANDO 'Trabajo' ===");
        tree.remove("Trabajo");

        System.out.println("\n=== ÁRBOL DESPUÉS DE ELIMINAR ===");
        System.out.println(tree.print());

        //PATH FALLIDO

        System.out.println("\n=== PATH A 'Python' (ya eliminado) ===");
        IndexedList<String> pathFail = tree.getPath("Python");
        pathFail.print();

        //ELIMINAR HOJA

        System.out.println("\n=== ELIMINANDO 'descarga1.txt' ===");
        tree.remove("descarga1.txt");

        System.out.println("\n=== ÁRBOL FINAL ===");
        System.out.println(tree.print());
    }
}
