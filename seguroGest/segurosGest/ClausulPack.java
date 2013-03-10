
/**
 * Write a description of class ClausulPacks here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class ClausulPack implements Serializable
{
    //variaveis de class
    private static Map<String,Clausula> clsSegAutoObrig = new TreeMap<String,Clausula>(); //Map das clausulas obrigatorias num seguro automovel
    private static Map<String,Clausula> clsSegAutoOpcion = new TreeMap<String,Clausula>(); //Map das clausulas opcionais num seguro automovel
    
    private static Map<String,Clausula> clsSegMedico = new TreeMap<String,Clausula>();//Map das clausulas disponiveis num seguro medico
    private static Map<String,Clausula> clsSegVida = new TreeMap<String,Clausula>();//Map das clausulas disponiveis num seguro de vida
    
    private static Map<String,Clausula> clsSegCasa = new TreeMap<String,Clausula>();//Map das clausulas disponiveis num seguro de vida
    
    //metodos de class
    /**
     * Devolve uma lista com todos as clausulas obrigatorias para o seguro Automovel
     */
    public static List<Clausula> getClsAutoObrig() {
        ArrayList<Clausula> cls = new ArrayList<Clausula>();
        for(Clausula c: clsSegAutoObrig.values())
            cls.add(c.clone());
        return cls;
    }
    
    /**
     * Adiciona à lista de clausulas obrigatorias do seguro automovel um conjunto de clausulas
     * 
     * @param listCls Lista com as clausulas a adicionar
     */
    public static void setClsAutoObrig(List<Clausula> listCls) {
        for(Clausula cl: listCls)
            clsSegAutoObrig.put(cl.getCod(),cl.clone());
    }
    
    
    /**
     * Adiciona uma clausula à lista de clausulas obrigatorias do seguro automovel
     * 
     * @param cl Clausula a adicionar. E uma instancia de Clausula
     */
    public static void addClsAutoObrig(Clausula cl) {
        clsSegAutoObrig.put(cl.getCod(),cl.clone());
    }
        
     /**
     * Remove uma clausula das clausulas Auto obrigatorias
     * 
     * @param cod e o codigo da causula a remover
     */
    public static void removeClsAutoObrig(String cod) {
        clsSegAutoObrig.remove(cod);
    }
     
    /**
     * Devolve uma lista com todos as clausulas opcionais para o seguro Automovel
     */
    public static List<Clausula> getClsAutoOpcion() {
        ArrayList<Clausula> cls = new ArrayList<Clausula>();
        for(Clausula c: clsSegAutoOpcion.values())
            cls.add(c.clone());
        return cls;
    }
     
    /**
     * Devolve uma clausula opcionai do seguro Automovel
     * 
     * @param cod e o codigo da causula a devolver
     */
    public static Clausula getClAutoOb(String cod) {
        return clsSegAutoOpcion.get(cod).clone();
    }
    
    /**
     * Adiciona à lista de clausulas obrigatorias do seguro automovel um conjunto de clausulas
     * 
     * @param listCls Lista com as clausulas a adicionar
     */
    public static void setClsAutoOpcion(List<Clausula> listCls) {
        for(Clausula cl: listCls)
            clsSegAutoOpcion.put(cl.getCod(),cl.clone());
    }
    
    /**
     * Adiciona uma clausula à lista de clausulas opcionais do seguro automovel
     * 
     * @param cl Clausula a adicionar. E uma instancia de Clausula
     */
    public static void addClsAutoOpcion(Clausula cl) {
        clsSegAutoOpcion.put(cl.getCod(),cl.clone());
    }
        
    /**
     * Remove uma clausula das clausulas Auto opcionais
     * 
     * @param cod e o codigo da causula a remover
     */
    public static void removeClsAutoOpcion(String cod) {
        clsSegAutoOpcion.remove(cod);
    }
    
    /**
     * Verifica se existe clausula opcional do seguro auto com o codigo fornecido
     * 
     * @param cod e o codigo da causula a pesquisar
     */
    public static boolean existeClsAuto(String cod) {
        return clsSegAutoOpcion.containsKey(cod);
    }
    
    /*
     *Calusulas seg medico
     */
    /**
     * Devolve uma lista com todos as clausulas para o seguro Medico
     */
    public static List<Clausula> getClsSegMedico() {
        ArrayList<Clausula> cls = new ArrayList<Clausula>();
        for(Clausula c: clsSegMedico.values())
            cls.add(c.clone());
        return cls;
    }
    
    /**
     * Adiciona à lista de clausulas do seguro Medico um conjunto de clausulas
     * 
     * @param listCls Lista com as clausulas a adicionar
     */
    public static void setClsSegMedico(List<Clausula> listCls) {
        for(Clausula cl: listCls)
            clsSegMedico.put(cl.getCod(),cl.clone());
    }
    
    /**
     * Adiciona uma clausula à lista de clausulas do seguro Medico
     * 
     * @param cl Clausula a adicionar. E uma instancia de Clausula
     */
    public static void addClsSegMedico(Clausula cl) {
        clsSegMedico.put(cl.getCod(),cl.clone());
    }
        
    /**
     * Remove uma clausula das clausulas Medico
     * 
     * @param cod e o codigo da causula a remover
     */
    public static void removeClsSegMedico(String cod) {
        clsSegMedico.remove(cod);
    }
    
    /**
     * Verifica se existe clausula do seguro Medico com o codigo fornecido
     * 
     * @param cod e o codigo da causula a pesquisar
     */    
    public static boolean existeClsMedico(String cod) {
        return clsSegMedico.containsKey(cod);
    }
    
    /*
     *Calusulas seg Vida 
     */
    /**
     * Devolve uma lista com todos as clausulas para o seguro Vida
     */
    public static List<Clausula> getClsSegVida() {
        ArrayList<Clausula> cls = new ArrayList<Clausula>();
        for(Clausula c: clsSegVida.values())
            cls.add(c.clone());
        return cls;
    }
    
    /**
     * Adiciona à lista de clausulas obrigatorias do seguro Vida um conjunto de clausulas
     * 
     * @param listCls Lista com as clausulas a adicionar
     */
    public static void setClsSegVida(List<Clausula> listCls) {
        for(Clausula cl: listCls)
            clsSegVida.put(cl.getCod(),cl.clone());
    }
    
    /**
     * Adiciona uma clausula à lista de clausulas obrigatorias do seguro automovel
     * 
     * @param cl Clausula a adicionar. E uma instancia de Clausula
     */
    public static void addClsSegVida(Clausula cl) {
        clsSegVida.put(cl.getCod(),cl.clone());
    }
        
    /**
     * Remove uma clausula das clausulas Vida
     * 
     * @param cod e o codigo da causula a remover
     */
    public static void removeClsSegVida(String cod) {
        clsSegVida.remove(cod);
    }
    
    /**
     * Verifica se existe clausula do seguro Vida com o codigo fornecido
     * 
     * @param cod e o codigo da causula a pesquisar
     */        
    public static boolean existeClsVida(String cod) {
        return clsSegVida.containsKey(cod);
    }
    
    /*
     *Calusulas seg Casa
     */
    /**
     * Devolve uma lista com todos as clausulas para o seguro Casa
     */
    public static List<Clausula> getClsSegCasa() {
        ArrayList<Clausula> cls = new ArrayList<Clausula>();
        for(Clausula c: clsSegCasa.values())
            cls.add(c.clone());
        return cls;
    }
    
    /**
     * Adiciona à lista de clausulas do seguro Casa um conjunto de clausulas
     * 
     * @param listCls Lista com as clausulas a adicionar
     */
    public static void setClsSegCasa(List<Clausula> listCls) {
        for(Clausula cl: listCls)
            clsSegCasa.put(cl.getCod(),cl.clone());
    }
    
    /**
     * Adiciona uma clausula à lista de clausulas do seguro Casa
     * 
     * @param cl Clausula a adicionar. E uma instancia de Clausula
     */
    public static void addClsSegCasa(Clausula cl) {
        clsSegCasa.put(cl.getCod(),cl.clone());
    }
        
    /**
     * Remove uma clausula das clausulas Vida
     * 
     * @param cod e o codigo da causula a remover
     */
    public static void removeClsSegCasa(String cod) {
        clsSegCasa.remove(cod);
    }
    
    /**
     * Verifica se existe clausula do seguro Vida com o codigo fornecido
     * 
     * @param cod e o codigo da causula a pesquisar
     */   
    public static boolean existeClsCasa(String cod) {
        return clsSegCasa.containsKey(cod);
    }
    
    //vaiaveis de instancia
    private String codigo; //codigo da clausula
    private String descricao; //descricao do pack de clausulas
    private Set<Clausula> pack; //pack que contem as clausulas
    
    //Construtores
    public ClausulPack() {
        codigo = "a";
        descricao = "b";
        pack = new TreeSet<Clausula>(new ClaComparator());
    }
    
    public ClausulPack(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
        pack = new TreeSet<Clausula> (new ClaComparator());
    }

    public ClausulPack(String codigo, String descricao, List<Clausula> lstClausulas) {
        this.codigo = codigo;
        this.descricao = descricao;
        pack = new TreeSet<Clausula> (new ClaComparator());
        for(Clausula c: lstClausulas)
            pack.add(c.clone());
    }
    
    public ClausulPack(ClausulPack cp) {
        codigo = cp.getCodigo();
        descricao = cp.getDescricao();
        pack = new TreeSet<Clausula> (new ClaComparator());
        for(Clausula c: cp.getClausulas())
            pack.add(c.clone());
    }
    
    //metodos de instancia
    /**
     * Devolve o codigo do pack
     * 
     */
    public String getCodigo() { return codigo; }
    
    /**
     * Modifica a codigo do pack
     * 
     * @param cod Codigo a atribuir ao pack
     */
    public void setCodigo(String cod) { codigo = cod; }
    
    /**
     * Devolve a descricao do pack
     * 
     */    
    public String getDescricao() { return descricao; }
    
    /**
     * Modifica a descricao do pack
     * 
     * @param desc Descricao do pack
     */    
    public void setDescricao(String desc) { descricao = desc; }
       
    /**
     * Devolve uma lista de clausulas que constam no pack
     */
    public List<Clausula> getClausulas() {
        ArrayList<Clausula> res = new ArrayList<Clausula>();
        Iterator<Clausula> it = pack.iterator();
        while(it.hasNext()) 
            res.add(it.next().clone());
        return res;
    }
    
    /**
     * Adiciona ao set de clausulas uma lista de clausulas
     * 
     * @param list Lista com as clausulas a adicionar ao Set
     */
    public void setClausulas(List<Clausula> list) {
        for(Clausula c: list)
            pack.add(c.clone());
    }
    
    /**
     * Cria um pack auto com os codigos fornecidos como parametro
     * 
     * @param codes Codigos que identificam as clausulas a adicionar
     */
    public void criarPackAuto(List<String> codes) {
        Clausula cl;
        for(Clausula c: ClausulPack.getClsAutoObrig())
            pack.add(c.clone());
        for(String cod: codes) {
            cl = clsSegAutoOpcion.get(cod);
            if (cl!=null) pack.add(cl.clone());
        }
    }
    
    /**
     * Cria um pack Medico com os codigos fornecidos como parametro
     * 
     * @param codes Codigos que identificam as clausulas a adicionar
     */
    public void criarPackMedico(List<String> codes) {
        for(String cod: codes)
            pack.add(clsSegMedico.get(cod).clone());
    }
        
    /**
     * Cria um pack Vida com os codigos fornecidos como parametro
     * 
     * @param codes Codigos que identificam as clausulas a adicionar
     */
    public void criarPackVida(List<String> codes) {
        for(String cod: codes)
            pack.add(clsSegVida.get(cod).clone());
    }
     
    /**
     * Cria um pack Casa com os codigos fornecidos como parametro
     * 
     * @param codes Codigos que identificam as clausulas a adicionar
     */
    public void criarPackCasa(List<String> codes) {
        for(String cod: codes)
            pack.add(clsSegCasa.get(cod).clone());
    }
    
    /**
     * Devolve boolean resultante da comparacao de dois packs de clausulas
     * 
     * @param o Objecto com o qual queremos fazer a comparacao
     */
    public boolean equals(Object o) {
        if (o==this)
            return true;
        if (o==null || o.getClass()!=this.getClass())
            return false;
        ClausulPack cp = (ClausulPack) o;
        return this.getCodigo().equals(cp.getCodigo());
   }
    
   /**
     * Devolve reprensentacao do estado do objecto sob a forma de String
     * 
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("\n-------- Pack Clausulas --------\n");
        sb.append("Codigo: " + codigo + "\n");
        sb.append("Descricao: " + descricao + "\n\n");
        sb.append("----------- Clausulas ----------\n");
        Iterator<Clausula> it = pack.iterator();
        while(it.hasNext())
            sb.append(it.next().getNome() + "\n");
        return sb.toString();
    }
    
    /**
     * Devolve uma copia do objecto
     * 
     */
    public ClausulPack clone() { return new ClausulPack(this); }
}
