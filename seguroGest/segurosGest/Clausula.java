
/**
 * Write a description of class Clausulas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;

public class Clausula implements Serializable
{
    /**
     * Le de ficheiro as clausulas
     */
    public static ArrayList<Clausula> readFile(String fileName) {
       ArrayList<Clausula> clausulas = new ArrayList<Clausula>();
       try {
           FileReader reader = new FileReader(fileName);
           BufferedReader in = new BufferedReader(reader);
           String codigo;
           String nome;
           String descricao;
           String string;
           double agravamento;
           while ((string = in.readLine()) != null) {
               codigo = string;
               nome = in.readLine();
               descricao = in.readLine();
               agravamento = Double.parseDouble(in.readLine());               
               clausulas.add(new Clausula(codigo,nome,descricao,agravamento));
            }
            in.close();
        } catch (IOException e) { e.printStackTrace(); }
        return clausulas;
    }

    private String codClausula; //codigo da clausula
    private String nomeClausula; //nome da clausula
    private String descricao; //descricao da clausula
    private double agravamento; //agravamento da clausula
    
    public Clausula() {
        codClausula="";
        nomeClausula="";
        descricao="";
        agravamento=0;
    }
    
    public Clausula(String codC,String nomeC,String des,double agrv) {
        codClausula=codC;
        nomeClausula=nomeC;
        descricao=des;
        agravamento=agrv;
    }
    
    public Clausula(Clausula cl){
        codClausula=cl.getCod();
        nomeClausula=cl.getNome();
        descricao=cl.getDescricao();
        agravamento=cl.getAgravamento();
    }
    
    /**
     * Devolve o codigo da clausula
     * 
     */
    public String getCod() { return codClausula; }
    
    /**
     * Devolve o nome do clausula
     * 
     */
    public String getNome() { return nomeClausula; }
    
    /**
     * Devolve a descricao da clausula
     * 
     */
    public String getDescricao() { return descricao; }
    
    /**
     * Devolve o agravamento da clausula
     * 
     */
    public double getAgravamento() { return agravamento; }
   
    /**
     * Modifica o codigo da clausula
     * 
     * @param codC codigo da clausula
     */
    public void setCod(String codC) {codClausula=codC; }
    
    /**
     * Modifica o nome da clausula
     * 
     * @param nomeC nome da clausula
     */
    public void setNome(String nomeC) { nomeClausula=nomeC; }
    
    
    /**
     * Modifica a descricao da clausula
     * 
     * @param desC descricao da clausula
     */
    public void setDescricao(String desC) {descricao=desC; }
    
    
    /**
     * Modifica o agravamento da clausula
     * 
     * @param agrv agravamento da clausula
     */
    public void setAgravamento(double agv) { agravamento=agv; }
   
    /**
     * Devolve boolean resultante da comparacao de duas clausulas
     * 
     * @param o Objecto com o qual queremos fazer a comparacao
     */
    public boolean equals(Object o) {
        if (o==this) 
            return true;
        if (o==null || o.getClass()!=this.getClass())
            return false;
            Clausula cl = (Clausula) o;
            return(agravamento==cl.getAgravamento() && codClausula.equals(cl.getCod()) 
                && nomeClausula.equals(cl.getNome()) && descricao.equals(cl.getDescricao())); 
    }
    
   /**
     * Devolve reprensentacao do estado do objecto sob a forma de String
     * 
     */    
    public String toString(){
        StringBuilder s=new StringBuilder();
        s.append("\nCodigo: " + codClausula);
        s.append("\nNome: " + nomeClausula);
        s.append("\nDescrição: " + descricao);
        s.append("\nAgravamento: " + agravamento*100 + "%\n");
        return s.toString();
    }
    
    /**
     * Devolve uma copia do objecto
     * 
     */
    public Clausula clone() {
        return new Clausula(this);
    }
}
