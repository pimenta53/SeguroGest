
/**
 * Write a description of class Histórico here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Histórico implements Serializable
{
    //variavel de instancia
    Calendar calendar = new GregorianCalendar();
    private TreeMap<Integer,Integer> historico = new TreeMap<Integer,Integer>();
    private Integer anoActual = calendar.get(Calendar.YEAR);
    
    //construtores
    
    public void Historico(){
        historico = new TreeMap<Integer,Integer>();
    }
    
    public void NumClientes(Integer ano, Integer n_cliente){
        historico = new TreeMap<Integer, Integer>();
        historico.put(ano,n_cliente);
    }
    
    
    //metodos de instancia
    
    public int getHistorico(Integer ano){
        if (historico.containsKey(ano)==false) return 0;
        return historico.get(ano);
    }
    
    public void putHistorico(Integer ano, Integer num){
        historico.put(ano,num);
    }
    
    public void somaHistorico(){
        if (historico.containsKey(anoActual)==false) {
            if (historico.containsKey(anoActual-1)==true) historico.put(anoActual,(historico.get(anoActual-1)+1));
             else historico.put(anoActual,1);
            return;
        }
        historico.put(anoActual,getHistorico(anoActual)+1);        
    }
    
    public void subtraiHistorico(){
        if (historico.containsKey(anoActual)==false) {
            if (historico.containsKey(anoActual-1)==true) historico.put(anoActual,(historico.get(anoActual-1)-1));
            return;
        }
        historico.put(anoActual,getHistorico(anoActual)-1);        
    }
    
    public int diferenca(Integer inicio, Integer fim){
        if ((historico.containsKey(inicio)==false) || (historico.containsKey(fim)==false)) return 0;
        return (getHistorico(fim) - getHistorico(inicio));
    }
    
    public int diferenca(){
        if ((historico.containsKey(anoActual-1)==false) || (historico.containsKey(anoActual)==false)) return 0;
        return (historico.get(anoActual) - historico.get(anoActual-1));
    }
    
}
