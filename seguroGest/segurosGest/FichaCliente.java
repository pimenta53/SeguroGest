
/**
 * Write a description of class FichaCliente here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.Collections;
import java.io.*;
import java.util.*;
import static java.lang.System.out;

public class FichaCliente implements Serializable
{
    //vaiaveis de instancia
    private Titular cliente;
    private TreeMap<String,Seguro> seguros;
    private double premioTotal;
    
    //construtores
    public FichaCliente() {
        cliente = new Titular();
        premioTotal = 0;
        seguros = new TreeMap<String,Seguro>();
    }
    
    public FichaCliente(Titular cliente, double premioTotal, TreeMap<String,Seguro> seguros) {
        this.cliente = new Titular(cliente);
        this.premioTotal = premioTotal;
        this.seguros = seguros;
    }
    
    public FichaCliente(FichaCliente fc) {
        cliente = fc.getTitular();
        premioTotal = fc.getPremio();
        seguros = new TreeMap<String,Seguro> (fc.copiaSeg());
    }
    
    //metodos de instancia
    
    /**
    * Método que devolve o titular
    */
   
    public Titular getTitular() {return cliente.clone();}
    
    /**
    * Método que devolve prémio total
    */
    
    public double getPremio() {return premioTotal;}
    
    /**
    * Método que devolve uma lista de seguros
    */
    
    public TreeSet<Seguro> getSegs() {
        TreeSet<Seguro> aux = new TreeSet<Seguro>();
        for (Seguro sg : seguros.values()) aux.add(sg);
        return aux;
    }
    
    /**
    * Método que devolve uma códiga da ficha de cliente
    */
        
    public FichaCliente clone() {return new FichaCliente(this);}
    

    
    /**
    * Método modifica o titular
    * 
    * @param titular a atribuir ao seguro
    */
    
    public void setTitular(Titular cliente){this.cliente=cliente;}
    public void setPremio(double premio){premioTotal=premio;}
    
    /**
    * Método que compara fichas de cliente
    * 
    * @param Ficha de cliente a comparar
    */
    
    public boolean equals(FichaCliente fc){
        if(fc!=null) return(premioTotal == fc.getPremio()
        && cliente.equals(fc.getTitular())); 
        else return false;
    }
    
   public int nVezesSeguro(Seguro seg){
       Collection<? extends Seguro> segs = seguros.values();
       int total=0;
       for(Seguro s: segs){
           if(s.equals(seg)) total++;
        }
        return total;
    }

    /**
     * Metodo que devolve o valor a pagar pelo seguro mais caro de um cliente
     */
    public double seguroMaisCaro(){
        Seguro seg=null;
        double maior=0,premio;
        Collection<? extends Seguro> segs= seguros.values();
        for(Seguro s: segs){
            premio=s.premio();
            if(premio>maior) maior=premio;
        }
        return maior;
    }
    
    
    /**
     * Metodo que devolve o pagamento actual de um seguro
     */
    public double precoSeguro(String codS){
       Seguro s=null;
        double premio=0;
       s=seguros.get(codS);
        if(s!=null) premio=s.premio();
   return premio;
   }
            
    
   /**
    * Metodo responsavel por adicionar mais um seguro a um cliente
    */
    public void adicionaSeguro(String cod, Seguro seg) {
        seguros.put(cod,seg.clone());
    }
    
    
   /**
    * Metodo responsavel por apagar um seguro a um cliente
    */
    public void removeSeguro(String cod){
        seguros.remove(cod);
    }
    /**
     * Retorna o seguro de um cliente correspondete ao codigo passado como argumento
     */
   public Seguro getSeguro(String cod){
       Seguro s=seguros.get(cod);
       if(s!=null) return s.clone();
       else return null;
    }
    /**
     * Verfica se o cliente é possuidor do determinado seguro
     */
    public boolean existeSeguro(String cod){
        return seguros.containsKey(cod);
    }
    
    /**
     * Total de seguros que possui o cliente
     */
    public int numSeguros(){
        return seguros.size();
    }
    
    public int numOcorrencias() {
        int ocorre = 0;
        for (Seguro s : seguros.values()) ocorre += s.getNumOcorrencias();
        return ocorre;
    }
    
    /**
     * Ficah de cliente para uma String
     */
    public String toString(){
        StringBuilder s = new StringBuilder("----------- Cliente --------\n");
        s.append(cliente.toString());
        s.append("\nPremio total: ");s.append(premioTotal);
        s.append("\n---------Seguros que possui----------\n");
        for(Seguro sg : seguros.values()){
            s.append(sg.toString());
        }
        return s.toString();
    }
    
    /**
     * Total de seguros com pagamento em atraso
     */
   public int pagAtraso(){

       int total=0;
       for(Seguro sg : seguros.values()){
        if(sg.getEmDivida()) total++;
        }
       return total;  
    }
    /**
     * Set dos seguros em atraso
     */
   public Set<Seguro> segsAtraso(){
       Collection<Seguro> segs = seguros.values();
       TreeSet<Seguro> segsF = new TreeSet<Seguro>();
       for(Seguro sg : segs){
        if(sg.getEmDivida()) segsF.add(sg.clone());
        }
       return segsF;  
    
    }
    
    
    /**
     * Total a pagar pelo conjunto de todos os seguros
     */
    public double totalPagar(){
        int total = 0;
        for (Seguro s : seguros.values()) {
            total += s.premio();
        }
    return total;
    }
        
    /**
     * Map com a copia dos seguros de um cliente
     */
   public Map<String,Seguro> copiaSeg(){
       Map<String,Seguro> segs = new TreeMap<String,Seguro > ();
       for(Seguro s : seguros.values()){
           segs.put(s.getCodigo(),s.clone());
        }
    return segs;
    }
       
}
