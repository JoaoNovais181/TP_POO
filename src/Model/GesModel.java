package Model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.io.Serializable;

public class GesModel implements Serializable {

    private HashMap<String, SmartDevice> devices;
    private HashMap<String, CasaInteligente> casas;
    private HashMap<String, Fornecedor> fornecedores;
    private LocalDateTime horaAtual;
    private List<String> mudancas;

    public GesModel ()
    {
        this.devices = new HashMap<String, SmartDevice>();
        this.casas = new HashMap<String, CasaInteligente>();
        this.fornecedores = new HashMap<String, Fornecedor>();
        this.horaAtual = LocalDateTime.now();
        this.mudancas = new ArrayList<>();
    }

    public GesModel (LocalDateTime t)
    {
        this.devices = new HashMap<>();
        this.casas = new HashMap<>();
        this.fornecedores = new HashMap<>();
        this.horaAtual = t;
        this.mudancas = new ArrayList<>();
    }

    public GesModel (GesModel g)
    {
        this.devices = (HashMap<String,SmartDevice>) g.getDevices().stream().collect(Collectors.toMap(d -> d.getID(), d -> d.clone()));
        this.casas   = (HashMap<String,CasaInteligente>) g.getCasas().stream().collect(Collectors.toMap(c -> c.getNIFproprietario(), c->c.clone())); 
        this.fornecedores = (HashMap<String, Fornecedor>) g.getFornecedores().stream().collect(Collectors.toMap(f -> f.getNome(), f -> f.clone()));
        this.horaAtual = g.getHoraAtual();
        this.mudancas = g.getMudancas();
    }

    public LocalDateTime getHoraAtual ()
    {
        return this.horaAtual;
    }

    public List<String> getMudancas()
    {
        return this.mudancas.stream().collect(Collectors.toList());
    }

    public void AddMudanca (String s)
    {
        this.mudancas.add(s);
    }

    public void ParseMudancas () throws NumberFormatException, MudancaInvalidaException, FornecedorNaoExisteException, CasaNaoExisteException, SmartDeviceNaoExisteException
    {
        for (String mudanca : this.mudancas)
        {
            String[] tokens = mudanca.split("-");
            if (tokens[0].equals("Fornecedor"))
            {
                try 
                {
                    this.mudaFornecedor(tokens[1]);
                } 
                catch (NumberFormatException | FornecedorNaoExisteException | MudancaInvalidaException e) 
                {
                    e.printStackTrace();
                    throw e;
                }
            }
            else if (tokens[0].equals("Casa"))
            {
                try
                {
                    this.mudaCasa(tokens[1]);
                }
                catch (NumberFormatException | FornecedorNaoExisteException | MudancaInvalidaException e) 
                {
                    e.printStackTrace();
                    throw e;
                }
            }
            else if (tokens[0].equals("SmartDevice"))
            {
                try
                {
                    this.mudaDevice(tokens[1]);
                }
                catch (SmartDeviceNaoExisteException | MudancaInvalidaException e)
                {
                    e.printStackTrace();
                    throw e;
                }
            }
            else 
                throw new MudancaInvalidaException("Mudança: " + mudanca + " é inválida!");
        }
        this.mudancas = new ArrayList<>()   ;
    }

    public GesModel clone()
    {
        return new GesModel(this);
    }


    //                           Casa  

    public void mudaCasa (String mudanca) throws CasaNaoExisteException, FornecedorNaoExisteException, MudancaInvalidaException
    {
        String[] tokens = mudanca.split(",");

        if (!this.existeCasaInteligente(tokens[0]))
            throw new CasaNaoExisteException("Casa com NIF=" + tokens[0] + " não existe!");

        String[] tokens2 = tokens[1].split(":");

        if (tokens2[0].equals("Fornecedor"))
        {
            if (!this.existeFornecedor(tokens2[1]))
                throw new FornecedorNaoExisteException("Fornecedor com Nome=" + tokens2[1] + " não existe!");
            
            Fornecedor f = this.getFornecedor(tokens2[1]);

            this.getCasa(tokens[0]).mudaFornecedor(f);
        }
        else
            throw new MudancaInvalidaException("Mudanca a casa NIF=" + tokens[0] + " é inválida!");
    }

