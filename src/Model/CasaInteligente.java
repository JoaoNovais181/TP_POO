package Model;

import java.util.Map;
import java.util.stream.Collectors;

import java.util.List;
import java.util.HashMap;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class CasaInteligente implements Serializable
{

    private String nomeProprietario, NIFproprietario;
    private Map<String, SmartDevice> devices; // identificador -> SmartDevice
    private Map<String, List<String>> locations; // Espaço -> Lista codigo dos devices
    private Fornecedor fornecedor;
    private double valorUltimaFaturacao;
    private List<Fatura> faturas;

    public CasaInteligente() {
        this.nomeProprietario = "";
        this.NIFproprietario = "";
        this.devices = new HashMap<>();
        this.locations = new HashMap<>();
        this.fornecedor = null;
        this.valorUltimaFaturacao = 0;
        this.faturas = new ArrayList<>();
    }

    public CasaInteligente(String nomeProprietario, String NIFproprietario, Fornecedor fornecedor) {
        this.nomeProprietario = nomeProprietario;
        this.NIFproprietario = NIFproprietario;
        this.devices = new HashMap<>();
        this.locations = new HashMap<>();
        this.fornecedor = fornecedor;
        fornecedor.addCasa(this);
        this.valorUltimaFaturacao = 0;
        this.faturas = new ArrayList<>();
    }

    public CasaInteligente(CasaInteligente umaCasaInteligente)
    {
        this.nomeProprietario = umaCasaInteligente.getNomeProprietario();
        this.NIFproprietario = umaCasaInteligente.getNIFproprietario();
        this.devices = new HashMap<>();
        this.devices = (HashMap<String, SmartDevice>) umaCasaInteligente.getDevices().entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey() , entry -> entry.getValue()));
        this.locations = new HashMap<>();   
        this.locations = (HashMap<String, List<String>>) umaCasaInteligente.getLocations().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue()));
        this.valorUltimaFaturacao = 0;
        this.faturas = new ArrayList<>();
        this.faturas = umaCasaInteligente.getFaturas();
    }

    public String getNomeProprietario ()
    {
        return this.nomeProprietario;
    }

    public String getNIFproprietario ()
    {
        return this.NIFproprietario;
    }

    public HashMap<String, SmartDevice> getDevices ()
    {
        return (HashMap<String, SmartDevice>) this.devices.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
    }

    public HashMap<String, List<String>> getLocations ()
    {
        return (HashMap<String, List<String>>) this.locations.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
    }

    public Fornecedor getFornecedor ()
    {
        return this.fornecedor;
    }

    public double getValorUltimaFaturacao ()
    {
        return this.valorUltimaFaturacao;
    }

    public List<Fatura> getFaturas ()
    {
        return this.faturas;
    }

    public String toString ()
    {
        return "CasaInteligente{Nome: " + this.nomeProprietario + ", NIF: " + this.NIFproprietario + "}";
    }
    

    public void mudaFornecedor (Fornecedor novoFornecedor)
    {
        this.fornecedor.removeCasa(this);
        novoFornecedor.addCasa(this);
        this.fornecedor = novoFornecedor;
    }

    public double calculaCosumo (LocalDateTime atual, LocalDateTime nova)
    {
        return this.devices.values().stream().collect(Collectors.summingDouble(smartdevice->smartdevice.calculaConsumo(atual, nova)));
    }

    public double calculaFaturacao (LocalDateTime atual, LocalDateTime nova)
    {
        double consumo = this.calculaCosumo(atual, nova);
        double faturacao = this.fornecedor.faturacao(consumo, this.devices.size());
        this.valorUltimaFaturacao = faturacao;
        Fatura fatura = new Fatura(atual, nova, consumo, faturacao);
        this.faturas.add(fatura);
        return faturacao;
    }

    public void setDeviceOn(String devCode) {
        this.devices.get(devCode).turnOn();
    }
    
    public boolean existsDevice(String id) 
    {
        return this.devices.keySet().stream().anyMatch(str -> str.equals(id));
    }
    
    public void addDevice(SmartDevice s) 
    {
        this.devices.put(s.getID(), s);
    }
    
    public SmartDevice getDevice(String s) 
    {
        return this.devices.get(s);
    }
    
    public void setOn(String s, boolean b) 
    {
        this.devices.entrySet().stream().filter(entry -> s.equals(entry.getKey())).map(entry -> {entry.getValue().setOn(b); return entry;}).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    
    public void setAllOn(boolean b) 
    {
        this.devices.entrySet().stream().map(entry -> {entry.getValue().setOn(b); return entry;}).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }
    
    public void addRoom(String s) 
    {
        this.locations.put(s, new ArrayList<String>());
    }
    
    public boolean hasRoom(String s) 
    {
        return this.locations.keySet().stream().anyMatch(key -> s.equals(key));
    }
    
    public void addToRoom (String room, String sdID) 
    {
        if (this.hasRoom(room))
        {
            List<String> dev = this.locations.get(room);
            dev.add(sdID);
        }
        else
        {
            this.addRoom(room);
            this.addToRoom(room, sdID);
        }
    }

    public void addToRoom (String room, SmartDevice sd)
    {
        if (this.hasRoom(room))
        {
            List<String> dev = this.locations.get(room);
            dev.add(sd.getID());
        }
        else
        {
            this.addRoom(room);
            this.addToRoom(room, sd.getID());
        }
        this.devices.put(sd.getID(), sd);
    }
    
    public boolean roomHasDevice (String s1, String s2) 
    {
        if (this.hasRoom(s1))
            return this.locations.get(s1).stream().anyMatch(dev -> s2.equals(dev));
        return false;
    }
}
