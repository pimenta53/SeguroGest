
/**
 * Write a description of class Seguradora here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;

public class Seguradora implements Cotavel,Serializable
{
   
    private double valorBolsaInicio;
    private double valorBolsa;
    private Histórico historico;
    private TreeMap<String,FichaCliente> contratos;
    private TreeMap<String,Seguro> seguros;
    private TreeMap<String,Integer> vendidos;
    
    /**
     * Constructor vazio de uma seguradora
     */
    public Seguradora() {
        valorBolsaInicio = 0;
        valorBolsa=valorBolsaInicio;
        contratos=new TreeMap<String,FichaCliente>();
        seguros = new TreeMap<String,Seguro>();
        historico = new Histórico();
        vendidos =  new TreeMap<String,Integer>();
    }
    public Seguradora(double inicio,double valor,TreeMap<String,FichaCliente> cls,TreeMap<String,? extends Seguro> sgs) {
          valorBolsaInicio=inicio;
          valorBolsa=valor;
          contratos= new TreeMap<String ,FichaCliente> ();
          seguros = new TreeMap<String,Seguro>();
          historico =new Histórico();
          for(FichaCliente fc : cls.values()){
            contratos.put(fc.getTitular().getNif(), fc.clone());
            historico.somaHistorico();
        }
         for(Seguro sg : sgs.values()){
            seguros.put(sg.getCodigo(), sg.clone()); 
        }
    }
    /**
     * Construtor copia de uma seguradora
     */
   public Seguradora(Seguradora sg){
       valorBolsaInicio=sg.getValorInicio();
       valorBolsa=sg.getValor();
       contratos= new TreeMap<String,FichaCliente> ();
        Set<FichaCliente> val = sg.getContratos();
        seguros= new TreeMap<String,Seguro> ();
        historico = new Histórico();
        Set<? extends Seguro> segs = sg.getSeguros();
        for(FichaCliente fc : val){
            contratos.put(fc.getTitular().getNif(), fc.clone());
            historico.somaHistorico();
        }
        for(Seguro seg : segs){
            seguros.put(seg.getCodigo(),seg.clone());
        }
    }
    
    public double getValorInicio(){ return valorBolsaInicio;}
    public double getValor(){ return valorBolsa;}
    
    public void setValorInicio(double valor){valorBolsaInicio=valor;}
    public void setValorBolsa(double valor){valorBolsa=valor;}
    
    
    /**
     * Obeter colecçao de seguros oferecidos pela seguradora
     */
    public Set<? extends Seguro> getSeguros (){
        TreeSet<Seguro> segs = new TreeSet<Seguro> ();
        Collection<Seguro> sgs = seguros.values();
        for(Seguro sg : sgs){
            segs.add(sg.clone()); 
        }   
    return segs;   
    }

    
    public TreeSet<FichaCliente> getContratos (){
        TreeSet<FichaCliente> contr = new TreeSet<FichaCliente> ();
        Collection<FichaCliente> fc = contratos.values();
        for(FichaCliente f : fc){
            contr.add(f.clone());  
        }   
    return contr;   
    }
    /**
     * Total de clientes que estão inscritos na seguradora e que possuem seguros de momento
     */
    public int numClientes(){ 
        int total=0;
        for(FichaCliente fc :contratos.values()){
            total+=(fc.getSegs()).size();
        }         
    return total;
    }
    /**
     * Adicionar um novo cliente á empresa
     */
    public void addCliente(int idade,String nome,String morada,String bi,String nif){
        Titular t = new Titular(idade,nome,morada,nif,bi);
        FichaCliente fc = new FichaCliente();
        fc.setTitular(t.clone());
        contratos.put(nif,fc.clone());
        historico.somaHistorico();
    }
    
    /**
     * Remover cliente que não tenha seguros
     */
    public void removerCliente(String nif){
        FichaCliente fc = contratos.get(nif);
        if(fc!=null){
            if(fc.numSeguros()==0) contratos.remove(nif);
        }
        historico.subtraiHistorico();
    }
    
    /**
     * Adicionar seguro aos disponiveis pela empresa
     */
    public void addSeguro(Seguro s){
        seguros.put(s.getCodigo(),s.clone());
        vendidos.put(s.getCodigo(),0);
    }
    /**
     * Remover seguro dos seguros disponiveis pela empresa
     */
    public void removeSeguro(String cod){
        seguros.remove(cod);
        vendidos.put(cod,(vendidos.get(cod))-1);
    }
    /**
     * Extinguir o seguro de um determinado cliente
     */
    public void apagaSeguroCliente(String codSeg,String codCliente){
        FichaCliente fc= contratos.get(codCliente);
        if(fc!=null){
            fc.removeSeguro(codSeg);
            fc.setPremio(fc.totalPagar());
            if (vendidos.get(codSeg)!=0)
                vendidos.put(codSeg,(vendidos.get(codSeg))-1);
        }
   }
    
    /**
     * Adicionar um seguro novo a um cliente que não tenha pagamentos de seguros em atraso
     */
    public void novoSegCliente(String codCl, String codSeg){
        Seguro novoS = (seguros.get(codSeg));
        FichaCliente novaF = contratos.get(codCl);
        if(novaF!=null && novoS!=null){
            if(novaF.pagAtraso()==0){
                novaF.adicionaSeguro(codSeg,novoS.clone());        
                novaF.setPremio(novaF.totalPagar());
                vendidos.put(codSeg,(vendidos.get(codSeg))+1);
            }
        }
    }
    
    /**
     * Coleção de todos os seguros em atraso pelos diverso clientes
     */
    public Set<Seguro> pagamentosAtraso(){
        TreeSet<Seguro> segsF = new TreeSet<Seguro>();
        for(FichaCliente fc : contratos.values()){
            if(fc.pagAtraso()>0)  segsF.addAll(fc.segsAtraso());
        }
    return segsF;
    }
        /**
         * String com os dados do titular do seguro mais caro
         */
       public String titSegMaisCaro(){
        StringBuilder s = new StringBuilder("----Tutlar do seguro mais caro----\n");
        Titular t=null;
        double maior=0,valor=0;
        for(FichaCliente f: contratos.values()){
            valor=f.seguroMaisCaro();
            if(valor>maior){
                maior=valor;
                t=f.getTitular();
            }
        }
        if(t==null) s.append("não existe");
        else s.append(t.toString());
        return s.toString();
    }
    
    
    /**
     * Preco de um determinado seguro
     */
        public double precoSeguro(String codT,String codS){
        FichaCliente fc =contratos.get(codT);
        Seguro s = seguros.get(codS);
        double premio=0;
        if(s!=null && fc!=null){
            premio=fc.precoSeguro(codS);
        }
        return premio;
    }
   
   /* public Set<String> titularesSeguroX(String cod){
        Seguro x= seguros.get(cod).clone();
        if(x!=null){
            */
    
    
    /**
     * String com os seguros com pagamento em atraso
     */
    public String toStringSegAtraso(Seguradora x){
       StringBuilder s = new StringBuilder("----------- Seguros Atraso --------\n");
       for(Seguro seg: x.pagamentosAtraso()){
           s.append(seg.getCodigo());s.append("\n");
        }
    return s.toString();
    }
    
    /**
     * Ficha individual de determinado cliente
     */
    public String fichaCliente(String codCliente){
        StringBuilder s = new StringBuilder("----------- FICHA CLIENTE --------\n");
        FichaCliente fc =contratos.get(codCliente);
        if(fc==null) s.append("Não existe\n");
        else s.append(fc.toString());
        return s.toString();
    }
 
    /**
     * Colecção de todos os seguros disponiveis
     */
    public Set<Seguro> collectionSeg(){
        TreeSet<Seguro> sgs = new TreeSet<Seguro> ();
        for(Seguro seg : seguros.values()){
            sgs.add(seg.clone());
        }
    return sgs;
    }
    
    /**
     * String de todos os seguros disponiveis
     */
    public String seguros(){
        StringBuilder s = new StringBuilder("----------- Seguros --------\n");
        for(Seguro seg : seguros.values()){
            s.append(seg.toString());
        }
    return s.toString();
    }
    
    /**
     * Total de seguros oferecidos aos clientes pela seguradora
     */
    public int numSeguros(){ 
        return seguros.size();
    }
        
    /**
     * Transformar em string todos os dados da seguradora
     */
   public String toString (){
       StringBuilder s = new StringBuilder("----Seguradora----");
       Collection<Seguro> segs = seguros.values();
       Collection<FichaCliente> fc = contratos.values();
       s.append("\nValor inicial em bolsa:");s.append(valorBolsaInicio);
       s.append("\nValor actual em bolsa:");s.append(valorBolsa);
       s.append("\n---------Lista de seguros disponiveis---------\n");
       for(Seguro seg : segs){
           s.append(seg.toString());
        }
       s.append("\n-------Lista de clientes----------- \n");
        for(FichaCliente f: fc){
            s.append(f.toString());
       }
       return s.toString();
   }
    
   public Seguradora clone(){
        return new Seguradora(this);
    }
    
    public void guardaSeguradora(String name) {
       
        try{
     ObjectOutputStream oout= new ObjectOutputStream(new FileOutputStream(name));
            oout.writeObject(this);
            oout.flush();oout.close();
        }
        catch(IOException e){e.printStackTrace();}
        
    }
    
    public int diferencaClientes(){
        return historico.diferenca();
    }
    
    public void editar(double preco,String code){
        Seguro s= seguros.get(code);
        if(s!=null) s.setPremioBase(preco);
    }
     public void dever(String code,String seg){
        FichaCliente f= contratos.get(code);
        Seguro s;
        if(f!=null){
            s=f.getSeguro(seg);
            if(s!=null) s.setEmDivida(true);
            
        }
    }
    
    
    public String toStringVendidos (){
       StringBuilder s = new StringBuilder("----Vendidos----\n");
       Collection<String> segC = vendidos.keySet();
       for(String seg : segC){
           s.append(seg.toString());
           s.append((vendidos.get(seg)).toString());
        }
       return s.toString();
   }   
   
      public Set<String> titularesSeguroX(Seguro s){
        Set<String> codigos = new TreeSet<String> ();
        for(FichaCliente f : contratos.values()) {
            if(f.getSeguro(s.getCodigo()).getClass().equals(s)) codigos.add(f.getTitular().getNif()); 
        }   

    return codigos;
    }
    
    public String titSegX(Seguro seg){
       StringBuilder s = new StringBuilder("----------- Titulares do seguro --------\n");
        Set<String> tit = titularesSeguroX(seg);
        for(String ss:tit){
            s.append(ss+" ");
        }
        return s.toString();
    }
   
}
