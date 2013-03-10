
/**
 * Write a description of class SegCasa here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;

public class SegCasa extends Seguro
{
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
    public static void setPack(List<ClausulPack> list) {
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
        FileOutputStream fos = new FileOutputStream("packsCasa.dat");
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
            ois = new ObjectInputStream(new FileInputStream("packsCasa.dat"));
            while ((o=ois.readObject())!=null) {
                if(o.getClass().getSimpleName().equals("ClausulPack")) {
                    ClausulPack cp = (ClausulPack) o;
                    pack.put(cp.getCodigo(),cp);
                }
            }
        }
        catch(IOException e) {System.out.println(e.getMessage());}
        catch(ClassNotFoundException e) {}
        finally { try { if (ois!=null)
                           ois.close();
                        } catch (IOException e) {/*tratamento*/}
        }
    }

    //vairiaveis de instancia
    private String morada; //local do imovel
    private double valorComercial;//valor do imovel

        public SegCasa(){
        super();
        morada="";
        valorComercial=0;
    }
    
    
    
    public SegCasa(String codigo, double premioBase, double desconto, String modoPag, String codeClausulPack, String morada, double valor) {
        super(codigo,premioBase,desconto,modoPag,pack.get(codeClausulPack));
        this.morada=morada;
        this.valorComercial=valor;
    }
    
    public SegCasa(SegCasa seg){
        super(seg.getCodigo(),seg.getPremioBase(),seg.getDesconto(),seg.getModoPagamento(),seg.getClausulas());
        morada=seg.getMorada();
        valorComercial = seg.getValorComercial();
    }
    
    /**
     * Devolve a morada do segurado
     * 
     */
    public String getMorada(){return morada;}
    
    /**
     * Modifica a morada do veiculo
     * 
     * @param morada Morada do segurado
     */
    public void setMorada(String morada){ this.morada = morada; }
    
    public double getValorComercial(){ return valorComercial;}
    
    /**
     * Modifica o valor comercial do segurado
     * 
     * @param valor Valor comercial do segurado
     */
    public void setValorComercial(int valor){ valorComercial = valor;}
    
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
        SegCasa sm = (SegCasa) o;
        return super.equals(sm) && this.morada.equals(sm.getMorada())
                                && this.valorComercial == sm.getValorComercial();
    }   
    
    /**
     * Devolve reprensentacao do estado do objecto sob a forma de String
     * 
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\n\n------------ Segurado ------------\n");        
        sb.append("Valor Comercial: " + valorComercial + "\n");        
        sb.append("Morada: " + morada + "\n");       
        return sb.toString();
    }    
   
    /**
     * Devolve uma copia do objecto
     * 
     */
    public SegCasa clone() { return new SegCasa(this); }
}
