
/**
 * Write a description of class SeguroGest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;

public class SeguroGest
{
    public void carregarClausulas() {
        ClausulPack.setClsAutoObrig(Clausula.readFile("ClausulasAutoOb.txt"));
        ClausulPack.setClsAutoOpcion(Clausula.readFile("ClausulasAutoOp.txt"));
        ClausulPack.setClsSegMedico(Clausula.readFile("ClausulasMedico.txt")); 
        ClausulPack.setClsSegVida(Clausula.readFile("ClausulasVida.txt"));         
        ClausulPack.setClsSegCasa(Clausula.readFile("ClausulasCasa.txt"));
    }
    
     /**
     * Operações de seguros auto
     */
    public void criarPackAuto() {
        ArrayList<String> cls = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        String clausula;
        String codigo = "";
        String descricao;
        boolean codValido = false;
        System.out.println("---- Novo pack automovel ----\n");
        System.out.println("Codigo: ");
        while (!codValido) {
            codigo = Input.lerString();
            if (SegVeiculo.existePack(codigo)) {
                System.out.println("Já existe pack com este código");
                System.out.println("Codigo: ");
            }
            else
                codValido = true;
        } 
        System.out.println("Insira descricao: ");
        descricao = Input.lerString();
        System.out.println("Insira os códigos das clausulas:    (para terminar insira 0)");
        clausula = Input.lerString();
        while (clausula.compareTo("0") != 0) {
            if (ClausulPack.existeClsAuto(clausula))
                cls.add(clausula);
            else
                System.out.println("Não existe nenhuma clausula com o código inserido");
            System.out.println("Codigo clausula: ");
            clausula = Input.lerString();
        }
        ClausulPack cp = new ClausulPack(codigo,descricao);
        cp.criarPackAuto(cls);
        SegVeiculo.addClsPack(cp.clone());
    }
    
    public void removerPackAuto() {
        Scanner sc = new Scanner(System.in);
        String codigo = "";
        System.out.println("Codigo do pack a remover: (para sair 0)");
        while (codigo.compareTo("0") != 0) {
            codigo = Input.lerString();
            if (SegVeiculo.existePack(codigo)) {
                SegVeiculo.removeClsPack(codigo);
                codigo = "0";
            }
            else {
                System.out.println("Não existe nenhum pack com o coigo introduzido.");
                System.out.println("Codigo: ");
            }
        }
    }
    
    public void verPacksAuto() {
        StringBuilder sb = new StringBuilder(" ------ Packs de Seguros automoveis ------\n");
        for(ClausulPack cp: SegVeiculo.getPacks())
            sb.append(cp.toString());
        System.out.println(sb.toString());
    }
    
    public void verClsAuto() {
         StringBuilder sb = new StringBuilder(" ------ Clausulas Auto Obrigatorias ------\n");
        for(Clausula c: ClausulPack.getClsAutoObrig())
            sb.append(c.toString());
        sb.append("\n\n------ Clausulas Auto Opcionais ------\n");    
        for(Clausula c: ClausulPack.getClsAutoOpcion())
            sb.append(c.toString());
        System.out.println(sb.toString());
    }
        
    /**
     * Operações de seguros Vida
     */
    public void criarPackVida() {
        ArrayList<String> cls = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        String clausula;
        String codigo = "";
        String descricao;
        boolean codValido = false;
        System.out.println("-------- Novo pack Vida -------\n");
        System.out.println("Codigo: ");
        while (!codValido) {
            codigo = Input.lerString();
            if (SegVida.existePack(codigo)) {
                System.out.println("Já existe pack com este código");
                System.out.println("Codigo: ");
            }
            else
                codValido = true;
        } 
        System.out.println("Insira descricao: ");
        descricao = Input.lerString();
        System.out.println("Insira os códigos das clausulas:    (para terminar insira 0)");
        clausula = Input.lerString();
        while (clausula.compareTo("0") != 0) {
            if (ClausulPack.existeClsVida(clausula))
                cls.add(clausula);
            else
                System.out.println("Não existe nenhuma clausula com o código inserido");
            System.out.println("Codigo clausula: ");
            clausula = Input.lerString();
        }
        ClausulPack cp = new ClausulPack(codigo,descricao);
        cp.criarPackVida(cls);
        SegVida.addClsPack(cp.clone());
    }
    
    public void removerPackVida() {
        Scanner sc = new Scanner(System.in);
        String codigo = "";
        System.out.println("Codigo do pack a remover: (para sair 0)");
        while (codigo.compareTo("0") != 0) {
            codigo = Input.lerString();
            if (SegVida.existePack(codigo)) {
                SegVida.removeClsPack(codigo);
                codigo = "0";
            }
            else {
                System.out.println("Não existe nenhum pack com o coigo introduzido.");
                System.out.println("Codigo: ");
            }
        }
    }
    
    public void verPacksVida() {
        StringBuilder sb = new StringBuilder(" --------- Packs de Seguros Vida -------\n");
        for(ClausulPack cp: SegVida.getPacks())
            sb.append(cp.toString());
        System.out.println(sb.toString());
    }
    
    public void verClsVida() {
        StringBuilder sb = new StringBuilder(" ---------- Clausulas Vida ------------\n");
        for(Clausula c: ClausulPack.getClsSegVida())
            sb.append(c.toString());
        System.out.println(sb.toString());
    }
    
    /**
     * Operações de seguros Medico
     */
    public void criarPackMedico() {
        ArrayList<String> cls = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        String clausula;
        String codigo = "";
        String descricao;
        boolean codValido = false;
        System.out.println("-------- Novo pack Medico -------\n");
        System.out.println("Codigo: ");
        while (!codValido) {
            codigo = Input.lerString();
            if (SegMedico.existePack(codigo)) {
                System.out.println("Já existe pack com este código");
                System.out.println("Codigo: ");
            }
            else
                codValido = true;
        } 
        System.out.println("Insira descricao: ");
        descricao = Input.lerString();
        System.out.println("Insira os códigos das clausulas:    (para terminar insira 0)");
        clausula = Input.lerString();
        while (clausula.compareTo("0") != 0) {
            if (ClausulPack.existeClsMedico(clausula))
                cls.add(clausula);
            else
                System.out.println("Não existe nenhuma clausula com o código inserido");
            System.out.println("Codigo clausula: ");
            clausula = Input.lerString();
        }
        ClausulPack cp = new ClausulPack(codigo,descricao);
        cp.criarPackMedico(cls);
        SegMedico.addClsPack(cp.clone());
    }
    
    public void removerPackMedico() {
        Scanner sc = new Scanner(System.in);
        String codigo = "";
        System.out.println("Codigo do pack a remover: (para sair 0)");
        while (codigo.compareTo("0") != 0) {
            codigo = Input.lerString();
            if (SegMedico.existePack(codigo)) {
                SegMedico.removeClsPack(codigo);
                codigo = "0";
            }
            else {
                System.out.println("Não existe nenhum pack com o coigo introduzido.");
                System.out.println("Codigo: ");
            }
        }
    }
    
    public void verPacksMedico() {
        StringBuilder sb = new StringBuilder(" --------- Packs de Seguros Medico -------\n");
        for(ClausulPack cp: SegMedico.getPacks())
            sb.append(cp.toString());
        System.out.println(sb.toString());
    }
    
    public void verClsMedico() {
        StringBuilder sb = new StringBuilder(" ---------- Clausulas Medico ------------\n");
        for(Clausula c: ClausulPack.getClsSegMedico())
            sb.append(c.toString());
        System.out.println(sb.toString());
    }
    
    /**
     * Operações de seguros Casa
     */
    public void criarPackCasa() {
        ArrayList<String> cls = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        String clausula;
        String codigo = "";
        String descricao;
        boolean codValido = false;
        System.out.println("-------- Novo pack Casa -------\n");
        System.out.println("Codigo: ");
        while (!codValido) {
            codigo = Input.lerString();
            if (SegCasa.existePack(codigo)) {
                System.out.println("Já existe pack com este código");
                System.out.println("Codigo: ");
            }
            else
                codValido = true;
        } 
        System.out.println("Insira descricao: ");
        descricao = Input.lerString();
        System.out.println("Insira os códigos das clausulas:    (para terminar insira 0)");
        clausula = Input.lerString();
        while (clausula.compareTo("0") != 0) {
            if (ClausulPack.existeClsCasa(clausula))
                cls.add(clausula);
            else
                System.out.println("Não existe nenhuma clausula com o código inserido");
            System.out.println("Codigo clausula: ");
            clausula = Input.lerString();
        }
        ClausulPack cp = new ClausulPack(codigo,descricao);
        cp.criarPackCasa(cls);
        SegCasa.addClsPack(cp.clone());
    }
    
    public void removerPackCasa() {
        Scanner sc = new Scanner(System.in);
        String codigo = "";
        System.out.println("Codigo do pack a remover: (para sair 0)");
        while (codigo.compareTo("0") != 0) {
            codigo = Input.lerString();
            if (SegCasa.existePack(codigo)) {
                SegCasa.removeClsPack(codigo);
                codigo = "0";
            }
            else {
                System.out.println("Não existe nenhum pack com o coigo introduzido.");
                System.out.println("Codigo: ");
            }
        }
    }
    
    public void verPacksCasa() {
        StringBuilder sb = new StringBuilder(" --------- Packs de Seguros Casa -------\n");
        for(ClausulPack cp: SegCasa.getPacks())
            sb.append(cp.toString());
        System.out.println(sb.toString());
    }
    
    public void verClsCasa() {
        StringBuilder sb = new StringBuilder(" ---------- Clausulas Casa ------------\n");
        for(Clausula c: ClausulPack.getClsSegCasa())
            sb.append(c.toString());
        System.out.println(sb.toString());
    }
    
    public void guardarPacks() throws IOException {
        SegVeiculo.guardarPacks();
        SegVida.guardarPacks();
        SegMedico.guardarPacks();
        SegCasa.guardarPacks();
    }
    
    public void carregaPacks() {
        SegVeiculo.carregaPacks();
        SegVida.carregaPacks();
        SegMedico.carregaPacks();        
        SegCasa.carregaPacks();
    }
        
    
    public static Seguro seguroT(){
        Scanner sc = new Scanner(System.in);
        String opcao;
        int sair=0;
        Seguro seg=null;
        
             System.out.println("Medico:1"); 
             System.out.println("Saude:2");
             System.out.println("Veiculo:3");
             System.out.println("Casa:4");
             while(sair==0){
                 opcao=Input.lerString();
                 if(opcao.equals("1")){
                     seg=new SegVida();
                     sair=1;
                 }
                 if(opcao.equals("2")){
                     seg=new SegVida();
                     sair=1;
                 }
                 if(opcao.equals("3")){
                     seg=new SegVeiculo();
                     sair=1;
                 }
                 if(opcao.equals("4")){
                     seg=new SegCasa();
                     sair=1;
                 }
             }
    return seg;
    }
    
        public static void menuNovoCliente(Seguradora sg){
        Scanner sc = new Scanner(System.in);
        String nome,morada,nif,bi;
        int idade;
        
             System.out.println("Idade:");  
             idade= Input.lerInt();
             System.out.println("Nome:");
             nome = Input.lerString();
             System.out.println("morada:");
             morada = Input.lerString();
             System.out.println("Nif");
             nif = Input.lerString();
             System.out.println("Bi:");
             bi = Input.lerString();
             sg.addCliente(idade,nome,morada,nif,bi);
             
     }
     
      
     
     
     
     public static void opcoes(){
         
            System.out.println("\n\n-----------Clientes------");
            System.out.println("Adicionar cliente: 1");  
            System.out.println("Remover cliente: 2");
            System.out.println("Compara clientes no ultimo ano: 3");
            System.out.println("Numero de clientes titulares de seguros 4");
            System.out.println("VerFichaCliente: 5");
            System.out.println("-------Seguros---------");
            System.out.println("Codigo dos titulares de determinado seguro: 6");
            System.out.println("Ver titular do seguro mais caro: 7");
            System.out.println("Extinguir seguro do cliente: 8");
            System.out.println("Adicionar seguro ao cliente: 9");
            System.out.println("Ver seguros com pagamento em atraso: 10");
            System.out.println("Pagamento actual de um seguro: 11");
            System.out.println("Ver seguros oferecidos: 12");
            System.out.println("-------Bolsa---------");
            System.out.println("Valores em bolsa da seguradora: 13");
            System.out.println("Alterar valor inicial em bolsa: 14");
            System.out.println("Alterar valor em bolsa: 15");
            System.out.println("sair:0\n\n");
        }
     
    public static void menu(Seguradora seg){
        Scanner sc = new Scanner(System.in);
        String opcao,codigo,codigo2;
        int sair=0;
        double valor=0;
        while(sair==0){
            opcoes();
            opcao = Input.lerString();
            if(opcao.equals("1")) menuNovoCliente(seg);
            if(opcao.equals("2")){
                System.out.println(" digite o nif do cliente a apagar");
                codigo=Input.lerString();
                seg.removerCliente(codigo);
            }
            if(opcao.equals("3")) System.out.println(seg.diferencaClientes());
            if(opcao.equals("4")) System.out.println(seg.numClientes());
            if(opcao.equals("5")){
                System.out.println(" digite o nif do cliente a consultar");
                codigo=Input.lerString();
                System.out.println(seg.fichaCliente(codigo));
            }
            if(opcao.equals("6")){
                Seguro seguro=seguroT();
                System.out.println((seg.titSegX(seguro)));
            }
            if(opcao.equals("7")) System.out.println( seg.titSegMaisCaro());
            if(opcao.equals("8")) {
                System.out.println(" digite o nif do cliente a remover o seguro");
                codigo=Input.lerString();
                System.out.println(" digite o nif do seguro a remover");
                codigo2=Input.lerString();
                seg.apagaSeguroCliente(codigo2,codigo);
            }
            if(opcao.equals("9")) {
                System.out.println(" digite o nif do cliente a adicionar o seguro");
                codigo=Input.lerString();
                System.out.println(" digite o nif do seguro a adicionar");
                codigo2=Input.lerString();
                seg.novoSegCliente(codigo,codigo2);
            }
            if(opcao.equals("10")) System.out.println(seg.toStringSegAtraso(seg));
            if(opcao.equals("11")){
                System.out.println(" digite o nif do cliente");
                codigo=Input.lerString();
                System.out.println(" digite o nif do seguro");
                codigo2=Input.lerString();
                System.out.println(seg.precoSeguro(codigo,codigo2));
            }
            if(opcao.equals("12")) System.out.println(seg.seguros());
            if(opcao.equals("13")) System.out.println(seg.toStringVendidos());
            if(opcao.equals("14")){
                Cotavel c= seg;
                System.out.println("valor em bolsa inicial");
                System.out.println(seg.getValorInicio());
                System.out.println("valor em bolsa ");
                System.out.println(c.getValor());
            }
            if(opcao.equals("15")){
                System.out.println("Valor inicial em bolsa");
                Cotavel c = seg;
                valor=Input.lerDouble();
                c.setValorInicio(valor);
            }
            if(opcao.equals("16")){
                System.out.println("diginte o novo valor");
                Cotavel c = seg;
                valor=Input.lerDouble();
                c.setValorBolsa(valor);
            }
            if(opcao.equals("0")) sair=1;
        }
        
    }
        
    /**
     * Le de ficheiro os clientes
     */
    public List<SegVeiculo> readAuto(String f) {
       ArrayList<SegVeiculo> segs= new ArrayList<SegVeiculo>();
       try {
           FileReader reader = new FileReader(f);
           BufferedReader in = new BufferedReader(reader);
           String codigo;
           double premioBase;
           double desconto;
           String modoPag;
           String codeClausulPack;
           String marca;
           String tipoVeiculo;
           int idade;
           int valorComercial;
           String string;
     
           while ((string = in.readLine()) != null) {
               codigo = string;
               premioBase = Double.parseDouble(in.readLine());
               desconto = Double.parseDouble(in.readLine());
               modoPag = in.readLine();
               codeClausulPack = in.readLine();
               marca = in.readLine();
               tipoVeiculo = in.readLine();
               idade = Integer.parseInt(in.readLine());
               valorComercial = Integer.parseInt(in.readLine());
               segs.add(new SegVeiculo(codigo,premioBase,desconto,modoPag,codeClausulPack,marca,tipoVeiculo,idade,valorComercial));
            }
            in.close();
        } catch (IOException e) { e.printStackTrace(); }
        return segs;
    }
    
    /**
     * Le de ficheiro os clientes
     */
    public List<SegMedico> readMedico(String f) {
       ArrayList<SegMedico> segs= new ArrayList<SegMedico>();
       try {
           FileReader reader = new FileReader(f);
           BufferedReader in = new BufferedReader(reader);
           String codigo;
           double premioBase;
           double desconto;
           String modoPag;
           String codeClausulPack;
           String doencas;
           int idade;
           String nome;
           String morada;
           String string;
           while ((string = in.readLine()) != null) {
               codigo = string;
               premioBase = Double.parseDouble(in.readLine());
               desconto = Double.parseDouble(in.readLine());
               modoPag = in.readLine();
               codeClausulPack = in.readLine();
               doencas = in.readLine();
               idade = Integer.parseInt(in.readLine());
               nome = in.readLine();
               morada = in.readLine();
               segs.add(new SegMedico(codigo,premioBase,desconto,modoPag,codeClausulPack,doencas,idade,nome,morada));
            }
            in.close();
        } catch (IOException e) { e.printStackTrace(); }
        return segs;
    }
        
    /**
     * Le de ficheiro os clientes
     */
    public List<SegVida> readVida(String f) {
       ArrayList<SegVida> segs= new ArrayList<SegVida>();
       try {
           FileReader reader = new FileReader(f);
           BufferedReader in = new BufferedReader(reader);
           String codigo;
           double premioBase;
           double desconto;
           String modoPag;
           String codeClausulPack;
           int idade;
           String nome;
           String morada;
           String string;
           while ((string = in.readLine()) != null) {
               codigo = string;
               premioBase = Double.parseDouble(in.readLine());
               desconto = Double.parseDouble(in.readLine());
               modoPag = in.readLine();
               codeClausulPack = in.readLine();
               idade = Integer.parseInt(in.readLine());
               nome = in.readLine();
               morada = in.readLine();
               segs.add(new SegVida(codigo,premioBase,desconto,modoPag,codeClausulPack,idade,nome,morada));
            }
            in.close();
        } catch (IOException e) { e.printStackTrace(); }
        return segs;
    }
    
    /**
     * Le de ficheiro os clientes
     */
    public List<SegCasa> readCasa(String f) {
       ArrayList<SegCasa> segs= new ArrayList<SegCasa>();
       try {
           FileReader reader = new FileReader(f);
           BufferedReader in = new BufferedReader(reader);
           String codigo;
           double premioBase;
           double desconto;
           String modoPag;
           String codeClausulPack;
           String morada;
           double valor;
           String string;
           
           while ((string = in.readLine()) != null) {
               codigo = string;
               premioBase = Double.parseDouble(in.readLine());
               desconto = Double.parseDouble(in.readLine());
               modoPag = in.readLine();
               codeClausulPack = in.readLine();
               morada = in.readLine();
               valor = Double.parseDouble(in.readLine());
               segs.add(new SegCasa(codigo,premioBase,desconto,modoPag,codeClausulPack,morada,valor));
            }
            in.close();
        } catch (IOException e) { e.printStackTrace(); }
        return segs;
    }
        
        
    public static Seguradora loadSeg(Seguradora bd,String f) {
    try {
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(f));
        bd = (Seguradora) oin.readObject();
        oin.close();
    }
    catch (IOException e) {System.out.println(e.getMessage());}
    catch (ClassNotFoundException e) {System.out.println(e.getMessage()); }
    return bd;
  }
        
    public static void main(String[] args){
        
        Seguradora seg = new Seguradora();
        seg=loadSeg(seg,"teste");     
        
       /*this.carregaPacks();
       List<SegCasa> segs1 = this.readCasa("segurosCasa.txt");
       for(SegCasa s: segs1)
            seg.addSeguro(s.clone());
       List<SegVida> segs2 = this.readVida("segurosVida.txt");
       for(SegVida s: segs2)
            seg.addSeguro(s.clone());
       List<SegMedico> segs3 = this.readMedico("segurosMedico.txt");
       for(SegMedico s: segs3)
            seg.addSeguro(s.clone());
       List<SegVeiculo> segs4 = this.readAuto("segurosAuto.txt");
       for(SegVeiculo s: segs4)
            seg.addSeguro(s.clone());*/
 
        
        //System.out.println(seg.seguros());

       
        menu(seg);
        seg.guardaSeguradora("teste");
        
        
        
    
    }
    
}
