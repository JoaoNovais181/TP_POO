// import java.util.Map;
import java.util.HashMap;

public class Controlador {

    private HashMap<String, CasaInteligente> casas;
    private HashMap<String, Fornecedor> fornecedores;

    public Controlador ()
    {
        this.casas = new HashMap<String, CasaInteligente>();
        this.fornecedores = new HashMap<String, Fornecedor>();
    }

    public void adicionarCasa (CasaInteligente casa)
    {
        this.casas.put(casa.getNIFproprietario(), casa);
    }

    public void adicionarFornecedor (Fornecedor fornecedor)
    {
        this.fornecedores.put(fornecedor.getNome(), fornecedor);
    }

    public static void main(String[] args) 
    {
        SmartSpeaker sp1 = new SmartSpeaker("1", "Megahits", 10), sp2 = new SmartSpeaker("2", "RFM", 5);
        SmartBulb sb1 = new SmartBulb("3", SmartBulb.WARM), sb2 = new SmartBulb("4", SmartBulb.COLD);
        Fornecedor f1 = new Fornecedor("EDP", 0.9, 0.7), f2 = new Fornecedor("Endesa", 0.7, 0.9);
        CasaInteligente ci1 = new CasaInteligente("Roz", "140", f1), ci2 = new CasaInteligente("T-Bag", "181", f2);
        ci1.addDevice(sp1);
        ci1.addToRoom("Sala", sp1.getID());
        ci1.addDevice(sb1);
        ci1.addToRoom("Quarto", sb1.getID());
        for (SmartDevice sd : ci1.getDevices().values())
        {
            System.out.println(sd.toString());
        }
        ci2.addDevice(sp2);
        ci2.addToRoom("Sala", sp2.getID());
        ci2.addDevice(sb2);
        ci2.addToRoom("Quarto", sb2.getID());
        for (SmartDevice sd : ci2.getDevices().values())
        {
            System.out.println(sd.toString());
        }
        System.out.println(ci1.toString() + "\n" + ci2.toString());
        // System.out.println(ci1.getFornecedor().getNome());
        System.out.println("Antes de mudar de fornecedor da casa 1\nCasas do fornecedor1");
        for (CasaInteligente ci : f1.getCasas().values())
        {
            System.out.println(ci.toString());
        }
        System.out.println("Casas do fornecedor2");
        for (CasaInteligente ci : f2.getCasas().values())
        {
            System.out.println(ci.toString());
        }
        ci1.mudaFornecedor(f2);
        System.out.println("Depois de mudar de fornecedor da casa 1\nCasas do fornecedor1");
        for (CasaInteligente ci : f1.getCasas().values())
        {
            System.out.println(ci.toString());
        }
        System.out.println("Casas do fornecedor2");
        for (CasaInteligente ci : f2.getCasas().values())
        {
            System.out.println(ci.toString());
        }
    }
}