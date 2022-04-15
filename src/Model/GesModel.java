package Model;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class GesModel {

    private HashMap<String, CasaInteligente> casas;
    private HashMap<String, Fornecedor> fornecedores;
    private LocalDateTime horaAtual;

    public GesModel ()
    {
        this.casas = new HashMap<>();
        this.fornecedores = new HashMap<>();
        this.horaAtual = LocalDateTime.now();
    }

    public GesModel (LocalDateTime t)
    {
        this.casas = new HashMap<>();
        this.fornecedores = new HashMap<>();
        this.horaAtual = t;
    }

    public LocalDateTime getHoraAtual ()
    {
        return this.horaAtual;
    }



    //                           Casa  

    public void addCasa (CasaInteligente casa)
    {
        this.casas.put(casa.getNIFproprietario(), casa);
    }

    public CasaInteligente getCasa (String NIF)
    {
        return this.casas.get(NIF);
    }

    public boolean existeCasaInteligente (String NIF)
    {
        return this.casas.containsKey(NIF);
    }

    public int getNumCasas ()
    {
        return this.casas.size();
    }

    //                  Smart Device

    public void addSmartDevice (SmartDevice sd, String NIFproprietario, String location)
    {
        this.casas.get(NIFproprietario).addToRoom(location, sd);
    }

    public SmartDevice getSmartDevice (String id, String NIFproprietario)
    {
        return this.casas.get(NIFproprietario).getDevice(id);
    }

    public boolean existeDevice (String id, String NIFproprietario)
    {
        return this.casas.get(NIFproprietario).existsDevice(id);
    }

    public List<CasaInteligente> getCasas ()
    {
        return this.casas.values().stream().collect(Collectors.toList());
    }

    public List<String> getNIFCasas ()
    {
        return this.casas.keySet().stream().collect(Collectors.toList());
    }

    //                   Fornecedor

    public void adicionarFornecedor (Fornecedor fornecedor)
    {
        this.fornecedores.put(fornecedor.getNome(), fornecedor);
    }

    public List<String> getNomeFornecedores ()
    {
        return this.fornecedores.keySet().stream().collect(Collectors.toList());
    }

    public int getNumFornecedores ()
    {
        return this.fornecedores.size();
    }

    public boolean existeFornecedor (String nome)
    {
        return this.fornecedores.containsKey(nome);
    }
    
    public void addFornecedor(Fornecedor f) 
    {
        this.fornecedores.put(f.getNome(), f);
    }

    public Fornecedor getFornecedor (String nome)
    {
        return this.fornecedores.get(nome);
    }

    //                       Outros

    public void calclulaFaturacao (LocalDateTime data)
    {
        for (CasaInteligente ci : this.casas.values())
            ci.calculaFaturacao(data);
    }


    // public void saveState ()
    // {
    //     Load_Save ls = new Load_Save();
    // }


}