package Practicas;

public class Coche implements Comparable<Coche>{

    private int valor;
    private String matricula;

    public Coche(int valor,String matricula){
        this.valor=valor;
        this.matricula=matricula;
    }

    public int getValor(){
        return valor;
    }

    public String getMatricula(){
        return matricula;
    }

    @Override
    public int compareTo(Coche o){
        return Integer.compare(this.valor,o.valor);
    }


    //Muestra solo el primer coche cuando se imprimen en una lista, aunque lo que importa es el valor
    @Override
    public String toString(){
        return matricula + "(" + valor + ")";
    }
}