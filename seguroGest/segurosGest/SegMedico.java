
/**
 * Abstract class SegurVeiculo - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.Collections;
import java.io.*;
import java.util.*;
import static java.lang.System.out;

public class SegMedico extends Seguro
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
    public static void setPacksMedicos(List<ClausulPack> list) {
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
        FileOutputStream fos = new FileOutputStream("packsMedico.dat");
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
            ois = new ObjectInputStream(new FileInputStream("packsMedico.dat"));
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
    
    //variaveis de instancia
    private String nome; //nome do segurado
    private int idade; //idade do segurado
    private String morada; //morada do segurado
    private String doencas; //doencas do segurado
    
        public SegMedico(){
        super();
        nome="";
        morada="";
        idade=-1;
        doencas="";
    }
    
    
    
    public SegMedico(String codigo, double premioBase, double desconto, String modoPag, String codeClausulPack, String doencas,int idade, String nome, String morada) {
        super(codigo,premioBase,desconto,modoPag,pack.get(codeClausulPack));
        this.nome = nome;
        this.idade = idade;
        this.morada = morada;        
        this.doencas = doencas;
    }
    
    public SegMedico(SegMedico seg){
        super(seg.getCodigo(),seg.getPremioBase(),seg.getDesconto(),seg.getModoPagamento(),seg.getClausulas());
        nome = seg.getNome();
        idade = seg.getIdade();
        morada = seg.getMorada();
        doencas = seg.getDoencas();
    }
    
    /**
     * Devolve a idade do segurado
     * 
     */
    public int getIdade() {return idade;}
    
    /**
     * Modifica a idade do segurado
     * 
     * @param idade Idade do segurado
     */
    public void setIdade(int idade){this.idade=idade;}

    /**
     * Devolve a nome do segurado
     * 
     */    
    public String getNome() {return nome;}
    
    /**
     * Modifica a nome do veiculo
     * 
     * @param nome Nome do segurado
     */
    public void setNome(String nome){this.nome=nome;}
   
    /**
     * Devolve a morada do segurado
     * 
     */    
    public String getMorada() {return morada;}
    
    /**
     * Modifica a morada do veiculo
     * 
     * @param morada Morada do segurado
     */
    public void setMorada(String morada){this.morada=morada;}
   
    /**
     * Devolve a doencas do segurado
     * 
     */    
    public String getDoencas() {return doencas;}
    
    /**
     * Modifica as doencas do segurado
     * 
     * @param doencas doencas do segurado
     */
    public void setDoencas(String doencas){this.doencas=doencas;}
   
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
        SegMedico sm = (SegMedico) o;
        return super.equals(sm) && this.doencas.equals(sm.getDoencas())
                                && this.idade == sm.getIdade()
                                && this.morada.equals(sm.getMorada())
                                && this.nome.equals(sm.getNome());
    }    
    
    /**
     * Devolve reprensentacao do estado do objecto sob a forma de String
     * 
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\n\n------------ Segurado ------------\n");
        sb.append("Nome: " + nome + "\n");
        sb.append("Idade: " + idade + "\n");        
        sb.append("Morada: " + morada + "\n");        
        sb.append("Doencas: " + doencas + "\n");
        return sb.toString();
    }
    
    /**
     * Devolve uma copia do objecto
     * 
     */
    public SegMedico clone() {return new SegMedico(this);}
    
}
