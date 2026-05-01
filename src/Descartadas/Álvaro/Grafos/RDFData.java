package Grafos;

public class RDFData {

    private String[] tipos;
    private Tripleta[] tripletas;

    public String[] getTipos(){
        return tipos;
    }

    public Tripleta[] getTripletas(){
        return tripletas;
    }

    public void setTripletas(Tripleta[] tripletas) {
        this.tripletas = tripletas;
    }
}