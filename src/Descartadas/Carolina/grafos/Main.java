package Descartadas.Carolina.grafos;

import Descartadas.Carolina.estructuras_necesarias.ListSE;
import Descartadas.Carolina.grafos.componentes.Grafo;
import Descartadas.Carolina.grafos.componentes.Nodo;
import Descartadas.Carolina.grafos.json.ArchivosJson;
import Descartadas.Carolina.grafos.json.Tripleta;
import Descartadas.Carolina.grafos.metodos.CaminoMinimo;
import Descartadas.Carolina.grafos.metodos.ConsultasNobel;
import Descartadas.Carolina.grafos.metodos.GrafoDisjunto;

public class Main { //main del programa de grafos

    public static void main(String[] args) {

        //cargar JSON
        ArchivosJson loader = new ArchivosJson("datos.json");
        ListSE<Tripleta> datos = loader.cargar();


        //construir grafo
        Grafo g = new Grafo();
        g.cargarDesdeTripletas(datos);


        //mostrar grafo en formato árbol
        System.out.println("----- Representación del grafo -----");
        g.imprimirComoArbol();


        //metodo del camino mínimo
        System.out.println("----- Camino mínimo -----");

        CaminoMinimo cm = new CaminoMinimo(g);

        ListSE<Nodo> camino = cm.obtenerCamino("persona:Albert Einstein", "persona:Marie Curie");

        for (int i = 0; i < camino.getSize(); i++) {
            System.out.print(camino.get(i).nombre);

            if (i < camino.getSize() - 1) System.out.print(" -> ");
        }


        // metodo componentes conexas
        System.out.println("\n\n----- Componentes conexas -----");

        GrafoDisjunto gd = new GrafoDisjunto(g);

        int componentes = gd.contarComponentes();

        System.out.println("Número de componentes: " + componentes);


        // consultas sobre los premios nobel
        System.out.println("\n----- Premios Nobel -----");

        ConsultasNobel cn = new ConsultasNobel(g);

        System.out.println("1.Mismo premio");

        ListSE<String> resultado = cn.fisicosMismaCiudadQueEinstein();

        if (resultado.getSize() == 0) {
            System.out.println("No hay resultados");
        }
        else {
            for (int i = 0; i < resultado.getSize(); i++) {
                System.out.println(resultado.get(i));
            }
        }

        System.out.println("2.Lugares de nacimiento");

        ListSE<String> lugares = cn.lugaresNacimientoNobel();

        for (int i = 0; i < lugares.getSize(); i++) {
            System.out.println(lugares.get(i));
        }
    }
}