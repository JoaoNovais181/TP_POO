package Model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.io.Serializable;

public class GesModel implements Serializable {

    private HashMap<String, SmartDevice> devices;
    private HashMap<String, CasaInteligente> casas;
    private HashMap<String, Fornecedor> fornecedores;
    private LocalDateTime horaAtual;

    public GesModel ()
    {
        this.devices = new HashMap<String, SmartDevice>();
        this.casas = new HashMap<String, CasaInteligente>();
        this.fornecedores = new HashMap<String, Fornecedor>();
        this.horaAtual = LocalDateTime.now();
    }

    public GesModel (LocalDateTime t)
    {
        this.devices = new HashMap<>();
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

    public CasaInteligente getCasa (String NIF) throws CasaNaoExisteException
    {
        CasaInteligente r = this.casas.get(NIF);
        if (r == null)
            throw new CasaNaoExisteException("Nao existe casa no modelo com NIF=" + NIF);
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

    public List<CasaInteligente> getCasas ()
    {
        return this.casas.values().stream().map(c -> c.clone()).collect(Collectors.toList());
    }

    public List<String> getNIFCasas ()
    {
        return this.casas.keySet().stream().collect(Collectors.toList());
    }

    //                  Smart Device

    public void addSmartDevice (SmartDevice sd, String NIFproprietario, String location)
    {
        this.casas.get(NIFproprietario).addToRoom(location, sd);
        this.devices.put(sd.getID(), sd);
    }

    public SmartDevice getSmartDevice (String id, String NIFproprietario) throws CasaNaoExisteException, SmartDeviceNaoExisteException 
    {
        if (!this.existeCasaInteligente(NIFproprietario))
            throw new CasaNaoExisteException("Não existe casa no modelo com NIF=" + NIFproprietario);
        
        // Se o smartdevice nao existir na casa o metodo sd levanta SmartDeviceNaoExisteException
        SmartDevice sd = this.casas.get(NIFproprietario).getDevice(id).clone();
        
        return sd;
    }

    public boolean existeDevice (String id, String NIFproprietario)
    {
        return this.casas.get(NIFproprietario).existsDevice(id);
    }

    public boolean existeDevice (String id)
    {
        return this.devices.containsKey(id);
    }


    public List<SmartDevice> getDevices ()
    {
        return this.devices.values().stream().collect(Collectors.toList());
    }

    // private sortDevices ()
    // {

    // }

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

    public List<Fornecedor> getFornecedores ()
    {
        return this.fornecedores.values().stream().collect(Collectors.toList());
    }

    //                       Outros

    public void calclulaFaturacao (LocalDateTime data)
    {
        for (CasaInteligente ci : this.casas.values())
            ci.calculaFaturacao(this.horaAtual, data);
        this.horaAtual = data;
    }

    public List<Fatura> getFaturas()
    {
        List<Fatura> list = new ArrayList<>();
        for (List<Fatura> l : this.casas.values().stream().map(CasaInteligente::getFaturas).collect(Collectors.toList()))
            list.addAll(l);
        return list;
    }


    // public void saveState ()
    // {
    //     Load_Save ls = new Load_Save();
    // }


}