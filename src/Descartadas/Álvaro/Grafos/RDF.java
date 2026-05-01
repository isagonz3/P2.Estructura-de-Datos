package Grafos;

import EstructurasP1.IndexedList;
import Nodos.Grafos.EdgeGraph;
import EstructurasP1.GsonUtil;

public class RDF{

    private Graph<String,String> graph;

    public RDF(){
        graph=new Graph<>();
    }

    public Graph<String,String> getGraph(){
        return graph;
    }

    public void addTriplet(String s,String p,String o){
        graph.addNode(s);
        graph.addNode(o);
        graph.addEdge(s,o,p);
    }


    public void addTriplet(Tripleta t){
        String s=t.getS();
        String p=t.getP();
        String o=t.getO();
        graph.addNode(s);
        graph.addNode(o);
        graph.addEdge(s,o,p);
    }

    //Carga un fichero JSON y construye el grafo RDF
    public void loadFromFile(String ruta){
        RDFData data= GsonUtil.cargarObjetoDesdeArchivo(ruta,RDFData.class);
        if(data!=null){
            Tripleta[] tripletas=data.getTripletas();
            int i=0;
            while(i<tripletas.length){
                Tripleta t=tripletas[i];
                addTriplet(t);
                i=i+1;
            }
        }
    }

    //Devuelve los objetos relacionados con un sujeto en el RDF
    public IndexedList<String> getObjects(String subject){
        IndexedList<String> result=new IndexedList<>();
        //Obtenemos todas las aristas que salen del sujeto
        IndexedList<EdgeGraph<String,String>> edges=graph.edgesFrom(subject);
        int i=0;
        //Recorremos las aristas salientes
        while(i<edges.len()){
            String obj=edges.get(i).getEnd().getData();
            if(result.contains(obj)==false){
                //El objeto es el destino de la arista
                result.append(obj);
            }
            i=i+1;
        }
        return result;
    }

    //Devuelve los sujetos que apuntan a un objeto
    public IndexedList<String> getSubjects(String object){
        IndexedList<String> result=new IndexedList<>();
        IndexedList<EdgeGraph<String,String>> edges=graph.edgesTo(object);
        int i=0;
        while(i<edges.len()){
            String subj=edges.get(i).getStart().getData();
            if(result.contains(subj)==false){
                result.append(subj);
            }
            i=i+1;
        }
        return result;
    }


    //Devuelve objetos filtrando por predicado
    public IndexedList<String> getObjectsByPredicate(String subject,String predicate){
        IndexedList<String> result=new IndexedList<>();
        IndexedList<EdgeGraph<String,String>> edges=graph.edgesFrom(subject);
        int i=0;
        while(i<edges.len()){
            EdgeGraph<String,String> edge=edges.get(i);
            if(edge.getData().equals(predicate)==true){
                result.append(edge.getEnd().getData());
            }
            i=i+1;
        }
        return result;
    }

    //Devuelve nodos que comparten el mismo objeto para un mismo predicado
    public IndexedList<String> getNodesWithSamePredicateAndObject(String subject,String predicate){
        IndexedList<String> result=new IndexedList<>();
        //Objetos del sujeto original con ese predicado
        IndexedList<String> objects=getObjectsByPredicate(subject,predicate);
        if(objects.len()>0){
            int o=0;
            while(o<objects.len()){
                String aux=objects.get(o);
                int i=0;
                while(i<graph.getNodes().len()){
                    String node=graph.getNodes().get(i).getData();
                    //Evitamos comparar el propio sujeto
                    if(node.equals(subject)==false){
                        //Buscamos coincidencias
                        IndexedList<String> check=getObjectsByPredicate(node,predicate);
                        if(check.contains(aux)==true){
                            result.append(node);
                        }
                    }
                    i=i+1;
                }
                o=o+1;
            }
        }
        return result;
    }

    //Devuelve los sujetos que tiene un resultado
    public IndexedList<String> getSubjectsByPredicate(String predicate){
        IndexedList<String> result=new IndexedList<>();
        int i=0;
        while(i<graph.getEdges().len()){
            EdgeGraph<String,String> e=graph.getEdges().get(i);
            if(e.getData().equals(predicate)){
                result.append(e.getStart().getData());
            }
            i++;
        }
        return result;
    }

    public void saveToFile(String ruta){
        RDFData data=new RDFData();
        IndexedList<Tripleta> list=new IndexedList<>();
        int i=0;
        while(i<graph.getNodes().len()){
            String s=graph.getNodes().get(i).getData();
            IndexedList<EdgeGraph<String,String>> edges=graph.edgesFrom(s);
            int j=0;
            while(j<edges.len()){
                EdgeGraph<String,String> e=edges.get(j);
                Tripleta t=new Tripleta();
                t.setS(s);
                t.setP(e.getData());
                t.setO(e.getEnd().getData());
                list.append(t);
                j=j+1;
            }
            i=i+1;
        }
        Tripleta[] array=new Tripleta[list.len()];
        int k=0;
        while(k<list.len()){
            array[k]=list.get(k);
            k=k+1;
        }
        data.setTripletas(array);
        GsonUtil.guardarObjetoEnArchivo(ruta,data);
    }
}