    public CasaInteligente casaMaisGastadora()
    {
        if (this.casas.values().stream().anyMatch(c -> c.getValorTotalFaturacao()==-1))
            return null;

        Comparator<CasaInteligente> comparator = (c1,c2) -> (Double.compare(c1.getValorTotalFaturacao(), c2.getValorTotalFaturacao()));

        return this.casas.values().stream().max(comparator).get();
    }

    public List<String> getMaioresConsumidores (LocalDateTime li, LocalDateTime ls)
    {
        List <CasaInteligente> h = new ArrayList<CasaInteligente>();

        for (CasaInteligente casa :this.casas.values())
            h.add(casa);

        h.sort((d1,d2) -> ((d2.getConsumoEntre(li, ls)-d1.getConsumoEntre(li, ls) > 0) ?1 :(d2.getConsumoEntre(li, ls)-d1.getConsumoEntre(li, ls)< 0) ?-1 :0));
        
        return h.stream().map(casa -> ("Consumo: " + casa.getConsumoEntre(li, ls) + "kWh -> " + casa.toString())).collect(Collectors.toList());
    }

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

    public void mudaDevice (String mudanca) throws SmartDeviceNaoExisteException, MudancaInvalidaException
    {
        String[] tokens = mudanca.split(",");

        if (!this.existeDevice(tokens[0]))
            throw new SmartDeviceNaoExisteException("SmartDevice com ID=" + tokens[0] + " não existe!");

        String[] tokens2 = tokens[1].split(":");

        if (tokens2[0].equals("SetOn"))
        {
            if (tokens2[1].equals("on"))
                this.devices.get(tokens[0]).setOn(true);
            else if (tokens2[1].equals("off"))
                this.devices.get(tokens[0]).setOn(false);
            else
                throw new MudancaInvalidaException("Mudança a SmartDevice com ID=" + tokens[0] + " é inválida!");
        }
        else
            throw new MudancaInvalidaException("Mudança a SmartDevice com ID=" + tokens[0] + " é inválida!");
    }

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

    public void mudaFornecedor (String mudanca) throws MudancaInvalidaException, NumberFormatException, FornecedorNaoExisteException
    {
        String[] tokens = mudanca.split(",");

        if (!this.existeFornecedor(tokens[0]))
            throw new FornecedorNaoExisteException("Fornecedor " + tokens[0] + " não existe!");

        String[] tokens2 = tokens[1].split(":");

        if (tokens2[0].equals("ValorBase"))
        {  
            double valorBase;
            try
            {
                valorBase = Double.parseDouble(tokens2[1]);
            }
            catch (NumberFormatException nfe)
            {
                nfe.printStackTrace();
                throw nfe;
            }
            this.getFornecedor(tokens[0]).setValorBase(valorBase);
        }
        else if (tokens2[0].equals("Imposto"))
        {
            double imposto;
            try
            {
                imposto = Double.parseDouble(tokens2[1]);
            }
            catch (NumberFormatException nfe)
            {
                nfe.printStackTrace();
                throw nfe;
            }
            this.getFornecedor(tokens[0]).setImposto(imposto);
        }
        else
            throw new MudancaInvalidaException("Mudanca a fornecedor " + tokens[0] + " é inválida!");
    } 

    public Fornecedor getFornecedorMaisFaturante ()
    {
        if (this.fornecedores.values().stream().anyMatch(f -> f.getVolumeFaturacao() < 0))
            return null;

        Comparator <Fornecedor> comparator = (f1,f2) -> (Double.compare(f1.getVolumeFaturacao(), f2.getVolumeFaturacao()));

        return this.fornecedores.values().stream().max(comparator).get();
    }

    public List<Fatura> getFaturasFornecedor (String nome)
    {
        return this.fornecedores.get(nome).getFaturas();
    }

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

    public void clearFaturas ()
    {
        for (CasaInteligente casa : this.casas.values())
            casa.clearFaturas();
    }

    // public void saveState ()
    // {
    //     Load_Save ls = new Load_Save();
    // }


}