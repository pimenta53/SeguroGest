/**
 * Abstract class SegurVeiculo - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;
import java.io.*;

public class SegVeiculo extends Seguro implements Serializable
{
    //variaveis de class
    /**
     * TreeMap com chave que é o codigo do pack e ClausulPack que contem um pack de clausulas referntes ao seguro Automovel
     */
    private static Map<String,ClausulPack> pack = new TreeMap<String,ClausulPack>();
 
    /**
     * Devolve uma lista com todos os packs disponiveis para o seguro Automovel
     */
    public static List<ClausulPack> getPacks() {
        ArrayList<ClausulPack> res = new ArrayList<ClausulPack>();
        for(ClausulPack cp: pack.values())
            res.add(cp.clone());
        return res;
    }
    
    /**
     * Adiciona à lista de packs um conjunto de packs
     * 
     * @param list Lista com os packs a adicionar ao TreeMap
     */
    public static void setPacksAuto(List<ClausulPack> list) {
        for(ClausulPack cp: list)
            pack.put(cp.getCodigo(),cp.clone());
    }
    
    /**
     * Adiciona uma Pack de clausulas ao TreeMap dos packs
     * 
     * @param cp corresponde a um pack de clausulas. E uma instancia de ClausulPack
     */
    public static void addClsPack(ClausulPack cp) { pack.put(cp.getCodigo(),cp.clone()); }
    
    /**
     * Remove um pack de clausulas
     * 
     * @param cod e o codigo com que o pack e identificado
     */
    public static void removeClsPack(String cod) { pack.remove(cod); }
    
    /**
     * Verifica se um pack ja se encontra no TreeMap
     * 
     * @param cod e o codigo do pack que procuramos
     */    
    public static boolean existePack(String cod) { return pack.containsKey(cod); }

    /**
     * Guarda os packs em ObjectStreams
     * 
     */
    public static void guardarPacks() throws IOException {
        FileOutputStream fos = new FileOutputStream("packsVeiculos.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for(ClausulPack cp: pack.values())
            oos.writeObject(cp);
        oos.flush();
        oos.close();
        fos.flush();
        fos.close();
    }

    /**
     * Carrega os packs do ficheiro definido como "packsVeiculos.dat"
     */
    public static void carregaPacks() {
        ObjectInputStream ois = null;
        Object o = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("packsVeiculos.dat"));
            while ((o=ois.readObject())!=null) {
                if(o.getClass().getSimpleName().equals("ClausulPack")) {
                    ClausulPack cp = (ClausulPack) o;
                    pack.put(cp.getCodigo(),cp);
                }
            }
        } 
    }
    
    //variaveis de class
    private String marca; //marca do automovel
    private int idade; //idade do automovel
    private double valorComercial; //valor de mercado
    private String tipoVeiculo; //descricao do tipo de veiculo
    
    //construtores
       public SegVeiculo(){
        super();
        marca="";
        idade=-1;
        valorComercial=0;
        tipoVeiculo="";
    }
    
    
    public SegVeiculo(String codigo, double premioBase, double desconto, String modoPag, String codeClausulPack, String marca, String tipoVeiculo, int idade, int valorComercial) {
        super(codigo,premioBase,desconto,modoPag,pack.get(codeClausulPack));
        this.marca = marca;
        this.idade = idade;
        this.valorComercial = valorComercial;
        this.tipoVeiculo = tipoVeiculo;    
    }
        
    public SegVeiculo(SegVeiculo seg) {
        super(seg.getCodigo(),seg.getPremioBase(),seg.getDesconto(),seg.getModoPagamento(),seg.getClausulas());
        marca = seg.getMarca();
        idade = seg.getIdade();
        valorComercial = seg.getValorComercial();
        tipoVeiculo = seg.getTipoVeiculo();
    }
    
    //metodos de instancia
    /**
     * Devolve a marca do veiculo
     * 
     */
    public String getMarca() { return marca; }
    
    /**
     * Modifica a marca do veiculo
     * 
     * @param m Marca do automovel
     */
    public void setMarca(String m) { marca = m; }
    
    /**
     * Devolve a idade do veiculo
     * 
     */
    public int getIdade() { return idade; }
    
    /**
     * Modifica a idade do veiculo
     * 
     * @param i Idade do veiculo
     */ 
    public void setIdade(int i) { idade = i; }
     
    /**
     * Devolve o valor comercial do veiculo
     * 
     */
    public double getValorComercial() { return valorComercial; }
    
    /**
     * Modifica o valor comercial do veiculo
     * 
     * @param val Valor comercial do veiculo
     */
    public void setValorComercial(double val) { valorComercial = val; }

     
    /**
     * Devolve descrição do tipo de veiculo
     * 
     */
    public String getTipoVeiculo() { return tipoVeiculo; }
    
    /**
     * Modifica a descricao do tipo de veiculo
     * 
     * @param tipo Contem a descricao do tipo do veiculo
     */
    public void setTipoVeiculo(String tipo) { tipoVeiculo = tipo; }

     
    /**
     * Devolve reprensentacao do estado do objecto sob a forma de String
     * 
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\n\n------------ Segurado ------------\n");
        sb.append("Tipo de Veiculo: " + tipoVeiculo + "\n");
        sb.append("Marca: " + marca + "\n");
        sb.append("Idade: " + idade + "\n");
        sb.append("Valor Comercial: " + valorComercial + "\n");
        return sb.toString();
    }
   
    /**
     * Devolve boolean resultante da comparacao de dois seguros
     * 
     * @param o Objecto com o qual queremos fazer a comparacao
     */
    public boolean equals (Object o) {
        if (o==this)
            return true;
        if (o==null || o.getClass()!=this.getClass())
            return false;
        SegVeiculo sv = (SegVeiculo) o;
        return super.equals(sv) && this.tipoVeiculo.equals(sv.getTipoVeiculo())
                                && this.idade == sv.getIdade()
                                && this.marca.equals(sv.getMarca())
                                && this.valorComercial == sv.getValorComercial();
    }
 
    /**
     * Devolve uma copia do objecto
     * 
     */
    public SegVeiculo clone() { return new SegVeiculo(this); }
}
