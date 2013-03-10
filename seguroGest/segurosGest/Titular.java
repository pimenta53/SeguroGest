
/**
 * Write a description of class Titular here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.*;

public class Titular implements Serializable
{
    // variaveis de instancia
    private String nome;
    private int idade;
    private String morada;
    private String nif;
    private String bi;

    /**
     * Constructor for objects of class Titular
     */
    public Titular() {
        idade = 0;
        nome="";
        morada="";
        nif="";
        bi="";
    }
    
    public Titular(int idade,String nome,String morada,String nif,String bi){
        this.idade=idade;
        this.nome=nome;
        this.morada=morada;
        this.nif=nif;
        this.bi=bi;
    }
    /**
     * Construtor copia do titular
     */
    public Titular(Titular tit){
        idade=tit.getIdade();
        nome=tit.getNome();
        morada=tit.getMorada();
        nif=tit.getNif();
        bi=tit.getBi();
    }
    
   //metodos de instancia
   public int getIdade() { return idade; }
   public String getNome(){return nome;}
   public String getMorada(){return morada;}
   public String getNif(){return nif;}
   public String getBi(){return bi;}
   
   
   public void setIdade(int idade){this.idade =idade;}
   public void setNome(String nome){this.nome =nome;}
   public void setMorada(String morada){this.morada =morada;}
   public void setNif(String nif){this.nif =nif;}
   public void setBi(String bi){this.bi =bi;}
   
   
   public boolean equals(Titular tit){
       if(tit!=null){
           return(this.idade==tit.getIdade() &&
           this.nome.equals(tit.getNome()) && 
           this.morada.equals(tit.getMorada()) && 
           this.nif.equals(tit.getNif()) &&
           this.bi.equals(tit.getBi()));
        }
       else return false;
    }
    
    public Titular clone(){
        return new Titular(this);
    }
    
    public String toString(){
        StringBuilder s=new StringBuilder();
        s.append("Nome:");s.append(nome);
        s.append("\nIdade:");s.append(idade);
        s.append("\nMorada:");s.append(morada);
        s.append("\nNif:");s.append(nif);
        s.append("\nBi:");s.append(bi);
        return s.toString();
    }
}

