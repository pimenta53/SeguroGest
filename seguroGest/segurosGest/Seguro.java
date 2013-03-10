
/**
 * Abstract class Seguro - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;
import java.io.*;

public abstract class Seguro implements Serializable
{    
    //variaveis de class
    private static double agravOcorrencia; //agravamento que cada ocorrencia produz no premio final
    
    //metodos de class
    public static double getAgravamento() { return agravOcorrencia; }
    
    public static void setAgravamento(double agravamento) { agravOcorrencia = agravamento; }
    
    
    //variaveis de instancia
    private String codigo; //codigo do seguro
    private double premioBase; // premio base
    private double desconto; // deconto por não haver ocurrencias
    private boolean emDivida; // indica se esta pago ou nao
    private String modoPagamento; // indica o modo de pagamento
    private int numOcorrencias; //numero de ocorrencias registadas
    private GregorianCalendar dataReg; //data de registo
    private ClausulPack clausulas; //pack de clausulas subscritas
 
    
    //construtores
    public Seguro() {
        codigo = "";
        premioBase = 0;
        desconto = 0;
        emDivida = false;
        modoPagamento = "";
        numOcorrencias = 0;
        dataReg = new GregorianCalendar();
        clausulas = new ClausulPack();
    }
    
    public Seguro(String codigo, double premioBase, double desconto, String modoPag, ClausulPack cp) {
        this.codigo = codigo;
        this.premioBase = premioBase;
        this.desconto = desconto;
        emDivida = false;
        modoPagamento = modoPag;
        numOcorrencias = 0;
        dataReg = new GregorianCalendar();
        clausulas = cp.clone();
    }
        
    
    // Metodos de Instencia
    /**
    * Devove o codigo do seguro.
    * 
    */
    public String getCodigo() {return codigo;}
    
    
    /**
    * Atribui codigo ao seguro
    * 
    * @param codigo codigo a atrbuir ao seguro
    */
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    /**
    * Devove o premio Base do seguro.
    * 
    */
    public double getPremioBase() { return premioBase; }
    
    /**
    * Muda o premio Base.
    * 
    * @param premio novo premio base (em euros) do seguro
    */
    public void setPremioBase(double premio) { premioBase = premio; } 
    
    /**
    * Devove a desconto do seguro.
    * 
    */
    public double getDesconto() { return desconto; }
    
    /**
    * Muda desconto do seguro.
    * 
    * @param valorDesconto novo valor de desconto (em euros)
    */
    public void setDesconto(double valorDesconto) { desconto = valorDesconto; } 
    
    /**
    * Devolve estado do pagamento.
    * 
    */
    public boolean getEmDivida() { return emDivida; }
    
    
    /**
    * atribui um estado ao pagamento
    * 
    * @param pago define se ja esta pago ou deve
    */
    public void setEmDivida(boolean emDivida) { this.emDivida = emDivida; }
    
    
    /**
    * Devove o modo de pagamento.
    * 
    */
    public String getModoPagamento() {return modoPagamento;}
    
    
    /**
    * Atribui um modo de pagamento
    * 
    * @param modoPag
    */
    public void setModoPagamento(String modoPag) { modoPagamento = modoPag; }
    
    
    /**
    * Devove o numero de ocurrencias registadas
    * 
    */
    public int getNumOcorrencias() { return numOcorrencias; }
    
    
    /**
    * Atribui um numero de ocurrencias
    * 
    * @param n numero de ocurrencias registadas
    */
    public void setNumOcorrencias(int n) { numOcorrencias = n; }
    
    
    /**
    * Devove a data de registo.
    * 
    */
    public GregorianCalendar getDataReg() { 
        return new GregorianCalendar(dataReg.get(dataReg.YEAR),dataReg.get(dataReg.MONTH),dataReg.get(dataReg.DAY_OF_MONTH));
    }
    
    /**
    * Modifica a data de registo.
    * 
    * @param data GregorianCalendar com a data a modificar
    */
    public void setDataReg(GregorianCalendar data) { 
        dataReg.set(GregorianCalendar.YEAR, data.get(data.YEAR));
        dataReg.set(GregorianCalendar.YEAR, data.get(data.MONTH));
        dataReg.set(GregorianCalendar.YEAR, data.get(data.DAY_OF_MONTH));
    }
    
   /**
    * Devolce pacote de clausulas subscritas
    * 
    */
    public ClausulPack getClausulas() { return clausulas.clone(); }
   
   /**
    * Actualiza pacote de clausulas
    * 
    * @param cp Pacote de clausulas 
    */
    public void setClausulas(ClausulPack cp) { clausulas = cp.clone(); }
     
    /**
    * Adiciona ocurrencia
    * 
    */
    public void addOcurrencia() { 
        numOcorrencias++;
    }  
    
    /**
    * Remover ocurrencia
    * 
    */
    public void removeOcurrencia() { 
        numOcorrencias--;
    }
   
    /**
    * Calcula o premio a pagar a seguradora
    * 
    */
    public double premio() {
        double valAditamentos = 0;
        for(Clausula c: clausulas.getClausulas())
            valAditamentos += c.getAgravamento() * this.getPremioBase();
        if (numOcorrencias == 0) 
            return premioBase + valAditamentos - desconto;
        else 
            return premioBase + valAditamentos + (numOcorrencias * (premioBase * agravOcorrencia)) ;
    } 
   
    /**
    * Compara dois seguros.
    * 
    * @param o seguro com o qual é feita a comparação
    */
    public boolean equals(Object o) {
        if (o==this)
            return true;
        if (o==null || o.getClass()!=this.getClass())
            return false;
        Seguro seg = (Seguro) o;
        return this.codigo.equals(seg.getCodigo())
            && this.premioBase==seg.getPremioBase()
            && this.emDivida==seg.getEmDivida()
            && this.modoPagamento.equals(seg.getModoPagamento())
            && this.numOcorrencias==seg.getNumOcorrencias()
            && this.dataReg.get(GregorianCalendar.YEAR)==seg.getDataReg().get(GregorianCalendar.YEAR)
            && this.dataReg.get(GregorianCalendar.MONTH)==seg.getDataReg().get(GregorianCalendar.MONTH)
            && this.dataReg.get(GregorianCalendar.DAY_OF_MONTH)==seg.getDataReg().get(GregorianCalendar.DAY_OF_MONTH)
            && clausulas.equals(seg.getClausulas());
    }    
    
   /**
   * Devolve seguro em forma de String.
   */
   public String toString() {
       StringBuilder sb = new StringBuilder("--------------Seguro------------\n");
       sb.append("Codigo do Seguro: " + codigo + "\n");
       sb.append("Premio Base: " + premioBase + "\n");
       sb.append("Esta Devedor: " + emDivida + "\n");
       sb.append("Modo pagamento: " + modoPagamento + "\n");
       sb.append("Numero de ocorrencias: " + numOcorrencias + "\n");
       sb.append("Data de Registo: " + dataReg.get(GregorianCalendar.YEAR) + "/"
                                     + dataReg.get(GregorianCalendar.MONTH) + "/"
                                     + dataReg.get(GregorianCalendar.DAY_OF_MONTH) + "\n");
       sb.append(clausulas.toString());
       return sb.toString();
    }
    
    /**
     * Devolve uma copia do objecto
     */
    public abstract Seguro clone();
}