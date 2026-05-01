package Descartadas.Carolina.grafos.json;

import Descartadas.Carolina.estructuras_necesarias.ListSE;
import com.google.gson.Gson;

import java.io.FileReader;

public class ArchivosJson {

    //Atributos
    private String ruta = "datos.json"; //ruta del archivo JSON que se va a leer


    //Constructores
    public ArchivosJson(String s) { //constructor necesario para los archivos JSON
    }


    //Métodos
    private class ContenedorTripletas { //clase auxiliar para leer el JSON
        Tripleta[] tripletas; //array de tripletas que vienen del archivo JSON
    }

    public ListSE<Tripleta> cargar() { //carga el JSON y lo convierte en una lista

        ListSE<Tripleta> lista = new ListSE<>(); //lista auxiliar donde se guardan las tripletas

        try { //bloque para comprobar si hay excepciones

            Gson gson = new Gson(); //objeto para leer el JSON

            ContenedorTripletas data = gson.fromJson(new FileReader(ruta), ContenedorTripletas.class); //lee el archivo JSON gracias a la ruta y lo convierte en un objeto

            if (data != null && data.tripletas != null) { //comprueba que hay datos

                for (int i = 0; i < data.tripletas.length; i++) { //recorre el array de tripletas
                    lista.addLast(data.tripletas[i]); //añade cada tripleta a la lista
                }
            }

        } catch (Exception e) { //si ocurre algún error al leer el archivo
            e.printStackTrace(); //imprime el error
        }

        return lista; //devuelve la lista
    }
}